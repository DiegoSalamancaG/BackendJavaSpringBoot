package Back_endSpringBoot.application.service;

import Back_endSpringBoot.application.dto.CategoryDTO;
import Back_endSpringBoot.application.mapper.CategoryMapper;
import Back_endSpringBoot.domain.model.Category;
import Back_endSpringBoot.domain.repository.CategoryRepository;
import Back_endSpringBoot.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toCategory(categoryDTO);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category existingCat = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        existingCat.setName(categoryDTO.getName());
        existingCat.setDescription(categoryDTO.getDescription());

        return categoryMapper.toDto(categoryRepository.save(existingCat));
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada");
        }
        categoryRepository.deleteById(id);
    }

}
