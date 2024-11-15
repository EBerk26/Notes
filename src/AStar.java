//TODO: ensure that all children are added to the open list before the next parent is chosen
public class AStar {
    Path[] openList;
    Path[] closedList;
    Path[] completeList;
    long startTime;

    public static void main(String[] args) {
    }
    //in general higher is faster but worse score. 0 is guaranteed to be perfect, 99 puts a really heavy emphasis on the future so will lean towards the most direct path
    public AStar(int[][] traffic, int startX, int startY, int endX, int endY,int distanceCoefficient,int size) {
        openList=new Path[1];
        closedList = new Path[0];
        completeList = new Path[0];
        openList[0]=new Path(traffic[startX][startY],traffic[startX][startY],startX,startY);
        startTime = System.currentTimeMillis();
        while(openList.length!=0){
            int bestF = 100*distance(startX,startY,endX,endY);
            int bestIndex = 0;
            for(int x =0;x<openList.length;x++){
                if(openList[x].f<bestF) {
                    bestF = openList[x].f;
                    bestIndex = x;
                }
            }
            Path q = openList[bestIndex];
            removeFromOpenList(bestIndex);
            //System.out.println("Checking: ("+(q.x)+","+(q.y)+")");
            Path[] successor = new Path[9];
            for(int x =1;x<=8;x++){
                successor[x]=new Path();
                successor[x].setParent(q,q.x,q.y);
                if(x==1){
                    successor[x].x=q.x;
                    successor[x].y=q.y-1;
                    if(successor[x].x==endX&&successor[x].y==endY){
                        printOutPath(successor[x],traffic,startX,startY,endX,endY,distanceCoefficient);
                        break;
                    } else if (successor[x].y>0){
                        successor[x].g=q.g+traffic[successor[x].x][successor[x].y];
                        successor[x].h=distanceCoefficient*distance(successor[x].x,successor[x].y,endX,endY);
                        successor[x].f=successor[x].g+successor[x].h;
                        boolean betterSolutionExistsForSamePlace = doesBetterSolutionExistForSamePlace(successor, x);
                        if(!betterSolutionExistsForSamePlace){
                            addToList(successor[x],"open");
                        }
                    }
                } else if (x==2){
                    successor[x].x=q.x+1;
                    successor[x].y=q.y-1;
                    if(successor[x].x==endX&&successor[x].y==endY){
                        printOutPath(successor[x],traffic,startX,startY,endX,endY,distanceCoefficient);
                        break;
                    } else if (successor[x].x<size-1&&successor[x].y>0) {
                        successor[x].g = q.g + traffic[successor[x].x][successor[x].y];
                        successor[x].h = distanceCoefficient * distance(successor[x].x, successor[x].y,endX,endY);
                        successor[x].f = successor[x].g + successor[x].h;
                        boolean betterSolutionExistsForSamePlace = doesBetterSolutionExistForSamePlace(successor, x);
                        if (!betterSolutionExistsForSamePlace) {
                            addToList(successor[x], "open");
                        }
                    }
                } else if (x==3){
                    successor[x].x=q.x+1;
                    successor[x].y=q.y;
                    if(successor[x].x==endX&&successor[x].y==endY){
                        printOutPath(successor[x],traffic,startX,startY,endX,endY,distanceCoefficient);
                        break;
                    } else if (successor[x].x<size-1) {
                        successor[x].g = q.g + traffic[successor[x].x][successor[x].y];
                        successor[x].h = distanceCoefficient * distance(successor[x].x, successor[x].y,endX,endY);
                        successor[x].f = successor[x].g + successor[x].h;
                        boolean betterSolutionExistsForSamePlace = doesBetterSolutionExistForSamePlace(successor, x);
                        if (!betterSolutionExistsForSamePlace) {
                            addToList(successor[x], "open");
                        }
                    }
                } else if (x==4){
                    successor[x].x=q.x+1;
                    successor[x].y=q.y+1;
                    if(successor[x].x==endX&&successor[x].y==endY){
                        printOutPath(successor[x],traffic,startX,startY,endX,endY,distanceCoefficient);
                        break;
                    } else if (successor[x].y<size-1&&successor[x].x<size-1) {
                        successor[x].g = q.g + traffic[successor[x].x][successor[x].y];
                        successor[x].h = distanceCoefficient * distance(successor[x].x, successor[x].y,endX,endY);
                        successor[x].f = successor[x].g + successor[x].h;
                        boolean betterSolutionExistsForSamePlace = doesBetterSolutionExistForSamePlace(successor, x);
                        if (!betterSolutionExistsForSamePlace) {
                            addToList(successor[x], "open");
                        }
                    }
                } else if (x==5){
                    successor[x].x=q.x;
                    successor[x].y=q.y+1;
                    if(successor[x].x==endX&&successor[x].y==endY){
                        printOutPath(successor[x],traffic,startX,startY,endX,endY,distanceCoefficient);
                        break;
                    } else if (successor[x].y<size-1) {
                        successor[x].g = q.g + traffic[successor[x].x][successor[x].y];
                        successor[x].h = distanceCoefficient * distance(successor[x].x, successor[x].y,endX,endY);
                        successor[x].f = successor[x].g + successor[x].h;
                        boolean betterSolutionExistsForSamePlace = doesBetterSolutionExistForSamePlace(successor, x);
                        if (!betterSolutionExistsForSamePlace) {
                            addToList(successor[x], "open");
                        }
                    }
                } else if(x==6){
                    successor[x].x=q.x-1;
                    successor[x].y=q.y+1;
                    if(successor[x].x==endX&&successor[x].y==endY){
                        printOutPath(successor[x],traffic,startX,startY,endX,endY,distanceCoefficient);
                        break;
                    } else if (successor[x].x>0&&successor[x].y<size-1) {
                        successor[x].g = q.g + traffic[successor[x].x][successor[x].y];
                        successor[x].h = distanceCoefficient * distance(successor[x].x, successor[x].y,endX,endY);
                        successor[x].f = successor[x].g + successor[x].h;
                        boolean betterSolutionExistsForSamePlace = doesBetterSolutionExistForSamePlace(successor, x);
                        if (!betterSolutionExistsForSamePlace) {
                            addToList(successor[x], "open");
                        }
                    }
                } else if (x==7){
                    successor[x].x=q.x-1;
                    successor[x].y=q.y;
                    if(successor[x].x==endX&&successor[x].y==endY){
                        printOutPath(successor[x],traffic,startX,startY,endX,endY,distanceCoefficient);
                        break;
                    } else if (successor[x].x>0) {
                        successor[x].g = q.g + traffic[successor[x].x][successor[x].y];
                        successor[x].h = distanceCoefficient * distance(successor[x].x, successor[x].y,endX,endY);
                        successor[x].f = successor[x].g + successor[x].h;
                        boolean betterSolutionExistsForSamePlace = doesBetterSolutionExistForSamePlace(successor, x);
                        if (!betterSolutionExistsForSamePlace) {
                            addToList(successor[x], "open");
                        }
                    }
                } else{
                    successor[x].x=q.x-1;
                    successor[x].y=q.y-1;
                    if(successor[x].x==endX&&successor[x].y==endY){
                        printOutPath(successor[x],traffic,startX,startY,endX,endY,distanceCoefficient);
                        break;
                    } else if (successor[x].x>0&&successor[x].y>0) {
                        successor[x].g = q.g + traffic[successor[x].x][successor[x].y];
                        successor[x].h = distanceCoefficient * distance(successor[x].x, successor[x].y,endX,endY);
                        successor[x].f = successor[x].g + successor[x].h;
                        boolean betterSolutionExistsForSamePlace = doesBetterSolutionExistForSamePlace(successor, x);
                        if (!betterSolutionExistsForSamePlace) {
                            addToList(successor[x], "open");
                        }
                    }
                }
            }
            addToList(q,"closed");
            //find 3 solutions before printing out the best one
            if(completeList.length>=10){
                bestF = 100*distance(startX,startY,endX,endY);
                bestIndex = 0;
                for(int x =0;x<completeList.length;x++){
                    if(completeList[x].f<bestF) {
                        bestF = completeList[x].f;
                        bestIndex = x;
                    }
                }
                printOutPath(completeList[bestIndex],traffic,startX,startY,endX,endY,distanceCoefficient);
            }
        }
    }

