package email;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class MyLinkedList<T> implements Iterable{
    private Node<T> head;
    private Node<T> tail;
     private int size=0;

    @Override
    public Iterator iterator() {
        return new LLIterator<>();
    }
     class LLIterator<T> implements Iterator {
        int current = 0;  // the current element we are looking at
        Node<T> curr =(Node) head;
        // return whether or not there are more elements in the array that
        // have not been iterated over.
        public boolean hasNext() {
            return curr!=null;
        }
        public void remove(){
            return;
        }

        // return the next element of the iteration and move the current
        // index to the element after that.
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T a = curr.getValue();
            curr=curr.getNextRef();
            return a;
        }
    }
    public void add(T element){
         
        Node<T> nd = new Node();
        nd.setValue(element);
        /**
         * check if the list is empty
         */
        if(head == null){
            //since there is only one element, both head and 
            //tail points to the same T.
            head = nd;
            tail = nd;
            size=0;
        } else {
            //set current tail next link to new node
            tail.setNextRef(nd);
            //set tail as newly created node
            tail = nd;
        }
        size++;
    }
    
    public void deleteFront(){
         
        if(head == null){
            System.out.println("Underflow...");
        }
        Node<T> tmp = head;
        head = tmp.getNextRef();
        if(head == null){
            tail = null;
        }
    }
    public void addAfter(T a,T b){
        Node<T> pos = head;
        Node<T> adds = new Node();
        adds.setValue(a);
        for(int i=0;i<size;i++){
            if(pos.getValue().equals(b)){
                adds.setNextRef(pos.getNextRef());
                pos.setNextRef(adds);
                size++;
                if(adds.getNextRef()==null) tail= adds;
                break;
            }
            pos=pos.getNextRef();
        }
    }
    public Boolean isEmpty(){
        return head==null;
    }
    public int size(){
        return size;
    }
    public Boolean contains(T o){
        for(int i=0;i<this.size;i++){
            if(get(i)==null) return false;
            if(this.get(i).equals(o)){
                return true;
            }
        }
        return false;
    }
    public T get(int i){
        Node<T> ptr = this.head;
        if(i>=size) return null;
        for(int j=0;j<i;j++){
            ptr= ptr.getNextRef();
        }
        if(ptr==null) return null;
        return ptr.getValue();
    }
    public T remove(){
        if(head!=null){
            Node<T> res = head;
            head = head.getNextRef();
            size--;
            if(head==null) tail=null;
            return res.getValue();
        }
        return null;
    }
    public void remove(T o){
        Node s = head;
        if(head.getValue().equals(o)) {
            head = head.getNextRef();
            size--;
            if(head==null) tail=null;
        }
        else{
            for(int i=0;i<size && s.getNextRef()!=null;i++){
                if(s.getNextRef().getValue().equals(o)){
                    Node a=s.getNextRef().getNextRef();
                    s.setNextRef(a);
                    if(a==null) tail= s;
                    size--;
                }
                s=s.getNextRef();
            }
        }
        
    }
    public MyLinkedList clone(){
        MyLinkedList<T> res = new MyLinkedList();
        for(int i=0;i<this.size;i++){
            res.add(this.get(i));
        }
        return res;
    }
}
 
class Node<T> implements Comparable {
     
    private T value;
    private Node nextRef;
     
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public Node getNextRef() {
        return nextRef;
    }
    public void setNextRef(Node ref) {
        this.nextRef = ref;
    }
    @Override
    public int compareTo(Object arg) {
        if(arg == this.value){
            return 0;
        } else {
            return 1;
        }
    }
}

