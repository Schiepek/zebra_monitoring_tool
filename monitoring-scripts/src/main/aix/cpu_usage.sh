#!/bin/bash

# cpu: vp * smt
# iostat -> first two lines only -> split by space -> split by = sign
numberOfCpus=$(iostat | sed -n '2p' | cut -d ' ' -f 3 | cut -d '=' -f 2)

# vp (nmon terminology), each line in the output represents a single cpu
# lsdev -> count lines -> trim
numberOfPhysicalCpus=$(lsdev -Cc processor | wc -l | tr -d ' ')

# entitled capacity (nmon terminology)
# mpstat -> last line only -> replace all whitespace with space -> split by space
ec=$(mpstat 1 1 | tail -1 | tr -s ' ' | cut -d ' ' -f 17)

#use `bc` for FP division
cpuLoad=$(bc <<< "scale=2; $ec / $numberOfPhysicalCpus")

if [ -n "$1" ]; then
	#use `bc` for FP division
	smt=$(bc <<< "scale=0; $numberOfCpus/ $numberOfPhysicalCpus")

	echo "CPUs: $numberOfCpus"
	echo "VP: $numberOfPhysicalCpus"
	echo "SMT: $smt"
	echo "EC: $ec"
	echo "CPU load: $cpuLoad"
else
	echo $cpuLoad
fi

exit 0