package Tests;

import BE.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IsUserAdminTest {

    @DisplayName("Green if a User is an admin")
    @Test
    void userIsAdmin() {
        // Triple A - Pattern
        // Arrange - Setup test objects etc.
        User user = new User(0,"Børge Bobsen",true);
        // Act - do the actual calculations or method runs.
        boolean actualValue = user.isAdmin();
        boolean expectedValue = true;
        // Assert - check if actual value is equal to expected value.
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @DisplayName("Green if the User isn't an admin")
    @Test
    void userIsNotAdmin() {
        // Triple A - Pattern
        // Arrange - Setup test objects etc.
        User user = new User(2,"Jakob Hansen",false);
        // Act - do the actual calculations or method runs.
        boolean actualValue = user.isAdmin();
        boolean expectedValue = false;
        // Assert - check if actual value is equal to expected value.
        Assertions.assertEquals(expectedValue, actualValue);
    }



}