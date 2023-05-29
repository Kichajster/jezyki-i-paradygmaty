import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number2;
        System.out.print("Wprowadź liczbę: ");
        number2 = input.nextInt();
        while (number2 < 1 || number2 > 1000) {
            System.out.print("Wprowadź liczbę od 1 do 1000: ");
            number2 = input.nextInt();
        }

        String newWord = Number.convertNumberToWord(number2);
        //String lettersOnly = newWord.replaceAll("\\s+", "");
        //int count = lettersOnly.length();
        int count;
        int totalCount = 0;
        for (int i = 1; i <= 1000; i++) {
            String letters = Number.convertNumberToWord(i);
            String lettersOnly = letters.replaceAll("\\s+", "");
            count = lettersOnly.length();
            totalCount = totalCount + count;
        }
        System.out.println("Wprowadzona liczba to: " + newWord);
        // System.out.println("Ilość liter wynosi: " + count);
        System.out.println("Ilość liter dla liczb od 1 do 1000 wynosi: " + totalCount);
    }
}
class Number {
    public static String convertNumberToWord(int number) {


        String[] units = {"", "jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć", "dziesięć", "jedenaście", "dwanaście", "trzynaście", "czternaście", "piętnaście", "szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście"};
        String[] tens = {"", "", "dwadzieścia", "trzydzieści", "czterdzieści", "pięćdziesiąt", "sześćdziesiąt", "siedemdziesiąt", "osiemdziesiąt", "dziewięćdziesiąt"};
        String[] hundreds = {"", "sto", "dwieście", "trzysta", "czterysta", "pięćset", "sześćset", "siedemset", "osiemset", "dziewięćset", "tysiąc"};
        String words = "";

        if (number / 100 > 0) {
            words += hundreds[number / 100] + " ";
            number %= 100;
        }

        if (number > 0) {
            if (number < 20) {
                words += units[number];
            } else {
                words += tens[number / 10] + " ";
                number %= 10;
                if (number > 0) {
                    words += units[number];
                }
            }
        }
        return words;
    }
}