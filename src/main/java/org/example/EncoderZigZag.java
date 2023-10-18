package org.example;

public class EncoderZigZag {
    protected static int encodeZigZag(int n) {
        return (n << 1) ^ (n >> 31);
    }

    protected static int decodeZigZag(int n) {
        return (n >>> 1) ^ -(n & 1);
    }

    protected static byte[] intToBytes(int value) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (value & 0xFF);
        bytes[1] = (byte) ((value >> 8) & 0xFF);
        bytes[2] = (byte) ((value >> 16) & 0xFF);
        bytes[3] = (byte) ((value >> 24) & 0xFF);
        return bytes;
    }

    protected static int bytesToInt(byte[] bytes, int index) {
        int value = 0;
        value |= (bytes[index] & 0xFF);
        value |= ((bytes[index + 1] & 0xFF) << 8);
        value |= ((bytes[index + 2] & 0xFF) << 16);
        value |= ((bytes[index + 3] & 0xFF) << 24);
        return value;
    }
}
