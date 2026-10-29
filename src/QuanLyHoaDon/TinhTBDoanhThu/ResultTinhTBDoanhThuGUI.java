package QuanLyHoaDon.TinhTBDoanhThu;

import QuanLyHoaDon.UITheme;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;

public class ResultTinhTBDoanhThuGUI extends JDialog {
    public ResultTinhTBDoanhThuGUI(JFrame parent, String loai, double avg) {
        super(parent, "Báo Cáo Doanh Thu Trung Bình", true);
        setSize(420, 230);
        setLocationRelativeTo(parent);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        DecimalFormat df = new DecimalFormat("#,##0 VNĐ");

        JPanel pnlCenter = new JPanel(new GridLayout(2, 1, 0, 8));
        pnlCenter.setBackground(Color.WHITE);
        pnlCenter.setBorder(new EmptyBorder(25, 20, 15, 20));

        JLabel lblTitle = new JLabel("Doanh Thu Trung Bình Theo HĐ " + loai, SwingConstants.CENTER);
        lblTitle.setFont(UITheme.FONT_SUB);
        lblTitle.setForeground(UITheme.TEXT_MUTED);

        JLabel lblMoney = new JLabel(df.format(avg), SwingConstants.CENTER);
        lblMoney.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblMoney.setForeground(UITheme.COLOR_DOANHTHU);

        pnlCenter.add(lblTitle);
        pnlCenter.add(lblMoney);
        add(pnlCenter, BorderLayout.CENTER);

        JPanel pBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 12));
        pBtn.setBackground(new Color(248, 250, 252));
        pBtn.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UITheme.CARD_BORDER));

        JButton btnClose = UITheme.createPrimaryButton("Đóng Báo Cáo", UITheme.TEXT_MUTED);
        btnClose.addActionListener(e -> dispose());
        pBtn.add(btnClose);
        add(pBtn, BorderLayout.SOUTH);
    }
}