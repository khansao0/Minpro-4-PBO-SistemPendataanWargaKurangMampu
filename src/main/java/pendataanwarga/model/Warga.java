/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendataanwarga.model;

import jakarta.persistence.*;

/**
 *
 * @author qonitah
 */
@Entity
@Table(name = "warga")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "kategori", discriminatorType = DiscriminatorType.STRING)
public abstract class Warga implements ValidasiSyarat {
    @Id
    private String nik;
    private String nama;
    private String alamat;

    @Column(name = "jumlah_tanggungan")
    private int jumlahTanggungan;

    @Embedded
    private KriteriaKemiskinan kriteria;

    public Warga() {}

    // Constructor
    public Warga(String nik, String nama, String alamat, int jumlahTanggungan, KriteriaKemiskinan kriteria) {
        this.nik = nik;
        this.nama = nama;
        this.alamat = alamat;
        setJumlahTanggungan(jumlahTanggungan);
        this.kriteria = kriteria;
    }

    // getter dan setter
    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getJumlahTanggungan() {
        return jumlahTanggungan;
    }

    public void setJumlahTanggungan(int jumlahTanggungan) {
        if (jumlahTanggungan >= 0) {
            this.jumlahTanggungan = jumlahTanggungan;
        } else {
            System.out.println("Jumlah tanggungan tidak boleh negatif! Diset ke 0");
            this.jumlahTanggungan = 0;
        }
    }

    public KriteriaKemiskinan getKriteria() {
        return kriteria;
    }

    public void setKriteria(KriteriaKemiskinan kriteria) {
        this.kriteria = kriteria;
    }

    // Abstract Method
    public abstract String getKategori();

    // Polymorphism method overloading
    public void tampilkanInfo() {
        System.out.println("NIK             : " + getNik());
        System.out.println("Nama            : " + getNama());
        System.out.println("Alamat          : " + getAlamat());
        System.out.println("Jml Tanggungan  : " + getJumlahTanggungan());
        if (getKriteria() != null) {
            System.out.println("ID Kriteria     : " + getKriteria().getIdKriteria());
            System.out.println("Pekerjaan       : " + getKriteria().getJenisPekerjaan());
            System.out.println("Pendapatan/Bln  : Rp " + getKriteria().getPendapatanBulanan());
            System.out.println("Status Rumah    : " + getKriteria().getStatusRumah());
        }
        System.out.println("Kelayakan Bantuan: " + (cekKelayakanBantuan() ? "LAYAK" : "TIDAK LAYAK"));
        System.out.println("Catatan Bantuan : " + getCatatanBantuan());
    }

    // Polymorphism method overloading lagi
    public void tampilkanInfo(boolean ringkas) {
        if (ringkas) {
            System.out.println("[" + getKategori() + "] " + getNama() + " (NIK: " + getNik() + ") - Status Bantuan: " + (cekKelayakanBantuan() ? "LAYAK" : "TIDAK LAYAK"));
        } else {
            tampilkanInfo();
        }
    }
}