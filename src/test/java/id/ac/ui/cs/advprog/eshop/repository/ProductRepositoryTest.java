package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();

        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(),savedProduct.getProductName());
        assertEquals(product.getProductQuantity(),savedProduct.getProductQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-468e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-98b1-437d-a0bf-d0821dde9896");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testSuccessfulEdit(){
        // Make new product
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        // Change the name & quantity of product
        product = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bango");
        product.setProductQuantity(80);
        productRepository.edit(product);

        // Verify
        product = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(product.getProductId(), "eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(product.getProductName(), "Sampo Cap Bango");
        assertEquals(product.getProductQuantity(), 80);
    }
    @Test
    void testFailedEdit(){
        // Make new product
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertThrows(IllegalArgumentException.class, () -> productRepository.findById("random id"));

        // Change the name & quantity of product
        product = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bango");
        product.setProductQuantity(80);
        productRepository.edit(product);

        assertThrows(IllegalArgumentException.class, () -> productRepository.findById("randomId"));
    }

    @Test
    void testSuccessfulDelete(){
        // Make new product
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);


        // Delete product
        productRepository.deleteProductById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        // Verify
        Iterator<Product> productIterator = productRepository.findAll();
        while (productIterator.hasNext()){
            assertNotEquals(productIterator.next().getProductId(), "eb558e9f-1c39-460e-8860-71af6af63bd6");
        }
    }
    @Test
    void testFailedDelete(){
        // Make new product
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bango");
        product.setProductQuantity(100);
        productRepository.create(product);


        // Delete product with unknown id
        assertThrows(IllegalArgumentException.class, () -> productRepository.deleteProductById("randomId"));

        // Make sure the product still there
        boolean stillThere = false;
        String id = productRepository.findAll().next().getProductId();
        assertEquals(id, "eb558e9f-1c39-460e-8860-71af6af63bd6");
    }

    @Test
    void testDeleteThenEdit(){
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        productRepository.deleteProductById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertThrows(IllegalArgumentException.class, () -> productRepository.getProductById("eb558e9f-1c39-460e-8860-71af6af63bd6"), "Product ID invalid");

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product currProduct = productIterator.next();
        assertFalse(productIterator.hasNext());
        assertEquals(product2.getProductId(), currProduct.getProductId());
        assertEquals(product2.getProductName(), currProduct.getProductName());
        assertEquals(product2.getProductQuantity(), currProduct.getProductQuantity());

        Product editedProduct = productRepository.getProductById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        editedProduct.setProductQuantity(10);
        editedProduct.setProductName("Sampo Cap Ucok");
        productRepository.edit(editedProduct);

        Iterator<Product> productIterator2 = productRepository.findAll();
        currProduct = productIterator2.next();
        assertFalse(productIterator2.hasNext());
        assertEquals(editedProduct.getProductId(), currProduct.getProductId());
        assertEquals(editedProduct.getProductName(), currProduct.getProductName());
        assertEquals(editedProduct.getProductQuantity(), currProduct.getProductQuantity());
        assertNotEquals("Sampo Cap Usep", currProduct.getProductName());
        assertNotEquals(50, currProduct.getProductQuantity());
    }
}