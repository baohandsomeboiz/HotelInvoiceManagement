package QuanLyHoaDon.SuaHD;

import QuanLyHoaDon.HoaDon;

public interface SuaHDDAO {
    boolean repair(HoaDon hd);
    HoaDon getHoaDon(int maHD);
}