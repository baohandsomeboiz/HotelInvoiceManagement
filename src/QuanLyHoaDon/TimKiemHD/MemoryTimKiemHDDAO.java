package QuanLyHoaDon.TimKiemHD;

import QuanLyHoaDon.HoaDon;
import QuanLyHoaDon.MemoryHDDatabase;

public class MemoryTimKiemHDDAO implements TimKiemHDDAO {
    private final MemoryHDDatabase db = MemoryHDDatabase.getInstance();

    @Override
    public HoaDon findHoaDon(int maHD) {
        return db.findHoaDon(maHD);
    }
}