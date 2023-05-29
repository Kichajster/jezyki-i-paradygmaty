public class Main {
    public static void main(String[] args) {
        long userNumber = 13;
        System.out.println("Przykład dla liczby zdefiniowanej przez użytkownika: " + userNumber);
        userNumberLength(userNumber);

        System.out.println("\nWartości dla zadania 1 do 999999:");
        int longestChainLength = 0;
        long numberWithLongestChain = 0;

        for (long number = 1; number <= 999999; number++) {
            int chainLength = exerciseNumberLength(number);

            if (chainLength > longestChainLength) {
                longestChainLength = chainLength;
                numberWithLongestChain = number;
            }
        }

        System.out.println("Najdłuższy łańcuch występuje dla liczby: " + numberWithLongestChain);
        System.out.println("Długość łańcucha: " + longestChainLength);
    }

    private static void userNumberLength(long number) {
        System.out.println("Liczba: " + number);
        int chainLength = 1;
        long maxNumber = number;

        while (number != 1) {
            if (number % 2 == 0) {
                number = number / 2;
            } else {
                number = 3 * number + 1;
            }
            chainLength++;
            if (number > maxNumber) {
                maxNumber = number;
            }
        }

        System.out.println("Długość łańcucha liczb: " + chainLength);
        System.out.println("Największa wygenerowana liczba: " + maxNumber);

    }

    private static int exerciseNumberLength(long number) {
        int chainLength = 1;

        while (number != 1) {
            if (number % 2 == 0) {
                number = number / 2;
            } else {
                number = 3 * number + 1;
            }
            chainLength++;
        }

        return chainLength;
    }
}
