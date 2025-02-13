import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
public class StockPortfolio {
    ObjectMapper mapper = new ObjectMapper();

    private ArrayList<Stock> stocks;

    public StockPortfolio() {
        this.stocks = new ArrayList<>();
    }

    public void add(Stock stock){
        stocks.add(stock);
    }
    public void remove(Stock stock){
        stocks.remove(stock);
    }
    public int size(){
       return stocks.size();
    }

    public Stock findCheapestStock(){
        Stock stock = stocks.get(0);
        for(int i = 1; i < stocks.size(); i++){
            if(stocks.get(i).getPrice() < stock.getPrice()){
                stock = stocks.get(i);
            }
       }
        return stock;
    }

    public Stock findMostExpensive(){
       Stock stock = stocks.get(0);
        for(int i = 1; i < stocks.size(); i++){
            if(stocks.get(i).getPrice() > stock.getPrice()){
                stock = stocks.get(i);
            }
        }
        return stock;

    }

    public double printAveragePriceOfAllStocks(){
        double sum = 0;
        for(Stock stock : stocks){
            sum += stock.getPrice();
        }
       return sum / stocks.size();

    }

    @Override
    public String toString() {
        return "StockPortfolio{" +
                "stocks=" + stocks +
                '}';
    }

    public Stock createStock(String symbol) throws IOException {
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
