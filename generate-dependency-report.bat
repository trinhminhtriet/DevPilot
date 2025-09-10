@echo off

set JAVA_HOME=C:\Users\trnm\.jdks\temurin-21.0.7
set MAVEN_HOME=C:\Users\trnm\.m2\wrapper\dists\apache-maven-3.9.11-bin\6mqf5t809d9geo83kj4ttckcbc\apache-maven-3.9.11
set PATH=%JAVA_HOME%\bin;%PATH%
set PATH=%MAVEN_HOME%\bin;%PATH%

set ONLY_UPGRADE=-DprocessDependencyManagementTransitive=false -DonlyUpgradable=true
if "%1" == "all" set ONLY_UPGRADE=

set EXTRA=-Dmaven.version.ignore=".+[0-9\.-]M[0-9]*,.+CR[0-9]*,.*[0-9\.-]dev,.*[0-9\.-]alpha[0-9]*,.*[0-9\.-]beta[0-9]*,.*[0-9\.-]rc[0-9]*,.*[0-9\.-]pre[0-9]*,200.*,.*[0-9\.-]Alpha[0-9]*,.*MISTAKE.*"
call mvn org.codehaus.mojo:versions-maven-plugin:2.19.0:dependency-updates-aggregate-report org.codehaus.mojo:versions-maven-plugin:2.19.0:plugin-updates-aggregate-report %ONLY_UPGRADE% %EXTRA%
start target/reports/dependency-updates-aggregate-report.html
start target/reports/plugin-updates-aggregate-report.html
