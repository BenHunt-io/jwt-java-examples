package jwt.java.examples.Controller.Common;

public class JwtUtil {

    public static String parseJWT(String authHeader) {
        return authHeader.split(" ")[1];
    }

}
