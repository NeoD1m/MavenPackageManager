package com.company;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {



    //String unzipPath = "C:\\Users\\Dima\\IdeaProjects\\PackageManager\\src\\unzip";
    public static void main(String[] args) throws IOException {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("MAVEN package manager");
        System.out.println("Input name of the package");
        String name = inputScanner.nextLine();
        System.out.println("Input version of the package");
        String ver = inputScanner.nextLine();
        download jar = new download(name,ver);

        String unzipPath = "src/unzip/" + name + " v" + ver;

        File newFolder = new File(unzipPath);
        newFolder.mkdir();
        unzip lol = new unzip("src/jars/" + name + " v" + ver + ".jar",unzipPath);

        System.out.println(findFile("pom.xml",newFolder));


    }

    public static String findFile(String name, File file)
    {
        File[] list = file.listFiles();
        if(list!=null)
            for (File fil : list)
            {
                if (fil.isDirectory())
                {
                    findFile(name,fil);
                }
                else if (name.equalsIgnoreCase(fil.getName()))
                {
                    System.out.println(fil.getParentFile());
                    return fil.getParentFile().toString();
                }
            }
        return null;
    }



}
