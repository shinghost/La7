import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.io.IOException;

public class Lab7_1 {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        try {
            File f1 = new File("E:\\MyCa.txt");
            if (f1.exists()) f1.delete();
            f1.createNewFile();
            RandomAccessFile rf = new RandomAccessFile(f1, "rw");
            rf.seek(0);
            System.out.print("Введите количество автомобилей для записи в файл\n"
                    + "=> ");
            int kol = sc.nextInt();

            for (int i = 0; i < kol; i++) {
                System.out.print("Введите модель " + (i + 1) + " автомобиля => ");
                String model = sc.next();
                rf.writeUTF(model);
                for (int j = 0; j < 20-model.length(); j++) {
                    rf.writeByte(1);
                }
                System.out.print("Введите его регистарционный номер => ");
                String reg_nom = sc.next();
                rf.writeUTF(reg_nom);
                for (int j = 0; j < 20-reg_nom.length(); j++) {
                    rf.writeByte(1);
                }

                System.out.print("Введите его год выпуска => ");
                int year = sc.nextInt();
                rf.writeInt(year);

                System.out.print("Введите его пробег => ");
                int probeg = sc.nextInt();
                rf.writeInt(probeg);

                System.out.print("Введите его стоимость => ");
                int cost = sc.nextInt();
                rf.writeInt(cost);
            }

            File f2 = new File("E:\\MyFiCar.txt");
            if (f2.exists()) f2.delete();
            f2.createNewFile();
            RandomAccessFile rf2 = new RandomAccessFile(f2, "rw");
            rf.seek(0);
            rf2.seek(0);
            int k = 0;
            for (int i = 0; i < kol; i++) {
                rf.seek(70 *i);
                String model = rf.readUTF();
                rf.seek(70*i+22);
                String reg_nom = rf.readUTF();
                rf.seek(70*i+44);
                int year = rf.readInt();
                rf.seek(70*i+66);
                int probeg = rf.readInt();
                rf.seek(70*i+88);
                int cost = rf.readInt();

                if (year > 2009) {
                    System.out.println("\nавтомобиль с годом выпуска позднее 2009 года: ");
                    System.out.println("Модель \t\t регистр. номер \t\t год выпуска \t\t пробег \t\t Стоимость");

                    rf2.writeUTF(model);
                    for (int j = 0; j < 20-model.length(); j++) {
                        rf2.writeByte(1);
                    }
                    rf2.writeUTF(reg_nom);
                    for (int j = 0; j < 20-reg_nom.length(); j++) {
                        rf2.writeByte(1);
                    }
                    rf2.writeInt(year);
                    k++;
                    rf2.writeInt(probeg);
                    k++;
                    rf2.writeInt(cost);
                    k++;

                }
            }
            System.out.println(k);
            rf2.seek(0);
            for (int i = 0; i < k; i++) {
                rf2.seek(70*i);
                String model = rf2.readUTF();
                rf2.seek(70*i+22);
                String reg_nom = rf2.readUTF();
                rf2.seek(70*i+44);
                int year = rf2.readInt();
                rf2.seek(70*i+66);
                int probeg = rf2.readInt();
                rf2.seek(70*i+88);
                int cost = rf2.readInt();
                System.out.println(model + "\t\t\t\t" + reg_nom + "\t\t\t\t" + year + "\t\t\t" + probeg + "\t\t\t\t" + cost);

            }
            rf.close();
            rf2.close();

        } catch (IOException e) {
            System.out.println("\n End of file " + e);
        }
    }
}

