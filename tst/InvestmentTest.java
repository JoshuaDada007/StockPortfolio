
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class InvestmentTest {
    URL link;
    Investment investment;
    String url = "https://gist.githubusercontent.com/JoshuaDada007/e35c86bd1e2a86cf823316fa0f1bbe3c/raw/42d73bc4bbb93bdea7c5423f4bf6df7021c5c87e/portfolio.csv";
    CSVParser parser;

    @Before
    public void setUp() throws IOException {
        investment = new Investment();
        link = new URL(url);
        parser = CSVParser.parse(link, StandardCharsets.UTF_8, CSVFormat.DEFAULT.builder().setHeader().build());
    }

    @Test
    public void loadPortfolioDataFromWeb() throws IOException {
        investment.loadPortfolioFromWeb(url);
    }


    @Test
    public void viewPortfolioFromWeb() {
        for (CSVRecord record : parser) {
            System.out.println("Stock: " + record.get("Symbol"));
            System.out.println("Shares: " + record.get("Shares") + "\n");
        }
    }

    @Test
    public void webPortfolioSize(){
        int count = 0;
        for (CSVRecord record : parser) {
            System.out.println("Stock: " + record.get("Symbol"));
            count++;
        }
        Assert.assertEquals(count, 5);
    }

    @Test
    public void totalAppleInvestment(){
        String apple ="";
        int shares = 0;
        for (CSVRecord record : parser) {
            apple = record.get("Symbol");
            shares = Integer.parseInt(record.get("Shares"));
            break;
        }
        System.out.println("Stock: " + apple);
        System.out.println("Shares: " + shares);
        Assert.assertEquals(shares, 500);
    }

    @Test(expected = MalformedURLException.class)
    public void exceptionTest() throws IOException {
        String fakeUrl = "github.com/joshy-washy";
        investment.loadPortfolioFromWeb(fakeUrl);
    }
}
