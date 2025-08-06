package Back_endSpringBoot.application.mapper;

import Back_endSpringBoot.application.dto.CollectionDTO;
import Back_endSpringBoot.domain.model.Collection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface CollectionMapper {

    CollectionDTO toDto(Collection col);
    Collection toCollection(CollectionDTO dto);
}
