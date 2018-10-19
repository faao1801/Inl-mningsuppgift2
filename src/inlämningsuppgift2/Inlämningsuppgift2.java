/*
 * Java Utveckling 18
 */

package inlämningsuppgift2;



import java.util.ArrayList;




public class Inlämningsuppgift2 {
 Inlämningsuppgift2() {
     FileReadWrite rw= new FileReadWrite();
     String inFil="src/inlämningsuppgift2/customers.txt";
    
     String utFil="src/inlämningsuppgift2/SparadeKunder1.txt";
     //skapa en tom kund lista
     ArrayList<Kund> kundList=new ArrayList<>();
     //sök i filen ochreturnerar en lista med dem kunder som hittas
     kundList=rw.readAndSearchInFile(inFil);
     //skriv info från kundlista till en fil
     rw.sparaTillFil(kundList, utFil);
 }
     
   
    public static void main(String[] args) {
        Inlämningsuppgift2  inl= new Inlämningsuppgift2();
        
    }

}
