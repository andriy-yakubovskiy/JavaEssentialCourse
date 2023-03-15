package taskCalcInConsole;

public class Calculator implements CountCalc {
    public double value1 = 0;
    public double value2 = 0;

    @Override
    public double add() {
        return (this.value1 + this.value2);
    }

    @Override
    public double sub() {
        return (this.value1 - this.value2);
    }

    @Override
    public double mult() {
        return (this.value1 * this.value2);
    }

    @Override
    public double div() throws ArithmeticException {
        double equal = 0;
        if (this.value2 == 0) {
            throw new ArithmeticException("ERR, div / 0");
        } else {
            equal = this.value1 / this.value2;
        }
        return equal;
    }

}


