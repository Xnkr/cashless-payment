import com.pi4j.io.serial.*;
import keypad.Keypad;
import security.AES;
import security.SHA256;
import security.utils.Utils;

import java.io.IOException;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Cashless Payment System!");

        final Serial serial = SerialFactory.createInstance();

        SerialConfig config = new SerialConfig();
        config.device("/dev/" + "ttyACM0")
                .baud(Baud._57600)
                .dataBits(DataBits._8)
                .parity(Parity.NONE)
                .stopBits(StopBits._1)
                .flowControl(FlowControl.NONE);

        try {
            serial.open(config);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        serial.addListener((SerialDataEventListener) event -> {
//            try {
//                String cardString = event.getAsciiString().trim();
//                System.out.println("Messaged Received from Card:");
//                System.out.print(event.getAsciiString());
//
//                byte[] encrypted = AES.encrypt(Utils.hexStringToByte("2b7e151628aed2a6abf7158809cf4f3c"), Utils.hexStringToByte("000102030405060708090a0b0c0d0e0f"), Utils.hexStringToByte("6bc1bee22e409f96"));
//
//                System.out.println("Please Enter your 4 digit pin:");
//
//                Keypad keypad = Keypad.getKeypadInstance();
//                String password = keypad.readPassword();
//                byte[] passwordBytes = password.getBytes();
//                System.out.println("You have entered: " + password);
//
//                byte[] hmac = SHA256.getHMAC(encrypted, passwordBytes);
//
//                String encryptedString = new String(Base64.getEncoder().encode(hmac));
//                System.out.println("The encrypted String is:" + encryptedString);
//
//                if(!encryptedString.equals(cardString)) {
//                    System.out.println("You have entered incorrect pin!!");
//                } else {
//                    System.out.println("You have entered correct pin!!");
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

        Keypad keypad = Keypad.getKeypadInstance();

        String line = keypad.readKeyLine();
        while (true);
    }
}