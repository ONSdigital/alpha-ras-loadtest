To run a load test, eg

mvn gatling:execute

You'll probably need to specify some details of where to connect, eg

mvn gatling:execute -DDOMAIN=apps.onsdigital.uk -DENVIRONMENT=Test -DPORT=80
