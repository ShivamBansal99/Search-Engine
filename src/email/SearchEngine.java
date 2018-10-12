/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.io.FileNotFoundException;

/**
 *
 * @author anmol
 */
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
            invpageindex.addPage(p);
        }
        if(s[0].equals("queryFindPagesWhichContainWord")){
            MySet<PageEntry> m = invpageindex.getPagesWhichContainWord(this.singular( s[1].toLowerCase()));
            for(int i=0;i<m.linkedl.size();i++){
                if(i==m.linkedl.size()-1) System.out.print(m.linkedl.get(i).Pagekaname+"\n");
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
                for(int j=0;j<wrd.pos.size();j++){
                    if(j==wrd.pos.size()-1) System.out.print(wrd.pos.get(j).wordIndex+"\n");
                    else System.out.print(wrd.pos.get(j).wordIndex+" ,");
                }
            if(wrd==null){
                System.out.println("Webpage "+s[2]+" does not contain word "+s[1]);
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
