# HỆ THỐNG QUẢN LÝ HÓA ĐƠN KHÁCH SẠN (HOTEL INVOICE MANAGEMENT)

Đồ án môn học: **Lập Trình Hướng Đối Tượng (OOP)**  
Trường: **Đại học Gia Định (GDU)** - Khoa Công Nghệ Thông Tin  
- **Sinh viên thực hiện:** NGUYỄN NHỰT KHANG
- **Giảng viên hướng dẫn:** ThS. LÊ HUỲNH PHƯỚC  

---

##Giới thiệu 
Ứng dụng desktop xây dựng bằng Java Swing quản lý hóa đơn thuê phòng khách sạn theo kiến trúc chuẩn **BCE (Boundary - Control - Entity)** kết hợp mô hình **DTO** và **DAO**.

## Công nghệ sử dụng
- **Ngôn ngữ:** Java (JDK 17 trở lên)
- **Giao diện:** Java Swing & AWT (Classic Grand Hotel / Modern UI)
- **Cơ chế lưu trữ:** In-Memory Database

## 7 Chức năng nghiệp vụ chính
1. **Lập hóa đơn mới:** Thuê phòng theo giờ / ngày, tự động tính giá theo quy tắc nghiệp vụ.
2. **In danh mục hóa đơn:** Hiển thị danh sách, click chuột phải sửa thông tin.
3. **Tra cứu & Tìm kiếm:** Tra cứu chi tiết theo mã hóa đơn.
4. **Hiệu chỉnh thông tin:** Cập nhật thông tin phòng, khóa mã HĐ cố định.
5. **Hủy bỏ hóa đơn:** Xóa hóa đơn với hộp thoại xác nhận an toàn.
6. **Thống kê số lượng:** Đếm tổng lượt thuê theo từng phân loại (Giờ/Ngày).
7. **Báo cáo doanh thu:** Tính doanh thu trung bình theo loại phòng.

## 🚀 Hướng dẫn khởi chạy
1. Tải dự án về máy và mở bằng VS Code.
2. Cài đặt tiện ích **Extension Pack for Java**.
3. Mở file \src/QuanLyHoaDon/MainQLHD.java\ và nhấn **Run (▷)**.
