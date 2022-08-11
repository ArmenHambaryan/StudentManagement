package storage;


import model.User;

public class UserStorage {
    private User[] array = new User[10];
    private int size = 0;

    public void add(User user) {
        if (array.length == size) {
            extend();
        }
        array[size++] = user;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ". " + array[i] + " ");
        }

    }

    private void extend() {
        User[] temp = new User[array.length + 10];
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


    public User getUserByIndex(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        }
        return null;
    }
    public User getUserByEmail(String email ){
        for (int i = 0; i < size; i++) {
            if (array[i].getEmail().equals(email)){
                return array[i];
            }

        }
        return null;
    }

}

