package br.ufrn.imd.web.services;

import br.ufrn.imd.web.dtos.ProductDTO;
import br.ufrn.imd.web.entities.ProductEntity;
import br.ufrn.imd.web.enums.Category;
import br.ufrn.imd.web.exceptions.InactiveEntityException;
import br.ufrn.imd.web.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> findAll() {
        List<ProductEntity> products = this.productRepository.findAll();
        products.removeIf(product -> !product.getActive());
        return products;
    }

    public List<ProductEntity> findAllById(List<Long> ids) {
        List<ProductEntity> products = this.productRepository.findAllById(ids);
        products.removeIf(product -> !product.getActive());

        if (products.isEmpty()) {
            throw new EntityNotFoundException("Products not found");
        }
        return products;
    }

    public ProductEntity findById(Long id) {
        Optional<ProductEntity> product = this.productRepository.findById(id);

        if (product.isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }
        if (!product.get().getActive()) {
            throw new InactiveEntityException("Product is inactive");
        }

        return product.get();
    }

    public ProductEntity save(ProductDTO productDTO) {
        ProductEntity productEntity = ProductEntity.builder()
                .name(productDTO.name())
                .brand(productDTO.brand())
                .manufacturingDate(productDTO.manufacturingDate())
                .expirationDate(productDTO.expirationDate())
                .category(Category.fromString(productDTO.category()))
                .active(true)
                .build();

        return this.productRepository.save(productEntity);
    }

    public ProductEntity update(Long id, ProductDTO productDTO) {
        ProductEntity product = this.findById(id);
        product.setName(productDTO.name() != null ? productDTO.name() : product.getName());
        product.setBrand(productDTO.brand() != null ? productDTO.brand() : product.getBrand());
        product.setManufacturingDate(productDTO.manufacturingDate() != null ? productDTO.manufacturingDate() : product.getManufacturingDate());
        product.setExpirationDate(productDTO.expirationDate() != null ? productDTO.expirationDate() : product.getExpirationDate());
        product.setCategory(productDTO.category() != null ? Category.fromString(productDTO.category()) : product.getCategory());

        return this.productRepository.save(product);
    }

    public void deleteById(Long id) {
        ProductEntity product = this.findById(id);
        this.productRepository.deleteById(id);
    }

    public void logicDelete(Long id) {
        ProductEntity product = this.findById(id);
        product.setActive(false);
        this.productRepository.save(product);
    }


}
