package recordСountryFile;
import java.util.Scanner;
import java.io.*;
import java.io.File;

class Strana implements Serializable {
    String name;
    double square;
}

public class task4{
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Scanner sc=new Scanner(System.in,"utf-8");
        File f=new File("E:\\MyFileSer");
        f.createNewFile();
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Strana strana = new Strana();
        System.out.println("Введите информацию о стране: ");
        System.out.print("Название страны => ");
        strana.name=sc.nextLine();
        System.out.print("Площадь страны => ");
        strana.square=sc.nextDouble();

        oos.writeObject(strana);

        oos.flush();
        oos.close();

        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream oin = new ObjectInputStream(fis);

        strana = (Strana) oin.readObject();
        System.out.println(" Название страны "+ strana.name);
        System.out.println(" ее площадь = "+ strana.square);

        oos.close();
    }
}

