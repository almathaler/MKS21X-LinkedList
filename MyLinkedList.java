import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;
public class MyLinkedList{
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
     Integer intObj1 = Integer.valueOf(34);
     list.remove(intObj1);
     System.out.println("Removed 34 using remove by type: " + list.toString());
     list.clear();
     System.out.println("Cleared the list. What does it look like?: " + list.toString());
     System.out.println("Tried getting 1st index value: " + list.get(1));
   }catch(IndexOutOfBoundsException e){
     System.out.println(e);
   }
 }

 public MyLinkedList(){
   size = 0;
   start = null;
   end = null;
 }

 public void clear(){ //just make it no longer point to start and end
    start = null;
    end = null;
    size = 0;
 }
 public int size(){
   return size;
 }

 public boolean add(Integer value){
   Node toAdd = new Node(value, null, null); //make a new node with the value we want, but don't yet link it to list
   if (start == null && end == null){ //if you're adding to an empty list, make toAdd both start and end
     start = toAdd; //but don't make toAdd point to itself as next and prev, that's bad
     end = toAdd;
   }
   else{ //however we will add a next to the hypothetical only element here where we:
     end.setNext(toAdd); //make whatever is the current end point to Node we are adding
     //toAdd.setNext(null); //make toAdd's next null(kinda irrelevant because it was already null -- test if this can be removed) -- seems like it can be
     toAdd.setPrev(end); //make whatever was added point back to what was before the end
     end = toAdd; //give the title of end to the node that was added
   }
   size+=1; //don't forget to increase size
   return true; //since we did it, true
 }

 private Node getNthNode(int nth) throws IndexOutOfBoundsException{
   if (nth >= size || nth < 0){ //if nth isn't an actual index, give exception
     throw new IndexOutOfBoundsException("requested index out of bounds: " + nth);
   }
   Node current = start; //starting from the start (0th index) node;
   for (int currentIndex = 0; currentIndex < nth; currentIndex++){ //cycle through n times, starting from 0th, setting current to one after current
     current = current.next(); //it's appropriate to make <n and not <=n cuz this block set's current to whatever's after, what's after the (n-1)th index is n
   }
   return current; //this method is LINEAR
 }

 public Integer get(int i) throws IndexOutOfBoundsException{
   if (i >= size || i < 0){ //the only reason we through exception here and not in getNthIndex is for clarity
     throw new IndexOutOfBoundsException("GETrequested index out of bounds: " + i);
   }
   return getNthNode(i).getData(); //use getNthNode and then return value -- user doesn't need to deal with node object
 }

 public Integer set(int i, Integer value) throws IndexOutOfBoundsException{
   if (i >= size || i < 0){ //same explanation for get
     throw new IndexOutOfBoundsException("SETrequested index out of bounds: " + i);
   }
   //Integer toReturn = this.get(i); //we return the old value, getting old value is 0(N)
   //getNthNode(i).setData(value); //another linear pass
   return getNthNode(i).setData(value); //return old value
 }

 public boolean contains(Integer value){
   Node current = start;
   for (int i = 0; i<size; i++){ //go through entire list
     if (current.getData().equals(value)){ //NOT GOOD THIS METHOD IS QUADRATIC!!! NO -- MAKE IT LINEAR
       return true; //made it linear by not using the get() method but by using currnet = start, current = current.next() instead of repeated gets
     }
     current = current.next();
   }
   return false;
 }

 public int indexOf(Integer value){ //AGAIN THIS IS QUADRATIC MAKE IT LINEAR BY USING CURRENT VARIABLE
   Node current = start;
   for (int i = 0; i<size; i++){
     if (current.getData().equals(value)){
       return i;
     }
     current = current.next();
   }
   return -1;
 }
//remember, you don't have to declare new Integers you just have to do Integer.valueOf(#youwant)
 public void add(int index,Integer value){
   if (index > size || index<0){ //we can add to size index, that's just adding to the end
     throw new IndexOutOfBoundsException("ADDrequested index out of bounds: " + index);
   }
   if (index == size){
     this.add(value);
   }
   else if (index == 0){
     Node toAdd = new Node(value, start, null);
     start.setPrev(toAdd);
     start = toAdd;
     size +=1;
   }
   else{ //check, adding to the beginning of the list will make error since index-1 is outofbounds
     Node toAdd = new Node(value, this.getNthNode(index), this.getNthNode(index-1));
     this.getNthNode(index).setPrev(toAdd);
     this.getNthNode(index-1).setNext(toAdd);
     size+=1;
   }
 }

 public Integer remove(int index) throws IndexOutOfBoundsException{
   if (index >= size || index<0){
     throw new IndexOutOfBoundsException("REMOVErequested index out of bounds: " + index);
   }
   Integer toReturn = this.get(index);
   if (index == size-1){
     end = end.prev();
     end.setNext(null); //if this wasn't done, toString would print out old ends
   }
   else if (index == 0){
     start = start.next();
     start.setPrev(null);
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
   //size-=1; if this was kept, removing by value would make size-=2
   return true;
 }

 public String toString(){
   String toReturn;
   if (start == null && end == null){
     toReturn = "[]";
     return toReturn;
   }
   if (start == end){
     toReturn = "[" + start.toString() + "]";
     return toReturn;
   }
   Node current = start.next();
   toReturn = "[" + start.toString();
   while (current != null){
     toReturn += ", " + current.toString();
     current = current.next();
   }
   toReturn += "]";
   return toReturn;
 }
}
