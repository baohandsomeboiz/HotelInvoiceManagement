package QuanLyHoaDon.ThemHD;

import QuanLyHoaDon.ResponseData;
import javax.swing.JOptionPane;
import java.awt.Component;

public class ThemHDResultDialogGUI {
    public static void show(Component parent, ResponseData resData) {
        if (resData.isSuccess()) {
            JOptionPane.showMessageDialog(parent, resData.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(parent, resData.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}