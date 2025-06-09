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
        sb.append("║                   SUPERVISOR INFORMATION                   ║\n");
        sb.append("╠════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║  Supervisor ID: %-40d ║\n", maGiamThi));
        sb.append(String.format("║  Full Name:    %-40s ║\n", hoTen));
        sb.append(String.format("║  Gender:       %-40s ║\n", gioiTinh));
        sb.append(String.format("║  Birth Year:   %-40d ║\n", namSinh));
        sb.append(String.format("║  Hometown:     %-40s ║\n", queQuan));
        sb.append("╚════════════════════════════════════════════════════════════╝");
        return sb.toString();
    }
}