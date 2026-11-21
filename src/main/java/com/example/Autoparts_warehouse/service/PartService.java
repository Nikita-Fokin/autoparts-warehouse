package com.example.Autoparts_warehouse.service;

import com.example.Autoparts_warehouse.entity.Part;
import com.example.Autoparts_warehouse.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartService {

    final PartRepository partRepository;

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

    public Part getByArt(String art) {
        return partRepository.findByArt(art).orElseThrow();
    }

    @Transactional
    public Part updateCount(Long id, int count) {
        Part part = partRepository.findById(id).orElseThrow();
        part.setCount(count);
        return part;
    }
}
