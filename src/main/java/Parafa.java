import java.util.ArrayList;

public class Parafa {

    public static ArrayList<String> coduriValide = new ArrayList<>();

    public static void AdaugaCoduri()
    {
        coduriValide.add("11234");
        coduriValide.add("12345");
        coduriValide.add("15671");
        coduriValide.add("14321");
        coduriValide.add("83203");
        coduriValide.add("84638");
        coduriValide.add("92732");
        coduriValide.add("valid");
    }
    public static int VerificareCod(String cod)
    {
        Parafa.AdaugaCoduri();
        if(coduriValide.contains(cod))
        {
            return 1;
        }
        return 0;
    }
}
