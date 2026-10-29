package QuanLyHoaDon.TimKiemHD;

import QuanLyHoaDon.HoaDon;
import javax.swing.table.DefaultTableModel;

public class ResultTimKiemGUI {
    public static void updateTable(DefaultTableModel model, HoaDon hd) {
        model.setRowCount(0);
        if (hd != null) {
            model.addRow(new Object[]{
                hd.getMaHD(),
                hd.getTenKH(),
                hd.getMaPhong(),
                hd.getLoaiHD(),
                hd.getDonGia(),
                hd.tinhTien()
            });
        }
    }
}