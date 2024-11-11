public class MrHalesTrafficChallenge {

    public int[][] traffic;
    int bestCost;
    String bestRouteString;
    int size=10;
    boolean[][] bestPath;



    public static void main(String[] args) {
        //new MrHalesTrafficChallenge();
    }
    public MrHalesTrafficChallenge(int[][]traffic,int size,int startX,int startY,int endX,int endY) {
        bestPath = new boolean[size][size];
        if(Math.abs(startX-endX)>Math.abs(startY-endY)){
            bestCost=99*Math.abs(startX-endX);
        } else{
            bestCost=99*Math.abs(startY-endY);
        }
        boolean[][] falseArray = new boolean[size][size];
        for(int x = 0;x< falseArray.length;x++){
            for(int y = 0;y<falseArray[0].length;y++){
                falseArray[x][y]=false;
            }
        }
        long startTime = System.currentTimeMillis();
        move(startX,startY,0,"",falseArray,traffic,endX,endY);
        for(int y =0;y< traffic[0].length;y++){
            for(int x =0;x< traffic.length;x++){
                if(x==startX&&y==startY){
                    System.out.print(TextColors.GREEN+traffic[x][y]+TextColors.RESET+" ");
                } else if(x==endX&&y==endY){
                    System.out.print(TextColors.RED+traffic[x][y]+TextColors.RESET+" ");
                } else if(bestPath[y][x]){
                    System.out.print(TextColors.YELLOW+traffic[x][y] + " "+TextColors.RESET);
                } else{
                    System.out.print(traffic[x][y]+" ");
                }
                if(traffic[x][y]<10){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("Cost: "+bestCost+". Exhaustive solution took "+(System.currentTimeMillis()-startTime)+" millisecond(s).");
    }
    public void move(int x, int y , int accruedCost, String pathString,boolean[][] placesVisited,int[][]traffic, int endX,int endY){
        accruedCost+=traffic[x][y];
        if(accruedCost<bestCost) {//if your cost at any point can be beaten, don't continue down that path.
            if (y == endY && x == endX) {
                bestCost = accruedCost;
                bestRouteString ="Done. Cost = " + accruedCost + ". Path was:" + pathString.substring(0, pathString.length() - 1) + ".";
                for(int a =0;a< placesVisited.length;a++){
                    System.arraycopy(placesVisited[a], 0, bestPath[a], 0, placesVisited[0].length);
                }
            }else {
                boolean[][] newPlacesVisited = new boolean[placesVisited.length][placesVisited[0].length];
                for(int a =0;a< placesVisited.length;a++){
                    System.arraycopy(placesVisited[a], 0, newPlacesVisited[a], 0, placesVisited[0].length);
                }
                newPlacesVisited[y][x]=true;

                boolean spaceToMoveSouth = y < traffic.length - 1;
                boolean spaceToMoveEast = x < traffic[0].length - 1;
                boolean spaceToMoveWest = x>0;
                boolean spaceToMoveNorth = y>0;
                boolean targetIsNorth = y>endY;
                boolean targetIsEast = x<endX;
                boolean targetIsSouth = y<endY;
                boolean targetIsWest = x>endX;
                if(targetIsNorth&&targetIsEast){
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                } else if (targetIsSouth&&targetIsEast){
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                } else if (targetIsSouth&&targetIsWest){
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                } else if (targetIsNorth&&targetIsWest){
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                } else if (targetIsNorth){
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                } else if (targetIsEast){
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                } else if (targetIsSouth){
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                } else if (targetIsWest){
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest,endX,endY,traffic);
                }
            }
        }
    }


    void moveNorth(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west,int endX,int endY,int[][]traffic){
        if(north&&!placesVisited[y-1][x]) {
            move(x, y - 1, accruedCost, pathString + " north,", placesVisited,traffic,endX,endY);
        }
    }
    void moveEast(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west,int endX,int endY,int[][]traffic){
        if(east&&!placesVisited[y][x+1]) {
            move(x+1, y, accruedCost, pathString + " east,", placesVisited,traffic,endX,endY);
        }
    }
    void moveSouth(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west,int endX,int endY,int[][]traffic){
        if(south&&!placesVisited[y+1][x]) {
            move(x, y + 1, accruedCost, pathString + " south,", placesVisited,traffic,endX,endY);
        }
    }
    void moveWest(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west,int endX,int endY,int[][]traffic){
        if(west&&!placesVisited[y][x-1]) {
            move(x-1, y, accruedCost, pathString + " west,", placesVisited,traffic,endX,endY);
        }
    }
    void moveNorthEast(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west,int endX,int endY,int[][]traffic){
        if(east&&north&&!placesVisited[y-1][x+1]) {
            move(x+1, y-1, accruedCost, pathString + " northeast,", placesVisited,traffic,endX,endY);
        }
    }
    void moveSouthEast(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west,int endX,int endY,int[][]traffic){
        if(south&&east&&!placesVisited[y+1][x+1]) {
            move(x+1, y+1, accruedCost, pathString + " southeast,", placesVisited,traffic,endX,endY);
        }
    }
    void moveSouthWest(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west,int endX,int endY,int[][]traffic){
        if(south&&west&&!placesVisited[y+1][x-1]) {
            move(x-1, y+1, accruedCost, pathString + " southwest,", placesVisited,traffic,endX,endY);
        }
    }
    void moveNorthWest(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west,int endX,int endY,int[][]traffic){
        if(north&&west&&!placesVisited[y-1][x-1]) {
            move(x-1, y-1, accruedCost, pathString + " northwest,", placesVisited,traffic,endX,endY);
        }
    }

}
/* Potential ways to make this more efficient - note that none of these will be perfect anymore due to a lack of exhaustive search
Make the matrix smaller by turning each 2x2 into a sum of all the parts, finding the best path in a matrix 1/4 the size, and then expanding back out.
split into chunks, find the best path through each of them, and string all the chunks together - EG in 9x9, find best path for top left 3x3, best path for middle 3x3, best path for bottom right 3x3 and string them together
create a new matrix that's sort of like a heat map - where every cell is the sum of all the cells around it or something
work backwards and forwards
start prioritizing earlier - if we realize that a certain path is not so great, we don't need to exhaust it.
 */

