import java.util.Hashtable;
import java.util.Set;
import java.util.Map.Entry;
/**
 * Represents a library
 */
public class Library extends Building implements LibraryRequirements {

    //Attributes
    private Hashtable<String, Boolean> collection;

    /* Default constructor */
    public Library() {
        this("<Name Unknown>", "<Address Unknown>", 1);
    }

    /** 
     * Overloaded constructor w/ just name 
     * @param name string name
    */
    public Library(String name) {
        this();
        this.name = name;
    }

    /** 
     * Overloaded constructor with name, address 
     * @param name
     * @param address
    */
    public Library(String name, String address) {
        this(name, address, 1); 
    }

    /**
     * Constructor
     * @param name string name of library
     * @param address string address of library
     * @param nFloors int number of floors
    */
    public Library(String name, String address, int nFloors) {
        super(name, address, nFloors);
        this.collection = new Hashtable<String, Boolean>();
        System.out.println("You have built a library: 📖");
    }

    /**
     * Adds a title to the colleciton
     * @param title string title of a book to add 
     */
    public void addTitle(String title) {
        collection.put(title, true);
    }

    /**
     * Removes a title from the collection
     * @param title string title to remove from collection
     * @return title that was removed
     * @throws RuntimeException if the title is not in the collection
     */
    public String removeTitle(String title) {
        if (!containsTitle(title)) {
        throw new RuntimeException("Title not found");
        } else {
        collection.remove(title);
        return title;
        }
    }

    /**
     * Checks out a title from collection, making it unavailable
     * @param title string title to check out
     * @throws RuntimeException if the title is not found in the collection
     * @throws RuntimeException if the title is not available
     */
    public void checkOut(String title) {
        if (!containsTitle(title)) {
            throw new RuntimeException("Title not found");
        }
        if (!isAvailable(title)) {
            throw new RuntimeException("Title not available");
        } else {
        collection.replace(title, false);
        }
    }

    /**
     * Returns a book, making it available
     * @param title to return 
     * @throws RuntimeException if title is not found in the collection
     * @throws RuntimeException if title is already available so it can't be returned
     */
    public void returnBook(String title) {
        if (!containsTitle(title)) {
            throw new RuntimeException("Title not found");
        } 
        if(isAvailable(title)) {
            throw new RuntimeException("Title is not checked out");
        } else {
        collection.replace(title, true);
        }
    }

    /**
     * Checks if the title is in the libary collection
     * @param title string title
     * @return boolean of whether title is in collection
     */
    public boolean containsTitle(String title) {
        if (collection.containsKey(title)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a given book is available
     * @param title string title
     * @return boolean of whether the book is available
     */
    public boolean isAvailable(String title) {
        if (collection.get(title) == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
    * Prints every book in the collection and its availability
    */
    public void printCollection() {
        Set<Entry<String, Boolean> > entrySet = collection.entrySet();
        for (Entry<String, Boolean> entry : entrySet) {
        String status;
        if (entry.getValue().equals(true)) {
            status = "Currently Available";
        } else {
            status = "Currently Checked Out";
        }
        System.out.println("Title: " + entry.getKey() + " ~ Availability: " + status);
        }
    }

    /**
     * Shows the user the options they can take in the library
     */
    @Override
    public void showOptions() {
        super.showOptions();
        System.out.println("\n + addTitle(title) \n + removeTitle(title) \n + checkOut(title) \n + returnBook(title) \n + isAvailable(title) \n + printCollection()");
    } 

    /**
     * Allows the reader to move to any floor, given it is a valid number (because there is an elevator)
     * @param floorNum the number of the floor the user wants to move to
     */
    @Override
    public void goToFloor(int floorNum) {
        super.goToFloor(floorNum);
    }
  
  public static void main(String[] args) {
    Library myLibrary = new Library("Nielson", "99 Green St", 4);
    myLibrary.showOptions();
    // myLibrary.addTitle("Little Women");
    // System.out.println(myLibrary.containsTitle("Little Women"));
  }
  
  }