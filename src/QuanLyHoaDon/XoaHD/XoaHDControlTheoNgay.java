package QuanLyHoaDon.XoaHD;

import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;

public class XoaHDControlTheoNgay extends XoaHDControl {
    @Override
    public ResponseData execute(RequestData reqData) {
        ResponseData res = new ResponseData();
        boolean ok = dao.delete(reqData.getMaHD());
        res.setSuccess(ok);
        res.setMessage(ok ? "Xóa hóa đơn thành công!" : "Xóa hóa đơn thất bại!");
        return res;
    }
}