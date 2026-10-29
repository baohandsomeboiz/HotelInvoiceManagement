package QuanLyHoaDon.TinhTongSLPhong;

import QuanLyHoaDon.MemoryHDDatabase;

public class TinhTongSLHDDAO {
    private final MemoryHDDatabase db = MemoryHDDatabase.getInstance();

    public int tinhTongSL(String loaiHD) {
        return db.tinhTongSL(loaiHD);
    }
}