package QuanLyHoaDon.ThemHD;

import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;
import QuanLyHoaDon.UITheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormThemHDGUI extends JFrame {
    private JTextField txtMaHD, txtNgayHD, txtTenKH, txtMaPhong, txtDonGia, txtSoLuong;
    private JComboBox<String> cbLoaiHD;
    private JLabel lblSL;

    public FormThemHDGUI() {
        super("Thêm Mới Hóa Đơn Khách Sạn");
        khoiTaoThanhPhan();
    }

    private void khoiTaoThanhPhan() {
        setSize(480, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(UITheme.CARD_BG);
        setLayout(new BorderLayout());

        // Header Form
        JPanel pnlHeader = new JPanel(new GridLayout(2, 1, 0, 4));
        pnlHeader.setBackground(UITheme.PRIMARY);
        pnlHeader.setBorder(new EmptyBorder(16, 25, 16, 25));

        JLabel lblT = new JLabel("LẬP HÓA ĐƠN MỚI");
        lblT.setFont(UITheme.FONT_TITLE);
        lblT.setForeground(Color.WHITE);

        JLabel lblD = new JLabel("Nhập chính xác thông tin phòng và thời gian thuê");
        lblD.setFont(UITheme.FONT_SMALL);
        lblD.setForeground(new Color(224, 231, 255));

        pnlHeader.add(lblT);
        pnlHeader.add(lblD);
        add(pnlHeader, BorderLayout.NORTH);

        // Body Form nhập liệu
        JPanel pnlBody = new JPanel(new GridLayout(7, 2, 12, 14));
        pnlBody.setBackground(UITheme.CARD_BG);
        pnlBody.setBorder(new EmptyBorder(20, 25, 20, 25));

        txtMaHD = taoTextField();
        txtNgayHD = taoTextField();
        txtNgayHD.setText("2026-10-30");
        txtTenKH = taoTextField();
        txtMaPhong = taoTextField();
        txtDonGia = taoTextField();
        txtSoLuong = taoTextField();

        cbLoaiHD = new JComboBox<>(new String[]{"HD Giờ", "HD Ngày"});
        cbLoaiHD.setFont(UITheme.FONT_BODY);

        lblSL = new JLabel("Số Giờ Thuê:");
        lblSL.setFont(UITheme.FONT_SUB);
        lblSL.setForeground(UITheme.TEXT_DARK);

        cbLoaiHD.addActionListener(e -> {
            boolean isGio = cbLoaiHD.getSelectedIndex() == 0;
            lblSL.setText(isGio ? "Số Giờ Thuê:" : "Số Ngày Thuê:");
        });

        themRow(pnlBody, "Mã Hóa Đơn:", txtMaHD);
        themRow(pnlBody, "Ngày Lập (YYYY-MM-DD):", txtNgayHD);
        themRow(pnlBody, "Tên Khách Hàng:", txtTenKH);
        themRow(pnlBody, "Mã Số Phòng:", txtMaPhong);
        themRow(pnlBody, "Đơn Giá (VNĐ):", txtDonGia);
        themRow(pnlBody, "Hình Thức Thuê:", cbLoaiHD);

        pnlBody.add(lblSL);
        pnlBody.add(txtSoLuong);

        add(pnlBody, BorderLayout.CENTER);

        // Footer nút bấm
        JPanel pnlBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        pnlBottom.setBackground(new Color(248, 250, 252));
        pnlBottom.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UITheme.CARD_BORDER));

        JButton btnHuy = new JButton("Hủy Bỏ");
        btnHuy.setFont(UITheme.FONT_BODY);
        btnHuy.addActionListener(e -> dispose());

        JButton btnLuu = UITheme.createPrimaryButton("Lưu Hóa Đơn", UITheme.COLOR_THEM);
        btnLuu.addActionListener(e -> xuLyThem());

        pnlBottom.add(btnHuy);
        pnlBottom.add(btnLuu);
        add(pnlBottom, BorderLayout.SOUTH);
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

    private void xuLyThem() {
        try {
            int maHD = Integer.parseInt(txtMaHD.getText().trim());
            String ngayHD = txtNgayHD.getText().trim();
            String tenKH = txtTenKH.getText().trim();
            String maPhong = txtMaPhong.getText().trim();
            double donGia = Double.parseDouble(txtDonGia.getText().trim());
            int sl = Integer.parseInt(txtSoLuong.getText().trim());
            String loai = cbLoaiHD.getSelectedItem().toString();

            if (tenKH.isEmpty() || maPhong.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ Tên Khách Hàng và Mã Phòng!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ThemHDControl control = loai.equals("HD Giờ") ? new ThemHDControlGio() : new ThemHDControlNgay();
            RequestData req = new RequestData(maHD, ngayHD, tenKH, maPhong, donGia, loai, sl);
            ResponseData res = control.execute(req);

            ThemHDResultDialogGUI.show(this, res);

            if (res.isSuccess()) {
                dispose();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Mã HĐ, đơn giá và số lượng phải là các số hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        }
    }
}