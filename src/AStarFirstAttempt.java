class AStarFirstAttempt {

    public int[][] traffic;
    public int[][] estimates;
    boolean[][] alreadyPassed;
    int size = 5;
    int startX = 0;
    int startY = 0;
    int endX = size-1;
    int endY = size-1;
    Path[][] paths;



    public static void main(String[] args) {
        new AStarFirstAttempt();
    }

    public AStarFirstAttempt() {
        traffic = new int[size][size];
        estimates = new int [size][size];
        alreadyPassed = new boolean[size][size];
        paths = new Path[size][size];
        for (int y = 0; y < traffic[0].length; y++) {
            for (int x = 0; x < traffic.length; x++) {
                traffic[x][y] = (int) (100 * Math.random());
                estimates[x][y] = 100*distance(startX,startY);
                alreadyPassed[x][y]=false;
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
        estimates[startX][startY]=traffic[startX][startY]+50*distance(startX,startY);
        System.out.println();
        findPaths(startX,startY,0);
        System.out.println(estimates[endX][endY]);
    }
    void findPaths(int x,int y,int scoreSoFar){
        alreadyPassed[x][y]=true;
        boolean spaceToMoveSouth = y < size - 1;
        boolean spaceToMoveEast = x < size - 1;
        boolean spaceToMoveWest = x>0;
        boolean spaceToMoveNorth = y>0;
        if(spaceToMoveNorth&&!alreadyPassed[x][y-1]){
            estimates[x][y-1]=traffic[x][y-1]+50*distance(x,y-1)+scoreSoFar;
        }
        if(spaceToMoveEast&&!alreadyPassed[x+1][y]){
            estimates[x+1][y]=traffic[x+1][y]+50*distance(x+1,y)+scoreSoFar;
        }
        if(spaceToMoveSouth&&!alreadyPassed[x][y+1]){
            estimates[x][y+1]=traffic[x][y+1]+50*distance(x,y+1)+scoreSoFar;
        }
        if(spaceToMoveWest&&!alreadyPassed[x-1][y]){
            estimates[x-1][y]=traffic[x-1][y]+50*distance(x-1,y)+scoreSoFar;
        }
        if(spaceToMoveNorth&&spaceToMoveEast&&!alreadyPassed[x+1][y-1]){
            estimates[x+1][y-1]=traffic[x+1][y-1]+50*distance(x+1,y-1)+scoreSoFar;
        }
        if(spaceToMoveSouth&&spaceToMoveEast&&!alreadyPassed[x+1][y+1]){
            estimates[x+1][y+1]=traffic[x+1][y+1]+50*distance(x+1,y+1)+scoreSoFar;
        }
        if(spaceToMoveSouth&&spaceToMoveWest&&!alreadyPassed[x-1][y+1]){
            estimates[x-1][y+1]=traffic[x-1][y+1]+50*distance(x-1,y+1)+scoreSoFar;
        }
        if(spaceToMoveNorth&&spaceToMoveWest&&!alreadyPassed[x-1][y-1]){
            estimates[x-1][y-1]=traffic[x-1][y-1]+50*distance(x-1,y-1)+scoreSoFar;
        }
        int bestNode=100*size;
        int bestA=-1;
        int bestB=-1;
        for(int a =0;a<size;a++){
            for(int b=0;b<size;b++){
                if(!alreadyPassed[a][b]&&estimates[a][b]<bestNode){
                    bestNode=estimates[a][b];
                    bestA=a;
                    bestB=b;
                }
            }
        }
        if(!(bestA==-1)&&!(bestB==-1)) {
            findPaths(bestA, bestB,scoreSoFar+traffic[bestA][bestB]);
        }
    }
    int distance(int x, int y){
        return Math.max(Math.abs(x-endX),Math.abs(y-endY));
    }
}