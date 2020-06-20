import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParafaTest {

    @Test
    void getCod() {
        Parafa parafa=new Parafa();
        parafa.setCod("abracadabra");
        assertEquals("abracadabra",parafa.getCod());
    }

    @Test
    void getAvailable() {
        Parafa parafa=new Parafa();
        parafa.setAvailable("1");
        assertEquals("1",parafa.getAvailable());
    }

    @Test
    void testToString() {
        Parafa parafa=new Parafa();
        parafa.setCod("abra");
        parafa.setAvailable("0");
        assertEquals("abra 0",parafa.toString());
    }
}