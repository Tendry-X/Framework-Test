@echo off
REM ===========================
REM CONFIGURATION DES VARIABLES
REM ===========================
set APP_NAME=framework-test
set SRC_DIR=src\main\java
set BUILD_DIR=build
set LIB_DIR=lib
set WEB_DIR=src\main\webapp
set TOMCAT_WEBAPPS=C:\apache-tomcat-9.0.112\webapps
set SERVLET_API_JAR=%LIB_DIR%\javax.servlet-api-4.0.1.jar
set MYSQL_JAR=%LIB_DIR%\mysql-connector-j-9.2.0.jar
set WAR_NAME=%APP_NAME%.war

REM ========================================
REM Étape 0 : Nettoyage de l'ancien dossier build
REM ========================================
if exist "%BUILD_DIR%\WEB-INF" (
    echo Nettoyage de l'ancien dossier build...
    rmdir /s /q "%BUILD_DIR%\WEB-INF"
)
mkdir "%BUILD_DIR%\WEB-INF\classes"

REM ========================================
REM Étape 1 : Compilation des fichiers Java
REM ========================================
echo Compilation des fichiers Java...

if not exist "%SRC_DIR%" (
    echo Erreur : Les fichiers source Java sont introuvables dans %SRC_DIR%.
    pause
    exit /b 1
)

REM Vérifier l'existence du JAR Servlet
if not exist "%SERVLET_API_JAR%" (
    echo Erreur : Le fichier %SERVLET_API_JAR% est introuvable.
    pause
    exit /b 1
)

REM Compilation
dir /b /s "%SRC_DIR%\*.java" > sources.txt
javac -cp "%SERVLET_API_JAR%;%MYSQL_JAR%" -d "%BUILD_DIR%\WEB-INF\classes" @sources.txt

if errorlevel 1 (
    echo Erreur : Échec de la compilation. Vérifiez vos fichiers Java.
    del sources.txt
    pause
    exit /b 1
)

del sources.txt
echo Compilation réussie !

REM =========================================
REM Étape 2 : Copie des fichiers webapp dans build
REM =========================================
echo Copie des fichiers webapp...

if not exist "%WEB_DIR%" (
    echo Erreur : Les fichiers webapp sont introuvables dans %WEB_DIR%.
    pause
    exit /b 1
)

xcopy "%WEB_DIR%\*" "%BUILD_DIR%\" /e /i /q >nul

if errorlevel 1 (
    echo Erreur : Échec de la copie des fichiers webapp.
    pause
    exit /b 1
)

REM =========================================
REM Étape 2.5 : Copier le fichier MySQL JDBC dans WEB-INF\lib
REM =========================================
if not exist "%BUILD_DIR%\WEB-INF\lib" mkdir "%BUILD_DIR%\WEB-INF\lib"

if exist "%MYSQL_JAR%" (
    copy "%MYSQL_JAR%" "%BUILD_DIR%\WEB-INF\lib\" >nul
    echo Connecteur MySQL copié.
)

REM =========================================
REM Étape 3 : Création du fichier WAR
REM =========================================
echo Création du fichier WAR...

pushd "%BUILD_DIR%"
jar -cvf "..\%WAR_NAME%" * >nul

if errorlevel 1 (
    echo Erreur : Échec de la création du fichier .war.
    popd
    pause
    exit /b 1
)
popd

echo Fichier WAR créé : %WAR_NAME%

REM =========================================
REM Étape 4 : Déploiement vers Tomcat
REM =========================================
echo Déploiement vers Tomcat...

if not exist "%TOMCAT_WEBAPPS%" (
    echo Erreur : Le dossier webapps de Tomcat est introuvable : %TOMCAT_WEBAPPS%.
    pause
    exit /b 1
)

REM Supprimer l'ancien déploiement
if exist "%TOMCAT_WEBAPPS%\%APP_NAME%" (
    rmdir /s /q "%TOMCAT_WEBAPPS%\%APP_NAME%"
)
if exist "%TOMCAT_WEBAPPS%\%WAR_NAME%" (
    del "%TOMCAT_WEBAPPS%\%WAR_NAME%"
)

copy "%WAR_NAME%" "%TOMCAT_WEBAPPS%\" >nul

if errorlevel 1 (
    echo Erreur : Impossible de copier le fichier .war vers Tomcat.
    pause
    exit /b 1
)

REM =========================================
REM Fin
REM =========================================
echo.
echo ========================================
echo DÉPLOIEMENT TERMINÉ AVEC SUCCÈS !
echo ========================================
echo Fichier WAR : %WAR_NAME%
echo Déployé dans : %TOMCAT_WEBAPPS%
echo.
echo Démarrez Tomcat et accédez à :
echo http://localhost:8080/%APP_NAME%/hello
echo ========================================
pause