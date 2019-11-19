// written by mayer379

public class CelestialDatabase {
    private List<Star> starList;

    // use string argument to determine data type used
    public CelestialDatabase(String type) {
        if (type.equals("array")) {
            starList = new ArrayList<Star>();
        }
        else if (type.equals("linked")) {
            starList = new LinkedList<Star>();
        }
    }

    public boolean add(Star s) {
        return starList.add(s);
    }

    // for loop checking each item against the given name
    public Star find(String name) {
        for (int i=0; i<starList.size(); i++) {
            String toCompare = starList.get(i).getName();
            if (toCompare.contains(name)) {
                return starList.get(i);
            }
        }
        return null;
    }

    // for loop running first checking if star is a sequence star, then calling isSun()
    public Star findSun() {
        for (int i=0; i<starList.size(); i++) {
            if (starList.get(i) instanceof Sequence) {
                Sequence toCompare = (Sequence) starList.get(i);
                if(toCompare.isSun()) {
                    return toCompare;
                }
            }
        }
        return null;
    }

    public Star remove(int index) {
        return starList.remove(index);
    }

    public Star get(int index) {
        return starList.get(index);
    }

    public void sort() {
        starList.sort(true);
    }

    // will return an array of stars of one type, iterates through the starList and adds them if type matches
    public Star[] getStarsByType(String type) {
        Star[] typeList = new Star[starList.size()];
        int count = 0;
        for (int i=0; i<starList.size(); i++) {
            if (type.equals("sequence")) {
                if (starList.get(i) instanceof Sequence) {
                    typeList[count] = starList.get(i);
                    count++;
                }
            }
            else if (type.equals("redgiant")) {
                if (starList.get(i) instanceof RedGiant) {
                    typeList[count] = starList.get(i);
                    count++;
                }
            }
            else if (type.equals("whitedwarf")) {
                if (starList.get(i) instanceof WhiteDwarf) {
                    typeList[count] = starList.get(i);
                    count++;
                }
            }
        }
        if (count == 0) {
            typeList = new Star[0];
            return typeList; // if no stars of given type were found, an empty list is returned
        }
        Star[] toReturn = new Star[count]; // typeList may have empty spaces, so a new array of proper size is created
        for (int i=0; i<count; i++) {
            toReturn[i] = typeList[i];
        }
        return toReturn;
    }

    // holeList is initialized to the same size as starList, and empty spaces are removed later
    public Star[] listBlackHoles() {
        Star[] holeList = new Star[starList.size()];
        int index = 0;
        for (int i=0; i<starList.size(); i++) {
            if (starList.get(i).isBlackHole()) {
                holeList[index] = starList.get(i);
                index++;
            }
        }
        Star[] toReturn = new Star[index];
        if (index > 0) {  // index > 0 is checked, won't enter for loop if there's nothing to be done
            for (int i=0; i<index; i++) {
                toReturn[i] = holeList[i];
            }
        }
        return toReturn;
    }

    public Star[] getTopKHeaviestStar(int k) {
        Star[] heavyList; // not given size at initialization, will check relative sizes of k and starlist first
        starList.sort(true);
        if (k <= starList.size()) { // block for k < starlist.length
            heavyList = new Star[starList.size()];
            for (int i=0; i<k; i++) {
                heavyList[i] = starList.get(i);
            }
        }
        else {  // block for k > starlist.length
            heavyList = new Star[k];
            for (int i=0; i<k; i++) {
                heavyList[i] = starList.get(i);
            }
        }
        return heavyList;
    }

    // returned array key: [0] = Sequence, [1] = Red Giant, [2] = White Dwarf
    public int[] countStars() {
        int[] count = new int[3];
        for (int i=0; i<starList.size(); i++) {
            if (starList.get(i) instanceof Sequence) {
                count[0]++;
            }
            else if (starList.get(i) instanceof RedGiant) {
                count[1]++;
            }
            else if (starList.get(i) instanceof WhiteDwarf) {
                count[2]++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CelestialDatabase stars = new CelestialDatabase("linked");
        Star s1 = new Sequence("Bitz",8000,200);
        Star s2 = new Sequence("Sol",2,430);
        Star r1 = new RedGiant("Apple",80,200);
        Star w1 = new WhiteDwarf("Dingo",600,200);
        Star w2 = new WhiteDwarf("Yop",2,430);
        stars.add(s1);
        stars.add(s2);
        stars.add(r1);
        stars.add(w1);
        stars.add(w2);
//        System.out.println(stars.find("Bitz"));
//        System.out.println(stars.findSun());
//        System.out.println(stars.remove(1)); // should remove Sol
        System.out.println(stars.findSun());
        System.out.println(stars.get(2));
        System.out.println(stars.remove(2));
        System.out.println(stars.get(2));
        stars.sort();
        System.out.println("\nThe new order of the stars is: \n");
        System.out.println(stars.get(0));
        System.out.println(stars.get(1));
        System.out.println(stars.get(2));
        System.out.println(stars.get(3));
        System.out.println(stars.get(4));
        stars.getStarsByType("whitedwarf");
        stars.listBlackHoles();
        stars.getTopKHeaviestStar(2);
        stars.countStars();
    }

}
