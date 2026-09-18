package services;

import db.DatabaseManager;
import models.Film;
import models.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArchiveManager {

    public void addFilm(Film film) {
        String sql = "INSERT INTO films(title, director, genre, release_year) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, film.getTitle());
            pstmt.setString(2, film.getDirector());
            pstmt.setString(3, film.getGenre());
            pstmt.setInt(4, film.getReleaseYear());
            pstmt.executeUpdate();
            System.out.println("Success: Film added to SQLite database.");
        } catch (SQLException e) {
            System.out.println("Error adding film: " + e.getMessage());
        }
    }

    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT * FROM films";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Film film = new Film(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("director"),
                    rs.getString("genre"),
                    rs.getInt("release_year")
                );
                films.add(film);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching films: " + e.getMessage());
        }
        return films;
    }

    public void displayAllFilms() {
        List<Film> films = getAllFilms();
        if (films.isEmpty()) {
            System.out.println("The archive is currently empty.");
            return;
        }
        System.out.println("\n--- Cinematic Archive (Database Records) ---");
        for (Film film : films) {
            System.out.println(film.toString());
        }
    }

    public void filterByGenre(String targetGenre) {
        String sql = "SELECT * FROM films WHERE LOWER(genre) = LOWER(?)";
        System.out.println("\n--- Filtering by Genre: " + targetGenre + " ---");

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, targetGenre);
            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            while (rs.next()) {
                Film film = new Film(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("director"),
                    rs.getString("genre"),
                    rs.getInt("release_year")
                );
                System.out.println(film.toString());
                found = true;
            }
            if (!found) {
                System.out.println("No films found under genre: " + targetGenre);
            }
        } catch (SQLException e) {
            System.out.println("Error filtering films: " + e.getMessage());
        }
    }

    public Film getFilmById(int id) {
        String sql = "SELECT * FROM films WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Film(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("director"),
                    rs.getString("genre"),
                    rs.getInt("release_year")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching film by ID: " + e.getMessage());
        }
        return null;
    }

    public void addReview(Review review) {
        String sql = "INSERT INTO reviews(film_id, user_id, rating, comment) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, review.getFilmId());
            pstmt.setInt(2, review.getUserId());
            pstmt.setDouble(3, review.getRating());
            pstmt.setString(4, review.getComment());
            pstmt.executeUpdate();
            System.out.println("Success: Review registered in database.");
        } catch (SQLException e) {
            System.out.println("Error adding review: " + e.getMessage());
        }
    }

    public void displayReviewsForFilm(int filmId) {
        String sql = "SELECT * FROM reviews WHERE film_id = ?";
        System.out.println("\n--- Film Reviews (Database Records) ---");

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, filmId);
            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            while (rs.next()) {
                Review r = new Review(
                    rs.getInt("id"),
                    rs.getInt("film_id"),
                    rs.getInt("user_id"),
                    rs.getDouble("rating"),
                    rs.getString("comment")
                );
                System.out.println(r.toString());
                found = true;
            }
            if (!found) {
                System.out.println("No reviews found for film ID " + filmId + ".");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching reviews: " + e.getMessage());
        }
    }
}