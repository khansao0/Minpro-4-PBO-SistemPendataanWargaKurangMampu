/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendataanwarga.view;

import java.util.Scanner;
import pendataanwarga.controller.WargaController;
import pendataanwarga.model.KriteriaKemiskinan;
import pendataanwarga.model.WargaDisabilitas;
import pendataanwarga.model.WargaLansia;

/**
 *
 * @author qonitah
 */
public class WargaView {
    private Scanner input = new Scanner(System.in);
    private WargaController controller;

    public WargaView(WargaController controller) {
        this.controller = controller;
    }

    private int inputAngka(String pesan) {
        while (true) {
            try {
                System.out.print(pesan);
                String strInput = input.nextLine();
                return Integer.parseInt(strInput);
            } catch (NumberFormatException e) {
                System.out.println("Format input salah! Harus berupa angka bulat.");
            }
        }
    }

    private double inputDesimal(String pesan) {
        while (true) {
            try {
                System.out.print(pesan);
                String strInput = input.nextLine();
                return Double.parseDouble(strInput);
            } catch (NumberFormatException e) {
                System.out.println("Format input salah! Harus berupa angka/desimal.");
            }
        }
    }

    public void tampilkanHeaderSystem() {
        System.out.println("=============================================");
        System.out.println(" ID Sistem       : " + controller.getIdData());
        System.out.println(" Tgl Pendataan   : " + controller.getTanggalPendataan());
        System.out.println(" Status Validasi : " + (controller.isStatusValidasi() ? "VALIDATED" : "PENDING"));
        System.out.println("=============================================");
    }

    public void jalankanMenu() {
        int pilihan = 0;
        do {
            tampilkanHeaderSystem();
            System.out.println("\n--------------------------------------------");
            System.out.println("  PENDATAAN WARGA KURANG MAMPU ");
            System.out.println("--------------------------------------------");
            System.out.println("1. Tambah Data Warga (Create)");
            System.out.println("2. Tampilkan Data Warga (Read)");
            System.out.println("3. Tampilkan Ringkasan Warga (Overloading)");
            System.out.println("4. Ubah Data Warga (Update)");
            System.out.println("5. Hapus Data Warga (Delete)");
            System.out.println("6. Keluar");

            pilihan = inputAngka("Pilih menu (1-6): ");

            switch (pilihan) {
                case 1:
                    menuTambahWarga();
                    break;
                case 2:
                    menuTampilkanWarga();
                    break;
                case 3:
                    menuTampilkanRingkasan();
                    break;
                case 4:
                    menuUbahWarga();
                    break;
                case 5:
                    menuHapusWarga();
                    break;
                case 6:
                    System.out.println("Terima kasih telah menggunakan program ini!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Masukkan angka 1 sampai 6.");
            }
        } while (pilihan != 6);
    }

    private void menuTambahWarga() {
        System.out.println("\n--- TAMBAH DATA WARGA ---");
        System.out.println("Pilih Kategori Warga:");
        System.out.println("1. Warga Lansia");
        System.out.println("2. Warga Disabilitas");
        int kat = inputAngka("Pilih Kategori (1-2): ");

        if (kat != 1 && kat != 2) {
            System.out.println("Pilihan kategori salah! Pendaftaran dibatalkan.");
            return;
        }

        System.out.print("Masukkan NIK            : ");
        String nik = input.nextLine();
        System.out.print("Masukkan Nama           : ");
        String nama = input.nextLine();
        System.out.print("Masukkan Alamat         : ");
        String alamat = input.nextLine();

        int tanggungan = -1;
        while (tanggungan < 0) {
            tanggungan = inputAngka("Masukkan Jml Tanggungan : ");
            if (tanggungan < 0) System.out.println("Jumlah tanggungan tidak boleh minus!");
        }

        double pendapatan = -1;
        while (pendapatan < 0) {
            pendapatan = inputDesimal("Masukkan Pendapatan/Bln : ");
            if (pendapatan < 0) System.out.println("Pendapatan tidak boleh minus!");
        }

        System.out.print("Masukkan Status Rumah   : ");
        String statusRumah = input.nextLine();
        System.out.print("Masukkan ID Kriteria    : ");
        String idKriteria = input.nextLine();
        System.out.print("Masukkan Jenis Pekerjaan: ");
        String jenisPekerjaan = input.nextLine();

        KriteriaKemiskinan kriteria = new KriteriaKemiskinan(idKriteria, jenisPekerjaan, pendapatan, statusRumah);

        boolean berhasil = false;
        if (kat == 1) {
            int umur = -1;
            while (umur < 60) {
                umur = inputAngka("Masukkan Umur (Min 60)  : ");
                if (umur < 60) System.out.println("Kategori lansia minimal berumur 60 tahun!");
            }
            System.out.print("Masukkan Kondisi Kesehatan: ");
            String kesehatan = input.nextLine();

            berhasil = controller.tambahWarga(new WargaLansia(nik, nama, alamat, tanggungan, kriteria, umur, kesehatan));
        } else if (kat == 2) {
            System.out.print("Masukkan Jenis Disabilitas : ");
            String disabilitas = input.nextLine();
            System.out.print("Kebutuhan Alat Bantu       : ");
            String alatBantu = input.nextLine();

            berhasil = controller.tambahWarga(new WargaDisabilitas(nik, nama, alamat, tanggungan, kriteria, disabilitas, alatBantu));
        }

        if (berhasil) {
            System.out.println("Yeyy Data Berhasil ditambahkan ke Database!");
        } else {
            System.out.println("Gagal menambahkan data ke Database.");
        }
    }

