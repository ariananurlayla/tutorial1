package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {

    }
    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("a1b2c3d4e5");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("a1b2c3d4e5");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("1a2b3c4d5e");
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
    void testEdit(){
        Product product = new Product();
        product.setProductId("a1b2c3d4e5");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product editedProduct = productRepository.getProductById("a1b2c3d4e5");
        editedProduct.setProductQuantity(60);
        editedProduct.setProductName("Sampo Cap Bango");
        productRepository.edit(editedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(editedProduct.getProductId(), savedProduct.getProductId());
        assertEquals(editedProduct.getProductName(), savedProduct.getProductName());
        assertEquals(editedProduct.getProductQuantity(), savedProduct.getProductQuantity());
        assertNotEquals(100, savedProduct.getProductQuantity());
        assertNotEquals("Sampo Cap Bambang", savedProduct.getProductName());
    }

    void testEditIdNotExist(){
        Product product = new Product();
        product.setProductId("a1b2c3d4e5");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        assertThrows(IllegalArgumentException.class, () -> productRepository.edit(product), "Product ID invalid");

        productRepository.create(product);
        assertThrows(IllegalArgumentException.class, () -> productRepository.getProductById("1"), "Product ID invalid");
    }

    @Test
    void testDelete(){
        Product product = new Product();
        product.setProductId("a1b2c3d4e5");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        productRepository.delete("a1b2c3d4e5");
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteIdNotExist(){
        Product product = new Product();
        product.setProductId("a1b2c3d4e5");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        productRepository.delete("1234");
        assertTrue(productIterator.hasNext());
    }

    @Test
    void testDeleteThenEdit(){
        Product product1 = new Product();
        product1.setProductId("a1b2c3d4e5");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("1a2b3c4d5e");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        productRepository.delete("a1b2c3d4e5");
        assertThrows(IllegalArgumentException.class, () -> productRepository.getProductById("a1b2c3d4e5"), "Product ID invalid");

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product currProduct = productIterator.next();
        assertFalse(productIterator.hasNext());
        assertEquals(product2.getProductId(), currProduct.getProductId());
        assertEquals(product2.getProductName(), currProduct.getProductName());
        assertEquals(product2.getProductQuantity(), currProduct.getProductQuantity());

        Product editedProduct = productRepository.getProductById("1a2b3c4d5e");
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
