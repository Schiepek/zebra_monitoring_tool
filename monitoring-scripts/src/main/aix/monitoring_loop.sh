#!/bin/bash

while true; do
	$(./jazzentwicklung/cpu/monitoring_cpu.sh)
	$(./jazzentwicklung/memory/monitoring_memory.sh)
	sleep 60
done