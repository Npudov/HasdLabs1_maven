package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Serialize
{
    public static void main( String[] args) throws Exception {
        ExampleData myObject = new ExampleData();
        myObject.setIntField(42);
        myObject.setStringField("Hello, World!");

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
       myObject.setNumberList(numbers);

        File file = new File("serializedData.bin");
        ZigZagSerializator.serializeToFile(myObject, file.getPath());

        String sizeFile = String.valueOf(file.length());
        try (PrintWriter printWriter = new PrintWriter("result.txt")){
            printWriter.write(sizeFile);
        }


        ExampleData deserializedObject = new ExampleData();
        ZigZagDeserializator.deserializeFromFile(deserializedObject, "serializedData.bin");

        System.out.println("Original intField: " + myObject.getIntField());
        System.out.println("Original stringField: " + myObject.getStringField());
        System.out.println("Deserialized intField: " + deserializedObject.getIntField());
        System.out.println("Deserialized stringField: " + deserializedObject.getStringField());
    }
}
