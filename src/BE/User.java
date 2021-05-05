package BE;

public class User {

    private final int id;
    private String fullName;
    private boolean isAdmin;

    /**
     * Constructor for the User class.
     * @param id
     * @param fullName
     * @param isAdmin
     */
    public User(int id, String fullName, Boolean isAdmin){
        this.id = id;
        this.fullName = fullName;
        this.isAdmin = isAdmin;
    }

    /**
     * Getter method for the id int.
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Getter method for the fullName String.
     * @return
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Setter method for the fullName String.
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Boolean method for the isAdmin variable.
     * @return
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Setter method for the isAdmin boolean.
     * @param admin
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
