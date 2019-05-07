package mainController;

import java.io.*;

public class Logger {
    private static String filename = "AssignmentsLog.txt";

    public static void log(String txt) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            System.out.println(txt);
            pw.println(txt);

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startNew() {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }
}
