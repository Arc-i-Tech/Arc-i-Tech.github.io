# Development Environment Setup
Follow below instructions to do **Development Environment setup** on your local PC.

## Download Installer Script
- Download [Tools.zip](https://1drv.ms/f/s!AoGr59nZgtwSkiXI2lk-i14_ySzE?e=PUfWe2) and _unzip_ to **'\Tools\'** directory. **Tools.zip** is not required for _Online_ installation.
- Download [tools_installer](./tools_installer.bat) if not present in **Tools.zip**
- For _offline_ installation copy the downloaded **tools_installer.bat** to the above _unzipped_ tools directory if already not present.
  
## Steps
1. Run **tools_installer.bat**
   - **Right click** and select **Run as Administrator**.

2. Select **Drive** option
   - Installer scripts ask to select the Drive to create _'\Arc-i-Tech\tools\'_ directory
   - If not selected **C: Drive** is selected as default. OR you can enter any drive later of your choice.

3. Select **Execution** mode
   - There are two execution modes
     - Online - All required tools get downloaded from the internet and then installed.
     - Offline - All required tools are downloaded from **Shared Drive** ([Tools.zip](https://1drv.ms/f/s!AoGr59nZgtwSkiXI2lk-i14_ySzE?e=PUfWe2)) and _unzipped_

### Online Installation

### Offline Installation
1. The script automatically Copies all tool installers from the current folder (_unzipped_) to the newly created _'\Arc-i-Tech\Tools\'_ directory.
2. Select MySQL version for installation
   
   ![image](https://github.com/user-attachments/assets/c5a5415a-692a-4411-806a-d7e5601815e5)

4. Script start installation of tools one by one
5. Follow below [Tool instructions](#user-content-tools-instructions---installation-work-setup-and-further-help)

### Tools instructions - Installation, Work Setup and further help
_Note: Java JDK or Eclipse installation do not touch your earlier installation if any_
1. [Java JDK](./java_jdk.md)
2. [Eclipse JEE](./Eclipse.md)
3. [MySQL](./MySQL.md)
4. VS Code - AUto install no manual steps required
5. GitHub Desktop - Auto install no manual steps required
6. Slack - Auto install no manual steps required
