# MySQL Installation through [tools_installer](./tools_installer.bat)
- If you alredy have MySQL installed on your machine then please consent with [Ajay Deshmukh.](https://github.com/ajstyle2410) or ask on Slack

1. For online installation script downloads **MySQL** from internet.
2. For offline execution alredy available MySQL from '\Tools\' directory is used.

## Installtion steps for script [tools_installer](./tools_installer.bat)
1. Script ask for version to choose
   * For Windows 10 or 11 Select MySQL version: **8.0.38**
   * For Windows 8 or lower select MySQL version: **5.7.44**
2. Based on your version selection script will call **MySQL Installer**
   
   ![image](https://github.com/user-attachments/assets/36c51bed-4f7d-4945-9a72-837817ad3824)
   
3. Choosing a setup type - **Full** anc click **Next**
   ![image](https://github.com/user-attachments/assets/418a0708-5630-448a-92c4-7b5fe0bc5cc7)
   
4. Installation - On below window click **Execute**
   ![image](https://github.com/user-attachments/assets/24a9434f-a58c-4cc0-ac45-d06f7ebe3178)

   Wait for installation to get completed.
   ![image](https://github.com/user-attachments/assets/18070b24-ff94-4c34-972b-e356762c3f0d)

   Once All completed click **Next**
   ![image](https://github.com/user-attachments/assets/03cce570-25ba-4b4d-80e2-266d89290fb2)

5. Product Configuration - Make sure all proucts are _ready to configure_. If yes click **Next**

   **5.1 - MySQL server 8.0.38 configuration**
   
   ![image](https://github.com/user-attachments/assets/e3884109-19fa-40bb-92ab-d0e86d9f57ad)

   - Type and Networking
     * Select _Config Type = Development Computer_
     * Select _Connectivity as below screenshot_
     * Once done click **Next**
     ![image](https://github.com/user-attachments/assets/ee5ff852-4007-454e-a2dc-281fe80beace)

   - Authentication Method - Select **RECOMMENDED** authentication method and click **Next**
     ![image](https://github.com/user-attachments/assets/1b095ce0-4454-4374-9592-bc7f72208c48)

   - Accounts and Roles
     * Enter root account password and confirm it. Remeber root account password.
     * Add MySQL user if you want.
     * Click **Next**
     ![image](https://github.com/user-attachments/assets/1f355775-6188-4d60-bfca-653e73e1efc3)

   - Windows Service
     * Select checkbox as screenshot
     * Enter service name: **MySQL80** OR **MySQL** OR **MySQL8038**
     * Select **Standard System Account** to Run windows service.
     * Click **Next**
     ![image](https://github.com/user-attachments/assets/9848fbd9-27c2-4258-b85f-1f9eb6cbdabd)

   - Server File Permissions
     * Grant full access to update server files. - _Warning! Just for learning purpose full access to be given to user otherwise you must choose other option_
     * Server data is stored at _'C:\ProgramData\MySQL\MySQL Server 8.0\Data'_
     ![image](https://github.com/user-attachments/assets/1548762d-cf29-4919-92f0-ee6162338850)

   - Apply Configuration
     * Execute to apply configuration changes.
     ![image](https://github.com/user-attachments/assets/d14a0467-47e2-48ee-8c6e-95097d9ac1de)

     Wait for configuration steps to be get executed.
     ![image](https://github.com/user-attachments/assets/62333eb0-0026-4b34-a81e-4b89a3f2ebd0)

     Once all steps done click **Finish**
     ![image](https://github.com/user-attachments/assets/f3b86e87-a5fc-4990-b3da-d61404badbbe)

   **5.2 MySQL Router 8.0.38 configuration** - Configuration not needed
   Click **Next** until it closes
   ![image](https://github.com/user-attachments/assets/0b465e4f-ebef-4454-898f-58d1bfde87ab)
    

   **5.3 Samples and Examples 8.0.38 Configuration**
   ![image](https://github.com/user-attachments/assets/f7cd766c-2cce-41fc-8aad-c8eadef68074)

   - Connect to Server
     * Enter **root** account password
     * **Check** password - You will see connection Status: **Connection succeeded**
     * Click **Next**
     ![image](https://github.com/user-attachments/assets/196c09b4-2bb4-4d86-a6a6-9fea59da04f9)

   - Apply Configuration
     * Execute configurations steps and wait for completion
       ![image](https://github.com/user-attachments/assets/a861ab6c-e67f-446c-9763-8e72fd9eea95)

     * Click **Finish**
       ![image](https://github.com/user-attachments/assets/7a00859d-ff45-4eb6-9175-cd4a9036abe1)

7. Installation Complete

   Uncheck below options and click **Finish**
   ![image](https://github.com/user-attachments/assets/d59fd8d0-6194-472c-8d52-03764b529da1)


8. Installation done successfuly. If any dificulties contact or ask help on Slack
   
