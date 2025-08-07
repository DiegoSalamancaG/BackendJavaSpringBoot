    package Back_endSpringBoot.application.service;

    import Back_endSpringBoot.application.dto.product.ProductDTO;
    import Back_endSpringBoot.application.dto.product.ProductResponseDTO;
    import Back_endSpringBoot.application.mapper.ProductMapper;
    import Back_endSpringBoot.domain.model.Product;
    import Back_endSpringBoot.domain.repository.CategoryRepository;
    import Back_endSpringBoot.domain.repository.CollectionRepository;
    import Back_endSpringBoot.domain.repository.ProductRepository;
    import Back_endSpringBoot.domain.service.ProductService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;
    import java.util.stream.Collectors;

    @Service
    @RequiredArgsConstructor
    public class ProductServiceImpl implements ProductService {

        private final ProductRepository productRepository;
        private final ProductMapper productMapper;
        private final CategoryRepository categoryRepository;
        private final CollectionRepository collectionRepository;

        //Métodos
        @Override
        public List<ProductResponseDTO> getAllProducts() {
            return productRepository.findByActiveTrue()
                    .stream()
                    .map(productMapper::toResponseDto)
                    .collect(Collectors.toList());
        }

        @Override
        public Optional<ProductResponseDTO> getProductById(Long id) {
            return productRepository.findById(id)
                    .map(productMapper::toResponseDto);
        }

        @Override
        public Optional<ProductResponseDTO> createProduct(ProductDTO dto) {
            Product product = productMapper.toProduct(dto);

            categoryRepository.findById(dto.getCategoryId())
                    .ifPresent(product::setCategory);
            collectionRepository.findById(dto.getCollectionId())
                    .ifPresent(product::setCollection);

            Product productCreated = productRepository.save(product);
            return Optional.of(productMapper.toResponseDto(productCreated));
        }

        @Override
        public Optional<ProductResponseDTO> updateProduct(Long id, ProductDTO dto) {
            return productRepository.findById(id).map(product -> {
                productMapper.updateFromProductDto(dto, product);

                categoryRepository.findById(dto.getCategoryId())
                        .ifPresent(product::setCategory);
                collectionRepository.findById(dto.getCollectionId())
                        .ifPresent(product::setCollection);

                Product productUpdated = productRepository.save(product);
                return productMapper.toResponseDto(productUpdated);
            });
        }

        @Override
        public Boolean deleteProduct(Long id) {
            return productRepository.findById(id).map(product -> {
                product.setActive(false);
                productRepository.save(product);
                return true;
            }).orElse(false);
        }
    }
