#!/bin/bash

# How this script works:
# - Copies the previous rsync result to a tmp folder
# - Checks for the latest timestamp in the tmp folder
# - Executes rsync
# - Finds all files with a timestamp that is newer than the old "latest timestamp"
# - Creates zip files and moves them to the pending folder

set -o errexit
set -o pipefail
set -o nounset

usage() { 
	echo ""
	echo "Usage: $0 -h <string> -u <string> -r <string> -s <string> -t <string>"
	echo ""
	echo "h: IP of the remote host"
	echo "u: User as which to log in on the remote host"
	echo "r: Remote folder that contains the log files"
	echo "s: Local folder that can be used by the synchronization script for temporary files"
	echo "t: Folder to which the new zip files should be written (e.g. a pending folder)"
	echo ""
	exit 1; 
}

# check that correct number of arguments has been set
num_args=$#
if [ $num_args -ne 10 ]; then
  usage
fi

while getopts ":h:u:r:s:t:" o; do
	case "${o}" in
		h)
			remote_ip=${OPTARG}
			;;
		u)
			remote_user=${OPTARG}
			;;
		r)
			remote_source=${OPTARG}
			;;
		s)
			sync_folder=${OPTARG}
			;;
		t)
			target_folder=${OPTARG}
			;;
		*)
			usage
			;;
	esac
done
shift $((OPTIND-1))

# internal
rsync_folder=${sync_folder}/rsync
tmp_folder=${sync_folder}/tmp
timestamp_file=${sync_folder}/timestamp.tmp

function cleanup {
	if [ -e "${tmp_folder}" ]; then
		rm -R ${tmp_folder} 
	fi
	if [ -e "${timestamp_file}" ]; then
		rm ${timestamp_file} 
	fi
}

function createZip {
	local file=$1
	# filename without extension
	filename=$(basename "$file" .log)
	echo "   Processing ${filename}..."
	zip -j ${target_folder}/${filename}.zip ${file} 1>/dev/null
}

# prepare
mkdir -p ${sync_folder}
mkdir -p ${rsync_folder}

# cleanup
cleanup

# create a copy of the previous rsync result (keep timestamps)
echo "Creating a working copy of the previous rsync result"
cp -rfp ${rsync_folder} ${tmp_folder} 

# check for latest modification timestamp
latestFile=$(find ${tmp_folder} -type f -printf '%T@ %p\n' | sort -n | tail -1 | cut -f2- -d" ")
echo "   Latest file: ${latestFile}"
# check if there was a latest file
if [ -n "${latestFile}" ]; then
	# keep timestamps
	cp -rfp ${latestFile} ${timestamp_file}
	echo "   Moved copy to ${timestamp_file}"
fi

# rsync (to the tmp folder)
echo "Syncing with remote host..."
rsync --delete -az --include='*.log' --exclude='*' ${remote_user}@${remote_ip}:${remote_source}/ ${tmp_folder}

# find changed files
if [ -n "${latestFile}" ]; then
	echo "Creating zip files (Incremental sync)..."
	find ${tmp_folder} -newer ${timestamp_file} -name '*.log' | while read line; do
		createZip ${line}
	done
else
	echo "Creating zip files (Initial sync)..."
	find ${tmp_folder} -name '*.log' | while read line; do
		createZip ${line}
	done
fi

# no errors: update the rsync folder
rm -R ${rsync_folder} 
cp -rfp ${tmp_folder} ${rsync_folder}

# cleanup
cleanup

echo ""
echo "-> Success"
echo ""