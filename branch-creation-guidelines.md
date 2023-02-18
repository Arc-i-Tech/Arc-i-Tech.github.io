## Branch creation guidelines.
Please, go through these steps before you start working on any issue.

1. Make sure that you have necessory branch created for issue.
2. If not, then make sure:
   - Issue/ Task you are working on is independent.
   - Issue/ Task is just a tiny part of `epic` or `user-story`.
   - Less than 2 `story-points`.
   - If any of the above is true for `issue`/ task you are working on. Then its optional to create `branch`. You can even consider additional factors such as `merge-conflict` or how many people working on it, or you can consult with `product owner` _([Ajay G.](www.github.com/ajayg2808))_.

3. To create branch,
   - Follow branch naming convention as follows.
     > `issue-number`-`issue-type-code`-`short-description`
        
      * **Issue-number**: Your task no.
       
      * **Issue type code**: is a 1 or 2 char based on issue type as follows:
             
        | Sr. No. 	| Issue Type 	| Description                	| Issue type code 	|
        |---------	|------------	|----------------------------	|-----------------	|
        | 1       	| Feature    	| Configuration feature      	| cf/ fc             |
        |         	|            	| Configuration              	| c               	|
        |         	|            	| normal/ new feature (code) 	| f               	|
        | 2       	| Bug        	| Bug (code)                 	| b               	|
        |         	|            	| configuration bug          	| cb              	|
        | 3       	| Epic       	| Epic                       	| E               	|
        | 4       	| User Story 	| User Story                 	| us              	|

     * **Short description**: max 2-3 word discription based on your issue/ task.


     > Example
       - `25-fc-github-template`
       - `25` represents -> #25
       - `fc` represents -> configuration feature
       - `github-templates` -> short descritpion of issue #25
        
   - Select base branch/ source branch.
     * Base branch is a branch from which branch you are creating new branch.
     * Base branch can be your `epic` branch or `user-story` branch of your Issue/ Task.
     * If there is no `epic` or `user-story` for your Issue/ Task then use `main` branch as base branch.

   - Commit all changes to your `branch` for which issue it is created.


_Thank You! Happy Coding!_

