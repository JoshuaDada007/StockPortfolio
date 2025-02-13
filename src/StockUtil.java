import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StockUtil {
    public static void main(String[] args) throws IOException {
        List<Stock> allStocks = new ArrayList<>();
        Stock apple = createStock("AAPL");
        Stock microsoft = createStock("MSFT");
        Stock bofA  = createStock("BAC");
        Stock NFLX  = createStock("NFLX");
        Stock MSFT  = createStock("MSFT");

        allStocks.add(apple);
        allStocks.add(microsoft);
        allStocks.add(bofA);
        allStocks.add(NFLX);
        allStocks.add(MSFT);

        for(Stock stocks: allStocks){
            System.out.println(stocks.toString());
        }
    }

    public static Stock createStock(String symbol) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new URL("https://financialmodelingprep.com/api/v3/quote/" + symbol + "?apikey=Sc7lTMoT3VVh4tx98nPTWCliWR6O9Gb9"));
        String name = "";
        double price = 0.0;
        long marketCap = 0;
        int volume = 0;
        double dividend = 0.0;


        JsonNode getDividend = mapper.readTree(new URL("https://financialmodelingprep.com/api/v3/historical-price-full/stock_dividend/" + symbol + "?apikey=Sc7lTMoT3VVh4tx98nPTWCliWR6O9Gb9"));
        JsonNode historical = getDividend.get("historical");
        for (JsonNode node : historical) {
           dividend = node.get("dividend").asDouble();
            break;
        }

        for (JsonNode node : root) {
            name = node.get("name").asText();
            price = node.get("price").asDouble();
            marketCap = node.get("marketCap").asLong();
            volume = node.get("volume").asInt();

        }
         return new Stock(symbol,name, price,volume, marketCap, dividend);



    }


}
