public class PathFindingComparison {
    int[][] traffic;
    int size = 20;
    boolean printUnsolvedTraffic = false;
    int startX =0;
    int startY=0;
    int endX=size-1;
    int endY=size-1;

    public PathFindingComparison() {
        initializeTraffic(size,printUnsolvedTraffic,startX,startY,endX,endY);
        new AStar(traffic,startX,startY,endX,endY,40,size);
        System.out.println();
        new AStar(traffic,startX,startY,endX,endY,0,size);
        System.out.println();
        System.out.println("The most direct path would have an average cost of "+(size*50)+".");
    }
    public static void main(String[] args) {
        new PathFindingComparison();
    }
    void initializeTraffic(int size, boolean print,int startX,int startY,int endX,int endY){
        traffic = new int[size][size];
        if(print) {
            for (int y = 0; y < traffic[0].length; y++) {
                for (int x = 0; x < traffic.length; x++) {
                    traffic[x][y] = (int) (100 * Math.random());
                    if (x == startX && y == startY) {
                        System.out.print(TextColors.GREEN + traffic[x][y] + TextColors.RESET + " ");
                    } else if (x == endX && y == endY) {
                        System.out.print(TextColors.RED + traffic[x][y] + TextColors.RESET + " ");
                    } else {
                        System.out.print(traffic[x][y] + " ");
                    }
                    if (traffic[x][y] < 10) {
                        System.out.print(" ");
                    }
                }
            }
        } else{
            for (int y = 0; y < traffic[0].length; y++) {
                for (int x = 0; x < traffic.length; x++) {
                    traffic[x][y] = (int) (100 * Math.random());
                }
            }
        }
    }
}