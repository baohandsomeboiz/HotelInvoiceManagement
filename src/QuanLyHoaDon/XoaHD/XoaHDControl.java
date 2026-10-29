package QuanLyHoaDon.XoaHD;

import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;

public abstract class XoaHDControl {
    protected XoaHDDAO dao = new MemoryXoaHDDAO();

    public abstract ResponseData execute(RequestData reqData);
}