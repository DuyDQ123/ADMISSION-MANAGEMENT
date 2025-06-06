package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GiamThiService {
    private List<GiamThi> danhSachGiamThi = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    
    // Thêm giám thị
    public void themGiamThi() {
        System.out.println("\n===== THÊM GIÁM THỊ =====");
        
        System.out.print("Nhập họ tên: ");
        String hoTen = sc.nextLine();
        
        // Nhập giới tính
        Gender gioiTinh = null;
        while (gioiTinh == null) {
            System.out.print("Nhập giới tính (MALE, FEMALE, OTHER): ");
            String gioiTinhInput = sc.nextLine().toUpperCase();
            try {
                gioiTinh = Gender.valueOf(gioiTinhInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Giới tính không hợp lệ. Vui lòng nhập lại.");
            }
        }
        
        // Nhập năm sinh
        int namSinh = 0;
        boolean validNamSinh = false;
        while (!validNamSinh) {
            try {
                System.out.print("Nhập năm sinh: ");
                namSinh = Integer.parseInt(sc.nextLine());
                if (namSinh < 1940 || namSinh > 2000) {
                    System.out.println("Năm sinh không hợp lệ. Vui lòng nhập năm sinh từ 1940-2000.");
                    continue;
                }
                validNamSinh = true;
            } catch (NumberFormatException e) {
                System.out.println("Năm sinh phải là số. Vui lòng nhập lại.");
            }
        }
        
        System.out.print("Nhập quê quán: ");
        String queQuan = sc.nextLine();
        
        // Tạo và thêm giám thị mới
        GiamThi giamThi = new GiamThi(hoTen, gioiTinh, namSinh, queQuan);
        danhSachGiamThi.add(giamThi);
        
        System.out.println("Thêm giám thị thành công! Mã giám thị: " + giamThi.getMaGiamThi());
    }
    
    // Cập nhật giám thị
    public void capNhatGiamThi() {
        System.out.println("\n===== CẬP NHẬT GIÁM THỊ =====");
        
        if (danhSachGiamThi.isEmpty()) {
            System.out.println("Danh sách giám thị trống!");
            return;
        }
        
        System.out.print("Nhập mã giám thị cần cập nhật: ");
        try {
            int maGiamThi = Integer.parseInt(sc.nextLine());
            
            // Tìm giám thị theo mã
            GiamThi giamThiCapNhat = null;
            for (GiamThi gt : danhSachGiamThi) {
                if (gt.getMaGiamThi() == maGiamThi) {
                    giamThiCapNhat = gt;
                    break;
                }
            }
            
            if (giamThiCapNhat == null) {
                System.out.println("Không tìm thấy giám thị với mã: " + maGiamThi);
                return;
            }
            
            // Hiển thị thông tin hiện tại
            System.out.println("Thông tin hiện tại của giám thị:");
            System.out.println(giamThiCapNhat);
            
            // Cập nhật thông tin
            System.out.println("\nNhập thông tin mới (để trống nếu không muốn thay đổi):");
            
            System.out.print("Họ tên mới: ");
            String hoTenMoi = sc.nextLine();
            if (!hoTenMoi.trim().isEmpty()) {
                giamThiCapNhat.setHoTen(hoTenMoi);
            }
            
            // Cập nhật giới tính
            System.out.print("Giới tính mới (MALE, FEMALE, OTHER - để trống nếu không thay đổi): ");
            String gioiTinhInput = sc.nextLine().toUpperCase();
            if (!gioiTinhInput.trim().isEmpty()) {
                try {
                    Gender gioiTinhMoi = Gender.valueOf(gioiTinhInput);
                    giamThiCapNhat.setGioiTinh(gioiTinhMoi);
                } catch (IllegalArgumentException e) {
                    System.out.println("Giới tính không hợp lệ. Giữ nguyên giới tính cũ.");
                }
            }
            
            // Cập nhật năm sinh
            System.out.print("Năm sinh mới (để trống nếu không thay đổi): ");
            String namSinhInput = sc.nextLine();
            if (!namSinhInput.trim().isEmpty()) {
                try {
                    int namSinhMoi = Integer.parseInt(namSinhInput);
                    if (namSinhMoi < 1940 || namSinhMoi > 2000) {
                        System.out.println("Năm sinh không hợp lệ. Giữ nguyên năm sinh cũ.");
                    } else {
                        giamThiCapNhat.setNamSinh(namSinhMoi);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Năm sinh phải là số. Giữ nguyên năm sinh cũ.");
                }
            }
            
            // Cập nhật quê quán
            System.out.print("Quê quán mới: ");
            String queQuanMoi = sc.nextLine();
            if (!queQuanMoi.trim().isEmpty()) {
                giamThiCapNhat.setQueQuan(queQuanMoi);
            }
            
            System.out.println("Cập nhật giám thị thành công!");
            
        } catch (NumberFormatException e) {
            System.out.println("Mã giám thị phải là số nguyên!");
        }
    }
    
    // Xóa giám thị
    public void xoaGiamThi() {
        System.out.println("\n===== XÓA GIÁM THỊ =====");
        
        if (danhSachGiamThi.isEmpty()) {
            System.out.println("Danh sách giám thị trống!");
            return;
        }
        
        System.out.print("Nhập mã giám thị cần xóa: ");
        try {
            int maGiamThi = Integer.parseInt(sc.nextLine());
            
            boolean removed = danhSachGiamThi.removeIf(gt -> gt.getMaGiamThi() == maGiamThi);
            
            if (removed) {
                System.out.println("Xóa giám thị thành công!");
            } else {
                System.out.println("Không tìm thấy giám thị với mã: " + maGiamThi);
            }
        } catch (NumberFormatException e) {
            System.out.println("Mã giám thị phải là số nguyên!");
        }
    }
    
    // Hiển thị danh sách giám thị
    public void hienThiDanhSachGiamThi() {
        if (danhSachGiamThi.isEmpty()) {
            System.out.println("Danh sách giám thị trống!");
        } else {
            System.out.println("\n===== DANH SÁCH GIÁM THỊ =====");
            for (GiamThi gt : danhSachGiamThi) {
                System.out.println(gt);
            }
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