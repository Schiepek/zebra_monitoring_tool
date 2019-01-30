#!/bin/bash

twiddle="/opt/jboss/jboss-6.0.0.Final-Coop/bin/twiddle.sh"
ip="10.5.2.30"
port="1390"
username="admin"
password="<password>"

totalHeapCommand="get "jboss.system:type=ServerInfo" TotalMemory"
freeHeapCommand="get "jboss.system:type=ServerInfo" FreeMemory"

twiddleTotalHeap="$twiddle -o$ip -r $port -u $username -p $password $totalHeapCommand"
twiddleFreeHeap="$twiddle -o$ip -r $port -u $username -p $password $freeHeapCommand"

totalHeapInBytes=$($twiddleTotalHeap)
freeHeapInBytes=$($twiddleFreeHeap)

megaBytes=1048576

totalHeap=$(($totalHeapInBytes/$megaBytes))
freeHeap=$(($freeHeapInBytes/$megaBytes))

echo $totalHeap
echo $freeHeap