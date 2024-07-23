@echo off
color 97
echo  .
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo  * *                                                 * *
echo  * *             Batch installer Script!             * *
echo  * *               Prepared by: Ajay G               * *
echo  * *                                                 * *
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo.

cd /d %~dp0 
echo  Drive selection to create '\Arc-i-Tech\tools\' directory
set drive=C
set /P input=" Do you want to select drive manually? (Y/N) "
if %input%==Y ( set /P drive=" Enter drive later[C or D]: ")
set tools=%drive%:\Arc-i-Tech\tools\
echo  Tools path = %tools%
if exist %tools% ( rd /S /Q %tools% )
md %tools%

echo. 
echo  Select execution mode
echo  1. Online
echo  2. Offline
set /P mode=Enter your choice: 
if %mode%==1 ( goto online)
if %mode%==2 ( goto offline)
goto end

:online
cls
color E0
echo.
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo  * *                                                 * *
echo  * *                   Online Mode                   * *
echo  * *                                                 * *
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo.

rem Change current working directory to %tools%
cd /d %tools%
echo  Working directory changed to %tools%
echo.
echo  =================================================================

echo.
rem Download OpenJDK's Java JDK 11.0.2
echo  Downloading Java JDK 11.0.2 (OpenJDK).
curl "https://download.java.net/java/GA/jdk11/9/GPL/openjdk-11.0.2_windows-x64_bin.zip" -o java_jdk.zip
echo.
echo  =================================================================

echo.
rem Download eclipse jee 2024-03.
echo  Downloading Eclipse JEE 2024-03.
curl "https://ftp.yz.yamagata-u.ac.jp/pub/eclipse/technology/epp/downloads/release/2024-03/R/eclipse-jee-2024-03-R-win32-x86_64.zip" -o eclipse.zip
echo.
echo  =================================================================

echo.
rem Download MySQL
echo  Select MySQL version to download and install
echo  5.7.44
echo  8.0.38
set /P mysql_version=Enter MySQL version: 
echo  Downloading MySQL %mysql_version%
powershell -command "Invoke-WebRequest 'https://dev.mysql.com/get/Downloads/MySQLInstaller/mysql-installer-community-%mysql_version%.0.msi' -outFile MySQLInstaller_%mysql_version%.0.msi"
echo.
echo  =================================================================

echo.
rem Download VS Code
echo  Downloading VS Code
powershell -command "Invoke-WebRequest 'https://code.visualstudio.com/sha/download?build=stable&os=win32-x64-user' -outFile vs_code.exe"
echo.
echo  =================================================================

echo.
rem Download Github desktop
echo  Downloading Github desktop
powershell -command "Invoke-WebRequest 'https://central.github.com/deployments/desktop/desktop/latest/win32' -OutFile gh_desktop.exe"
echo.
echo  =================================================================

echo.
rem Download Slack
echo  Downloading Slack
powershell -command "Invoke-WebRequest 'https://slack.com/api/desktop.latestRelease?arch=x64&variant=exe&redirect=true' -outFile slack.exe"
echo.
echo  =================================================================

rem install tools
goto install

:offline
cls
color B0
color 
echo.
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo  * *                                                 * *
echo  * *                  Offline Mode                   * *
echo  * *                                                 * *
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo.

rem Copy tools from current working directory to tools directory
echo  Copying tools to '%tools%' directory
copy * %tools%*
echo.
echo  =================================================================

echo.
echo  Select MySQL version to install
echo  5.7.44
echo  8.0.38
set /P mysql_version=Enter MySQL version: 
echo  Selected MySQL %mysql_version%
echo.
echo  =================================================================

echo.
rem Change current working directory to %tools%
cd /d %tools%
echo  Working directory changed to %tools%

rem install tools
goto install

:install
pause
cls
color A0
echo.
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo  * *                                                 * *
echo  * *                Installing tools!                * *
echo  * *                                                 * *
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo  * * * * * * * * * * * * * * * * * * * * * * * * * * * *
echo.
echo  Installing the following tools
echo  1. Java JDK 11.0.2
echo  2. Eclipse JEE 2024-03
echo  3. MySQL
echo  4. VS Code
echo  5. Github Desktop
echo  6. Slack
echo.
echo  =================================================================

echo.
rem Unzip Java JDK
echo  Unziping Java JDK 11.0.2
tar -xf java_jdk.zip
rem Set java env path if not set
set /P input=" Do you want to set JAVA_HOME env variable? (Y/N) "
if %input%==Y (
  echo  Setting JAVA_HOME environment variable
  setx JAVA_HOME %tools%jdk-11.0.2/bin /M
  setx PATH "%PATH%;%JAVA_HOME%" /M
)
echo.
echo  =================================================================

echo. 
rem Unziping Eclipse
echo  Unziping Eclipse
tar -xf eclipse.zip
rem Create eclipse desktop shortcut
echo  Creating Eclipse desktop shortcut
set TARGET='%tools%eclipse/eclipse.exe'
set SHORTCUT='%userprofile%eclipse-2024-03.ink'
set SCRIPT="%TEMP%\%RANDOM%-%RANDOM%-%RANDOM%-%RANDOM%.vbs"
echo Set oWS = WScript.CreateObject("WScript.Shell") >> %SCRIPT%
echo sLinkFile = "%USERPROFILE%\Desktop\eclipse-jee-2024-03.lnk" >> %SCRIPT%
echo Set oLink = oWS.CreateShortcut(sLinkFile) >> %SCRIPT%
echo oLink.TargetPath = "%tools%eclipse\eclipse.exe" >> %SCRIPT%
echo oLink.Save >> %SCRIPT%

cscript /nologo %SCRIPT%
del %SCRIPT%
echo.
echo  =================================================================

echo.
rem Install MySQL
echo Installing MySQL %mysql_version%
msiexec /i MySQLInstaller_%mysql_version%.0.msi
echo.
echo  =================================================================

echo.
rem Install VS code
echo Installing VS code
call vs_code.exe
echo.
echo  =================================================================

echo.
rem Install Github desktop
echo Installing Github desktop
call gh_desktop.exe
echo.
echo  =================================================================

echo.
rem Install slack
echo Installing slack
call slack.exe
echo.
echo  =================================================================

echo  Installation done successfully.
goto end

:end
color 07
pause
