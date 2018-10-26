package email;
import java.util.Iterator;
public class PageIndex {
    MyLinkedList<WordEntry> wordentries = new MyLinkedList<>();
    void addPositionForWord(String str, Position p){
        Iterator<WordEntry> it = wordentries.iterator();
        int flag=0;
        while(it.hasNext()){
            WordEntry a = it.next();
            if(a.str.equals(str)){
                flag=1;
                a.addPosition(p);
            }
        }
        if(flag==0){
            WordEntry a = new WordEntry(str);
            a.addPosition(p);
            wordentries.add(a);
        }
        
    }
    MyLinkedList<WordEntry> getWordEntries(){
        return wordentries;
    }
}
