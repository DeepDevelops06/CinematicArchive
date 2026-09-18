package models;

public class Film {
    private int id; // Corresponds to the primary key we will use in JDBC
    private String title;
    private String director;
    private String genre;
    private int releaseYear;
    
    // Constructor
    public Film(int id, String title, String director, String genre, int releaseYear) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    // Constructor without ID (useful for creating new films before DB insertion)
    public Film(String title, String director, String genre, int releaseYear) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDirector() { return director; }
    public String getGenre() { return genre; }
    public int getReleaseYear() { return releaseYear; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDirector(String director) { this.director = director; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    @Override
    public String toString() {
        return String.format("[%d] %s (%d) - Dir: %s | Genre: %s", 
                id, title, releaseYear, director, genre);
    }
}
