import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
public class WikipediaPage {
    URL url;
    WikipediaPage parent;
    int g;
    boolean hasParent = false;
    boolean isGoal = false;

    public static void main(String[] args) {
        WikipediaPage amongus =new WikipediaPage("https://en.wikipedia.org/wiki/Austin,_Texas",0);
        WikipediaPage[] children = new WikipediaPage[amongus.findChildren().length];
        System.arraycopy(amongus.findChildren(),0,children,0,amongus.findChildren().length);
        System.out.println();
    }
    public WikipediaPage(URL url,int g){
        this.url=url;
        this.g=g;
    }
    public WikipediaPage(String url,int g){
        try{
            this.url=new URL(url);
            this.g=g;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public WikipediaPage(URL url){
        try{
            this.url=url;
        } catch (Exception e) {
                System.out.println(e.getMessage());
        }
    }
    void setParent(WikipediaPage parent){
        this.parent=parent;
        this.g=parent.g+1;
        hasParent = true;
    }
    WikipediaPage[] findChildren(URL goal){
        WikipediaPage[] output = new WikipediaPage[0];
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.url.openStream()));
            String line = "";
            while(line!=null) {
                while (line.contains("href")) {
                    String link = getLink(line, this.url);
                    if(link.contains("wikipedia")) {
                        URL urlLink = new URL(link);
                        WikipediaPage currentWikipediaPage = new WikipediaPage(urlLink);
                        if (urlLink.getHost().equals("en.wikipedia.org") && urlLink.getFile().contains("wiki/") && !(WikipediaPageArrayContainsURL(output, currentWikipediaPage)) && !urlLink.getFile().contains(":") && !urlLink.getFile().equals("/wiki/Main_Page") && !link.contains("(")) {
                            WikipediaPage[] temp = new WikipediaPage[output.length + 1];
                            System.arraycopy(output, 0, temp, 0, output.length);
                            temp[temp.length - 1] = new WikipediaPage(urlLink);
                            temp[temp.length - 1].setParent(this);
                            if(urlLink.getFile().equals(goal.getFile())){
                                temp[temp.length-1].isGoal=true;
                            }
                            output = new WikipediaPage[temp.length];
                            System.arraycopy(temp, 0, output, 0, temp.length);
                        }
                    }
                    line = line.substring(line.indexOf("\"", line.indexOf("href=\"") + 6));
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return output;
    }
    WikipediaPage[] findChildren(){
        WikipediaPage[] output = new WikipediaPage[0];
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.url.openStream()));
            String line = "";
            while(line!=null) {
                while (line.contains("href")) {
                    String link = getLink(line, this.url);
                    if(link.contains("wikipedia")) {
                        URL urlLink = new URL(link);
                        WikipediaPage currentWikipediaPage = new WikipediaPage(urlLink);
                        if (urlLink.getHost().equals("en.wikipedia.org") && urlLink.getFile().contains("wiki/") && !(WikipediaPageArrayContainsURL(output, currentWikipediaPage)) && !urlLink.getFile().contains(":") && !urlLink.getFile().equals("/wiki/Main_Page") && !link.contains("(")) {
                            WikipediaPage[] temp = new WikipediaPage[output.length + 1];
                            System.arraycopy(output, 0, temp, 0, output.length);
                            temp[temp.length - 1] = new WikipediaPage(urlLink);
                            temp[temp.length - 1].setParent(this);
                            output = new WikipediaPage[temp.length];
                            System.arraycopy(temp, 0, output, 0, temp.length);
                        }
                    }
                    line = line.substring(line.indexOf("\"", line.indexOf("href=\"") + 6));
                }
                line = reader.readLine();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return output;
    }
    public String urlString(){
        return this.url.getHost()+this.url.getFile();
    }
    private String getLink(String line, URL url){
        String link = line.substring(line.indexOf("href=\"")+6, line.indexOf("\"", line.indexOf("href=\"")+6));
        if(!link.isEmpty()) {
            if (link.charAt(0) == '/') {
                if (link.charAt(1) == '/') {
                    link = link.substring(2);
                } else {
                    link = url.getHost() + link;
                }
            }
        }
        if(link.indexOf("https://")!=0){
            link="https://"+link;
        }
        return link;
    }
    boolean WikipediaPageArrayContainsURL(WikipediaPage[] WikipediaPageArray,WikipediaPage input){
        for (WikipediaPage x : WikipediaPageArray) {
            if (x.url.getFile().equals(input.url.getFile())) {
                return true;
            }
        }
        return false;
    }
    boolean betterPathExistsToSamePlace(WikipediaPage[] WikipediaPageArray){
        for (WikipediaPage x : WikipediaPageArray) {
            if (x.url.getFile().equals(this.url.getFile())&&x.g<this.g) {
                return true;
            }
        }
        return false;
    }
    void printOutPath(){
        System.out.println();
        WikipediaPage backtracking = this;
        WikipediaPage[] path = new WikipediaPage[this.g+1];
        int i=this.g;
        while(i>=0){
            path[i]=backtracking;
            backtracking=backtracking.parent;
            i--;
        }
        for(int x =0;x<path.length;x++){
            System.out.println(x+". "+path[x].url.getFile().substring(6));
        }
    }
}