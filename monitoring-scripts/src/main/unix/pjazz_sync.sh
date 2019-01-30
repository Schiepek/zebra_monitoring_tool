#!/bin/bash

set -o errexit
set -o pipefail
set -o nounset

host=10.5.2.31
user=mey07

# profiling
echo "Profiling files"
echo "---------------------------"
/opt/zebra/monitoring/sync/sync.sh \
	-h ${host} \
	-u ${user} \
	-r /opt/ergon/profiling/jazzproduktion \
	-s /data/zebra/monitoring/coop.pjazz/remote-sync/profiling \
	-t /data/zebra/monitoring/coop.pjazz/profiling/pending