public class AStarTryTwo {
    public int[][] traffic;
    int size =5;
    int startX = 0;
    int startY=0;
    int endX=size-1;
    int endY=size-1;
    Path[] openList;
    Path[] closedList;
    public static void main(String[] args) {
        new AStarTryTwo();
    }
    public AStarTryTwo() {
        traffic = new int[size][size];
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
            System.out.println();
        }
        openList=new Path[1];
        closedList = new Path[1];
        openList[0]=new Path("",traffic[startX][startY],traffic[startX][startY],startX,startY);
        while(openList.length!=0){
            int bestF = 100*distance(startX,startY);
            int bestIndex = 0;
            for(int x =0;x<openList.length;x++){
                if(openList[x].f<bestF) {
                    bestF = openList[x].f;
                    bestIndex = x;
                }
            }
            Path q = openList[bestIndex];
            removeFromOpenList(bestIndex);
        }
    }

    int distance(int x, int y){
        return Math.max(Math.abs(x-endX),Math.abs(y-endY));
    }
    void addToList(Path input,String openOrClosed){
        if(openOrClosed.equals("open")){
            Path[] temp = new Path[openList.length+1];
            System.arraycopy(openList,0,temp,0,openList.length);
            temp[temp.length-1]=input;
            openList=new Path[temp.length];
            System.arraycopy(temp,0,openList,0,temp.length);
        }
        if(openOrClosed.equals("closed")){
            Path[] temp = new Path[closedList.length+1];
            System.arraycopy(closedList,0,temp,0,closedList.length);
            temp[temp.length-1]=input;
            closedList=new Path[temp.length];
            System.arraycopy(temp,0,closedList,0,temp.length);
        }
    }
    void removeFromOpenList(int index){
        Path[] temp = new Path[openList.length-1];
        System.arraycopy(openList,0,temp,0,index);
        System.arraycopy(openList,index+1,temp,index,openList.length-index-1);
        openList=new Path[temp.length];
        System.arraycopy(temp,0,openList,0,temp.length);
    }

}

/* Pseudocode - source: https://www.geeksforgeeks.org/a-search-algorithm/
// A* Search Algorithm
1.  Initialize the open list
2.  Initialize the closed list
    put the starting node on the open
    list (you can leave its f at zero)
3.  while the open list is not empty
    a) find the node with the least f on
       the open list, call it "q"
    b) pop q off the open list

    c) generate q's 8 successors and set their
       parents to q

    d) for each successor
        i) if successor is the goal, stop search

        ii) else, compute both g and h for successor
          successor.g = q.g + distance between
                              successor and q
          successor.h = distance from goal to
          successor (This can be done using many
          ways, we will discuss three heuristics-
          Manhattan, Diagonal and Euclidean
          Heuristics)

          successor.f = successor.g + successor.h
        iii) if a node with the same position as
            successor is in the OPEN list which has a
           lower f than successor, skip this successor
        iV) if a node with the same position as
            successor  is in the CLOSED list which has
            a lower f than successor, skip this successor
            otherwise, add  the node to the open list
     end (for loop)

    e) push q on the closed list
    end (while loop)
 */