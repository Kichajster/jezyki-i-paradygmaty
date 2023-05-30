import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    Scanner scanner = new Scanner(System.in);
    private final ArrayList<Book> bookList = new ArrayList<>();

    public void borrowBook() {
        ShowAvailableBook();
        boolean validInventoryNumber = false;
        String inventoryNumber = "";

        while (!validInventoryNumber) {
            System.out.println("\nPodaj numer ID książki: ");
            inventoryNumber = scanner.nextLine();

            for (Book book : bookList) {
                if (book.getInventoryNumber().equals(inventoryNumber)) {
                    validInventoryNumber = book.isAvailable();
                    break;
                }
            }

            if (!validInventoryNumber) {
                System.out.println(ANSI_RED + "Podany numer ID nie jest dostępny. Proszę wybrać inny numer!" + ANSI_RESET);
            }
        }

        System.out.println("Podaj na ile dni chcesz wypożyczyć książkę: ");
        int days = Integer.parseInt(scanner.nextLine());
        System.out.println("Podaj imię osoby wypożyczającej książkę: ");
        String borrowerName = scanner.nextLine();

        for (Book book : bookList) {
            if (book.getInventoryNumber().equals(inventoryNumber)) {
                book.setBorrowerName(borrowerName);
                book.setBorrowDate(LocalDate.now());
                book.setDueDate(book.getBorrowDate().plusDays(days));

                System.out.println("Książka \"" + book.getTitle() + "\" wypożyczona przez " + borrowerName + ".");
                System.out.println("Data wypożyczenia: " + book.getBorrowDate());
                System.out.println("Do dnia: " + book.getDueDate());
                book.setAvailable(false);
                break;
            }
        }
    }


    public void ShowAvailableBook() {
        for (Book book : bookList) {
            book.ShowAvailableBook();
        }
    }

    public void BookShow() {
        for (Book book : bookList) {
            book.showBook();
        }
    }

    public void bookSearchByTitle() {
        boolean shouldContinue = true;
        while (shouldContinue) {
            System.out.println("Podaj Tytuł");
            String title = scanner.nextLine();
            if (!title.isBlank()) {
                for (Book book : bookList) {
                    if (book.getTitle().equalsIgnoreCase(title)) {
                        book.showBook();
                        shouldContinue = false;
                    }
                }
                if (shouldContinue) {
                    System.out.println(ANSI_RED + "Nie znaleziono książki o podanym tytule. Spróbuj ponownie." + ANSI_RESET);
                    shouldContinue = false;
                }
            }
        }
    }


    public void bookSearchByAuthor() {
        boolean shouldContinue = true;
        while (shouldContinue) {
            System.out.println("Podaj autora");
            String author = scanner.nextLine();
            if (!author.isBlank()) {
                for (Book book : bookList) {
                    if (book.getAuthor().equalsIgnoreCase(author)) {
                        book.showBook();
                        shouldContinue = false;
                    }
                }
                if (shouldContinue) {
                    System.out.println(ANSI_RED + "Nie znaleziono książki o podanym autorze. Spróbuj ponownie." + ANSI_RESET);
                    shouldContinue = false;
                }
            }
        }
    }

    public void bookSearchByGenre() {
        boolean shouldContinue = true;
        while (shouldContinue) {
            System.out.println("Podaj gatunek");
            String genre = scanner.nextLine();
            if (!genre.isBlank()) {
                for (Book book : bookList) {
                    if (book.getGenre().equalsIgnoreCase(genre)) {
                        book.showBook();
                        shouldContinue = false;
                    }
                }
                if (shouldContinue) {
                    System.out.println(ANSI_RED + "Nie znaleziono książki o podanym gatunku. Spróbuj ponownie." + ANSI_RESET);
                    shouldContinue = false;
                }
            }
        }
    }

    public void bookAdd() {
        System.out.println("Podaj tytuł książki:");
        String title = scanner.nextLine();
        System.out.println("Podaj autora książki:");
        String autor = scanner.nextLine();
        System.out.println("Podaj rok wydania:");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("Podaj wydawnictwo książki:");
        String publisher = scanner.nextLine();
        System.out.println("Podaj numer ISBN książki:");
        String isbn = scanner.nextLine();
        System.out.println("Podaj gatunek książki:");
        String genre = scanner.nextLine();
        System.out.println("Podaj numer ID:");
        String inventoryNumber = scanner.nextLine();
        if (!title.isBlank() && !autor.isBlank() && !publisher.isBlank() && !isbn.isBlank() && !genre.isBlank() && year != 0 && !inventoryNumber.isBlank()) {
            bookList.add(new Book(title, autor, year, publisher, isbn, genre, true, inventoryNumber));
        }
    }


    public void bookUpdate() {
        System.out.println("Podaj numer ID:");
        String inventoryNumber = scanner.nextLine();
        for (Book book : bookList) {
            if (book.getInventoryNumber().equals(inventoryNumber)) {
                System.out.println("Podaj nowy tytuł książki (wciśnij ENTER by pominąć) :");
                String title = scanner.nextLine();
                if (!title.isBlank()) {
                    book.setTitle(title);
                }
                System.out.println("Podaj nowego autora książki (wciśnij ENTER by pominąć) :");
                String autor = scanner.nextLine();
                if (!autor.isBlank()) {
                    book.setAuthor(autor);
                }
                System.out.println("Podaj nowy rok wydania (wpisz 0 i wciśnij ENTER by pominąć) :");
                int year = Integer.parseInt(scanner.nextLine());
                if (year != 0) {
                    book.setYear(year);
                }
                System.out.println("Podaj nowe wydawnictwo książki, (wciśnij ENTER by pominąć) :");
                String publisher = scanner.nextLine();
                if (!publisher.isBlank()) {
                    book.setPublisher(publisher);
                }
                System.out.println("Podaj nowy gatunek książki, (wciśnij ENTER by pominąć) :");
                String genre = scanner.nextLine();
                if (!title.isBlank()) {
                    book.setGenre(genre);
                }
                System.out.println("Książka została zaktualizowana :");
                book.showBook();
                return;
            }
        }
        System.out.println("Nie znaleziono książki o podanym ID.");

    }


    public void bookDelete() {
        System.out.println("Podaj numer ID:");
        String inventoryNumber = scanner.nextLine();
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getInventoryNumber().equals(inventoryNumber)) {
                System.out.println("Znaleziono książkę:");
                bookList.get(i).showBook();
                bookList.remove(i);
                System.out.println("Książka została usunięta z listy.");
                return;
            }
        }
        System.out.println("Nie znaleziono książki o podanym ID.");
    }

    public void examples() {
        Book book1 = new Book("Lalka", "Bolesław Prus", 1890, "Biblioteka Polska", "9788373270184", "powieść", true, "1");
        Book book2 = new Book("Pan Tadeusz", "Adam Mickiewicz", 1834, "Czytelnik", "9788373271891", "epopeja", true, "2");
        Book book3 = new Book("Języki i Paradygmaty", "Jan Kowalski", 2023, "SAN", "1234567890123", "horror", true, "3");
        Book book4 = new Book("Dziady", "Adam Mickiewicz", 1822, "Czytelnik", "9788308040589", "dramat", true, "4");
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
    }
}
