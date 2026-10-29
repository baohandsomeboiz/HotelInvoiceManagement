package QuanLyHoaDon.TimKiemHD;

import QuanLyHoaDon.HoaDon;
import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;

public class TimKiemHDControl {
    private final TimKiemHDDAO dao = new MemoryTimKiemHDDAO();

    public ResponseData execute(RequestData reqData) {
        ResponseData res = new ResponseData();
        HoaDon hd = dao.findHoaDon(reqData.getMaHD());
        if (hd != null) {
            res.setSuccess(true);
            res.setHoaDon(hd);
        } else {
            res.setSuccess(false);
            res.setMessage("Không tìm thấy hóa đơn có mã: " + reqData.getMaHD());
        }
        return res;
    }
}