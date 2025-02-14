public class chairsPOTW {
    public static void main(String[] args) {
        new chairsPOTW();
    }
    public chairsPOTW(){
        for(int x =1;x<=100;x++){
            System.out.println("n="+x+": "+probability(x));
        }
    }
     double probability (int n){
        if(n==1){
            return 1;
        } else {
            double runningTotal = 0;
            for (int k = 1; k <= n - 1; k++) {
                runningTotal += probability(k);
            }
            return runningTotal / n;
        }
    }
}
