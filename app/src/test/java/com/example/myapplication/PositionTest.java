package com.example.myapplication;

import org.junit.Test;

public class PositionTest {


    @Test
    public void testPosi() {
        byte[] bytes = new byte[20];


        int packetPosition = 0;
        int packetContentLength;
        int packetSize;
        int position;
        int type;

        position = 0 + 1;
        type = bytes[position] & 0xFF;
        position++;

        int vendorId = ((bytes[position++] & 0xFF) << 8) + (bytes[position++] & 0xFF);
        int meshUUID = (bytes[position++] & 0xFF) + ((bytes[position++] & 0xFF) << 8);
        position += 4;

        assert (position == 10);

    }
}
