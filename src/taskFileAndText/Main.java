/*
---TaskWorkWithFileAndText---
Get a list of words from one file and get three files of words as output 'uniqueList', 'onlyOneList', 'doubleList'.
uniqueList - list of only unique words,
onlyOneList - list of words repeated only once,
doubleList - list of words with their repetition count (order list)
*/
package taskFileAndText;

import java.io.*;
import java.util.*;

import static taskFileAndText.WorkWithFiles.*;
import static taskFileAndText.WorkWithLists.*;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        File mainFile = selectFile();
        String textFromFile = readTextFromFile(mainFile);

        Map<String, Object> specialLists = getSpecialLists();
        if (!startAlgorithm(specialLists, textFromFile)){
            System.out.println("Execution error.");
            exit(0);
        }

        // File directory = new File("");
        // String myPath = directory.getAbsolutePath();
        String directoryPath = mainFile.getParent();
        listToFiles(specialLists, directoryPath);

        System.out.println("Finish. Result in: " + directoryPath);
    }

    public static boolean startAlgorithm(Map<String, Object> specialLists, String currentText) {
        boolean done = false;
        if (currentText.isEmpty()){
            return done;
        }

        //Get lines from text
        List<String> originalListCurrentText = currentText.lines().toList();
        //Get lines for work
        List<String> listCurrentText = new ArrayList<>();
        for (String strLineLists : originalListCurrentText) {
            String temp = strLineLists.toLowerCase();
            listCurrentText.add(temp.trim());
        }

        List<String> uniqueList = (List<String>) specialLists.get(NAME_LIST_OF_UNIQUE);
        List<String> onlyOneList = (List<String>) specialLists.get(NAME_LIST_OF_ONLY_ONE);
        Map<Integer, List<String>> doubleList = (Map<Integer, List<String>>) specialLists.get(NAME_LIST_OF_DOUBLES);

        Map<String, Integer> countDoubleList = new HashMap<>();
        //Main loop
        for (String strLineLists : listCurrentText) {
            if (!uniqueList.contains(strLineLists)) {
                uniqueList.add(strLineLists);
            }
            int numberOfElements = Collections.frequency(listCurrentText, strLineLists);
            if (numberOfElements == 1) {
                onlyOneList.add(strLineLists);
            } else {
                if (!countDoubleList.containsKey(strLineLists))
                    countDoubleList.put(strLineLists, numberOfElements);
            }
        }

        //Map, number and how many words
        for (Map.Entry<String, Integer> elemDoubleList : countDoubleList.entrySet()) {
            Integer numberStrMap = elemDoubleList.getValue();
            String strMap = elemDoubleList.getKey();
            if (doubleList.containsKey(numberStrMap)) {
                List<String> listString = doubleList.get(numberStrMap);
                listString.add(strMap);
                doubleList.put(numberStrMap, listString);
            } else {
                List<String> listString = new ArrayList<>();
                listString.add(strMap);
                doubleList.put(numberStrMap, listString);
            }
        }

        TreeMap<Integer, List<String>> sortedMap = sortbykey(doubleList);
        doubleList.clear();
        for (Map.Entry<Integer, List<String>> entry : sortedMap.entrySet())
            doubleList.put(entry.getKey(), entry.getValue());

        done = (uniqueList.size()!=0 || onlyOneList.size()!=0 || doubleList.size()!=0);

        return done;
    }

}