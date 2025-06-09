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
        System.out.print("Enter aspiration code to update: ");
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
        System.out.println("Aspiration code not found.");
    } catch (NumberFormatException e) {
        System.out.println("Error: Aspiration code must be a number.");
    }
}


    // Xoá nguyện vọng
    public void xoaNguyenVong() {
    System.out.print("Enter aspiration code to delete: ");
    try {
        int maNguyenVong = Integer.parseInt(sc.nextLine());
        boolean removed = danhSachNguyenVong.removeIf(nv -> nv.getMaNguyenVong() == maNguyenVong);
        if (removed) {
            System.out.println("Aspiration successfully deleted.");
        } else {
            System.out.println("Aspiration code not found.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Error: Aspiration code must be a number.");
    }
}

    // Hiển thị danh sách
    public void hienThiDanhSachNguyenVong() {
        if (danhSachNguyenVong.isEmpty()) {
            System.out.println("Aspiration list is empty.");
        } else {
            System.out.println("\\n===== ASPIRATION LIST =====");
            for (NguyenVong nv : danhSachNguyenVong) {
                System.out.println(nv);
            }
        }
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
                System.out.print("Enter major code to search: ");
                String maNganh = sc.nextLine();
                ketQua = danhSachNguyenVong.stream()
                        .filter(nv -> nv.getMaNganh().equalsIgnoreCase(maNganh))
                        .collect(Collectors.toList());
                break;
            case 2:
                System.out.print("Enter major name to search: ");
                String tenNganh = sc.nextLine().toLowerCase();
                ketQua = danhSachNguyenVong.stream()
                        .filter(nv -> nv.getTenNganh().toLowerCase().contains(tenNganh))
                        .collect(Collectors.toList());
                break;
            case 3:
                System.out.print("Enter school code to search: ");
                String maTruong = sc.nextLine();
                ketQua = danhSachNguyenVong.stream()
                        .filter(nv -> nv.getMaTruong().equalsIgnoreCase(maTruong))
                        .collect(Collectors.toList());
                break;
            case 4:
                System.out.print("Enter admission group ( A, A1, B, C, D): ");
                String khoi = sc.nextLine().toUpperCase();
                ketQua = danhSachNguyenVong.stream()
                        .filter(nv -> nv.getKhoiXetTuyen().name().equals(khoi))
                        .collect(Collectors.toList());
                break;
            case 5:
                System.out.print("Enter required score (>=): ");
                double diem = Double.parseDouble(sc.nextLine());
                ketQua = danhSachNguyenVong.stream()
                        .filter(nv -> nv.getDiemDatDieuKien() >= diem)
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Invalid choice.");
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
