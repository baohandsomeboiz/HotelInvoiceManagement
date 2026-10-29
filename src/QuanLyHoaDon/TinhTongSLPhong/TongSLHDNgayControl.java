package QuanLyHoaDon.TinhTongSLPhong;

import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;

public class TongSLHDNgayControl extends TinhTongSLHDControl {
    @Override
    public ResponseData execute(RequestData reqData) {
        ResponseData res = new ResponseData();
        int count = dao.tinhTongSL("ngay");
        res.setCountResult(count);
        res.setSuccess(true);
        return res;
    }
}