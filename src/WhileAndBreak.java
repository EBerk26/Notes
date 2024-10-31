import java.util.Scanner;
public class WhileAndBreak {
    public static void main(String[] args) {
        new WhileAndBreak();
    }
    WhileAndBreak() {
        mostFreeDonuts();
        dunkinOrderV2();
    }
    void freeDonutWheel(){
        System.out.println("You get "+(int)(13*Math.random())+" free donuts.");
    }

    void moreFreeDonuts(int minimumAmount){
        int counter = 1;
        int freeDonuts = 0;
        int temp;
        while(counter<10){
            temp = (int)(13*Math.random());
            if(freeDonuts<temp){
                freeDonuts=temp;
            }
            if(freeDonuts>=minimumAmount){
                break;
            }
            counter++;
        }
        if(counter!=1) {
            System.out.println("After " + counter + " spins, you get " + freeDonuts + " free donuts.");
        } else{
            System.out.println("After 1 spin, you get " + freeDonuts + " free donuts.");
        }
    }
    void mostFreeDonuts(){
        int freeDonuts = 0;
        int counter = 1;
        while(freeDonuts!=12){
            freeDonuts=(int)(Math.random()*13);
            counter++;
        }
        if(counter!=1&&counter<23) {
            System.out.println("After " + counter+ " spins, you get " + freeDonuts + " free donuts.");
        } else if (counter>=23){ //There's an 85.3% chance that it takes fewer than 23 spins
            System.out.println("After " + counter+ " spins, you FINALLY get " + freeDonuts + " free donuts.");
        } else {
            System.out.println("Wow! You're very luck! After 1 spin, you get " + freeDonuts + " free donuts.");
        }
    }
    int dunkinOrder(int numPeople){
        int numOfBoxes = 0;
        if(numPeople%12>0){
            numOfBoxes++;
        }
        while(numPeople>=12){
            numPeople-=12;
            numOfBoxes++;
        }
        return numOfBoxes;
        /* how I would've done this:
        if(numPeople%12!=0){
            return((int)(numPeople/12)+1);
            } else {
            return numPeople/12;
            }
         */
    }
    void dunkinOrderV2(){
        //this method allows to input num of people
        //prevents dunkin from running out of donuts
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter how many donuts you want to order");
        int numPeople = scanner.nextInt();
        int numOfBoxes = 0;
        int boxesInSupply = 34;
        while(numPeople>=12){
            numPeople-=12;
            numOfBoxes++;
            if(numOfBoxes>=33){
                System.out.println("Sorry, we're out of donuts.");
                break;
            }
        }
        if(numPeople>0){
            numOfBoxes++;
        }
        if(numOfBoxes!=1) {
            System.out.println("We'll give you " + numOfBoxes + " boxes.");
        } else {
            System.out.println("We'll give you " + numOfBoxes + " box.");
        }
        scanner.close();
    }
}