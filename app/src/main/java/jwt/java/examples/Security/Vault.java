package jwt.java.examples.Security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import lombok.Getter;

@ApplicationScoped
public class Vault {

    @Getter
    private RSAPublicKey publicKey;
    @Getter
    private RSAPrivateKey privateKey;

    @PostConstruct
    public void init() throws Exception {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        RSAKeyGenParameterSpec algoParams = new RSAKeyGenParameterSpec(1024, RSAKeyGenParameterSpec.F4);
        keyPairGenerator.initialize(algoParams);

        KeyPair keyPair = keyPairGenerator.genKeyPair();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }

}
