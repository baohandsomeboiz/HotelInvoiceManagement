package QuanLyHoaDon.XoaHD;

import QuanLyHoaDon.ResponseData;
import javax.swing.JOptionPane;
import java.awt.Component;

public class ResultDialogGUI {
    public static void show(Component parent, ResponseData res) {
        JOptionPane.showMessageDialog(parent, res.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}