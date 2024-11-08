# Java Coding Guidelines

## Table of Contents
- [Java Coding Guidelines](#java-coding-guidelines)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [Code Formatting](#code-formatting)
      - [Indentation](#indentation)
      - [Line Length](#line-length)
      - [Braces](#braces)
      - [Blank Lines](#blank-lines)
      - [Whitespace](#whitespace)
  - [Naming Conventions](#naming-conventions)
      - [Classes and Interfaces](#classes-and-interfaces)
      - [Methods and Variables](#methods-and-variables)
      - [Constants](#constants)
      - [Packages](#packages)
      - [Enums](#enums)
  - [Code Structure](#code-structure)
      - [Class Organization](#class-organization)
      - [Method Organization](#method-organization)
      - [Imports](#imports)
  - [Error Handling](#error-handling)
      - [Exception Handling](#exception-handling)
      - [Logging](#logging)
  - [Best Practices](#best-practices)
      - [DRY Principle](#dry-principle)
      - [Encapsulation](#encapsulation)
      - [Code Comments](#code-comments)
      - [Avoid Magic Numbers](#avoid-magic-numbers)
      - [Avoid Hardcoded Paths](#avoid-hardcoded-paths)
      - [Immutable Classes](#immutable-classes)
  - [SonarQube Recommendations](#sonarqube-recommendations)
      - [Code Smells](#code-smells)
      - [Maintainability and Technical Debt](#maintainability-and-technical-debt)
      - [Cyclomatic Complexity](#cyclomatic-complexity)
      - [Security Hotspots](#security-hotspots)
      - [Coverage and Quality Gates](#coverage-and-quality-gates)

---

## Introduction
This document outlines **coding guidelines for Java** to ensure code readability, maintainability, and **adherence to SonarQube's quality standards**. By following these guidelines, developers can produce consistent, high-quality Java code.

---

## Code Formatting

#### Indentation
- Use **4 spaces** per indentation level.
- Avoid using tabs. Ensure all team members use spaces for indentation to maintain consistency.

#### Line Length
- Limit line length to **120 characters**. Use line breaks for longer lines to enhance readability.

#### Braces
- Use the **K&R style** (Kernighan & Ritchie) for braces.
- Place the opening brace on the same line as the statement.
  ```java
  if (condition) {
      // code block
  } else {
      // code block
  }
  ```

#### Blank Lines
- Use blank lines to separate different code sections, especially between methods and logical blocks within methods, to improve readability.

#### Whitespace
- Add spaces around operators (`=`, `+`, `-`, etc.).
- Add a space after commas in parameter lists and array initializations.
  ```java
  int[] numbers = {1, 2, 3, 4};
  ```

---

## Naming Conventions

#### Classes and Interfaces
- Use **PascalCase** for class and interface names.
  ```java
  public class CustomerService { }
  ```

#### Methods and Variables
- Use **camelCase** for method and variable names.
  ```java
  private int customerCount;
  public void calculateTotal() { }
  ```

#### Constants
- Use **UPPER_CASE** with underscores for constants.
  ```java
  public static final int MAX_LIMIT = 100;
  ```

#### Packages
- Use **lowercase** with no underscores.
  ```java
  package com.example.project;
  ```

#### Enums
- Use **PascalCase** for enum names and **UPPER_CASE** for values.
  ```java
  public enum OrderStatus {
    PENDING, COMPLETED, CANCELED;
  }
  ```

---

## Code Structure

#### Class Organization
1. **Constants:** `public static final` fields should come first.
2. **Fields:** Instance variables should be private and organized by visibility.
3. **Constructors:** Place constructors after fields.
4. **Methods:** Order methods by access modifier: `public`, `protected`, and `private`.

#### Method Organization
- Place helper methods close to the methods they support.
- Group related methods together for easier reading and maintenance.

#### Imports
- Use `import` statements at the beginning _(after package declaration)_ of the file.
- Avoid wildcard imports (`import java.util.*;`).
- Organize imports alphabetically and by standard libraries first, followed by custom imports.

---

## Error Handling

#### Exception Handling
- Use **checked exceptions** for recoverable conditions and **unchecked exceptions** for programming errors.
- Catch specific exceptions instead of general ones.
  ```java
  try {
    // code
  } catch (IOException e) {
    // handle exception
  }
  ```

#### Logging
- Log exceptions with relevant information to understand the error context.
- Avoid logging sensitive information (passwords, tokens, etc.).
  ```java
  logger.error("Failed to process order", e);
  ```

---

## Best Practices

#### DRY Principle
- Follow the **Don't Repeat Yourself (DRY)** principle by creating reusable methods or classes for common functionality.

#### Encapsulation
- Use **private** or **protected** visibility for member variables.
- Use **getter** and **setter** methods to access private fields.

#### Code Comments
- Use comments sparingly. Prefer self-explanatory code.
- Use Javadoc comments for public classes, methods, and complex logic sections.
  ```java
  /**
  * Calculates the total price of the order.
  * @param quantity Quantity of items
  * @param pricePerItem Price per item
  * @return Total price
  */
  public double calculateTotal(int quantity, double pricePerItem) {
    return quantity * pricePerItem;
  }
  ```
- Use **default** comments from **eclipse**

#### Avoid Magic Numbers
- Replace magic numbers with named constants.
  ```java
  private static final int MAX_RETRIES = 5;
  ```

#### Avoid Hardcoded Paths
- Use configuration files or environment variables for paths and URLs instead of hardcoding.

#### Immutable Classes
- Use final fields and provide no setters to make a class immutable if its state should not change after construction.

---

## SonarQube Recommendations

#### Code Smells
- Fix code smells reported by SonarQube, such as unused variables, long methods, and duplicated code.

#### Maintainability and Technical Debt
- Reduce technical debt by following SonarQube’s maintainability suggestions, such as avoiding deeply nested structures and redundant code.

#### Cyclomatic Complexity
- Keep methods and classes simple, aiming for a low **cyclomatic complexity** score. Break down complex methods into smaller, reusable methods.

#### Security Hotspots
- Follow SonarQube's **security hotspot** recommendations to address security issues like SQL injection, hardcoded passwords, and insecure cryptographic algorithms.

#### Coverage and Quality Gates
- Ensure **unit tests** cover new code with a minimum of 80% coverage.
- Address issues flagged by SonarQube’s **Quality Gate** before merging code.


