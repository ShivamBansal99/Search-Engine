package email;
public class Position {
    PageEntry p;
    int wordIndex;
    int originalWordIndex;
    Position(PageEntry pe, int w,int k){
        this.p=pe;
        this.wordIndex=w;
        this.originalWordIndex=k;
    }
    public PageEntry getPageEntry(){
        return p;
    }
    public int getWordIndex(){
        return wordIndex;
    }
}
