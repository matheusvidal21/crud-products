package br.ufrn.imd.web.controllers;

import br.ufrn.imd.web.dtos.OrderDTO;
import br.ufrn.imd.web.dtos.OrderProductDTO;
import br.ufrn.imd.web.entities.ClientEntity;
import br.ufrn.imd.web.entities.OrderEntity;
import br.ufrn.imd.web.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(this.orderService.findAll());
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.orderService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<OrderEntity> create(@RequestBody @Valid OrderDTO orderDTO){
        return ResponseEntity.created(null).body(this.orderService.save(orderDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/{id}")
    public ResponseEntity<OrderEntity> update(@PathVariable Long id, @RequestBody @Valid OrderDTO orderDTO){
        return ResponseEntity.ok(this.orderService.update(id, orderDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/logic-delete/{id}")
    public ResponseEntity<Void> logicDelete(@PathVariable Long id) {
        this.orderService.logicDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add-product")
    public ResponseEntity<OrderEntity> addProduct(@RequestBody @Valid OrderProductDTO orderProductDTO) {
        return ResponseEntity.ok(this.orderService.addProduct(orderProductDTO.orderId(), orderProductDTO.productId()));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/remove-product")
    public ResponseEntity<OrderEntity> removeProduct(@RequestBody @Valid OrderProductDTO orderProductDTO) {
        return ResponseEntity.ok(this.orderService.removeProduct(orderProductDTO.orderId(), orderProductDTO.productId()));
    }

}
