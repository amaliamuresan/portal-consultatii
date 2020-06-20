import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MainPacientTest {

    @Test
    void parseJSONFile() throws IOException {
        JSONObject jsonObject=new JSONObject();
        JSONObject cerere=new JSONObject();
        cerere.put("docA","21");
        JSONArray cereri=new JSONArray();
        cereri.put(cerere);
        jsonObject.put("Cereri",cereri);
        jsonObject.put("adresa","add1");
        cereri.remove(0);
        jsonObject.put("Raspunsuri",cereri);
        assertEquals(jsonObject,MainPacient.parseJSONFile("src/main/resources/pacA.json"));
    }
}