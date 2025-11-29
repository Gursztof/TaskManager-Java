# Java Task Manager

This is my **first full Java project** — a console-based **Task Manager** that allows you to create, display, sort, filter, update, and delete tasks.  
The project was built to practice Java fundamentals, object-oriented programming, and basic file handling.

---

## 🔧 Features
- Add new tasks  
- Mark tasks as completed / not completed  
- Delete tasks  
- Sort tasks by:
  - date (ascending/descending)
  - priority (L → H, H → L)
- Filter tasks (completed, not completed, priority L/M/H)
- Tasks are stored in a local `.txt` file
- Colored priority display (ANSI escape codes)

---

## 🛠 Technologies & Concepts Used
- **Java 17+**
- **OOP (Object-Oriented Programming)**
  - Classes: `Task`, `TaskManager`, `FileManager`, `Main`
- **LocalDateTime** (`java.time`)
  - `LocalDateTime.now()`
  - `DateTimeFormatter`
- **Collections**
  - `ArrayList`
  - Sorting with `Comparator`
- **File I/O**
  - `FileWriter`, `BufferedReader`, `FileReader`
- **ANSI Console Colors**
- **Exception handling**
  - `try/catch`
  - Input validation using `Scanner`

---

## 🚀 How to Run
1. Clone the repository  
2. Open it in any Java IDE (IntelliJ / VS Code / Eclipse)  
3. Run `Main.java`  
4. The program creates/uses a file named `allTasks.txt`

---

## 📌 Notes
This is my first “real” Java project — built to understand how console applications work and how to handle data persistence without databases.
