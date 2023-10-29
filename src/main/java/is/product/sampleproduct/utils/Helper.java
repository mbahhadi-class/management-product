package is.product.sampleproduct.utils;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @Author "Noverry Ambo"
 * @start 10/25/2023
 */
public class Helper {

    public static void main(String[] args) throws Exception {
        // Generate a random key
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        SecureRandom secureRandom = new SecureRandom();
        keyGen.init(256, secureRandom);
        Key key = keyGen.generateKey();

        // Create a Mac object with the HmacSHA256 algorithm
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key);

        // Generate a random byte array
        byte[] randomBytes = new byte[32]; // 32 bytes for HMAC-SHA256
        secureRandom.nextBytes(randomBytes);

        // Compute the HMAC-SHA256 hash
        byte[] hmacResult = mac.doFinal(randomBytes);

        // Convert the result to a hexadecimal string
        String hmacHex = bytesToHex(hmacResult);
        System.out.println(hmacHex);
    }

    // Helper method to convert bytes to a hexadecimal string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
