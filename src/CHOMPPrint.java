import java.util.ArrayList;
import java.util.Arrays;

public class CHOMPPrint {

    public static void main(String[] args) {
        new CHOMPPrint();
    }

    int boardLength = 8;
    ArrayList<ArrayList<Integer>> gameStates = new ArrayList<>();
    boolean[] isWinState;
    int totalGameStates;

    public CHOMPPrint() {
        final long startTime = System.currentTimeMillis();
        listGameStates(new ArrayList<>(), boardLength);
        totalGameStates = gameStates.size();
        isWinState = new boolean[totalGameStates];
        isWinState[0] = true;
        isWinState[1] = false;
        orderedPair[] listOfMoves = new orderedPair[totalGameStates];
        listOfMoves[0] = new orderedPair(-1,-1);
        listOfMoves[1] = new orderedPair(0,0);
        for(int x=2; x<totalGameStates;x++){
            listOfMoves[x] = chooseBestMove(gameStates.get(x),x);
        }
        for(int x = 0; x<listOfMoves.length;x++){
            System.out.println(gameStates.get(x));
            System.out.println("Is Win: "+isWinState[x]);
            listOfMoves[x].printInfo();
            System.out.println();
        }
        System.out.println("Solving "+boardLength+"x"+boardLength+" CHOMP took "+(System.currentTimeMillis()-startTime)/1000+" second(s).");
    }

    void listGameStates(ArrayList<Integer> previousColumns, int max) {
        if (previousColumns.size() == boardLength) {
            gameStates.add(previousColumns);
        } else {
            for (int x = 0; x <= max; x++) {
                ArrayList<Integer> nextColumns = new ArrayList<>(previousColumns);
                nextColumns.add(x);
                listGameStates(nextColumns, x);
            }
        }
    }

    orderedPair chooseBestMove(ArrayList<Integer> gameState,int whichGameState) {
        if(canMakeV(gameState)){
            isWinState[whichGameState] = true;
            return new orderedPair(1,1);

        }
        int totalPossibleMoves = 0;
        for(int i: gameState){
            totalPossibleMoves+=i;
        }
        orderedPair[] possibleOrderedPairs = new orderedPair[totalPossibleMoves];
        int i =0;
        for (int x = 0; x < gameState.size(); x++) {
            for (int y = 0; y< gameState.get(x); y++) {
                possibleOrderedPairs[i] = new orderedPair(x,y);
                i++;
            }
        }
        for (orderedPair c : possibleOrderedPairs) {
            ArrayList<Integer> gameStatePossibility = new ArrayList<>(gameState);
            Object[] arrayVersion = gameStatePossibility.toArray();
            for (int x = c.x; x < gameState.size(); x++) {
                if ((int) (arrayVersion[x]) >= c.y) {
                    arrayVersion[x] = c.y;
                }
            }
            ArrayList<Object> choice = new ArrayList<>(Arrays.asList(arrayVersion));
            if(!isWinState[(gameStates.indexOf(choice))]){ //if the choice has been previously marked as a loss state, play it and mark this one as a win.
                isWinState[whichGameState] = true;
                //System.out.println("Solved: Game State "+(whichGameState+1)+"/"+totalGameStates+" total game states.");
                return c;
            }
        }
        isWinState[whichGameState] = false;
        //play the move that is first, highest, and second, furthest to the right - this basically removes the fewest possible chips if you know you're going to lose
        int highestY = gameState.get(0);
        for (int x = 0;x<gameState.size();x++){
            if(gameState.get(x)<highestY){
                //System.out.println("Solved: Game State "+(whichGameState+1)+"/"+totalGameStates+" total game states.");
                return new orderedPair(x-1,highestY-1);
            }
        }
        //System.out.println("Solved: Game State "+(whichGameState+1)+"/"+totalGameStates+" total game states.");
        return new orderedPair(0,0);
    }
    boolean canMakeV(ArrayList<Integer> gameState){
        if(gameState.get(1)<2){
            return false;
        }
        int numberInFirstColumn = gameState.get(0);
        for (int x =0;x<gameState.size();x++){
            if(x<=numberInFirstColumn-1){
                if(gameState.get(x)<1){
                    return false;
                }
            } else {
                if (gameState.get(x)!=0){
                    return false;
                }
            }
        }
        return true;
    }
    CHOMPPrint(boolean test){
        System.out.println(canMakeV(new ArrayList<>(Arrays.asList(9,5,4,3,2,2,2,2,2,0))));
    }
}
class orderedPair{
    int x;
    int y;
    public orderedPair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    void printInfo(){
        System.out.println("("+x+","+y+")");
    }
}