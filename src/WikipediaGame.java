import java.net.URL;

//TODO this is currently Dijkstra's so we need some kind of heuristic function or this will continue to take forever

public class WikipediaGame{
    URL startPage;
    URL endPage;
    WikipediaPage[] toCheck;
    WikipediaPage[] checkedAlready;
    WikipediaPage[] cleanedInput;
    WikipediaPage q;
    boolean done = false;
    public static void main(String[] args) {
        new WikipediaGame();
    }
    public WikipediaGame() {
        try {
            String startPageURLString = "https://en.wikipedia.org/wiki/Glen_Powell";
            startPage = new URL(startPageURLString);
            endPage = new URL("https://en.wikipedia.org/wiki/Kevin_Bacon");
            toCheck = new WikipediaPage[1];
            checkedAlready = new WikipediaPage[0];
            toCheck[0] = new WikipediaPage(startPageURLString,0);
            while(toCheck.length>0&&!done){
                int bestG = 100;
                int bestIndex = 0;
                for(int x =0;x<toCheck.length;x++){
                    if(toCheck[x].g<bestG) {
                        bestG = toCheck[x].g;
                        bestIndex = x;
                    }
                }
                q = toCheck[bestIndex];
                System.out.println("Checking: "+q.url.getFile().substring(6));
                removeFromToCheck(bestIndex);
                addToList("checkedAlready",q);
                addArraytoToCheck(q.findChildren(endPage));
            }



        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    void addToList(String list,WikipediaPage input){
        if(list.equals("toCheck")){
                WikipediaPage[] temp = new WikipediaPage[toCheck.length+1];
                System.arraycopy(toCheck,0,temp,0,toCheck.length);
                temp[temp.length-1]=input;
                toCheck=new WikipediaPage[temp.length];
                System.arraycopy(temp,0,toCheck,0,temp.length);
        } else if (list.equals("checkedAlready")){
            WikipediaPage[] temp = new WikipediaPage[checkedAlready.length+1];
            System.arraycopy(checkedAlready,0,temp,0,checkedAlready.length);
            temp[temp.length-1]=input;
            checkedAlready=new WikipediaPage[temp.length];
            System.arraycopy(temp,0,checkedAlready,0,temp.length);
        } else if (list.equals("cleanedInput")){
            WikipediaPage[] temp = new WikipediaPage[cleanedInput.length+1];
            System.arraycopy(cleanedInput,0,temp,0,cleanedInput.length);
            temp[temp.length-1]=input;
            cleanedInput=new WikipediaPage[temp.length];
            System.arraycopy(temp,0,cleanedInput,0,temp.length);
        }
    }
    void removeFromToCheck(int index){
        WikipediaPage[] temp = new WikipediaPage[toCheck.length-1];
        System.arraycopy(toCheck,0,temp,0,index);
        System.arraycopy(toCheck,index+1,temp,index,toCheck.length-index-1);
        toCheck=new WikipediaPage[temp.length];
        System.arraycopy(temp,0,toCheck,0,temp.length);
    }
    void addArraytoToCheck(WikipediaPage[] input){
        cleanedInput = new WikipediaPage[0];
        for (WikipediaPage x : input) { //first, delete all the things that you're about to add for which there's already a better version out there
            if (!(x.betterPathExistsToSamePlace(toCheck) || x.betterPathExistsToSamePlace(checkedAlready))) {
                if(x.isGoal){
                    x.printOutPath();
                    done = true;
                }
                addToList("cleanedInput", x);
            }
        }

        WikipediaPage[] temp = new WikipediaPage[cleanedInput.length+toCheck.length];
        System.arraycopy(toCheck,0,temp,0,toCheck.length);
        for(int x = toCheck.length;x<cleanedInput.length+toCheck.length;x++){
            temp[x]=cleanedInput[x-toCheck.length];
        }
        toCheck = new WikipediaPage[temp.length];
        System.arraycopy(temp,0,toCheck,0,temp.length);
    }
}