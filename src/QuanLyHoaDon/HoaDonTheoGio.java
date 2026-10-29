package QuanLyHoaDon;

/**
 * Hóa đơn thuê theo giờ
 */
public class HoaDonTheoGio extends HoaDon {
    private int soGioThue;

    public HoaDonTheoGio() {
        super();
    }

    public HoaDonTheoGio(int maHD, String ngayHD, String tenKH, String maPhong, double donGia, int soGioThue) {
        super(maHD, ngayHD, tenKH, maPhong, donGia);
        this.soGioThue = soGioThue;
    }

    public int getSoGioThue() {
        return soGioThue;
    }

    public void setSoGioThue(int soGioThue) {
        this.soGioThue = soGioThue;
    }

    @Override
    public double tinhTien() {
        // Business Rule: nếu 24 < soGioThue < 30 thì tính 24 giờ
        int soGioTinhTien = soGioThue;
        if (soGioThue > 24 && soGioThue < 30) {
            soGioTinhTien = 24;
        }
        return soGioTinhTien * donGia;
    }

    @Override
    public String getLoaiHD() {
        return "Theo giờ";
    }

    @Override
    public double getSoLuong() {
        return soGioThue;
    }
}