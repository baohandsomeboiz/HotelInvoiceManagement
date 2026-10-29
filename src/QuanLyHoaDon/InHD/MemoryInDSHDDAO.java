package QuanLyHoaDon.InHD;

import QuanLyHoaDon.HoaDon;
import QuanLyHoaDon.MemoryHDDatabase;
import java.util.List;

public class MemoryInDSHDDAO implements InDSHDDAO {
    private final MemoryHDDatabase db = MemoryHDDatabase.getInstance();

    @Override
    public List<HoaDon> getAllHD() {
        return db.getAll();
    }
}