package mainController;

import java.io.*;

public class Logger {
    private static String filename = "AssignmentsLog.txt";

    public static void log(String txt) {
        try {
            File file = new File(filename);

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(txt);

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startNew() {
        try {
            File file = new File(filename);
            if (file.exists()) {
                file.delete();
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void emergencyCallHeader() {
        try {
            File file = new File(filename);

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("Emergency Calls");
            pw.println("ID\tTime\tNumber of patients\tLocation");

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void assignmentsHeader() {
        try {
            File file = new File(filename);

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("");
            pw.println("Assignments");
            pw.println("Type\tAmbulance Name\tDestination Name\t");

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
