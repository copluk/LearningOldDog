package com.example.myapplication;

import org.junit.Test;

public class TestWithFuck {

    public static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

    @Test
    public void test428WB() {
        String returnValue = "";

        byte[] oriData = hexStringToByteArray("11021102412C11770400010100800200050A74312E32C7A40B04025011");
        byte[] oriDataaaa = hexStringToByteArray(
                "11" + // 00
                        "02" +
                        "41" +
                        "2C" +
                        "11" + // 04
                        "77" +
                        "04" +
                        "00" +
                        "01" +
                        "01" + // 09
                        "00" +
                        "80" +
                        "02" +
                        "00" +
                        "05" + // 14
                        "0A" +
                        "74" +
                        "31" +
                        "2E" +
                        "32" + // 19
                        "C7" +
                        "A4" +
                        "0B" +
                        "04" +
                        "02" + // 24
                        "50" +
                        "11"
        );

        int length = oriData.length;
        int packetPosition = 0;
        int packetContentLength;
        int packetSize;
        int position = 0;
        int type;
        byte[] meshName = null;

        int rspData = 0;

        while (packetPosition < length) {


            int vendorId = ((oriData[position++] & 0xFF) << 8) + (oriData[position++] & 0xFF);
//                    if (vendorId != Manufacture.getDefault().getVendorId())
//                        returnValue = "vendorId != Manufacture.getDefault().getVendorId()";


            int canNotReadByNoticeType = 0;

            int meshUUID = (oriData[position++] & 0xFF) + ((oriData[position++] & 0xFF) << 8);
            position += 4;
            int productUUID = (oriData[position++] & 0xFF) + ((oriData[position++] & 0xFF) << 8);
            int status = oriData[position++] & 0xFF;
            int meshAddress = (oriData[position++] & 0xFF) + ((oriData[position++] & 0xFF) << 8);
            position += 3;
            byte roleTypeCode = oriData[position++];
            position += 1;
            byte[] firmwareVersion = new byte[4];
            for (int i = 0; i < firmwareVersion.length; i++) {
                firmwareVersion[i] = oriData[position++];
            }
            position += 3;
            byte meshOtaCode = oriData[position++];
            byte pwmFrequency = oriData[position++];
            byte chipsCode = oriData[position++];
            byte sType = oriData[position++];

            returnValue = "Finish";

        }

    }

    @Test
    public void wtfCH() {
        String showCh = "52";

        String deviceCh = "4";

        boolean ass =
                !("52".equals(deviceCh)
                        || deviceCh.equals("2")
                        || deviceCh.equals("1")
                        || deviceCh.equals("5")
                        || deviceCh.equals("4")
                );

        assert ass;

    }

    @Test
    public void wtfCHddd() {
        String showCh = "52";

        String deviceCh = "4";

        boolean ass =
                !(false || false || true);

        assert ass;

    }

    @Test
    public void testTrue15Byte(){
        byte[] test = new byte[]{(byte) 0xff, (byte) 0xbb, 0x03, 0x06 , 0x07,0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};

        assert checkByteArrayForSequence(test);
    }


    @Test
    public void testFalse15Byte(){
        byte[] test = new byte[]{(byte) 0xff, (byte) 0xbb, 0x03, 0x06 , 0x07,0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0c, 0x0c, 0x0c, 0x0e, 0x0f};

        assert !checkByteArrayForSequence(test);
    }

    @Test
    public void testNotLast15Byte(){
        byte[] test = new byte[]{(byte) 0xff, (byte) 0xbb, 0x03, 0x06 , 0x07,0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, (byte) 0xbb, 0x03, 0x06 , 0x07,0x01};

        assert checkByteArrayForSequence(test);
    }

    public  boolean checkByteArrayForSequence(byte[] byteArray) {
        byte[] expectedBytes = new byte[] {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};

        if (byteArray.length < expectedBytes.length) {
            return false;
        }

        for (int i = 0; i <= byteArray.length - expectedBytes.length; i++) {
            boolean match = true;
            for (int j = 0; j < expectedBytes.length; j++) {
                if (byteArray[i + j] != expectedBytes[j]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
        }

        return false;
    }
}
