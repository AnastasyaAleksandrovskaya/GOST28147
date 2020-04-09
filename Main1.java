package com.option.model;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Main1 {
    public static void main(String[] args) throws DecoderException, UnsupportedEncodingException {
        byte[][] block1 = { { 0x4, 0xA, 0x9, 0x2, 0xD, 0x8, 0x0, 0xE, 0x6, 0xB, 0x1, 0xC, 0x7, 0xF, 0x5, 0x3 },
                { 0xE, 0xB, 0x4, 0xC, 0x6, 0xD, 0xF, 0xA, 0x2, 0x3, 0x8, 0x1, 0x0, 0x7, 0x5, 0x9 },
                { 0x5, 0x8, 0x1, 0xD, 0xA, 0x3, 0x4, 0x2, 0xE, 0xF, 0xC, 0x7, 0x6, 0x0, 0x9, 0xB },
                { 0x7, 0xD, 0xA, 0x1, 0x0, 0x8, 0x9, 0xF, 0xE, 0x4, 0x6, 0xC, 0xB, 0x2, 0x5, 0x3 },
                { 0x6, 0xC, 0x7, 0x1, 0x5, 0xF, 0xD, 0x8, 0x4, 0xA, 0x9, 0xE, 0x0, 0x3, 0xB, 0x2 },
                { 0x4, 0xB, 0xA, 0x0, 0x7, 0x2, 0x1, 0xD, 0x3, 0x6, 0x8, 0x5, 0x9, 0xC, 0xF, 0xE },
                { 0xD, 0xB, 0x4, 0x1, 0x3, 0xF, 0x5, 0x9, 0x0, 0xA, 0xE, 0x7, 0x6, 0x8, 0x2, 0xC },
                { 0x1, 0xF, 0xD, 0x0, 0x5, 0x7, 0xA, 0x4, 0x9, 0x2, 0x3, 0xE, 0x6, 0xB, 0x8, 0xC } };

        byte[][] block2 = { { 0x9, 0x6, 0x3, 0x2, 0x8, 0xB, 0x1, 0x7, 0xA, 0x4, 0xE, 0xF, 0xC, 0x0, 0xD, 0x5 },
                { 0x3, 0x7, 0xE, 0x9, 0x8, 0xA, 0xF, 0x0, 0x5, 0x2, 0x6, 0xC, 0xB, 0x4, 0xD, 0x1 },
                { 0xE, 0x4, 0x6, 0x2, 0xB, 0x3, 0xD, 0x8, 0xC, 0xF, 0x5, 0xA, 0x0, 0x7, 0x1, 0x9 },
                { 0xE, 0x7, 0xA, 0xC, 0xD, 0x1, 0x3, 0x9, 0x0, 0x2, 0xB, 0x4, 0xF, 0x8, 0x5, 0x6 },
                { 0xB, 0x5, 0x1, 0x9, 0x8, 0xD, 0xF, 0x0, 0xE, 0x4, 0x2, 0x3, 0xC, 0x7, 0xA, 0x6 },
                { 0x3, 0xA, 0xD, 0xC, 0x1, 0x2, 0x0, 0xB, 0x7, 0x5, 0x9, 0x4, 0x8, 0xF, 0xE, 0x6 },
                { 0x1, 0xD, 0x2, 0x9, 0x7, 0xA, 0x6, 0x0, 0x8, 0xC, 0x4, 0x5, 0xF, 0x3, 0xB, 0xE },
                { 0xB, 0xA, 0xF, 0x5, 0x0, 0xC, 0xE, 0x8, 0x6, 0x2, 0x3, 0x9, 0x1, 0x7, 0xD, 0x4 } };

        String keyTest = "8CA6002BF5EB5D09C05F356D8FF37F66629AA3741D670AAA6291732706AA6530";
        String messageToEncode = "Uzbekistan is also a country of world-famous historic cities and sites of ancient" +
                " settlements with their most impressive architectural monuments. During its long and rich history, " +
                "the predecessors of today’s Uzbekistan experienced a lot of everything. They were involved in the growth" +
                " and decline of the world’s most powerful empires of Alexander the Great, Genghis Khan and Tamerlane;";

        String s = toHex(messageToEncode);

        Cipher encryptor = new Cipher();
        byte[] bytesForEnc = DatatypeConverter.parseHexBinary(s);
        byte[] key = DatatypeConverter.parseHexBinary(keyTest);
        System.out.println("DefaultSblock: " + DatatypeConverter.printHexBinary(encryptor.encrypt(bytesForEnc, key)));

        encryptor = new Cipher(block2);
        System.out.println("SetSblock:     " + DatatypeConverter.printHexBinary(encryptor.encrypt(bytesForEnc, key)));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32000000; i++) {
            sb.append('0');
        }

        System.out.println("Input Data:    " + DatatypeConverter.printHexBinary(bytesForEnc));
        System.out.println("Encr. Data:    " + DatatypeConverter.printHexBinary(encryptor.encrypt(bytesForEnc, key)));
        byte[] bytes = Hex.decodeHex(DatatypeConverter.printHexBinary(encryptor.decrypt(encryptor.encrypt(bytesForEnc, key), key)).toCharArray());
        String decodedMessage = new String(bytes, "UTF-8");
        System.out.println("==========   " + decodedMessage);
        System.out.println("Decr. Data:    " + new String(bytes,"UTF-8"));

    }

        public static String toHex(String arg) {
            return String.format("%040x", new BigInteger(1, arg.getBytes()));
        }

    }


