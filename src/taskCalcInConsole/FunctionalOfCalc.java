package taskCalcInConsole;

@FunctionalInterface
interface FunctionalOfCalc<T1, T2> {
    double compute(T1 calc, T2 command);
}

