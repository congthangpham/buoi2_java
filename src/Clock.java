import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Clock extends JFrame implements Runnable {
    private JLabel clockLabel;
    private TimeZone timeZone;

    public Clock(TimeZone timeZone) {
        this.timeZone = timeZone;
        setTitle("Clock - " + timeZone.getID());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(200, 150); // Đã thay đổi kích thước để hiển thị cả múi giờ
        setLocationRelativeTo(null);

        initComponents();

        Thread thread = new Thread(this);
        thread.start();
    }

    private void initComponents() {
        setLayout(new GridLayout(2, 1)); // Sử dụng GridLayout để chia cửa sổ thành 2 phần

        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(clockLabel);

        // Hiển thị thông tin múi giờ khu vực
        JLabel timeZoneLabel = new JLabel("Timezone: " + timeZone.getID());
        timeZoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(timeZoneLabel);
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(timeZone);

        while (true) {
            Calendar calendar = Calendar.getInstance(timeZone);
            String time = sdf.format(calendar.getTime());
            String timeZoneInfo = "Timezone: " + timeZone.getID() + " | Time: " + time;
            clockLabel.setText(timeZoneInfo);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
