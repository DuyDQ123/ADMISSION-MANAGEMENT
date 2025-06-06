package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Lớp phụ trợ để lưu thông tin của thí sinh trúng tuyển
class ThiSinhTrungTuyen implements Comparable<ThiSinhTrungTuyen> {
    private ThiSinh thiSinh;
    private NguyenVong nguyenVong;
    private double tongDiem;
    private boolean trungTuyen;
    
    public ThiSinhTrungTuyen(ThiSinh thiSinh, NguyenVong nguyenVong) {
        this.thiSinh = thiSinh;
        this.nguyenVong = nguyenVong;
        this.tongDiem = thiSinh.getDiemThi() + thiSinh.getDiemUuTien();
        this.trungTuyen = this.tongDiem >= nguyenVong.getDiemDatDieuKien();
    }
    
    // Dùng để sắp xếp theo điểm thi giảm dần
    @Override
    public int compareTo(ThiSinhTrungTuyen o) {
        // Sắp xếp giảm dần theo điểm
        return Double.compare(o.tongDiem, this.tongDiem);
    }
    
    public ThiSinh getThiSinh() { return thiSinh; }
    public NguyenVong getNguyenVong() { return nguyenVong; }
    public double getTongDiem() { return tongDiem; }
    public boolean isTrungTuyen() { return trungTuyen; }
}

public class ThongKeTuyenSinhService {
    private ThiSinhService thiSinhService;
    private Scanner sc = new Scanner(System.in);
    
    public ThongKeTuyenSinhService(ThiSinhService thiSinhService) {
        this.thiSinhService = thiSinhService;
       
    }
    
