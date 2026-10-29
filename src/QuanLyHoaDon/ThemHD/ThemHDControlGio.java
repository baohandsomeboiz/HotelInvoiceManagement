package QuanLyHoaDon.ThemHD;

import QuanLyHoaDon.HoaDonTheoGio;
import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;

public class ThemHDControlGio extends ThemHDControl {
    @Override
    public ResponseData execute(RequestData reqData) {
        ResponseData res = new ResponseData();
        HoaDonTheoGio hd = new HoaDonTheoGio(
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