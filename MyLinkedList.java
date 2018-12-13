public class MyLinkedList{
 private int size;
 private Node start,end;

 public static void main(String[] args){
   MyLinkedList list = new MyLinkedList();
   System.out.println(list.toString());
   list.add(4);
   list.add(23);
   System.out.println(list.toString());
 }

 public MyLinkedList(){
   size = 0;
   start = null;
   end = null;
 }

 public int size(){
   return size;
 }

 public boolean add(Integer value){
   Node toAdd = new Node(value, null, null);
   if (start == null && end == null){
     start = toAdd;
     end = toAdd;
   }
   else{
     end.setNext(toAdd);
     toAdd.setNext(null);
     toAdd.setPrev(end);
     end = toAdd;
   }
   size+=1;
   System.out.println("added " + toAdd.toString());
   return true;
 }
 public String toString(){
   String toReturn;
   if (start == null && end == null){
     toReturn = "{}";
     return toReturn;
   }
   if (start == end){
     toReturn = "{" + start.toString() + "}";
     return toReturn;
   }
   System.out.println("at line 49");
   Node current = start.next();
   System.out.println("at line 51");
   toReturn = "{" + start.toString();
   System.out.println("at line 53");
   while (current != null){
     toReturn += ", " + current.toString();
     current = current.next();
   }
   toReturn += "}";
   return toReturn;
 }
}
