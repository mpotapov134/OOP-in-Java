class WrongInputException extends Exception {
    public WrongInputException(String errorMessage) {
        super(errorMessage);
    }
    public WrongInputException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}

class GuessHandler {
    private final String number;
    private int numberOfOxes = 0;

    public GuessHandler(String number) {
        this.number = number;
    }

    private boolean inputIsValid(String input) {
        if (input.length() != number.length())
            return false;

        String checkString = "";
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);

            if (!Character.isDigit(character) ||
                    checkString.contains(Character.toString(character)))
                return false;

            checkString += character;
        }
        return true;
    }

    public void countCowsAndOxes(String input) throws WrongInputException {
        if (!inputIsValid(input))
            throw new WrongInputException("Ожидалась " + number.length() +
                    "-значная " + "последовательность различных цифр");

        int totalMatches = 0, oxes = 0;
        for (int i = 0; i < number.length(); i++) {
            if (input.charAt(i) == number.charAt(i))
                oxes++;
            if (number.contains(Character.toString(input.charAt(i))))
                totalMatches++;
        }

        System.out.println((totalMatches - oxes) + " коров, " +
                oxes + " быков");

        numberOfOxes = oxes;
    }

    public boolean allOxes() {
        return numberOfOxes == number.length();
    }
}
