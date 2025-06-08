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
        System.out.println("\n===== LIST OF ADMITTED CANDIDATES =====");
        
        List<NguyenVong> danhSachNguyenVong = NguyenVongService.getDanhSachNguyenVong();
        if (danhSachNguyenVong.isEmpty()) {
            System.out.println("Aspiration list is empty!");
            return;
        }
        
        System.out.print("Enter aspiration code to view admission list: ");
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
                System.out.println("No aspiration found with code: " + maNguyenVong);
                return;
            }
            
            // Hiển thị thông tin nguyện vọng
            System.out.println("\nAspiration Information:");
            System.out.println("- Major Code: " + nguyenVong.getMaNganh());
            System.out.println("- Major Name: " + nguyenVong.getTenNganh());
            System.out.println("- School Code: " + nguyenVong.getMaTruong());
            System.out.println("- Admission Group: " + nguyenVong.getKhoiXetTuyen());
            System.out.println("- Required Score: " + nguyenVong.getDiemDatDieuKien());
            
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
            System.out.println("\nList of candidates by test score (descending):");
            
            if (danhSachThiSinhTrungTuyen.isEmpty()) {
                System.out.println("No candidates registered for this aspiration.");
                return;
            }
            
            System.out.printf("%-15s %-30s %-10s %-15s %-15s%n",
                              "ID", "Full Name", "Gender", "Total Score", "Result");
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
                                 tstt.isTrungTuyen() ? "Admitted" : "Not Admitted");
            }
            
            if (!coThiSinhTrungTuyen) {
                System.out.println("No candidates qualified for this aspiration.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Aspiration code must be an integer!");
        }
        
        // Đoạn code cho người dùng nhấn 0 để quay lại menu
        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nPress 0 to return to main menu...");
            try {
                String input = sc.nextLine();
                if (input.equals("0")) {
                    validInput = true;
                    System.out.println("Returning to main menu...");
                } else {
                    System.out.println("Please press 0 to return to main menu.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
    }
    
    public void hienThiDanhSachTrungTuyenTheoMaTruongVaMaNganh() {
        System.out.println("\n===== LIST OF ADMITTED CANDIDATES BY SCHOOL AND MAJOR =====");
        
        List<NguyenVong> danhSachNguyenVong = NguyenVongService.getDanhSachNguyenVong();
        if (danhSachNguyenVong.isEmpty()) {
            System.out.println("Aspiration list is empty!");
            return;
        }
        
        // Nhập mã trường
        System.out.print("Enter school code: ");
        String maTruong = sc.nextLine();
        
        // Nhập mã ngành
        System.out.print("Enter major code: ");
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
            System.out.println("No aspirations found for school " + maTruong +
                            " and major " + maNganh);
            return;
        }
        
        System.out.println("\nFound " + nguyenVongTheoTruongNganh.size() +
                            " aspirations with school code " + maTruong +
                           " and major code " + maNganh + ":");
        
        // Hiển thị thông tin các nguyện vọng tìm thấy
        for (NguyenVong nv : nguyenVongTheoTruongNganh) {
            System.out.println("- Aspiration Code: " + nv.getMaNguyenVong() +
                              ", Major Name: " + nv.getTenNganh() +
                             ", Group: " + nv.getKhoiXetTuyen() +
                             ", Required Score: " + nv.getDiemDatDieuKien());
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
        System.out.println("\nList of candidates by test score (descending):");
        
        if (danhSachThiSinhTrungTuyen.isEmpty()) {
            System.out.println("No candidates registered for major " + maNganh +
                                " of school " + maTruong);
            return;
        }
        
        System.out.printf("%-15s %-30s %-10s %-15s %-15s%n",
                          "ID", "Full Name", "Gender", "Total Score", "Result");
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
                             tstt.isTrungTuyen() ? "Admitted" : "Not Admitted");
        }
        
        if (!coThiSinhTrungTuyen) {
            System.out.println("No candidates qualified for major " + maNganh +
                                " of school " + maTruong);
        }
        
        // Đoạn code cho người dùng nhấn 0 để quay lại menu
        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nPress 0 to return to main menu...");
            try {
                String input = sc.nextLine();
                if (input.equals("0")) {
                    validInput = true;
                    System.out.println("Returning to main menu...");
                } else {
                    System.out.println("Please press 0 to return to main menu.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
    }
    
    public void ghiDanhSachTrungTuyenTheoNguyenVong() {
        System.out.println("\n===== WRITE ADMITTED CANDIDATES LIST TO FILE =====");
        
        List<NguyenVong> danhSachNguyenVong = NguyenVongService.getDanhSachNguyenVong();
        if (danhSachNguyenVong.isEmpty()) {
            System.out.println("Aspiration list is empty!");
            return;
        }
        
        System.out.print("Enter aspiration code to export admission list: ");
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
                System.out.println("No aspiration found with code: " + maNguyenVong);
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
                writer.write("           LIST OF ADMITTED CANDIDATES\n");
                writer.write("======================================================\n\n");
                writer.write("ASPIRATION INFORMATION:\n");
                writer.write("- Aspiration Code: " + nguyenVong.getMaNguyenVong() + "\n");
                writer.write("- Major Code: " + nguyenVong.getMaNganh() + "\n");
                writer.write("- Major Name: " + nguyenVong.getTenNganh() + "\n");
                writer.write("- School Code: " + nguyenVong.getMaTruong() + "\n");
                writer.write("- Admission Group: " + nguyenVong.getKhoiXetTuyen() + "\n");
                writer.write("- Required Score: " + nguyenVong.getDiemDatDieuKien() + "\n\n");
                
                // Ghi danh sách thí sinh
                writer.write(String.format("%-15s %-30s %-10s %-15s %-15s\n", 
                                         "ID", "Full Name", "Gender", "Total Score", "Result"));
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
                                                "Admitted"));
                    }
                }
                
                if (!coThiSinhTrungTuyen) {
                    writer.write("No candidates qualified for this aspiration.\n");
                }
                
                // Thêm thông tin thời gian xuất báo cáo
                writer.write("\n\n");
                writer.write("Report generated at: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + "\n");
                
                System.out.println(" Successfully wrote admitted candidates list to file: " + fileName);
            } catch (IOException e) {
                System.out.println(" Error writing file: " + e.getMessage());
            }
            
        } catch (NumberFormatException e) {
            System.out.println(" Aspiration code must be an integer!");
        }
        
        // Đoạn code cho người dùng nhấn 0 để quay lại menu
        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nPress 0 to return to main menu...");
            try {
                String input = sc.nextLine();
                if (input.equals("0")) {
                    validInput = true;
                    System.out.println("Returning to main menu...");
                } else {
                    System.out.println("Please press 0 to return to main menu.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
    }
}
