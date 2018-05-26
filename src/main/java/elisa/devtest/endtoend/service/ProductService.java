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

public class ProductService {
    private final ProductDao productDao = new ProductDao();
    final ObjectMapper objectMapper = new ObjectMapper();

    public List<Product> findProducts() {
        List<Product> products = new ArrayList<>();
        List<ProductDto> productDtos = productDao.findProducts();
        productDtos.stream().forEach(productDto ->
                mapAsProducts(productDto).stream()
                        .forEach(product -> products.add(product)));
        return products;
    }

    private List<Product> mapAsProducts(ProductDto productDto)  {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(productDto.getProductJson(), new TypeReference<List<Product>>(){});
        } catch (IOException e) {
            throw new JsonParseException(e);
        }
    }
}
