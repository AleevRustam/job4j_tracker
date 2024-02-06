package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book book1 = new Book("Book1", 100);
        Book book2 = new Book("Book2", 200);
        Book book3 = new Book("Book3", 300);
        Book cleanCode = new Book("Clean code", 400);

        Book[] library = new Book[4];
        library[0] = book1;
        library[1] = book2;
        library[2] = book3;
        library[3] = cleanCode;

        for (int i = 0; i < library.length; i++) {
            System.out.println(library[i].getName() + ", " + library[i].getPages());
        }

        Book temp = library[0];
        library[0] = library[3];
        library[3] = temp;

        for (int i = 0; i < library.length; i++) {
            System.out.println(library[i].getName() + ", " + library[i].getPages());
        }

        for (int i = 0; i < library.length; i++) {
            if (library[i].getName().equals("Clean code")) {
                System.out.println(library[i].getName() + ", " + library[i].getPages());
            }
        }
    }
}
