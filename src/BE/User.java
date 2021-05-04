package BE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private final int id;
    private String fullName;
    private boolean isAdmin;

    public User(int id, String fullName, Boolean isAdmin){
        this.id = id;
        this.fullName = fullName;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
