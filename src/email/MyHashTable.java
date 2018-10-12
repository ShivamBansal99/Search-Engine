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
    MyLinkedList<WordEntry>[] hash = new MyLinkedList[10000];
    public int getHashIndex(String str){
        return ((int)str.charAt(0));
    }
    void addPositionsForWord(WordEntry w){
        //System.out.println("a is "+w.str+ " "+this.getHashIndex(w.str) );
        MyLinkedList<WordEntry> a = hash[this.getHashIndex(w.str)];
        if(a!=null){
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
        else{
            hash[this.getHashIndex(w.str)]= new MyLinkedList<>();
            hash[this.getHashIndex(w.str)].add(w);
            //System.out.println("a is null "+w.str+ " "+this.getHashIndex(w.str) );
        }
    }
}
