import models.Film;
import models.Review;
import services.ArchiveManager;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArchiveManager archive = new ArchiveManager();
        boolean running = true;
        db.DatabaseManager.initializeDatabase();

        System.out.println("Welcome to the Cinematic Archive & Review Engine");

        while (running) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Add a new Film");
            System.out.println("2. View all Films");
            System.out.println("3. Filter Films by Genre");
            System.out.println("4. Leave a Review");
            System.out.println("5. View Reviews for a Film");
            System.out.println("6. Exit");
            System.out.print("Select an option (1-6): ");

            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine(); 
                continue; 
            }

            switch (choice) {
                case 1:
                    addFilmInteractive(scanner, archive);
                    break;
                case 2:
                    archive.displayAllFilms();
                    break;
                case 3:
                    System.out.print("Enter genre to filter by: ");
                    String genre = scanner.nextLine();
                    archive.filterByGenre(genre);
                    break;
                case 4:
                    leaveReviewInteractive(scanner, archive);
                    break;
                case 5:
                    viewReviewsInteractive(scanner, archive);
                    break;
                case 6:
                    System.out.println("Exiting Archive. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select a number between 1 and 6.");
            }
        }
        scanner.close();
    }

    private static void addFilmInteractive(Scanner scanner, ArchiveManager archive) {
        System.out.println("\n--- Add a New Film ---");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        
        System.out.print("Director: ");
        String director = scanner.nextLine();
        
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        
        int year = 0;
        while (true) {
            System.out.print("Release Year (e.g., 2024): ");
            try {
                year = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Release year must be a 4-digit number.");
                scanner.nextLine();
            }
        }

        Film newFilm = new Film(title, director, genre, year);
        archive.addFilm(newFilm);
    }

    private static void leaveReviewInteractive(Scanner scanner, ArchiveManager archive) {
        System.out.println("\n--- Leave a Review ---");
        archive.displayAllFilms();
        System.out.print("Enter the ID number (inside the brackets [ ]) of the film to review: ");
        
        try {
            int filmId = scanner.nextInt();
            scanner.nextLine();
            
            Film selectedFilm = archive.getFilmById(filmId);
            if (selectedFilm == null) {
                System.out.println("Error: Film ID not found in database.");
                return;
            }

            System.out.print("Enter your rating (1.0 to 5.0): ");
            double rating = scanner.nextDouble();
            scanner.nextLine();
            
            System.out.print("Enter your text review: ");
            String comment = scanner.nextLine();

            Review newReview = new Review(filmId, 1, rating, comment);
            archive.addReview(newReview);
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format.");
            scanner.nextLine();
        }
    }
    private static void viewReviewsInteractive(Scanner scanner, ArchiveManager archive) {
        archive.displayAllFilms();
        System.out.print("Enter the ID number of the film to view reviews: ");
        try {
            int filmIndex = scanner.nextInt();
            scanner.nextLine();
            archive.displayReviewsForFilm(filmIndex);
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter a valid number.");
            scanner.nextLine();
        }
    }
}