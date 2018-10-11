/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

/**
 *
 * @author anmol
 */
public class InvertedPageIndex {
    MySet<PageEntry> set  = new MySet<>();
    void addPage(PageEntry p){
        set.Insert(p);
    }
    MySet<PageEntry> getPagesWhichContainWord(String str){
        MySet<PageEntry> ms = new MySet<>();
        for(int i=0;i<set.linkedl.size();i++){
            for(int j=0;j<set.linkedl.get(i).PI.getWordEntries().size();j++){
                if(set.linkedl.get(i).PI.getWordEntries().get(j).str.equals(str)) ms.Insert(set.linkedl.get(i));
            }
        }
        for(int i=0;i<ms.linkedl.size();i++){
            for(int j=0;j<ms.linkedl.size()-1;j++){
                if(ms.linkedl.get(j).getTermFrequency(str)<ms.linkedl.get(j+1).getTermFrequency(str)) {
                    System.out.println(ms.linkedl.get(j).getTermFrequency(str)+" "+ ms.linkedl.get(j+1).getTermFrequency(str));
                    PageEntry p;
                    p=ms.linkedl.get(j);
                    ms.linkedl.remove(ms.linkedl.get(j));
                    ms.linkedl.addAfter( p,ms.linkedl.get(j));
                }
            }
        }
        return ms;
    }
}
