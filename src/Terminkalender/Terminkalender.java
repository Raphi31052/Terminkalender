package Terminkalender;

import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Terminkalender {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.print("Befehl Eingeben:");
            String Eingabe = scanner.next();
            if (Eingabe.equals("create")) {
                create();
            }

            if (Eingabe.equals("exit")) {
                System.out.println("Good Bye");
                System.exit(0);
            }

            if (Eingabe.equals("read")) {
                read();
            }
        }
    }

    public static void read() {



        try {
            File myObj = new File("src/Terminkalender/meineTextdatei.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void create() {
        String reason;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Jahr eingeben:");
        int year = scanner.nextInt();
        System.out.print("Monat eingeben:");
        int month = scanner.nextInt();
        System.out.print("Tag eingeben:");
        int day = scanner.nextInt();
        System.out.println("Grund eingeben:");
        reason = scanner.next();
        if (year >= 0 && month >= 1 && month <= 12 && day >= 0 && day <= 31) {
            String[] textParts = new String[]{(year) + "/" + month + "/" + day + "/" + reason + "\n"};
            String textToWrite = String.join("/", textParts);
            FileWriter writer = null;

            try {
                writer = new FileWriter("src/Terminkalender/meineTextdatei.txt", true);
                writer.write(textToWrite);
                writer.close();
                System.out.println("Text wurde in die Datei geschrieben.");
            } catch (IOException var17) {
                var17.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException var16) {
                        var16.printStackTrace();
                    }
                }

            }
        } else {
            System.out.print("Falsche Eingabe");
        }

    }
}
