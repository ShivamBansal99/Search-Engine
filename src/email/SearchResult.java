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
public class SearchResult implements Comparable {
    PageEntry p;
    float r;
     public SearchResult(PageEntry p, float r){
         this.p=p;
         this.r=r;
     }
     public PageEntry getPageEntry(){
         return p;
     }
     
    public float getRelevance(){
    return r;    
    }
    public int compareTo(SearchResult otherObject) {
        if(otherObject.r>this.r) return 1;
        if(otherObject.r>this.r) return -1;
        return 0;
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