    public void hienThiDanhSachTrungTuyen() {
        System.out.println("\n===== DANH SÁCH THÍ SINH TRÚNG TUYỂN =====");
        
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
            
            // Tạo danh sách thí sinh để sắp xếp
            List<ThiSinhTrungTuyen> danhSachThiSinhTrungTuyen = new ArrayList<>();
            List<ThiSinh> danhSachThiSinh = thiSinhService.getDanhSachThiSinh();
            
            for (ThiSinh ts : danhSachThiSinh) {
                for (NguyenVong nv : ts.getDanhSachNguyenVong()) {
                    if (nv.getMaNguyenVong() == maNguyenVong) {
                        danhSachThiSinhTrungTuyen.add(new ThiSinhTrungTuyen(ts, nv));
                        break;
                    }
                }
            }
            
            // Sắp xếp danh sách theo điểm thi giảm dần
            Collections.sort(danhSachThiSinhTrungTuyen);
            
            // Hiển thị danh sách đã sắp xếp
            System.out.println("\nDanh sách thí sinh theo điểm thi (giảm dần):");
            
            if (danhSachThiSinhTrungTuyen.isEmpty()) {
                System.out.println("Không có thí sinh nào đăng ký nguyện vọng này.");
                return;
            }
            
            System.out.printf("%-15s %-30s %-10s %-15s %-15s%n", 
                             "Số báo danh", "Họ và tên", "Giới tính", "Tổng điểm", "Kết quả");
            System.out.println("-".repeat(85));
            
            boolean coThiSinhTrungTuyen = false;
            for (ThiSinhTrungTuyen tstt : danhSachThiSinhTrungTuyen) {
                ThiSinh ts = tstt.getThiSinh();
                coThiSinhTrungTuyen = coThiSinhTrungTuyen || tstt.isTrungTuyen();
                
                System.out.printf("%-15s %-30s %-10s %-15.2f %-15s%n", 
                                 ts.getSoBaoDanh(),
                                 ts.getHoTen(),
                                 ts.getGioiTinh(),
                                 tstt.getTongDiem(),
                                 tstt.isTrungTuyen() ? "Trúng tuyển" : "Không trúng tuyển");
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
    
    public void hienThiDanhSachTrungTuyenTheoMaTruongVaMaNganh() {
        System.out.println("\n===== DANH SÁCH THÍ SINH TRÚNG TUYỂN THEO TRƯỜNG VÀ NGÀNH =====");
        
        List<NguyenVong> danhSachNguyenVong = NguyenVongService.getDanhSachNguyenVong();
        if (danhSachNguyenVong.isEmpty()) {
            System.out.println("Danh sách nguyện vọng trống!");
            return;
        }
        
        // Nhập mã trường
        System.out.print("Nhập mã trường: ");
        String maTruong = sc.nextLine();
        
        // Nhập mã ngành
        System.out.print("Nhập mã ngành: ");
        String maNganh = sc.nextLine();
        
        // Tìm các nguyện vọng thuộc trường và ngành
        List<NguyenVong> nguyenVongTheoTruongNganh = new ArrayList<>();
        for (NguyenVong nv : danhSachNguyenVong) {
            if (nv.getMaTruong().equalsIgnoreCase(maTruong) && 
                nv.getMaNganh().equalsIgnoreCase(maNganh)) {
                nguyenVongTheoTruongNganh.add(nv);
            }
        }
        
        if (nguyenVongTheoTruongNganh.isEmpty()) {
            System.out.println("Không tìm thấy nguyện vọng nào thuộc trường " + maTruong + 
                               " và ngành " + maNganh);
            return;
        }
        
        System.out.println("\nĐã tìm thấy " + nguyenVongTheoTruongNganh.size() + 
                           " nguyện vọng với mã trường " + maTruong + 
                           " và mã ngành " + maNganh + ":");
        
        // Hiển thị thông tin các nguyện vọng tìm thấy
        for (NguyenVong nv : nguyenVongTheoTruongNganh) {
            System.out.println("- Mã nguyện vọng: " + nv.getMaNguyenVong() + 
                             ", Tên ngành: " + nv.getTenNganh() + 
                             ", Khối: " + nv.getKhoiXetTuyen() + 
                             ", Điểm chuẩn: " + nv.getDiemDatDieuKien());
        }
        
        // Tạo danh sách thí sinh để sắp xếp
        List<ThiSinhTrungTuyen> danhSachThiSinhTrungTuyen = new ArrayList<>();
        List<ThiSinh> danhSachThiSinh = thiSinhService.getDanhSachThiSinh();
        
        for (ThiSinh ts : danhSachThiSinh) {
            for (NguyenVong nv : ts.getDanhSachNguyenVong()) {
                if (nv.getMaTruong().equalsIgnoreCase(maTruong) && 
                    nv.getMaNganh().equalsIgnoreCase(maNganh)) {
                    danhSachThiSinhTrungTuyen.add(new ThiSinhTrungTuyen(ts, nv));
                    break;
                }
            }
        }
        
        // Sắp xếp danh sách theo điểm thi giảm dần
        Collections.sort(danhSachThiSinhTrungTuyen);
        
        // Hiển thị danh sách đã sắp xếp
        System.out.println("\nDanh sách thí sinh theo điểm thi (giảm dần):");
        
        if (danhSachThiSinhTrungTuyen.isEmpty()) {
            System.out.println("Không có thí sinh nào đăng ký ngành " + maNganh + 
                               " của trường " + maTruong);
            return;
        }
        
        System.out.printf("%-15s %-30s %-10s %-15s %-15s%n", 
                         "Số báo danh", "Họ và tên", "Giới tính", "Tổng điểm", "Kết quả");
        System.out.println("-".repeat(85));
        
        boolean coThiSinhTrungTuyen = false;
        for (ThiSinhTrungTuyen tstt : danhSachThiSinhTrungTuyen) {
            ThiSinh ts = tstt.getThiSinh();
            coThiSinhTrungTuyen = coThiSinhTrungTuyen || tstt.isTrungTuyen();
            
            System.out.printf("%-15s %-30s %-10s %-15.2f %-15s%n", 
                             ts.getSoBaoDanh(),
                             ts.getHoTen(),
                             ts.getGioiTinh(),
                             tstt.getTongDiem(),
                             tstt.isTrungTuyen() ? "Trúng tuyển" : "Không trúng tuyển");
        }
        
        if (!coThiSinhTrungTuyen) {
            System.out.println("Không có thí sinh nào trúng tuyển ngành " + maNganh + 
                               " của trường " + maTruong);
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
    
    public void ghiDanhSachTrungTuyenTheoNguyenVong() {
        System.out.println("\n===== GHI DANH SÁCH THÍ SINH TRÚNG TUYỂN RA FILE =====");
        
        List<NguyenVong> danhSachNguyenVong = NguyenVongService.getDanhSachNguyenVong();
        if (danhSachNguyenVong.isEmpty()) {
            System.out.println("Danh sách nguyện vọng trống!");
            return;
        }
        
        System.out.print("Nhập mã nguyện vọng để xuất danh sách trúng tuyển: ");
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
            
            // Tạo thư mục reports nếu chưa tồn tại
            File reportsDir = new File("reports");
            if (!reportsDir.exists()) {
                reportsDir.mkdir();
            }
            
            // Tạo tên file với timestamp để đảm bảo tên file là duy nhất
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = dateFormat.format(new Date());
            String fileName = "reports/trung_tuyen_nv_" + maNguyenVong + "_" + timestamp + ".txt";
            
            // Tạo danh sách thí sinh trúng tuyển và sắp xếp
            List<ThiSinhTrungTuyen> danhSachThiSinhTrungTuyen = new ArrayList<>();
            List<ThiSinh> danhSachThiSinh = thiSinhService.getDanhSachThiSinh();
            
            for (ThiSinh ts : danhSachThiSinh) {
                for (NguyenVong nv : ts.getDanhSachNguyenVong()) {
                    if (nv.getMaNguyenVong() == maNguyenVong) {
                        danhSachThiSinhTrungTuyen.add(new ThiSinhTrungTuyen(ts, nv));
                        break;
                    }
                }
            }
            
            // Sắp xếp danh sách theo điểm thi giảm dần
            Collections.sort(danhSachThiSinhTrungTuyen);
            
            // Ghi ra file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                // Ghi thông tin nguyện vọng
                writer.write("======================================================\n");
                writer.write("           DANH SÁCH THÍ SINH TRÚNG TUYỂN\n");
                writer.write("======================================================\n\n");
                writer.write("THÔNG TIN NGUYỆN VỌNG:\n");
                writer.write("- Mã nguyện vọng: " + nguyenVong.getMaNguyenVong() + "\n");
                writer.write("- Mã ngành: " + nguyenVong.getMaNganh() + "\n");
                writer.write("- Tên ngành: " + nguyenVong.getTenNganh() + "\n");
                writer.write("- Mã trường: " + nguyenVong.getMaTruong() + "\n");
                writer.write("- Khối xét tuyển: " + nguyenVong.getKhoiXetTuyen() + "\n");
                writer.write("- Điểm chuẩn: " + nguyenVong.getDiemDatDieuKien() + "\n\n");
                
                // Ghi danh sách thí sinh
                writer.write(String.format("%-15s %-30s %-10s %-15s %-15s\n", 
                                         "Số báo danh", "Họ và tên", "Giới tính", "Tổng điểm", "Kết quả"));
                writer.write("-".repeat(85) + "\n");
                
                boolean coThiSinhTrungTuyen = false;
                for (ThiSinhTrungTuyen tstt : danhSachThiSinhTrungTuyen) {
                    ThiSinh ts = tstt.getThiSinh();
                    
                    // Chỉ ghi thí sinh trúng tuyển
                    if (tstt.isTrungTuyen()) {
                        coThiSinhTrungTuyen = true;
                        writer.write(String.format("%-15s %-30s %-10s %-15.2f %-15s\n", 
                                                ts.getSoBaoDanh(),
                                                ts.getHoTen(),
                                                ts.getGioiTinh(),
                                                tstt.getTongDiem(),
                                                "Trúng tuyển"));
                    }
                }
                
                if (!coThiSinhTrungTuyen) {
                    writer.write("Không có thí sinh nào trúng tuyển nguyện vọng này.\n");
                }
                
                // Thêm thông tin thời gian xuất báo cáo
                writer.write("\n\n");
                writer.write("Báo cáo được tạo lúc: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + "\n");
                
                System.out.println(" Đã ghi danh sách thí sinh trúng tuyển vào file: " + fileName);
            } catch (IOException e) {
                System.out.println(" Lỗi khi ghi file: " + e.getMessage());
            }
            
        } catch (NumberFormatException e) {
            System.out.println(" Mã nguyện vọng phải là số nguyên!");
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
