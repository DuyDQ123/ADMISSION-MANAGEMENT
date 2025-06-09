package model;
import java.util.*;

public class ThiSinh {
    private String soBaoDanh;
    private String hoTen;
    private Gender gioiTinh;
    private int namSinh;
    private String queQuan;
    private double diemThi;
    private double diemUuTien;
    private List<NguyenVong> danhSachNguyenVong;

    public ThiSinh(String soBaoDanh, String hoTen, Gender gioiTinh, int namSinh, String queQuan,
                   double diemThi, double diemUuTien) {
        this.soBaoDanh = soBaoDanh;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.queQuan = queQuan;
        this.diemThi = diemThi;
        this.diemUuTien = diemUuTien;
        this.danhSachNguyenVong = new ArrayList<>();
    }

    public void themNguyenVong(NguyenVong nv) {
        this.danhSachNguyenVong.add(nv);
    }

    public void xoaNguyenVong(int maNguyenVong) {
        this.danhSachNguyenVong.removeIf(nv -> nv.getMaNguyenVong() == maNguyenVong);
    }

    public String getSoBaoDanh() {
        return soBaoDanh;
    }

    public void setSoBaoDanh(String soBaoDanh) {
        this.soBaoDanh = soBaoDanh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Gender getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Gender gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public double getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(double diemThi) {
        this.diemThi = diemThi;
    }

    public double getDiemUuTien() {
        return diemUuTien;
    }

    public void setDiemUuTien(double diemUuTien) {
        this.diemUuTien = diemUuTien;
    }

    public List<NguyenVong> getDanhSachNguyenVong() {
        return danhSachNguyenVong;
    }

    public void setDanhSachNguyenVong(List<NguyenVong> danhSachNguyenVong) {
        this.danhSachNguyenVong = danhSachNguyenVong;
    }
@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("╔════════════════════════════════════════════════════════════╗\n");
    sb.append("║                     STUDENT INFORMATION                    ║\n");
    sb.append("╠════════════════════════════════════════════════════════════╣\n");
    sb.append(String.format("║  ID            : %-40s ║\n", soBaoDanh));
    sb.append(String.format("║  Full Name     : %-40s ║\n", hoTen));
    sb.append(String.format("║  Gender        : %-40s ║\n", gioiTinh));
    sb.append(String.format("║  Birth Year    : %-40d ║\n", namSinh));
    sb.append(String.format("║  Hometown      : %-40s ║\n", queQuan));
    sb.append(String.format("║  Test Score    : %-40.2f ║\n", diemThi));
    sb.append(String.format("║  Priority Score: %-40.2f ║\n", diemUuTien));
    sb.append("╚════════════════════════════════════════════════════════════╝\n");

    if (danhSachNguyenVong.isEmpty()) {
        sb.append("Candidate has not registered for any aspirations.\n");
    } else {
        sb.append("List of aspirations:\n");
        for (NguyenVong nv : danhSachNguyenVong) {
            sb.append(nv.toString()).append("\n");
        }
    }

    return sb.toString();
}

}
