package QuanLyHoaDon.XoaHD;

import QuanLyHoaDon.HoaDon;
import QuanLyHoaDon.HoaDonTheoGio;
import QuanLyHoaDon.InHD.InDSHDControl;
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

public class FormXoaHDGUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton btnXoa;

    public FormXoaHDGUI() {
        super("Quản Lý & Xóa Hóa Đơn Khách Sạn");
        khoiTaoThanhPhan();
        napDuLieu();
    }

    private void khoiTaoThanhPhan() {
        setSize(950, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(UITheme.BG_MAIN);
        setLayout(new BorderLayout());

        // Header Banner màu Đỏ Rose
        JPanel pnlHeader = new JPanel(new GridLayout(2, 1, 0, 4));
        pnlHeader.setBackground(UITheme.COLOR_XOA);
        pnlHeader.setBorder(new EmptyBorder(16, 25, 16, 25));

        JLabel lblT = new JLabel("QUẢN TRỊ & HỦY BỎ HÓA ĐƠN");
        lblT.setFont(UITheme.FONT_TITLE);
        lblT.setForeground(Color.WHITE);

        JLabel lblD = new JLabel("Chọn một hóa đơn trong danh sách và bấm nút xóa để loại bỏ khỏi cơ sở dữ liệu");
        lblD.setFont(UITheme.FONT_SMALL);
        lblD.setForeground(new Color(254, 226, 226));

        pnlHeader.add(lblT);
        pnlHeader.add(lblD);
        add(pnlHeader, BorderLayout.NORTH);

        // Bảng danh sách
        String[] headers = {"Mã HD", "Tên Khách Hàng", "Phòng", "Đơn Giá", "Hình Thức", "Số Giờ", "Số Ngày", "Thành Tiền"};
        model = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        table = new JTable(model) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 250, 252));
                } else {
                    c.setBackground(new Color(254, 226, 226)); // Màu đỏ nhạt khi chọn dòng xóa
                }
                return c;
            }
        };

        table.setRowHeight(36);
        table.setFont(UITheme.FONT_BODY);
        table.setForeground(UITheme.TEXT_DARK);
        table.setShowGrid(true);
        table.setGridColor(UITheme.CARD_BORDER);

        JTableHeader header = table.getTableHeader();
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
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        DecimalFormat df = new DecimalFormat("#,##0 VNĐ");
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof Number) setText(df.format(value));
                else super.setValue(value);
            }
        };
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object val, boolean isSel, boolean hasFocus, int r, int c) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(t, val, isSel, hasFocus, r, c);
                l.setHorizontalAlignment(SwingConstants.RIGHT);
                l.setFont(UITheme.FONT_SUB);
                l.setForeground(UITheme.COLOR_XOA);
                if (val instanceof Number) l.setText(df.format(val));
                return l;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        add(scrollPane, BorderLayout.CENTER);

        // Footer chứa nút Xóa bo tròn đỏ
        JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 14));
        pSouth.setBackground(Color.WHITE);
        pSouth.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, UITheme.CARD_BORDER));

        btnXoa = UITheme.createPrimaryButton("Xác Nhận Xóa Hóa Đơn", UITheme.COLOR_XOA);
        btnXoa.addActionListener(e -> xuLyXoa());

        pSouth.add(new JLabel("💡 Chọn một dòng trên bảng để kích hoạt xóa:"));
        pSouth.add(btnXoa);
        add(pSouth, BorderLayout.SOUTH);
    }

    private void napDuLieu() {
        model.setRowCount(0);
        InDSHDControl inControl = new InDSHDControl();
        ResponseData res = inControl.execute(new RequestData());
        if (res != null && res.getListHoaDon() != null) {
            for (HoaDon hd : res.getListHoaDon()) {
                int gio = (hd instanceof HoaDonTheoGio) ? (int) hd.getSoLuong() : 0;
                int ngay = !(hd instanceof HoaDonTheoGio) ? (int) hd.getSoLuong() : 0;
                model.addRow(new Object[]{
                        hd.getMaHD(),
                        hd.getTenKH(),
                        hd.getMaPhong(),
                        hd.getDonGia(),
                        hd.getLoaiHD(),
                        gio > 0 ? gio + " Giờ" : "-",
                        ngay > 0 ? ngay + " Ngày" : "-",
                        hd.tinhTien()
                });
            }
        }
    }

    private void xuLyXoa() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng click chọn một hóa đơn cần xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int maHD = (int) table.getValueAt(row, 0);
        String loai = (String) table.getValueAt(row, 4);

        int opt = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn xóa vĩnh viễn hóa đơn Mã [" + maHD + "]?",
                "Xác Nhận Xóa", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (opt == JOptionPane.YES_OPTION) {
            XoaHDControl control = loai.toLowerCase().contains("giờ") ? new XoaHDControlTheoGio() : new XoaHDControlTheoNgay();
            ResponseData res = control.execute(new RequestData(maHD));
            ResultDialogGUI.show(this, res);
            napDuLieu();
        }
    }
}