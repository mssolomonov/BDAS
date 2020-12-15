import java.io.IOException;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import org.bouncycastle.cms.CMSException;

import com.crypto.*;
import org.bouncycastle.operator.OperatorCreationException;

public class Launcher {
    public static void main(String[] args) throws CertificateException, UnrecoverableKeyException,
            NoSuchAlgorithmException, IOException, KeyStoreException, NoSuchProviderException, CMSException, OperatorCreationException {
        Receiver receiver = new Receiver();
        Sender sender = new Sender(receiver.getCertificate());

        String secretMessage = "This is the very secret message";
        byte[] stringToEncrypt = secretMessage.getBytes();
        byte[] encryptedData = sender.encryptData(stringToEncrypt);

        byte[] decryptedData = receiver.decryptData(encryptedData);
        String decryptedMessage = new String(decryptedData);

        System.out.println("Message before encode: " + secretMessage);
        System.out.println("Message after decode: " + decryptedMessage);

        Signer signer = new Signer();
        Verifier verifier = new Verifier();

        String testData = "This data need to be signed";
        byte[] rawData = testData.getBytes();

        byte[] signedData = signer.signData(rawData);
        boolean check = verifier.verifySignedData(signedData);
        if (check) {
            System.out.println("Check passed!");
        } else {
            System.out.println("Check failed");
        }
    }
}