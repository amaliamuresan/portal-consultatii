import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parafa {

    public static List<Parafa> listaCoduri;

    private String cod;
    private String available;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String avaible) {
        this.available = avaible;
    }

    public static void setCoduri() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/parafe.json");
        Parafa.listaCoduri = objectMapper.readValue(file, new TypeReference<List<Parafa>>() {});
    }

    public String toString()
    {
        return this.cod + " " + this.available;
    }


}
