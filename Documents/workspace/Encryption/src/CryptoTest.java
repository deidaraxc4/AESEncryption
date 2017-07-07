import java.io.File;
 
/**
 * A tester for the CryptoUtils class.
 * @author www.codejava.net
 *
 */
public class CryptoTest {
    public static void main(String[] args) {
        String key = "Mary has one cat1";
        File inputFile = new File("example.pdf");
        File encryptedFile = new File("document.encrypted");
        File decryptedFile = new File("document.pdf");
         
        try {
            AES.encrypt(key, inputFile, encryptedFile);
            AES.decrypt(key, encryptedFile, decryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}