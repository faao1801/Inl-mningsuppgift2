/*
 * Java Utveckling 18
 */

package inlämningsuppgift2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class FileReadWrite {
    /**
     * metod som söker efter namn eller personnummer och returnerar en objekt list av typen Kund
     * @param path
     * @return kund lista
     */
 public ArrayList<Kund> readAndSearchInFile(String path){
     Path inPath= Paths.get(path);
     String firstLine;
     String secondLine;
     String []firstLineArray;
     ArrayList<Kund> kundList= new ArrayList<>();
     
     while(true){
     //try with resources
     try(Scanner scanner= new Scanner(inPath);){
         
         String sök=JOptionPane.showInputDialog("Ange namn eller personnummer :");
         if(sök==null||sök.equals(""))
             return kundList; 
          
         sök=sök.trim();
         boolean hitta=false;
         while(scanner.hasNext()){
             //läser första raden
             firstLine=scanner.nextLine();
             firstLineArray=firstLine.split(",");
             //om personen hittas
             if(sök.equals(firstLineArray[0].trim())||sök.equalsIgnoreCase(firstLineArray[1].trim())){
                 
                 hitta=true;
                 if(scanner.hasNext()){
                     secondLine=scanner.nextLine().trim();
                     //kontrollerera om personen är fortfarande kund
                     if(DAYS.between(LocalDate.parse(secondLine),LocalDate.now())>366)
                         System.out.println(firstLine+" har inte betalat avgiften, sista betalda avgiften: "+secondLine);
                     else{
                         System.out.println(firstLine+" är nuvarande medlem sedan "+secondLine);
                         skapaKundList(firstLineArray[1].trim(),firstLineArray[0],secondLine,kundList);
                         
                     }
                 }
             } 
             
             else 
                 secondLine=scanner.nextLine();
                 
             
                 
         }
         if(!hitta) 
             System.out.println(sök+" är obehörig");
         
     }
        
     catch(IOException e){
         System.out.println("Det gick inte att läsa fil");
         e.printStackTrace();
     }
     catch(Exception e){
         System.out.println("Fel med systemet");
         e.printStackTrace();
     }
     
     
 }
    
 }
 
 /**
  * metod som skapar kontrollerar om ett objekt inte finns i listan i så fall addas det till listan
  * @param namn
  * @param personnummer
  * @param medlemsDatum
  * @param kundList
  * @return kund lista
  */
 
 public ArrayList<Kund> skapaKundList(String namn, String personnummer,String medlemsDatum,ArrayList<Kund> kundList){
     for(Kund kund1:kundList){
      if(kund1.getName().equals(namn)||kund1.getPersonnummer().equals(personnummer)){
          kund1.nyBesök();
          return kundList;
      }
     }
     Kund kund=new Kund(namn,personnummer,medlemsDatum);
     kund.nyBesök();
     kundList.add(kund);
     return kundList;
 }
 
 /**
  * metod som skriver till en fil kundernas träning
  * @param kundList
  * @param utFil 
  */
 
 public void sparaTillFil(ArrayList<Kund>kundList, String utFil){
     try(PrintWriter pw= new PrintWriter(new BufferedWriter(new FileWriter(utFil,true)));){

         for(Kund k:kundList)
             pw.println(k.visaFörTränare());
         
         System.out.println("Skrivning till fil är klar");
         
     }
     catch(Exception e){
         System.out.println("Fel");
         e.printStackTrace();
     }
 }


 }

