/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmorsa_md5;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/**|
 *
 * @author Yesica
 */
public class MD5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
    
        MessageDigest md = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        md.update("yesica".getBytes());
        byte[] digest = md.digest();
        
        for(byte b:digest){
            System.out.println(Integer.toHexString(0xFF & b));
        }
        System.out.println();
        byte[] encoded = Base64.encodeBase64(digest);
        System.out.println(new String(encoded));
    }
    
}
