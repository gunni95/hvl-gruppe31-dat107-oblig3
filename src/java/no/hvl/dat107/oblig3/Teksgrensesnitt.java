package no.hvl.dat107.oblig3;

import java.util.concurrent.Callable;

public class Teksgrensesnitt {
    protected static <T> T safeRead(Callable<T> operation, String errorMessage) {
        boolean valid = false;
        T response = null;
        while (!valid) {
            try {
                response = operation.call();
                valid = true;
            } catch (Exception e) {
                System.out.println(errorMessage + ", " + e.getMessage());
            }
        }

        return response;
    }
}
