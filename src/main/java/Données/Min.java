package Données;



import java.io.FileInputStream;

import java.io.ObjectInputStream;



public class Min {

    public static void main(String[] args){

        //Serialisation();
       Deserialisation();
    }


    private static void Deserialisation(){

    try{

        FileInputStream fis = new FileInputStream("//Users//MatteoQ//Documents//Serser//Testser.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Racine p = (Racine) ois.readObject();
        System.out.println(p);
    }
    catch (Exception e){
        e.printStackTrace();
    }

    }
}
