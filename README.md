# Cinematic Archive & Review Engine

## Overview
A modular Java CLI application built to manage cinematic records, user ratings, and asynchronous metadata ingestion. The system utilizes an embedded SQLite database through JDBC to provide ACID-compliant relational data persistence without requiring an external server daemon.

## Features
*   **Object-Oriented Architecture:** Fully encapsulated domain models for Films, Users, and Reviews.
*   **Persistent Storage:** Embedded SQLite integration executing sanitized `PreparedStatement` transactions.
*   **Multithreading & Concurrency:** Background batch ingestion utilizing Java `Thread` and `Runnable` to process records asynchronously while maintaining an active terminal prompt.
*   **Input Validation:** Terminal validation catching scanner mismatch errors to prevent runtime crashes.

## Technologies & Tools Used
*   **Language:** Java (JDK 17+)
*   **Database:** SQLite 3
*   **Libraries:** Java SQL (`java.sql`), Java Util (`java.util`), SQLite JDBC Driver (`sqlite-jdbc.jar`)
*   **Version Control:** Git & GitHub

## Steps to Install & Run
1. Ensure the SQLite JDBC driver (`sqlite-jdbc.jar`) is located inside the `lib/` directory at the project root.
2. Open a terminal and navigate to the project root directory.
3. **Compile the project:**
   ```bash
   javac -cp "lib/*" src/models/*.java src/services/*.java src/db/*.java src/utils/*.java src/Main.java -d out
4. **Execute the application:**
  ```Bash
  java -cp "out:lib/*" Main
## Instructions for Testing
*  Exception Testing: At any menu prompt expecting a number (e.g., Release Year or Menu Selection), input a text string to verify the InputMismatchException safely catches the error and re-prompts you.
*  Database Persistence Testing: Add a new film (Option 1) and a review (Option 4). Exit the application (Option 7), restart it, and select Option 2 to verify the records load directly from cinematic_archive.db.
*  Concurrency Testing: Select Option 6 to trigger the batch import. Immediately type 2 and press Enter to view the catalog while the background thread continues to log imported records asynchronously.

## Screenshots
<img width="485" height="197" alt="image" src="https://github.com/user-attachments/assets/9475534a-c9f7-4f0d-a4cb-58dcdca4c672" />
<img width="485" height="180" alt="image" src="https://github.com/user-attachments/assets/89da246a-dd98-48e0-b1d4-546c61029c89" />
<img width="491" height="136" alt="image" src="https://github.com/user-attachments/assets/e92522ee-de95-456b-876c-25c7f3bb057a" />
