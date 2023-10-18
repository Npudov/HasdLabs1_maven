package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class Serialize
{
    public static void main( String[] args) throws Exception {
        ExampleData myObject = new ExampleData();
        myObject.setIntField(42);
        myObject.setStringField("Hello, World!");

        FileOutputStream serialData = ZigZagSerializator.serializeToFile(myObject, "serializedData.bin");
        File file = new File("serializedData.bin");
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
