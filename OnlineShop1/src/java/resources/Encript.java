/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.Base64;

/**
 *
 * @author VINH
 * SOURCE : https://www.baeldung.com/java-base64-encode-and-decode
 */
public class Encript {

    public String encodeString(String key) {
        return Base64.getEncoder().encodeToString(key.getBytes());
    }

    public String decodedString(String key) {
        byte[] decodedBytes = Base64.getDecoder().decode(key);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }

    public static void main(String[] args) {

        String originalInput = "123";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        System.out.println(encodedString);

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        System.out.println(decodedString);
    }

}
