package main;
import model.*;
import java.util.Scanner;


public class main {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    NguyenVongService nvservice = new NguyenVongService();
    ThiSinhService tsservice = new ThiSinhService();
    GiamThiService gtservice = new GiamThiService();
    ThongKeTuyenSinhService tkservice = new ThongKeTuyenSinhService(tsservice);
    int choice = -1;

    do {
            System.out.println("\n===== QUẢN LÝ TUYỂN SINH =====");
            // Menu hiện tại
            System.out.println("1. Thêm nguyện vọng");
            System.out.println("2. Cập nhật nguyện vọng");
            System.out.println("3. Xóa nguyện vọng");
            System.out.println("4. Hiển thị danh sách nguyện vọng");
            System.out.println("5. Tìm kiếm nguyện vọng");
            System.out.println("6. Thêm thí sinh");
            System.out.println("7. Cập nhật thí sinh");
            System.out.println("8. Xoá thí sinh");
            System.out.println("9. Thêm nguyện vọng cho thí sinh");
            System.out.println("10. Xóa nguyện vọng cho thí sinh");
            System.out.println("11. Tìm kiếm thí sinh");
            System.out.println("12. Hiển thị danh sách thí sinh");
            System.out.println("13. Thêm giám thị");
            System.out.println("14. Cập nhật giám thị");
            System.out.println("15. Xóa giám thị");
            System.out.println("16. Hiển thị danh sách giám thị");
            System.out.println("17. Hiển thị danh sách thí sinh trúng tuyển theo mã nguyện vọng");
            System.out.println("18. Hiển thị danh sách thí sinh trúng tuyển theo mã trường và mã ngành");
            System.out.println("19. Ghi danh sách thí sinh trúng tuyển theo mã nguyện vọng ra file");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Lựa chọn không hợp lệ. Vui lòng nhập một số.");
                continue;
            }

            switch (choice) {
                case 1:
                    nvservice.themNguyenVong();
                    break;
                case 2:
                    nvservice.capNhatNguyenVong();
                    break;
                case 3:
                    nvservice.xoaNguyenVong();
                    break;
                case 4:
                    nvservice.hienThiDanhSachNguyenVong();
                    break;
                case 5:
                    nvservice.timKiemNguyenVong();
                    break;
                case 6:
                    tsservice.themThiSinh();
                    break;
                case 7:
                    tsservice.capNhatThiSinh();
                    break;
                case 8:
                    tsservice.xoaThiSinh();
                    break;
                case 9:
                    tsservice.themNguyenVongChoThiSinh();
                    break;
                case 10:
                    tsservice.xoaNguyenVongChoThiSinh();
                    break;
                case 11:
                    tsservice.timKiemThiSinh();
                    break;
                case 12:
                    tsservice.hienThiDanhSachThiSinh();
                    break;
                case 13:
                    gtservice.themGiamThi();
                    break;
                case 14:
                    gtservice.capNhatGiamThi();
                    break;
                case 15:
                    gtservice.xoaGiamThi();
                    break;
                case 16:
                    gtservice.hienThiDanhSachGiamThi();
                    break;
                case 17:
                    tkservice.hienThiDanhSachTrungTuyen();
                    break;
                case 18:
                    tkservice.hienThiDanhSachTrungTuyenTheoMaTruongVaMaNganh();
                    break;
                case 19:
                    tkservice.ghiDanhSachTrungTuyenTheoNguyenVong();
                    break;
                case 0:
                    System.out.println("Đang thoát chương trình...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn từ 0-19.");
            }
        } while (choice != 0);

        sc.close();
    }
}