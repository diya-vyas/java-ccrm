# USAGE Guide – Campus Course & Records Manager (CCRM)

This guide explains how to **compile, run, and use** the CCRM console application.

---

## 1. Prerequisites

* **Java 17 or later** installed and added to `PATH`.
  Verify with:

  ```bash
  java -version
  javac -version
  ```

* **Project structure:**

  ```
  src/
    edu/ccrm/cli/
    edu/ccrm/domain/
    edu/ccrm/service/
    edu/ccrm/io/
  data/        (for import/export CSV files)
  backup/      (auto-created for backups)
  ```

---

## 2. Compile the Project

From the project root:

```bash
rm -rf out
javac -d out $(find src -name "*.java")
```

This compiles all Java sources into the `out/` directory.

---

## 3. Run the Application

```bash
java -cp out edu.ccrm.cli.Main
```

---

## 4. CLI Navigation

When you run the app, you’ll see the **main menu**:

```
===== Campus Course & Records Manager =====
1. Manage Students
2. Manage Courses
3. Enrollment & Grades
4. Import/Export Data
5. Backup & Reports
0. Exit
```

Choose an option by typing a number.

---

## 👨‍🎓 5. Student Management

**Menu:**

```
--- Student Management ---
1. Add Student
2. List Students
3. Update Student
4. Deactivate Student
5. Print Student Profile & Transcript
```

* **Add Student** → Enter ID, Name, Email, RegNo.
* **List Students** → Shows all active & inactive students.
* **Update Student** → Modify name/email.
* **Deactivate Student** → Marks student as inactive.
* **Print Profile** → Shows student details and transcript.

---

## 6. Course Management

**Menu:**

```
--- Course Management ---
1. Add Course
2. List Courses
3. Update Course
4. Deactivate Course
5. Search & Filter
```

* Each course has: `code, title, credits, instructor, semester, department`.
* **Search & Filter** supports queries by instructor, department, or semester (via Java Streams).

---

## 7. Enrollment & Grades

**Menu:**

```
--- Enrollment & Grades ---
1. Enroll Student in Course
2. Unenroll Student from Course
3. Record Marks & Grade
4. View Transcript
```

* **Enroll/Unenroll** → Students may enroll, respecting **max credits per semester**.
* **Record Marks** → Enter numeric marks, system maps to `Grade` (S, A, B, …, F).
* **Transcript** → Displays enrolled courses + GPA (computed via Grade points).

---

## 8. Import/Export Data

**Menu:**

```
--- Import/Export ---
1. Import Students
2. Import Courses
3. Export Students
4. Export Courses
5. Export Enrollments
```

* Place `students.csv` or `courses.csv` in the `data/` folder.
  Example `students.csv`:

  ```
  id,fullName,email,regNo
  S1,John Doe,john@example.com,R001
  S2,Jane Smith,jane@example.com,R002
  ```

  Example `courses.csv`:

  ```
  code,title,credits,instructor,semester,department
  CSE101,Intro to CS,3,Dr. Alan,SPRING,CSE
  MAT201,Discrete Math,4,Dr. Eve,FALL,MATH
  ```

* Exports will be saved as `*_export.csv` under `data/`.

---

## 💾 9. Backup & Reports

**Menu:**

```
--- Backup & Reports ---
1. Backup Data
2. Compute Backup Directory Size
3. List Backup Files (depth 2)
```

* **Backup Data** → Copies exported files into `backup/YYYYMMDD_HHmmss/`.
* **Compute Backup Directory Size** → Recursively sums file sizes.
* **List Backup Files** → Recursively lists backup files up to depth 2.

---

## 🎯 10. Control Structure Demonstrations

The CLI is designed to showcase **Java decision & loop constructs**:

* **Classic switch** → main menu.
* **Enhanced switch** → student submenu.
* **do-while** → main menu loop.
* **while** → used in enrollment iteration.
* **for & enhanced-for** → used in course and student listing.
* **break, continue** → input validation & menu exits.
* **labeled break** → `break MAIN_LOOP;` clean exit from app.

---

## ✅ 11. Exit

Choose option `0` in the main menu to exit:

```
Exiting...
```
