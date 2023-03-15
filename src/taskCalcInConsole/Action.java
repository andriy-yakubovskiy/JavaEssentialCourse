package taskCalcInConsole;

public class Action {
    public static FunctionalOfCalc doAction(){
        FunctionalOfCalc<Calculator,String> actionCalculator = (calc, comm) -> {
                    double equal = 0;
                    if(CommandCalc.add.name() == comm) {
                        equal = calc.add();
                    }
                    if(CommandCalc.sub.name() == comm) {
                        equal = calc.sub();
                    }
                    if(CommandCalc.mult.name() == comm) {
                        equal = calc.mult();
                    }
                    if(CommandCalc.div.name() == comm) {
                        equal = calc.div();
                    }
                    return equal;
                };
        return actionCalculator;
    }
}
