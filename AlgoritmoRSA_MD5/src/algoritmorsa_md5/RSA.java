/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmorsa_md5;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
/**
 *
 * @author Yesica
 */
public class RSA {

    private static Cipher rsa;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws  Exception {
        // TODO code application logic here
        
        KeyPairGenerator keyPairGenerator= KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        
        saveKey(publicKey, "publickey.dat");
        
        publicKey = loadPublicKey("publickey.dat");
        
        saveKey(privateKey,"privatekey.dat");
        privateKey = loadPrivateKey("privatekey.dat");
        
        rsa=Cipher.getInstance("RSA/ECB/PKCS1Padding");
        
        String text = "Yesica";
        
        rsa.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encriptado = rsa.doFinal(text.getBytes());
        
        for(byte b:encriptado){
            System.out.println(Integer.toHexString(0xFF&b));
        }
        System.out.println();
        
        rsa.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytesDesencriptados = rsa.doFinal(encriptado);
        String textoDesencriptado = new String(bytesDesencriptados);
        
        System.out.println(textoDesencriptado);
    }
    private static PublicKey loadPublicKey(String fileName) throws Exception{
        
        FileInputStream fis = new FileInputStream(fileName);
        int numBtyes = fis.available();
        byte[] bytes = new byte[numBtyes];
        fis.read(bytes);
        fis.close();
        
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        KeySpec keySpec = new X509EncodedKeySpec(bytes);
        PublicKey keyFromBytes = keyFactory.generatePublic(keySpec);
        return keyFromBytes;
    }
    private static PrivateKey loadPrivateKey(String fileName) throws Exception{
        
        FileInputStream fis = new FileInputStream(fileName);
        int numBtyes = fis.available();
        byte[] bytes = new byte[numBtyes];
        fis.read(bytes);
        fis.close();
        
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        PrivateKey keyFromBytes = keyFactory.generatePrivate(keySpec);
        return keyFromBytes;
    }
    private static  void saveKey(Key key, String fileName) throws Exception{
        byte[] publicKeyBytes = key.getEncoded();
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(publicKeyBytes);
        fos.close();
    }
}
