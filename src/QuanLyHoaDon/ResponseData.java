package QuanLyHoaDon;

import java.util.List;


public class ResponseData {
    private boolean success;
    private String message;
    private HoaDon hoaDon;
    private List<HoaDon> listHoaDon;
    private int countResult;
    private double avgResult;

    public ResponseData() {}

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public HoaDon getHoaDon() { return hoaDon; }
    public void setHoaDon(HoaDon hoaDon) { this.hoaDon = hoaDon; }

    public List<HoaDon> getListHoaDon() { return listHoaDon; }
    public void setListHoaDon(List<HoaDon> listHoaDon) { this.listHoaDon = listHoaDon; }

    public int getCountResult() { return countResult; }
    public void setCountResult(int countResult) { this.countResult = countResult; }

    public double getAvgResult() { return avgResult; }
    public void setAvgResult(double avgResult) { this.avgResult = avgResult; }
}