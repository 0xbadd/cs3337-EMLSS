package mainController;

import java.io.*;

public class Logger {
    public static void log(String txt) {
        try {
            File file = new File("AssignmentsLog.txt");
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
}
