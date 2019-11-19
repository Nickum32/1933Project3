// written by mayer379

public class WhiteDwarf extends Star {

    public WhiteDwarf(String name, double mass, double size) {
        super(name, mass, size);
    }

    public boolean isBlackHole() {
        return false;
    }

    public String toString() {
        String returnStr = returnStr = this.getName() + ": A White Dwarf with mass = " + this.getMass() +
                " *10^30 KG; size = " + this.getSize()*5000 + "miles";
        return returnStr;
    }

    public static void main(String[] args) {
        Star test = new WhiteDwarf("Bitz",8000,200);
        Star sun = new WhiteDwarf("Sol",2,430);
        System.out.println(test);
        System.out.println(test.isBlackHole());
    }

}
