# Cinematic Archive & Review Engine

A modular Java CLI application managing cinematic records, user ratings, and asynchronous metadata ingestion using an embedded SQLite database.

## Features
- **Object-Oriented Architecture:** Domain models for Films, Users, and Reviews with full encapsulation.
- **Persistent Storage:** SQLite integration with JDBC (PreparedStatement, ResultSet) for ACID-compliant transactions.
- **Multithreading & Concurrency:** Background batch ingestion utilizing Java Thread and Runnable to process records asynchronously without blocking the terminal interface.
- **Input Validation & Exception Handling:** Terminal validation preventing crashes from malformed user data.

## Project Structure
```text
CinematicArchive/
├── lib/
│   └── sqlite-jdbc.jar
├── src/
│   ├── db/
│   │   └── DatabaseManager.java
│   ├── models/
│   │   ├── Film.java
│   │   ├── Review.java
│   │   └── User.java
│   ├── services/
│   │   ├── ArchiveManager.java
│   │   └── BatchImportService.java
│   ├── utils/
│   │   └── FileHandler.java
│   └── Main.java
├── .gitignore
└── README.md
```

## Setup & Execution

### 1. Prerequisites
- Java Development Kit (JDK 17 or higher)
- Terminal / Command Line access

### 2. Compilation
Compile all packages into the `out` directory from the root folder:
```bash
javac -cp "lib/*" src/models/*.java src/services/*.java src/db/*.java src/utils/*.java src/Main.java -d out
```

### 3. Execution
Launch the CLI application:
```bash
java -cp "out:lib/*" Main
```