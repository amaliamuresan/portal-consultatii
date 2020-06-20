import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonUserTest {

    @Test
    void getUsername() {
        JsonUser user=new JsonUser();
        user.setUsername("username");
        assertEquals("username",user.getUsername());
    }

    @Test
    void getPassword() {
        JsonUser user=new JsonUser();
        user.setPassword("password");
        assertEquals("password",user.getPassword());
    }

    @Test
    void getRole() {
        JsonUser user=new JsonUser();
        user.setRole("medic");
        assertEquals("medic",user.getRole());
    }

    @Test
    void testEquals() {
        JsonUser user=new JsonUser("username","password","medic"), anotherUser=new JsonUser("username","password","medic");
        assertEquals(true,user.equals(anotherUser));
    }

    @Test
    void testNotEquals() {
        JsonUser user=new JsonUser("usernam","password","medic"), anotherUser=new JsonUser("username","password","medic");
        assertNotEquals(true,user.equals(anotherUser));
    }
}