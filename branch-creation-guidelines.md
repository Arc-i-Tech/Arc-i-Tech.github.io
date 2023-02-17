## Branch creation guidelines.
Please, go through these steps before you start working on any issue.

1. Make sure that you have necessory branch created for issue.
2. If not, then make sure:
   - Issue/ Task you are working on is independent.
   - Issue/ Task is just a tiny part of `epic` or `user-story`.
   - Less than 2 `story-points`.
   - If any of the above is applicable for `issue`/ task you are working on. Then its optional to create `branch`.
   - You can even consider additional factors such as `merge-conflict` or many people working on same part, or you can concern with `product owner`

3. To create branch,
   - Follow branch naming convention as follows.
     > `issue-number`-`issue-type-code`-`short-description`
     
      * Issue type code: is a 1 or 2 char based on issue type as follows:
             
        | Sr. No. 	| Issue Type 	| Description                	| Issue type code 	|
        |---------	|------------	|----------------------------	|-----------------	|
        | 1       	| Feature    	| Configuration feature      	| cf/ fc            |
        |         	|            	| Configuration              	| c               	|
        |         	|            	| normal/ new feature (code) 	| f               	|
        | 2       	| Bug        	| Bug (code)                 	| b               	|
        |         	|            	| configuration bug          	| cb              	|
        | 3       	| Epic       	| Epic                       	| F               	|
        | 4       	| User Story 	| User Story                 	| us              	|

     * Example
       - `25-fc-github-template`
       - `25` represents -> #25
       - `fc` represents -> configuration feature
       - `github-templates` -> short descritpion of issue #25
        
   - Select base branch.
     * Base branch is a branch from which branch you are creating new branch.
     * Base brach can be your `epic` branch or `user-story` branch or `main` branch if      there is no `epic` or `user-story` for your Issue/ Task

   - Commit all changes to your `branch` for which issue it is created.


_Thank You! Happy Coding!_

