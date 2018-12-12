public class MyLinkedList{
 private int size;
 private Node start,end;

 public MyLinkedList(int size_, Node start_, Node end_){
   size = size_;
   start = start_;
   end = end_;
 }
 public int size(){
   return size;
 }
 public boolean add(int value){
   Node temp = new Node(value, null, end);
   end.setNext(temp);
   end = temp;
   return true;
 }
 public String toString(){
   Node current = start;
   String toReturn = "{ ";
   while (current.getNext() != null){
     toReturn += current.getData() + ", ";
     current = current.getNext();
   }
   toReturn += end.getData() + "}";
   return toReturn;
 }
}
