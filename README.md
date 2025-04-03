# Investment Portfolio Tracker

A console-based Java application that reads a user's stock portfolio from a remote CSV file, fetches live stock data from the Financial Modeling Prep API, and provides analysis such as total investment value, the cheapest and most expensive stocks, and average stock price.

---

## 📌 Features

- 🔎 **Live Stock Data** – Fetches up-to-date stock prices, volumes, and market caps using Financial Modeling Prep API.
- 📈 **Portfolio Analysis** – Calculates total investment per stock and total portfolio value.
- 💹 **Stock Comparison** – Identifies the **cheapest** and **most expensive** stocks in the portfolio.
- 📊 **Average Stock Price** – Computes the average price of all stocks.
- 📄 **Remote CSV Parsing** – Loads and reads a remote CSV of stock symbols and shares.
- ✅ **Test Coverage** – Includes JUnit-based unit tests to validate logic and ensure reliability.

---

## 🛠️ Technologies Used

- Java 17  
- Apache Commons CSV  
- Jackson (FasterXML)  
- JUnit 4

---

## 📂 Sample CSV Format

Your portfolio should be structured like this:

Symbol,Shares
AAPL,500
IBM,300
NFLX,200
AVB,150
BAC,400

## 🚀 How to Run the App

1. **Clone the repo**  
   ```bash
   git clone https://github.com/JoshuaDada007/StockPortfolio.git

	2.	Update the CSV URL
Open InvestmentTest.java and replace the default URL with your own public CSV:

String url = "https://your-csv-link.com/yourfile.csv";


	3.	Open the project in IntelliJ or any Java IDE.
	4.	Ensure Java 17+ is installed.
	5.	Run InvestmentTest to fetch live stock data and view analysis.

