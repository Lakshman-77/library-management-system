package com.library.main;

import com.library.model.*;
import com.library.service.Library;

import java.util.*;

public class Main {

    private static void preloadBooks(Library library) {

        library.addBook(
                new Book("9780134685991", "Effective Java", "Joshua Bloch", "Programming"),
                2
        );

        library.addBook(
                new Book("9780201633610", "Design Patterns", "Erich Gamma", "Software Engineering"),
                2
        );

        library.addBook(
                new Book("9780132350884", "Clean Code", "Robert C. Martin", "Programming"),
                2
        );

        library.addBook(
                new Book("9781118063330", "Operating System Concepts", "Silberschatz", "Operating Systems"),
                2
        );

        library.addBook(
                new Book("9780073523323", "Database System Concepts", "Silberschatz", "Databases"),
                2
        );

        library.addBook(
                new Book("9780596009205", "Head First Java", "Kathy Sierra", "Programming"),
                2
        );
    }

    private static void printAvailableBooks(Library library) {

        System.out.println("\n📚 Available Books and Copies");
        System.out.println("--------------------------------");

        Map<String, List<String>> available = library.getAvailableCopiesByBook();

        if (available.isEmpty()) {
            System.out.println("No books available at the moment.");
            return;
        }

        for (String title : available.keySet()) {
            System.out.print(title + " → ");
            System.out.println(String.join(", ", available.get(title)));
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        preloadBooks(library);

        String currentMemberId;

        while (true) {

            System.out.println("\n==============================");
            System.out.print("Enter Member ID: ");
            currentMemberId = sc.next();

            if (library.memberExists(currentMemberId)) {
                Member m = library.getMember(currentMemberId);
                System.out.println("Welcome back, " + m.getName());
            } else {
                System.out.print("Enter Member Name: ");
                String name = sc.next();
                library.registerMember(new Member(currentMemberId, name));
                System.out.println("Member registered successfully");
            }

            while (true) {

                System.out.println("\n--------- MENU ---------");
                System.out.println("1. Issue Book");
                System.out.println("2. Return Book");
                System.out.println("3. Switch Member");
                System.out.println("4. Exit");
                System.out.print("Choose option: ");

                int choice = sc.nextInt();

                try {
                    if (choice == 1) {
                        printAvailableBooks(library);
                        System.out.print("Enter Copy ID: ");
                        library.issueBook(sc.next(), currentMemberId);
                        System.out.println("✅ Book issued");
                    }
                    else if (choice == 2) {
                        printAvailableBooks(library);
                        System.out.print("Enter Copy ID: ");
                        library.returnBook(sc.next(), currentMemberId);
                        System.out.println("✅ Book returned");
                    }
                    else if (choice == 3) {
                        break; // switch member
                    }
                    else if (choice == 4) {
                        System.out.println("Exiting system. Bye 👋");
                        sc.close();
                        return;
                    }
                    else {
                        System.out.println("Invalid option");
                    }
                } catch (Exception e) {
                    System.out.println("❌ " + e.getMessage());
                }
            }
        }
    }
}
