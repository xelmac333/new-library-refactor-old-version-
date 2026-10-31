import exception.DuplicateItemException;
import exception.ItemAlreadyBorrowedException;
import exception.ItemNotFoundException;
import model.Book;
import model.Magazine;
import service.LibrariService;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws RuntimeException {
        LibrariService service = new LibrariService();
        Scanner scanner1 = new Scanner(System.in);
        try {
            service.loadData();
        } catch (Exception e) {
            System.out.println("сохраненых даных не обнаружено,создана новая база!");
        }
            while (true) {
                System.out.println("  =============== ");
                System.out.println("выберете нужную функцию");
                System.out.println("1 - добавить предмет");
                System.out.println("2 - убрать предмет");
                System.out.println("3 - вернуть предмет которого нету в наличии");
                System.out.println("4 - вывести список предметов");
                System.out.println("5 - выход(сохранение даных)");
                int switchChoice = scanner1.nextInt();
                scanner1.nextLine();
                switch (switchChoice) {
                    case 1:
                        System.out.println("Що ви хочете додати? 1 - Книгу, 2 - Журнал");
                        int typeChoice = scanner1.nextInt();
                        scanner1.nextLine();

                            System.out.print("Введіть ID: ");
                            String id = scanner1.nextLine();

                        System.out.print("Введіть назву: ");
                        String title = scanner1.nextLine();


                        if (typeChoice == 1) {
                            System.out.print("Введіть автора: ");
                            String author = scanner1.nextLine();

                            System.out.print("Введіть кількість сторінок: ");
                            int pages = scanner1.nextInt();
                            scanner1.nextLine();

                            Book book = new Book(id, title, author, pages);
                            try {
                                service.addItem(book);
                                System.out.println("Книгу успішно додано!");
                            } catch (DuplicateItemException e) {
                                System.out.println("ошибка" + e.getMessage());
                            }

                        } else if (typeChoice == 2) {
                            System.out.print("Введіть номер випуску: ");
                            int issueNumber = scanner1.nextInt();
                            scanner1.nextLine();

                            Magazine magazine = new Magazine(id, title, issueNumber);
                            try {
                                service.addItem(magazine);
                                System.out.println("Журнал успішно додано!");
                            } catch (DuplicateItemException e) {
                                System.out.println("предмет и с таким id уже существует");
                            }
                        } else {
                            System.out.println("Невірний вибір типу предмета!");
                        }
                        break;
                    case 2:
                        try {
                            System.out.print("Введіть ID предмета, який хочете забрати: ");
                            String typeChoice2 = scanner1.nextLine();
                            service.borrowItem(typeChoice2);
                            System.out.println("Предмет успішно забрано з наявності!");
                        } catch (ItemNotFoundException a) {
                            System.out.println("Помилка: " + a.getMessage());
                        } catch (ItemAlreadyBorrowedException a) {
                            System.out.println("Помилка: " + a.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            System.out.print("Введіть ID предмета, який хочете повернути: ");
                            String typeChoice3 = scanner1.nextLine();
                            service.returnItem(typeChoice3);
                            System.out.println("Предмет успішно повернуто в наявність!");
                        } catch (ItemNotFoundException b) {
                            System.out.println("Помилка: " + b.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println(service.listAll());
                        break;
                    case 5:
                        try {
                            service.setData();
                        } catch (RuntimeException c) {
                            throw new RuntimeException(c);
                        }
                        System.out.println("сохранение даных... подождите немного");
                        System.exit(0);
                    default:
                        System.out.println("неверный выюор,");
                }
            }
        }
    }
