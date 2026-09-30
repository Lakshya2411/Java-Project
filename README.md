# Java Quiz Application

A desktop quiz app built with Java Swing and MySQL. Users register and log in, then take a timed multiple-choice Java quiz and get their score at the end.

## Features

- **Registration and login** backed by MySQL through JDBC
- **Secure password storage:** salted PBKDF2-HMAC-SHA256 hashes (`PasswordUtil.java`), never plain text
- **SQL injection safe:** all queries use `PreparedStatement`
- **Timed questions:** 15-second countdown per question; the quiz moves on automatically when time runs out
- **Instant scoring** when the quiz ends

## Tech stack

Java · Swing · JDBC · MySQL

## Project structure

| File | Purpose |
|---|---|
| `Login.java` | Login window and app entry point |
| `RegisterGUI.java` | Registration window |
| `QuizSystem.java` | Quiz window: questions, options, timer and scoring |
| `PasswordUtil.java` | Password hashing and verification |
| `schema.sql` | Creates the `story_login_db` database and `users` table |

## Setup

1. Create the database:
   ```bash
   mysql -u root -p < schema.sql
   ```
2. Download [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) and put the `.jar` in the project folder.
3. Set your database password (credentials are read from environment variables):

   | Variable  | Default                                      |
   |-----------|----------------------------------------------|
   | `DB_URL`  | `jdbc:mysql://localhost:3306/story_login_db` |
   | `DB_USER` | `root`                                       |
   | `DB_PASS` | *(empty)*                                    |

4. Compile and run:
   ```bash
   # macOS / Linux
   javac *.java
   java -cp ".:mysql-connector-j.jar" Login

   # Windows
   javac *.java
   java -cp ".;mysql-connector-j.jar" Login
   ```

> Accounts created before password hashing was added won't log in; register them again.

## Roadmap

- [ ] Load questions from a file or the database instead of hardcoding them
- [ ] Admin screen to add, edit and delete questions
- [ ] Save each user's quiz history and show a leaderboard
