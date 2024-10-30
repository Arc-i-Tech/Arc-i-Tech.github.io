## Conflict Resolution Guidelines
Here are some steps to help/ guide you do conflict resolution.
If you don't know what is conflict then go through the [Know the conflict](./know-d-conflict.md)

## Guidelines
### 1. Identify the Merge Conflict
When you try to merge branches on GitHub, it will show a message like <span style="color:red;">**"This branch has conflicts that must be resolved"**</span> if there are conflicts.

### 2. Decide on Conflict Resolution Strategy
There are two main options:
- **Resolve directly on GitHub:** For small, straightforward conflicts.
- **Resolve locally:** For more complex conflicts or when there are multiple files with conflicts.

### 3. Resolve Minor Conflicts on GitHub
If GitHub allows it, you can resolve minor conflicts in the GitHub interface:
1. Go to the **Pull Request** or **Branch page** showing the conflict.
2. Click **Resolve Conflicts**.
3. GitHub will show the conflicting files with the **conflicting lines marked by**:
   ```csharp
   <<<<<<< HEAD
   Changes from the branch you’re merging into
   =======
   Changes from the branch you’re merging
   >>>>>>> branch-name
   ```
4. Edit the file to keep or modify the conflicting lines as needed, removing the **conflict markers**.
5. Once resolved, click Mark as resolved and then **Commit merge**.
This is ideal for minor text conflicts but isn't suitable for larger or binary file conflicts.

### 4. Resolve Conflicts Locally _(Git)_
For more complex scenarios, resolving conflicts locally offers more control.

##### Step 1: Clone the Repository and Checkout the Branch
If in case you already didn't have local repositories cloned.
```bash
# Clone your repository
git clone https://github.com/username/repository.git

# Navigate to your repository
cd repository

# Checkout the branch with conflicts
git checkout branch-with-conflicts
```

##### Step 2: Fetch and Merge the Target Branch
Fetch the latest changes from the branch you are trying to <u>merge into</u> (often `main` or `develop`):
```bash
# Fetch the latest changes from the remote
git fetch origin

# Checkout the target branch
git checkout main

# Pull the latest updates
git pull origin main

# Switch back to the branch with conflicts
git checkout branch-with-conflicts

# Try to merge the target branch into your feature branch
git merge main
```
Git will attempt to merge and notify you of any conflicts, showing output like:
```sql
CONFLICT (content): Merge conflict in filename.txt
Automatic merge failed; fix conflicts and then commit the result.
```

##### Step 3: Identify and Edit Conflicted Files
Use git status to list the files with conflicts:
```bash
git status
```
Conflicting files will be labeled as **"Unmerged paths"** in the output. Open each conflicted file in an editor to review and resolve conflicts.

##### Step 4: Resolve the Conflicts in Each File
In each conflicted file, you’ll see markers like these:
```csharp
<<<<<<< HEAD
Changes from the branch you are merging into (main)
=======
Changes from the branch you are merging from (feature-branch)
>>>>>>> feature-branch
```
- Remove the `<<<<<<<`, `=======`, and `>>>>>>>` markers.
- Edit the code to keep the changes you need, combining or discarding lines as appropriate.

##### Step 5: Mark the Conflicts as Resolved
After editing each file and saving your changes, use the following command to mark the file as resolved:
```bash
git add filename.txt
```
Repeat this for each conflicted file.

##### Step 6: Complete the Merge
Once all conflicts are resolved, commit the changes to complete the merge:
```bash
git commit -m "Resolved merge conflicts"
```

##### Step 7: Push the Resolved Branch Back to GitHub
Push the updated branch back to GitHub:
```bash
git push origin branch-with-conflicts
```
This will update the Pull Request (PR) automatically if you’re working on one. GitHub will now show the branch as mergeable if all conflicts were resolved correctly.


### 5. Resolve Conflicts Locally _(GitHub Desktop & VS Code)_
Using GitHub Desktop and VS Code together for resolving merge conflicts provides a streamlined, GUI-based approach.

##### Step 1: Open GitHub Desktop and Identify Conflicts
1. Open **GitHub Desktop**.
2. Pull the latest changes from the remote repository to ensure your local branch is up-to-date.
   - In the main view, select **Fetch origin** to pull the latest changes.
3. If there’s a merge conflict, GitHub Desktop will notify you with a message **indicating which files have conflicts**.

##### Step 2: Start the Merge and Open VS Code to Resolve Conflicts
1. Attempt to merge the branch by selecting **Branch > Merge into Current Branch** or using the **Pull Request** interface if applicable.
2. GitHub Desktop will indicate that there are conflicts and show a list of conflicting files.
3. Select **Open in Visual Studio Code** from GitHub Desktop to open the repository in VS Code.

##### Step 3: Resolve Conflicts in VS Code
1. In VS Code, the conflicted files will appear in the **Source Control** panel with labels like **Merge Changes** or **Conflicts**.
2. Click on a conflicted file to open it. You’ll see conflict markers showing where each branch’s changes are:
   ```csharp
   <<<<<<< HEAD
   Changes from the current branch
   =======
   Changes from the branch you are merging
   >>>>>>> feature-branch
   ```
3. Resolve each conflict by selecting the appropriate changes:
   - You can **Accept Current Change** (changes from the branch you’re on), **Accept Incoming Change** (changes from the branch you’re merging), **Accept Both Changes**, or **Compare Changes**.
   - Alternatively, edit the file manually by deleting the markers and combining changes as needed.
4. Save the file after resolving each conflict.

##### Step 4: Mark the Conflict as Resolved in GitHub Desktop
1. Once you’ve resolved and saved all conflicted files, return to GitHub Desktop.
2. You’ll see the files listed with **Resolved** next to them. If not, manually select **Mark as Resolved** for each conflicted file.
3. In the **Changes** tab, confirm that the conflicts are resolved by reviewing the changes.

##### Step 5: Commit the Merge in GitHub Desktop
1. In GitHub Desktop, add a commit message indicating that conflicts were resolved.
2. Click **Commit to [branch name]** to commit the changes.
##### Step 6: Push the Resolved Branch to GitHub
1. After committing, click **Push origin** in GitHub Desktop to push the resolved changes to the remote repository.
2. This will update any open Pull Requests or branches on GitHub.
##### Step 7: Complete the Merge on GitHub
1. Go to GitHub, navigate to the Pull Request or Branch page, and verify that there are no more conflicts.
2. Click **Merge Pull Request** to complete the merge.

## Additional Tips for Resolving Conflicts
- **Rebasing:** If your branch is far behind the main branch, consider rebasing before merging to reduce potential conflicts.
  ```bash
  git rebase main
  ```
- **Merge Tools:** Git offers merge tools (like `git mergetool`) that can visually help you manage conflicts, which are useful for resolving complex conflicts.