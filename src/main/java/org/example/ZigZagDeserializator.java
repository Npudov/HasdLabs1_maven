package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class ZigZagDeserializator {
    public static void deserializeFromFile(Object obj, String filePath) throws IOException, IllegalAccessException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] data = fis.readAllBytes();
            deserialize(obj, data);
        }
    }

    public static void deserialize(Object obj, byte[] data) throws IllegalAccessException {
        int index = 0;
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == int.class) {
                int encodedValue = EncoderZigZag.bytesToInt(data, index);
                int decodedValue = EncoderZigZag.decodeZigZag(encodedValue);
                field.setInt(obj, decodedValue);
                index += 4;
            } else if (field.getType() == String.class) {
                int strLength = EncoderZigZag.bytesToInt(data, index);
                index += 4;
                byte[] strBytes = new byte[strLength];
                System.arraycopy(data, index, strBytes, 0, strLength);
                String value = new String(strBytes);
                field.set(obj, value);
                index += strLength;
            }
        }
    }
}
