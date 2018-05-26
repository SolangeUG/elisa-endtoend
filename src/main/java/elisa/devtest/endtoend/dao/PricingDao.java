package elisa.devtest.endtoend.dao;

import elisa.devtest.endtoend.model.PriceDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PricingDao {
    public List<PriceDto> findPrices() {
        JdbcTemplate template = new JdbcTemplate(DBConnection.getDataSource());
        return template.query("select pricing_group, price_json from pricing_dump where pricing_group = ?", new Object[] {"PHONES"}, new PriceDtoMapper());
    }

    private class PriceDtoMapper implements RowMapper<PriceDto> {
        @Override
        public PriceDto mapRow(ResultSet resultSet, int i) throws SQLException {
            return new PriceDto(resultSet.getString(1), resultSet.getString(2));
        }
    }
}
