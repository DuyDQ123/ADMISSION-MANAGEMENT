package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class GiamThiService {
    private List<GiamThi> danhSachGiamThi = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    
    // Thêm giám thị
    public void themGiamThi() {
        System.out.println("\n===== THÊM GIÁM THỊ =====");
        
        System.out.print("Enter full name: ");
        String hoTen = sc.nextLine();
        
        // Nhập giới tính
        Gender gioiTinh = null;
        while (gioiTinh == null) {
            System.out.print("Enter gender (MALE, FEMALE, OTHER): ");
            String gioiTinhInput = sc.nextLine().toUpperCase();
            try {
                gioiTinh = Gender.valueOf(gioiTinhInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid gender. Please try again.");
            }
        }
        
        // Nhập năm sinh
        int namSinh = 0;
        boolean validNamSinh = false;
        while (!validNamSinh) {
            try {
                System.out.print("Enter birth year: ");
                namSinh = Integer.parseInt(sc.nextLine());
                if (namSinh < 1940 || namSinh > 2000) {
                    System.out.println("Invalid birth year. Please enter year between 1940-2000.");
                    continue;
                }
                validNamSinh = true;
            } catch (NumberFormatException e) {
                System.out.println("Birth year must be a number. Please try again.");
            }
        }
        
        System.out.print("Enter hometown: ");
        String queQuan = sc.nextLine();
        
        // Tạo và thêm giám thị mới
        GiamThi giamThi = new GiamThi(hoTen, gioiTinh, namSinh, queQuan);
        danhSachGiamThi.add(giamThi);
        
        System.out.println("Successfully added supervisor! Supervisor ID: " + giamThi.getMaGiamThi());
    }
    
    // Cập nhật giám thị
    public void capNhatGiamThi() {
        System.out.println("\n===== CẬP NHẬT GIÁM THỊ =====");
        
        if (danhSachGiamThi.isEmpty()) {
            System.out.println("Supervisor list is empty!");
            return;
        }
        
        System.out.print("Enter supervisor ID to update: ");
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
                System.out.println("No supervisor found with ID: " + maGiamThi);
                return;
            }
            
            // Hiển thị thông tin hiện tại
            System.out.println("Current supervisor information:");
            System.out.println(giamThiCapNhat);
            
            // Cập nhật thông tin
            System.out.println("\nEnter new information (leave blank to keep current):");
            
            System.out.print("New full name: ");
            String hoTenMoi = sc.nextLine();
            if (!hoTenMoi.trim().isEmpty()) {
                giamThiCapNhat.setHoTen(hoTenMoi);
            }
            
            // Cập nhật giới tính
            System.out.print("New gender (MALE, FEMALE, OTHER - leave blank to keep current): ");
            String gioiTinhInput = sc.nextLine().toUpperCase();
            if (!gioiTinhInput.trim().isEmpty()) {
                try {
                    Gender gioiTinhMoi = Gender.valueOf(gioiTinhInput);
                    giamThiCapNhat.setGioiTinh(gioiTinhMoi);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid gender. Keeping current gender.");
                }
            }
            
            // Cập nhật năm sinh
            System.out.print("New birth year (leave blank to keep current): ");
            String namSinhInput = sc.nextLine();
            if (!namSinhInput.trim().isEmpty()) {
                try {
                    int namSinhMoi = Integer.parseInt(namSinhInput);
                    if (namSinhMoi < 1940 || namSinhMoi > 2000) {
                        System.out.println("Invalid birth year. Keeping current birth year.");
                    } else {
                        giamThiCapNhat.setNamSinh(namSinhMoi);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Birth year must be a number. Keeping current birth year.");
                }
            }
            
            // Cập nhật quê quán
            System.out.print("New hometown: ");
            String queQuanMoi = sc.nextLine();
            if (!queQuanMoi.trim().isEmpty()) {
                giamThiCapNhat.setQueQuan(queQuanMoi);
            }
            
            System.out.println("Successfully updated supervisor!");
            
        } catch (NumberFormatException e) {
            System.out.println("Supervisor ID must be an integer!");
        }
    }
    
    // Xóa giám thị
    public void xoaGiamThi() {
        System.out.println("\n===== XÓA GIÁM THỊ =====");
        
        if (danhSachGiamThi.isEmpty()) {
            System.out.println("Supervisor list is empty!");
            return;
        }
        
        System.out.print("Enter supervisor ID to delete: ");
        try {
            int maGiamThi = Integer.parseInt(sc.nextLine());
            
            boolean removed = danhSachGiamThi.removeIf(gt -> gt.getMaGiamThi() == maGiamThi);
            
            if (removed) {
                System.out.println("Successfully deleted supervisor!");
            } else {
                System.out.println("No supervisor found with ID: " + maGiamThi);
            }
        } catch (NumberFormatException e) {
            System.out.println("Supervisor ID must be an integer!");
        }
    }
    
    // Hiển thị danh sách giám thị
    public void hienThiDanhSachGiamThi() {
        if (danhSachGiamThi.isEmpty()) {
            System.out.println("Supervisor list is empty!");
        } else {
            System.out.println("\n===== DANH SÁCH GIÁM THỊ =====");
            for (GiamThi gt : danhSachGiamThi) {
                System.out.println(gt);
            }
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
    
    // Đọc danh sách giám thị từ file
    public void docDanhSachGiamThiTuFile() {
        System.out.println("\n===== ĐỌC DANH SÁCH GIÁM THỊ TỪ FILE =====");
        
        System.out.print("Enter file path: ");
        String filePath = sc.nextLine();
        
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("❌ File does not exist: " + filePath);
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 0;
            
            // Bỏ qua dòng tiêu đề nếu có
            boolean headerSkipped = false;
            
            while ((line = reader.readLine()) != null) {
                // Bỏ qua dòng trống
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                // Bỏ qua dòng tiêu đề nếu là dòng đầu tiên không rỗng
                if (!headerSkipped) {
                    headerSkipped = true;
                    if (!line.contains(",")) {
                        continue;
                    }
                }
                
                try {
                    // Phân tích dòng (định dạng CSV với dấu phẩy làm dấu phân cách)
                    // Định dạng: hoTen,gioiTinh,namSinh,queQuan
                    String[] parts = line.split(",");
                    if (parts.length < 4) {
                        System.out.println(" Invalid line format: " + line);
                        continue;
                    }
                    
                    String hoTen = parts[0].trim();
                    Gender gioiTinh;
                    try {
                        gioiTinh = Gender.valueOf(parts[1].trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println(" Invalid gender: " + parts[1] + " - skipping line: " + line);
                        continue;
                    }
                    
                    int namSinh;
                    try {
                        namSinh = Integer.parseInt(parts[2].trim());
                        if (namSinh < 1940 || namSinh > 2000) {
                            System.out.println(" Invalid birth year: " + parts[2] + " (outside range 1940-2000) - skipping line: " + line);
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Invalid birth year: " + parts[2] + " - skipping line: " + line);
                        continue;
                    }
                    
                    String queQuan = parts[3].trim();
                    
                    // Tạo đối tượng GiamThi và thêm vào danh sách
                    GiamThi giamThi = new GiamThi(hoTen, gioiTinh, namSinh, queQuan);
                    danhSachGiamThi.add(giamThi);
                    count++;
                    
                } catch (Exception e) {
                    System.out.println("Error processing line: " + line + " - " + e.getMessage());
                }
            }
            
            System.out.println(" Successfully read " + count + " supervisors from file!");
            
        } catch (IOException e) {
            System.out.println(" Error reading file: " + e.getMessage());
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