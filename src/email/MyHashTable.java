/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.util.Iterator;

/**
 *
 * @author anmol
 */
public class MyHashTable {
    MyLinkedList<WordEntry>[] hash = new MyLinkedList[100];
    private int getHashIndex(String str){
        return ((int)str.charAt(0))-65;
    }
    void addPositionsForWord(WordEntry w){
        MyLinkedList<WordEntry> a = hash[this.getHashIndex(w.str)];
        Iterator<WordEntry> it = a.iterator();
        int flag =0;
        while(it.hasNext()){
            WordEntry b= it.next();
            if(b.str.equals(w.str)){
                b.addPositions(w.pos);
                flag=1;
            }
        }
        if(flag==0){
            a.add(w);
        }
    }
}
