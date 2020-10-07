package com.company;


import java.io.*;
import java.util.Scanner;

public class Main {

    public static String pathToPom;
    private static boolean debugMode = false;
    private static String name;
    private static String ver;
    public static void main(String[] args) throws IOException {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("MAVEN package manager");
        System.out.println("Input name of the package");
        if (!debugMode)
        name = inputScanner.nextLine();
        System.out.println("Input version of the package");
        if (!debugMode)
        ver = inputScanner.nextLine();

        if (debugMode){
            name = "Tlog Core";
            ver = "1.1.1";
        }
        download jar = new download(name,ver);

        String unzipPath = "src/unzip/" + name + " v" + ver;

        File newFolder = new File(unzipPath);
        newFolder.mkdir();
        unzip lol = new unzip("src/jars/" + name + " v" + ver + ".jar",unzipPath);
        findFile("pom.xml",newFolder);
        getListOfDependencies(pathToPom);

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
        reader = new BufferedReader(new FileReader(pathToPom + "/pom.xml"));
        String line = reader.readLine();

        while (!line.contains("<dependencies>")){
            line = reader.readLine();
        }

        while (!line.equals("</project>")){
            line = reader.readLine();
            if (line.contains("<artifactId>")){
                line = line.replace("            <artifactId>","");
                line = line.replace("</artifactId>","");
                System.out.println(line);
            }
        }

    }


}
