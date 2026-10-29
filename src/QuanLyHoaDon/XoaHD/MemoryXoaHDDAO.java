package QuanLyHoaDon.XoaHD;

import QuanLyHoaDon.MemoryHDDatabase;

public class MemoryXoaHDDAO implements XoaHDDAO {
    private final MemoryHDDatabase db = MemoryHDDatabase.getInstance();

    @Override
    public boolean delete(int maHD) {
        return db.delete(maHD);
    }
}