package storage;


import exception.AutherNotFaundException;
import model.Author;

public class AutherStorage {
    private Author[] array = new Author[10];
    private int size = 0;

    public void add(Author author) {
        if (array.length == size) {
            extend();
        }
        array[size++] = author;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ". " + array[i] + " ");
        }

    }

    private void extend() {
        Author[] temp = new Author[array.length + 10];
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
            System.out.println("auther deleted");
        } else {
            System.out.println("index out of bounds");
        }
    }


    public Author getAutherByIndex(int index) throws AutherNotFaundException {
        if (index >= 0 && index < size) {
            return array[index];
        }else
            throw new AutherNotFaundException("Auther with "+index+" index does not exist");
    }


}
