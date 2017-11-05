@echo off

set VERSION=

:Set_Title
title Class Searcher %VERSION%

:Set_JAVA_HOME
if not DEFINED JAVA_HOME (
    set JAVA_HOME=
)

:Set_classpath
set classpath=%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;

setlocal enabledelayedexpansion

set THIRDPARTY_LIB=.\thirdPartyLib
for /f  %%a in ('dir /b "%THIRDPARTY_LIB%"') do (
set "classpath=!classpath!;%THIRDPARTY_LIB%\%%a"
)

set MAIN_LIB=.\lib
for /f  %%a in ('dir /b "%MAIN_LIB%"') do (
set "classpath=!classpath!;%MAIN_LIB%\%%a"
)

:Is_Need_JAVA_OPTS -- add/remove rem
rem set JAVA_OPTS=-classic -Xdebug -Xnoagent -XX:+PrintGCDetails -verbose:gc -Xloggc:..\log\gc.log -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8789,server=y,suspend=y %JAVA_OPTS%

:Set_Java_Process -- choose 1 in 2
rem set runProcess=%JAVA_HOME%\bin\java
set runProcess=start javaw

:Check_Before_Start
if not EXIST %JAVA_HOME% (
     echo Please specify JAVA_HOME before running
	 pause
     goto End
)

:Start_Java
%runProcess% %JAVA_OPTS% -Xms20m -Xmx200m  name.cdd.product.clzsearch.jarclzsearch.gui.JarClassSearchDlg

:End