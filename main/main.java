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
            System.out.println("\n===== ADMISSION MANAGEMENT =====");
            // Current menu
            System.out.println("1. Add aspiration");
            System.out.println("2. Update aspiration");
            System.out.println("3. Delete aspiration");
            System.out.println("4. Display aspirations list");
            System.out.println("5. Search aspiration");
            System.out.println("6. Add candidate");
            System.out.println("7. Update candidate");
            System.out.println("8. Delete candidate");
            System.out.println("9. Add aspiration for candidate");
            System.out.println("10. Delete aspiration for candidate");
            System.out.println("11. Search candidate");
            System.out.println("12. Display candidates list");
            System.out.println("13. Add supervisor");
            System.out.println("14. Update supervisor");
            System.out.println("15. Delete supervisor");
            System.out.println("16. Display supervisors list");
            System.out.println("17. Display list of admitted candidates by aspiration code");
            System.out.println("18. Display list of admitted candidates by school code and major code");
            System.out.println("19. Write list of admitted candidates by aspiration code to file");
            System.out.println("20. Read supervisors list from file");
            System.out.println("21. Read candidates list from file");
            System.out.println("0. Exit");
            System.out.print("Choose function: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                // nvservice là đối tượng quản lý các nguyện vọng và themNguyenVong là phương thức để thêm các nguyện vọng
                // vì không thể gọi trực tiếp phương thức NguyenVong() của lớp NguyenVongService, chúng ta cần tạo một đối tượng nvservice
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
                case 20:
                    gtservice.docDanhSachGiamThiTuFile();
                    break;
                case 21:
                    tsservice.docDanhSachThiSinhTuFile();
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose from 0-21.");
            }
        } while (choice != 0);

        sc.close();
    }
}