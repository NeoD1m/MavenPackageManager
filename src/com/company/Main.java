package com.company;


import java.io.*;
import java.util.Scanner;

public class Main {

    public static String pathToPom = null;
    private static boolean debugMode = true;
    private static String name;
    public static void main(String[] args) throws IOException {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("MAVEN package manager");
        System.out.println("Input name of the package");
        if (!debugMode)
        name = inputScanner.nextLine();

        if (debugMode)
            name = "Tlog Core";

        get(name);
    }

    public static void get(String name) throws IOException {
        download jar = new download(name);

        String unzipPath = "src/unzip/" + name;

        File newFolder = new File(unzipPath);
        newFolder.mkdir();
        unzip lol = new unzip("src/jars/" + name + ".jar",unzipPath);
        pathToPom=null;
        findFile("pom.xml",newFolder);
        if (pathToPom != null) {
            getListOfDependencies(pathToPom);
        } else System.out.println("no dependencies");
    }

    public static void  findFile(String name, File file) {
        File temp = null;
        File[] list = file.listFiles();
        if (list != null)
            for (File fil : list) {
                if (fil.isDirectory()) {
                    findFile(name, fil);
                } else if (name.equalsIgnoreCase(fil.getName())) {
                    temp = fil.getParentFile();
                }
                if (temp != null)
                    pathToPom = temp.toString();
            }
    }

    public static void getListOfDependencies(String pathToPom) throws IOException {
        BufferedReader reader;
        BufferedReader safeReader;
        reader = new BufferedReader(new FileReader(pathToPom + "/pom.xml"));
        safeReader = new BufferedReader(new FileReader(pathToPom + "/pom.xml"));
        String line = reader.readLine();
        String kek = safeReader.readLine();
        boolean lol = false;
        while (!kek.contains("</project>")){
            kek = safeReader.readLine();
            if (kek.contains("<dependencies>")){

                while (!line.contains("<dependencies>")) {
                    line = reader.readLine();
                }
                while (!line.equals("</project>")){
                    line = reader.readLine();
                    if (line.contains("<artifactId>")){
                        line = line.replace(" ","");
                        line = line.replace("<artifactId>","");
                        line = line.replace("</artifactId>","");
                        System.out.println(line);
                        get(line);

                        lol = true;
                    }
                }
                break;
            }
            if (lol &&  (kek.contains("</project>")))
             System.out.println("no dependencies");
        }
    }
}
