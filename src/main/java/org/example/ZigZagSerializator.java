package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;



public class ZigZagSerializator {
    public static FileOutputStream serializeToFile(Object obj, String filePath) throws Exception {
        byte[] serializedData = serialize(obj);
        //System.out.println("Data length: " + serializedData.length);
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(serializedData);
        fos.close();
        /*try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(serializedData);
        }*/
        return fos;
    }
    public static byte[] serialize(Object obj) throws Exception {
        List<Byte> serializedData = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == int.class) {
                int value = field.getInt(obj);
                int encodedValue = EncoderZigZag.encodeZigZag(value);
                byte[] bytes = EncoderZigZag.intToBytes(encodedValue);
                for (byte b : bytes) {
                    serializedData.add(b);
                }
            } else if (field.getType() == String.class) {
                String value = (String) field.get(obj);
                byte[] strBytes = value.getBytes();
                int strLength = strBytes.length;
                byte[] strLengthBytes = EncoderZigZag.intToBytes(strLength);
                for (byte b : strLengthBytes) {
                    serializedData.add(b);
                }
                for (byte b : strBytes) {
                    serializedData.add(b);
                }
            } else {
                throw new Exception("Illegal Type");
            }
        }

        byte[] result = new byte[serializedData.size()];
        for (int i = 0; i < serializedData.size(); i++) {
            result[i] = serializedData.get(i);
        }

        return result;
    }
}
