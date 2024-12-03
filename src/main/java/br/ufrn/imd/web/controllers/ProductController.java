package br.ufrn.imd.web.controllers;

import br.ufrn.imd.web.dtos.ProductDTO;
import br.ufrn.imd.web.entities.ProductEntity;
import br.ufrn.imd.web.enums.Category;
import br.ufrn.imd.web.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAll(){
        return ResponseEntity.ok(this.productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductEntity> create(@RequestBody ProductDTO productDTO){
        return ResponseEntity.created(null).body(this.productService.save(productDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> update(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(this.productService.update(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logic-delete/{id}")
    public ResponseEntity<Void> logicDelete(@PathVariable Long id) {
        this.productService.logicDelete(id);
        return ResponseEntity.noContent().build();
    }

}
