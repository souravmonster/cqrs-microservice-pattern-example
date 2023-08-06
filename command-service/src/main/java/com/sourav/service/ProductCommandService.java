package com.sourav.service;

import com.sourav.dto.ProductEvent;
import com.sourav.entity.Product;
import com.sourav.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {

    private static final String CREATE_PRODUCT = "CreateProduct";
    private static final String UPDATE_PRODUCT = "UpdateProduct";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, Object> template;

    public Product createProduct(ProductEvent event) {
        Product product =  productRepository.save(event.getProduct());
        ProductEvent productEvent = new ProductEvent(CREATE_PRODUCT, product);
        template.send("product-event-topic", productEvent);
        return product;
    }

    public Product updateProduct(long id, ProductEvent event) {
        Product existingProduct = productRepository.findById(id).get();
        existingProduct.setName(event.getProduct().getName());
        existingProduct.setDescription(event.getProduct().getDescription());
        existingProduct.setPrice(event.getProduct().getPrice());
        Product product = productRepository.save(existingProduct);
        ProductEvent productEvent = new ProductEvent(UPDATE_PRODUCT, product);
        template.send("product-event-topic", productEvent);
        return product;
    }

}
