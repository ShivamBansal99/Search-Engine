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
    PageEntry(String pageName) throws FileNotFoundException{
        Pagekaname=pageName;
        
    }
    float getTermFrequency(String word){
        float count=0;
        MyLinkedList<WordEntry> ms = PI.getWordEntries();
        for(int i=0;i<ms.size();i++){
            if(ms.get(i).str.equals(word)){
                for(int j=0;j<ms.get(i).pos.size();j++){
                    if(ms.get(i).pos.get(j).p.Pagekaname.equals(this.Pagekaname)) {
                        count++;
                        //System.out.println(this.Pagekaname+ ms.get(i).str);
                    }
                }
                
            }
        }
        //System.out.println(this.Pagekaname+" "+ count/ms.size());
        if(ms.size()==0) return 0;
        return count/ms.size();
    }
    
}
