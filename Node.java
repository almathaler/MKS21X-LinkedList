public class Node{
 private Integer data;
 private Node next,prev;
 public Node(Integer val, Node next_, Node prev_){
   data = val;
   next = next_;
   prev = prev_;
 }
 /**
    Node next()
    Node prev()
    void setNext(Node other)
    void setPrev(Node other)
    Integer getData()
    Integer setData(Integer i)
    String toString()
 **/
 public Node next(){
   return next;
 }

 public Node prev(){
   return prev;
 }

 public void setNext(Node newNext){
   next = newNext;
 }

 public void setPrev(Node newPrev){
   prev = newPrev;
 }

 public Integer getData(){
   return data;
 }

 public Integer setData(Integer i){
   Integer oldData = data;
   data = i;
   return oldData;
 }

 public String toString(){
   String toReturn;
   if (this == null){
     toReturn = "";
   }
   else{
     toReturn = "" + data;
   }
   return toReturn;
 }

}
