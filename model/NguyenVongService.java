package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NguyenVongService {
    // Đảm bảo danh sách nguyện vọng đã là static
    private static List<NguyenVong> danhSachNguyenVong = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // Thêm nguyện vọng
    public void themNguyenVong() {
        try {
            System.out.print("Enter major code: ");
            String maNganh = sc.nextLine();

            System.out.print("Enter major name: ");
            String tenNganh = sc.nextLine();

            System.out.print("Enter school code: ");
            String maTruong = sc.nextLine();

            KhoiXetTuyen khoiXetTuyen = nhapKhoiXetTuyen();

            System.out.print("Enter qualifying score: ");
            double diemDatDieuKien = Double.parseDouble(sc.nextLine());

            NguyenVong nv = new NguyenVong(maNganh, tenNganh, maTruong, khoiXetTuyen, diemDatDieuKien);
            danhSachNguyenVong.add(nv);

            System.out.println("Successfully added aspiration:\n" + nv);
        } catch (Exception e) {
            System.out.println("Error adding aspiration: " + e.getMessage());
        }
    }

    // Cập nhật nguyện vọng
    public void capNhatNguyenVong() {
        System.out.print("Nhập mã nguyện vọng cần cập nhật: ");
        try {
            int maNguyenVong = Integer.parseInt(sc.nextLine());
            for (NguyenVong nv : danhSachNguyenVong) {
            if (nv.getMaNguyenVong() == maNguyenVong) {
                System.out.print("Enter new major name: ");
                nv.setTenNganh(sc.nextLine());

                System.out.print("Enter new school code: ");
                nv.setMaTruong(sc.nextLine());

                KhoiXetTuyen khoi = nhapKhoiXetTuyen();
                nv.setKhoiXetTuyen(khoi);

                System.out.print("Enter new qualifying score: ");
                nv.setDiemDatDieuKien(Double.parseDouble(sc.nextLine()));

                System.out.println("Aspiration updated successfully.");
                  return;
            }
        }
        System.out.println("Không tìm thấy mã nguyện vọng.");
    } catch (NumberFormatException e) {
        System.out.println("Lỗi: Mã nguyện vọng phải là số.");
    }
}


    // Xoá nguyện vọng
    public void xoaNguyenVong() {
    System.out.print("Nhập mã nguyện vọng cần xóa: ");
    try {
        int maNguyenVong = Integer.parseInt(sc.nextLine());
        boolean removed = danhSachNguyenVong.removeIf(nv -> nv.getMaNguyenVong() == maNguyenVong);
        if (removed) {
            System.out.println("Xóa nguyện vọng thành công.");
        } else {
            System.out.println("Không tìm thấy mã nguyện vọng.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Lỗi: Mã nguyện vọng phải là số.");
    }
}

    // Hiển thị danh sách
    public void hienThiDanhSachNguyenVong() {
        if (danhSachNguyenVong.isEmpty()) {
            System.out.println("Aspiration list is empty.");
        } else {
            System.out.println("\\n===== DANH SÁCH NGUYỆN VỌNG =====");
            for (NguyenVong nv : danhSachNguyenVong) {
                System.out.println(nv);
            }
        }
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

    // Nhập enum khối xét tuyển từ bàn phím
    private KhoiXetTuyen nhapKhoiXetTuyen() {
        while (true) {
            System.out.print("Enter admission group (Example: A, A1, B, C, D): ");
            String input = sc.nextLine().toUpperCase();
            try {
                return KhoiXetTuyen.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println(" Invalid admission group. Please try again.");
            }
        }
    }
    // Tìm kiếm nguyện vọng
    public void timKiemNguyenVong() {
        System.out.println(" Search aspirations by:");
        System.out.println("1. Major code");
        System.out.println("2. Major name");
        System.out.println("3. School code");
        System.out.println("4. Admission group");
        System.out.println("5. Qualifying score");

        int choice = Integer.parseInt(sc.nextLine());
        List<NguyenVong> ketQua = new ArrayList<>();

         switch (choice) {
            case 1:
                System.out.print("Nhập mã ngành cần tìm: ");
                String maNganh = sc.nextLine();
                ketQua = danhSachNguyenVong.stream()
                        .filter(nv -> nv.getMaNganh().equalsIgnoreCase(maNganh))
                        .collect(Collectors.toList());
                break;
            case 2:
                System.out.print("Nhập tên ngành cần tìm: ");
                String tenNganh = sc.nextLine().toLowerCase();
                ketQua = danhSachNguyenVong.stream()
                        .filter(nv -> nv.getTenNganh().toLowerCase().contains(tenNganh))
                        .collect(Collectors.toList());
                break;
            case 3:
                System.out.print("Nhập mã trường cần tìm: ");
                String maTruong = sc.nextLine();
                ketQua = danhSachNguyenVong.stream()
                        .filter(nv -> nv.getMaTruong().equalsIgnoreCase(maTruong))
                        .collect(Collectors.toList());
                break;
            case 4:
                System.out.print("Nhập khối xét tuyển ( A, A1, B, C, D): ");
                String khoi = sc.nextLine().toUpperCase();
                ketQua = danhSachNguyenVong.stream()
                        .filter(nv -> nv.getKhoiXetTuyen().name().equals(khoi))
                        .collect(Collectors.toList());
                break;
            case 5:
                System.out.print("Nhập điểm đạt điều kiện (>=): ");
                double diem = Double.parseDouble(sc.nextLine());
                ketQua = danhSachNguyenVong.stream()
                        .filter(nv -> nv.getDiemDatDieuKien() >= diem)
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }
        if (ketQua.isEmpty()) {
            System.out.println("No results found.");
        } else {
            System.out.println("Have found your aspiration:");
            for (NguyenVong nv : ketQua) {
                System.out.println(nv);
            }
        }
    }

    // Thêm phương thức static để lấy danh sách nguyện vọng
    public static List<NguyenVong> getDanhSachNguyenVong() {
        return danhSachNguyenVong;
    }
}
