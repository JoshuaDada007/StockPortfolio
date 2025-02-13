import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public class Investment {
    StockPortfolio stockPortfolio = new StockPortfolio();

    public Investment() {
    }

    public JsonNode loadPricesFromWeb(String symbol) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new URL("https://financialmodelingprep.com/api/v3/quote/" + symbol + "?apikey=Sc7lTMoT3VVh4tx98nPTWCliWR6O9Gb9"));
        String url = "https://gist.githubusercontent.com/JoshuaDada007/e35c86bd1e2a86cf823316fa0f1bbe3c/raw/42d73bc4bbb93bdea7c5423f4bf6df7021c5c87e/portfolio.csv";
        URL link = new URL(url);
        InputStream is = link.openStream();
        String text = IOUtils.toString(is, StandardCharsets.UTF_8);
        System.out.println(text);
        System.out.println(node);
        return node;

    }


    public void loadPortfolioFromWeb(String url) throws IOException {
        String name = "";
        double price = 0.0;
        int volume = 0;
        long marketCap = 0;
        double dividends = 0.0;
        TreeMap<String, Double> investment = new TreeMap<>();
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

        System.out.println("Cheapest Stock: " + stockPortfolio.findCheapestStock());
        System.out.println("Most Expensive Stock: " + stockPortfolio.findMostExpensive() + "\n");
        for (Map.Entry<String, Double> entry : investment.entrySet()) {
            System.out.println("Symbol: " + entry.getKey() + "\n Total Investment: " + entry.getValue());
        }

    }

}
