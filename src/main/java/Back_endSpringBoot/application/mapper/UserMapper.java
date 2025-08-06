package Back_endSpringBoot.application.mapper;

import Back_endSpringBoot.application.dto.UserDTO;
import Back_endSpringBoot.application.dto.UserResponseDTO;
import Back_endSpringBoot.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    /**
     * Convierte una entidad User a un DTO de respuesta (UserResponseDTO).
     * Ideal para operaciones de lectura (GET /usuarios o GET /usuarios/{id}).
     */
    @Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    UserResponseDTO toResponseDTO(User user);

    /**
     * Convierte un DTO de entrada (UserDTO) a una entidad User.
     * Ideal para la creación de nuevos usuarios (POST /usuarios).
     * Se ignora el 'id' porque generalmente lo genera la base de datos.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "admin", expression = "java(dto.getAdmin() != null ? dto.getAdmin() : false)")
    @Mapping(target = "active", expression = "java(dto.getActive() != null ? dto.getActive() : true)")
    User toUser(UserDTO dto);

    /**
     * Actualiza una entidad User existente con los datos de un DTO (UserDTO).
     * Ideal para la actualización de usuarios (PUT /usuarios/{id}).
     * Se ignora 'id' para evitar su modificación y 'password' para no sobrescribirlo accidentalmente.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateFromDto(UserDTO dto, @MappingTarget User user);

}
