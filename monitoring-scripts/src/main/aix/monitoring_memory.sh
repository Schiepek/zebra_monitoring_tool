#!/bin/bash

directory="/opt/ergon/monitoring/jazzentwicklung/memory"
filename="${directory}/memory_usage.log"
	
writeArffHeader () { 
   echo "@RELATION MemoryUsage" > $filename
   echo "@ATTRIBUTE sysdatetime date \"dd.MM.yyyy HH:mm:ss\"" >> $filename
   echo "@ATTRIBUTE totalHeap numeric" >> $filename
   echo "@ATTRIBUTE freeHeap numeric" >> $filename
   echo -en "\n" >> $filename
   echo "@DATA" >> $filename
}

initializeData() {
	read totalHeap freeHeap <<< $(${directory}/memory_usage.sh)
	sysdatetime=$(date +'%d.%m.%Y %H:%M:%S')
	lastLine=$(tail -1 $filename 2> /dev/null)
	lastLineDate=$(echo $lastLine | cut -c2-11)
	currentDate=$(date +'%d.%m.%Y')
}

archiveFile() {
	if [ -f "$filename" ]
	then
		lastlineYear=$(echo $lastLineDate | cut -c7-11)
		lastlineMonth=$(echo $lastLineDate | cut -c4-5)
		lastlineDay=$(echo $lastLineDate | cut -c1-2)
		archiveFilename="${directory}/memory_usage_${lastlineYear}${lastlineMonth}${lastlineDay}.log"
		mv $filename $archiveFilename
	fi
}

writeData() {
	if [ "$lastLineDate" != "$currentDate" ]
	then
		archiveFile
		writeArffHeader
	fi
	echo "'$sysdatetime',$totalHeap","$freeHeap" >> $filename
}

initializeData
if [ ! -z "$totalHeap" ] && [ ! -z "$freeHeap" ] 
then
writeData
fi