    private void menuTampilkanWarga() {
        System.out.println("\n--- DAFTAR WARGA KURANG MAMPU ---");
        if (controller.getDaftarWarga().isEmpty()) {
            System.out.println("Belum ada data warga di database.");
        } else {
            System.out.println("------------------------------------------");
            for (int i = 0; i < controller.getDaftarWarga().size(); i++) {
                System.out.println("Data ke-" + (i + 1));
                controller.getDaftarWarga().get(i).tampilkanInfo();
                System.out.println("------------------------------------------");
            }
        }
    }

    private void menuTampilkanRingkasan() {
        System.out.println("\n--- RINGKASAN DATA WARGA (OVERLOADING) ---");
        if (controller.getDaftarWarga().isEmpty()) {
            System.out.println("Belum ada data warga.");
        } else {
            for (int i = 0; i < controller.getDaftarWarga().size(); i++) {
                System.out.print((i + 1) + ". ");
                controller.getDaftarWarga().get(i).tampilkanInfo(true);
            }
        }
    }

    private void menuUbahWarga() {
        menuTampilkanWarga();
        if (controller.jumlahData() > 0) {
            int nomorData = inputAngka("Masukkan nomor data yang ingin diubah: ");
            int idxUpdate = nomorData - 1;

            if (idxUpdate >= 0 && idxUpdate < controller.jumlahData()) {
                System.out.print("Nama Baru             : ");
                String namaBaru = input.nextLine();
                System.out.print("Alamat Baru           : ");
                String alamatBaru = input.nextLine();

                int tanggunganBaru = -1;
                while (tanggunganBaru < 0) {
                    tanggunganBaru = inputAngka("Jml Tanggungan Baru   : ");
                    if (tanggunganBaru < 0) System.out.println("Jumlah tanggungan tidak boleh minus!");
                }

                double pendapatanBaru = -1;
                while (pendapatanBaru < 0) {
                    pendapatanBaru = inputDesimal("Pendapatan Baru/Bln   : ");
                    if (pendapatanBaru < 0) System.out.println("Pendapatan tidak boleh minus!");
                }

                System.out.print("Status Rumah Baru     : ");
                String statusRumahBaru = input.nextLine();

                boolean sukses = controller.ubahWarga(idxUpdate, namaBaru, alamatBaru, tanggunganBaru, pendapatanBaru, statusRumahBaru);
                if (sukses) {
                    System.out.println("Data warga berhasil diperbarui di Database!");
                } else {
                    System.out.println("Gagal memperbarui data.");
                }
            } else {
                System.out.println("Nomor data tidak valid.");
            }
        }
    }

    private void menuHapusWarga() {
        menuTampilkanWarga();
        if (controller.jumlahData() > 0) {
            int nomorHapus = inputAngka("Masukkan nomor data yang ingin dihapus: ");
            int idxHapus = nomorHapus - 1;

            if (idxHapus >= 0 && idxHapus < controller.jumlahData()) {
                System.out.print("Apakah anda yakin ingin menghapus data ini? (y/n): ");
                String konfirmasi = input.nextLine();

                if (konfirmasi.equalsIgnoreCase("y")) {
                    boolean suksesHapus = controller.hapusWarga(idxHapus);
                    if (suksesHapus) {
                        System.out.println("Data warga berhasil dihapus dari Database!");
                    } else {
                        System.out.println("Gagal menghapus data.");
                    }
                } else {
                    System.out.println("Penghapusan data dibatalkan.");
                }
            } else {
                System.out.println("Nomor data tidak valid.");
            }
        }
    }
}
