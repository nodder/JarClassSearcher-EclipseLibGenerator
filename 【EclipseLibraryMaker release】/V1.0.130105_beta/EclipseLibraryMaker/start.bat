@echo off
title Eclipse Library Generator v1.0

rem echo JAVA_HOME=%JAVA_HOME%

if DEFINED JAVA_HOME goto AFTER_DEFINED

    set JAVA_HOME=C:\jdk1.6.0_20
    
:AFTER_DEFINED

set JAVA=%JAVA_HOME%\bin\java
set MAIN_LIB=.\lib
set classpath=%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%2\lib\tools.jar;%MAIN_LIB%\cdd-eclipselibrarygenerator.jar;%MAIN_LIB%\spring-core-3.1.2.RELEASE.jar;%MAIN_LIB%\log4j.jar;%MAIN_LIB%\jdom.jar;

rem set JAVA_OPTS=-classic -Xdebug -Xnoagent -XX:+PrintGCDetails -verbose:gc -Xloggc:..\log\gc.log -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8789,server=y,suspend=y %JAVA_OPTS%
rem set runProcess=start javaw

set runProcess=%JAVA%

%runProcess% %JAVA_OPTS% -Xms100m -Xmx250m  cdd.product.clzsearch.userlibmaker.Start

pause