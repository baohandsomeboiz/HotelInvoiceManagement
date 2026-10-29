package QuanLyHoaDon;

import QuanLyHoaDon.InHD.TableDSHDGUI;
import QuanLyHoaDon.SuaHD.FormSuaHDGUI;
import QuanLyHoaDon.ThemHD.FormThemHDGUI;
import QuanLyHoaDon.TimKiemHD.FormTimKiemHDGUI;
import QuanLyHoaDon.TinhTBDoanhThu.FormTinhTBDoanhThuGUI;
import QuanLyHoaDon.TinhTongSLPhong.TinhTongSLHDGUI;
import QuanLyHoaDon.XoaHD.FormXoaHDGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class QLHDMenuGUI extends JFrame {

    private JLabel lblTotalInvoices;
    private JLabel lblHourInvoices;
    private JLabel lblDayInvoices;

    public QLHDMenuGUI() {
        super("Hệ Thống Quản Lý Hóa Đơn Khách Sạn");
        khoiTaoGiaoDien();
        capNhatSoLieuKPI();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                capNhatSoLieuKPI();
            }
        });
    }

    private void khoiTaoGiaoDien() {
        setSize(960, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(UITheme.BG_MAIN);
        setLayout(new BorderLayout(0, 0));

        khoiTaoMenuBar();

        // 1. TOP HEADER BANNER
        JPanel panelTopContainer = new JPanel(new BorderLayout());
        panelTopContainer.setOpaque(false);

        JPanel panelHero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                UITheme.setupAntialiasing(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(15, 23, 42), getWidth(), 0, new Color(30, 58, 138));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelHero.setLayout(new BorderLayout());
        panelHero.setBorder(new EmptyBorder(22, 32, 22, 32));

        JLabel lblTitle = new JLabel("HỆ THỐNG QUẢN LÝ HÓA ĐƠN KHÁCH SẠN");
        lblTitle.setFont(UITheme.FONT_HEADER);
        lblTitle.setForeground(Color.WHITE);

        JLabel lblSub = new JLabel("Trường Đại học Gia Định • Khoa Công Nghệ Thông Tin");
        lblSub.setFont(UITheme.FONT_BODY);
        lblSub.setForeground(new Color(203, 213, 225));

        JPanel pnlTitles = new JPanel(new GridLayout(2, 1, 0, 4));
        pnlTitles.setOpaque(false);
        pnlTitles.add(lblTitle);
        pnlTitles.add(lblSub);

        JLabel lblBadgeLive = new JLabel("");
        lblBadgeLive.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblBadgeLive.setForeground(new Color(52, 211, 153));

        panelHero.add(pnlTitles, BorderLayout.WEST);
        panelHero.add(lblBadgeLive, BorderLayout.EAST);
        panelTopContainer.add(panelHero, BorderLayout.NORTH);

        // 2. THANH KPI METRICS
        JPanel panelKPI = new JPanel(new GridLayout(1, 3, 16, 0));
        panelKPI.setBackground(UITheme.BG_MAIN);
        panelKPI.setBorder(new EmptyBorder(16, 32, 4, 32));

        lblTotalInvoices = new JLabel("0 HĐ");
        lblHourInvoices = new JLabel("0 HĐ");
        lblDayInvoices = new JLabel("0 HĐ");

        panelKPI.add(taoMetricCard("TỔNG HÓA ĐƠN", lblTotalInvoices, UITheme.PRIMARY));
        panelKPI.add(taoMetricCard("PHÒNG THUÊ THEO GIỜ", lblHourInvoices, UITheme.COLOR_THEM));
        panelKPI.add(taoMetricCard("PHÒNG THUÊ THEO NGÀY", lblDayInvoices, UITheme.COLOR_THONGKE));
        panelTopContainer.add(panelKPI, BorderLayout.SOUTH);

        add(panelTopContainer, BorderLayout.NORTH);

        // 3. 6 THẺ ACTION CARDS
        JPanel panelGrid = new JPanel(new GridLayout(2, 3, 18, 18));
        panelGrid.setBackground(UITheme.BG_MAIN);
        panelGrid.setBorder(new EmptyBorder(16, 32, 20, 32));

        panelGrid.add(taoActionCard("LẬP HÓA ĐƠN", "Thêm Hóa Đơn Mới", "Tạo hóa đơn phòng giờ/ngày và lưu trữ", "THEM", UITheme.COLOR_THEM, () -> new FormThemHDGUI().setVisible(true)));
        panelGrid.add(taoActionCard("DANH MỤC", "In Danh Sách Hóa Đơn", "Bảng dữ liệu chi tiết, click phải để sửa", "IN", UITheme.COLOR_IN, () -> new TableDSHDGUI().setVisible(true)));
        panelGrid.add(taoActionCard("TRA CỨU", "Tìm Kiếm Hóa Đơn", "Tra cứu thông tin chính xác theo mã", "TIM", UITheme.COLOR_TIM, () -> new FormTimKiemHDGUI().setVisible(true)));
        panelGrid.add(taoActionCard("QUẢN TRỊ", "Xóa Hóa Đơn", "Chọn và loại bỏ hóa đơn khỏi hệ thống", "XOA", UITheme.COLOR_XOA, () -> new FormXoaHDGUI().setVisible(true)));
        panelGrid.add(taoActionCard("THỐNG KÊ", "Tổng Số Lượng Phòng", "Thống kê tổng số lượng theo phân loại", "THONGKE", UITheme.COLOR_THONGKE, () -> new TinhTongSLHDGUI().setVisible(true)));
        panelGrid.add(taoActionCard("BÁO CÁO", "Doanh Thu Trung Bình", "Tính doanh thu bình quân phòng giờ/ngày", "DOANHTHU", UITheme.COLOR_DOANHTHU, () -> new FormTinhTBDoanhThuGUI().setVisible(true)));

        add(panelGrid, BorderLayout.CENTER);

        // 4. FOOTER
        JPanel panelFooter = new JPanel(new BorderLayout());
        panelFooter.setBackground(Color.WHITE);
        panelFooter.setBorder(new EmptyBorder(12, 32, 12, 32));

        JLabel lblAuthor = new JLabel("|  Sinh viên thực hiện: NGUYỄN NHỰT KHANG");
        lblAuthor.setFont(UITheme.FONT_SUB);
        lblAuthor.setForeground(UITheme.TEXT_DARK);

        JLabel lblInstructor = new JLabel("GVHD: ThS. LÊ HUỲNH PHƯỚC ");
        lblInstructor.setFont(UITheme.FONT_SMALL);
        lblInstructor.setForeground(UITheme.TEXT_MUTED);

        panelFooter.add(lblAuthor, BorderLayout.WEST);
        panelFooter.add(lblInstructor, BorderLayout.EAST);
        add(panelFooter, BorderLayout.SOUTH);
    }

    private JPanel taoMetricCard(String title, JLabel lblValue, Color accentColor) {
        JPanel card = new JPanel(new BorderLayout(5, 4));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.CARD_BORDER, 1, true),
                new EmptyBorder(12, 16, 12, 16)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblTitle.setForeground(UITheme.TEXT_MUTED);

        lblValue.setFont(UITheme.FONT_KPI);
        lblValue.setForeground(accentColor);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);
        return card;
    }

    private JPanel taoActionCard(String tag, String title, String desc, String iconType, Color accentColor, Runnable action) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                UITheme.setupAntialiasing(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 14, 14);
                g2.setColor(UITheme.CARD_BORDER);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 14, 14);
            }
        };
        card.setLayout(new BorderLayout(14, 10));
        card.setBackground(UITheme.CARD_BG);
        card.setBorder(new EmptyBorder(16, 18, 16, 18));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JComponent iconBox = UITheme.createVectorIcon(iconType, accentColor);
        JPanel pnlIconWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlIconWrapper.setOpaque(false);
        pnlIconWrapper.add(iconBox);

        JPanel pnlContent = new JPanel();
        pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.Y_AXIS));
        pnlContent.setOpaque(false);

        JLabel lblTag = new JLabel(tag);
        lblTag.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblTag.setForeground(accentColor);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(UITheme.FONT_TITLE);
        lblTitle.setForeground(UITheme.TEXT_DARK);

        JLabel lblDesc = new JLabel("<html><p style='color:#64748B; font-size:11px; width:135px;'>" + desc + "</p></html>");
        lblDesc.setFont(UITheme.FONT_BODY);

        pnlContent.add(lblTag);
        pnlContent.add(Box.createVerticalStrut(3));
        pnlContent.add(lblTitle);
        pnlContent.add(Box.createVerticalStrut(4));
        pnlContent.add(lblDesc);

        JLabel lblAction = new JLabel("Truy cập ->");
        lblAction.setFont(UITheme.FONT_SUB);
        lblAction.setForeground(accentColor);

        card.add(pnlIconWrapper, BorderLayout.WEST);
        card.add(pnlContent, BorderLayout.CENTER);
        card.add(lblAction, BorderLayout.SOUTH);

        // GẮN SỰ KIỆN CLICK ĐỆ QUY CHO TẤT CẢ PHẦN TỬ CON TRÊN THẺ
        MouseAdapter clickAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                action.run();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(UITheme.CARD_HOVER);
                card.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(UITheme.CARD_BG);
                card.repaint();
            }
        };

        dangKyClickDeQuy(card, clickAdapter);

        return card;
    }

    // Hàm duyệt gán sự kiện cho mọi component con bên trong
    private void dangKyClickDeQuy(Component comp, MouseAdapter adapter) {
        comp.addMouseListener(adapter);
        comp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (comp instanceof Container) {
            for (Component child : ((Container) comp).getComponents()) {
                dangKyClickDeQuy(child, adapter);
            }
        }
    }

    private void capNhatSoLieuKPI() {
        MemoryHDDatabase db = MemoryHDDatabase.getInstance();
        int tong = db.getAll().size();
        int gio = db.tinhTongSL("gio");
        int ngay = db.tinhTongSL("ngay");

        lblTotalInvoices.setText(tong + " Hóa Đơn");
        lblHourInvoices.setText(gio + " Phòng");
        lblDayInvoices.setText(ngay + " Phòng");
    }

    private void khoiTaoMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuChucNang = new JMenu("Chức năng");
        JMenuItem itemThem = new JMenuItem("Thêm");
        JMenuItem itemXoa = new JMenuItem("Xóa");
        JMenuItem itemTongSL = new JMenuItem("Tính tổng số lượng phòng");
        JMenuItem itemTBT = new JMenuItem("Tính trung bình doanh thu tháng");
        JMenuItem itemSua = new JMenuItem("Sửa");
        JMenuItem itemTimKiem = new JMenuItem("Tìm kiếm");
        JMenuItem itemIn = new JMenuItem("In");
        JMenuItem itemThoat = new JMenuItem("Thoát");

        itemThem.addActionListener(e -> new FormThemHDGUI().setVisible(true));
        itemIn.addActionListener(e -> new TableDSHDGUI().setVisible(true));
        itemTimKiem.addActionListener(e -> new FormTimKiemHDGUI().setVisible(true));
        itemXoa.addActionListener(e -> new FormXoaHDGUI().setVisible(true));
        itemTongSL.addActionListener(e -> new TinhTongSLHDGUI().setVisible(true));
        itemTBT.addActionListener(e -> new FormTinhTBDoanhThuGUI().setVisible(true));
        itemSua.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Nhập mã hóa đơn cần sửa:");
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int ma = Integer.parseInt(input.trim());
                    if (MemoryHDDatabase.getInstance().findHoaDon(ma) == null) {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy mã HĐ: " + ma, "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } else {
                        new FormSuaHDGUI(ma, this::capNhatSoLieuKPI).setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Mã hóa đơn phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        itemThoat.addActionListener(e -> System.exit(0));

        menuChucNang.add(itemThem);
        menuChucNang.add(itemXoa);
        menuChucNang.add(itemTongSL);
        menuChucNang.add(itemTBT);
        menuChucNang.add(itemSua);
        menuChucNang.add(itemTimKiem);
        menuChucNang.add(itemIn);
        menuChucNang.addSeparator();
        menuChucNang.add(itemThoat);

        JMenu menuTuyChon = new JMenu("Tùy chọn");
        JMenuItem itemThongTinPM = new JMenuItem("Thông tin phiên bản");
        itemThongTinPM.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Hệ Thống Quản Lý Hóa Đơn Khách Sạn (GDU Modern Edition)\nPhiên bản: 2.5 Pro Dashboard\nKiến trúc chuẩn: BCE & OOP Java",
                "Thông Tin Phiên Bản", JOptionPane.INFORMATION_MESSAGE));
        menuTuyChon.add(itemThongTinPM);

        JMenu menuHoTro = new JMenu("Hỗ trợ");
        JMenuItem itemTacGia = new JMenuItem("Thông tin sinh viên");
        itemTacGia.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Đề tài: Quản Lý Hóa Đơn Khách Sạn\nSinh viên: NGUYỄN NHỰT KHANG - MSSV: 24150010\nGiảng viên hướng dẫn: ThS. LÊ HUỲNH PHƯỚC\nTrường Đại học Gia Định",
                "Thông Tin Tác Giả", JOptionPane.INFORMATION_MESSAGE));
        menuHoTro.add(itemTacGia);

        menuBar.add(menuChucNang);
        menuBar.add(menuTuyChon);
        menuBar.add(menuHoTro);

        setJMenuBar(menuBar);
    }
}