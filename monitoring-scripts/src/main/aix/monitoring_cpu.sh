#!/bin/bash

directory="/opt/ergon/monitoring/jazzentwicklung/cpu"
filename="${directory}/cpu_usage.log"
	
writeArffHeader () { 
   echo "@RELATION CpuUsage" > $filename
   echo "@ATTRIBUTE sysdatetime date \"dd.MM.yyyy HH:mm:ss\"" >> $filename
   echo "@ATTRIBUTE cpuUsage numeric" >> $filename
   echo -en "\n" >> $filename
   echo "@DATA" >> $filename
}

initializeData() {
	cpuLoad=$(${directory}/cpu_usage.sh)	
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
		archiveFilename="${directory}/cpu_usage_${lastlineYear}${lastlineMonth}${lastlineDay}.log"
		mv $filename $archiveFilename
	fi
}

writeData() {
	if [ "$lastLineDate" != "$currentDate" ]
	then
		archiveFile
		writeArffHeader
	fi
	echo "'$sysdatetime',$cpuLoad" >> $filename
}


initializeData
writeData