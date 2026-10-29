package QuanLyHoaDon.SuaHD;

import QuanLyHoaDon.HoaDon;
import QuanLyHoaDon.MemoryHDDatabase;

public class MemorySuaHDDAO implements SuaHDDAO {
    private final MemoryHDDatabase db = MemoryHDDatabase.getInstance();

    @Override
    public boolean repair(HoaDon hd) {
        return db.repair(hd);
    }

    @Override
    public HoaDon getHoaDon(int maHD) {
        return db.findHoaDon(maHD);
    }
}