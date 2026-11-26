package com.example.Autoparts_warehouse.controller;

import com.example.Autoparts_warehouse.entity.Brand;
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

    @GetMapping("/all-parts")
    public List<Part> getAllParts() {
        return partService.getAllParts();
    }

    @GetMapping("/by-art")
    public Part getPartByArt(@RequestParam String art) {
        return partService.getByArt(art);
    }

    @GetMapping("/by-brand")
    public List<Part> getPartByBrand(@RequestParam String brand) {
        Brand brand1 = Brand.valueOf(brand.toUpperCase());
        try {
            return partService.getAllPartByBrand(brand1);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/by-art-containing")
    public List<Part> getAllPartByArt(@RequestParam String art) {
        return partService.getAllPartsByArtIgnoreCase(art);
    }

    @PostMapping
    public Part addPart(@RequestBody Part part) {
        return partService.addPart(part);
    }

}
