contactUsForm = document.querySelector("#contact-us")

contactUsForm.addEventListener('submit', function (event) {
  if (!contactUsForm.checkValidity()) {
    return;
  }
  var mobileNo = document.getElementById("mono").value;
  if (mobileNo) {
    mobileNo = "<tel:" + mobileNo + "|" + mobileNo + ">";
  } else {
    mobileNo = "NA"
  }

  var payload = {
    "blocks": [
      {
        "type": "header",
        "text": {
          "type": "plain_text",
          "text": document.getElementById("title").value,
          "emoji": true
        }
      },
      {
        "type": "section",
        "text": {
          "type": "plain_text",
          "text": document.getElementById("message").value,
          "emoji": true
        }
      },
      {
        "type": "divider"
      },
      {
        "type": "section",
        "fields": [
          {
            "type": "mrkdwn",
            "text": "*Sender:*\n" + document.getElementById("name").value,
          },
          {
            "type": "mrkdwn",
            "text": "*Sender Email:*\n<mailto:" + document.getElementById("email").value + "|" + document.getElementById("email").value + ">"
          }
        ]
      },
      {
        "type": "section",
        "fields": [
          {
            "type": "mrkdwn",
            "text": "*Sender Mobile no:*\n" + mobileNo,
          }
        ]
      },
      {
        "type": "divider"
      },
      {
        "type": "section",
        "text": {
          "type": "mrkdwn",
          "text": "*Assignee:*"
        },
        "accessory": {
          "type": "users_select",
          "placeholder": {
            "type": "plain_text",
            "text": "Select a user",
            "emoji": true
          },
          "action_id": "users_select-action"
        }
      },
      {
        "type": "section",
        "text": {
          "type": "mrkdwn",
          "text": "*Deadline:*"
        },
        "accessory": {
          "type": "datepicker",
          "initial_date": "1990-04-28",
          "placeholder": {
            "type": "plain_text",
            "text": "Select a date",
            "emoji": true
          },
          "action_id": "datepicker-action"
        }
      },
      {
        "type": "input",
        "element": {
          "type": "plain_text_input",
          "multiline": true,
          "action_id": "plain_text_input-action"
        },
        "label": {
          "type": "plain_text",
          "text": "Comment",
          "emoji": true
        }
      }
    ]
  };

  result = postDataToWebHook("https://hooks.slack.com/services/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", payload);
  if (result) {
    contactUsForm.reset();
  }
}, false);