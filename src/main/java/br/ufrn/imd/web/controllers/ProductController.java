package br.ufrn.imd.web.controllers;

import br.ufrn.imd.web.dtos.ProductDTO;
import br.ufrn.imd.web.entities.ProductEntity;
import br.ufrn.imd.web.enums.Category;
import br.ufrn.imd.web.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAll(){
        List<ProductEntity> products = this.productRepository.findAll();
        products.removeIf(product -> !product.getActive());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getById(@PathVariable Long id){
        Optional<ProductEntity> productOptional = this.productRepository.findById(id);

        if (productOptional.isEmpty() || !productOptional.get().getActive()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productOptional.get());
    }

    @PostMapping
    public ResponseEntity<ProductEntity> create(@RequestBody ProductDTO productDTO){
        ProductEntity productEntity = ProductEntity.builder()
                .name(productDTO.name())
                .brand(productDTO.brand())
                .manufacturingDate(productDTO.manufacturingDate())
                .expirationDate(productDTO.expirationDate())
                .category(Category.fromString(productDTO.category()))
                .build();

        ProductEntity savedProduct = this.productRepository.save(productEntity);
        return ResponseEntity.created(null).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> update(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        Optional<ProductEntity> productOptional = this.productRepository.findById(id);

        if (productOptional.isEmpty() || !productOptional.get().getActive()) {
            return ResponseEntity.notFound().build();
        }

        ProductEntity productEntity = productOptional.get();
        productEntity.setName(productDTO.name() != null ? productDTO.name() : productEntity.getName());
        productEntity.setBrand(productDTO.brand() != null ? productDTO.brand() : productEntity.getBrand());
        productEntity.setManufacturingDate(productDTO.manufacturingDate() != null ? productDTO.manufacturingDate() : productEntity.getManufacturingDate());
        productEntity.setExpirationDate(productDTO.expirationDate() != null ? productDTO.expirationDate() : productEntity.getExpirationDate());
        productEntity.setCategory(productDTO.category() != null ? Category.fromString(productDTO.category()) : productEntity.getCategory());

        ProductEntity updatedProduct = this.productRepository.save(productEntity);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<ProductEntity> productOptional = this.productRepository.findById(id);

        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        this.productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logic-delete/{id}")
    public ResponseEntity<Void> logicDelete(@PathVariable Long id) {
        Optional<ProductEntity> productOptional = this.productRepository.findById(id);

        if (productOptional.isEmpty() || !productOptional.get().getActive()) {
            return ResponseEntity.notFound().build();
        }

        productOptional.get().setActive(false);
        this.productRepository.save(productOptional.get());
        return ResponseEntity.noContent().build();
    }

}
