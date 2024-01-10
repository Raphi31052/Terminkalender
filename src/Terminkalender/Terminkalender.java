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
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Welche Zeile?");
        int Zeile = scanner1.nextInt();

        try {

            File file = new File("meineTextdatei.txt");
            Scanner reader = new Scanner(file);
            int lineNumber = 1;
            --Zeile;

            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                if (lineNumber == Zeile) {
                    System.out.println("----------------Event---------------");
                    System.out.println(line);
                    System.out.println("----------------Event---------------");
                    break;
                }

                ++lineNumber;
            }

            reader.close();
        } catch (FileNotFoundException var6) {
            var6.printStackTrace();
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
        reason = scanner.nextLine();
        if (year >= 0 && month >= 1 && month <= 12 && day >= 0 && day <= 31) {
            String[] textParts = new String[]{(year) + "/" + month + "/" + day + "/" + reason + "\n"};
            String textToWrite = String.join("/", textParts);
            FileWriter writer = null;

            try {
                writer = new FileWriter("meineTextdatei.txt", true);
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
