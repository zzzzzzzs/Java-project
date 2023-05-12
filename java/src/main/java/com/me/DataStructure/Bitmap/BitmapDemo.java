package com.me.DataStructure.Bitmap;

public class BitmapDemo {
    private byte[] bits;

    public BitmapDemo(int size) {
        bits = new byte[(size + 7) / 8];
    }

    public void set(int pos) {
        int index = pos / 8;
        int bit = pos % 8;
        bits[index] |= (1 << bit);
    }

    public boolean get(int pos) {
        int index = pos / 8;
        int bit = pos % 8;
        return ((bits[index] >> bit) & 1) == 1;
    }

    public void and(BitmapDemo other) {
        for (int i = 0; i < bits.length; i++) {
            bits[i] &= other.bits[i];
        }
    }

    public void or(BitmapDemo other) {
        for (int i = 0; i < bits.length; i++) {
            bits[i] |= other.bits[i];
        }
    }

    public void not() {
        for (int i = 0; i < bits.length; i++) {
            bits[i] = (byte) ~bits[i];
        }
    }

    public static void main(String[] args) {
        BitmapDemo bitmap = new BitmapDemo(10000); // 创建一个可以存储 10000 个位的 Bitmap
        bitmap.set(42); // 将第 42 个位置上的位设置为 1
        boolean bitValue = bitmap.get(42); // 获取第 42 个位置上的位的值
        System.out.println(bitValue);
    }
}
