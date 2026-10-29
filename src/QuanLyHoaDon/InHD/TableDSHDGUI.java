package QuanLyHoaDon.InHD;

import QuanLyHoaDon.HoaDon;
import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;
import QuanLyHoaDon.SuaHD.FormSuaHDGUI;
import QuanLyHoaDon.UITheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class TableDSHDGUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public TableDSHDGUI() {
        super("Danh sách Hóa Đơn Chi Tiết");
        khoiTaoThanhPhan();
        napDuLieu();
    }

    private void khoiTaoThanhPhan() {
        setSize(950, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.setBackground(Color.WHITE);
        pnlTop.setBorder(new EmptyBorder(14, 20, 14, 20));

        JLabel lblHeader = new JLabel("DANH SÁCH TOÀN BỘ HÓA ĐƠN TRONG HỆ THỐNG");
        lblHeader.setFont(UITheme.FONT_TITLE);
        lblHeader.setForeground(UITheme.TEXT_DARK);

        JLabel lblNote = new JLabel("• Nhấp chuột phải vào dòng bất kỳ để sửa hóa đơn");
        lblNote.setFont(UITheme.FONT_SMALL);
        lblNote.setForeground(UITheme.TEXT_MUTED);

        pnlTop.add(lblHeader, BorderLayout.WEST);
        pnlTop.add(lblNote, BorderLayout.EAST);
        add(pnlTop, BorderLayout.NORTH);

        String[] headers = {"Mã HĐ", "Ngày HĐ", "Tên Khách Hàng", "Phòng", "Đơn Giá", "Loại HĐ", "Thời Lượng", "Thành Tiền"};
        model = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(36);
        table.setFont(UITheme.FONT_BODY);
        table.setForeground(UITheme.TEXT_DARK);
        table.setShowGrid(true);
        table.setGridColor(UITheme.CARD_BORDER);

        // Header sắc nét: Nền Slate đậm, Chữ Trắng nổi bật
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
                l.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(51, 65, 85)));
                return l;
            }
        });

        // Căn giữa các cột thông tin
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setForeground(UITheme.TEXT_DARK);

        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        // Hiển thị loại HĐ có màu riêng biệt
        table.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object val, boolean isSel, boolean hasFocus, int r, int c) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(t, val, isSel, hasFocus, r, c);
                l.setHorizontalAlignment(SwingConstants.CENTER);
                l.setFont(UITheme.FONT_SUB);
                if (val != null && val.toString().toLowerCase().contains("giờ")) {
                    l.setForeground(UITheme.PRIMARY);
                } else {
                    l.setForeground(UITheme.ACCENT_PURPLE);
                }
                return l;
            }
        });

        // Định dạng tiền tệ căn phải
        DecimalFormat df = new DecimalFormat("#,##0 VNĐ");
        DefaultTableCellRenderer rightMoneyRenderer = new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof Number) {
                    setText(df.format(value));
                } else {
                    super.setValue(value);
                }
            }
        };
        rightMoneyRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        rightMoneyRenderer.setForeground(UITheme.TEXT_DARK);
        table.getColumnModel().getColumn(4).setCellRenderer(rightMoneyRenderer);

        table.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object val, boolean isSel, boolean hasFocus, int r, int c) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(t, val, isSel, hasFocus, r, c);
                l.setHorizontalAlignment(SwingConstants.RIGHT);
                l.setFont(UITheme.FONT_SUB);
                l.setForeground(UITheme.PRIMARY);
                if (val instanceof Number) {
                    l.setText(df.format(val));
                }
                return l;
            }
        });

        // Menu click chuột phải để Sửa
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem itemSua = new JMenuItem("Sửa thông tin hóa đơn này");
        itemSua.setFont(UITheme.FONT_BODY);
        itemSua.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int maHD = (int) table.getValueAt(row, 0);
                new FormSuaHDGUI(maHD, this::napDuLieu).setVisible(true);
            }
        });
        popupMenu.add(itemSua);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < table.getRowCount()) {
                        table.setRowSelectionInterval(row, row);
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void napDuLieu() {
        InDSHDControl control = new InDSHDControl();
        ResponseData res = control.execute(new RequestData());
        show(res);
    }

    public void show(ResponseData resData) {
        model.setRowCount(0);
        if (resData != null && resData.getListHoaDon() != null) {
            for (HoaDon hd : resData.getListHoaDon()) {
                String thoiLuong = (int) hd.getSoLuong() + (hd.getLoaiHD().toLowerCase().contains("giờ") ? " Giờ" : " Ngày");
                model.addRow(new Object[]{
                        hd.getMaHD(),
                        hd.getNgayHD(),
                        hd.getTenKH(),
                        hd.getMaPhong(),
                        hd.getDonGia(),
                        hd.getLoaiHD(),
                        thoiLuong,
                        hd.tinhTien()
                });
            }
        }
    }
}