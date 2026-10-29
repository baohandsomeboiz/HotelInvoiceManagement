package QuanLyHoaDon.TinhTBDoanhThu;

import QuanLyHoaDon.RequestData;
import QuanLyHoaDon.ResponseData;

public abstract class TinhTBDoanhThuControl {
    protected TinhTBDoanhThuDAO dao = new TinhTBDoanhThuDAO();

    public abstract ResponseData execute(RequestData reqData);
}