    private boolean doesBetterSolutionExistForSamePlace(Path[] successor, int x) {
        boolean betterSolutionExistsForSamePlace = false;
        for (int y = 0; y < openList.length; y++) {
            if ((((openList[y].x == successor[x].x && openList[y].y == successor[x].y && openList[y].f < successor[x].f)) || (y<closedList.length && (closedList[y].x == successor[x].x && closedList[y].y == successor[x].y && closedList[y].f < successor[x].f)))) {
                betterSolutionExistsForSamePlace = true;
                break;
            }
        }
        return betterSolutionExistsForSamePlace;
    }

    int distance(int x, int y,int endX,int endY){
        return Math.max(Math.abs(x-endX),Math.abs(y-endY));
    }
    void addToList(Path input,String list){
        if(list.equals("open")){
            Path[] temp = new Path[openList.length+1];
            System.arraycopy(openList,0,temp,0,openList.length);
            temp[temp.length-1]=input;
            openList=new Path[temp.length];
            System.arraycopy(temp,0,openList,0,temp.length);
        }
        if(list.equals("closed")){
            Path[] temp = new Path[closedList.length+1];
            System.arraycopy(closedList,0,temp,0,closedList.length);
            temp[temp.length-1]=input;
            closedList=new Path[temp.length];
            System.arraycopy(temp,0,closedList,0,temp.length);
        }
        if(list.equals("complete")){
            Path[] temp = new Path[completeList.length+1];
            System.arraycopy(completeList,0,temp,0,completeList.length);
            temp[temp.length-1]=input;
            completeList=new Path[temp.length];
            System.arraycopy(temp,0,completeList,0,temp.length);
        }
    }
    void removeFromOpenList(int index){
        Path[] temp = new Path[openList.length-1];
        System.arraycopy(openList,0,temp,0,index);
        System.arraycopy(openList,index+1,temp,index,openList.length-index-1);
        openList=new Path[temp.length];
        System.arraycopy(temp,0,openList,0,temp.length);
    }
    void printOutPath(Path input,int[][]traffic,int startX,int startY,int endX,int endY,int distanceCoefficient){
        openList=new Path[0];
        int[][] traversedSquares;
        traversedSquares = new int[input.generation+1][2];
        Path backtracking = input;
        int i =0;
        while(backtracking.generation>0){
            traversedSquares[i][0]=backtracking.x;
            traversedSquares[i][1]=backtracking.y;
            backtracking=backtracking.parent;
            i++;
        }
        traversedSquares[i][0]=backtracking.x;
        traversedSquares[i][1]=backtracking.y;
        for(int y =0;y< traffic[0].length;y++){
            for(int x =0;x< traffic.length;x++){
                if(x==startX&&y==startY){
                    System.out.print(TextColors.GREEN+traffic[x][y]+TextColors.RESET+" ");
                } else if(x==endX&&y==endY){
                    System.out.print(TextColors.RED+traffic[x][y]+TextColors.RESET+" ");
                } else{
                    boolean printedOutAYellowNumber = false;
                    for (int[] traversedSquare : traversedSquares) {
                        if (x == traversedSquare[0] && y == traversedSquare[1]) {
                            System.out.print(TextColors.YELLOW + traffic[x][y] + " " + TextColors.RESET);
                            printedOutAYellowNumber = true;
                        }
                    }
                    if(!printedOutAYellowNumber) {
                        System.out.print(traffic[x][y] + " ");
                    }
                }
                if(traffic[x][y]<10){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.print("Cost: "+((input.parent.g+traffic[input.x][input.y]))+". ");

        if (distanceCoefficient != 0) {
            if(distanceCoefficient==50) {
                System.out.println("A* solution took " + (System.currentTimeMillis() - startTime) + " milliseconds.");
            } else {
                System.out.println("A* ("+ distanceCoefficient+") solution took " + (System.currentTimeMillis() - startTime) + " milliseconds.");
            }
        } else {
            System.out.println("Dijkstra's Algorithm solution took "+(System.currentTimeMillis()-startTime)+" milliseconds.");
        }
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