/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;
//TODO: check if z is root in rotaion and height

import static java.lang.Math.max;

/**
 *
 * @author anmol
 */
public class MyAVLTree {
    Node root ;
    void addNode(Position p){
        if(root==null){
            root=new Node(p,null,true);
            root.height=1;
            return ;
        }
        if(root.getValue().originalWordIndex<p.originalWordIndex){
            if(root.getRight()==null){
                Node n = new Node(p,root,true);
                root.setRight(n);
                root=rotateAndHeight(root);
                return ;
            }
            MyAVLTree a = new MyAVLTree();
            a.root=root.getRight();
            a.addNode(p);
            root=rotateAndHeight(a.root);
            return;
        }
        if(root.getLeft()==null){
            Node n=new Node(p,root,false);
            root.setLeft(n);
            root=rotateAndHeight(root);
            return ;
        }
        MyAVLTree a = new MyAVLTree();
        a.root=root.getLeft ();
        a.addNode(p);
        root=rotateAndHeight(a.root);
    }
    Node rotateAndHeight(Node z){
        //System.out.println(z.value.originalWordIndex+" hi");
        //if(z.left!=null) System.out.println(z.left.value.originalWordIndex+" le");
        //if(z.right!=null) System.out.println(z.right.value.originalWordIndex+" ri");

        if(z==null) return null;
        if(z.getRightHeight()>z.getLeftHeight()+1){
            //System.out.println("z is null");
            Node y= z.getRight();
            if(y.getRightHeight()>y.getLeftHeight()){
                Node x=y.getRight();
                //if(z.rightChild==null) 
                //System.out.println("z is null");
                if(z.rightChild){
                    Node pare = z.parent;
                    Node temp= y.getLeft();
                    z.setRight(temp);
                    y.setLeft(z);
                    if(pare!=null){
                        pare.setRight(y);
                    }else{
                        root=y;
                        y.setParent(null);
                    }
                    if(y.parent!=null)
                    root=rotateAndHeight(y.parent);
                }else{
                    Node pare = z.parent;
                    Node temp= y.getLeft();
                    z.setRight(temp);
                    y.setLeft(z);
                    if(pare!=null){
                        pare.setLeft(y);
                    }else{
                        root=y;
                        y.setParent(null);
                    }
                    if(y.parent!=null)
                    root=rotateAndHeight(y.parent);
                }
            }
            else if(y.getRightHeight()<y.getLeftHeight()){
                //System.out.println(y.getRightHeight()+" "+y.getLeftHeight()+" "+y.value.originalWordIndex+" "+y.left.value.originalWordIndex);
                Node x=y.getLeft();
                if(z.rightChild){
                    //System.out.println("therr");
                    Node pare = z.parent;
                    Node Xl= x.getLeft();
                    Node Xr = x.getRight();
                    y.setLeft(Xr);
                    z.setRight(Xl);
                    x.setLeft(z);
                    x.setRight(y);
                    
                    if(pare!=null){
                        pare.setRight(x);
                    }else{
                        root=x;
                        x.setParent(null);
                    }
                    
                    if(x.parent!=null){
                        root=rotateAndHeight(x.parent);
                        //System.out.println("y "+x.left.value.originalWordIndex);
                    }
                }else{
                    Node pare = z.parent;
                    Node Xl= x.getLeft();
                    Node Xr = x.getRight();
                    y.setLeft(Xr);
                    z.setRight(Xl);
                    x.setLeft(z);
                    x.setRight(y);
                    
                    if(pare!=null){
                        pare.setLeft(x);
                    }else{
                        root=x;
                        x.setParent(null);
                    }
                    
                    if(x.parent!=null){
                        root=rotateAndHeight(x.parent);
                        //System.out.println("y "+x.left.value.originalWordIndex);
                    }
                }
                return root;
            }
            return root;
        }
        else if(z.getRightHeight()+1<z.getLeftHeight()){
            //System.out.println("z is not null");
            //System.out.println("z is null");
            Node y=z.getLeft();
            if(y.getRightHeight()<y.getLeftHeight()){
                Node x=y.getLeft();
                if(z.rightChild){
                    Node pare = z.parent;
                    Node temp= y.getRight();
                    z.setLeft(temp);
                    y.setRight(z);
                    if(pare!=null){
                        pare.setRight(y);
                    }else{
                        root=y;
                        y.setParent(null);
                    }
                    if(pare!=null)
                    root=rotateAndHeight(y.parent);
                }else{
                    Node pare = z.parent;
                    Node temp= y.getRight();
                    z.setLeft(temp);
                    y.setRight(z);
                    if(pare!=null){
                        pare.setLeft(y);
                    }else{
                        root=y;
                        y.setParent(null);
                    }
                    if(pare!=null)
                    root=rotateAndHeight(y.parent);
                }
            }
            else if(y.getRightHeight()<y.getLeftHeight()){
                Node x=y.getLeft();
                if(z.rightChild){
                    Node pare = z.parent; 
                    Node Xl= x.getLeft();
                    Node Xr = x.getRight();
                    y.setRight(Xl);
                    z.setLeft(Xr);
                    x.setRight(z);
                    x.setLeft(y);
                    if(pare!=null){
                    pare.setRight(x);}
                    else{
                        root=x;
                        x.setParent(null);
                    }
                    if(x.parent!=null)
                    root=rotateAndHeight(x.parent);
                }else{
                    Node pare = z.parent;
                    Node Xl= x.getLeft();
                    Node Xr = x.getRight();
                    y.setRight(Xl);
                    z.setLeft(Xr);
                    x.setRight(z);
                    x.setLeft(y);
                    if(pare!=null){
                    pare.setLeft(x);}
                    else{
                        root=x;
                        x.setParent(null);
                    }
                    if(x.parent!=null)
                    root=rotateAndHeight(x.parent);                }
            }
        }
        //System.out.println(z.value.wordIndex);
        else if(z.getRightHeight()>z.getLeftHeight()){
            z.height=z.getRightHeight()+1;
            if(z.getParent()==null) return z;
            root =this.rotateAndHeight(z.parent);
        }
        else if(z.getRightHeight()<z.getLeftHeight()){
            z.height=z.getLeftHeight()+1;
            if(z.getParent()==null) return z;
            root=this.rotateAndHeight(z.parent);
        }
        else{
            z.height=z.getLeftHeight()+1;
            if(z.getParent()==null) return z;
            root=this.rotateAndHeight(z.parent);
        }
        return root;
    }
    Position Find(int i){
        Node finder = root;
        while(finder!=null){
            if(finder.getValue().originalWordIndex==i){
                return finder.getValue();
            }
            else if(finder.getValue().originalWordIndex<i){
                finder=finder.right;
            }
            else finder=finder.left;
        }
        return null;
    }
    public void inorder(){
        MyAVLTree a = new MyAVLTree();
        a.root = root.left;
        MyAVLTree b = new MyAVLTree();
        b.root = root.right;
        if(a.root!=null)a.inorder();
        System.out.print(root.value.originalWordIndex+", ");
        if(b.root!=null)b.inorder();
    }
    class Node implements Comparable {
        public int height;
        private Node parent;
        public Boolean rightChild=true;
        private Position value;
        private Node right;
        private Node left;
        Node(Position p,Node parent,Boolean right){
            this.rightChild=right;
            this.parent=parent;
            this.value=p;
            height=1;
        }
        public Position getValue() {
            return value;
        }
        public void setValue(Position value) {
            this.value = value;
        }
        public Node getRight() {
            return right;
        }
        public void setRight(Node ref) {
            if(ref!=null){
                ref.setParent(this);
                ref.rightChild=true;
                //System.out.println(ref.value.wordIndex);
            }
            this.right = ref;
            this.height=max(this.getLeftHeight()+1,this.getRightHeight()+1);
        }
        public Node getLeft() {
            return left;
        }
        public void setLeft(Node ref) {
            if(ref!=null){
                ref.setParent(this);
                ref.rightChild=false;
                //System.out.println(ref.value.wordIndex);
            }
            this.left = ref;
            this.height=max(this.getLeftHeight()+1,this.getRightHeight()+1);
        }
        public Node getParent() {
            return parent;
        }
        public void setParent(Node ref) {
            this.parent = ref;
        }
        public int getLeftHeight(){
            if(this.left==null) return 0;
            else return this.left.height;
        }
        public int getRightHeight(){
            if(this.right==null) return 0;
            else return this.right.height;
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
    
}
