public class Path {
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
    public Path(int g, int f, int x, int y) {
        this.g=g;
        this.f=f;
        this.x=x;
        this.y=y;
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
