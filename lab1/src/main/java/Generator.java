import java.util.Random;

class Generator {
    private final Random numGenerator = new Random();

    public String generateNum() {
        String res = "";
        for (int i = 0; i < 4; i++) {
            int digit = numGenerator.nextInt(10);
            while (res.contains(Integer.toString(digit))) {
                digit = numGenerator.nextInt(10);
            }
            res += Integer.toString(digit);
        }
        return res;
    }
}
