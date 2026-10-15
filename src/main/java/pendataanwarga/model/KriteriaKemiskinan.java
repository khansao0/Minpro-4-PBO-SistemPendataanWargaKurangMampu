/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pendataanwarga.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author qonitah
 */
@Embeddable
public class KriteriaKemiskinan {
    // atribut
    @Column(name = "id_kriteria")
    private String idKriteria;

    @Column(name = "jenis_pekerjaan")
    private String jenisPekerjaan;

    @Column(name = "pendapatan_bulanan")
    private double pendapatanBulanan;

    @Column(name = "status_rumah")
    private String statusRumah;

    public KriteriaKemiskinan() {}

    // Constructor
    public KriteriaKemiskinan(String idKriteria, String jenisPekerjaan, double pendapatanBulanan, String statusRumah) {
        this.idKriteria = idKriteria;
        this.jenisPekerjaan = jenisPekerjaan;
        setPendapatanBulanan(pendapatanBulanan);
        this.statusRumah = statusRumah;
    }
    
    // Getter & Setter
    public String getIdKriteria() {
        return idKriteria;
    }

    public void setIdKriteria(String idKriteria) {
        this.idKriteria = idKriteria;
    }

    public String getJenisPekerjaan() {
        return jenisPekerjaan;
    }

    public void setJenisPekerjaan(String jenisPekerjaan) {
        this.jenisPekerjaan = jenisPekerjaan;
    }

    public double getPendapatanBulanan() {
        return pendapatanBulanan;
    }

    public void setPendapatanBulanan(double pendapatanBulanan) {
        if (pendapatanBulanan >= 0) {
            this.pendapatanBulanan = pendapatanBulanan;
        } else {
            System.out.println("Pendapatan tidak boleh negatif!");
            this.pendapatanBulanan = 0;
        }
    }

    public String getStatusRumah() {
        return statusRumah;
    }

    public void setStatusRumah(String statusRumah) {
        this.statusRumah = statusRumah;
    }
}