package taskCalcInConsole;

public abstract class FactoryCalc {
    public static Calculator CreateCalc() {
        return new Calculator();
    }
}