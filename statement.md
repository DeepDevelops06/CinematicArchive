# Project Statement

## Problem Statement
Managing and retrieving localized cinematic metadata and user reviews traditionally requires heavy web-based architectures and complex database server configurations. There is a need for a lightweight, terminal-executable archiving solution that securely persists relational data, handles malformed inputs gracefully, and ingests bulk metadata asynchronously without freezing the primary interface.

## Scope of the Project
The Cinematic Archive & Review Engine is a Java-based command-line interface (CLI) application. Its scope includes managing core film domain models, establishing relational foreign-key links to user ratings, and executing embedded SQLite database transactions. It also implements multi-threaded asynchronous workers to simulate high-latency batch data processing.

## Target Users
*   **Local Archivists & Film Enthusiasts:** Individuals looking for a fast, keyboard-driven tool to catalog movies and record personal ratings.
*   **Data Entry Personnel:** Users who require uninterrupted terminal access while the system processes large bulk metadata imports in the background.

## High-Level Features
1.  **Catalog Management:** Interactive CRUD operations for film records with dynamic genre filtering.
2.  **Relational Review Tracking:** Secure storage of numeric ratings and text critiques mapped to specific films.
3.  **Concurrency / Asynchronous Ingestion:** Background batch processing using Java Threads to prevent UI blocking.
4.  **Robust Exception Handling:** Continuous terminal loop protection against `InputMismatchException` and malformed data types.