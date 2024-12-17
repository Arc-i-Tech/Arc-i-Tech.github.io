### Project automation

#Why proction automation written
- Before project automation Develpoer doing project management manually
- problem with manual intervention is more error prone system.
- Develpoer need not to change board view's from one to other side.
- New , product backlog ,EPIC ,sprint backlog, In progress,Blocked, in review and Done Are the tabs used in project. 

#How Project automation working with assignee and label
-Only assignee and admin  allowed to change "started working" status
-Without an assignee cannot be labeled
-Only the assignee can remove label
-If issue is unassigned and there is no other assignee remove label
-This will be taken care by Job: issue-automation

##issue-automation
#A. "EPIC" :  yes
    - "Started-working"  remove label 
#B.  "Assigned" :yes
    -****Check	"Started-working' or "labeled' and "Started-working"
       -"In progress"else "sprint backlog"
#C.  "labeled"
	-"EPIC" and "Started-working'  remove  and keep with label "EPIC"
	-"Accepted" change board view to "product backlog"
#D . "Unlabeled"
	-label name : Accepted   "New"
	-label name : Started-working  "Product Backlog"
	-label name : Started-working  and Accepted   "Product Backlog"
	-label name : Started-working  and not Accepted   "New"
#E. "Unassigned"
	-label name : Accepted  "Product Backlog"
	-Label name : not Accepted "NEw"
	-label name : Started-working and not assigned  " started-working removed"	
