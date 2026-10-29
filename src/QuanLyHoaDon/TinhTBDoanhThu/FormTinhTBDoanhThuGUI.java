package QuanLyHoaDon.TinhTBDoanhThu;

import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;
import QuanLyHoaDon.UITheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormTinhTBDoanhThuGUI extends JFrame {
    private JComboBox<String> cbLoai;
    private JButton btnTinh;

    public FormTinhTBDoanhThuGUI() {
        super("Tính Doanh Thu Bình Quân");
        khoiTao();
    }

    private void khoiTao() {
        setSize(440, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(UITheme.BG_MAIN);
        setLayout(new BorderLayout());

        // Header Banner màu Sky Cyan
        JPanel pnlHeader = new JPanel(new GridLayout(2, 1, 0, 4));
        pnlHeader.setBackground(UITheme.COLOR_DOANHTHU);
        pnlHeader.setBorder(new EmptyBorder(14, 20, 14, 20));

        JLabel lblT = new JLabel("TÍNH TOÁN DOANH THU TRUNG BÌNH");
        lblT.setFont(UITheme.FONT_TITLE);
        lblT.setForeground(Color.WHITE);

        JLabel lblD = new JLabel("Báo cáo mức thu nhập bình quân theo từng phân loại hóa đơn");
        lblD.setFont(UITheme.FONT_SMALL);
        lblD.setForeground(new Color(224, 242, 254));

        pnlHeader.add(lblT);
        pnlHeader.add(lblD);
        add(pnlHeader, BorderLayout.NORTH);

        // Body Card
        JPanel pnlCenter = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 25));
        pnlCenter.setBackground(UITheme.BG_MAIN);

        JPanel pnlCard = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 12));
        pnlCard.setBackground(Color.WHITE);
        pnlCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.CARD_BORDER, 1, true),
                new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel lblSelect = new JLabel("Loại hình thuê:");
        lblSelect.setFont(UITheme.FONT_SUB);
        lblSelect.setForeground(UITheme.TEXT_DARK);

        cbLoai = new JComboBox<>(new String[]{"Giờ", "Ngày"});
        cbLoai.setFont(UITheme.FONT_BODY);
        cbLoai.setPreferredSize(new Dimension(100, 32));

        pnlCard.add(lblSelect);
        pnlCard.add(cbLoai);
        pnlCenter.add(pnlCard);
        add(pnlCenter, BorderLayout.CENTER);

        // Footer Button
        JPanel pnlBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 14));
        pnlBottom.setBackground(Color.WHITE);
        pnlBottom.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UITheme.CARD_BORDER));

        btnTinh = UITheme.createPrimaryButton("Tính Toán Ngay", UITheme.COLOR_DOANHTHU);
        btnTinh.addActionListener(e -> {
            String loai = cbLoai.getSelectedItem().toString();
            TinhTBDoanhThuControl control = loai.equals("Giờ") ? new TinhTBDoanhHDThuGioControl() : new TinhTBDoanhHDThuNgayControl();
            ResponseData res = control.execute(new RequestData());
            new ResultTinhTBDoanhThuGUI(this, loai, res.getAvgResult()).setVisible(true);
        });

        pnlBottom.add(btnTinh);
        add(pnlBottom, BorderLayout.SOUTH);
    }
}