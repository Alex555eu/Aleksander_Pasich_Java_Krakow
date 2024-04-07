import com.app.BasketSplitter;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class BasketSplitterTest {


    @Test
    public void testSplitMethod() {
        List<String> inputData = BasketSplitterTestResources.getBasketSplitterInputData();

        Map<String, List<String>> expectedResult = BasketSplitterTestResources.getBasketSplitterOutputData();

        BasketSplitter basketSplitter = new BasketSplitter("src/test/resources/BasketDataSplitterTestConfig.txt");

        Map<String, List<String>> result = basketSplitter.split(inputData);

        assertEquals(expectedResult, result);

    }

    @Test
    public void testEmptyInputForSplitMethod() { // expected empty output
        List<String> inputData = new ArrayList<>();

        Map<String, List<String>> expectedResult = new HashMap<>();

        BasketSplitter basketSplitter = new BasketSplitter("src/test/resources/BasketDataSplitterTestConfig.txt");

        Map<String, List<String>> result = basketSplitter.split(inputData);

        assertEquals(expectedResult, result);

    }

    @Test
    public void testIncorrectInputForSplitMethod() { // expected to ignore incorrect value
        List<String> inputData = BasketSplitterTestResources.getBasketSplitterInputData();
        inputData.add("Incorrect Product Name");

        Map<String, List<String>> expectedResult = BasketSplitterTestResources.getBasketSplitterOutputData();

        BasketSplitter basketSplitter = new BasketSplitter("src/test/resources/BasketDataSplitterTestConfig.txt");

        Map<String, List<String>> result = basketSplitter.split(inputData);

        assertEquals(expectedResult, result);
    }



}
