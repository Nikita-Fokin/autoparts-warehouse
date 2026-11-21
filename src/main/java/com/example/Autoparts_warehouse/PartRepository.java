package com.example.Autoparts_warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Long> {
    Optional<Part> findByArt(String atr);
    List<Part> findAllByArtContainingIgnoreCase(String art);
}
