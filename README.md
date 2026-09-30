# Java Quiz Application

A simple Java-based quiz application that allows users to take multiple-choice quizzes and receive scores. Built using core Java concepts with optional GUI using Swing or JavaFX.

## Features

- ✅ Multiple-choice questions
- ✅ Score calculation with instant results
- ✅ Optional countdown timer
- ✅ Admin panel to add/update/delete questions (optional)
- ✅ Questions stored in external files (CSV/JSON/Text)

## Technologies Used

- Java (Core)
- Swing/JavaFX (for GUI version)
- Collections Framework
- JDBC(Java Database Connectivity)
- MySQL(For storing and registering Users)

## Setup

Database credentials are read from environment variables:

| Variable  | Default                                         |
|-----------|-------------------------------------------------|
| `DB_URL`  | `jdbc:mysql://localhost:3306/story_login_db`    |
| `DB_USER` | `root`                                          |
| `DB_PASS` | *(empty)*                                       |

Create the schema with `user info db.sql`, then compile and run:

```bash
javac *.java
java Login
```
