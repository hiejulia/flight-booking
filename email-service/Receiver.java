
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;
import org.apache.commons.codec.binary.Base64;

public class Receiver {


    // receive message

    public void receiveMessage(byte[]  message) throws UnsupportedEncodingException {
        CountDownLatch latch = new CountDownLatch(1);
        Mail mail = new Mail();
        
        String strMessage=new String(message, "UTF-8");

        byte[] valueDecoded= Base64.decodeBase64(strMessage);
    	
        mail.sendEmail(new String(valueDecoded));
        
        latch.countDown();

    }




}