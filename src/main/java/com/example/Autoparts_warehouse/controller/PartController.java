package com.example.Autoparts_warehouse.controller;

import com.example.Autoparts_warehouse.entity.Part;
import com.example.Autoparts_warehouse.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parts")
public class PartController {
    private final PartService partService;

    @GetMapping()
    public List<Part> getAllParts() {
        return partService.getAllParts();
    }

    @GetMapping("/by-art")
    public Part getPartByArt(@RequestParam String art) {
        return partService.getPartByArt(art);
    }

    @GetMapping("/by-brand")
    public List<Part> getAllPartsByBrand(@RequestParam String brand) {
        return partService.getAllPartsByBrand(brand);
    }

    @GetMapping("/by-category")
    public List<Part> getAllPartsByCategory(@RequestParam String category) {
        return partService.getAllPartsByCategory(category);
    }

    @GetMapping("/by-art-containing")
    public List<Part> getAllPartsByArt(@RequestParam String art) {
        return partService.getAllPartsByArtIgnoreCase(art);
    }

    @PostMapping
    public Part addPart(@RequestBody Part part) {
        return partService.addPart(part);
    }
}


