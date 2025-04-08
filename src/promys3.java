public class promys3 {

    //returns a list of numbers coprime to x that are between x/2 and x
    public static void main(String[] args) {
        new promys3();
    }

    public promys3() {
        int x = 25;
        for(int i = (x/2)+1;i<x;i++){
            if(gcd(i,x)==1){
                System.out.println(i);
            }
        }

    }
    int gcd(int a, int b) {
        int result = Math.min(a, b);
        while (result > 0) {
            if (a % result == 0 && b % result == 0) {
                break;
            }
            result--;
        }
        return result;
    }
}
