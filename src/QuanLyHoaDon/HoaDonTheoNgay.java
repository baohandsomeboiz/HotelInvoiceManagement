package QuanLyHoaDon;

/**
 * Hóa đơn thuê theo ngày
 */
public class HoaDonTheoNgay extends HoaDon {
    private int soNgayThue;

    public HoaDonTheoNgay() {
        super();
    }

    public HoaDonTheoNgay(int maHD, String ngayHD, String tenKH, String maPhong, double donGia, int soNgayThue) {
        super(maHD, ngayHD, tenKH, maPhong, donGia);
        this.soNgayThue = soNgayThue;
    }

    public int getSoNgayThue() {
        return soNgayThue;
    }

    public void setSoNgayThue(int soNgayThue) {
        this.soNgayThue = soNgayThue;
    }

    @Override
    public double tinhTien() {
        // Business Rule: nếu soNgayThue > 7 ngày thì giảm 20% (nhân 0.8)
        if (soNgayThue > 7) {
            return soNgayThue * donGia * 0.8;
        }
        return soNgayThue * donGia;
    }

    @Override
    public String getLoaiHD() {
        return "Theo ngày";
    }

    @Override
    public double getSoLuong() {
        return soNgayThue;
    }
}