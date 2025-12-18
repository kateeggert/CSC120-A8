/**
 * Represents a Cafe
 */
public class Cafe extends Building implements CafeRequirements {

    // Attributes
    private int nCoffeeOunces; // The number of ounces of coffee remaining in inventory
    private int nSugarPackets; // The number of sugar packets remaining in inventory
    private int nCreams; // The number of "splashes" of cream remaining in inventory
    private int nCups; // The number of cups remaining in inventory

    /* Default constructor */
    public Cafe() {
        this("<Name Unknown>", "<Address Unknown>", 1, 100, 20, 20, 20);
    }
    
    /** 
     * Overloaded constructor w/ just name 
     * @param name string name
    */
    public Cafe(String name) {
        this();
        this.name = name;
    }
    
    /** 
     * Overloaded constructor with name, address 
     * @param name
     * @param address
    */
    public Cafe(String name, String address) {
        this(name, address, 1, 100, 20, 20, 20); 
    }

    /**
     * Constructor
     * @param name string cafe name
     * @param address string cafe address
     * @param nFloors int number of floors
     * @param nCoffeeOunces int number of oz of coffee
     * @param nSugarPackets int number of sugar packets
     * @param nCreams int number of creams
     * @param nCups int number of cups
     */
    public Cafe(String name, String address, int nFloors, int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        super(name, address, nFloors);
        this.nCoffeeOunces = nCoffeeOunces;
        this.nSugarPackets = nSugarPackets;
        this.nCreams = nCreams;
        this.nCups = nCups;
        System.out.println("You have built a cafe: ☕");
    }

    /**
     * Sells a cup of coffee with the items needed
     * @param size int of coffee size
     * @param nSugarPackets int of sugar packets in the coffee
     * @param nCreams int of number of creams for the coffee
     */
    public void sellCoffee(int size, int nSugarPackets, int nCreams) {
        while (true) {
            // given that there is enough materials, it makes the coffee. otherwise, it restocks then makes the coffee
            if (this.nCoffeeOunces - size >= 0 && this.nSugarPackets >= nSugarPackets && this.nCreams >= nCreams && this.nCups >= 1) {
                this.nCoffeeOunces -= size;
                this.nSugarPackets -= nSugarPackets;
                this.nCreams -= nCreams;
                this.nCups -= 1;
                System.out.println("Here is your coffee!");
                break;
            } else {
                System.out.println("One moment, we are restocking...");
                restock(100, 20, 20, 10);
            }
        }
    }

    /**
     * Refills the quanitity of each item with the given params
     * @param nCoffeeOunces int number of coffee ounces to restock to
     * @param nSugarPackets int num of sugar packets to restock to
     * @param nCreams int num of creams to restock to
     * @param nCups int num of cups to restock to
     */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        this.nCoffeeOunces += nCoffeeOunces;
        this.nSugarPackets += nSugarPackets;
        this.nCreams += nCreams;
        this.nCups += nCups;
    }

    /**
     * Shows the user options they can take in this cafe
     */
    @Override
    public void showOptions() {
        super.showOptions();
        System.out.println("\n + sellCoffee(size, nSugarPackets, nCreams)");
    } 

    /**
     * Stops the user from moving off of the first floor
     * @param floorNum the number of the floor the user wants to move to
     */
    @Override
    public void goToFloor(int floorNum) {
        if (this.activeFloor == -1) {
            throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
        }
        if (floorNum != 1) {
            throw new RuntimeException("Must remain on the first floor of " + this.name);
        }
        System.out.println("You are now on floor #" + floorNum + " of " + this.name);
        this.activeFloor = floorNum;
    }
    
    public static void main(String[] args) {
        Cafe myCafe = new Cafe("Woodstar", "123", 1, 100, 10, 10, 10);
        myCafe.showOptions();
        myCafe.enter();
        myCafe.goToFloor(1);
        myCafe.goToFloor(7);
    }
    
}
