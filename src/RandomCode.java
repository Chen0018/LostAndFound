import java.util.Random;

public class RandomCode {
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int LENGTH = 6;
    private static final Random RANDOM = new Random();

    public static String generateRandomCode(){
        StringBuilder code = new StringBuilder(LENGTH);

        boolean have123 = false;
        boolean haveABC = false;

        for (int i = 0; i< LENGTH; i++){
            char randomCodeChar = CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length()));
            code.append(randomCodeChar);

            if (Character.isDigit(randomCodeChar)){
                have123 = true;
            }
            else if (Character.isLetter(randomCodeChar)) {
                haveABC = true;
            }
        }

        while (!(have123&&haveABC)){
            code.setLength(0);
            have123 = false;
            haveABC = false;
            for (int i = 0; i< LENGTH; i++){
                char randomCodeChar = CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length()));
                code.append(randomCodeChar);

                if (Character.isDigit(randomCodeChar)){
                    have123 = true;
                }
                else if (Character.isLetter(randomCodeChar)) {
                    haveABC = true;
                }
            }
        }
        return code.toString();
    }
}
