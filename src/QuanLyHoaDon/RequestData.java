package QuanLyHoaDon;

/**
 * Data Transfer Object đóng gói dữ liệu gửi từ Boundary sang Control
 */
public class RequestData {
    private int maHD;
    private String ngayHD;
    private String tenKH;
    private String maPhong;
    private double donGia;
    private String loaiHD; // "HD Gio" hoặc "HD Ngay"
    private int soLuong;   // soGioThue hoặc soNgayThue

    public RequestData() {}

    public RequestData(int maHD) {
        this.maHD = maHD;
    }

    public RequestData(int maHD, String ngayHD, String tenKH, String maPhong, double donGia, String loaiHD, int soLuong) {
        this.maHD = maHD;
        this.ngayHD = ngayHD;
        this.tenKH = tenKH;
        this.maPhong = maPhong;
        this.donGia = donGia;
        this.loaiHD = loaiHD;
        this.soLuong = soLuong;
    }

    public int getMaHD() { return maHD; }
    public void setMaHD(int maHD) { this.maHD = maHD; }

    public String getNgayHD() { return ngayHD; }
    public void setNgayHD(String ngayHD) { this.ngayHD = ngayHD; }

    public String getTenKH() { return tenKH; }
    public void setTenKH(String tenKH) { this.tenKH = tenKH; }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    public String getLoaiHD() { return loaiHD; }
    public void setLoaiHD(String loaiHD) { this.loaiHD = loaiHD; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
}