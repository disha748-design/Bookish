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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookReviewApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Books!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.decode("#F1EAFF"));
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.setBackground(Color.decode("#F1EAFF"));

        JLabel label = new JLabel("Did you read a new book?", SwingConstants.CENTER);
        label.setFont(new Font("Consolas", Font.BOLD, 80));
        label.setForeground(Color.BLACK);
        panel.add(label);

        JButton button = new JButton("Add a new book");
        button.setFont(new Font("Courier new", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(500, 50));
        button.setBackground(Color.PINK);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulating data collection
                String title = "New Book Title";
                String author = "Author Name";
                insertBook(title, author);
            }
        });
        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);

        // Initialize database
        initializeDatabase();
    }

    private static void initializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS books (id INTEGER PRIMARY KEY, title TEXT, author TEXT);";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\path_to_your_database\\books.db");
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
        }
    }

    private static void insertBook(String title, String author) {
        String sql = "INSERT INTO books(title, author) VALUES(?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\path_to_your_database\\books.db");
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Book added successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding book: " + e.getMessage());
        }
    }
}