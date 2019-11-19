public class LinkedList<T extends Comparable<T>> implements List<T> {
    private NGen start;
    private NGen ptr;
    private NGen trailer;
    private int size = 0;
    private NGen tail;

    public LinkedList() {
        start = new NGen();
        ptr = start;
    }

    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        else {
            if (size == 0) {
                start = new NGen(element, null);
                tail = start;
                size++;
            }
            else {
                tail.setNext(new NGen(element, null));
                tail = tail.getNext();
                size++;
            }
            return true;
        }
    }

    // add will check if element is null, then go through adding based on the given index
    public boolean add(int index, T element) {
        trailer = start;
        ptr = start.getNext();
        if (index > size) {
            return false;
        }
        if (element == null) {
            return false;
        }
        else if (index == 0) {
            start = new NGen(element, start);
        }
        for (int i=1; i <= index; i++) {
            if (i==index-1) { // need to use (index-1), as trailer is tied to the current i value
                trailer.setNext(new NGen(element, ptr));
            }
            else {
                trailer = ptr;
                ptr = ptr.getNext();
            }

        }
        size++;
        return true;
    }

    // clear simply starts fresh, ignoring all previous data, no garbage collection is done
    public void clear() {
        start = new NGen();
        tail = start;
        size = 0;
    }

    // contains will iterate through the linked list, returning true if the element is found
    public boolean contains(T element) {
        ptr = start;
        for (int i=0; i<= size; i++) {
            if (element.equals(ptr.getData())) {
                return true;
            }
        }
        return false;
    }

    // get uses a type cast (T) as ptr.getData() will return an Object type
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        ptr = start;
        for (int i=0; i<index; i++) {
            ptr = ptr.getNext();
        }
        return (T) ptr.getData();
    }

    // once again, using iteration through the linked list, stopping when the desired element is found
    public int indexOf(T element) {
        ptr = start;
        for (int i=0; i<size; i++) {
            if (element.equals(ptr.getData())) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        if (size == 0) { return true; }
        else { return false; }
    }

    // similar to indexOf, but it will iterate through the entire list, storing the value of the highest matching index
    public int lastIndexOf(T element) {
        int last = -1;
        ptr = start;
        for (int i=0; i<size; i++) {
            if (element.equals(ptr.getData())) {
                last = i;
            }
        }
        return last;
    }

    // set will iterate through to the space desired, using trailer.setNext to like the new node behind, and
    // creating the new node with ptr as the next node
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            return null;
        }
        ptr = start.getNext();
        trailer = start;
        T toReturn;
        if (index==0) { // a special case is required if the new node is going at index 0
            toReturn = (T) start.getData();
            start.setData(element);
            start.setNext(ptr);
            return toReturn;
        }
        for (int i=1; i<size; i++) { // for loop starts at one, as index == 0 is handled in previous block
            if (i==index) {
                toReturn = (T) ptr.getData();
                trailer.setNext(new NGen(element, ptr.getNext()));
                return toReturn;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    // sort method uses a selection sort
    public void sort(boolean order) {
        if (size == 1) {
            return;
        }
        trailer = start;
        ptr = start.getNext();
        for (int i=0; i<size-1; i++) { // first loop gives the base item of comparison
            for (int j=size-i-1; j<size; j++) { // second for loop gives the second item for comparison
                if (ptr == null){
                    continue;
                }
                T trlData = (T) trailer.getData(); // pulling the data out of each node for comparison
                T ptrData = (T) ptr.getData();
                if (order == true) { // if block for sorting in ascending order
                    if (trlData.compareTo(ptrData) > 0) {
                        T tempData = (T) trailer.getData();
                        trailer.setData(ptrData);
                        ptr.setData(tempData);
                    }
                }
                else if (order == false) { // if block for sorting in descending order
                    if (trlData.compareTo(ptrData) < 0) {
                        T tempData = (T) trailer.getData();
                        trailer.setData(ptrData);
                        ptr.setData(tempData);
                    }
                }
            }

            trailer = trailer.getNext();
            ptr = trailer.getNext();
        }
    }

    public boolean remove(T element) {
        if (size == 0) { // special case is again required if size == 0
            return false;
        }
        else if (size == 1) { // special case also required if there's only 1 element in the list
            if (element.equals(start.getData())){
                start = null;
                size = 0;
                return true;
            }
        }
        trailer = start;
        ptr = start.getNext();
        for (int i=0; i<size; i++) { // main block for removing an element
            if (element.equals(ptr.getData())){
                trailer.setNext(ptr.getNext());
                size--;
                return true;
            }
        }
        return false;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        trailer = start;
        ptr = start.getNext();
        if (index == 0) { // special case for removing the first item
            NGen toReturn = start;
            start = start.getNext();
            size--;
            return (T) toReturn.getData();
        }
        for (int i=0; i<=index; i++) {
            if (i == index-1) {
                NGen toReturn = ptr;
                trailer.setNext(ptr.getNext());
                size--;
                return (T) toReturn.getData();
            }
            trailer = ptr;
            ptr = ptr.getNext();
        }
        return null;
    }

    public static void main(String[] args) {
        Star s1 = new Sequence("Bitz",8000,200);
        Star s2 = new Sequence("Sol",2,430);
        Star r1 = new RedGiant("Apple",80,200);
        Star w1 = new WhiteDwarf("Dingo",600,200);
        Star w2 = new WhiteDwarf("Yop",2,430);
        ArrayList myList = new ArrayList();
        System.out.println(myList.isEmpty());
        myList.add(s1);
        myList.add(0, s2);
        myList.add(1, r1);
        myList.add(w1);
//        System.out.println(myList.contains(s1));
//        System.out.println(myList.contains(w2));
//        System.out.println(myList.get(0));
//        System.out.println(myList.get(40));
//        System.out.println(myList.indexOf(s2));
//        System.out.println(myList.indexOf(r1));
//        System.out.println(myList.lastIndexOf(s1));
        System.out.println(myList.set(3, s1));
        System.out.println(myList.size());
        myList.sort(true);
        System.out.println(myList.get(0));
        System.out.println(myList.get(1));
        System.out.println(myList.get(2));
        System.out.println(myList.get(3));
        System.out.println(myList.remove(0));
        System.out.println(myList.remove(0));
        System.out.println(myList.get(0));
        myList.clear();
        System.out.println(myList.isEmpty());

    }
}
