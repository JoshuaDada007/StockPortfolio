import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public class Investment {
    StockPortfolio stockPortfolio = new StockPortfolio();
    TreeMap<String, Double> investment;

    public Investment() {
        investment = new TreeMap<>();
    }

    public JsonNode loadPricesFromWeb(String symbol) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new URL("https://financialmodelingprep.com/api/v3/quote/" + symbol + "?apikey=Sc7lTMoT3VVh4tx98nPTWCliWR6O9Gb9"));
        return node;

    }


    public void loadPortfolioFromWeb(String url) throws IOException {
        String name = "";
        double price = 0.0;
        int volume = 0;
        long marketCap = 0;
        double dividends = 0.0;
        URL link = new URL(url);
        CSVParser csv = CSVParser.parse(link, StandardCharsets.UTF_8, CSVFormat.DEFAULT.builder().setHeader().build());
        for (CSVRecord record : csv) {

            String symbol = record.get("Symbol");
            int shares = Integer.parseInt(record.get("Shares"));
            JsonNode node = loadPricesFromWeb(symbol);
            for (JsonNode entry : node) {
                name = entry.get("name").asText();
                price = entry.get("price").asDouble();
                volume = entry.get("volume").asInt();
                marketCap = entry.get("marketCap").asLong();
            }

            Stock stock = new Stock(symbol, name, price, volume, marketCap, dividends);
            stockPortfolio.add(stock);
            if (!investment.containsKey(symbol)) {
                investment.put(symbol, null);
            }
            double invested = shares * price;
            investment.put(symbol, invested);
        }
        System.out.println("ALL STOCKS");
       String str =  stockPortfolio.toString();
        System.out.println(str + "\n");
        System.out.println("CHEAP AND EXPENSIVE STOCKS \n");
        System.out.println("Cheapest Stock: " + stockPortfolio.findCheapestStock());
        System.out.println("Most Expensive Stock: " + stockPortfolio.findMostExpensive() + "\n");
        System.out.println("TOTAL INVESTMENT \n");
        getTotalValueOfInvestment();

    }
    public void getTotalValueOfInvestment() {
        for (Map.Entry<String, Double> entry : investment.entrySet()) {
            System.out.println("Symbol: " + entry.getKey() + "\n Total Investment: " + entry.getValue());
        }
    }

}
