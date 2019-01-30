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
	-r /opt/ergon/profiling/jazzentwicklung \
	-s /data/zebra/monitoring/coop.ejazz/remote-sync/profiling \
	-t /data/zebra/monitoring/coop.ejazz/profiling/pending
	
# cpu
echo "CPU files"
echo "---------------------------"
/opt/zebra/monitoring/sync/sync.sh \
	-h ${host} \
	-u ${user} \
	-r /opt/ergon/monitoring/jazzentwicklung/cpu \
	-s /data/zebra/monitoring/coop.ejazz/remote-sync/cpu \
	-t /data/zebra/monitoring/coop.ejazz/cpu/pending
	
# memory
echo "Memory files"
echo "---------------------------"
/opt/zebra/monitoring/sync/sync.sh \
	-h ${host} \
	-u ${user} \
	-r /opt/ergon/monitoring/jazzentwicklung/memory \
	-s /data/zebra/monitoring/coop.ejazz/remote-sync/memory \
	-t /data/zebra/monitoring/coop.ejazz/memory/pending