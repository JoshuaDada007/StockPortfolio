import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainClass {
    public static void main(String[] args) {
        Random rand = new Random();
        int rNumber = rand.nextInt(10);


       // System.out.println(rNumber);
    Integer[] list = new Integer[10];
    list[rNumber] = 69;
    for(Integer i : list) {
        System.out.println(i);
    }



    }


}
