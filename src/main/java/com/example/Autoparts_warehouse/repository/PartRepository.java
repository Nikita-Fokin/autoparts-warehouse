package com.example.Autoparts_warehouse.repository;

import com.example.Autoparts_warehouse.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Long> {
    Optional<Part> findByArt(String art);
    List<Part> findAllByArtContainingIgnoreCase(String art);
}
