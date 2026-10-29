package QuanLyHoaDon.SuaHD;

import QuanLyHoaDon.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormSuaHDGUI extends JFrame {
    private JTextField txtMaHD, txtNgayHD, txtTenKH, txtMaPhong, txtDonGia, txtSoLuong;
    private JRadioButton rbGio, rbNgay;
    private JLabel lblSoLuong;
    private final Runnable onUpdatedCallback;

    public FormSuaHDGUI(int maHD, Runnable onUpdatedCallback) {
        super("Cập Nhật Hóa Đơn Mã: " + maHD);
        this.onUpdatedCallback = onUpdatedCallback;
        khoiTaoThanhPhan(maHD);
    }

    private void khoiTaoThanhPhan(int maHD) {
        setSize(480, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(UITheme.CARD_BG);
        setLayout(new BorderLayout());

        // Header Form màu Xanh Primary
        JPanel pnlHeader = new JPanel(new GridLayout(2, 1, 0, 4));
        pnlHeader.setBackground(UITheme.PRIMARY);
        pnlHeader.setBorder(new EmptyBorder(16, 25, 16, 25));

        JLabel lblT = new JLabel("CẬP NHẬT THÔNG TIN HÓA ĐƠN");
        lblT.setFont(UITheme.FONT_TITLE);
        lblT.setForeground(Color.WHITE);

        JLabel lblD = new JLabel("Mã hóa đơn được khóa cố định theo nguyên tắc toàn vẹn dữ liệu");
        lblD.setFont(UITheme.FONT_SMALL);
        lblD.setForeground(new Color(224, 231, 255));

        pnlHeader.add(lblT);
        pnlHeader.add(lblD);
        add(pnlHeader, BorderLayout.NORTH);

        // Body Form
        JPanel panel = new JPanel(new GridLayout(7, 2, 12, 14));
        panel.setBackground(UITheme.CARD_BG);
        panel.setBorder(new EmptyBorder(20, 25, 20, 25));

        txtMaHD = taoTextField();
        txtMaHD.setText(String.valueOf(maHD));
        txtMaHD.setEditable(false);
        txtMaHD.setBackground(new Color(241, 245, 249)); // Khóa mã

        txtNgayHD = taoTextField();
        txtTenKH = taoTextField();
        txtMaPhong = taoTextField();
        txtDonGia = taoTextField();
        txtSoLuong = taoTextField();

        rbGio = new JRadioButton("Theo Giờ");
        rbNgay = new JRadioButton("Theo Ngày");
        rbGio.setFont(UITheme.FONT_BODY);
        rbNgay.setFont(UITheme.FONT_BODY);
        rbGio.setOpaque(false);
        rbNgay.setOpaque(false);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbGio);
        bg.add(rbNgay);

        JPanel panelLoai = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelLoai.setOpaque(false);
        panelLoai.add(rbGio);
        panelLoai.add(rbNgay);

        lblSoLuong = new JLabel("Số Lượng:");
        lblSoLuong.setFont(UITheme.FONT_SUB);

        rbGio.addActionListener(e -> lblSoLuong.setText("Số Giờ Thuê:"));
        rbNgay.addActionListener(e -> lblSoLuong.setText("Số Ngày Thuê:"));

        // Nạp dữ liệu hiện tại
        HoaDon hd = MemoryHDDatabase.getInstance().findHoaDon(maHD);
        if (hd != null) {
            txtNgayHD.setText(hd.getNgayHD());
            txtTenKH.setText(hd.getTenKH());
            txtMaPhong.setText(hd.getMaPhong());
            txtDonGia.setText(String.valueOf(hd.getDonGia()));
            if (hd instanceof HoaDonTheoGio) {
                rbGio.setSelected(true);
                lblSoLuong.setText("Số Giờ Thuê:");
                txtSoLuong.setText(String.valueOf(((HoaDonTheoGio) hd).getSoGioThue()));
            } else {
                rbNgay.setSelected(true);
                lblSoLuong.setText("Số Ngày Thuê:");
                txtSoLuong.setText(String.valueOf(((HoaDonTheoNgay) hd).getSoNgayThue()));
            }
        }

        themRow(panel, "Mã HĐ (Cố định):", txtMaHD);
        themRow(panel, "Ngày HĐ (YYYY-MM-DD):", txtNgayHD);
        themRow(panel, "Tên Khách Hàng:", txtTenKH);
        themRow(panel, "Mã Số Phòng:", txtMaPhong);
        themRow(panel, "Đơn Giá (VNĐ):", txtDonGia);
        themRow(panel, "Hình Thức Thuê:", panelLoai);
        panel.add(lblSoLuong);
        panel.add(txtSoLuong);

        add(panel, BorderLayout.CENTER);

        // Footer buttons
        JPanel pBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        pBtn.setBackground(new Color(248, 250, 252));
        pBtn.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UITheme.CARD_BORDER));

        JButton btnHuy = new JButton("Hủy Bỏ");
        btnHuy.setFont(UITheme.FONT_BODY);
        btnHuy.addActionListener(e -> dispose());

        JButton btnLuu = UITheme.createPrimaryButton("Lưu Thay Đổi", UITheme.PRIMARY);
        btnLuu.addActionListener(e -> xuLySua());

        pBtn.add(btnHuy);
        pBtn.add(btnLuu);
        add(pBtn, BorderLayout.SOUTH);
    }

    private void themRow(JPanel p, String label, Component cmp) {
        JLabel l = new JLabel(label);
        l.setFont(UITheme.FONT_SUB);
        l.setForeground(UITheme.TEXT_DARK);
        p.add(l);
        p.add(cmp);
    }

    private JTextField taoTextField() {
        JTextField tf = new JTextField();
        tf.setFont(UITheme.FONT_BODY);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.CARD_BORDER, 1),
                new EmptyBorder(5, 8, 5, 8)
        ));
        return tf;
    }

    private void xuLySua() {
        try {
            int maHD = Integer.parseInt(txtMaHD.getText().trim());
            String ngayHD = txtNgayHD.getText().trim();
            String tenKH = txtTenKH.getText().trim();
            String maPhong = txtMaPhong.getText().trim();
            double donGia = Double.parseDouble(txtDonGia.getText().trim());
            int sl = Integer.parseInt(txtSoLuong.getText().trim());
            String loai = rbGio.isSelected() ? "HD Giờ" : "HD Ngày";

            RequestData req = new RequestData(maHD, ngayHD, tenKH, maPhong, donGia, loai, sl);
            SuaHDControl control = new SuaHDControl();
            ResponseData res = control.execute(req);

            JOptionPane.showMessageDialog(this, res.getMessage(), "Cập Nhật", JOptionPane.INFORMATION_MESSAGE);
            if (res.isSuccess()) {
                if (onUpdatedCallback != null) onUpdatedCallback.run();
                dispose();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập định dạng số hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        }
    }
}