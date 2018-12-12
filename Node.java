public class Node{
 private int data;
 private Node next,prev;
 public Node(int val, Node next_, Node prev_){
   data = val;
   next = next_;
   prev = prev_;
 }
 public Node getNext(){
   return next;
 }

 public int getData(){
   return data;
 }

 public void setNext(Node newNext){
   next = newNext;
 }

 public void setPrev(Node newPrev){
   prev = newPrev;
 }
}
