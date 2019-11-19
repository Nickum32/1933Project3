// written by mayer379

public class RedGiant extends Star {

    public RedGiant(String name, double mass, double size) {
        super(name, mass, size);
    }

    public boolean isSuperGiant() {
        if (this.getMass() > 100) {
            return true;
        }
        else { return false; }
    }

    public boolean isBlackHole() {
        return isSuperGiant();
    }

    public String toString() {
        String returnStr = this.getName() + ": A  ";
        if (isSuperGiant()) {
            returnStr = returnStr + "Super Giant";
        }
        else {
            returnStr = returnStr + "Red Giant";
        }
        returnStr = returnStr + " with mass = " + this.getMass() +
                " *10^30 KG; size = " + this.getSize()*5000 + "miles";
        return returnStr;
    }

    public static void main(String[] args) {
        Star test = new RedGiant("Bitz",80,200);
        System.out.println(test);
        System.out.println(test.isBlackHole());
        System.out.println(((RedGiant) test).isSuperGiant());

    }

}
