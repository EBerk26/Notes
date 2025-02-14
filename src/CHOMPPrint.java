import java.util.ArrayList;
import java.util.Arrays;

public class CHOMPPrint {

    public static void main(String[] args) {
        new CHOMPPrint();
    }

    int boardLength = 10;
    ArrayList<ArrayList<Integer>> gameStates = new ArrayList<>();
    boolean[] isWinState;
    int totalGameStates;

    public CHOMPPrint() {
        listGameStates(new ArrayList<>(), boardLength);
        totalGameStates = gameStates.size();
        isWinState = new boolean[totalGameStates];
        isWinState[0] = true;
        isWinState[1] = false;
        ArrayList<orderedPair> listOfMoves = new ArrayList<>(new ArrayList<>(Arrays.asList(new orderedPair(-1,-1),new orderedPair(0,0))));
        for(int x=2; x<totalGameStates;x++){
            listOfMoves.add(chooseBestMove(gameStates.get(x),x));
        }
        for(int x = 0; x<listOfMoves.size();x++){
            System.out.println(gameStates.get(x));
            System.out.println("Is Win: "+isWinState[x]);
            listOfMoves.get(x).printInfo();
            System.out.println();
        }
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
        ArrayList<orderedPair> possibleOrderedPairs = new ArrayList<>();
        for (int x = 0; x < gameState.size(); x++) {
            for (int y = 0; y< gameState.get(x); y++) {
                possibleOrderedPairs.add(new orderedPair(x, y));
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
                System.out.println("Solved: Game State "+(whichGameState+1)+"/"+totalGameStates+" total game states.");
                return c;
            }
        }
        isWinState[whichGameState] = false;
        return new orderedPair(0,0);
        //TODO: pick a better move in a loss situation
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