# Arc-i-Tech Frontend

### Prerequisites

##### Node JS : v20.18
- Follow steps from [Dev Tools/Node](./../dev_env_setup/)
- Check Node version
  ```bash
  node --version
  ```

##### Angular : **18**
1. Install the Angular CLI **Globally** (if not already installed)
   ```bash
   npm install -g @angular/cli@18
   ```
2. Verify the installation by checking the version
   ```bash
   ng version
   ```
3. Install **Project** Dependencies
   - Navigate to the **Frontend** project directory _<u>(Assumed you opened terminal/ command prompt at root of repository)</u>_
     ```bash
     cd Frontend
     cd Arc-i-Tech
     ```
     **OR**
     ```bash
     cd Frontend\Arc-i-Tech
     ```
   - Install dependencies
     ```bash
     npm install
     ```
4. Run the Angular application
   ```bash
   ng serve
   ```
5. SSR (Server-Side Rendering) Setup **(not applicable)**
   ```bash
   npm run build:ssr
   npm run serve:ssr
   ```


### Working with Frontend project
##### Open Frontend project in VS Code separately
1. You can use [open_frontend.bat](./../open_frontend.bat). Double click on file to run it. 
   It will open the frontend project in separate VS Code instance.
2. Manually open the frontend project in VS Code.
   - **Open command prompt or terminal** from repository root folder and run below commands
     ```bash
     cd Frontend
     cd Arc-i-Tech
     code .
     ```
     **OR**
     ```bash
     cd Frontend/Arc-i-Tech
     code .
     ```
   - **Open file explorer**
     - Navigate to Repository
     - Navigate -> Frontend 
     - Right click on the "Arc-i-Tech" folder
     - Select **Open with Code** option