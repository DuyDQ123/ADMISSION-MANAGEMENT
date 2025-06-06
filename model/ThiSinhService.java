package model;

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
        System.out.print("Nhập số báo danh: ");
        String soBaoDanh = sc.nextLine();

        System.out.print("Nhập họ và tên: ");
        String hoTen = sc.nextLine();

        // Nhập giới tính dạng enum GenDer
        Gender gioiTinh = null;
        while (gioiTinh == null) {
            System.out.print("Nhập giới tính (MALE, FEMALE, OTHER): ");
            String gtInput = sc.nextLine().toUpperCase();
            try {
                gioiTinh = Gender.valueOf(gtInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Giới tính không hợp lệ, vui lòng nhập lại.");
            }
        }

        System.out.print("Nhập năm sinh: ");
        int namSinh = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập quê quán: ");
        String queQuan = sc.nextLine();

        // Nếu muốn nhập điểm thi, điểm ưu tiên thì thêm phần nhập bên dưới
        System.out.print("Nhập điểm thi (hoặc để trống là 0): ");
        String diemThiInput = sc.nextLine();
        double diemThi = diemThiInput.isEmpty() ? 0.0 : Double.parseDouble(diemThiInput);

        System.out.print("Nhập điểm ưu tiên (hoặc để trống là 0): ");
        String diemUuTienInput = sc.nextLine();
        double diemUuTien = diemUuTienInput.isEmpty() ? 0.0 : Double.parseDouble(diemUuTienInput);

        ThiSinh ts = new ThiSinh(soBaoDanh, hoTen, gioiTinh, namSinh, queQuan, diemThi, diemUuTien);
        danhSachThiSinh.add(ts);

        System.out.println("Thêm thí sinh thành công:\n" + ts);
    } catch (Exception e) {
        System.out.println("Lỗi khi thêm thí sinh: " + e.getMessage());
    }
}

    // Cập nhật thí sinh
    public void capNhatThiSinh() {
    System.out.print("Nhập số báo danh để cập nhật: ");
    String soBaoDanh = sc.nextLine();
    for (ThiSinh ts : danhSachThiSinh) {
        if (ts.getSoBaoDanh().equalsIgnoreCase(soBaoDanh)) {
            try {
                System.out.print("Nhập họ và tên mới (để trống giữ nguyên): ");
                String hoTen = sc.nextLine();
                if (!hoTen.isEmpty()) {
                    ts.setHoTen(hoTen);
                }

                // Nhập giới tính mới
                System.out.print("Nhập giới tính mới (MALE, FEMALE, OTHER) (để trống giữ nguyên): ");
                String gtInput = sc.nextLine().toUpperCase();
                if (!gtInput.isEmpty()) {
                    try {
                        Gender gioiTinh = Gender.valueOf(gtInput);
                        ts.setGioiTinh(gioiTinh);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Giới tính không hợp lệ, giữ nguyên giá trị cũ.");
                    }
                }

                System.out.print("Nhập năm sinh mới (để trống giữ nguyên): ");
                String namSinhInput = sc.nextLine();
                if (!namSinhInput.isEmpty()) {
                    int namSinh = Integer.parseInt(namSinhInput);
                    ts.setNamSinh(namSinh);
                }

                System.out.print("Nhập quê quán mới (để trống giữ nguyên): ");
                String queQuan = sc.nextLine();
                if (!queQuan.isEmpty()) {
                    ts.setQueQuan(queQuan);
                }

                System.out.print("Nhập điểm thi mới (để trống giữ nguyên): ");
                String diemThiInput = sc.nextLine();
                if (!diemThiInput.isEmpty()) {
                    double diemThi = Double.parseDouble(diemThiInput);
                    ts.setDiemThi(diemThi);
                }

                System.out.print("Nhập điểm ưu tiên mới (để trống giữ nguyên): ");
                String diemUuTienInput = sc.nextLine();
                if (!diemUuTienInput.isEmpty()) {
                    double diemUuTien = Double.parseDouble(diemUuTienInput);
                    ts.setDiemUuTien(diemUuTien);
                }

                System.out.println("Cập nhật thí sinh thành công:\n" + ts);
                return;
            } catch (Exception e) {
                System.out.println("Lỗi khi cập nhật thí sinh: " + e.getMessage());
                return;
            }
        }
    }
    System.out.println("Không tìm thấy thí sinh với số báo danh: " + soBaoDanh);
}
    // Xoá thí sinh
    public void xoaThiSinh() {
    System.out.print("Nhập số báo danh của thí sinh cần xoá: ");
    String soBaoDanh = sc.nextLine();
    
    boolean removed = danhSachThiSinh.removeIf(ts -> ts.getSoBaoDanh().equalsIgnoreCase(soBaoDanh));
        if (removed) {
            System.out.println("Thí sinh đã được xóa thành công.");
        } else {
            System.out.println("Không tìm thấy thí sinh với số báo danh: " + soBaoDanh);
        }
    }
    

