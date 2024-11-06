public class Path {
    String howToGetThere;
    int g;
    int f;
    int x;
    int y;
    int parentX;
    int parentY;
    public Path(String howToGetThere,int g,int f,int x,int y) {
        this.howToGetThere=howToGetThere;
        this.g=g;
        this.f=f;
        this.x=x;
        this.y=y;
    }
    public Path(int x){
        this.x=x;
    }
    void setParent(int parentX, int parentY){
        this.parentX=parentX;
        this.parentY=parentY;
    }
}
