/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author anmol
 */
public class InvertedPageIndex {
    MySet<PageEntry> set  = new MySet<>();
    MyHashTable ht = new MyHashTable();
    MyLinkedList<String> connct=new MyLinkedList<>(); 
    String singular(String s){
        if(s.equals("stacks"))
        {
            s = "stack";
        }
        if(s.equals("structures"))
        {
            s = "structure";
        }
        if(s.equals("applications"))
        {
            s = "application";
        }
        return s;
    }
    void addPage(PageEntry p) throws FileNotFoundException{
        
        connct.add("a");connct.add("an");connct.add("the");connct.add("they");connct.add("these");connct.add("this");connct.add("for");connct.add("is");connct.add("are");connct.add("was");connct.add("of");
        connct.add("and");connct.add("does");connct.add("will");connct.add("whose");connct.add("or");
        
        FileInputStream fs = new FileInputStream(p.Pagekaname);
        Scanner s = new Scanner(fs);
        int count = 1;
        while(s.hasNext()){
            String st = s.nextLine();
            st=st.toLowerCase();
            String[] t = st.split("\\s++|\\{|}|<|>|\\(|\\)|\\.|,|;|'|\"|\\?|#|!|-|:");
            for(int i=0;i<t.length;i++){
                if(!t[i].equals("")){
                    if(!connct.contains(t[i])){
                        t[i]=singular(t[i]);
                        WordEntry we= new WordEntry(t[i]);
                        Position pos =new Position(p,count);
                        we.addPosition(pos);
                        this.ht.addPositionsForWord(we);
                        p.PI.addPositionForWord(t[i], pos);
                    }
                count++;
                }
            }
        }
        set.Insert(p);
    }
    MySet<PageEntry> getPagesWhichContainWord(String str){
        MySet<PageEntry> ms = new MySet<>();
        MyLinkedList<WordEntry> n = ht.hash[ht.getHashIndex(str)];
        if(n!=null){
        for(int i=0;i<n.size();i++){
            if(n.get(i).str.equals(str)){
                WordEntry w = n.get(i);
                for(int j=0;j<w.pos.size();j++){
                    ms.Insert(w.pos.get(j).p);
                    }
                }
            }
        }
        /*for(int i=0;i<set.linkedl.size();i++){
            for(int j=0;j<set.linkedl.get(i).PI.getWordEntries().size();j++){
                if(set.linkedl.get(i).PI.getWordEntries().get(j).str.equals(str)) ms.Insert(set.linkedl.get(i));
            }
        }*/
        for(int i=0;i<ms.linkedl.size();i++){
            for(int j=0;j<ms.linkedl.size()-1;j++){
                if(ms.linkedl.get(j).getTermFrequency(str)<ms.linkedl.get(j+1).getTermFrequency(str)) {
                   // System.out.println(ms.linkedl.get(j).getTermFrequency(str)+" "+ ms.linkedl.get(j+1).getTermFrequency(str));
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
