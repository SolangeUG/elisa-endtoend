package elisa.devtest.endtoend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import elisa.devtest.endtoend.dao.ProductDao;
import elisa.devtest.endtoend.exception.JsonParseException;
import elisa.devtest.endtoend.model.Product;
import elisa.devtest.endtoend.model.ProductDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for processing price related operations
 */
public class ProductService {
    private final ProductDao productDao = new ProductDao();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Return all products
     * @return list of products
     */
    public List<Product> findProducts() {
        List<Product> products = new ArrayList<>();
        List<ProductDto> productDtos = productDao.findProducts();
        productDtos.forEach(productDto -> products.addAll(mapAsProducts(productDto)));
        return products;
    }

    /**
     * Map a product data transfer object to a list of prices
     * @param productDto product data transfer object
     * @return corresponding list of products
     */
    private List<Product> mapAsProducts(ProductDto productDto)  {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(productDto.getProductJson(), new TypeReference<List<Product>>(){});
        } catch (IOException e) {
            throw new JsonParseException(e);
        }
    }
}
