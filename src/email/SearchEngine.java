package email;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class SearchEngine {
    InvertedPageIndex invpageindex;
    SearchEngine(){
        this.invpageindex = new InvertedPageIndex();
    }
    PageIndex getPageIndexFromStr(String str){
        MySet<PageEntry> ms = invpageindex.set;
        for(int i=0;i<ms.linkedl.size();i++){
            if(ms.linkedl.get(i).Pagekaname.equals(str)) return ms.linkedl.get(i).PI;
        }
        return null;
    }
    WordEntry getWordsFromPageIndex(PageIndex p,String str){
        for(int i=0;i<p.wordentries.size();i++){
            if(p.wordentries.get(i).str.equals(str)) return p.wordentries.get(i);
        }
        return null;
    }
    void performAction(String actionMessage) throws FileNotFoundException{
        String[] s = actionMessage.split("\\s+");
        if(s[0].equals("addPage")){
            PageEntry p = new PageEntry(s[1]);
            p.IPI=invpageindex;
            invpageindex.addPage(p);
        }
        if(s[0].equals("queryFindPagesWhichContainWord")){
            MySet<PageEntry> m = invpageindex.getPagesWhichContainWord(this.singular( s[1].toLowerCase()));
            String[] s1 = {s[1]};
            for(int i=0;i<m.linkedl.size();i++){
                if(i==m.linkedl.size()-1) {System.out.print(m.linkedl.get(i).Pagekaname+"\n");}
                else System.out.print(m.linkedl.get(i).Pagekaname+" ,");
            }
            if(m.linkedl.size()==0){
                System.out.println("No webpage contains word "+s[1]);
            }
        }
        if(s[0].equals("queryFindPositionsOfWordInAPage")){
            PageIndex pi = this.getPageIndexFromStr(s[2]);
            if(pi==null) {
                System.out.println("No webpage "+s[2]+" found");
                return;
            }
            
            WordEntry wrd = this.getWordsFromPageIndex(pi, this.singular( s[1].toLowerCase()));
            if(wrd==null){
                System.out.println("Webpage "+s[2]+" does not contain word "+s[1]);
                return;
            }
                for(int j=0;j<wrd.pos.size();j++){
                    if(j==wrd.pos.size()-1) System.out.print(wrd.pos.get(j).wordIndex+"\n");
                    else System.out.print(wrd.pos.get(j).wordIndex+" ," );
                }
            
        }
        if(s[0].equals("queryFindPagesWhichContainAllWords")){
            MySet<PageEntry> ms = new MySet<>();
            MySet<SearchResult> m1 = new MySet<>();
            for(int i=1;i<s.length;i++){
                if(i==1) ms=this.invpageindex.getPagesWhichContainWord(this.singular( s[i].toLowerCase()));
                ms=ms.Intersection(this.invpageindex.getPagesWhichContainWord(this.singular( s[i].toLowerCase())));
                
            }
            if(ms.linkedl.size()==0){
                System.out.println("None");
                return;
            }
            for(int j=0;j<ms.linkedl.size();j++){
                m1.Insert(new SearchResult(ms.linkedl.get(j),ms.linkedl.get(j).getRelevanceOfPage(s, false)));
            }
            ArrayList<SearchResult> arr = MySort.sortThisList(m1);
            if(arr==null) {
                System.out.println("None");
                return;
            }
            for(int i=0;i<arr.size();i++){
                if(i==arr.size()-1){
                    System.out.println(arr.get(i).p.Pagekaname);
                }else{
                    System.out.print(arr.get(i).p.Pagekaname+", ");
                }
            }
        }
        if(s[0].equals("queryFindPagesWhichContainAnyOfTheseWords")){
            
            MySet<PageEntry> ms = new MySet<>();
            MySet<SearchResult> m1 = new MySet<>();
            for(int i=1;i<s.length;i++){
                ms=ms.Union(this.invpageindex.getPagesWhichContainWord(this.singular( s[i].toLowerCase())));
            }
            if(ms.linkedl.size()==0){
                System.out.println("None");
                return;
            }
            for(int j=0;j<ms.linkedl.size();j++){
                m1.Insert(new SearchResult(ms.linkedl.get(j),ms.linkedl.get(j).getRelevanceOfPage(s, false)));
            }
            ArrayList<SearchResult> arr = MySort.sortThisList(m1);
            for(int i=0;i<arr.size();i++){
                if(i==arr.size()-1){
                    System.out.println(arr.get(i).p.Pagekaname);
                }else{
                    System.out.print(arr.get(i).p.Pagekaname+", ");
                }
            }
        }
        if(s[0].equals("queryFindPagesWhichContainPhrase")){
            
            String[] sa = new String[s.length-1];
            for(int i=1;i<s.length;i++){
                sa[i-1]=s[i];
            }
            MySet<SearchResult> m1 = new MySet<>();
            MySet<PageEntry> ms = this.invpageindex.getPagesWhichContainPhrase(sa);
            //System.out.println(ms.linkedl.size());
            if(ms.linkedl.size()==0){
                System.out.println("None");
            }
            for(int j=0;j<ms.linkedl.size();j++){
                m1.Insert(new SearchResult(ms.linkedl.get(j),ms.linkedl.get(j).getRelevanceOfPage(sa, true)));
            }
            ArrayList<SearchResult> arr = MySort.sortThisList(m1);
            for(int i=0;i<arr.size();i++){
                if(i==arr.size()-1){
                    System.out.println(arr.get(i).p.Pagekaname);
                }else{
                    System.out.print(arr.get(i).p.Pagekaname+", ");
                }
            }
        }
    }
    String singular(String s){
        if(s.equals("stacks")){
            s = "stack";
        }
        if(s.equals("structures")){
            s = "structure";
        }
        if(s.equals("applications")){
            s = "application";
        }
        return s;
    }
}
