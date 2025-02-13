import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.source.tree.Tree;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class NewCSVTestClass {



    @Test
    public void loadState() throws Exception {
        CSVParser parse = CSVParser.parse(new File("data.csv"), StandardCharsets.UTF_8,
                CSVFormat.DEFAULT.builder().setHeader().build());
        TreeMap<String, Integer> states = new TreeMap();
        for (CSVRecord record : parse) {
            String state = record.get("State");
            if (!states.containsKey(state)) {
                states.put(state, 1);
            } else {
                states.put(state, states.get(state) + 1);
            }
        }
        System.out.println(states);
    }

    @Test
    public void downloadFile() throws Exception {
        URL url = new URL("https://gist.githubusercontent.com/JoshuaDada007/e35c86bd1e2a86cf823316fa0f1bbe3c/raw/5fbf2dd6d268e785a3cd6a5c3f9564133d8129db/portfolio.csv");
        InputStream in = url.openStream();
        CSVParser parser = CSVParser.parse(url, StandardCharsets.UTF_8, CSVFormat.DEFAULT.builder().setHeader().build());
        System.out.println("#################################");
        TreeMap<String, Integer> map = new TreeMap<>();
        TreeSet<Integer> shares = new TreeSet<>();
        for (CSVRecord record : parser) {
            String stocks = record.get("Symbol");
            int value = Integer.parseInt(record.get("Shares"));
            shares.add(value);
            if (!map.containsKey(stocks)) {
                map.put(stocks, 1);
            }
            map.put(stocks, value);
        }
        System.out.println(map);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " \n Value: " + entry.getValue());
        }
    }
}


//Using your existing Stock java and Portfolio java classes. Create a new class called Investment.java that maintains a portfolio of investments. The Investment class has a method that loads the prices from the web with the following method signature:
//• void loadPortfolioFromWeb(String url)
//Create a csv file that describes a set of stock symbols and each number of shares invested in them and use the loadPricesFromWeb method to populate the portfolio. Each line in the cs file describes the relevant information of an investment. You will need to parse this data and load it to the portfolio of the investment. After loading the portfolio with the data, you will verify that the prices have been loaded correctly by using the following methods in the Portfolio class.
//• Stock findCheapestStock0
//• Stock findMostExpensiveStock0
//• double getTotal ValueOfInvestmentO
//Write JUnit tests to verify this result in the InvestmentTest java class. Follow the guidelines as discussed in class:
//• Use AAA (Arrange-Act-Assert) style of testing
//• Have good code coverage (test at the minimum every method excluding simple getters and setters, every branches inside a method, test with boundary values, etc)
//• Have happy cases (at a minimum), and failure cases when a failure can happen
//• You will need to use a setup method annotated with @Before in order to arrange the required portfolio (this will allow you to refactor some repeated code)
//Submit the files Investment java and Investment Test java when done.



