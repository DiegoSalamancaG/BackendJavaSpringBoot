package Back_endSpringBoot.application.service;

import Back_endSpringBoot.application.dto.CollectionDTO;
import Back_endSpringBoot.application.mapper.CollectionMapper;
import Back_endSpringBoot.domain.model.Collection;
import Back_endSpringBoot.domain.repository.CollectionRepository;
import Back_endSpringBoot.domain.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;
    private final CollectionMapper collectionMapper;


    @Override
    public CollectionDTO createCollection(CollectionDTO collectionDTO) {
        Collection collection = collectionMapper.toCollection(collectionDTO);
        return collectionMapper.toDto(collectionRepository.save(collection));
    }

    @Override
    public List<CollectionDTO> getAllCollections() {
        return collectionRepository.findAll()
                .stream()
                .map(collectionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CollectionDTO getCollectionById(Long id) {
        Collection collection = collectionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Colección no encontrada"));

        return collectionMapper.toDto(collection);
    }

    @Override
    public CollectionDTO updateCollection(Long id, CollectionDTO collectionDTO) {
        Collection existingCol = collectionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Colección no encontrada"));

        return collectionMapper.toDto(collectionRepository.save(existingCol));
    }

    @Override
    public void deleteCollection(Long id) {
        if (!collectionRepository.existsById(id)){
            throw new RuntimeException("Colección no encontrada");
        }
        collectionRepository.deleteById(id);
    }
}
