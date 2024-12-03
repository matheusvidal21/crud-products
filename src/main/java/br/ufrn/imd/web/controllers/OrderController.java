package br.ufrn.imd.web.controllers;

import br.ufrn.imd.web.dtos.OrderDTO;
import br.ufrn.imd.web.dtos.OrderProductDTO;
import br.ufrn.imd.web.entities.ClientEntity;
import br.ufrn.imd.web.entities.OrderEntity;
import br.ufrn.imd.web.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(this.orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.orderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderEntity> create(@RequestBody OrderDTO orderDTO){
        return ResponseEntity.created(null).body(this.orderService.save(orderDTO));
    }

    @PostMapping("/{id}")
    public ResponseEntity<OrderEntity> update(@PathVariable Long id, @RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(this.orderService.update(id, orderDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logic-delete/{id}")
    public ResponseEntity<Void> logicDelete(@PathVariable Long id) {
        this.orderService.logicDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add-product")
    public ResponseEntity<OrderEntity> addProduct(@RequestBody OrderProductDTO orderProductDTO) {
        return ResponseEntity.ok(this.orderService.addProduct(orderProductDTO.orderId(), orderProductDTO.productId()));
    }

    @PostMapping("/remove-product")
    public ResponseEntity<OrderEntity> removeProduct(@RequestBody OrderProductDTO orderProductDTO) {
        return ResponseEntity.ok(this.orderService.removeProduct(orderProductDTO.orderId(), orderProductDTO.productId()));
    }

}
