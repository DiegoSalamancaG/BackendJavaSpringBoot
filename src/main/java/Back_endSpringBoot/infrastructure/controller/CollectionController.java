package Back_endSpringBoot.infrastructure.controller;

import Back_endSpringBoot.application.dto.CollectionDTO;
import Back_endSpringBoot.domain.service.CollectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collections")
public class CollectionController {

    private final CollectionService collectionService;

    @PostMapping
    public ResponseEntity<CollectionDTO> createCategory(
            @RequestBody CollectionDTO categoryDTO){
        CollectionDTO cat = collectionService.createCollection(categoryDTO);
        return ResponseEntity.status(201).body(cat);
    }

    @GetMapping
    public ResponseEntity<List<CollectionDTO>> getAllCategories() {
        return ResponseEntity.ok(collectionService.getAllCollections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionDTO> getCategoryById(
            @PathVariable Long id) {
        return ResponseEntity.ok(collectionService.getCollectionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionDTO> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CollectionDTO categoryDTO) {
        return ResponseEntity.ok(collectionService.updateCollection(id, categoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Long id) {
        collectionService.deleteCollection(id);
        return ResponseEntity.noContent().build();
    }
}
