package model;

import java.util.List;
import java.util.Scanner;

public class ThongKeTuyenSinhService {
    private ThiSinhService thiSinhService;
    private Scanner sc = new Scanner(System.in);
    
    public ThongKeTuyenSinhService(ThiSinhService thiSinhService, NguyenVongService nguyenVongService) {
        this.thiSinhService = thiSinhService;
       
    }
    
    public void hienThiDanhSachTrungTuyen() {
        System.out.println("\n===== DANH SÁCH THÍ SINH TRÚNG TUYỂN =====");
        
        // Sửa dòng này - gọi phương thức static thông qua tên lớp
        List<NguyenVong> danhSachNguyenVong = NguyenVongService.getDanhSachNguyenVong();
        if (danhSachNguyenVong.isEmpty()) {
            System.out.println("Danh sách nguyện vọng trống!");
            return;
        }
        
        System.out.print("Nhập mã nguyện vọng để xem danh sách trúng tuyển: ");
        try {
            int maNguyenVong = Integer.parseInt(sc.nextLine());
            
            // Tìm nguyện vọng theo mã
            NguyenVong nguyenVong = null;
            for (NguyenVong nv : danhSachNguyenVong) {
                if (nv.getMaNguyenVong() == maNguyenVong) {
                    nguyenVong = nv;
                    break;
                }
            }
            
            if (nguyenVong == null) {
                System.out.println("Không tìm thấy nguyện vọng với mã: " + maNguyenVong);
                return;
            }
            
            // Hiển thị thông tin nguyện vọng
            System.out.println("\nThông tin nguyện vọng:");
            System.out.println("- Mã ngành: " + nguyenVong.getMaNganh());
            System.out.println("- Tên ngành: " + nguyenVong.getTenNganh());
            System.out.println("- Mã trường: " + nguyenVong.getMaTruong());
            System.out.println("- Khối xét tuyển: " + nguyenVong.getKhoiXetTuyen());
            System.out.println("- Điểm chuẩn: " + nguyenVong.getDiemDatDieuKien());
            
            // Tìm tất cả thí sinh đăng ký nguyện vọng này và đạt điểm chuẩn
            System.out.println("\nDanh sách thí sinh trúng tuyển:");
            
            List<ThiSinh> danhSachThiSinh = thiSinhService.getDanhSachThiSinh();
            
            boolean coThiSinhTrungTuyen = false;
            System.out.printf("%-15s %-30s %-10s %-15s %-15s%n", 
                             "Số báo danh", "Họ và tên", "Giới tính", "Tổng điểm", "Kết quả");
            System.out.println("-".repeat(85));
            
            for (ThiSinh ts : danhSachThiSinh) {
                for (NguyenVong nv : ts.getDanhSachNguyenVong()) {
                    if (nv.getMaNguyenVong() == maNguyenVong) {
                        double tongDiem = ts.getDiemThi() + ts.getDiemUuTien();
                        boolean trungTuyen = tongDiem >= nguyenVong.getDiemDatDieuKien();
                        
                        coThiSinhTrungTuyen = coThiSinhTrungTuyen || trungTuyen;
                        
                        System.out.printf("%-15s %-30s %-10s %-15.2f %-15s%n", 
                                         ts.getSoBaoDanh(),
                                         ts.getHoTen(),
                                         ts.getGioiTinh(),
                                         tongDiem,
                                         trungTuyen ? "Trúng tuyển" : "Không trúng tuyển");
                        break;
                    }
                }
            }
            
            if (!coThiSinhTrungTuyen) {
                System.out.println("Không có thí sinh nào trúng tuyển nguyện vọng này.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Mã nguyện vọng phải là số nguyên!");
        }
        
        // Đoạn code cho người dùng nhấn 0 để quay lại menu
        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nNhấn 0 để quay lại menu chính...");
            try {
                String input = sc.nextLine();
                if (input.equals("0")) {
                    validInput = true;
                    System.out.println("Đang quay lại menu chính...");
                } else {
                    System.out.println("Vui lòng nhấn 0 để quay lại menu chính.");
                }
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi. Vui lòng thử lại.");
            }
        }
    }
}
