package elisa.devtest.endtoend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import elisa.devtest.endtoend.dao.PricingDao;
import elisa.devtest.endtoend.exception.JsonParseException;
import elisa.devtest.endtoend.model.Price;
import elisa.devtest.endtoend.model.PriceDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PricingService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PricingDao pricingDao = new PricingDao();


    public List<Price> findPrices() {
        List<Price> prices = new ArrayList<>();
        List<PriceDto> priceDtos = pricingDao.findPrices();
        priceDtos.stream().forEach(priceDto ->
                mapAsPrices(priceDto).stream()
                        .forEach(price -> prices.add(price)));
        return prices;
    }

    private List<Price> mapAsPrices(PriceDto priceDto)  {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(priceDto.getPriceJson(), new TypeReference<List<Price>>(){});
        } catch (IOException e) {
            throw new JsonParseException(e);
        }
    }
}
