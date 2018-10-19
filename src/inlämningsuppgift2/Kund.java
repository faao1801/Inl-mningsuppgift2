/*
 * Java Utveckling 18
 */

package inlämningsuppgift2;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


public class Kund {
 String namn;
 String personnummer;
 String medlemsDatum;
 ArrayList<String>besökDatum=new ArrayList<>();
 int antalBesök=0;
 
 public Kund(String namn,String personnummer, String medlemsDatum){
     this.namn=namn;
     this.personnummer=personnummer;
     this.medlemsDatum=medlemsDatum;
 }
 
 public void nyBesök(){
     antalBesök++;
     besökDatum.add(LocalDate.now().toString());
 }
 public String getName(){
     return namn;
 }
 public String getPersonnummer(){
     return personnummer;
 }
 public String visaFörTränare(){
     String d="";
     for(String s:besökDatum)
         d+=s+" ";
     return namn+" "+personnummer+" tränade "+antalBesök+" gånger :"+d;
 }
}
