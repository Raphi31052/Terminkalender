package Terminkalender;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;

public class Terminkalender {

    static HashMap<String, String> info = new HashMap<>();
    String[] splitinfo;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
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
            if (Eingabe.equals("save")){
                saveinfo();
            }
        }
    }

    public static void read() {
        int userinput = 0;
        boolean valid = false;
        boolean exist = false;
        Scanner input = new Scanner(System.in);
        while (valid != true) {
            System.out.println("Bitte Zeile eingeben");
            userinput = input.nextInt();
            if (userinput < 0) {
                System.out.println("Bitte valide Zeile eingeben");
            } else {
                valid = true;
            }
        }


        try {
            int i = 1;
            File myObj = new File("src/Terminkalender/meineTextdatei.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (i == userinput) {
                    System.out.println(data);
                    exist = true;

                }
                i++;
            }
            if(exist!= true){
                System.out.println("Bitte existierende Zeile eingeben");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static HashMap<String, String> create() {
        String Input;
        String[] splitinfo;
        String DateString;

        Scanner scanner = new Scanner(System.in);
        System.out.print("YY/DD/MM/Reason");
        Input = scanner.next();
        splitinfo = Input.split("/");


        if (Integer.parseInt(splitinfo[0]) >= 0 && Integer.parseInt(splitinfo[2]) >= 1 && Integer.parseInt(splitinfo[2]) <= 12 && Integer.parseInt(splitinfo[1]) >= 0 && Integer.parseInt(splitinfo[1]) <= 31) {


            DateString = splitinfo[0]+("-")+splitinfo[2]+("-")+splitinfo[1];
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateformat = LocalDate.parse(DateString);
            String date = dateformat.format(myFormatObj);
            //LocalDate datef = LocalDate.parse(date);
            info.put(date,splitinfo[3]);
            System.out.println(info.size());
            return info;



        }

        return null;
    }
    public static void saveinfo() {
        for (String i : info.keySet()) {
            String textToWrite = String.join("/", i+info.get(i));
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
                }else{
                    System.out.print("Falsche Eingabe");

            }

        }

        }
    }
    }



