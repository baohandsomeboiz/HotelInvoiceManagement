package QuanLyHoaDon.ThemHD;

import QuanLyHoaDon.HoaDon;
import QuanLyHoaDon.MemoryHDDatabase;

public class MemoryThemHDDAO implements ThemHDDAO {
    private final MemoryHDDatabase db = MemoryHDDatabase.getInstance();

    @Override
    public boolean insert(HoaDon hd) {
        return db.insert(hd);
    }
}