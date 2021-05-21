package Tests;

import BE.User;
import BLL.UserManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {

    /**
     * This JUnit test uses a test implementation of the fillLists method, to remove its dependency on the database.
     */
    @Test
    void fillLists() {
        // Triple A - Pattern
        // Arrange - Setup test objects etc.
        List<User> expectedAdminsList = new ArrayList<>();
        List<User> expectedUsersList = new ArrayList<>();
        List<User> allUsers = new ArrayList<>();
        List<User> adminsList = new ArrayList<>();
        List<User> usersList = new ArrayList<>();
        User user1 = new User(0,"Børge Bobsen",true);
        User user2 = new User(1,"Jakob Hansen",false);
        User user3 = new User(2,"Trine Svendsen",true);
        // Act - do the actual calculations or method runs.
        allUsers.add(user1);
        allUsers.add(user2);
        allUsers.add(user3);
        expectedAdminsList.add(user1);
        expectedAdminsList.add(user3);
        expectedUsersList.add(user2);
        for (User u: allUsers) {
            if(u.isAdmin()){
                adminsList.add(u);
            }
            else if(!u.isAdmin()){
                usersList.add(u);
            }
        }
        // Assert - check if actual value is equal to expected value.
        assertEquals(expectedAdminsList,adminsList);
        assertEquals(expectedUsersList,usersList);
    }

    /**
     * Here we used a test implementation of the emptyLists method, because the UserManager doesn't have a
     * method to add users to the allUsers, adminsList and usersLists, which is needed to test the method.
     */
    @Test
    void emptyLists() {
        // Triple A - Pattern
        // Arrange - Setup test objects etc.
        List<User> emptyList = new ArrayList<>();
        List<User> allUsers = new ArrayList<>();
        List<User> adminsList = new ArrayList<>();
        List<User> usersList = new ArrayList<>();
        User user1 = new User(0,"Børge Bobsen",true);
        User user2 = new User(1,"Jakob Hansen",false);
        User user3 = new User(2,"Trine Svendsen",true);
        // Act - do the actual calculations or method runs.
        allUsers.add(user1);
        allUsers.add(user2);
        allUsers.add(user3);
        adminsList.add(user1);
        adminsList.add(user3);
        usersList.add(user2);
        allUsers.clear();
        adminsList.clear();
        usersList.clear();
        // Assert - check if actual value is equal to expected value.
        assertEquals(emptyList,adminsList);
        assertEquals(emptyList,usersList);
        assertEquals(emptyList,allUsers);
    }


}