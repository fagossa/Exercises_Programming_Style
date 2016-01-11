#!/usr/bin/env bash

sbt "run src/main/resources/mobydick.txt" | grep --regex="\w*  -  \d" > output.txt
diff output.txt src/test/resources/response.txt > diff.txt

if [[ -s diff.txt ]]; then
    SUCCESS=1
else
    SUCCESS=0
fi

PREVIOUS_SUCCESS=$(cat .previoussuccess 2> /dev/null)

if [[ ( -z "${PREVIOUS_SUCCESS}" ) && ( "${SUCCESS}" = "1" ) ]]; then
    PREVIOUS_SUCCESS=0
fi
if [[ ( -z "${PREVIOUS_SUCCESS}" ) && ( "${SUCCESS}" = "0" ) ]]; then
    PREVIOUS_SUCCESS=1
fi

if   [[ "${SUCCESS}" -eq "1" && "${PREVIOUS_SUCCESS}" -eq "0"  ]]
then
    osascript -e "display notification \"$(cat output.txt)\" with title \"Échec\""
elif [[ "${SUCCESS}" -eq "0" && "${PREVIOUS_SUCCESS}" -eq "1"  ]]
then
    osascript -e 'display notification with title "Réparé" subtitle "bien joué"'
fi

echo ${SUCCESS} > .previoussuccess

rm output.txt
rm diff.txt

exit ${SUCCESS}
