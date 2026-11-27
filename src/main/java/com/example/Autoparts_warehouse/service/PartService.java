package com.example.Autoparts_warehouse.service;

import com.example.Autoparts_warehouse.entity.Brand;
import com.example.Autoparts_warehouse.entity.Category;
import com.example.Autoparts_warehouse.entity.Part;
import com.example.Autoparts_warehouse.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartService {

    private final PartRepository partRepository;

    @Transactional
    public Part addPart(Part part) {
        return partRepository.findByArt(part.getArt())
                .map(existing -> {
                    int added = part.getCount() != null ? part.getCount() : 1;
                    int current = existing.getCount() != null ? existing.getCount() : 0;
                    existing.setCount(current + added);
                    return partRepository.save(existing);
                })
                .orElseGet(() -> {
                    if (part.getCount() == null) {
                        part.setCount(1);
                    }
                    return partRepository.save(part);
                });
    }

    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    public Part getPartByArt(String art) {
        return partRepository.findByArt(art).orElseThrow();
    }

    public List<Part> getAllPartsByBrand(String brand) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand must not be blank");
        }

        try {
            Brand parsedBrand = Brand.valueOf(brand.strip().toUpperCase());
            return partRepository.findAllByBrand(parsedBrand);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown brand: " + brand, e);
        }
    }

    public List<Part> getAllPartsByArtIgnoreCase(String art) {
        return partRepository.findAllByArtContainingIgnoreCase(art);
    }

    public List<Part> getAllPartsByCategory(String cat) {
        if (cat == null || cat.isBlank()) {
            throw new IllegalArgumentException("Category must not be blank");
        }

        try {
            Category category = Category.valueOf(cat.strip().toUpperCase());
            return partRepository.findAllByCategory(category);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown category: " + cat, e);
        }
    }

    @Transactional
    public Part updateCount(Long id, int count) {
        Part part = partRepository.findById(id).orElseThrow();
        part.setCount(count);
        return part;
    }
}
