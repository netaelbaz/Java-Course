package hadar_and_neta;

import java.util.InputMismatchException;

public class ExceptionUtil {
    public static void getIntInput(String receivedInput) throws InputMismatchException {
       try {
           Integer.parseInt(receivedInput);
       }
       catch (NumberFormatException e ) {
           throw new InputMismatchException("Input should be an integer");
       }
    }

}
