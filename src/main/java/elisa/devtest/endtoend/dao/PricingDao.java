package elisa.devtest.endtoend.dao;

import elisa.devtest.endtoend.model.PriceDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Price Data Access Object
 */
public class PricingDao {

    /**
     * Return a list of all prices
     * @return prices
     */
    public List<PriceDto> findPrices() {
        JdbcTemplate template = new JdbcTemplate(DBConnection.getDataSource());
        return template.query("select pricing_group, price_json from pricing_dump where pricing_group = ?",
                                new Object[] {"PHONES"},
                                new PriceDtoMapper());
    }

    /**
     * Utility class
     */
    private class PriceDtoMapper implements RowMapper<PriceDto> {

        /**
         * Map a resultset row to a price data transfer object
         * @param resultSet resultset
         * @param i row
         * @return price DTO
         * @throws SQLException in case of a database error
         */
        @Override
        public PriceDto mapRow(ResultSet resultSet, int i) throws SQLException {
            return new PriceDto(resultSet.getString(1), resultSet.getString(2));
        }
    }
}
