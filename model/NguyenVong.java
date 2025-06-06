package model;
public class NguyenVong {
    private static int autoIncrement = 1;
    private int maNguyenVong;
    private String maNganh;
    private String tenNganh;
    private String maTruong;
    private KhoiXetTuyen khoiXetTuyen;
    private double diemDatDieuKien;

    public NguyenVong(String maNganh, String tenNganh, String maTruong, KhoiXetTuyen khoiXetTuyen, double diemDatDieuKien) {
        this.maNguyenVong = autoIncrement++;
        this.maNganh = maNganh;
        this.tenNganh = tenNganh;
        this.maTruong = maTruong;
        this.khoiXetTuyen = khoiXetTuyen;
        this.diemDatDieuKien = diemDatDieuKien;
    }

    public static int getAutoIncrement() {
        return autoIncrement;
    }

    public static void setAutoIncrement(int autoIncrement) {
        NguyenVong.autoIncrement = autoIncrement;
    }

    public int getMaNguyenVong() {
        return maNguyenVong;
    }

    public void setMaNguyenVong(int maNguyenVong) {
        this.maNguyenVong = maNguyenVong;
    }

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }

    public String getMaTruong() {
        return maTruong;
    }

    public void setMaTruong(String maTruong) {
        this.maTruong = maTruong;
    }

    public KhoiXetTuyen getKhoiXetTuyen() {
        return khoiXetTuyen;
    }

    public void setKhoiXetTuyen(KhoiXetTuyen khoiXetTuyen) {
        this.khoiXetTuyen = khoiXetTuyen;
    }

    public double getDiemDatDieuKien() {
        return diemDatDieuKien;
    }

    public void setDiemDatDieuKien(double diemDatDieuKien) {
        this.diemDatDieuKien = diemDatDieuKien;
    }
    @Override
    
    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("╔═══════════════════════════════════════════════╗\n");
    sb.append(String.format("║  NGUYỆN VỌNG #%-3d                         ║\n", maNguyenVong));
    sb.append("╠═══════════════════════════════════════════════╣\n");
    sb.append(String.format("║  Mã ngành:     %-27s ║\n", maNganh));
    sb.append(String.format("║  Tên ngành:    %-27s ║\n", tenNganh));
    sb.append(String.format("║  Mã trường:    %-27s ║\n", maTruong));
    sb.append(String.format("║  Khối xét tuyển: %-25s ║\n", khoiXetTuyen));
    sb.append(String.format("║  Điểm chuẩn:   %-27.1f ║\n", diemDatDieuKien));
    sb.append("╚═══════════════════════════════════════════════╝");
    return sb.toString();
}

}
