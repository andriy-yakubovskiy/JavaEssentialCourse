package taskFileAndText;

import java.io.*;
import static java.lang.System.exit;

public class WorkWithFiles {

    public static File selectFile() {
        String pathToMainFile = "";
        System.out.println("Enter path to main file: ");
        //pathToMainFile = "D://MyJava//CODE//old.txt";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            pathToMainFile = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }

        if(pathToMainFile.isEmpty()){
            System.out.println("Not path found.");
            exit(0);
        }

        File mainFile = new File(pathToMainFile);
        if (!mainFile.exists()) {
            try {
                if (!mainFile.createNewFile()) {
                    System.out.println("Not file to analyze.");
                    exit(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
                exit(0);
            }
        }

        return mainFile;
    }

    public static String readTextFromFile(File myFile) {
        String scanText = new String("");

        try (BufferedReader bReader = new BufferedReader(new InputStreamReader((new FileInputStream(myFile))))) {
            String strText;
            while ((strText = bReader.readLine()) != null) {
                scanText = scanText.concat(strText).concat("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        return scanText;
    }

    public static void prepareWriteTextToFile(String directoryPath, String mainText, String nameFile) {
        String newFilePath = directoryPath + "\\" + nameFile + ".txt";
        File newFile = new File(newFilePath);
        if (!newFile.exists()) {
            try {
                if (!newFile.createNewFile()) {
                    exit(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
                exit(0);
            }
        }

        try {
            writeTextToFile(newFile, mainText);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void writeTextToFile(File myFile, String myText) throws FileNotFoundException{
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(myFile)), true)) {
            writer.print(myText);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.toString());
        }
    }
}