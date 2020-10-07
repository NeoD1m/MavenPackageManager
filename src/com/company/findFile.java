package com.company;
import java.io.*;

public class findFile
{
    String temp;

    public findFile(String name, File file) {

    }

    public String findFile(String name, File file)
    {
        File[] list = file.listFiles();
        if(list!=null)
            for (File fil : list)
            {
                if (fil.isDirectory())
                {
                    new findFile(name,fil);
                }
                else if (name.equalsIgnoreCase(fil.getName()))
                {
                    temp = fil.getParentFile() + "";
                    //output(fil.getParentFile());
                    return temp;
                }
            }
        return null;
    }
}