package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind(){

        // Creating new products
        Product product1 = new Product();
        product1.setProductId("a1b2c3d4e5");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        when(productRepository.create(any(Product.class))).thenReturn(product1);
        service.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(200);

        when(productRepository.create(any(Product.class))).thenReturn(product2);
        service.create(product2);

        // Verifying create was called
        verify(productRepository,times(1)).create(product1);
        verify(productRepository,times(1)).create(product2);

        // Verifying findAll works properly
        Iterator<Product> productIterator = Arrays.asList(product1, product2).iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> productList = service.findAll();
        assertFalse(productList.isEmpty());
        Product savedProduct = productList.getFirst();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        assertEquals(product1.getProductName(), savedProduct.getProductName());
        assertEquals(product1.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testDelete(){
        service.delete("02");
        verify(productRepository,times(1)).delete("02");
    }

    @Test
    void testGetProductById(){

        // Set up
        Product product1 = new Product();
        product1.setProductId("a1b2c3d4e5");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        when(productRepository.getProductById("a1b2c3d4e5")).thenReturn(product1);
        Product result = service.getProductById("a1b2c3d4e5");

        // Verification
        verify(productRepository,times(1)).getProductById("a1b2c3d4e5");
        assertEquals(product1.getProductName(), result.getProductName());
        assertEquals(product1.getProductQuantity(), result.getProductQuantity());
    }

    @Test
    void testEdit(){

        // Set up
        Product product2 = new Product();
        product2.setProductId("1a2b3c4d5e");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(200);

        when(productRepository.edit(product2)).thenReturn(product2);
        Product result = service.edit(product2);

        // Verification
        verify(productRepository,times(1)).edit(product2);
        assertEquals(product2.getProductName(), result.getProductName());
        assertEquals(product2.getProductQuantity(), result.getProductQuantity());
    }
}