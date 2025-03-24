import javax.swing.*;
import com.toedter.calendar.JCalendar;

class todo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create a JFrame
            JFrame frame = new JFrame("Calendar App");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Create a JCalendar instance
            JCalendar calendar = new JCalendar();

            // Add the calendar to the frame
            frame.add(calendar, BorderLayout.CENTER);

            // Make the frame visible
            frame.setVisible(true);
        });
    }
}