package model;

public class GiamThi {
    private static int counter = 1000;

    private int maGiamThi;
    private String hoTen;
    private Gender gioiTinh;
    private int namSinh;
    private String queQuan;


    public GiamThi(String hoTen, Gender gioiTinh, int namSinh, String queQuan) {
        this.maGiamThi = ++counter;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.queQuan = queQuan;
    }

    public int getMaGiamThi() {
        return maGiamThi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public Gender getGioiTinh() {
        return gioiTinh;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setGioiTinh(Gender gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔════════════════════════════════════════════════════════════╗\n");
        sb.append("║                   THÔNG TIN GIÁM THỊ                      ║\n");
        sb.append("╠════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║  Mã giám thị:  %-40d ║\n", maGiamThi));
        sb.append(String.format("║  Họ và tên:    %-40s ║\n", hoTen));
        sb.append(String.format("║  Giới tính:    %-40s ║\n", gioiTinh));
        sb.append(String.format("║  Năm sinh:     %-40d ║\n", namSinh));
        sb.append(String.format("║  Quê quán:     %-40s ║\n", queQuan));
        sb.append("╚════════════════════════════════════════════════════════════╝");
        return sb.toString();
    }
}