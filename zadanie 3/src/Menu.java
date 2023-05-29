import java.util.Scanner;

public class Menu {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    Scanner scanner = new Scanner(System.in);

    Library library = new Library();

    public void mainMenu() {
        boolean shouldContinue = true;
        System.out.println("WITAMY W KSIĘGARNI. WYBIERZ OPCJE:\n");
        while (shouldContinue) {
            System.out.println("1. Wypożycz książkę");
            System.out.println("2. Zobacz książki");
            System.out.println("3. Szukaj książki");
            System.out.println("4. Dodaj książkę");
            System.out.println("5. Modyfikuj książkę");
            System.out.println("6. Usuń książkę");
            System.out.println("9. Wyjdź");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> library.borrowBook();
                    case 2 -> library.BookShow();
                    case 3 -> bookSearch();
                    case 4 -> library.bookAdd();
                    case 5 -> library.bookUpdate();
                    case 6 -> library.bookDelete();
                    case 9 -> shouldContinue = false;
                    default -> System.out.println(ANSI_RED + "Wybierz jedną z dostępnych opcji!" + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "Wybierz jedną z dostępnych opcji!" + ANSI_RESET);
                scanner.next();
            }

        }

    }

    private void bookSearch() {
        boolean shouldContinue = true;
        while (shouldContinue) {
            System.out.println("Szukaj książki\n");
            System.out.println("1. Szukaj po tytule");
            System.out.println("2. Szukaj po autorze");
            System.out.println("3. Szukaj po gatunku");
            System.out.println("9. Cofnij");

            if (scanner.hasNextInt()) {
                int userChoice = scanner.nextInt();
                switch (userChoice) {
                    case 1 -> library.bookSearchByTitle();
                    case 2 -> library.bookSearchByAuthor();
                    case 3 -> library.bookSearchByGenre();
                    case 9 -> shouldContinue = false;
                }
            } else {
                System.out.println(ANSI_RED + "Wybierz jedną z dostępnych opcji!" + ANSI_RESET);
                scanner.next();
            }
        }
    }
}
