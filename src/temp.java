import java.net.URL;

public class temp {
    public temp() {
        try{
            WikipediaPage joe = new WikipediaPage("https://en.wikipedia.org/wiki/Austin,_Texas",0);
            WikipediaPage[] children = new WikipediaPage[joe.findChildren().length];
            System.arraycopy(joe.findChildren(),0,children,0,joe.findChildren().length);
            for(int x =0;x<children.length;x++){
                System.out.println(children[x].urlString());
            }
        } catch (Exception ignored){

        }
    }

    public static void main(String[] args) {
        new temp();
    }
    private static String getLink(String line, URL url) {
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
}
