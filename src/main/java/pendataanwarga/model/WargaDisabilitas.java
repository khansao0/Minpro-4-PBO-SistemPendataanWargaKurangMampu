/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendataanwarga.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 *
 * @author qonitah
 */
@Entity
@DiscriminatorValue("DISABILITAS")
public class WargaDisabilitas extends Warga {
    // atribut
    @Column(name = "jenis_disabilitas")
    private String jenisDisabilitas;

    @Column(name = "kebutuhan_alat_bantu")
    private String kebutuhanAlatBantu;

    public WargaDisabilitas() {
        super();
    }
    
    // constructor subclass
    public WargaDisabilitas(String nik, String nama, String alamat, int jumlahTanggungan, KriteriaKemiskinan kriteria, String jenisDisabilitas, String kebutuhanAlatBantu) {
        // inheritance
        super(nik, nama, alamat, jumlahTanggungan, kriteria);
        setJenisDisabilitas(jenisDisabilitas);
        setKebutuhanAlatBantu(kebutuhanAlatBantu);
    }

    public String getJenisDisabilitas() {
        return jenisDisabilitas;
    }

    public void setJenisDisabilitas(String jenisDisabilitas) {
        this.jenisDisabilitas = jenisDisabilitas;
    }

    public String getKebutuhanAlatBantu() {
        return kebutuhanAlatBantu;
    }

    public void setKebutuhanAlatBantu(String kebutuhanAlatBantu) {
        this.kebutuhanAlatBantu = kebutuhanAlatBantu;
    }

    @Override
    public String getKategori() {
        return "WARGA DISABILITAS";
    }

    @Override
    public boolean cekKelayakanBantuan() {
        return getJumlahTanggungan() >= 2 || (getKriteria() != null && getKriteria().getPendapatanBulanan() <= 1200000);
    }

    @Override
    public String getCatatanBantuan() {
        return "Bantuan Alat Bantu Khusus: " + getKebutuhanAlatBantu();
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("[KATEGORI: " + getKategori() + "]");
        super.tampilkanInfo();
        System.out.println("Jenis Disabilitas: " + getJenisDisabilitas());
        System.out.println("Kebutuhan Alat   : " + getKebutuhanAlatBantu());
    }
}