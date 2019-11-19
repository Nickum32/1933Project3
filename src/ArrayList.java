// written by mayer379

public class ArrayList<T extends Comparable <T>> implements List<T> {

    // size will keep track of the first empty index in the array
    private int size = 0;
    private T[] starsArray;

    public ArrayList() {
        starsArray = (T[]) new Comparable[2];
    }

    // this add method will put element at the end of the array, and will resize the array if the new element
    // occupies the final spot in the array
    public boolean add(T element) {
        if (element == null) { return false; }
        else {
            starsArray[size] = element;
            size++;
            if (size == starsArray.length) {
                grow();
            }
            return true;
        }
    }

    // this add method will place a new element at the desired index after moving all necessary existing elements
    // forward one position within the array, it will extend the array if there are no more empty spaces
    public boolean add(int index, T element) {
        if (element == null) { return false; }
        else {
            for (int i=(size-1); i>=index; i--) {
                starsArray[i+1] = starsArray[i];
            }
            starsArray[index] = element;
            size++;
            if (size == starsArray.length) {
                grow();
            }
            return true;
        }
    }

    // clear will eliminate all element within starsArray and reset size to 0
    public void clear() {
        starsArray = (T[]) new Comparable[2];
        size = 0;
    }

    // contains will check each existing element within starsArray to check for equality to the argument
    public boolean contains(T element) {
        for (int i=0; i<size; i++) {
            if (starsArray[i].equals(element)) { return true; }
        }
        return false;
    }

    public T get(int index) {
        if (index >= 0 && index < starsArray.length) {
            return starsArray[index];
        }
        else { return null; }
    }

    public int indexOf(T element) {
        for (int i=0; i<size; i++) {
            if (starsArray[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        if (size == 0) { return true; }
        else { return false; }
    }

    public int lastIndexOf(T element) {
        for (int i = size - 1; i >= 0; i--) {
            if (starsArray[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public T set(int index, T element) {
        if (index >= 0 && index < size) {
            T oldElement = starsArray[index];
            starsArray[index] = element;
            return oldElement;
        }
        return null;
    }

    public int size() {
        return size;
    }

    // if order == true, sort in ascending alphabetical order (i.e. A,B,C)
    // if order == false, sort in descending alphabetical order (i.e. C,B,A)
    // sort() will use a selection sort
    public void sort(boolean order) {
        T hold; // hold will store the element that is being swapped out
        for (int i=0; i<size; i++) {
//            if (starsArray[i] == null) {
//                break;
//            }
            for (int j=i+1; j<size; j++) {
//                if (starsArray[j] == null) {
//                    break;
//                }
                if (order == true) { // if block for sorting in ascending order
                    if (starsArray[i].compareTo(starsArray[j]) > 0) {
                        hold = starsArray[j];
                        starsArray[j] = starsArray[i];
                        starsArray[i] = hold;
                    }
                }
                else if (order == false) { // if block for sorting in descending order
                    if (starsArray[i].compareTo(starsArray[j]) < 0) {
                        hold = starsArray[j];
                        starsArray[j] = starsArray[i];
                        starsArray[i] = hold;
                    }
                }
            }
        }
    }

    public boolean remove(T element) {
        for (int i=0; i<size; i++) {
            if (starsArray[i] == element) {
                for(int j=i; j<size; j++) {
                    starsArray[j] = starsArray[j+1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    public T remove(int index) {
        if (index >= 0 && index < size) {
            T toReturn = starsArray[index]; // toReturn stores the element that is being removed
            for (int i=index; i<size; i++){
                starsArray[i] = starsArray[i+1];
            }
            size--;
            return toReturn;
        }
        else { return null; }
    }

    //grow will be used when the array length needs to be doubled
    private void grow() {
        int newSize = starsArray.length * 2;
        T[] doubleSize = (T[]) new Comparable[newSize];
        for (int i=0; i<starsArray.length; i++) {
            doubleSize[i] = starsArray[i];
        }
        starsArray = doubleSize;
    }

    public static void main(String[] args) {
        String n1 = "Boy";
        String n2 = "Apple";
        String n3 = "Zebra";
        String n4 = "Home";
        ArrayList myList = new ArrayList();
        System.out.println(myList.isEmpty());
        myList.add(n1);
        myList.add(0, n2);
        myList.add(1, n3);
        myList.add(n3);
        System.out.println(myList.contains(n1));
        System.out.println(myList.contains(n4));
        System.out.println(myList.get(0));
        System.out.println(myList.get(40));
        System.out.println(myList.indexOf(n1));
        System.out.println(myList.indexOf(n4));
        System.out.println(myList.lastIndexOf(n3));
        System.out.println(myList.set(3,n2));
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
