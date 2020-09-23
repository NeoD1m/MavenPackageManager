package com.company;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("MAVEN package manager");
        System.out.println("Input name of the package");
        String name = inputScanner.nextLine();
        System.out.println("Input version of the package");
        String ver = inputScanner.nextLine();
        download jar = new download(name,ver);

    }
}
