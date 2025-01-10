import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class StockUtility {

    public static void main(String[] args) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(
                new URL("https://finnhub.io/api/v1/quote?symbol=NVDA&token=csqnulpr01qv7qe2g130csqnulpr01qv7qe2g13g"));
        double priceOfNVDA = root.get("c").asDouble();
        System.out.println(priceOfNVDA);
        // GME, RBLX, DOCN 1/9 starting capital $1,000,000
        // 40%, 30%, 30%
        // May 1st Portfolio Value?
        double gmeShares = getSharesForAmount("GME", 400000);
        double rblxShares = getSharesForAmount("RBLX", 300000);
        double docnShares = getSharesForAmount("DOCN", 300000);
        System.out.println("GME shares " + gmeShares);
        System.out.println("RBLX shares " + rblxShares);
        System.out.println("DOCN shares " + docnShares);

        /*
        GME shares 12135.922330097086
        RBLX shares 4834.810636583401
        DOCN shares 8700.696055684455
         */

    }

    public static double priceOfStock(String symbol) {
        ObjectMapper mapper = new ObjectMapper();
        double price = 0;
        try {
            JsonNode root = mapper.readTree(
                    new URL("https://finnhub.io/api/v1/quote?symbol=" + symbol + "&token=csqnulpr01qv7qe2g130csqnulpr01qv7qe2g13g"));
            price = root.get("c").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return price;
    }

    public static double getSharesForAmount(String symbol, double amount) {
        double price = priceOfStock(symbol);
        return amount/price;
    }

}
