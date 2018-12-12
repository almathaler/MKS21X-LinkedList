public class MyLinkedList{
 private int size;
 private Node start,end;

 public static void main(String[] args){
   Node a = new Node(1, null, null);
   MyLinkedList list = new MyLinkedList(1, a, a);
   System.out.println(list.toString());
   list.add(4);
   list.add(23);
   System.out.println(list.toString());
 }

 public MyLinkedList(int size_, Node start_, Node end_){
   size = size_;
   if (start_ == end_){
     size = 1;
   }
   if (start_ == null && end_ == null){
     size = 0;
   }
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
   size += 1;
   return true;
 }
 public String toString(){
   Node current = start;
   String toReturn = "{";
   if (start != null){
   toReturn += current.getData() + ",";
   }
   while (current.getNext() != null){
     current = current.getNext();
     toReturn += " " + current.getData();
     if (current.getNext() != null){
       toReturn += ",";
     }
   };
   toReturn += "}";
   return toReturn;
 }
}
