package QuanLyHoaDon.TinhTongSLPhong;

import QuanLyHoaDon.UITheme;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ResultTinhTongSLHDGUI extends JDialog {
    public ResultTinhTongSLHDGUI(JFrame parent, String loai, int total) {
        super(parent, "Kết Quả Thống Kê", true);
        setSize(380, 220);
        setLocationRelativeTo(parent);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel pnlCenter = new JPanel(new GridLayout(2, 1, 0, 8));
        pnlCenter.setBackground(Color.WHITE);
        pnlCenter.setBorder(new EmptyBorder(25, 20, 15, 20));

        JLabel lblTitle = new JLabel("Tổng Số Lượng Hóa Đơn (" + loai + ")", SwingConstants.CENTER);
        lblTitle.setFont(UITheme.FONT_SUB);
        lblTitle.setForeground(UITheme.TEXT_MUTED);

        JLabel lblNumber = new JLabel(total + " Phòng", SwingConstants.CENTER);
        lblNumber.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblNumber.setForeground(UITheme.COLOR_THONGKE);

        pnlCenter.add(lblTitle);
        pnlCenter.add(lblNumber);
        add(pnlCenter, BorderLayout.CENTER);

        JPanel pBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 12));
        pBtn.setBackground(new Color(248, 250, 252));
        pBtn.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UITheme.CARD_BORDER));

        JButton btnClose = UITheme.createPrimaryButton("Đóng Cửa Sổ", UITheme.TEXT_MUTED);
        btnClose.addActionListener(e -> dispose());
        pBtn.add(btnClose);
        add(pBtn, BorderLayout.SOUTH);
    }
}