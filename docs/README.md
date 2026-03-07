# Orange - User Guide

![Orange Task Manager](/docs/Ui.png)

Orange is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Orange can get your task management done faster than traditional GUI apps.

---

## Quick Start

1. Ensure you have Java `21` or above installed in your Computer.

2. Download the latest `orange.jar` from [here](https://github.com/LEDTOMATO/ip/releases).

3. Copy the file to the folder you want to use as the home folder for Orange.

4. Open a command terminal, navigate to the folder with the jar file, and run:
```bash
   java -jar orange.jar
```
The GUI should appear in a few seconds.

5. Type a command in the command box and press Enter to execute it.

---

## Features

### 🎯 Quick Action Buttons (NEW!)

Orange now includes three convenient buttons to help you create tasks faster:

- **📝 Todo Button** - Automatically fills `todo ` in the input field
- **⏰ Deadline Button** - Automatically fills `deadline ` in the input field
- **📅 Event Button** - Automatically fills `event ` in the input field

**How to use:**
1. Click any of the three buttons
2. The command template will appear in the input field
3. Complete the command with your task details
4. Press Enter or click Send!

**Example:**
1. Click **📝 Todo**
2. Input field shows: `todo `
3. Type: `read book`
4. Press Enter
5. Task created! ✅

### Adding a todo task: `todo`

Adds a simple task without any date/time.

**Format:** `todo DESCRIPTION`

**Examples:**
- `todo read book`
- `todo buy groceries`

**Expected output:**
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.

---

### Adding a deadline task: `deadline`

Adds a task that needs to be done by a specific date.

**Format:** `deadline DESCRIPTION /by YYYY-MM-DD`

**Examples:**
- `deadline return book /by 2025-03-15`
- `deadline submit assignment /by 2025-04-01`

**Expected output:**
Got it. I've added this task:
[D][ ] return book (by: Mar 15 2025)
Now you have 2 tasks in the list.

---

### Adding an event task: `event`

Adds a task that starts and ends at specific dates.

**Format:** `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD`

**Examples:**
- `event project meeting /from 2025-03-10 /to 2025-03-12`
- `event conference /from 2025-05-01 /to 2025-05-03`

**Expected output:**
Got it. I've added this task:
[E][ ] project meeting (from: Mar 10 2025 to: Mar 12 2025)
Now you have 3 tasks in the list.

---

### Listing all tasks: `list`

Shows all tasks in your task list.

**Format:** `list`

**Expected output:**
Here are the tasks in your list:

[T][ ] read book
[D][ ] return book (by: Mar 15 2025)
[E][ ] project meeting (from: Mar 10 2025 to: Mar 12 2025)


---

### Marking a task as done: `mark`

Marks a task as completed.

**Format:** `mark INDEX`

**Examples:**
- `mark 1`
- `mark 2`

**Expected output:**
Nice! I've marked this task as done:
[T][X] read book

---

### Unmarking a task: `unmark`

Marks a task as not done yet.

**Format:** `unmark INDEX`

**Examples:**
- `unmark 1`

**Expected output:**
OK, I've marked this task as not done yet:
[T][ ] read book

---

### Finding tasks: `find`

Finds tasks that contain a specific keyword.

**Format:** `find KEYWORD`

**Examples:**
- `find book`
- `find meeting`

**Expected output:**
Here are the matching tasks in your list:

[T][ ] read book
[D][ ] return book (by: Mar 15 2025)


---

### Deleting a task: `delete`

Removes a task from your list permanently.

**Format:** `delete INDEX`

**Examples:**
- `delete 1`
- `delete 3`

**Expected output:**
Noted. I've removed this task:
[T][ ] read book
Now you have 2 tasks in the list.

---

### Adding a place: `place`

Saves information about restaurants or places you've visited.

**Format:** `place NAME /at LOCATION /rating RATING /notes NOTES`

**Examples:**
- `place Marina Bay Sands /at 10 Bayfront Avenue, 018956 /rating 5 /notes Amazing rooftop pool`
- `place Lau Pa Sat /at Raffles Quay /rating 4 /notes Good satay`

**Expected output:**
Got it. I've added this place:
[P][ ] Marina Bay Sands | 10 Bayfront Avenue, 018956 📍 | ★★★★★ | Amazing rooftop pool
Now you have 3 items in the list.

---

### Getting motivation: `cheer`

Displays a random motivational quote for programmers!

**Format:** `cheer`

**Expected output:**
Keep going – even the best programmers started out writing 'Hello World'!

---

### Exiting the program: `bye`

Exits Orange and saves all your data.

**Format:** `bye`

**Expected output:**
Bye. Hope to see you again soon!

---

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| **Todo** | `todo DESCRIPTION` | `todo read book` |
| **Deadline** | `deadline DESCRIPTION /by DATE` | `deadline return book /by 2025-03-15` |
| **Event** | `event DESCRIPTION /from DATE /to DATE` | `event meeting /from 2025-03-10 /to 2025-03-12` |
| **List** | `list` | `list` |
| **Mark** | `mark INDEX` | `mark 1` |
| **Unmark** | `unmark INDEX` | `unmark 1` |
| **Find** | `find KEYWORD` | `find book` |
| **Delete** | `delete INDEX` | `delete 1` |
| **Place** | `place NAME /at LOCATION /rating 1-5 /notes NOTES` | `place Restaurant /at Downtown /rating 5 /notes Great food` |
| **Cheer** | `cheer` | `cheer` |
| **Exit** | `bye` | `bye` |

---

## FAQ

**Q: How do I transfer my data to another computer?**  
A: Copy the `data/orange.txt` file to the same folder as your JAR file on the new computer.

**Q: Can I edit the data file directly?**  
A: Yes, but be careful! The app expects a specific format. Any errors may cause data loss.

**Q: What date format should I use?**  
A: Always use `YYYY-MM-DD` format (e.g., 2025-03-15).

---

## Acknowledgements

- JavaFX tutorial: [SE-EDU JavaFX Guide](https://se-education.org/guides/tutorials/javaFx.html)
- Project structure based on: [Duke Increment](https://nus-cs2103-ay2425s2.github.io/website/schedule/week2/project.html)
