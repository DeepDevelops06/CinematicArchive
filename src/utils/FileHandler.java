package utils;

import models.Film;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "archive_data.txt";

    // Saves the list of films to a text file
    public static void saveFilms(List<Film> films) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Film film : films) {
                // Formatting as: Title,Director,Genre,Year
                writer.write(film.getTitle() + "," + film.getDirector() + "," + 
                             film.getGenre() + "," + film.getReleaseYear());
                writer.newLine();
            }
            System.out.println("Data successfully saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Loads films from the text file into a list
    public static List<Film> loadFilms() {
        List<Film> loadedFilms = new ArrayList<>();
        File file = new File(FILE_NAME);
        
        if (!file.exists()) {
            return loadedFilms; // Return empty list if no save file exists yet
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String title = data[0];
                    String director = data[1];
                    String genre = data[2];
                    int year = Integer.parseInt(data[3]);
                    
                    loadedFilms.add(new Film(title, director, genre, year));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading existing data: " + e.getMessage());
        }
        return loadedFilms;
    }
}