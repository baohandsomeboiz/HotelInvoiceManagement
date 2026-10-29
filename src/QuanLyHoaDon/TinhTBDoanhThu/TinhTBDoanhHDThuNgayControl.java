package QuanLyHoaDon.TinhTBDoanhThu;

import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;

public class TinhTBDoanhHDThuNgayControl extends TinhTBDoanhThuControl {
    @Override
    public ResponseData execute(RequestData reqData) {
        ResponseData res = new ResponseData();
        double tb = dao.tinhTB("ngay");
        res.setAvgResult(tb);
        res.setSuccess(true);
        return res;
    }
}