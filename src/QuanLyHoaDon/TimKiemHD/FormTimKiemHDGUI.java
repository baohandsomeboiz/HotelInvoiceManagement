package QuanLyHoaDon.TimKiemHD;

import QuanLyHoaDon.HoaDon;
import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;
import QuanLyHoaDon.UITheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.DecimalFormat;

public class FormTimKiemHDGUI extends JFrame {
    private JTextField txtMaHD;
    private JComboBox<String> cbLoaiTimKiem;
    private JButton btnTimKiem;
    private JTable tableResult;
    private DefaultTableModel modelResult;

    public FormTimKiemHDGUI() {
        super("Tra Cứu Hóa Đơn Khách Sạn");
        khoiTaoThanhPhan();
    }

    private void khoiTaoThanhPhan() {
        setSize(950, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(UITheme.BG_MAIN);
        setLayout(new BorderLayout());

        // 1. Header Banner màu Hổ phách (Amber)
        JPanel pnlHeader = new JPanel(new GridLayout(2, 1, 0, 4));
        pnlHeader.setBackground(UITheme.COLOR_TIM);
        pnlHeader.setBorder(new EmptyBorder(16, 25, 16, 25));

        JLabel lblT = new JLabel("TRA CỨU THÔNG TIN HÓA ĐƠN");
        lblT.setFont(UITheme.FONT_TITLE);
        lblT.setForeground(Color.WHITE);

        JLabel lblD = new JLabel("Nhập chính xác mã hóa đơn để xem chi tiết thông tin thanh toán");
        lblD.setFont(UITheme.FONT_SMALL);
        lblD.setForeground(new Color(254, 243, 199));

        pnlHeader.add(lblT);
        pnlHeader.add(lblD);
        add(pnlHeader, BorderLayout.NORTH);

        // 2. Khu vực thanh tìm kiếm (Card Search Bar)
        JPanel pnlCenterContainer = new JPanel(new BorderLayout(0, 15));
        pnlCenterContainer.setBackground(UITheme.BG_MAIN);
        pnlCenterContainer.setBorder(new EmptyBorder(15, 20, 15, 20));

        JPanel pnlSearchBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        pnlSearchBar.setBackground(Color.WHITE);
        pnlSearchBar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.CARD_BORDER, 1, true),
                new EmptyBorder(6, 12, 6, 12)
        ));

        cbLoaiTimKiem = new JComboBox<>(new String[]{"Theo Mã HĐ"});
        cbLoaiTimKiem.setFont(UITheme.FONT_BODY);

        txtMaHD = new JTextField(12);
        txtMaHD.setFont(UITheme.FONT_BODY);
        txtMaHD.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.CARD_BORDER, 1),
                new EmptyBorder(5, 8, 5, 8)
        ));

        btnTimKiem = UITheme.createPrimaryButton("Tìm Kiếm Ngay", UITheme.COLOR_TIM);
        btnTimKiem.addActionListener(e -> thucHienTimKiem());

        pnlSearchBar.add(new JLabel("Tiêu chí tra cứu:"));
        pnlSearchBar.add(cbLoaiTimKiem);
        pnlSearchBar.add(new JLabel("Mã Hóa Đơn:"));
        pnlSearchBar.add(txtMaHD);
        pnlSearchBar.add(btnTimKiem);

        pnlCenterContainer.add(pnlSearchBar, BorderLayout.NORTH);

        // 3. Bảng kết quả thiết kế đồng bộ với TableDSHDGUI
        String[] headers = {"Mã HĐ", "Tên Khách Hàng", "Phòng", "Loại HĐ", "Thời Lượng", "Đơn Giá", "Thành Tiền"};
        modelResult = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        tableResult = new JTable(modelResult) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 250, 252));
                } else {
                    c.setBackground(new Color(254, 243, 199));
                }
                return c;
            }
        };

        tableResult.setRowHeight(36);
        tableResult.setFont(UITheme.FONT_BODY);
        tableResult.setForeground(UITheme.TEXT_DARK);
        tableResult.setShowGrid(true);
        tableResult.setGridColor(UITheme.CARD_BORDER);

        JTableHeader header = tableResult.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 38));
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object val, boolean isSel, boolean hasFocus, int r, int c) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(t, val, isSel, hasFocus, r, c);
                l.setFont(UITheme.FONT_SUB);
                l.setBackground(new Color(30, 41, 59));
                l.setForeground(Color.WHITE);
                l.setHorizontalAlignment(SwingConstants.CENTER);
                l.setOpaque(true);
                return l;
            }
        });

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tableResult.getColumnModel().getColumn(0).setPreferredWidth(70);
        tableResult.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableResult.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableResult.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        DecimalFormat df = new DecimalFormat("#,##0 VNĐ");
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof Number) setText(df.format(value));
                else super.setValue(value);
            }
        };
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        tableResult.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        tableResult.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object val, boolean isSel, boolean hasFocus, int r, int c) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(t, val, isSel, hasFocus, r, c);
                l.setHorizontalAlignment(SwingConstants.RIGHT);
                l.setFont(UITheme.FONT_SUB);
                l.setForeground(UITheme.COLOR_TIM);
                if (val instanceof Number) l.setText(df.format(val));
                return l;
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableResult);
        scrollPane.setBorder(BorderFactory.createLineBorder(UITheme.CARD_BORDER, 1));
        pnlCenterContainer.add(scrollPane, BorderLayout.CENTER);

        add(pnlCenterContainer, BorderLayout.CENTER);
    }

    public void thucHienTimKiem() {
        try {
            String input = txtMaHD.getText().trim();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn cần tìm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int maHD = Integer.parseInt(input);
            TimKiemHDControl control = new TimKiemHDControl();
            ResponseData res = control.execute(new RequestData(maHD));

            modelResult.setRowCount(0);
            if (res.isSuccess() && res.getHoaDon() != null) {
                HoaDon hd = res.getHoaDon();
                String thoiLuong = (int) hd.getSoLuong() + (hd.getLoaiHD().toLowerCase().contains("giờ") ? " Giờ" : " Ngày");
                modelResult.addRow(new Object[]{
                        hd.getMaHD(),
                        hd.getTenKH(),
                        hd.getMaPhong(),
                        hd.getLoaiHD(),
                        thoiLuong,
                        hd.getDonGia(),
                        hd.tinhTien()
                });
            } else {
                JOptionPane.showMessageDialog(this, res.getMessage(), "Kết Quả Tra Cứu", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Mã hóa đơn phải là số nguyên hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        }
    }
}