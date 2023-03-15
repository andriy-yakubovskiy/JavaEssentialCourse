package taskCalcInConsole;

public enum CommandCalc {
    add("+"), sub("-"), mult("*"), div("/");

    public String symbol;

    public String getSymbol() {
        return symbol;
    }

    CommandCalc(String symbol) {
        this.symbol = symbol;
    }

}
