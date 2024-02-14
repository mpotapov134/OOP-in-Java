public class CowsAndOxes {
    public static void main(String[] args) {
        Generator generator = new Generator();
        String number = generator.generateNum();
        GuessHandler handler = new GuessHandler(number);
        InputReader reader = new InputReader();

        System.out.println("Попытайтесь угадать " + number.length() +
                "-значную последовательность различных цифр");

        do {
            String guess = reader.readInput();
            try {
                handler.countCowsAndOxes(guess);
            } catch (WrongInputException exception) {
                System.out.println(exception.getMessage());
            }
        } while (!handler.allOxes());

        System.out.println("Поздравляем! Вы угадали число!");
    }
}
