package elisa.devtest.endtoend.dao;

import elisa.devtest.endtoend.model.ProductDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Product Data Access Object
 */
public class ProductDao {

    /**
     * Return the list of all products
     * @return products
     */
    public List<ProductDto> findProducts() {
        JdbcTemplate template = new JdbcTemplate(DBConnection.getDataSource());
        return template.query("select product_group, product_json from product_dump where product_group = ?",
                                new Object[] {"PHONES"},
                                new ProductDtoMapper());
    }

    /**
     * Utility class
     */
    private class ProductDtoMapper implements RowMapper<ProductDto> {

        /**
         * Map a result set row to a product data transfer object
         * @param resultSet result set
         * @param i row
         * @return product DTO
         * @throws SQLException in case of a database error
         */
        @Override
        public ProductDto mapRow(ResultSet resultSet, int i) throws SQLException {
            return new ProductDto(resultSet.getString(1), resultSet.getString(2));
        }
    }
}
