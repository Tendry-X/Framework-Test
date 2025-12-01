@echo off
REM ===========================
REM CONFIGURATION
REM ===========================
set APP_NAME=framework-test
set BUILD_DIR=build
set WEB_DIR=src\main\webapp
set TOMCAT_WEBAPPS=C:\apache-tomcat-9.0.112\webapps
set WAR_NAME=%APP_NAME%.war

REM ========================================
REM Nettoyage
REM ========================================
echo Nettoyage...
if exist "%BUILD_DIR%\WEB-INF" rmdir /s /q "%BUILD_DIR%\WEB-INF"
mkdir "%BUILD_DIR%\WEB-INF\classes"
mkdir "%BUILD_DIR%\WEB-INF\lib"

REM =========================================
REM Copie des fichiers webapp
REM =========================================
echo Copie des fichiers webapp...
xcopy "%WEB_DIR%\*" "%BUILD_DIR%\" /e /i /q >nul

REM =========================================
REM Copie des JARs
REM =========================================
echo Copie des bibliotheques...

if exist "lib\mysql-connector-j-9.2.0.jar" (
    copy "lib\mysql-connector-j-9.2.0.jar" "%BUILD_DIR%\WEB-INF\lib\" >nul
    echo - MySQL copie
)

if not exist "lib\framework.jar" (
    echo ERREUR : lib\framework.jar introuvable !
    echo Copiez framework.jar depuis le projet Framework
    pause
    exit /b 1
)

copy "lib\framework.jar" "%BUILD_DIR%\WEB-INF\lib\" >nul
echo - Framework copie

REM =========================================
REM Creation du WAR
REM =========================================
echo Creation du WAR...
pushd "%BUILD_DIR%"
jar -cvf "..\%WAR_NAME%" * >nul
popd
echo Fichier WAR cree : %WAR_NAME%

REM =========================================
REM Deploiement vers Tomcat
REM =========================================
echo Deploiement vers Tomcat...

if exist "%TOMCAT_WEBAPPS%\%APP_NAME%" rmdir /s /q "%TOMCAT_WEBAPPS%\%APP_NAME%"
if exist "%TOMCAT_WEBAPPS%\%WAR_NAME%" del "%TOMCAT_WEBAPPS%\%WAR_NAME%"

copy "%WAR_NAME%" "%TOMCAT_WEBAPPS%\" >nul

echo.
echo ========================================
echo DEPLOIEMENT TERMINE !
echo ========================================
echo Acces : http://localhost:8080/%APP_NAME%/hello
echo ========================================
pause