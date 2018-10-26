/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static java.lang.Math.log;
import java.util.Scanner;

/**
 *
 * @author anmol
 */
class PageEntry {
    PageIndex PI=new PageIndex();
    String Pagekaname;
    InvertedPageIndex IPI;
    PageEntry(String pageName) throws FileNotFoundException{
        Pagekaname=pageName;
        
    }
    PageEntry() throws FileNotFoundException{
   
    }
    float getPhraseFrequency(String[] str){
        int count=0;
        WordEntry first=new WordEntry(str[0]);
        for(int k=0;k<PI.wordentries.size();k++){
                if(PI.wordentries.get(k).str.equals(str[0]))  first=PI.wordentries.get(k);
            }
            //System.out.println(first.pos.size());
            for(int j=0;j<first.pos.size();j++){
                int flag=0;
                for(int m=1;m<str.length;m++){
                    //System.out.println("yes");
                    for(int k=0;k<PI.wordentries.size();k++){
                        if(PI.wordentries.get(k).str.equals(str[m]))  {
                            //s0.linkedl.get(i).PI.wordentries.get(k).postree.inorder();System.out.println(s0.linkedl.get(i).PI.wordentries.get(k).str+" "+s0.linkedl.get(i).Pagekaname);
                            if(PI.wordentries.get(k).postree.Find(m+first.pos.get(j).originalWordIndex)!=null){
                                //System.out.println(m+first.pos.get(j).originalWordIndex +" "+s0.linkedl.get(i).Pagekaname);
                                if(m==str.length-1) count++;
                            }else{
                                //System.out.println("this "+s0.linkedl.get(i).Pagekaname+" "+s0.linkedl.get(i).PI.wordentries.get(k).postree.Find(4).wordIndex);
                            }
                        }
                    }
                }
            }
            return (float)count/((float)this.PI.wordentries.size()-(float)(str.length-1)*count);
    }
    float getInverseDocumentForPhrase(String[] str){
        int N = IPI.set.linkedl.size();
        int nw = IPI.getPagesWhichContainPhrase(str).linkedl.size();
        if(N==0 || nw==0) return 0;
        return (float)log((float)N/(float)nw);
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
        float count1=0;
        for(int i=0;i<ms.size();i++){
            count1+=ms.get(i).pos.size();
        }
        //System.out.println(this.Pagekaname+" "+ count/ms.size());
        if(count1==0) return 0;
        return count/count1;
    }
    float getInverseDocumentFrequency(String word){
        int N = IPI.set.linkedl.size();
        int nw = IPI.getPagesWhichContainWord(word).linkedl.size();
        if(N==0 || nw==0) return 0;
        return (float)log((float)N/(float)nw);
    }
    float getRelevanceOfPage(String[] str, boolean doTheseWordsRepresentAPhrase){
        float ans =0;
        if(doTheseWordsRepresentAPhrase){
            ans=this.getInverseDocumentForPhrase(str)*this.getPhraseFrequency(str);
        }else{
            for(int i=0;i<str.length;i++){
                //System.out.println(str[i]+this.getTermFrequency(str[i])+this.Pagekaname+getInverseDocumentFrequency(str[i]));
                ans+=this.getInverseDocumentFrequency(str[i])*this.getTermFrequency(str[i]);
            }
        }
        return ans;
    }
}
