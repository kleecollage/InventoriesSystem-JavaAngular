package gm.inventories.controller;

import gm.inventories.exception.ResourceNotFoundException;
import gm.inventories.model.Product;
import gm.inventories.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// http://localhost:8080/inventory-app
@RequestMapping("inventory-app")
@CrossOrigin(value = "http://localhost:4200")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    //CONSTRUCTOR
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET [host/inventory-app/products]
    @GetMapping("/products")
    public List<Product> getProducts() {
        List<Product> products = productService.listProducts();
        logger.info("Products list: ");
        // DEBUG // products.forEach(product -> logger.info(product.toString()));
        return products;
    }

    // POST [host/inventory-app/products]
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        logger.info("Product to add: " + product);
        return this.productService.saveProduct(product);
    }

    // POST [host/inventory-app/products/?id]
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = this.productService.searchProductById(id);
        if (product != null)
            return ResponseEntity.ok(product);
        else
            throw new ResourceNotFoundException("Product with id " + id + " not found");
    }

    // PUT [host/inventory-app/products/?id]
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productRequest) {
        Product product = this.productService.searchProductById(id);
        if (product == null)
            throw new ResourceNotFoundException("Product with id " + id + " not found");
        product.setDescriptionProduct(productRequest.getDescriptionProduct());
        product.setPriceProduct(productRequest.getPriceProduct());
        product.setStock(productRequest.getStock());
        this.productService.saveProduct(product);
        return ResponseEntity.ok(product);
    }

    // DELETE [host/inventory-app/products/?id]
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable int id) {
        Product product = this.productService.searchProductById(id);
        if (product == null)
            throw new ResourceNotFoundException("Product with id " + id + " not found");
        this.productService.deleteProductById(product.getIdProduct());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}









