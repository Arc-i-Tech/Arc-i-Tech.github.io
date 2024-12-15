## Project Automation

- Project automation is perfomred through [project.yml](./../../../.github/workflows/project.yml)
- You can check workflow actions here: [Project Automate](https://github.com/Arc-i-Tech/Arc-i-Tech.github.io/actions/workflows/project.yml)

Below is the Actions/ Workflow diagram:

```mermaid
  ---
title: Github Workflow Current
---
  flowchart LR
    subgraph check-valid-run
      st1[actor-info] --> st2[check-run] --> |OPTION, LABELS, COMMENT, NUM, type|st3[job-result]
    end
    subgraph get-details
      s4[get-required-ids] --> |PRJ_ID, ids|s5[fetch-project-details] --> |PRJ|s6[job-result]
    end
    subgraph issue-automation
      s7[find-item] --> |ITEM|s8[find-target-status] --> |OPTION, LABELS, OPTION_NODE, FIELD_ID, UPDATE_STATUS|s9[job-result]
    end
    subgraph pr-automation
      s10[check-associated-issues] --> |COMMENT, CLOSING_ISSUES, PR_ASSIGNEES|s11[reopened] --> |COMMENT|s12[find-ref-issues-target-status] --> |OPTION_NODE, FIELD_ID, UPDATE_STATUS|s13[get-open-ref-items] --> |COMMENT, ITEMS|s14[check-and-assign-pr] --> s15[job-result]
    end
    subgraph update-status-sprint
      s16[collect-inputs] --> |OPTION_NODE, FIELD_ID, UPDATE_STATUS, ITEMS, PRJ, ids|s17[update-status] --> s18[update-sprint] --> s19[job-result]
    end
    subgraph add-remove-label-comment
      s20[find-labels-options] --> |LABELS, OPTION, COMMENTS|s21[remove-label] --> s22[issue-comment]
    end
    

    check-valid-run --> |NUM, type, output-result|get-details
    check-valid-run --> |OPTION, LABELS, COMMENT|add-remove-label-comment
    get-details --> |output-result, PRJ, ids, NUM, type|issue-automation
    get-details --> |output-result, PRJ, ids|pr-automation
    issue-automation --> |output-result, job-result, OPTION_NODE, FIELD_ID, UPDATE_STATUS, ITEM, PRJ, ids|update-status-sprint
    issue-automation --> |job-result, LABELS, OPTION|add-remove-label-comment
    pr-automation --> |output-result, job-result, OPTION_NODE, FIELD_ID, UPDATE_STATUS, ITEMS, PRJ, ids|update-status-sprint
    pr-automation --> |COMMENT. COMMENT1, COMMENT2|add-remove-label-comment

```
