import keypad.Keypad;
import security.AES;
import security.SHA256;
import security.utils.Utils;

import java.io.IOException;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Cashless Payment System!");

        byte[] encrypted = AES.encrypt(Utils.hexStringToByte("2b7e151628aed2a6abf7158809cf4f3c"), Utils.hexStringToByte("000102030405060708090a0b0c0d0e0f"), Utils.hexStringToByte("6bc1bee22e409f96"));

        for(int i = 0; i < encrypted.length; i++) {
            System.out.println(encrypted[i]);
        }

        try {
            Keypad keypad = Keypad.getKeypadInstance();
            String password = keypad.readPassword();
            byte[] passwordBytes = password.getBytes();
            System.out.println(password);

            byte[] hmac = SHA256.getHMAC(encrypted, passwordBytes);

            System.out.println(Base64.getEncoder().encode(hmac));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}