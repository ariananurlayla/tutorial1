package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements  ProductService{

    private long productIdCounter = 1;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product){
        product.setProductId(String.valueOf(productIdCounter++));
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll(){
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining((allProduct::add));
        return allProduct;
    }

    @Override
    public void edit(Product product){
        productRepository.edit(product);
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteProductById(String id){
        productRepository.deleteProductById(id);
    }

    @Override
    public Product getProductById(String productId){
        return productRepository.getProductById(productId);
    }
}