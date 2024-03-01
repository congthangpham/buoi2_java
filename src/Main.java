import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimeZone;

public class Main extends JFrame {
    private JTextField timeZoneField;

    public Main() {
        setTitle("Multi-Clock");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        timeZoneField = new JTextField(10);
        JButton addButton = new JButton("Add Clock");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClock();
            }
        });
        mainPanel.add(new JLabel("Timezone: "));
        mainPanel.add(timeZoneField);
        mainPanel.add(addButton);

        add(mainPanel);
    }

    private void addClock() {
        String timeZoneId = timeZoneField.getText().trim();
        if (timeZoneId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a timezone.");
            return;
        }

        TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
        if (!isValidTimeZone(timeZone)) {
            JOptionPane.showMessageDialog(this, "Invalid timezone.");
            return;
        }

        Clock clock = new Clock(timeZone);
        clock.setVisible(true);
    }

    private boolean isValidTimeZone(TimeZone timeZone) {
        return timeZone != null && !timeZone.getID().equals("GMT");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main app = new Main();
                app.setVisible(true);
            }
        });
    }
}
