package QuanLyHoaDon;

import java.util.*;

/**
 * Cơ sở dữ liệu lưu trữ đối tượng HoaDon trên bộ nhớ RAM (Singleton / Shared Memory Database)
 */
public class MemoryHDDatabase {
    private static MemoryHDDatabase instance;
    private final Map<Integer, HoaDon> db;

    private MemoryHDDatabase() {
        db = new LinkedHashMap<>();
        khoiTaoDuLieuMau();
    }

    public static synchronized MemoryHDDatabase getInstance() {
        if (instance == null) {
            instance = new MemoryHDDatabase();
        }
        return instance;
    }

    private void khoiTaoDuLieuMau() {
        insert(new HoaDonTheoGio(1, "2026-11-20", "A", "101", 50000, 4));
        insert(new HoaDonTheoNgay(2, "2026-11-21", "B", "102", 120000, 4));
        insert(new HoaDonTheoGio(3, "2026-11-22", "C", "103", 500000, 2));
        insert(new HoaDonTheoGio(4, "2026-11-23", "D", "104", 50000, 6));
        insert(new HoaDonTheoNgay(5, "2026-11-24", "E", "105", 1200000, 3));
    }

    public boolean insert(HoaDon hd) {
        if (hd == null || db.containsKey(hd.getMaHD())) {
            return false;
        }
        db.put(hd.getMaHD(), hd);
        return true;
    }

    public List<HoaDon> getAll() {
        return new ArrayList<>(db.values());
    }

    public HoaDon findHoaDon(int maHD) {
        return db.get(maHD);
    }

    public boolean repair(HoaDon hd) {
        if (hd != null && db.containsKey(hd.getMaHD())) {
            db.put(hd.getMaHD(), hd);
            return true;
        }
        return false;
    }

    public boolean delete(int maHD) {
        return db.remove(maHD) != null;
    }

    public int tinhTongSL(String loaiHD) {
        int count = 0;
        for (HoaDon hd : db.values()) {
            if (loaiHD.equalsIgnoreCase("gio") && hd instanceof HoaDonTheoGio) {
                count++;
            } else if (loaiHD.equalsIgnoreCase("ngay") && hd instanceof HoaDonTheoNgay) {
                count++;
            }
        }
        return count;
    }

    public double tinhTBDoanhThu(String loaiHD) {
        double tongTien = 0;
        int count = 0;
        for (HoaDon hd : db.values()) {
            if (loaiHD.equalsIgnoreCase("gio") && hd instanceof HoaDonTheoGio) {
                tongTien += hd.tinhTien();
                count++;
            } else if (loaiHD.equalsIgnoreCase("ngay") && hd instanceof HoaDonTheoNgay) {
                tongTien += hd.tinhTien();
                count++;
            }
        }
        return count == 0 ? 0 : (tongTien / count);
    }
}