import utils.Utils;

import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(Utils.hashPassword("password"));
    }
}
