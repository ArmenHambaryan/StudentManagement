package storage;


import model.Books;

public class BooksStorage {
    private Books[] array = new Books[10];
    private int size = 0;

    public void add(Books books) {
        if (array.length == size) {
            extend();
        }
        array[size++] = books;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ". " + array[i] + " ");
        }

    }

    private void extend() {
        Books[] temp = new Books[array.length + 10];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public int getSize() {
        return size;
    }

    public void delete(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size; i++) {
                array[i] = array[i + 1];

            }
            size--;
            System.out.println("student deleted");
        } else {
            System.out.println("index out of bounds");
        }
    }

    public void printBookssByGenre(String Genre) {
        for (int i = 0; i < size; i++) {
            if (array[i].getGenre().equals(Genre)) {
                System.out.println(array[i]);
            }

        }
    }

    public Books getBooksByIndex(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        }
        return null;
    }

    public void printByAuther(String name) {
        for (int i = 0; i < size; i++) {
            if (array[i].getAuthor().equals(name)) {
                System.out.println(array[i]);
            }

        }
    }


    public void printBooksByPriceRange(double min, double max){
        for (int i = 0; i < size; i++) {
            if (array[i].getPrice() >= min &&array[i].getPrice() <= max){
                System.out.println(array[i]);
            }
        }

    }
}