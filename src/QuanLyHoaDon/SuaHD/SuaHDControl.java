package QuanLyHoaDon.SuaHD;

import QuanLyHoaDon.HoaDon;
import QuanLyHoaDon.HoaDonTheoGio;
import QuanLyHoaDon.HoaDonTheoNgay;
import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;

public class SuaHDControl {
    private final SuaHDDAO dao = new MemorySuaHDDAO();

    public ResponseData execute(RequestData reqData) {
        ResponseData res = new ResponseData();
        HoaDon hdMoi;
        if (reqData.getLoaiHD().contains("Giờ") || reqData.getLoaiHD().equalsIgnoreCase("gio")) {
            hdMoi = new HoaDonTheoGio(
                reqData.getMaHD(),
                reqData.getNgayHD(),
                reqData.getTenKH(),
                reqData.getMaPhong(),
                reqData.getDonGia(),
                reqData.getSoLuong()
            );
        } else {
            hdMoi = new HoaDonTheoNgay(
                reqData.getMaHD(),
                reqData.getNgayHD(),
                reqData.getTenKH(),
                reqData.getMaPhong(),
                reqData.getDonGia(),
                reqData.getSoLuong()
            );
        }

        boolean ok = dao.repair(hdMoi);
        res.setSuccess(ok);
        res.setMessage(ok ? "Cập nhật hóa đơn thành công!" : "Cập nhật hóa đơn thất bại!");
        return res;
    }
}