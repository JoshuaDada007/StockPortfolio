
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


public class InvestmentTest {
    Investment investment;
    StockPortfolio portfolio;
    String url = "https://gist.githubusercontent.com/JoshuaDada007/e35c86bd1e2a86cf823316fa0f1bbe3c/raw/42d73bc4bbb93bdea7c5423f4bf6df7021c5c87e/portfolio.csv";

    @Before
    public void setUp() {
        investment = new Investment();
        portfolio = new StockPortfolio();
    }

    @Test
    public void loadPortfolioFromWeb() throws IOException {
        investment.loadPortfolioFromWeb(url);
    }


    @Test
    public void getTotalInvestment() {
        investment.getTotalValueOfInvestment();
    }
}
