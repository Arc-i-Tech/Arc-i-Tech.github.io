## Project Automation

- Project automation is perfomred through [project.yml](./../../../.github/workflows/project.yml)
- You can check workflow actions here: [Project Automate](https://github.com/Arc-i-Tech/Arc-i-Tech.github.io/actions/workflows/project.yml)

Below is the Actions/ Workflow diagram:

```mermaid
  flowchart LR
    subgraph check-valid-run
      s1[serv-cat test-run] --> s2[check-run] --> |OPTION, LABELS,COMMENT, NUM, type|s3[job-result]
    end
    subgraph get-details
      s4[get-required-ids] --> |PRJ_ID, ids, PRJ_CNT|s5[fetch-project-details] --> |PRJ|s6[find-item] --> |ITEM|s7[job-result]
    end
    subgraph issue-automation
      s8[find-target-status] --> |OPTION, LABELS, OPTION_NODE, FIELD_ID, UPDATE_STATUS|s9[job-result]
    end
    subgraph pr-automation
      s10[opened] --> s11[find-target-status] --> |OPTION_NODE, FIELD_ID, UPDATE_STATUS|s12[job-result]
    end
    subgraph update-status-sprint
      s13[collect-inputs] --> |OPTION_NODE, FILEDL_ID, UPDATE_STATUS, ITEM, PRJ, ids|s14[update-status] --> s15[update-sprint] --> s16[job-result]
    end
    subgraph remove-labe-add-comment
      s17[find-labels-options] --> |LABELS, COMMENT, OPTION|s18[remove-label] --> s19[issue-comment]
    end

    check-valid-run --> |output-result, NUM, type|get-details
    get-details --> |output-result, ITEM, PRJ, ids|issue-automation
    issue-automation --> |LABELS, COMMENT, OPTION|remove-labe-add-comment
    check-valid-run --> |LABELS, COMMENT, OPTION|remove-labe-add-comment
    get-details --> |output-result, ITEM, PRJ, ids|pr-automation
    pr-automation --> |output-result, job-result, OPTION_NODE, FIELD_ID, UPDATE_STATUS, ITEM, PRJ, ids|update-status-sprint
    issue-automation --> |output-result, job-result, OPTION_NODE, FIELD_ID, UPDATE_STATUS, ITEM, PRJ, ids|update-status-sprint

```
