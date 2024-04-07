import java.util.*;

public class BasketSplitterTestResources {

    public static List<String> getBasketSplitterInputData() {
        List<String> inputData = new ArrayList<>();
        inputData.add("Steak (300g)");
        inputData.add("Carrots (1kg)");
        inputData.add("Cold Beer (330ml)");
        inputData.add("AA Battery (4 Pcs.)");
        inputData.add("Espresso Machine");
        inputData.add("Garden Chair");

        return inputData;
    }

    public static Map<String, List<String>> getBasketSplitterOutputData() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("Express Delivery", new ArrayList<>(Arrays.asList("Steak (300g)", "Carrots (1kg)", "Cold Beer (330ml)", "AA Battery (4 Pcs.)")));
        map.put("Courier", new ArrayList<>(Arrays.asList("Espresso Machine", "Garden Chair")));

        return map;
    }


}
