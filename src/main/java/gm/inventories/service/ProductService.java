package gm.inventories.service;

import gm.inventories.model.Product;
import gm.inventories.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;
    // CONSTRUCTOR
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product searchProductById(Integer idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(Integer idProduct) {
        productRepository.deleteById(idProduct);
    }
}
