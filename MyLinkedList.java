import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;
public class MyLinkedList{
 private int size;
 private Node start,end;

 public static void main(String[] args){
   try{
     MyLinkedList list = new MyLinkedList();
     System.out.println(list.toString());
     list.add(4);
     list.add(23);
     System.out.println(list.toString());
     System.out.println("" + list.size());
     System.out.println("0th index: " + list.get(0));
     System.out.println("1st index: " + list.get(1));
     //System.out.println("2nd index: " + list.get(2));
     list.set(1, 34);
     System.out.println("set 1st index to 34, proof: " + list.get(1));
     System.out.println(list.toString());
     System.out.println("Does this list contain 4?: " + list.contains(4));
     System.out.println("What about 5?: " + list.contains(5));
     System.out.println("What's the index of 34?: " + list.indexOf(34));
     System.out.println("What's the index of 568?: " + list.indexOf(568));
     list.add(1, 65);
     System.out.println("Added 65: " + list.toString());
     System.out.println("Current size: " + list.size());
     list.remove(1);
     System.out.println("Removed 65: " + list.toString());
     Integer intObj1 = new Integer(34);
     list.remove(intObj1);
     System.out.println("Removed 34 using remove by type: " + list.toString());
   }catch(IndexOutOfBoundsException e){
     System.out.println(e);
   }
 }

 public MyLinkedList(){
   size = 0;
   start = null;
   end = null;
 }

 public void clear(){
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
   return true;
 }

 private Node getNthNode(int nth) throws IndexOutOfBoundsException{
   if (nth >= size || nth < 0){
     throw new IndexOutOfBoundsException("requested index out of bounds: " + nth);
   }
   Node current = start;
   for (int currentIndex = 0; currentIndex < nth; currentIndex++){
     current = current.next();
   }
   return current;
 }

 public Integer get(int i) throws IndexOutOfBoundsException{
   if (i >= size || i < 0){
     throw new IndexOutOfBoundsException("GETrequested index out of bounds: " + i);
   }
   return getNthNode(i).getData();
 }

 public Integer set(int i, Integer value) throws IndexOutOfBoundsException{
   if (i >= size || i < 0){
     throw new IndexOutOfBoundsException("SETrequested index out of bounds: " + i);
   }
   Integer toReturn = this.get(i);
   getNthNode(i).setData(value);
   return toReturn;
 }

 public boolean contains(Integer value){
   for (int i = 0; i<size; i++){
     if (this.get(i).equals(value)){
       return true;
     }
   }
   return false;
 }

 public int indexOf(Integer value){
   for (int i = 0; i<size; i++){
     if (this.get(i).equals(value)){
       return i;
     }
   }
   return -1;
 }

 public void add(int index,Integer value){
   if (index >= size || index<0){
     throw new IndexOutOfBoundsException("ADDrequested index out of bounds: " + index);
   }
   Node toAdd = new Node(value, this.getNthNode(index), this.getNthNode(index-1));
   this.getNthNode(index).setPrev(toAdd);
   this.getNthNode(index-1).setNext(toAdd);
   size+=1;
 }

 public Integer remove(int index) throws IndexOutOfBoundsException{
   if (index >= size || index<0){
     throw new IndexOutOfBoundsException("REMOVErequested index out of bounds: " + index);
   }
   Integer toReturn = this.get(index);
   if (index == size-1){
     end = end.prev();
   }
   else if (index == 0){
     start = start.next();
   }
   else{
     //you have to reset the prev first, because getNthNode depends on the setNext to get node. once you setNext from the previous
     //to pass over the current one, (index+1) is now out of bounds in some cases, like removing a middle element from a list of length 3
     getNthNode(index+1).setPrev(getNthNode(index-1));
     getNthNode(index-1).setNext(getNthNode(index+1));
     //getNthNode(index+1).setPrev(getNthNode(index-1));
   }
   size-=1;
   return toReturn;
 }

 public boolean remove(Integer value){
   //this function is 0[n^2] !!! so slow
   try{
     remove(this.indexOf(value));
   }catch(IndexOutOfBoundsException e){
     return false;
   }
   size-=1;
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
   Node current = start.next();
   toReturn = "{" + start.toString();
   while (current != null){
     toReturn += ", " + current.toString();
     current = current.next();
   }
   toReturn += "}";
   return toReturn;
 }
}
