import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class Chompfile {
    public static void main(String[] args) {
        new Chompfile();
    }
    int boardLength = 10;
    ArrayList<ArrayList<Integer>> gameStates = new ArrayList<>();
    int totalGameStates;
    orderedPair[] listOfMoves;
    ArrayList<Integer> gameStatePossibility;
    Object[] arrayVersion;
    orderedPair[] possibleOrderedPairs;
    int totalPossibleMoves;
    int highestY;
    orderedPair leastRemoval;
    int index;
    int numberInFirstColumn;
    Hashtable<ArrayList<Integer>,Boolean> hashtable;
    Hashtable<ArrayList<Integer>,orderedPair> solutions = new Hashtable<>();

    public Chompfile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("/Users/eliberk/Desktop/CS2/Chomp/chompSolutions.ser")))) {
            listGameStates(new ArrayList<>(),boardLength);
            totalGameStates = gameStates.size();
            hashtable = new Hashtable<>();
            hashtable.put(gameStates.get(0),true);
            hashtable.put(gameStates.get(1),false);
            listOfMoves = new orderedPair[totalGameStates];
            listOfMoves[0] = new orderedPair(-1,-1);
            listOfMoves[1] = new orderedPair(0,0);
            for(int x=2; x<totalGameStates;x++){
                if(listOfMoves[x] == null) {
                    listOfMoves[x] = chooseBestMove(gameStates.get(x));
                }
            }
            for(int x = 0; x<listOfMoves.length;x++){
                solutions.put(gameStates.get(x),listOfMoves[x]);
            }
            oos.writeObject(solutions);
            System.out.println("Hashtable saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
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


    orderedPair chooseBestMove(ArrayList<Integer> gameState) {
        if(canMakeV(gameState)){
            hashtable.put(gameState,true);
            return new orderedPair(1,1);
        }
        totalPossibleMoves = 0;
        for(int i: gameState){
            totalPossibleMoves+=i;
        }
        possibleOrderedPairs = new orderedPair[totalPossibleMoves];
        int i =0;
        for (int x = 0; x < gameState.size(); x++) {
            for (int y = 0; y< gameState.get(x); y++) {
                possibleOrderedPairs[i] = new orderedPair(x,y);
                i++;
            }
        }
        for (orderedPair c : possibleOrderedPairs) {
            gameStatePossibility = new ArrayList<>(gameState);
            arrayVersion = gameStatePossibility.toArray();
            for (int x = c.x; x < gameState.size(); x++) {
                if ((int) (arrayVersion[x]) >= c.y) {
                    arrayVersion[x] = c.y;
                }
            }
            if(!hashtable.get(new ArrayList<>(Arrays.asList(arrayVersion)))){//if the choice has been previously marked as a loss state, play it and mark this one as a win.
                hashtable.put(gameState,true);
                //System.out.println("Solved: Game State "+(whichGameState+1)+"/"+totalGameStates+" total game states.");
                //go through and find any game state larger than this where the same move brings you to the same place
               /*for(int x = whichGameState+1;x<gameStates.size();x++){
                   if(listOfMoves[x]!=null){
                       if(moveGivesSameResult(c,gameStates.get(x))){
                           listOfMoves[x] = c;
                       }
                   }
               }*/ //this slows you down because of the searching
                //mirrorImage(gameState,c,true); this also slows you down
                return c;
            }
        }
        hashtable.put(gameState,false);
        //play the move that is first, highest, and second, furthest to the right - this basically removes the fewest possible chips if you know you're going to lose
        highestY = gameState.get(0);
        for (int x = 0;x<gameState.size();x++){
            if(gameState.get(x)<highestY){
                //System.out.println("Solved: Game State "+(whichGameState+1)+"/"+totalGameStates+" total game states.");
                leastRemoval = new orderedPair(x-1,highestY-1);
                //mirrorImage(gameState,leastRemoval,false);
                return leastRemoval;
            }
        }
        //System.out.println("Solved: Game State "+(whichGameState+1)+"/"+totalGameStates+" total game states.");
        return new orderedPair(0,0);
    }
    @SuppressWarnings("unused")
    boolean canMakeV(ArrayList<Integer> gameState){
        if(gameState.get(1)<2){
            return false;
        }
        numberInFirstColumn = gameState.get(0);
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
}
