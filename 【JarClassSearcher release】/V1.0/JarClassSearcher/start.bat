@echo off
title Telnet Command Comparer

if DEFINED JAVA_HOME goto AFTER_DEFINED

    set JAVA_HOME=
    
:AFTER_DEFINED
set JAVA=%JAVA_HOME%\bin\java

set MAIN_LIB=.\lib
set classpath=%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%2\lib\tools.jar;%MAIN_LIB%\cdd-jarclasssearcher.jar;%MAIN_LIB%\jta20.jar;%MAIN_LIB%\spring-core-3.1.2.RELEASE.jar;

rem set JAVA_OPTS=-classic -Xdebug -Xnoagent -XX:+PrintGCDetails -verbose:gc -Xloggc:..\log\gc.log -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8789,server=y,suspend=y %JAVA_OPTS%

rem set runProcess=%JAVA%
 set runProcess=start javaw

%runProcess% %JAVA_OPTS% -Xms20m -Xmx200m  cdd.product.clzsearch.jarclzsearch.gui.JarClassSearchDlg %BeyondCompare_dir%