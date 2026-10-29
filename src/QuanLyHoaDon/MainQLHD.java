package QuanLyHoaDon;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MainQLHD {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            QLHDMenuGUI gui = new QLHDMenuGUI();
            gui.setVisible(true);
        });
    }
}