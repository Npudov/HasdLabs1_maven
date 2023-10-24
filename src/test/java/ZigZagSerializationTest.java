import org.example.ExampleData;
import org.example.ZigZagDeserializator;
import org.example.ZigZagSerializator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZigZagSerializationTest {
    @Test
    public void testSerializeDeserializeExampleData() throws Exception {
        ExampleData originalData = new ExampleData();
        originalData.setIntField(42);
        originalData.setStringField("Hello, World!");

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        originalData.setNumberList(numbers);

        ZigZagSerializator.serializeToFile(originalData, "serializedData.bin");

        ExampleData deserializedData = new ExampleData();
        ZigZagDeserializator.deserializeFromFile(deserializedData, "serializedData.bin");

        assertEquals(originalData.getIntField(), deserializedData.getIntField());
        assertEquals(originalData.getStringField(), deserializedData.getStringField());
        assertEquals(originalData.getNumberList(), deserializedData.getNumberList());
    }

    @Test
    public void testSerializeDeserializeExampleDataWithEmptyList() throws Exception {
        ExampleData originalData = new ExampleData();
        originalData.setIntField(42);
        originalData.setStringField("Hello, World!");

        List<Integer> numbers = new ArrayList<>();
        originalData.setNumberList(numbers);

        ZigZagSerializator.serializeToFile(originalData, "serializedData.bin");

        ExampleData deserializedData = new ExampleData();
        ZigZagDeserializator.deserializeFromFile(deserializedData, "serializedData.bin");

        assertEquals(originalData.getIntField(), deserializedData.getIntField());
        assertEquals(originalData.getStringField(), deserializedData.getStringField());
        assertEquals(originalData.getNumberList(), deserializedData.getNumberList());
    }
}
