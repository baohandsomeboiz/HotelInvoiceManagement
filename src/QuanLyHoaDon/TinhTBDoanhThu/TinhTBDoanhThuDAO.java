package QuanLyHoaDon.TinhTBDoanhThu;

import QuanLyHoaDon.MemoryHDDatabase;

public class TinhTBDoanhThuDAO {
    private final MemoryHDDatabase db = MemoryHDDatabase.getInstance();

    public double tinhTB(String loaiHD) {
        return db.tinhTBDoanhThu(loaiHD);
    }
}