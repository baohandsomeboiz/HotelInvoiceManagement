package QuanLyHoaDon.TinhTongSLPhong;

import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;

public abstract class TinhTongSLHDControl {
    protected TinhTongSLHDDAO dao = new TinhTongSLHDDAO();

    public abstract ResponseData execute(RequestData reqData);
}