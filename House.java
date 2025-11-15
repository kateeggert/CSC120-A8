import java.util.ArrayList;

public class House extends Building implements HouseRequirements {
  
    // Attributes
    private ArrayList<Student> residents; // The <Student> tells Java what kind of data we plan to store IN the ArrayList
    private boolean hasDiningRoom;
    private boolean hasElevator;

    /* Default constructor w/ no add ons */
    public House() {
        this("<Name Unknown>", "<Address Unknown>", 1, false, false);
    }

    /** 
     * Overloaded constructor w/ just name 
     * @param name string name
    */
    public House(String name) {
        this();
        this.name = name;
    }

    /** 
     * Overloaded constructor with name, address 
     * @param name
     * @param address
    */
    public House(String name, String address) {
        this(name, address, 1, false, false); 
    }

    /**
     * Constructor
     * @param name String house name
     * @param address String house address
     * @param nFloors int number of floors in house
     * @param hasDiningRoom boolean if the house has a dining room
     * @param hasElevator boolean if the house has an elevator
     */
    public House(String name, String address, int nFloors, boolean hasDiningRoom, boolean hasElevator) {
        super(name, address, nFloors);
        this.residents = new ArrayList<Student>();
        this.hasDiningRoom = hasDiningRoom;
        this.hasElevator = hasElevator;
        System.out.println("You have built a house: 🏠");
    }

    /**
     * Checks if the house has a dining room
     * @return boolean of whether the house has a dining room
     */

    public boolean hasDiningRoom() {
        return hasDiningRoom;
    }

    /**
     * Gets the number of residents living in the house
     * @return int number of residents 
     */
    public int nResidents() {
        return residents.size();
    }

    /**
     * Adds a student as a resident in the house
     * @param s student to move in to the house
     * @throws RuntimeException if student has already been added to the house
     */
    public void moveIn(Student s) {
        if (isResident(s)) {
        throw new RuntimeException("Student already lives here!");
        } else {
        residents.add(s);
        }
        
    }
    
    /**
     * Removes a student resident from the house
     * @param s student to remove from the house
     * @return student that was removed
     * @throws RuntimeException if student is not already in the house
     */
    public Student moveOut(Student s) {
        if (!isResident(s)) {
        throw new RuntimeException("Student not found");
        } else {
        residents.remove(s);
        return s;
        }
    }

    /**
     * Checks if a student is residing in the house
     * @param s student to check if they are a resident
     * @return boolean if the student is a resident or not
     */
    public boolean isResident(Student s) {
        if (residents.contains(s)) {
        return true;
        } else {
        return false;
        }
    }

    /**
     * Prints out the options a user can take in the house
     */
    @Override
        public void showOptions() {
            System.out.println("Available options at " + this.name + ":\n + enter() \n + exit() \n + goUp() \n + goDown()\n + goToFloor(n) \n + hasDiningRoom() \n + nResidents() \n + moveIn(s) \n + moveOut(s) \n + isResident(s)");
        } 

    /**
     * Lets the user move one floor if no elevator, and to any floor if there is an elevator
     * @param floorNum floor number to move to
     */
    @Override
    public void goToFloor(int floorNum) {
        if (this.activeFloor == -1) {
            throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
        }
        if (floorNum < 1 || floorNum > this.nFloors) {
            throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
        }
        if (!this.hasElevator) {
            if (this.activeFloor - floorNum > 1 || this.activeFloor-floorNum < -1) {
                throw new RuntimeException("Cannot move more than one floor at a time because " + this.name + " doesn't have an elevator.");
            }
        }
        System.out.println("You are now on floor #" + floorNum + " of " + this.name);
        this.activeFloor = floorNum;
    }

    public static void main(String[] args) {
        House myHouse = new House("home", "6047 28th", 10, true, false);
        myHouse.showOptions();
        myHouse.enter();
        myHouse.goToFloor(1);
        // System.out.println(myHouse.hasDiningRoom);
        // Student kate = new Student("Kate", "987055", 2028);
        // Student naomi = new Student("Naomi", "987056", 2028);
        // myHouse.moveIn(kate);
        // myHouse.moveIn(kate);
        // System.out.println(myHouse.isResident(kate));
        // System.out.println(myHouse.moveOut(naomi));
        // System.out.println(myHouse.isResident(kate));
    }

    }