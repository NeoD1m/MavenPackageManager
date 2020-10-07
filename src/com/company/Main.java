package com.company;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static String pathToPom;
    private static boolean debugMode = true;
    private static String name;
    private static String ver;
    //String unzipPath = "C:\\Users\\Dima\\IdeaProjects\\PackageManager\\src\\unzip";
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
        System.out.println(pathToPom);
        //System.out.println(findFile("pom.xml",newFolder));


    }

    public static void  findFile(String name, File file)
    {
        File temp = null;
        File[] list = file.listFiles();
        if(list!=null)
            for (File fil : list)
            {
                if (fil.isDirectory())
                {
                    //System.out.println("1");
                   findFile(name,fil);
                }
                else if (name.equalsIgnoreCase(fil.getName()))
                {
                    //System.out.println("2");
                    //System.out.println(fil.getParentFile());
                    temp = fil.getParentFile();
                    //return fil.getParentFile().toString();
                }
            }
        //System.out.println("3");
        //System.out.println(temp);
        if (temp != null)
            pathToPom = temp.toString();
    }



}
