package QuanLyHoaDon.TinhTBDoanhThu;

import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;

public class TinhTBDoanhHDThuGioControl extends TinhTBDoanhThuControl {
    @Override
    public ResponseData execute(RequestData reqData) {
        ResponseData res = new ResponseData();
        double tb = dao.tinhTB("gio");
        res.setAvgResult(tb);
        res.setSuccess(true);
        return res;
    }
}