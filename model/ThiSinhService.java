package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ThiSinhService {
    private List<ThiSinh> danhSachThiSinh = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // Thêm Thí sinh

    public void themThiSinh() {
    try {
        System.out.print("Enter candidate ID: ");
        String soBaoDanh = sc.nextLine();

        System.out.print("Enter full name: ");
        String hoTen = sc.nextLine();

        // Nhập giới tính dạng enum GenDer
        Gender gioiTinh = null;
        while (gioiTinh == null) {
            System.out.print("Enter gender (MALE, FEMALE, OTHER): ");
            String gtInput = sc.nextLine().toUpperCase();
            try {
                gioiTinh = Gender.valueOf(gtInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid gender, please try again.");
            }
        }

        System.out.print("Enter birth year: ");
        int namSinh = Integer.parseInt(sc.nextLine());

        System.out.print("Enter hometown: ");
        String queQuan = sc.nextLine();

        // Nếu muốn nhập điểm thi, điểm ưu tiên thì thêm phần nhập bên dưới
        System.out.print("Enter test score (leave blank for 0): ");
        String diemThiInput = sc.nextLine();
        double diemThi = diemThiInput.isEmpty() ? 0.0 : Double.parseDouble(diemThiInput);

        System.out.print("Enter priority score (leave blank for 0): ");
        String diemUuTienInput = sc.nextLine();
        double diemUuTien = diemUuTienInput.isEmpty() ? 0.0 : Double.parseDouble(diemUuTienInput);

        ThiSinh ts = new ThiSinh(soBaoDanh, hoTen, gioiTinh, namSinh, queQuan, diemThi, diemUuTien);
        danhSachThiSinh.add(ts);

        System.out.println("Successfully added candidate:\n" + ts);
    } catch (Exception e) {
        System.out.println("Error adding candidate: " + e.getMessage());
    }
}

    // Cập nhật thí sinh
    public void capNhatThiSinh() {
    System.out.print("Enter candidate ID to update: ");
    String soBaoDanh = sc.nextLine();
    for (ThiSinh ts : danhSachThiSinh) {
        if (ts.getSoBaoDanh().equalsIgnoreCase(soBaoDanh)) {
            try {
                System.out.print("Enter new full name (leave blank to keep current): ");
                String hoTen = sc.nextLine();
                if (!hoTen.isEmpty()) {
                    ts.setHoTen(hoTen);
                }

                // Nhập giới tính mới
                System.out.print("Enter new gender (MALE, FEMALE, OTHER) (leave blank to keep current): ");
                String gtInput = sc.nextLine().toUpperCase();
                if (!gtInput.isEmpty()) {
                    try {
                        Gender gioiTinh = Gender.valueOf(gtInput);
                        ts.setGioiTinh(gioiTinh);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid gender, keeping current value.");
                    }
                }

                System.out.print("Enter new birth year (leave blank to keep current): ");
                String namSinhInput = sc.nextLine();
                if (!namSinhInput.isEmpty()) {
                    int namSinh = Integer.parseInt(namSinhInput);
                    ts.setNamSinh(namSinh);
                }

                System.out.print("Enter new hometown (leave blank to keep current): ");
                String queQuan = sc.nextLine();
                if (!queQuan.isEmpty()) {
                    ts.setQueQuan(queQuan);
                }

                System.out.print("Enter new test score (leave blank to keep current): ");
                String diemThiInput = sc.nextLine();
                if (!diemThiInput.isEmpty()) {
                    double diemThi = Double.parseDouble(diemThiInput);
                    ts.setDiemThi(diemThi);
                }

                System.out.print("Enter new priority score (leave blank to keep current): ");
                String diemUuTienInput = sc.nextLine();
                if (!diemUuTienInput.isEmpty()) {
                    double diemUuTien = Double.parseDouble(diemUuTienInput);
                    ts.setDiemUuTien(diemUuTien);
                }

                System.out.println("Successfully updated candidate:\n" + ts);
                return;
            } catch (Exception e) {
                System.out.println("Error updating candidate: " + e.getMessage());
                return;
            }
        }
    }
    System.out.println("No candidate found with ID: " + soBaoDanh);
}
    // Xoá thí sinh
    public void xoaThiSinh() {
    System.out.print("Enter candidate ID to delete: ");
    String soBaoDanh = sc.nextLine();
    
    boolean removed = danhSachThiSinh.removeIf(ts -> ts.getSoBaoDanh().equalsIgnoreCase(soBaoDanh));
        if (removed) {
            System.out.println("Candidate successfully deleted.");
        } else {
            System.out.println("No candidate found with ID: " + soBaoDanh);
        }
    }
    

public void themNguyenVongChoThiSinh() {
    System.out.print("Enter candidate ID: ");
    String soBaoDanh = sc.nextLine();
    
    // Tìm thí sinh theo số báo danh
    ThiSinh thiSinh = null;
    for (ThiSinh ts : danhSachThiSinh) {
        if (ts.getSoBaoDanh().equalsIgnoreCase(soBaoDanh)) {
            thiSinh = ts;
            break;
        }
    }
    
    if (thiSinh == null) {
        System.out.println("No candidate found with ID: " + soBaoDanh);
        return;
    }
    
    // Lấy danh sách nguyện vọng từ NguyenVongService
    List<NguyenVong> danhSachNguyenVong = NguyenVongService.getDanhSachNguyenVong();
    
    if (danhSachNguyenVong.isEmpty()) {
        System.out.println("No aspirations in the system! Please add aspirations first.");
        return;
    }
    
    // Hiển thị danh sách nguyện vọng có sẵn
    System.out.println("\n===== AVAILABLE ASPIRATIONS =====");
    for (NguyenVong nv : danhSachNguyenVong) {
        System.out.println("Code: " + nv.getMaNguyenVong() +
                          " | Major: " + nv.getTenNganh() +
                         " | School: " + nv.getMaTruong() +
                         " | Group: " + nv.getKhoiXetTuyen() +
                         " | Required Score: " + nv.getDiemDatDieuKien());
    }
    
    try {
        System.out.print("\nEnter aspiration code to add for candidate: ");
        int maNguyenVong = Integer.parseInt(sc.nextLine());
        
        // Tìm nguyện vọng theo mã
        NguyenVong nguyenVongChon = null;
        for (NguyenVong nv : danhSachNguyenVong) {
            if (nv.getMaNguyenVong() == maNguyenVong) {
                nguyenVongChon = nv;
                break;
            }
        }
        
        if (nguyenVongChon == null) {
            System.out.println("No aspiration found with code: " + maNguyenVong);
            return;
        }
        
        // Kiểm tra nguyện vọng đã tồn tại trong danh sách của thí sinh chưa
        for (NguyenVong nv : thiSinh.getDanhSachNguyenVong()) {
            if (nv.getMaNguyenVong() == maNguyenVong) {
                System.out.println("Candidate has already registered for this aspiration!");
                return;
            }
        }
        
        // Kiểm tra điểm của thí sinh có đủ điều kiện không
        if (thiSinh.getDiemThi() + thiSinh.getDiemUuTien() < nguyenVongChon.getDiemDatDieuKien()) {
            System.out.println("Candidate does not meet score requirement for this aspiration!");
            System.out.println("Candidate's score: " + (thiSinh.getDiemThi() + thiSinh.getDiemUuTien()));
            System.out.println("Required score: " + nguyenVongChon.getDiemDatDieuKien());
            return;
        }
        
        // Thêm nguyện vọng cho thí sinh
        thiSinh.themNguyenVong(nguyenVongChon);
        System.out.println(" Successfully added aspiration for candidate " + thiSinh.getHoTen());
        
    } catch (NumberFormatException e) {
        System.out.println("Aspiration code must be an integer!");
    } catch (Exception e) {
        System.out.println("Error adding aspiration: " + e.getMessage());
    }
}
public void xoaNguyenVongChoThiSinh() {
    System.out.print("Enter candidate ID: ");
    String soBaoDanh = sc.nextLine();
    
    // Tìm thí sinh theo số báo danh
    ThiSinh thiSinh = null;
    for (ThiSinh ts : danhSachThiSinh) {
        if (ts.getSoBaoDanh().equalsIgnoreCase(soBaoDanh)) {
            thiSinh = ts;
            break;
        }
    }
    
    if (thiSinh == null) {
        System.out.println("No candidate found with ID: " + soBaoDanh);
        return;
    }
    
    // Kiểm tra danh sách nguyện vọng của thí sinh
    List<NguyenVong> danhSachNV = thiSinh.getDanhSachNguyenVong();
    if (danhSachNV.isEmpty()) {
        System.out.println("This candidate has no aspirations!");
        return;
    }
    
    // Hiển thị danh sách nguyện vọng của thí sinh
    System.out.println("List of aspirations for candidate " + thiSinh.getHoTen() + ":");
    for (NguyenVong nv : danhSachNV) {
        System.out.println("Aspiration Code: " + nv.getMaNguyenVong() +
                           ", Major: " + nv.getMaNganh() + " - " + nv.getTenNganh() +
                          ", School: " + nv.getMaTruong() +
                          ", Group: " + nv.getKhoiXetTuyen());
    }
    
    // Chọn nguyện vọng cần xóa
    try {
        System.out.print("Enter aspiration code to delete: ");
        int maNguyenVong = Integer.parseInt(sc.nextLine());
        
        boolean daXoa = false;
        for (NguyenVong nv : danhSachNV) {
            if (nv.getMaNguyenVong() == maNguyenVong) {
                thiSinh.xoaNguyenVong(maNguyenVong);
                System.out.println("Successfully deleted aspiration!");
                daXoa = true;
                break;
            }
        }
        
        if (!daXoa) {
            System.out.println("No aspiration found with code " + maNguyenVong + " in this candidate's aspiration list!");
        }
    } catch (NumberFormatException e) {
        System.out.println("Error: Aspiration code must be an integer!");
    }
}
public void timKiemThiSinh() {
    System.out.println("Search candidate by:");
    System.out.println("1. ID");
    System.out.println("2. Full name");
    System.out.println("3. Gender");
    System.out.println("4. Test score");

    System.out.print("Choice: ");
    int choice = Integer.parseInt(sc.nextLine());
    List<ThiSinh> ketQua = new ArrayList<>();

    switch (choice) {
        case 1:
            System.out.print("Enter ID to search: ");
            String soBaoDanh = sc.nextLine().trim();
            ketQua = danhSachThiSinh.stream()
                    .filter(ts -> ts.getSoBaoDanh().equalsIgnoreCase(soBaoDanh))
                    .collect(Collectors.toList());
            break;

        case 2:
            System.out.print("Enter full name (or part of name): ");
            String hoTen = sc.nextLine().toLowerCase().trim();
            ketQua = danhSachThiSinh.stream()
                    .filter(ts -> ts.getHoTen().toLowerCase().contains(hoTen))
                    .collect(Collectors.toList());
            break;

        case 3:
            System.out.print("Enter gender (MALE/FEMALE): ");
            String gioiTinhStr = sc.nextLine().toUpperCase().trim();
            try {
                Gender gioiTinh = Gender.valueOf(gioiTinhStr);
                ketQua = danhSachThiSinh.stream()
                        .filter(ts -> ts.getGioiTinh() == gioiTinh)
                        .collect(Collectors.toList());
            } catch (IllegalArgumentException e) {
                System.out.println(" Invalid gender.");
                return;
            }
            break;

        case 4:
            System.out.print("Enter test score to search: ");
            double diem = Double.parseDouble(sc.nextLine());
            ketQua = danhSachThiSinh.stream()
                    .filter(ts -> ts.getDiemThi() == diem)
                    .collect(Collectors.toList());
            break;

        default:
            System.out.println(" Invalid choice.");
            return;
    }

    // In kết quả
    if (ketQua.isEmpty()) {
        System.out.println(" No matching candidates found.");
    } else {
        System.out.println(" List of found candidates:");
        ketQua.forEach(System.out::println);
    }
}
    public void hienThiDanhSachThiSinh() {
    if (danhSachThiSinh.isEmpty()) {
        System.out.println("Candidate list is empty!");
    } else {
        System.out.println("DANH SÁCH THÍ SINH:");
        for (ThiSinh ts : danhSachThiSinh) {
            System.out.println(ts);
            
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
public List<ThiSinh> getDanhSachThiSinh() {
    return danhSachThiSinh;
}
public void docDanhSachThiSinhTuFile() {
    System.out.println("\n===== ĐỌC DANH SÁCH THÍ SINH TỪ FILE =====");
    
    System.out.print("Enter file path: ");
    String filePath = sc.nextLine();
    
    File file = new File(filePath);
    if (!file.exists()) {
        System.out.println(" File does not exist: " + filePath);
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
                // Phân tích dòng - Định dạng: soBaoDanh,hoTen,gioiTinh,namSinh,queQuan,diemThi,diemUuTien
                String[] parts = line.split(",");
                if (parts.length < 7) {
                    System.out.println(" Invalid line format: " + line);
                    continue;
                }
                
                String soBaoDanh = parts[0].trim();
                String hoTen = parts[1].trim();
                
                Gender gioiTinh;
                try {
                    gioiTinh = Gender.valueOf(parts[2].trim().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println(" Invalid gender: " + parts[2] + " - skipping line: " + line);
                    continue;
                }
                
                int namSinh;
                try {
                    namSinh = Integer.parseInt(parts[3].trim());
                    if (namSinh < 2000 || namSinh > 2010) {
                        System.out.println(" Invalid birth year: " + parts[3] + " (outside range 2000-2010) - skipping line: " + line);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Invalid birth year: " + parts[3] + " - skipping line: " + line);
                    continue;
                }
                
                String queQuan = parts[4].trim();
                
                double diemThi;
                try {
                    diemThi = Double.parseDouble(parts[5].trim());
                    if (diemThi < 0 || diemThi > 30) {
                        System.out.println(" Invalid test score: " + parts[5] + " (outside range 0-30) - skipping line: " + line);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Invalid test score: " + parts[5] + " - skipping line: " + line);
                    continue;
                }
                
                double diemUuTien;
                try {
                    diemUuTien = Double.parseDouble(parts[6].trim());
                    if (diemUuTien < 0 || diemUuTien > 10) {
                        System.out.println(" Invalid priority score: " + parts[6] + " (outside range 0-10) - skipping line: " + line);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Invalid priority score: " + parts[6] + " - skipping line: " + line);
                    continue;
                }
                
                // Kiểm tra xem thí sinh đã tồn tại trong danh sách chưa
                boolean thiSinhDaTonTai = false;
                for (ThiSinh ts : danhSachThiSinh) {
                    if (ts.getSoBaoDanh().equalsIgnoreCase(soBaoDanh)) {
                        thiSinhDaTonTai = true;
                        break;
                    }
                }
                
                if (thiSinhDaTonTai) {
                    System.out.println(" Candidate with ID " + soBaoDanh + " already exists - skipping line: " + line);
                    continue;
                }
                
                // Tạo đối tượng ThiSinh và thêm vào danh sách
                ThiSinh thiSinh = new ThiSinh(soBaoDanh, hoTen, gioiTinh, namSinh, queQuan, diemThi, diemUuTien);
                danhSachThiSinh.add(thiSinh);
                count++;
                
            } catch (Exception e) {
                System.out.println(" Error processing line: " + line + " - " + e.getMessage());
            }
        }
        
        System.out.println(" Successfully read " + count + " candidates from file!");
        
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