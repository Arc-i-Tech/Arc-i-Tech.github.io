## Review Guidelines _(Review Pull Request)_
Reviewing pull requests (PRs) on GitHub is a key part of ensuring code quality, consistency, and collaboration in any project. Here are some guidelines to conduct a thorough and effective PR review:

#### 1. Preparation
- **Understand the Scope:** Before diving into the code, read the PR _title_, _description_, and related _issues_ or _tasks_. This helps you understand what the PR aims to achieve and the specific areas it touches.
- **Review Commit History:** Glance over the commits in the PR to see a breakdown of changes and focus areas. This can help identify whether changes were made gradually or if some areas need closer inspection.

#### 2. Code Quality
- **Check Code Readability:** Ensure code is easy to read and understand. Proper naming conventions, consistent formatting, and clear organization make the code easier for future contributors.
- **Maintainability:** Ensure the code is modular, reusable, and maintains separation of concerns. Ask, “Is this code easy to maintain and extend?”
- **Consistency with Style Guide:** Code should align with the team’s agreed-upon coding style and standards _(such as using ESLint for JavaScript)_.

#### 3. Functional Testing
- **Check the Requirements:** Verify that the PR meets all acceptance criteria defined in the related **task** or **issue**.
- **Test the Code Locally:** If feasible, pull the PR locally and test the changes. Running the code can reveal issues that may not be apparent from reading the code alone.
- **Automated Tests:** Review any added tests to ensure they cover the functionality thoroughly. If the PR lacks tests, request that the author add them.
- **Performance Impact:** For complex PRs, consider the performance implications. Will this code impact the app's load times, memory use, or efficiency?

#### 4. Code Logic and Functionality
- **Evaluate Core Logic:** Ensure the code’s core logic is correct and aligns with the project requirements.
- **Edge Cases and Error Handling:** Look for code that considers edge cases, validation, and error handling. If edge cases are not covered, suggest improvements.
- **Avoid Redundant Code:** Make sure there is no duplication or redundant functionality. Encourage using utility functions and adhering to DRY (Don't Repeat Yourself) principles.

#### 5. Documentation
- **Code Comments:** Complex sections should have comments explaining the logic. Ensure comments are clear, concise, and genuinely helpful.
- **Public Documentation and Code Docs:** For changes affecting public `APIs`, `configuration`, or project functionality, ensure that documentation is updated (README, wiki, or inline docs).
- **Commit Messages:** Commit messages should be clear, descriptive, and follow a consistent format. They should explain the "why" behind changes, not just the "what."

#### 6. Security Considerations
- **Validate Input Data:** Check for validation on any user input or data passed into functions to prevent injections, XSS, or other vulnerabilities.
- **Sensitive Data:** Ensure n**o sensitive information** (like passwords, tokens, or API keys) is exposed in the code.
- **Access Control:** Verify that only authorized users or processes have access to the code’s functions or data.

#### 7. Provide Constructive Feedback
- **Be Specific:** Point out specific lines or sections and give concrete suggestions for improvement.
- **Focus on the Code, Not the Author:** Keep feedback objective and focused on the code. Use language like, `Could we consider…` or `What if we tried…` to foster collaboration.
- **Acknowledge Good Work:** Highlight well-written code, clever solutions, or good documentation to encourage positive practices.
- **Use GitHub's Review Tools:** Use the **Approve**, **Request Changes**, or **Comment** options to clearly communicate your feedback status on GitHub.

#### 8. Final Checks Before Approval
- **Run Tests:** Verify that all tests pass on `CI/CD`. If tests fail, evaluate whether it’s a false negative or if the author needs to address it.
- **Ensure No Unnecessary Files Are Included:** Ensure that no irrelevant files (like .DS_Store, temporary files, or debugging assets) are in the PR.
- **Cross-Check Dependencies:** Make sure the PR doesn’t introduce unnecessary dependencies or libraries, as they can bloat the project.

#### 9. Approve or Request Changes
- If the PR meets all requirements, and there are no blockers, you can approve it.
- If changes are needed, request changes with specific suggestions on what to address.
- If uncertain, add comments to open a discussion without blocking the PR (choose the Comment option instead of Request Changes).
  
#### 10. Follow Up
- **Respond to Replies:** If the PR author has questions or makes changes, be prompt in your responses to help the PR move forward efficiently.
- **Review Revisions:** Re-check the changes made after your review to ensure they addressed the feedback correctly.