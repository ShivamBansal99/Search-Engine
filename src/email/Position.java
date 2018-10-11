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
public class Position {
    PageEntry p;
    int wordIndex;
    Position(PageEntry pe, int w){
        this.p=pe;
        this.wordIndex=w;
    }
    public PageEntry getPageEntry(){
        return p;
    }
    public int getWordIndex(){
        return wordIndex;
    }
}
