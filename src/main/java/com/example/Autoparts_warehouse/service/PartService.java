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
        if (part.getCount() != null && part.getCount() < 0) {
            throw new IllegalArgumentException("Count must not be negative");
        }

        return partRepository.findByArt(part.getArt())
                .map(existing -> {
                    int added = part.getCount() != null ? part.getCount() : 1;
                    int current = existing.getCount() != null ? existing.getCount() : 0;
                    existing.setCount(current + added);
                    return existing;
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
        Brand parsedBrand = parseEnum(Brand.class, brand, "brand");
        return partRepository.findAllByBrand(parsedBrand);
    }

    public List<Part> getAllPartsByArtIgnoreCase(String art) {
        return partRepository.findAllByArtContainingIgnoreCase(art);
    }

    public List<Part> getAllPartsByCategory(String cat) {
        Category parsedCat = parseEnum(Category.class, cat, "category");
        return partRepository.findAllByCategory(parsedCat);
    }

    @Transactional
    public Part updateCount(Long id, int count) {
        Part part = partRepository.findById(id).orElseThrow();
        part.setCount(count);
        return part;
    }

    private <E extends Enum<E>> E parseEnum(Class<E> enumType, String rawValue, String fieldName) {
        if(rawValue == null || rawValue.isBlank()) {
            throw new IllegalArgumentException("Parameter " + fieldName + " must not be blank");
        }

        try {
            return Enum.valueOf(enumType, rawValue.strip().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Unknown " + fieldName + ": " + rawValue, e);
        }
    }
}
