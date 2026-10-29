package QuanLyHoaDon.TinhTongSLPhong;

import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;

public class TongSLHDGioControl extends TinhTongSLHDControl {
    @Override
    public ResponseData execute(RequestData reqData) {
        ResponseData res = new ResponseData();
        int count = dao.tinhTongSL("gio");
        res.setCountResult(count);
        res.setSuccess(true);
        return res;
    }
}