# 📘 Campus Course & Records Manager (CCRM)

A console-based Java SE application to manage students, courses, enrollments, grades, and transcripts, with file operations (import/export, backup), and demonstration of OOP, Java Streams, NIO.2, and design patterns.

---

## 🚀 Evolution of Java (Timeline Highlights)

* **1991** – Project Oak initiated by Sun Microsystems.
* **1995** – Java 1.0 officially released (“Write Once, Run Anywhere”).
* **1998** – Java 2 (J2SE, J2EE, J2ME) split into editions.
* **2004** – Java 5 introduced generics, annotations, enhanced for-loop.
* **2011** – Java 7: try-with-resources, NIO.2 File API.
* **2014** – Java 8: Lambdas, Streams, Date/Time API.
* **2017** – Oracle moves to 6-month release cycle.
* **2021** – Java 17 (LTS): modern features, sealed classes, pattern matching.
* **2023+** – Java 21 (latest LTS): record patterns, virtual threads.

---

## 🔎 Java Editions: ME vs SE vs EE

| Feature/Aspect     | **Java ME** (Micro Edition)                      | **Java SE** (Standard Edition)                 | **Java EE / Jakarta EE** (Enterprise Edition) |
| ------------------ | ------------------------------------------------ | ---------------------------------------------- | --------------------------------------------- |
| **Purpose**        | Mobile/embedded devices                          | General-purpose desktop apps                   | Large-scale, distributed enterprise apps      |
| **APIs**           | Subset of SE (lightweight libs)                  | Core Java (Collections, IO, JDBC, Swing, etc.) | Adds web, EJB, JMS, JPA, Servlets, etc.       |
| **Use Cases**      | Smartcards, IoT, feature phones                  | CLI tools, desktop apps, small servers         | Banking apps, e-commerce, enterprise portals  |
| **Runtime**        | KVM (Kilo Virtual Machine)                       | JVM                                            | Application servers (GlassFish, JBoss, etc.)  |
| **Current Status** | Mostly legacy (replaced by Android SDK/IoT SDKs) | Active, foundation of all Java                 | Active as Jakarta EE under Eclipse Foundation |

---

## 🏗️ Java Architecture (JDK, JRE, JVM)

### 1. **JDK (Java Development Kit)**

* Complete development toolkit.
* Includes compiler (`javac`), JRE, and tools (debugger, javadoc, etc.).
* Used by developers to **write and build Java programs**.

### 2. **JRE (Java Runtime Environment)**

* Provides libraries + JVM for **running Java applications**.
* No compiler — only runtime environment.
* Suitable for end-users who want to run, not develop.

### 3. **JVM (Java Virtual Machine)**

* Abstract machine that executes Java bytecode.
* Platform-independent execution.
* Handles class loading, JIT compilation, garbage collection.

### 🔄 How They Interact

1. Developer writes code → compiles with **JDK** (`javac`).
2. Compiled `.class` (bytecode) is executed by the **JVM**.
3. **JVM** needs libraries → provided by the **JRE**.

```
 Source Code (.java)
        |
     [ JDK - javac ]
        v
  Bytecode (.class)
        |
     [ JVM + JRE ]
        v
   Machine Code (Execution)
```

---

## ⚙️ Install & Configure Java on Windows

