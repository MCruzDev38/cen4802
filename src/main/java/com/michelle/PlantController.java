package com.michelle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlantController {

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("plants", PlantDatabase.getAllPlants());

        return "index";
    }

    @PostMapping("/add")
    public String addPlant(
            @RequestParam String name,
            @RequestParam String type,
            @RequestParam String wateringFrequency,
            @RequestParam String sunlight,
            @RequestParam String notes,
            @RequestParam String dateAdded) {

        Plant plant = new Plant(
                0,
                name,
                type,
                wateringFrequency,
                sunlight,
                notes,
                dateAdded
        );

        PlantDatabase.addPlant(plant);

        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deletePlant(@RequestParam int id) {

        PlantDatabase.deletePlant(id);

        return "redirect:/";
    }

    @PostMapping("/update")
    public String updatePlant(
            @RequestParam int id,
            @RequestParam String wateringFrequency,
            @RequestParam String notes) {

        PlantDatabase.updatePlant(id, wateringFrequency, notes);

        return "redirect:/";
    }
}