package br.ufrn.imd.web.services;

import br.ufrn.imd.web.dtos.OrderDTO;
import br.ufrn.imd.web.entities.ClientEntity;
import br.ufrn.imd.web.entities.OrderEntity;
import br.ufrn.imd.web.entities.ProductEntity;
import br.ufrn.imd.web.exceptions.InactiveEntityException;
import br.ufrn.imd.web.repositories.ClientRepository;
import br.ufrn.imd.web.repositories.OrderRepository;
import br.ufrn.imd.web.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    public List<OrderEntity> findAll() {
        List<OrderEntity> orders = this.orderRepository.findAll();
        orders.removeIf(order -> !order.getActive());
        return orders;
    }

    public OrderEntity findById(Long id) {
        Optional<OrderEntity> order = this.orderRepository.findById(id);

        if (order.isEmpty()) {
            throw new EntityNotFoundException("Order not found");
        }

        if (!order.get().getActive()) {
            throw new EntityNotFoundException("Order not found");
        }

        return order.get();
    }

    public OrderEntity save(OrderDTO orderDTO) {
        ClientEntity client = this.clientService.findById(orderDTO.clientId());
        List<ProductEntity> products = this.productService.findAllById(orderDTO.productsIds());
        OrderEntity orderEntity = OrderEntity.builder()
                .codigo(orderDTO.codigo())
                .client(client)
                .products(products)
                .active(true)
                .build();
        return this.orderRepository.save(orderEntity);
    }

    public OrderEntity update(Long id, OrderDTO orderDTO) {
        OrderEntity order = this.findById(id);
        ClientEntity client = this.clientService.findById(orderDTO.clientId());
        List<ProductEntity> products = this.productService.findAllById(orderDTO.productsIds());

        order.setCodigo(orderDTO.codigo() != null ? orderDTO.codigo() : order.getCodigo());
        order.setProducts(products != null ? products : order.getProducts());
        order.setClient(client != null ? client : order.getClient());

        return this.orderRepository.save(order);
    }

    public void deleteById(Long id) {
        OrderEntity order = this.findById(id);
        this.orderRepository.delete(order);
    }

    public void logicDelete(Long id) {
        OrderEntity order = this.findById(id);
        order.setActive(false);
        this.orderRepository.save(order);
    }

    public OrderEntity addProduct(Long orderId, Long productId) {
        OrderEntity order = this.findById(orderId);
        ProductEntity product = this.productService.findById(productId);
        order.addProduct(product);
        return this.orderRepository.save(order);
    }

    public OrderEntity removeProduct(Long orderId, Long productId) {
        OrderEntity order = this.findById(orderId);
        ProductEntity product = this.productService.findById(productId);
        order.removeProduct(product);
        return this.orderRepository.save(order);
    }
}
