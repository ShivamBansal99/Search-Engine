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
class PageEntry {
    PageIndex PI=new PageIndex();
    String Pagekaname;
    MyLinkedList<String> connct=new MyLinkedList<>(); 
    PageEntry(String pageName) throws FileNotFoundException{
        connct.add("a");connct.add("an");connct.add("the");connct.add("they");connct.add("these");connct.add("this");connct.add("for");connct.add("is");connct.add("are");connct.add("was");connct.add("of");
        connct.add("and");connct.add("does");connct.add("will");connct.add("whose");connct.add("or");
        Pagekaname=pageName;
        FileInputStream fs = new FileInputStream(pageName);
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
                        PI.addPositionForWord(t[i], new Position(this,count));
                    }
                count++;
                }
            }
        }
    }
    float getTermFrequency(String word){
        float count=0;
        MyLinkedList<WordEntry> ms = PI.getWordEntries();
        for(int i=0;i<ms.size();i++){
            if(ms.get(i).str.equals(word)){
                for(int j=0;j<ms.get(i).pos.size();j++){
                    if(ms.get(i).pos.get(j).p.Pagekaname.equals(this.Pagekaname)) {
                        count++;
                        System.out.println(this.Pagekaname+ ms.get(i).str);
                    }
                }
                
            }
        }
        System.out.println(this.Pagekaname+" "+ count/ms.size());
        if(ms.size()==0) return 0;
        return count/ms.size();
    }
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
}
