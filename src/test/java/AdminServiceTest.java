import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest extends TestCase {

    @Test
    void testencrypt() throws NoSuchAlgorithmException {
        assertEquals("182a15b93cd323556be21fd4fe8f3a8a",AdminService.encrypt("pac"));
    }

    @Test
    public void testPasswordEncoding() throws NoSuchAlgorithmException {
        assertNotEquals("testPass1", AdminService.encrypt("testPass1"));
    }

    @Test
    void testuserNotAlraedyExists() {
        Main.updateUsers();
        assertEquals(false,AdminService.userAlraedyExists("proiectfis"));
    }

    @Test
    void testuserAlraedyExists() {
        Main.updateUsers();
        assertEquals(true,AdminService.userAlraedyExists("pacient"));
    }

    @Test
    void verificareParafaInexistenta() throws IOException {
        Parafa.setCoduri();
        assertEquals(null,AdminService.verificareParafa("XYZ"));
    }

    @Test
    void verificareParafa() throws IOException {
        Parafa.setCoduri();
        Parafa parafa=new Parafa();
        parafa.setAvailable("1");
        parafa.setCod("EFG");
        assertEquals(parafa.toString(),AdminService.verificareParafa("EFG").toString());
    }

    @Test
    void verifyUser() throws NoSuchAlgorithmException, IOException {
        Main.updateUsers();
        assertEquals(true,AdminService.verifyUser("pac","pac"));
    }

    @Test
    void verifyWrongUser() throws NoSuchAlgorithmException, IOException {
        Main.updateUsers();
        assertEquals(false,AdminService.verifyUser("proiectfis","pac"));
    }

    @Test
    void returnRoleMedic() {
        Main.updateUsers();
        assertEquals("Medic",AdminService.returnRole("doc1"));
    }

    @Test
    void returnRolePacient() {
        Main.updateUsers();
        assertEquals("Pacient",AdminService.returnRole("pac"));
    }

    @Test
    void returnRole() {
        Main.updateUsers();
        assertNotEquals("Medic",AdminService.returnRole("pac"));
    }

    @Test
    void returnRoleInexistent() {
        Main.updateUsers();
        assertEquals(null,AdminService.returnRole("proiectfis"));
    }
}