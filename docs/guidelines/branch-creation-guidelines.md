## Branch creation guidelines.
Please, go through these steps before you start working on any issue.

#### Data Model
Refer **Data Model** we follow it for our project management.
![data-model](./../project-management/data-model.md)


#### Branch naming convention for branches
  - Follow below `branch` naming conventions.
    > **`issue-number`-`issue-type-code`-`short-description`**
    
    * **Issue-number**: Your issue number for which you are creating branch. Issue can be of type - `Feature` or `Epic` or `Task` or `Bug`
       
    * **Issue type code**: is a 1 or 2 char based on issue type as follows:    
      | Sr. No. | Issue Type 	    | Sub Type                    | Description               	  | Issue type code 	|
      |--------	|---------------- |----------------------------	|------------------------------ | ----------------- |
      | 1       | **Feature**     | Feature of Code/ Project    | Feature for project i.e code  | **_F_**           |
      |         |            	    | Configuration (Repo/ Proj)  | Configuration feature for Repository or project. Ex. Automation | Repo - **_cF_**/ proj - **_Fc_** |
      |         |                 | Feature enhancement         | Enhancement to existing feature | **_Fe_**        |
      | 2       | **Epic**        | Epic                 	      | Epic for project              | **_E_**          	|
      |         |                 | Configuration               | Epic for configuration related work. OR Epic for configuration feature | Repo - **_cE_**/ proj - **_Ec_** |
      | 3       | **User-Story**  | User-Story for Feature/Epic | User-Stories for Feature/ Epic | **_us_**        	|
      | 4       | **Task** 	      | Task                  	    | User-Stroy divided into Tasks | **_t_**        	  |
      | 5       | **Bug**         | Bug                         | Identified bugs - can be or part of Feature/ Epic/ User-Story/ Task | **_b_**           |
      | 6       | **SubTask** 	  | Sub-Task                  	| Task or Bug-Task can be divided into subtasks | **_st_**          |

    * **Short description**: **Max 2-3 word** description based on your issue.
      > Example: Issue --> [**`#25 - Github Templates for issue and pull request`**](https://github.com/Arc-i-Tech/Arc-i-Tech.github.io/issues/25)
        **`25-cF-gh-template`**
       - `25` represents -> [#25](https://github.com/Arc-i-Tech/Arc-i-Tech.github.io/issues/25)
       - `cF` represents -> Repository configuration feature
       - `gh-templates` -> short description of issue [#25](https://github.com/Arc-i-Tech/Arc-i-Tech.github.io/issues/25)

### Steps for branch creation
1. To create a **new branch** you must be assigned to a **Issue** for which you want to create a new branch.
2. Enter the **new branch name** as per above naming conventions. 
3. Select a **`base/ source/ target`** branch. _Base branch is a `parent branch` from which, you are creating new branch._
   * Base branch must be branch of your issue's **parent issue**. Parent issue can be `Feature`or `Epic` or `User-Story` or `Task`
   * If there is no parent `Feature` or `Epic` or `User-Story` for your Issue/ Task then use `dev-main` branch as base branch or consult with [Ajay Gaikwad](https://github.com/ajayg2808).
   :memo:_Commit all changes of issues to it's respective `branch`s only._
4. Cases where branches not needed for issues:
   - If your issue is only child of parent issue.
   - Parent issue assignee/ owner is also owner of child issue and child issue being estimated less than 2hr.

**:memo: Changes from any `branch` which doesn't follow the above naming convention are not accepted.**

_Thank You! Happy Coding!_

