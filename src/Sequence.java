// written by mayer379

public class Sequence extends Star {

    public Sequence(String name, double mass, double size) {
        super(name, mass, size);
    }

    public boolean isBlackHole() {
        if (this.getMass() > 1000) {
            return true;
        }
        else { return false; }
    }

    public String toString() {
        String returnStr = returnStr = this.getName() + ": A Sequence Star with mass = " + this.getMass() +
                " *10^30 KG; size = " + this.getSize()*5000 + "miles";
        return returnStr;
    }

    public boolean isSun() {
        if (this.getMass() == 2 && this.getSize() == 430) {
            return true;
        }
        else { return false; }
    }

    public static void main(String[] args) {
        Star test = new Sequence("Bitz",8000,200);
        Star sun = new Sequence("Sol",2,430);
        System.out.println(test);
        System.out.println(test.isBlackHole());
        System.out.println(((Sequence) sun).isSun());
        System.out.println(sun.compareTo(test));
    }

}
