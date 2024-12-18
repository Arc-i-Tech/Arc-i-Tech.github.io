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
      s10[actor-info] --> st11[check-run] --> |OPTION, LABELS, COMMENT, NUM, type|s12[job-result]
    end
    subgraph get-details
      s20[get-required-ids] --> |PRJ_ID, ids|s21[fetch-project-details] --> |PRJ|s22[job-result]
    end
    subgraph issue-automation
      s30[find-item] --> |ITEM|s31[find-target-status] --> |OPTION, LABELS, OPTION_NODE, FIELD_ID, UPDATE_STATUS|s32[job-result]
    end
    subgraph pr-automation
      s40[check-associated-issues] --> |COMMENT, CLOSING_ISSUES, PR_ASSIGNEES|s41[reopened] --> |COMMENT|s42[find-ref-issues-target-status] --> |OPTION_NODE, FIELD_ID, UPDATE_STATUS|s43[get-open-ref-items] --> |COMMENT, ITEMS, openRefIssueNos|s44[check-pr-assignees] --> |ASSIGN_PR, ASSIGNEES, COMMENT|s45[assign-pr]--> s45[handle-closed-pr] --> s46[job-result]
    end
    subgraph update-status-sprint
      s50[collect-inputs] --> |OPTION_NODE, FIELD_ID, UPDATE_STATUS, ITEMS, PRJ, ids|s51[update-status] --> s52[update-sprint] --> s53[job-result]
    end
    subgraph add-remove-label-comment
      s60[find-labels-options] --> |LABELS, OPTION, COMMENTS|s61[remove-label] --> s62[issue-comment]
    end
    

    check-valid-run --> |NUM, type, output-result|get-details
    check-valid-run --> |OPTION, LABELS, COMMENT|add-remove-label-comment
    get-details --> |output-result, PRJ, ids, NUM, type|issue-automation
    get-details --> |output-result, PRJ, ids|pr-automation
    issue-automation --> |output-result, job-result, OPTION_NODE, FIELD_ID, UPDATE_STATUS, ITEM, PRJ, ids|update-status-sprint
    issue-automation --> |job-result, LABELS, OPTION|add-remove-label-comment
    pr-automation --> |output-result, job-result, OPTION_NODE, FIELD_ID, UPDATE_STATUS, ITEMS, PRJ, ids|update-status-sprint
    pr-automation --> |COMMENT. COMMENT1, COMMENT2, COMMENT3|add-remove-label-comment

```
