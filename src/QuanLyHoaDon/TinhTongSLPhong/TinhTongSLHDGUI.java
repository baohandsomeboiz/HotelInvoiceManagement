package QuanLyHoaDon.TinhTongSLPhong;

import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;
import QuanLyHoaDon.UITheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TinhTongSLHDGUI extends JFrame {
    private JComboBox<String> cbLoai;
    private JButton btnTinhTong;

    public TinhTongSLHDGUI() {
        super("Thống Kê Tổng Số Lượng Phòng");
        khoiTao();
    }

    private void khoiTao() {
        setSize(440, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(UITheme.BG_MAIN);
        setLayout(new BorderLayout());

        // Header Banner màu Tím Violet
        JPanel pnlHeader = new JPanel(new GridLayout(2, 1, 0, 4));
        pnlHeader.setBackground(UITheme.COLOR_THONGKE);
        pnlHeader.setBorder(new EmptyBorder(14, 20, 14, 20));

        JLabel lblT = new JLabel("THỐNG KÊ SỐ LƯỢNG HÓA ĐƠN");
        lblT.setFont(UITheme.FONT_TITLE);
        lblT.setForeground(Color.WHITE);

        JLabel lblD = new JLabel("Chọn phân loại thuê phòng để xem tổng số lượng phát sinh");
        lblD.setFont(UITheme.FONT_SMALL);
        lblD.setForeground(new Color(243, 232, 255));

        pnlHeader.add(lblT);
        pnlHeader.add(lblD);
        add(pnlHeader, BorderLayout.NORTH);

        // Card chọn phân loại
        JPanel pnlCenter = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 25));
        pnlCenter.setBackground(UITheme.BG_MAIN);

        JPanel pnlCard = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 12));
        pnlCard.setBackground(Color.WHITE);
        pnlCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.CARD_BORDER, 1, true),
                new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel lblSelect = new JLabel("Chọn loại hóa đơn:");
        lblSelect.setFont(UITheme.FONT_SUB);
        lblSelect.setForeground(UITheme.TEXT_DARK);

        cbLoai = new JComboBox<>(new String[]{"Giờ", "Ngày"});
        cbLoai.setFont(UITheme.FONT_BODY);
        cbLoai.setPreferredSize(new Dimension(100, 32));

        pnlCard.add(lblSelect);
        pnlCard.add(cbLoai);
        pnlCenter.add(pnlCard);
        add(pnlCenter, BorderLayout.CENTER);

        // Nút bấm thống kê
        JPanel pnlBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 14));
        pnlBottom.setBackground(Color.WHITE);
        pnlBottom.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UITheme.CARD_BORDER));

        btnTinhTong = UITheme.createPrimaryButton("Xem Thống Kê", UITheme.COLOR_THONGKE);
        btnTinhTong.addActionListener(e -> {
            String loai = cbLoai.getSelectedItem().toString();
            TinhTongSLHDControl control = loai.equals("Giờ") ? new TongSLHDGioControl() : new TongSLHDNgayControl();
            ResponseData res = control.execute(new RequestData());
            new ResultTinhTongSLHDGUI(this, loai, res.getCountResult()).setVisible(true);
        });

        pnlBottom.add(btnTinhTong);
        add(pnlBottom, BorderLayout.SOUTH);
    }
}