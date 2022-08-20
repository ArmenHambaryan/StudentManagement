import lombok.Data;

public class Test {
    public static void main(String[] args) {
        Integer value = 10;
        increment(value);
        System.out.println(value);
    }

    static void increment(Integer value) {
        ++value;
    }
}

