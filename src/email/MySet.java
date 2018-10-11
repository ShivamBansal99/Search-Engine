package email;
public class MySet<T> {
    public MyLinkedList<T> linkedl = new MyLinkedList();
    public Boolean isEmpty(){
        return this.linkedl.isEmpty();
    }
    public Boolean IsMember(T o){
        return this.linkedl.contains(o);
    }
    public void Insert(T o){
        if(!this.IsMember(o)) linkedl.add(o);
    }
    public void Delete(T o)throws Exception{
        if(!this.IsMember(o)) throw new Exception("not present");
        this.linkedl.remove(o);
    }
    public MySet Union(MySet a){
        MySet<T> union= new MySet();
        MyLinkedList<T> an;
        MyLinkedList<T> bn;
        an =  a.linkedl.clone();
        bn =  this.linkedl.clone();
        while(!an.isEmpty()){
            union.Insert(an.remove());
        }
        while(!bn.isEmpty()){
            union.Insert(bn.remove());
        }
        return union;
    }
    public MySet<T> Intersection(MySet a){
        MySet<T> inter= new MySet();
        MyLinkedList<T> an;
        MyLinkedList<T> bn;
        an = (MyLinkedList) a.linkedl.clone();
        bn = (MyLinkedList) this.linkedl.clone();
        while(!an.isEmpty()){
            T v = an.remove();
            if(bn.contains(v)){
                inter.Insert(v);
            }
        }
        return inter;
    }
            
            
}