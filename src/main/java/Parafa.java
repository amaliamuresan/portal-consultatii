import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parafa {

    public static List<Parafa> listaCoduri;

    private String cod;
    private String avaible;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getAvaible() {
        return avaible;
    }

    public void setAvaible(String avaible) {
        this.avaible = avaible;
    }

    public static void setCoduri() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("parafe.json");
        Parafa.listaCoduri = objectMapper.readValue(file, new TypeReference<List<Parafa>>() {});
    }
}
