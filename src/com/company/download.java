package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import java.io.IOException;

public class download {

    download(String name,String ver){

        String urlSearch = "https://mvnrepository.com/search?q=";

        String nameCorrected = name.replaceAll(" ","+");
        Element doc = getPageBody(urlSearch + nameCorrected); // Full page URL here

        Element link = doc.select("a:contains(" + name + ")").get(0);
        String fullLink = link.attr("href");
        //System.out.println(fullLink);
        Element doc1 = getPageBody("https://mvnrepository.com" + fullLink + "/" + ver);
        //System.out.println("https://mvnrepository.com" + fullLink + "/" + ver);

        Element linkToVer = doc1.select("a:contains(jar)").get(0);
        //System.out.println(linkToVer);
        String linkToDownload = linkToVer.attr("href");
        //System.out.println(linkToDownload);
        DownloadFile(linkToDownload,"src/jars/" + name + " v" + ver + ".jar" );
    }

    public static Element getPageBody(String url){
        Element doc = null;
        try {
            doc = Jsoup.connect(url).get().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
    public static void DownloadFile(String url, String file_name){
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file_name)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}