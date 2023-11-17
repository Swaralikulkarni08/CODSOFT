import java.util.Random;
import java.util.Scanner;

public class NumberGame{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num;
        Random random=new Random();
        int r=random.nextInt(100)+1;
        System.out.println("Enter number:");
        num=sc.nextInt();
        if(num>r){
            System.out.println("high");
        }else if (num<r) {
             System.out.println("low");
        }else{
             System.out.println("correct");
        }
        sc.close();
    }
}