1. **Download JDK**

   * Go to [Oracle JDK Downloads](https://www.oracle.com/java/technologies/javase-downloads.html)
   * Choose **JDK 17 (LTS)** or later.

2. **Install JDK**

   * Run the installer and follow steps (default path: `C:\Program Files\Java\jdk-17`).

3. **Set Environment Variables**

   * Open **System Properties → Advanced → Environment Variables**.
   * Add:

     * `JAVA_HOME = C:\Program Files\Java\jdk-17`
     * Append `%JAVA_HOME%\bin` to `PATH`.

4. **Verify Installation**

   * Open Command Prompt and run:

     ```bash
     java -version
     javac -version
     ```
   * Expected output:

     ```
     java version "17.x.x"
     javac 17.x.x
     ```

*(Add a screenshot of your terminal output as proof in `/screenshots/java_version.png`)*

---

## 🖥️ Eclipse IDE Setup

1. **Download Eclipse IDE**

   * Get [Eclipse IDE for Java Developers](https://www.eclipse.org/downloads/).

2. **Install & Configure JDK in Eclipse**

   * Go to `Window → Preferences → Java → Installed JREs`.
   * Click `Add → Standard VM → point to JDK path (C:\Program Files\Java\jdk-17)`.

3. **Create New Project**

   * `File → New → Java Project`.
   * Name: `CCRM`.
   * Set JDK 17 as execution environment.

4. **Add Source Folders**

   * Right-click `src → New → Package`.
   * Create: `edu.ccrm.cli`, `edu.ccrm.domain`, `edu.ccrm.service`, `edu.ccrm.io`, `edu.ccrm.util`, `edu.ccrm.config`.

5. **Add Main Class**

   * Create `edu.ccrm.cli.Main`.
   * Add `public static void main(String[] args)` entry point.

6. **Run Configurations**

   * Right-click `Main.java → Run As → Java Application`.

*(Add screenshots of Eclipse setup and run in `/screenshots/eclipse_setup.png`)*

---

## 📂 Project Overview

This project demonstrates:

* **OOP** (Encapsulation, Inheritance, Abstraction, Polymorphism).
* **Enums, Interfaces, Lambdas, Streams**.
* **Design Patterns** (Singleton, Builder).
* **File Ops** with NIO.2 + Streams (import/export, backup).
* **CLI menu** with loops, switches, labeled jumps.
* **Transcript/GPA calculation** with polymorphism.

## 📑 Requirement-to-Code Mapping

| **Syllabus / Requirement**                                               | **Where Implemented in Code**                                                                                                               | **Notes**                                                                                                                     |
| ------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------- |
| **OOP Concepts (Encapsulation, Inheritance, Abstraction, Polymorphism)** | `edu.ccrm.domain.Person` (abstract base), `edu.ccrm.domain.Student`, `edu.ccrm.domain.Course`, `edu.ccrm.domain.Enrollment`                 | `Person` → abstraction, `Student` inherits, `Enrollment` polymorphic transcript printing                                      |
| **Use of Java Enums**                                                    | `edu.ccrm.domain.Semester`, `edu.ccrm.domain.Grade`                                                                                         | Used in Courses and GPA calculation                                                                                           |
| **Interfaces & Design Patterns**                                         | Builder Pattern → `Course.Builder`, Singleton-like Service → `StudentService`, `CourseService`                                              | Simplifies object construction and service reuse                                                                              |
| **Streams & Lambda Expressions**                                         | `CourseService.search/filter` methods (Stream API), `ImportExportService` (Files.lines + Streams)                                           | Demonstrates filtering by instructor/department/semester                                                                      |
| **Exception Handling**                                                   | Throughout (`ImportExportService`, `BackupService`)                                                                                         | File handling uses try/catch                                                                                                  |
| **Collections Framework**                                                | `Map<String, Student>` in `StudentService`, `Map<String, Course>` in `CourseService`, `Map<String,List<Enrollment>>` in `EnrollmentService` | Efficient storage & retrieval                                                                                                 |
| **Date/Time API (java.time)**                                            | `Student.enrollmentDate`                                                                                                                    | Uses `LocalDate`                                                                                                              |
| **File I/O (NIO.2 + Streams)**                                           | `edu.ccrm.io.ImportExportService`, `edu.ccrm.io.BackupService`                                                                              | Import/Export CSV, recursive backup                                                                                           |
| **Backup & Recursive Utilities**                                         | `BackupService.backup()`, `computeDirSize()`, `listFilesByDepth()`                                                                          | Uses `Files.walk()` recursion                                                                                                 |
| **CLI Menu with Control Structures**                                     | `edu.ccrm.cli.Menu`                                                                                                                         | Shows `switch` (classic & enhanced), loops (`do-while`, `while`, `for`, `enhanced-for`), `continue`, `break`, labeled `break` |
| **Transcript / GPA Calculation**                                         | `Student.printTranscript()`, `EnrollmentService.computeGPA()`                                                                               | Polymorphism + grade points                                                                                                   |
| **Import/Export Data**                                                   | `ImportExportService.importStudents()`, `importCourses()`, `exportStudents()`, etc.                                                         | Works with `data/*.csv` files                                                                                                 |
| **Reports**                                                              | `BackupService.computeDirSize()`, `listFilesByDepth()`                                                                                      | Summarizes backup directory                                                                                                   |

---

## CLI Workflow Demonstrations

* **Loops:**

  * `do-while` → main menu loop.
  * `while` → enrollment iteration.
  * `for` → course listing.
  * `enhanced-for` → student listing.
* **Decision Constructs:**

  * **Classic switch** → Main Menu.
  * **Enhanced switch (→)** → Student Menu.
* **Control Flow:**

  * `continue` → input validation skip.
  * `break` → exit switch.
  * **Labeled break** → `break MAIN_LOOP;` exits menu loop.