import commands.commands;
import exception.AutherNotFaundException;
import model.*;
import storage.AutherStorage;
import storage.BooksStorage;
import storage.UserStorage;

import java.util.Scanner;

public class BooksDemo implements commands {
    private static Scanner scanner = new Scanner(System.in);
    private static BooksStorage booksStorage = new BooksStorage();
    private static AutherStorage autherStorage = new AutherStorage();
    private static UserStorage userStorage = new UserStorage();
    private static User currentUser = null;

    public static void main(String[] args) {
        initData();

        boolean run = true;
        while (run) {
            commands.printlogincommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    run = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("invalod command");
            }
        }
    }

    private static void login() {
        System.out.println("please input email,password");
        String emailPasswordStr = scanner.nextLine();
        String[] emailPassword = emailPasswordStr.split(",");
        User user = userStorage.getUserByEmail(emailPassword[0]);
        if (user == null) {
            System.out.println("user with " + emailPassword[0] + " does not exist");
        } else {
            if (user.getPassword().equals(emailPassword[1])) {
                currentUser = user;
                if (user.getUserType() == UserType.ADMIN) {
                    loginAdmin();
                } else if (user.getUserType() == UserType.USER) {
                    loginUser();
                }
            } else {
                System.out.println("password is wrong");
            }
        }
    }

    private static void loginUser() {
        System.out.println("Welcome " + currentUser);

        boolean run = true;
        while (run) {
            commands.printUsercommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    run = false;
                    break;
                case ADD_BOOK:
                    addbook();
                    break;
                case PRINT_ALL_BOOKS:
                    booksStorage.print();
                    break;
                case PRINT_BOOKS_BY_AUTHOR_NAME:
                    printByAutherName();
                    break;
                case ADD_AUTHOR:
                    addauther();
                    break;

                default:
                    System.out.println("invalid command");
            }
        }
    }

    private static void register() {
        System.out.println("please input name,surname,email,paswword");
        String userDataStr = scanner.nextLine();
        String[] userData = userDataStr.split(",");
        if (userData.length < 4) {
            System.out.println(" please input corect data");
        } else {
            if (userStorage.getUserByEmail(userData[0]) == null) {
                User user = new User();
                user.setName(userData[0]);
                user.setSurname(userData[1]);
                user.setEmail(userData[2]);
                user.setPassword(userData[3]);
                user.setUserType(UserType.USER);
                userStorage.add(user);
                System.out.println("user created!");
            } else {
                System.out.println("user with " + userData[0] + " already exist");
            }
        }
    }

    private static void loginAdmin() {
        System.out.println("Welcome " + currentUser);
        boolean run = true;
        while (run) {
            commands.printAdmincommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }

            switch (command) {
                case EXIT:
                    run = false;
                    break;
                case ADD_BOOK:
                    addbook();
                    break;
                case PRINT_ALL_BOOKS:
                    booksStorage.print();
                    break;
                case PRINT_BOOKS_BY_AUTHOR_NAME:
                    printByAutherName();
                    break;
                case PRINT_BOOKS_BY_GENRE:
                    printBooksByGenre();
                    break;
                case PRINT_BOOKS_BY_PRICE_RANGE:
                    printBooksByPriceRange();
                    break;
                case ADD_AUTHOR:
                    addauther();
                    break;
                case PRINT_ALL_AUTHORS:
                    autherStorage.print();
                    break;

                default:
                    System.out.println("invalid command");
            }

        }
    }

    private static void initData() {
        User user = new User("admin", "admin", "admin@mail.com", "admin", UserType.ADMIN);
        userStorage.add(user);
    }


    private static void addauther() {
        System.out.println("please input auther name");
        String name = scanner.nextLine();

        System.out.println("please input auther surname");
        String surname = scanner.nextLine();

        System.out.println("please input auther email");
        String email = scanner.nextLine();
        try {
            System.out.println("pleas input auther gender");
            Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase().trim());
            Author author = new Author(name, surname, email, gender);
            autherStorage.add(author);
            System.out.println("auther created");

        } catch (IllegalArgumentException e) {
            System.out.println("please input corect gender");
            addbook();
        }


    }


    private static void printBooksByPriceRange() {
        try {
            System.out.println("please input min price");
            double max = Double.parseDouble(scanner.nextLine());
            System.out.println("please input max price");
            double min = Double.parseDouble(scanner.nextLine());
            booksStorage.printBooksByPriceRange(min, max);
        } catch (NumberFormatException e) {
            System.err.println("use only numbers");
            printBooksByPriceRange();
        }


    }


    private static void printBooksByGenre() {
        System.out.println("please input genre");
        String name = scanner.nextLine();
        booksStorage.printBookssByGenre(name);
    }

    private static void printByAutherName() {
        System.out.println("please input name");
        String name = scanner.nextLine();
        booksStorage.printByAuther(name);
    }

    private static void addbook() {
        if (autherStorage.getSize() == 0) {
            System.out.println("please add auther");
            addauther();
        } else {
            autherStorage.print();
            System.out.println("please choose auther index");
            int authorindex = Integer.parseInt(scanner.nextLine());
            try {
                Author author = autherStorage.getAutherByIndex(authorindex);
                System.out.println("pleas input books title name");
                String title = scanner.nextLine();
                System.out.println("please input books price");
                String priceStr = scanner.nextLine();
                System.out.println("please input books count ");
                String countStr = scanner.nextLine();
                System.out.println("please input books genre");
                String genre = scanner.nextLine();


                double price = Integer.parseInt(priceStr);
                int count = Integer.parseInt(countStr);
                Books books = new Books(title, author, price, count, genre, currentUser);
                booksStorage.add(books);
                System.out.println("thanks for added");
            } catch (AutherNotFaundException e) {
                System.out.println(e.getMessage());
                addbook();


            }
        }

    }
}

