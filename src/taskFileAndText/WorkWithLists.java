package taskFileAndText;

import java.util.*;

import static taskFileAndText.WorkWithFiles.*;

public class WorkWithLists {
    public static final String NAME_LIST_OF_UNIQUE = "uniqueList";
    public static final String NAME_LIST_OF_ONLY_ONE = "onlyOneList";
    public static final String NAME_LIST_OF_DOUBLES = "doubleList";

    public static Map<String, Object> getSpecialLists() {
        Map<String, Object> wrapperList = new HashMap<>();

        List<String> uniqueList = setNewList();             //list of words, no repeats
        List<String> onlyOneList = setNewList();            //list of words, only once
        Map<Integer, List<String>> doublesList = setNewMap();//list of words, duplicates/doubles and how much

        wrapperList.put(NAME_LIST_OF_UNIQUE, uniqueList);
        wrapperList.put(NAME_LIST_OF_ONLY_ONE, onlyOneList);
        wrapperList.put(NAME_LIST_OF_DOUBLES, doublesList);

        return wrapperList;
    }

    public static void listToFiles(Map<String, Object> specialLists, String directoryPath) {

        for (Map.Entry<String, Object> listOfMap : specialLists.entrySet()) {
            if (listOfMap.getKey().equals(NAME_LIST_OF_UNIQUE)) {
                List<String> uniqueList = (List<String>) listOfMap.getValue();
                String mainText = "";
                for (String elOfLists : uniqueList) {
                    mainText = mainText.concat(elOfLists.concat("\n"));
                }
                prepareWriteTextToFile(directoryPath, mainText, listOfMap.getKey());
            }
            if (listOfMap.getKey().equals(NAME_LIST_OF_ONLY_ONE)) {
                List<String> onlyOneList = (List<String>) listOfMap.getValue();
                String mainText = "";
                for (String elOfLists : onlyOneList) {
                    mainText = mainText.concat(elOfLists.concat("\n"));
                }
                prepareWriteTextToFile(directoryPath, mainText, listOfMap.getKey());
            }
            if (listOfMap.getKey().equals(NAME_LIST_OF_DOUBLES)) {
                Map<Integer, List<String>> doubleList = (Map<Integer, List<String>>) listOfMap.getValue();
                String mainText = "";
                for (Map.Entry<Integer, List<String>> entry : doubleList.entrySet()) {
                    String countWords = entry.getKey().toString();
                    List<String> listFromMap = entry.getValue();
                    mainText = mainText.concat(countWords.concat("\n"));
                    for (String elOfLists : listFromMap) {
                        mainText = mainText.concat(elOfLists.concat("\n"));
                    }
                }
                prepareWriteTextToFile(directoryPath, mainText, listOfMap.getKey());
            }
        }
    }

    // Function to sort Map by Key
    public static <K, V> TreeMap<K, V> sortbykey(Map<K, V> map) {
        // TreeMap to store values of HashMap
        TreeMap<K, V> sorted = new TreeMap<>();

        // Copy all data from hashMap into TreeMap
        sorted.putAll(map);

        return sorted;
    }

    public static <T> List<T> setNewList() {
        return new ArrayList<>();
    }

    public static <K, V> Map<K, V> setNewMap() {
        return new HashMap<>();
    }

}
