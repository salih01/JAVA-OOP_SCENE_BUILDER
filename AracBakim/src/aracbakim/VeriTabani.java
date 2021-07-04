/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aracbakim;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author alfa
 */
public class VeriTabani {
    
    private BufferedReader  bufferedReader;
    
     //------------------------Singletion Tasarım Kalibi----------------------------------- global bir nesne oluşturduğumuz yer    
    //Text Dosyasına burada yazıyoruz ..
    public void TextWrite(String Veri) 
    {
        try{  
            File file =new File("VeriTabani.txt");// Nereye Kaydedeceğimizi konumunu belirtiyoruz 
            FileWriter fileWriter=new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); //Text Dosyasına yaz
        
            bufferedWriter.write(Veri);
            bufferedWriter.newLine();
            bufferedWriter.close();
            } catch (IOException e) {
                System.out.println("Text Yazma Hatasi (ERROR)");
        }
        
    }
    
    public void TextReader()
    {
        try {
            FileReader fileReader=new FileReader("VeriTabani.txt");
            bufferedReader=new BufferedReader(fileReader);
            String line;
            
            while ((line=bufferedReader.readLine()) !=null)
            {    
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Dosya Okuma Hatasi (ERROR)");
        }
    }
    
    
}
