package commands;

public interface commands {
    int LOGOUT = 0;
    int ADD_BOOK = 1;
    int PRINT_ALL_BOOKS = 2;
    int PRINT_BOOKS_BY_AUTHOR_NAME = 3;
    int PRINT_BOOKS_BY_GENRE = 4;
    int PRINT_BOOKS_BY_PRICE_RANGE = 5;
    int ADD_AUTHOR = 6;
    int PRINT_ALL_AUTHORS = 7;
    int DOWNLOAD_AUTHORS_EXCEL = 8;

    int EXIT = 0;
    int LOGIN = 1;
    int REGISTER = 2;


    static void printAdmincommands() {
        System.out.println("please input " + LOGOUT + " for LOGOUT");
        System.out.println("please input " + ADD_BOOK + " for added book");
        System.out.println("please input " + PRINT_ALL_BOOKS + " for print all books");
        System.out.println("please input " + PRINT_BOOKS_BY_AUTHOR_NAME + " for print books by AutherName");
        System.out.println("please input " + PRINT_BOOKS_BY_GENRE + " for print books by genre");
        System.out.println("please input " + PRINT_BOOKS_BY_PRICE_RANGE + " for print books by price range");
        System.out.println("please input " + ADD_AUTHOR + " for add auther");
        System.out.println("please input " + PRINT_ALL_AUTHORS + " for print all authers");
        System.out.println("please input " + DOWNLOAD_AUTHORS_EXCEL + " for Download authors on excel");
    }
    static void printUsercommands() {
        System.out.println("please input " + LOGOUT + " for LOGOUT");
        System.out.println("please input " + ADD_BOOK + " for added book");
        System.out.println("please input " + PRINT_ALL_BOOKS + " for print all books");
        System.out.println("please input " + PRINT_BOOKS_BY_AUTHOR_NAME + " for print books by AutherName");
        System.out.println("please input " + ADD_AUTHOR + " for add auther");
        System.out.println("please input " + DOWNLOAD_AUTHORS_EXCEL + " for download authors on excel");
    }

    static void printlogincommands() {
        System.out.println("please input " + EXIT + " for EXIT");
        System.out.println("please input " + LOGIN + " for LOGIN");
        System.out.println("please input " + REGISTER + " for REGISTER");
    }
}

