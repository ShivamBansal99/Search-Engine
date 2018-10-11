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
public class WordEntry {
    String str;
    MyLinkedList<Position> pos;
    
    WordEntry(String word){
        str = word;
        pos = new MyLinkedList<>();
    }
    void addPosition(Position position){
        pos.add(position);
    }
    void addPositions(MyLinkedList<Position> positions){
        MyLinkedList<Position> an = positions.clone();
        while(!an.isEmpty()){
            pos.add(an.remove());
        }
    }
    MyLinkedList<Position> getAllPositionsForThisWord(){
        return pos;
    }
}
