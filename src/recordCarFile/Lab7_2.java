package recordCarFile;
import java.util.Scanner;
import java.io.*;
import java.io.File;

class Car implements Serializable {
    String model, reg_nom;
    int year, probeg, cost;

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", reg_nom=" + reg_nom +
                ", year=" + year +
                ", probeg=" + probeg +
                ", cost=" + cost +
                '}';
    }
}

public class Lab7_2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in, "utf-8");
        System.out.println("Введите количество автомобилей для записи в файл\n"
                + "=> ");
        int count = sc.nextInt();
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        FileOutputStream fos2 = null;
        ObjectOutputStream oos2 = null;
        FileInputStream fis = null;
        ObjectInputStream oin = null;
        FileInputStream fis2 = null;
        ObjectInputStream oin2 = null;

        try {
            File f = new File("E:\\MyFileSer");
            f.createNewFile();
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            sc.nextLine();
            for (int i = 0; i < count; i++) {
                Car car = new Car();
                System.out.print("Введите модель автомобиля => ");
                car.model = sc.nextLine();
                System.out.print("Введите его регистарционный номер =>");
                car.reg_nom = sc.nextLine();
                System.out.print("Введите его год выпуска => ");
                car.year = sc.nextInt();
                System.out.print("Введите его пробег => ");
                car.probeg = sc.nextInt();
                System.out.print("Введите его стоимость => ");
                car.cost = sc.nextInt();

                sc.nextLine();
                oos.writeObject(car);
            }

            fis = new FileInputStream(f);
            oin = new ObjectInputStream(fis);
            Car auto = null;

            File f2 = new File("E:\\MyFileSer2");
            fos2 = new FileOutputStream(f2);
            oos2 = new ObjectOutputStream(fos2);
            int k = 0;
            System.out.println("\nавтомобиль с годом выпуска позднее 2009 года: ");
            for (int i = 0; i < count; i++) {
                auto = (Car) oin.readObject();
                if (auto.year > 2009) {
                    oos2.writeObject(auto);
                    System.out.println(auto);
                    k++;
                }
            }
            fis2 = new FileInputStream(f2);
            oin2 = new ObjectInputStream(fis2);
            Car carTwo = null;
            if (k > 0) {
                for (int i = 0; i < k; i++) {
                    carTwo = (Car) oin.readObject();
                    System.out.println(carTwo);
                }
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            oos.flush();
            oos.close();
            oos2.flush();
            oos2.close();
            oin.close();
            fos.close();
            fis.close();
            oin2.close();
            fos2.close();
            fis2.close();
        }
    }
}

