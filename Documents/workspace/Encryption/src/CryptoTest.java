import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
 
/**
 * A tester for the CryptoUtils class.
 * @author www.codejava.net
 *
 */
public class CryptoTest {
    public static void main(String[] args) {
        String key = "Mary has one cat1";
        File inputFile = new File("example.pdf");
        File encryptedFile = new File("edocument.pdf");
        File decryptedFile = new File("ddocument.pdf");
         
        try {
            AES.encrypt(key, inputFile, encryptedFile);
            AES.decrypt(key, encryptedFile, decryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
//    	File file = new File("example.pdf");
//    	try {
//			InputStream is = new BufferedInputStream(new FileInputStream(file));
//			String mimeType = URLConnection.guessContentTypeFromStream(is);
//			System.out.println(mimeType);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
}