package Back_endSpringBoot.application.mapper;

import Back_endSpringBoot.application.dto.CategoryDTO;
import Back_endSpringBoot.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDto(Category cat);
    Category toCategory(CategoryDTO dto);
}
