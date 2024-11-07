public class Path {
    String howToGetThere;
    int g;
    int f;
    int x;
    int h;
    int y;
    int parentX;
    int parentY;
    Path parent;
    boolean hasParent = false;
    int generation=0;
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
    public Path(){

    }
    void setParent(Path parent,int parentX, int parentY){
        this.parent=parent;
        this.parentX=parentX;
        this.parentY=parentY;
        this.hasParent = true;
        generation = parent.generation+1;
    }
}
