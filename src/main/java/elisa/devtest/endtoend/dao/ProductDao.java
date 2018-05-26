package elisa.devtest.endtoend.dao;

import elisa.devtest.endtoend.model.ProductDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDao {
    public List<ProductDto> findProducts() {
        JdbcTemplate template = new JdbcTemplate(DBConnection.getDataSource());
        return template.query("select product_group, product_json from product_dump where product_group = ?", new Object[] {"PHONES"}, new ProductDtoMapper());
    }

    private class ProductDtoMapper implements RowMapper<ProductDto> {
        @Override
        public ProductDto mapRow(ResultSet resultSet, int i) throws SQLException {
            return new ProductDto(resultSet.getString(1), resultSet.getString(2));
        }
    }
}
