package DAL.DAO;

import java.io.*;
import java.util.Scanner;

public class CSVDAO {
    private Scanner sc;

    public CSVDAO() throws FileNotFoundException {
        sc = new Scanner(new File("src/Resources/Files/ArlaKPI.csv"));
        sc.useDelimiter(",");
    }


    public void printCSV(){
        try {
            sc = new Scanner(new File("src/Resources/Files/ArlaKPI.csv"));
            sc.useDelimiter(",");
            while(sc.hasNext()){
                System.out.print(sc.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }





}



