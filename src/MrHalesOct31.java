public class MrHalesOct31 {

    public int[][] traffic;


    public static void main(String[] args) {
        new MrHalesOct31();
    }
    public MrHalesOct31() {
        traffic=new int[3][3];
        for(int x =0;x< traffic.length;x++){
            for(int y =0;y< traffic[0].length;y++){
                traffic[x][y] =(int)(100*Math.random());
                System.out.print(traffic[x][y]+" ");
            }
            System.out.println();
        }
        System.out.println();
        move(0, 0, 0,"");
    }
    public void move(int x, int y , int accruedCost, String pathString){
        accruedCost+=traffic[y][x];
        if(y== traffic.length-1&&x== traffic[0].length-1){
            System.out.println("Done. Cost = "+accruedCost+". Path was:"+pathString.substring(0,pathString.length()-1)+".");
        } else {
            boolean spaceToGoDown = y<traffic.length-1;
            boolean spaceToGoRight = x<traffic[0].length-1;
            if(spaceToGoRight){
                move(x+1, y, accruedCost,pathString+" right,");
            }
            if(spaceToGoDown){
                move(x, y+1, accruedCost,pathString+" down,");
            }
            if(spaceToGoRight&&spaceToGoDown) {
                move(x + 1, y + 1, accruedCost,pathString+" diagonal,");
            }
        }
    }
    //TODO: only print out the cheapest directions



    //in the beginning our goal is to traverse from the top left to the bottom right the cheapest way possible
}
