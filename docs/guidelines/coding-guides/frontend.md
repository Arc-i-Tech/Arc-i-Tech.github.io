# HTML, CSS, JavaScript, and Angular Coding Guidelines
## Table of Contents
- [HTML, CSS, JavaScript, and Angular Coding Guidelines](#html-css-javascript-and-angular-coding-guidelines)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [HTML Guidelines](#html-guidelines)
      - [General Structure](#general-structure)
      - [Indentation](#indentation)
      - [Naming Conventions](#naming-conventions)
      - [Accessibility (a11y)](#accessibility-a11y)
      - [Performance](#performance)
  - [CSS Guidelines](#css-guidelines)
      - [Structure and Organization](#structure-and-organization)
      - [Indentation](#indentation-1)
      - [Naming Conventions](#naming-conventions-1)
      - [Avoid Inline Styles](#avoid-inline-styles)
      - [Specificity and Nesting](#specificity-and-nesting)
      - [Responsive Design](#responsive-design)
      - [Variables and Reusability](#variables-and-reusability)
  - [JavaScript Guidelines](#javascript-guidelines)
      - [Code Structure](#code-structure)
      - [Naming Conventions](#naming-conventions-2)
      - [Indentation](#indentation-2)
      - [Functions](#functions)
      - [Error Handling](#error-handling)
      - [Comments](#comments)
      - [Code Quality](#code-quality)
      - [Security](#security)
  - [Angular Guidelines](#angular-guidelines)
      - [Project Structure](#project-structure)
      - [Naming Conventions](#naming-conventions-3)
      - [Components](#components)
      - [Services](#services)
      - [Modules](#modules)
      - [RxJS](#rxjs)
      - [Forms](#forms)
      - [Error Handling](#error-handling-1)
  - [SonarQube Recommendations](#sonarqube-recommendations)
      - [Code Smells](#code-smells)
      - [Maintainability and Technical Debt](#maintainability-and-technical-debt)
      - [Cyclomatic Complexity](#cyclomatic-complexity)
      - [Security Hotspots](#security-hotspots)
      - [Coverage and Quality Gates](#coverage-and-quality-gates)

---

## Introduction
- This document provides coding guidelines for **HTML**, **CSS**, **JavaScript**, and **Angular** development.
- These guidelines aim to ensure readability, maintainability, and quality, aligned with SonarQube standards for code quality and security.

---

## HTML Guidelines

#### General Structure
- Use semantic **HTML5** elements (e.g., `<header>`, `<footer>`, `<article>`, `<section>`).
- Ensure proper nesting and closing of **HTML** tags.
- Structure documents logically, with a clear hierarchy.

#### Indentation
- Use **2 spaces** for indentation to maintain readability and consistency.

#### Naming Conventions
- Use **kebab-case** for class and ID names.
  ```html
  <div id="main-container" class="content-box"></div>
  ```
- _**Kebab case** is a way of writing phrases without spaces, where spaces are replaced with hyphens `-`, and the words are typically all lower case._

#### Accessibility (a11y)
- Use `aria` attributes to improve accessibility where applicable.
- Ensure all images have `alt` attributes.
- Use `<label>` tags for form inputs to improve accessibility.

#### Performance
- Minimize the use of inline styles.
- Use external CSS and JavaScript files for better caching.
- Optimize images and avoid large assets where possible.

---

## CSS Guidelines

#### Structure and Organization
- Use a **modular structure** for CSS. Separate styles by components, pages, and common utilities.
- For larger projects, consider a methodology like [**BEM** (Block Element Modifier)](https://getbem.com/) for class naming.

#### Indentation
- Use **2 spaces** for indentation.

#### Naming Conventions
- Use **kebab-case** for class names and adhere to **BEM** conventions.
  ```css
  .button-primary { }
  .card__title { }
  ```

#### Avoid Inline Styles
- Avoid inline styles as they hinder reusability and maintainability.
- Prefer external stylesheets or styles defined within scoped Angular components.

#### Specificity and Nesting
- Avoid high specificity selectors. Use class selectors and avoid IDs in CSS selectors.
- Limit nesting to **3 levels deep** to maintain readability.

#### Responsive Design
- Use media queries to design for multiple screen sizes, **we follow a mobile-first approach**.
  ```css
  @media (min-width: 768px) {
    .container {
      padding: 16px;
    }
  }
  ```
- _Refer [CSS template](./../../../templates/css.tmp) for **mobile first approach** and **predefined media query**_

#### Variables and Reusability
- Use CSS variables (custom properties) to maintain a consistent design system.
  ```css
  :root {
    --primary-color: #3498db;
  }

  .button {
    background-color: var(--primary-color);
  }
  ```

---

## JavaScript Guidelines

#### Code Structure
- Follow [**ES6+ standards**](https://www.w3schools.com/js/js_es6.asp) and use modules to structure code in a modular way.
- Avoid global variables by using `const` and `let` instead of `var`.

#### Naming Conventions
- Use **camelCase** for variables and functions.
- Use **PascalCase** for class names and **ALL_CAPS** for constants.
  ```javascript
  const MAX_LIMIT = 100;
  function fetchData() { }
  ```

#### Indentation
- Use **2 spaces** per indentation level.

#### Functions
- Use arrow functions for concise syntax, but avoid them in object methods where `this` binding is required.
  ```javascript
  const add = (a, b) => a + b;
  ```

#### Error Handling
- Use `try-catch` blocks for error handling in asynchronous code.
- Log errors meaningfully without exposing sensitive information.
  ```javascript
  try {
    const data = await fetchData();
  } catch (error) {
    console.error("Failed to fetch data:", error);
  }
  ```

#### Comments
- Use comments sparingly and prefer self-documenting code. When necessary, use `//` for single-line comments and `/* */` for block comments.
- Include JSDoc comments for functions that are complex or widely used.
  ```javascript
  /**
  * Calculates the sum of two numbers.
  * @param {number} a - First number
  * @param {number} b - Second number
  * @return {number} Sum of a and b
  */
  function add(a, b) {
    return a + b;
  }
  ```

#### Code Quality
- Avoid deeply nested structures; consider simplifying logic by breaking it down into smaller functions.
- Use `map`, `filter`, and `reduce` instead of loops where applicable.

#### Security
- Avoid using `eval()` and limit the use of `innerHTML` to reduce XSS risks.
- Sanitize inputs in form handling to prevent injection attacks.

---

## Angular Guidelines

#### Project Structure
- Follow the **Angular Style Guide** for project structure and naming conventions.
- Organize files by features/modules, and avoid deeply nested folders.

#### Naming Conventions
- Use **PascalCase** for components, directives, and services.
- Use descriptive names with suffixes for Angular artifacts (e.g., `CustomerService`, `OrderComponent`).

#### Components
- Use `OnPush Change Detection` when possible to improve performance.
- Keep component classes small by delegating logic to services.
- Use lifecycle hooks appropriately (e.g., `ngOnInit`, `ngOnDestroy`).

#### Services
- Use Angular services to encapsulate business logic and data fetching.
- Ensure services are stateless and rely on Observables or Promises for async operations.

#### Modules
- Organize features into **feature modules** and lazy-load them where applicable.
- Use **SharedModule** for common components, directives, and pipes used across the app.

#### RxJS
- Use **pipeable operators** and **avoid nested subscriptions** to prevent memory leaks.
- Use `async` pipe in templates instead of subscribing manually where possible.

#### Forms
- Use **Reactive Forms** over Template-driven forms for scalability.
- Use custom validators for complex form validations and avoid direct DOM manipulation.

#### Error Handling
- Handle HTTP errors in services and display user-friendly messages in components.
- Use Angular’s `HttpInterceptor` to handle common HTTP behaviors like authentication or logging.

---

## SonarQube Recommendations

#### Code Smells
- Fix **code smells** in HTML, CSS, and JavaScript flagged by SonarQube, such as unused variables, duplicate code, and overly complex methods.

#### Maintainability and Technical Debt
- Keep maintainability in mind by breaking down large components and functions.
- Regularly address technical debt highlighted by SonarQube to ensure code quality over time.

#### Cyclomatic Complexity
- Simplify complex methods to keep cyclomatic complexity low. Break down large functions into smaller, more manageable ones.

#### Security Hotspots
- Address **security hotspots** flagged by SonarQube, including XSS in JavaScript, injection vulnerabilities in Angular, and secure handling of sensitive data.

#### Coverage and Quality Gates
- Ensure high **unit test coverage** for JavaScript/Angular components, aiming for a minimum of 80% coverage.
- Pass SonarQube **Quality Gate** checks before merging code.