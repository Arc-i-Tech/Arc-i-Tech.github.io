# Git Installation through [tools_installer](./tools_installer.bat)
- If you already have Git installed on your machine then it is optional to install new version.

1. For online installation script downloads **Git 2.47.1 64-bit version** from the internet.
2. For offline execution already available Git 2.47.1 64-bit version from '\Tools\' directory is used.

## Installation steps for script [tools_installer](./tools_installer.bat)
1. The script will call **Git (2.47.1) installer**
2. Click **Next** on license page.
   
   ![1](https://github.com/user-attachments/assets/58ca2f9a-1d7a-402d-bb52-188b72d65b21)

3. Select destination folder and then click **Next**

   ![2](https://github.com/user-attachments/assets/b4f9762b-dee9-4785-8330-ea7f334a0e72)

   If destination folder already exist, either select different setination folder or continue install in that folder anyways.

   ![3](https://github.com/user-attachments/assets/404a217f-ced3-4dcd-922d-2e9c3b5774f7)

4. Select component to install and click **Next**. _(Mostly default selection is sufficient)_
   
   ![4](https://github.com/user-attachments/assets/4a08c283-4eab-4092-92b3-44a89a1f2bdb)
   
5. On Select Start Menu Folder - 
   - To go with default folder click **Next**
   - To select different folder click on **Browse** and select start menu folder
   - Or select checkbox to not create Start Menu Folder
   
   ![5](https://github.com/user-attachments/assets/224dc979-ae37-4e8c-b0a0-ef4cc5427fbd)

6. Choose the deafult editor used by **Git** and click **Next**

   ![6](https://github.com/user-attachments/assets/e88b17a5-51e8-4215-8932-4fa713b7404c)

   There are many options to choose, select your preferred option from drop down.

7. Name of the initial branch in new repositories _(Branch name when new repository is created)_

    Choose `Let Git decide` and click **Next**

    ![7](https://github.com/user-attachments/assets/00ad8ff2-346b-401c-ab84-44d4598bc709)

8. Adjust `PATH` environment variable _(Choose **Recommended** option)_ then click **Next**

    ![8](https://github.com/user-attachments/assets/93614ee1-9e4b-40a9-858c-3f7a5f9316a1)

9. Choose _bundled OpenSSH_ and click **Next**

    ![9](https://github.com/user-attachments/assets/23339959-3934-4ee1-8848-6f1ce1661783)

10. Choose OpenSSL library as HTTPS transport backend and click **Next**

    ![10](https://github.com/user-attachments/assets/5ca0c69c-5aa9-42ef-b3ce-79098e27297b)

11. Select `Checkout Windows-style, commit Unix-style line endings` for line ending conversions and click **Next**.

    ![11](https://github.com/user-attachments/assets/2365ece5-a884-4e37-bd7e-8f8fcc77d730)

12. Select `MinTTY` as terminal emulator for **Git Bash** and click **Next**

    ![12](https://github.com/user-attachments/assets/01a6d8e0-f061-42c6-9def-40d5bfc48bd9)

13. Choose `Fast-forward or merge` as default behaviour of `git pull` and click **Next**

    ![13](https://github.com/user-attachments/assets/cbd79389-20eb-404e-a51f-83b2866180a3)

14. Choose `Git Credential Manager` as creadential helper and click **Next**

    ![14](https://github.com/user-attachments/assets/6fcd0fa2-e31a-4aea-bcd7-c89551ef51f2)

15. `Enable file system caching` and click **Install**

    ![15](https://github.com/user-attachments/assets/f6e3849d-a119-491e-af66-b5cb1e2fe347)

16. Installation may take upto few minutes. Click **Finish** once done.

    ![16](https://github.com/user-attachments/assets/2c77ae46-a7d6-4f77-9883-dc548dfb7d87)

17. Open Windows Command Prompt and verify **Git** installation
    ```bash
    git --version
    ```
    You will get below output if installation is successful:
    ```bash
    git version 2.47.1.windows.1
    ```

## Setup Git
- Do necessary setup to work with Git and GitHub repositories.

#### 1. Set Up Global Configuration
- Configure your **username** and **email** _(this will appear in your commits)_
	```bash
	git config --global user.name "Your Name"
	git config --global user.email "your_email@example.com"
	```
- example:
	```bash
	git config --global user.name "ajayg2808"
	git config --global user.email "ajayg2808@example.com"
	```

#### 2. Generate an SSH key (Optional but recommended)
- To securely connect to **GitHub** without entering your **password** repeatedly:
1. Generate a new SSH key:
	 ```bash
	 ssh-keygen -t ed25519 -C "your_email@example.com"
   ```
   If prompted, press **Enter** to accept the default file location and optionally set a **`passphrase`**
2. Add your SSH key to the SSH agent:
   ```bash
   eval "$(ssh-agent -s)"
   ssh-add ~/.ssh/id_ed25519
   ```
3. Add the SSH key to your **GitHub** account:
   - Copy your public key to the clipboard
   ```bash
	 cat ~/.ssh/id_ed25519.pub
	 ```
4. **Log in** _(Via web browser)_ to GitHub and got to **`Settings -> SSH & GPG keys -> New SSH key`**. Paste the key and **save**.

#### 3. Set Up GitHub Token for HTTPS authentication
- If you're using **HTTPS**, **GitHub** requires a **personal access token _(PAT)_** instead of your password.
  1. Generate a token:
     - Go to **`GitHub -> Settings -> Developer Settings -> Personal Access Tokens -> Tokens (classic) -> Generate new token`**
     - Select scopes (e.g. repo) and generate the token.
	2. When pushing changes, use the token in place of your password. [Refer](#4-steps-to-use-a-pat)

#### 4. Steps to use a PAT
1. **Push Changes with PAT:**
   - When you push changes for the first time, Git will prompt you for authentication:
	 ```bash
	 git push origin <branch_name>
	 ```
   - Use your **GitHub username** as the username.
   - Use the **PAT** as the password.
  
	Example:
	```makefile
	Username: your_github_username
	Password: your_personal_access_token
	```
2. **Save Your Credentials (Optional):** To avoid entering your PAT every time, configure a credential helper:
   ```bash
	 git config --global credential.helper store
	 ```
   Then, when prompted during a `git push`, enter your PAT. **Git** will **save** it **locally**.

3. **Set Remote URL with PAT (Optional):** You can embed the PAT directly in the remote URL (not recommended for security reasons unless necessary):
   ```bash
	 git remote set-url origin https://<PAT>@github.com/<username>/<repository>.git
	 ```
	Replace `<PAT>`, `<username>`, and `<repository>` with your actual **token**, **GitHub username**, and **repository name**.

##### **Best Practices for PAT Usage**
- **Do not share your PAT:** Treat it like a password.
- **Use a credential manager:** Avoid manually typing the PAT each time.
- **Regenerate tokens regularly:** For enhanced security, set an expiration date for your tokens and rotate them periodically.
- **Use scopes wisely:** Only grant the permissions you need.

#### 5. OAuth support for GitHub
- OAuth is nothing but browser based authorization instead of **SSH Key** or **PAT**.
- Git version 2.29 or higher supports OAuth for GitHub
- Read more at [Git Credential Manager](https://docs.github.com/en/get-started/getting-started-with-git/caching-your-github-credentials-in-git#git-credential-manager)
- _Advantages - PAT has to be regenerated regularly for security reasons but OAuth doesn't need reauthorization._

#### 6. Clone a Repository (Optional - can be used to clone any repository from a GitHub)
- Copy the repository URL from **GitHub** (use **SSH** if you've set it up, or **HTTPS** otherwise)
- Run:
	```bash
	git clone <repository_url>
	```
- Example: **SSH**
  ```bash
	git clone git@github.com:Arc-i-Tech/Arc-i-Tech.github.io.git
	```
	If **HTTPS** selected and **PAT**	 is not configured then above **version of Git (2.47)** will prompt you to login using a browser window.

#### 7. Working with Repository
- Navigate to the repository folder:
	```bash
	cd repository
	```
- Create a new branch:
	```bash
	git checkout -b new-branch
	```
- Add changes:
	```bash
	git add .
	```
- Commit changes:
	```bash
	git commit -m "Commit message"
	```
- Push changes:
	```bash
	git push origin new-branch
	```


Enjoy Using A **Git**!!!
