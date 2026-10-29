package QuanLyHoaDon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UITheme {
    // 1. Palette màu Slate & SaaS hiện đại
    public static final Color BG_MAIN       = new Color(241, 245, 249); // Slate-100
    public static final Color CARD_BG       = Color.WHITE;
    public static final Color CARD_BORDER   = new Color(226, 232, 240); // Slate-200
    public static final Color CARD_HOVER    = new Color(248, 250, 252);
    public static final Color PRIMARY       = new Color(37, 99, 235);   // Royal Blue
    public static final Color PRIMARY_HOVER = new Color(29, 78, 216);   // Blue-700
    public static final Color TEXT_DARK     = new Color(15, 23, 42);    // Slate-900
    public static final Color TEXT_MUTED    = new Color(100, 116, 139); // Slate-500

    // 2. Các biến màu Accent (Sửa triệt để lỗi thiếu ACCENT_PURPLE)
    public static final Color ACCENT_GREEN  = new Color(16, 185, 129); // Emerald
    public static final Color ACCENT_AMBER  = new Color(245, 158, 11);  // Amber
    public static final Color ACCENT_RED    = new Color(239, 68, 68);   // Rose
    public static final Color ACCENT_PURPLE = new Color(139, 92, 246);  // Violet
    public static final Color ACCENT_CYAN   = new Color(14, 165, 233);  // Sky Cyan

    public static final Color COLOR_THEM    = ACCENT_GREEN;
    public static final Color COLOR_IN      = PRIMARY;
    public static final Color COLOR_TIM     = ACCENT_AMBER;
    public static final Color COLOR_XOA     = ACCENT_RED;
    public static final Color COLOR_THONGKE = ACCENT_PURPLE;
    public static final Color COLOR_DOANHTHU= ACCENT_CYAN;

    // 3. Typography
    public static final Font FONT_HEADER = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FONT_TITLE  = new Font("Segoe UI", Font.BOLD, 15);
    public static final Font FONT_SUB    = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_BODY   = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_SMALL  = new Font("Segoe UI", Font.PLAIN, 11);
    public static final Font FONT_KPI    = new Font("Segoe UI", Font.BOLD, 20);

    // Kích hoạt khử răng cưa
    public static void setupAntialiasing(Graphics g) {
        if (g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
    }

    // Nút bấm chính có hiệu ứng bo tròn (Sửa triệt để lỗi createPrimaryButton)
    public static JButton createPrimaryButton(String text, Color bg) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                setupAntialiasing(g);
                Graphics2D g2 = (Graphics2D) g;
                if (getModel().isPressed()) {
                    g2.setColor(bg.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(bg.brighter());
                } else {
                    g2.setColor(bg);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                super.paintComponent(g);
            }
        };
        btn.setFont(FONT_SUB);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(btn.getPreferredSize().width + 24, 38));
        return btn;
    }

    // Vector Icon tự vẽ
    public static JComponent createVectorIcon(String type, Color color) {
        return new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setupAntialiasing(g);
                Graphics2D g2 = (Graphics2D) g;

                g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 28));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

                g2.setColor(color);
                g2.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

                int cx = getWidth() / 2;
                int cy = getHeight() / 2;

                switch (type) {
                    case "THEM":
                        g2.drawLine(cx - 7, cy, cx + 7, cy);
                        g2.drawLine(cx, cy - 7, cx, cy + 7);
                        break;
                    case "IN":
                        g2.drawRoundRect(cx - 8, cy - 9, 16, 18, 4, 4);
                        g2.drawLine(cx - 4, cy - 4, cx + 4, cy - 4);
                        g2.drawLine(cx - 4, cy, cx + 4, cy);
                        g2.drawLine(cx - 4, cy + 4, cx + 2, cy + 4);
                        break;
                    case "TIM":
                        g2.drawOval(cx - 7, cy - 8, 10, 10);
                        g2.drawLine(cx + 1, cy, cx + 7, cy + 6);
                        break;
                    case "XOA":
                        g2.drawRoundRect(cx - 6, cy - 4, 12, 12, 3, 3);
                        g2.drawLine(cx - 8, cy - 4, cx + 8, cy - 4);
                        g2.drawLine(cx - 3, cy - 7, cx + 3, cy - 7);
                        g2.drawLine(cx - 2, cy - 1, cx - 2, cy + 5);
                        g2.drawLine(cx + 2, cy - 1, cx + 2, cy + 5);
                        break;
                    case "THONGKE":
                        g2.drawRect(cx - 7, cy + 1, 3, 6);
                        g2.drawRect(cx - 2, cy - 4, 3, 11);
                        g2.drawRect(cx + 3, cy - 8, 3, 15);
                        break;
                    case "DOANHTHU":
                        g2.drawOval(cx - 9, cy - 9, 18, 18);
                        g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                        FontMetrics fm = g2.getFontMetrics();
                        g2.drawString("đ", cx - fm.stringWidth("đ") / 2, cy + 4);
                        break;
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(42, 42);
            }
        };
    }
}