public void themNguyenVongChoThiSinh() {
    System.out.print("Nhập số báo danh của thí sinh: ");
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
        System.out.println("Không tìm thấy thí sinh với số báo danh: " + soBaoDanh);
        return;
    }
    
    // Lấy danh sách nguyện vọng từ NguyenVongService
    List<NguyenVong> danhSachNguyenVong = NguyenVongService.getDanhSachNguyenVong();
    
    if (danhSachNguyenVong.isEmpty()) {
        System.out.println("Chưa có nguyện vọng nào trong hệ thống! Vui lòng thêm nguyện vọng trước.");
        return;
    }
    
    // Hiển thị danh sách nguyện vọng có sẵn
    System.out.println("\n===== DANH SÁCH NGUYỆN VỌNG CÓ SẴN =====");
    for (NguyenVong nv : danhSachNguyenVong) {
        System.out.println("Mã: " + nv.getMaNguyenVong() + 
                         " | Ngành: " + nv.getTenNganh() + 
                         " | Trường: " + nv.getMaTruong() + 
                         " | Khối: " + nv.getKhoiXetTuyen() + 
                         " | Điểm chuẩn: " + nv.getDiemDatDieuKien());
    }
    
    try {
        System.out.print("\nNhập mã nguyện vọng muốn thêm cho thí sinh: ");
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
            System.out.println("Không tìm thấy nguyện vọng với mã: " + maNguyenVong);
            return;
        }
        
        // Kiểm tra nguyện vọng đã tồn tại trong danh sách của thí sinh chưa
        for (NguyenVong nv : thiSinh.getDanhSachNguyenVong()) {
            if (nv.getMaNguyenVong() == maNguyenVong) {
                System.out.println("Thí sinh đã đăng ký nguyện vọng này rồi!");
                return;
            }
        }
        
        // Kiểm tra điểm của thí sinh có đủ điều kiện không
        if (thiSinh.getDiemThi() + thiSinh.getDiemUuTien() < nguyenVongChon.getDiemDatDieuKien()) {
            System.out.println("Thí sinh không đủ điểm để đăng ký nguyện vọng này!");
            System.out.println("Điểm của thí sinh: " + (thiSinh.getDiemThi() + thiSinh.getDiemUuTien()));
            System.out.println("Điểm chuẩn yêu cầu: " + nguyenVongChon.getDiemDatDieuKien());
            return;
        }
        
        // Thêm nguyện vọng cho thí sinh
        thiSinh.themNguyenVong(nguyenVongChon);
        System.out.println(" Thêm nguyện vọng thành công cho thí sinh " + thiSinh.getHoTen());
        
    } catch (NumberFormatException e) {
        System.out.println("Mã nguyện vọng phải là số nguyên!");
    } catch (Exception e) {
        System.out.println("Lỗi khi thêm nguyện vọng: " + e.getMessage());
    }
}
public void xoaNguyenVongChoThiSinh() {
    System.out.print("Nhập số báo danh của thí sinh: ");
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
        System.out.println("Không tìm thấy thí sinh với số báo danh: " + soBaoDanh);
        return;
    }
    
    // Kiểm tra danh sách nguyện vọng của thí sinh
    List<NguyenVong> danhSachNV = thiSinh.getDanhSachNguyenVong();
    if (danhSachNV.isEmpty()) {
        System.out.println("Thí sinh này chưa có nguyện vọng nào!");
        return;
    }
    
    // Hiển thị danh sách nguyện vọng của thí sinh
    System.out.println("Danh sách nguyện vọng của thí sinh " + thiSinh.getHoTen() + ":");
    for (NguyenVong nv : danhSachNV) {
        System.out.println("Mã nguyện vọng: " + nv.getMaNguyenVong() + 
                          ", Ngành: " + nv.getMaNganh() + " - " + nv.getTenNganh() + 
                          ", Trường: " + nv.getMaTruong() + 
                          ", Khối: " + nv.getKhoiXetTuyen());
    }
    
    // Chọn nguyện vọng cần xóa
    try {
        System.out.print("Nhập mã nguyện vọng cần xóa: ");
        int maNguyenVong = Integer.parseInt(sc.nextLine());
        
        boolean daXoa = false;
        for (NguyenVong nv : danhSachNV) {
            if (nv.getMaNguyenVong() == maNguyenVong) {
                thiSinh.xoaNguyenVong(maNguyenVong);
                System.out.println("Đã xóa nguyện vọng thành công!");
                daXoa = true;
                break;
            }
        }
        
        if (!daXoa) {
            System.out.println("Không tìm thấy nguyện vọng có mã " + maNguyenVong + " trong danh sách nguyện vọng của thí sinh này!");
        }
    } catch (NumberFormatException e) {
        System.out.println("Lỗi: Mã nguyện vọng phải là số nguyên!");
    }
}
public void timKiemThiSinh() {
    System.out.println("Tìm kiếm thí sinh theo:");
    System.out.println("1. Số báo danh");
    System.out.println("2. Họ và tên");
    System.out.println("3. Giới tính");
    System.out.println("4. Điểm thi");

    System.out.print("Lựa chọn: ");
    int choice = Integer.parseInt(sc.nextLine());
    List<ThiSinh> ketQua = new ArrayList<>();

    switch (choice) {
        case 1:
            System.out.print("Nhập số báo danh cần tìm: ");
            String soBaoDanh = sc.nextLine().trim();
            ketQua = danhSachThiSinh.stream()
                    .filter(ts -> ts.getSoBaoDanh().equalsIgnoreCase(soBaoDanh))
                    .collect(Collectors.toList());
            break;

        case 2:
            System.out.print("Nhập họ và tên (hoặc một phần): ");
            String hoTen = sc.nextLine().toLowerCase().trim();
            ketQua = danhSachThiSinh.stream()
                    .filter(ts -> ts.getHoTen().toLowerCase().contains(hoTen))
                    .collect(Collectors.toList());
            break;

        case 3:
            System.out.print("Nhập giới tính (NAM/NU): ");
            String gioiTinhStr = sc.nextLine().toUpperCase().trim();
            try {
                Gender gioiTinh = Gender.valueOf(gioiTinhStr);
                ketQua = danhSachThiSinh.stream()
                        .filter(ts -> ts.getGioiTinh() == gioiTinh)
                        .collect(Collectors.toList());
            } catch (IllegalArgumentException e) {
                System.out.println(" Giới tính không hợp lệ.");
                return;
            }
            break;

        case 4:
            System.out.print("Nhập điểm thi cần tìm: ");
            double diem = Double.parseDouble(sc.nextLine());
            ketQua = danhSachThiSinh.stream()
                    .filter(ts -> ts.getDiemThi() == diem)
                    .collect(Collectors.toList());
            break;

        default:
            System.out.println(" Lựa chọn không hợp lệ.");
            return;
    }

    // In kết quả
    if (ketQua.isEmpty()) {
        System.out.println(" Không tìm thấy thí sinh nào phù hợp.");
    } else {
        System.out.println(" Danh sách thí sinh tìm thấy:");
        ketQua.forEach(System.out::println);
    }
}
    public void hienThiDanhSachThiSinh() {
    if (danhSachThiSinh.isEmpty()) {
        System.out.println("Danh sách thí sinh trống!");
    } else {
        System.out.println("DANH SÁCH THÍ SINH:");
        for (ThiSinh ts : danhSachThiSinh) {
            System.out.println(ts);
            
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
public List<ThiSinh> getDanhSachThiSinh() {
    return danhSachThiSinh;
}
}