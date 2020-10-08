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

    download(String name){

        String url = "https://mvnrepository.com";
        String urlSearch = "https://mvnrepository.com/search?q=";
        String nameCorrected = name.replaceAll(" ","+");
        Element doc = getPageBody(urlSearch + nameCorrected); // Full page URL here

        Element link = doc.select("a:contains(" + name + ")").get(0);
        String fullLink = link.attr("href");
        String nameLink = fullLink.substring(fullLink.lastIndexOf("/")+1,fullLink.length());
        Element doc2 = getPageBody(url + fullLink);

        Element linkToPAge = doc2.select("a[href*="+ nameLink +"/]").get(1);
        String linkKek = linkToPAge.attr("href");
        String shortFullLink = fullLink.substring(0,fullLink.lastIndexOf("/"));
        Element doc1 = getPageBody("https://mvnrepository.com"+ shortFullLink + "/" + linkKek);
        Element linkToVer = doc1.select("a:contains(jar)").get(0);
        String linkToDownload = linkToVer.attr("href");
        DownloadFile(linkToDownload,"src/jars/" + name + ".jar");


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