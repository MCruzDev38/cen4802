package com.michelle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlantDatabase {

    private static final String DATABASE_URL = "jdbc:sqlite:plant_care.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    public static void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS plants (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    type TEXT NOT NULL,
                    watering_frequency TEXT NOT NULL,
                    sunlight TEXT NOT NULL,
                    notes TEXT
                );
                """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Database table is ready.");

        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public static void addPlant(Plant plant) {
        String sql = """
                INSERT INTO plants (name, type, watering_frequency, sunlight, notes)
                VALUES (?, ?, ?, ?, ?);
                """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, plant.getName());
            pstmt.setString(2, plant.getType());
            pstmt.setString(3, plant.getWateringFrequency());
            pstmt.setString(4, plant.getSunlight());
            pstmt.setString(5, plant.getNotes());

            pstmt.executeUpdate();
            System.out.println("Plant added successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding plant: " + e.getMessage());
        }
    }

    public static void viewPlants() {
        String sql = "SELECT * FROM plants;";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Plant Records ---");

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Type: " + rs.getString("type"));
                System.out.println("Watering Frequency: " + rs.getString("watering_frequency"));
                System.out.println("Sunlight: " + rs.getString("sunlight"));
                System.out.println("Notes: " + rs.getString("notes"));
                System.out.println("----------------------");
            }

            if (!found) {
                System.out.println("No plant records found.");
            }

        } catch (SQLException e) {
            System.out.println("Error viewing plants: " + e.getMessage());
        }
    }

    public static List<Plant> getAllPlants() {

        List<Plant> plants = new ArrayList<>();

        String sql = "SELECT * FROM plants;";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Plant plant = new Plant(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("watering_frequency"),
                        rs.getString("sunlight"),
                        rs.getString("notes")
                );

                plants.add(plant);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving plants: " + e.getMessage());
        }

        return plants;
    }

    public static void updatePlant(int id, String newWateringFrequency, String newNotes) {
        String sql = """
                UPDATE plants
                SET watering_frequency = ?, notes = ?
                WHERE id = ?;
                """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newWateringFrequency);
            pstmt.setString(2, newNotes);
            pstmt.setInt(3, id);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Plant updated successfully.");
            } else {
                System.out.println("No plant found with that ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating plant: " + e.getMessage());
        }
    }

    public static void deletePlant(int id) {
        String sql = "DELETE FROM plants WHERE id = ?;";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Plant deleted successfully.");
            } else {
                System.out.println("No plant found with that ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting plant: " + e.getMessage());
        }
    }
}