package Back_endSpringBoot.application.mapper;

import Back_endSpringBoot.application.dto.product.ProductDTO;
import Back_endSpringBoot.application.dto.product.ProductResponseDTO;
import Back_endSpringBoot.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    /**
     * Convierte una entidad Product a un DTO de respuesta (ProductResponseDTO).
     * Ideal para operaciones de lectura (GET /products o GET /products/{id}).
     */
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "collection.id", target = "collectionId")
    @Mapping(source = "collection.name", target = "collectionName")
    ProductResponseDTO toResponseDto(Product product);


    /**
     * Convierte un DTO de entrada a una entidad User.
     * Ideal para la creación de nuevos productos (POST /products).
     * Se ignora el 'id' porque generalmente lo genera la base de datos.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toProduct(ProductDTO dto);

    /**
     * Actualiza una entidad Product existente con los datos de un DTO (ProductDto).
     * Ideal para la actualización de productos (PUT /products/{id}).
     * Se ignora 'id' para evitar su modificación.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateFromProductDto(ProductDTO dto, @MappingTarget Product product);

}
