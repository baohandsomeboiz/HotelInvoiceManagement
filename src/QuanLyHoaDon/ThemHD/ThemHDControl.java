package QuanLyHoaDon.ThemHD;

import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;

public abstract class ThemHDControl {
    protected ThemHDDAO dao = new MemoryThemHDDAO();

    public abstract ResponseData execute(RequestData reqData);
}