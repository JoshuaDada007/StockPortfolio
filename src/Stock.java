

import java.io.IOException;
import java.util.Objects;

public class Stock {
    private String name;
    private String symbol;
    private double price;
    private int volume;
    private long marketCap;
    private double dividend;
    private int shares;

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }



    public Stock(String symbol, String name, double price, int volume, long marketCap, double dividend) throws IOException {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.volume = volume;
        this.marketCap = marketCap;
        this.dividend = dividend;
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getDividend() {
        return dividend;
    }

    public void setDividend(double dividend) {
        this.dividend = dividend;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                ", marketCap=" + marketCap +
                ", dividend=" + dividend +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Stock stock)) return false;
        return Double.compare(price, stock.price) == 0 && volume == stock.volume && marketCap == stock.marketCap && Double.compare(dividend, stock.dividend) == 0 && Objects.equals(name, stock.name) && Objects.equals(symbol, stock.symbol);
    }
}
