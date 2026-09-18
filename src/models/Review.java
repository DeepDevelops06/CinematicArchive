package models;

public class Review {
    private int id;
    private int filmId;
    private int userId;
    private double rating; // Expecting a 1.0 to 5.0 scale
    private String comment;

    public Review(int id, int filmId, int userId, double rating, String comment) {
        this.id = id;
        this.filmId = filmId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    public Review(int filmId, int userId, double rating, String comment) {
        this.filmId = filmId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getId() { return id; }
    public int getFilmId() { return filmId; }
    public int getUserId() { return userId; }
    public double getRating() { return rating; }
    public String getComment() { return comment; }

    public void setId(int id) { this.id = id; }
    public void setFilmId(int filmId) { this.filmId = filmId; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setRating(double rating) { this.rating = rating; }
    public void setComment(String comment) { this.comment = comment; }

    @Override
    public String toString() {
        return String.format("Rating: %.1f/5.0 | %s", rating, comment);
    }
}