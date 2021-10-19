package co.com.mintrabajo.infrastructure.security.jwt;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeyManager {

	private static final Logger LOGGER = LogManager.getLogger(KeyManager.class);

	@Value("${key.folder}")
	private String keyFolder;

	private static final String PRIVATE_KEY = "/tys_private_key.der";
	private static final String PUBLIC_KEY = "/tys_public_key.der";

	private PrivateKey privKey;
	private PublicKey pubKey;
	
	private KeyManager(){
		super();
	}

	public void loadKeys() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, FileNotFoundException, IOException {

		LOGGER.info("Cargando llaves del file system: {}", keyFolder);
		
		if( !FileUtils.getFile( keyFolder + PRIVATE_KEY).exists() || !FileUtils.getFile( keyFolder + PUBLIC_KEY).exists()){
			generateKeys();
		}
		
		loadPrivateKey(IOUtils.toByteArray(new FileInputStream(keyFolder + PRIVATE_KEY)));
		loadPublicKey(IOUtils.toByteArray(new FileInputStream(keyFolder + PUBLIC_KEY)));
		LOGGER.info("Llave privada cargada: {}", privKey);
		LOGGER.info("Llave publica cargada: {}", pubKey);
		LOGGER.info("Llaves cargadas con exito");
	}

	private void loadPrivateKey(byte[] data)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException {

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(data);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		privKey = kf.generatePrivate(keySpec);

		Cipher decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		decryptCipher.init(Cipher.DECRYPT_MODE, privKey);
	}

	private void loadPublicKey(byte[] data)
			throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(data);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		pubKey = kf.generatePublic(keySpec);

		Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		encryptCipher.init(Cipher.ENCRYPT_MODE, pubKey);
	}

	public synchronized void generateKeys() throws NoSuchAlgorithmException, IOException {

		LOGGER.info("Generando nuevo par de llaves private/publica");
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(4096);
		KeyPair keyPair = kpg.generateKeyPair();
		privKey = keyPair.getPrivate();
		pubKey = keyPair.getPublic();

		LOGGER.info("Llave privada generada: {}", privKey);
		LOGGER.info("Llave publica generada: {}", pubKey);

		writeKeyIntoFyleSystem(PRIVATE_KEY, privKey.getEncoded());
		writeKeyIntoFyleSystem(PUBLIC_KEY, pubKey.getEncoded());
	}

	private void writeKeyIntoFyleSystem(final String key, final byte[] encoded) throws IOException {
		DataOutputStream out = new DataOutputStream(new FileOutputStream(keyFolder + key));
		out.write(encoded);
		out.close();
	}

	public PrivateKey getPrivKey() {
		return privKey;
	}

	public PublicKey getPubKey() {
		return pubKey;
	}

}
