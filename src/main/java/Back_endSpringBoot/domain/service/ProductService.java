package Back_endSpringBoot.domain.service;

import Back_endSpringBoot.application.dto.product.ProductDTO;
import Back_endSpringBoot.application.dto.product.ProductResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductResponseDTO> getAllProducts();
    Optional<ProductResponseDTO> getProductById(Long id);
    Optional<ProductResponseDTO> createProduct(ProductDTO dto);
    Optional<ProductResponseDTO> updateProduct(Long id,ProductDTO dto);
    Boolean deleteProduct(Long id);
}
