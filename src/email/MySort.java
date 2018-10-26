/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author anmol
 * @param <Comparable>
 */
public class MySort {
    public static ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries){
        ArrayList<SearchResult> arr = new ArrayList<>();
        Iterator<SearchResult> it = listOfSortableEntries.linkedl.iterator();
        while(it.hasNext()){
            SearchResult c =  it.next();
            int flag =0;
            for(int i=0;i<arr.size();i++){
                if(arr.get(i).compareTo(c)==1){
                    arr.add(i, c);
                    flag=1;
                    break;
                }
            }
            if(flag==0){
                arr.add(c);
            }
        }
        return arr;
    }
}
