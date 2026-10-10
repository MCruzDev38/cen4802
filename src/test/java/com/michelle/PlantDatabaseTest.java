package com.michelle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlantDatabaseTest {

    @BeforeEach
    void setUp() throws Exception {
        PlantDatabase.setDatabaseUrl("jdbc:sqlite:test_plant_care.db");
        PlantDatabase.createTable();

        try (Connection conn = PlantDatabase.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM plants;");
        }
    }

    @Test
    void testAddPlant() {
        Plant plant = new Plant(
                "Snake Plant",
                "Succulent",
                "Every 14 days",
                "Indirect light",
                "Living Room",
                "Low maintenance",
                "2026-09-13"
        );

        PlantDatabase.addPlant(plant);

        List<Plant> plants = PlantDatabase.getAllPlants();

        assertEquals(1, plants.size());
        assertEquals("Aloe", plants.get(0).getName());
        assertEquals("Living Room", plants.get(0).getLocation());
        assertEquals("Succulent", plants.get(0).getType());
    }

    @Test
    void testUpdatePlant() {
        Plant plant = new Plant(
                "Pothos",
                "Tropical",
                "Every 7 days",
                "Bright indirect light",
                "Kitchen",
                "Original notes",
                "2026-09-13"
        );

        PlantDatabase.addPlant(plant);

        Plant savedPlant = PlantDatabase.getAllPlants().get(0);

        PlantDatabase.updatePlant(
                savedPlant.getId(),
                "Every 5 days",
                "Living Room",
                "Updated notes"
        );

        Plant updatedPlant = PlantDatabase.getAllPlants().get(0);

        assertEquals("Every 5 days", updatedPlant.getWateringFrequency());
        assertEquals("Updated notes", updatedPlant.getNotes());
        assertEquals("Living Room", updatedPlant.getLocation());
    }

    @Test
    void testDeletePlant() {
        Plant plant = new Plant(
                "ZZ Plant",
                "Tropical",
                "Every 14 days",
                "Low light",
                "Bedroom",
                "Easy care",
                "2026-09-13"
        );

        PlantDatabase.addPlant(plant);

        Plant savedPlant = PlantDatabase.getAllPlants().get(0);

        PlantDatabase.deletePlant(savedPlant.getId());

        assertTrue(PlantDatabase.getAllPlants().isEmpty());
    }

    @Test
    void testMultiplePlants() {
        Plant plant1 = new Plant(
                "Pothos",
                "Tropical",
                "Every 7 days",
                "Indirect light",
                "Living Room",
                "Fast growing",
                "2026-09-13"
        );

        Plant plant2 = new Plant(
                "Aloe",
                "Succulent",
                "Every 14 days",
                "Bright light",
                "Patio",
                "Allow soil to dry",
                "2026-09-13"
        );

        PlantDatabase.addPlant(plant1);
        PlantDatabase.addPlant(plant2);

        List<Plant> plants = PlantDatabase.getAllPlants();

        assertEquals(2, plants.size());
    }
}
