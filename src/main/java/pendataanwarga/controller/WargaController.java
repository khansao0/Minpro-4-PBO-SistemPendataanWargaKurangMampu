/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendataanwarga.controller;

import java.util.ArrayList;
import pendataanwarga.model.Warga;
import pendataanwarga.model.WargaDAO;

/**
 *
 * @author qonitah
 */
public class WargaController {
    private String idData;
    private String tanggalPendataan;
    private boolean statusValidasi;
    private WargaDAO wargaDAO;

    public WargaController(String idData, String tanggalPendataan, boolean statusValidasi) {
        this.idData = idData;
        this.tanggalPendataan = tanggalPendataan;
        this.statusValidasi = statusValidasi;
        this.wargaDAO = new WargaDAO();
    }

    public boolean tambahWarga(Warga warga) {
        return wargaDAO.tambahWarga(warga);
    }

    public ArrayList<Warga> getDaftarWarga() {
        return wargaDAO.getAllWarga();
    }

    public boolean ubahWarga(int index, String namaBaru, String alamatBaru, int tanggunganBaru, double pendapatanBaru, String statusRumahBaru) {
        ArrayList<Warga> daftar = wargaDAO.getAllWarga();
        if (index >= 0 && index < daftar.size()) {
            Warga target = daftar.get(index);
            return wargaDAO.updateWarga(target.getNik(), namaBaru, alamatBaru, tanggunganBaru, pendapatanBaru, statusRumahBaru);
        }
        return false;
    }

    public boolean hapusWarga(int index) {
        ArrayList<Warga> daftar = wargaDAO.getAllWarga();
        if (index >= 0 && index < daftar.size()) {
            Warga target = daftar.get(index);
            return wargaDAO.deleteWarga(target.getNik());
        }
        return false;
    }

    public int jumlahData() {
        return wargaDAO.getAllWarga().size();
    }

    public String getIdData() {
        return idData;
    }

    public void setIdData(String idData) {
        this.idData = idData;
    }

    public String getTanggalPendataan() {
        return tanggalPendataan;
    }

    public void setTanggalPendataan(String tanggalPendataan) {
        this.tanggalPendataan = tanggalPendataan;
    }

    public boolean isStatusValidasi() {
        return statusValidasi;
    }

    public void setStatusValidasi(boolean statusValidasi) {
        this.statusValidasi = statusValidasi;
    }
}