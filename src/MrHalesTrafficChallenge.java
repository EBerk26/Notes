public class MrHalesTrafficChallenge {

    public int[][] traffic;
    int bestCost;
    String bestRoute;
    int size=4;
    int endX = 0;
    int endY = 0;
    int startX = size-1;
    int startY = size-1;



    public static void main(String[] args) {
        new MrHalesTrafficChallenge();
    }
    public MrHalesTrafficChallenge() {
        traffic=new int[size][size];
        if(Math.abs(startX-endX)>Math.abs(startY-endY)){
            bestCost=99*Math.abs(startX-endX);
        } else{
            bestCost=99*Math.abs(startY-endY);
        }
        for(int y =0;y< traffic[0].length;y++){
            for(int x =0;x< traffic.length;x++){
                traffic[x][y] =(int)(100*Math.random());
                System.out.print(traffic[x][y]+" ");
            }
            System.out.println();
        }
        System.out.println();
        boolean[][] falseArray = new boolean[size][size];
        for(int x = 0;x< falseArray.length;x++){
            for(int y = 0;y<falseArray[0].length;y++){
                falseArray[x][y]=false;
            }
        }
        long startTime = System.currentTimeMillis();
        move(startX, startY, 0,"",falseArray);
        System.out.println(bestRoute);
        System.out.println("This took "+(System.currentTimeMillis()-startTime)+" millisecond(s).");
    }
    public void move(int x, int y , int accruedCost, String pathString,boolean[][] placesVisited){
        accruedCost+=traffic[x][y];
        if(accruedCost<bestCost) {//if your cost at any point can be beaten, don't continue down that path.
            if (y == endY && x == endX) {
                bestCost = accruedCost;
                bestRoute="Done. Cost = " + accruedCost + ". Path was:" + pathString.substring(0, pathString.length() - 1) + ".";
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
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                } else if (targetIsSouth&&targetIsEast){
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                } else if (targetIsSouth&&targetIsWest){
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                } else if (targetIsNorth&&targetIsWest){
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                } else if (targetIsNorth){
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                } else if (targetIsEast){
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                } else if (targetIsSouth){
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                } else if (targetIsWest){
                    moveWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorthWest(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouth(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveNorthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveSouthEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                    moveEast(x,y,accruedCost,pathString,newPlacesVisited,spaceToMoveNorth,spaceToMoveEast,spaceToMoveSouth,spaceToMoveWest);
                }
            }
        }
    }


    void moveNorth(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west){
        if(north&&!placesVisited[y-1][x]) {
            move(x, y - 1, accruedCost, pathString + " north,", placesVisited);
        }
    }
    void moveSouth(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west){
        if(south&&!placesVisited[y+1][x]) {
            move(x, y + 1, accruedCost, pathString + " south,", placesVisited);
        }
    }
    void moveWest(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west){
        if(west&&!placesVisited[y][x-1]) {
            move(x - 1, y, accruedCost, pathString + " west,", placesVisited);
        }
    }
    void moveEast(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west){
        if(east&&!placesVisited[y][x+1]) {
            move(x + 1, y, accruedCost, pathString + " east,", placesVisited);
        }
    }
    void moveNorthEast(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west){
        if(north&&east&&!placesVisited[y-1][x+1]) {
            move(x + 1, y - 1, accruedCost, pathString + " northeast,", placesVisited);
        }
    }
    void moveSouthEast(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west){
        if(south&&east&&!placesVisited[y+1][x+1]) {
            move(x + 1, y + 1, accruedCost, pathString + " southeast,", placesVisited);
        }
    }
    void moveSouthWest(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west){
        if(south&&west&&!placesVisited[y+1][x-1]) {
            move(x - 1, y + 1, accruedCost, pathString + " southwest,", placesVisited);
        }
    }
    void moveNorthWest(int x, int y, int accruedCost,String pathString,boolean[][] placesVisited,boolean north, boolean east, boolean south, boolean west){
        if(north&&west&&!placesVisited[y-1][x-1]) {
            move(x - 1, y - 1, accruedCost, pathString + " northwest,", placesVisited);
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

/* Eli - expected expansions to the question
Any two starting points
Moving up, left, and other diagonals - ensure not to bounce back and forth infinitely

 */

