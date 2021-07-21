package com.example.encrypt;

import java.security.InvalidKeyException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {

    private Cipher cipher;
    private Cipher deCipher;
    private final SecretKeySpec secretKey;

    public Encrypt(){

        try {
            cipher = Cipher.getInstance("AES");
            deCipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        byte[] encryptionKey = randomKeyGenerator();
        secretKey = new SecretKeySpec(encryptionKey, "AES");
    }

    public String AESEncrypt(String string){

        byte[] stringByte = string.getBytes();
        byte[] encryptedByte = new byte[stringByte.length];

        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedByte = cipher.doFinal(stringByte);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        String returnString = null;

        try {
            returnString = new String(encryptedByte, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    public String AESDecrypt(String string) throws UnsupportedEncodingException {
        byte[] EncryptedByte = string.getBytes("ISO-8859-1");
        String decryptedString = string;

        byte[] decryption;

        try {
            deCipher.init(cipher.DECRYPT_MODE, secretKey);
            decryption = deCipher.doFinal(EncryptedByte);
            decryptedString = new String(decryption);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return decryptedString;
    }

    public byte[] randomKeyGenerator(){
        int bytesLength = 16;
        int max = 128;
        int min = -128;
        byte[] bytesReturn = new byte[bytesLength];

        Random rand = new Random();

        for(int i = 0 ; i < bytesLength ; i++){
            Integer num = rand.nextInt((max - min) + 1) + min;
            byte b = num.byteValue();
            bytesReturn[i] = b;
        }
        return bytesReturn;
    }
}