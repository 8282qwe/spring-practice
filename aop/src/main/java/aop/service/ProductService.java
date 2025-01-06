package aop.service;

import aop.domain.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public Product find(String name) throws RuntimeException {
        if (name.isBlank()) {
            throw new RuntimeException("empty name");
        }

        System.out.println("finding ...");
        return new Product(name);
    }
}
