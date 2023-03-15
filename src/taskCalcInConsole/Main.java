/*
---TaskCalculatorInConsole---
Create class ‘Calculator’, with methods to perform basic arithmetic operations.
Implementation of class ‘Calculator’ through a Factory.
List of operation commands in enum.
Calling a method of operations through a functional interface.
*/
package taskCalcInConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        componentCalculator();
    }

    public static void componentCalculator() {
        Calculator calc = FactoryCalc.CreateCalc();
        FunctionalOfCalc doActionCalc = Action.doAction();

        InputStreamReader streamReader = new InputStreamReader(System.in);

        CommandCalc currentCommand = null;
        String strLineReaded = "";
        boolean stopDoingRead = false;
        String exitWordDoing = "quit";
        String textCommands = "";

        for (CommandCalc nameCommand : CommandCalc.values()){
            textCommands = textCommands + (textCommands.isBlank() ? (nameCommand.name()) : (","+nameCommand.name()));
        }

        do {
            writerConsole("Select command ("+textCommands+"):");
            try {
                strLineReaded = readerConsole(streamReader);
            } catch (IOException e) { e.printStackTrace(); exit(0); }
            try {
                currentCommand = CommandCalc.valueOf(strLineReaded);
                stopDoingRead = true;
            } catch (IllegalArgumentException e) {
                if (strLineReaded.equals(exitWordDoing)) {
                    try {
                        streamReader.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    exit(0);
                }
                writerConsole("Incorrect command: '"+strLineReaded+"'");
                writerConsole("Try again or write '"+exitWordDoing+"': '"+strLineReaded+"'");
            }
        }while(!stopDoingRead);

        try {
            writerConsole("Writer value1:");
            try {
                calc.value1 = Double.valueOf(readerConsole(streamReader));
            } catch (IOException e) { e.printStackTrace(); exit(0); }
            writerConsole("Writer value2:");
            try {
                calc.value2 = Double.valueOf(readerConsole(streamReader));
            } catch (IOException e) { e.printStackTrace(); exit(0); }

            double equal = doActionCalc.compute(calc, currentCommand.name());
            System.out.println(calc.value1+" "+currentCommand.getSymbol()+" "+calc.value2+" = "+equal);

        } catch (ArithmeticException e) {
            System.out.println("Catch err: " + e.getMessage());
        }
        try {
            streamReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String readerConsole(InputStreamReader streamReader) throws IOException{
        String lineString = "";
        BufferedReader reader = new BufferedReader(streamReader);
        try {
            lineString = reader.readLine();
        } catch (IOException e) {
            reader.close();
            throw new IOException(e);
        }
//        try (BufferedReader reader = new BufferedReader(streamReader)) {
//            lineString = reader.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return lineString;
    }

    private static void writerConsole(String lineString){
        System.out.println(lineString);
    }

}
