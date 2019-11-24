@echo off

javac -d ../bin ua/nure/sidorov/practice1/*.java

java -cp ../bin ua.nure.sidorov.practice1.Demo

pause

del ..\bin\ua\nure\sidorov\practice1\*.class