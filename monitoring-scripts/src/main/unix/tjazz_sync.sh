#!/bin/bash

set -o errexit
set -o pipefail
set -o nounset

host=10.5.2.30
user=mey07

# profiling
echo "Profiling files"
echo "---------------------------"
/opt/zebra/monitoring/sync/sync.sh \
	-h ${host} \
	-u ${user} \
	-r /opt/ergon/profiling/jazztest \
	-s /data/zebra/monitoring/coop.tjazz/remote-sync/profiling \
	-t /data/zebra/monitoring/coop.tjazz/profiling/pending
	
# cpu
echo "CPU files"
echo "---------------------------"
/opt/zebra/monitoring/sync/sync.sh \
	-h ${host} \
	-u ${user} \
	-r /opt/ergon/monitoring/jazztest/cpu \
	-s /data/zebra/monitoring/coop.tjazz/remote-sync/cpu \
	-t /data/zebra/monitoring/coop.tjazz/cpu/pending
	
# memory
echo "Memory files"
echo "---------------------------"
/opt/zebra/monitoring/sync/sync.sh \
	-h ${host} \
	-u ${user} \
	-r /opt/ergon/monitoring/jazztest/memory \
	-s /data/zebra/monitoring/coop.tjazz/remote-sync/memory \
	-t /data/zebra/monitoring/coop.tjazz/memory/pending