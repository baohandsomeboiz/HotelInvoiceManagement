package QuanLyHoaDon.ThemHD;

import QuanLyHoaDon.HoaDonTheoNgay;
import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;

public class ThemHDControlNgay extends ThemHDControl {
    @Override
    public ResponseData execute(RequestData reqData) {
        ResponseData res = new ResponseData();
        HoaDonTheoNgay hd = new HoaDonTheoNgay(
            reqData.getMaHD(),
            reqData.getNgayHD(),
            reqData.getTenKH(),
            reqData.getMaPhong(),
            reqData.getDonGia(),
            reqData.getSoLuong()
        );
        boolean ok = dao.insert(hd);
        res.setSuccess(ok);
        res.setMessage(ok ? "Đã thêm HD thành công!" : "Thêm thất bại (Mã HĐ có thể đã tồn tại)!");
        return res;
    }
}