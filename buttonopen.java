import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.plaf.basic.BasicButtonUI;
import java.sql.*;

class Book {
    String title, author, genre;
    ArrayList<Review> reviews;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        reviews = new ArrayList<>();
    }
}

class Review {
    int rating;
    String comment;

    public Review(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }
}

class BookDiary {
    ArrayList<Book> books;

    public BookDiary() {
        books = new ArrayList<>();
    }

    public void addBook(Book b) {
        books.add(b);
    }

    public void addReview(Book b, Review r) {
        b.reviews.add(r);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}

public class buttonopen {
    public static void Button() {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Review page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("C:\\Users\\disha\\Desktop\\java project\\books.jpg");
        frame.setIconImage(icon.getImage());

        BookDiary bd = new BookDiary();
        String title = (String) JOptionPane.showInputDialog(
                frame,
                "Enter the title of the book:",
                "Book Information",
                JOptionPane.PLAIN_MESSAGE,
                icon,
                null,
                "");

        // Show input dialog for the author of the book
        String author = (String) JOptionPane.showInputDialog(
                frame,
                "Enter the author of the book:",
                "Book Information",
                JOptionPane.PLAIN_MESSAGE,
                icon,
                null,
                "");

        // Show input dialog for the genre of the book
        String genre = (String) JOptionPane.showInputDialog(
                frame,
                "Enter the genre of the book:",
                "Book Information",
                JOptionPane.PLAIN_MESSAGE,
                icon,
                null,
                "");

        Book book = new Book(title, author, genre);
        int rating = Integer.parseInt((String) JOptionPane.showInputDialog(
                frame,
                "Enter the rating (1-5) for the book:",
                "Book Rating",
                JOptionPane.PLAIN_MESSAGE,
                icon,
                null,
                ""));

        // Show input dialog for the review comment of the book
        String comment = (String) JOptionPane.showInputDialog(
                frame,
                "Enter your review for the book:",
                "Book Review",
                JOptionPane.PLAIN_MESSAGE,
                icon,
                null,
                "");

        Review review = new Review(rating, comment);

        bd.addBook(book);
        bd.addReview(book, review);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setFont(new Font("Serif", Font.PLAIN, 14));

        // Simulated data
        for (Book b : bd.getBooks()) {
            textArea.append("Title: " + b.title + "\n");
            textArea.append("Author: " + b.author + "\n");
            textArea.append("Genre: " + b.genre + "\n");

            if (b.reviews.isEmpty()) {
                textArea.append("No reviews yet.\n");
            } else {
                for (Review r : b.reviews) {
                    textArea.append("Rating: " + r.rating + "\n");
                    textArea.append("Review: " + r.comment + "\n");
                }
            }

            textArea.append("\n");
        }

        // Set the background image for the text area
        try {
            BufferedImage image = ImageIO.read(new File("C:\\Users\\disha\\Desktop\\java project\\disha1.jpg"));
            textArea.setOpaque(false);
            textArea.setBackground(new Color(0, 0, 0, 0));
            textArea.setFont(new Font("Courier New", Font.PLAIN, 20));
            frame.setContentPane(new JLabel(new ImageIcon(image)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a JScrollPane and add the JTextArea to it
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Create a JFrame and add the JScrollPane to it
        JFrame frame1 = new JFrame("Book Reviews");
        frame1.add(scrollPane);

        // Set frame properties
        frame1.setSize(400, 300);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }
}