import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class PortfolioAutomationTest {
    StockPortfolio portfolio;

    @Before
    public void setUp() {
        portfolio = new StockPortfolio();

    }

    @Test
    public void testSize() throws IOException {

        Stock apple = portfolio.createStock("AAPL");
        Stock IBM = portfolio.createStock("IBM");
        Stock netflix = portfolio.createStock("NFLX");
        portfolio.add(apple);
        portfolio.add(IBM);
        portfolio.add(netflix);

        Assert.assertEquals(portfolio.size(), 3);

    }

    @Test
    public void removeStockTest() throws IOException {
        Stock apple = portfolio.createStock("AAPL");
        Stock IBM = portfolio.createStock("IBM");
        portfolio.add(apple);
        portfolio.add(IBM);
        portfolio.remove(IBM);
        Assert.assertEquals(portfolio.size(), 1);
    }

    @Test
    public void findCheapestStockTest() throws IOException {
        Stock apple = portfolio.createStock("AAPL");
        Stock AVB = portfolio.createStock("AVB");
        portfolio.add(apple);
        portfolio.add(AVB);
        Assert.assertEquals(portfolio.findCheapestStock(), AVB);

    }

    @Test
    public void findMostExpensiveStockTest() throws IOException {
        Stock apple = portfolio.createStock("AAPL");
        Stock IBM = portfolio.createStock("IBM");
        Stock AVB = portfolio.createStock("AVB");
        portfolio.add(apple);
        portfolio.add(IBM);
        portfolio.add(AVB);
        Assert.assertEquals(portfolio.findMostExpensive(), apple);
    }

    @Test
    public void findAveragePriceTest() throws IOException {
        StockPortfolio portfolio = new StockPortfolio();
        Stock apple = portfolio.createStock("AAPL");
        Stock AVB = portfolio.createStock("AVB");
        Stock bofA = portfolio.createStock("BAC");
        portfolio.add(apple);
        portfolio.add(AVB);
        portfolio.add(bofA);
        Assert.assertEquals(portfolio.printAveragePriceOfAllStocks(), 168.84, 1);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void emptyListTest(){
       portfolio.findMostExpensive();
    }



}
