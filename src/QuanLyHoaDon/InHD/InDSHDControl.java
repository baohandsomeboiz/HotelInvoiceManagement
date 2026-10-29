package QuanLyHoaDon.InHD;

import QuanLyHoaDon.HoaDon;
import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;
import java.util.List;

public class InDSHDControl {
    private final InDSHDDAO dao = new MemoryInDSHDDAO();

    public ResponseData execute(RequestData reqData) {
        ResponseData res = new ResponseData();
        List<HoaDon> list = dao.getAllHD();
        res.setListHoaDon(list);
        res.setSuccess(true);
        return res;
    }
}