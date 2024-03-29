package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private int totalProduct = 1;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        product.setProductId(String.valueOf(totalProduct));
        totalProduct++;
        productRepository.create(product);
        return product;
    }

    @Override
    public void delete(String productId){
        productRepository.delete(productId);
    }

    @Override
    public List<Product> findAll(){
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product getProductById(String productId){
        return productRepository.getProductById(productId);
    }

    @Override
    public Product edit(Product editedProduct) {
        productRepository.edit(editedProduct);
        return editedProduct;
    }
}