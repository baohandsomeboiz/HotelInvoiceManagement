package QuanLyHoaDon;

/**
 * Lớp trừu tượng đại diện cho Hóa đơn khách sạn cơ sở (Entity)
 */
public abstract class HoaDon {
    protected int maHD;
    protected String ngayHD;
    protected String tenKH;
    protected String maPhong;
    protected double donGia;

    public HoaDon() {
    }

    public HoaDon(int maHD, String ngayHD, String tenKH, String maPhong, double donGia) {
        this.maHD = maHD;
        this.ngayHD = ngayHD;
        this.tenKH = tenKH;
        this.maPhong = maPhong;
        this.donGia = donGia;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getNgayHD() {
        return ngayHD;
    }

    public void setNgayHD(String ngayHD) {
        this.ngayHD = ngayHD;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    // Phương thức nghiệp vụ tính tiền được ghi đè ở các lớp con
    public abstract double tinhTien();

    public abstract String getLoaiHD();

    public abstract double getSoLuong();
}