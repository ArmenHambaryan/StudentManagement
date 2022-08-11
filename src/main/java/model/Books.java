package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Books {
    private String title;
    private Author author;
    private double price;
    private int count;
    private String genre;
    private User registeredUser;

}