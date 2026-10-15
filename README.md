# Minpro-4-PBO-SistemPendataanWargaKurangMampu <br>  

# Judul : Sistem Pendataan Warga Kurang Mampu

---

## 1. Deskripsi Singkat Program  
Pada Mini Project 4 ini, saya melanjutkan pengembangan program pendataan warga kurang mampu penerima bantuan sosial dari Mini Project 3. Pada tahap ini, sistem telah diintegrasikan secara penuh dengan MySQL Database menggunakan arsitektur Data Access Object (DAO) dan PreparedStatement, serta mengimplementasikan Framework ORM (Hibernate / Jakarta Persistence - JPA) sebagai nilai tambah. Program ini memfasilitasi manajemen pendataan warga (kategori Lansia dan Disabilitas) dengan fitur CRUD lengkap (Create, Read, Update, Delete) yang kini tersimpan secara permanen di database.

---

## 2. Penjelasan Class & Atribut  
Pada project ini, strukturnya dibagi ke beberapa package dan class sesuai konsep MVC yang dikombinasikan dengan konfigurasi basis data:  

Pada Package pendataanwarga.config disini ada :  

- Koneksi
  * Method: getConnection()
- HibernateUtil
  * Method: getSessionFactory()

Pada Package pendataanwarga.model disini ada :  

- ValidasiSyarat (Interface)
  * Method: cekKelayakanBantuan(), getCatatanBantuan()

- Warga (superclass & abstract class) 
  * nik
  * nama
  * alamat
  * jumlahTanggungan
  * kriteria
 
- WargaLansia (subclass)
  * umur
  * kondisiKesehatan
 
- WargaDisabilitas (subclass)
  * jenisDisabilitas
  * kebutuhanAlatBantu
 
- KriteriaKemiskinan
  * idKriteria
  * jenisPekerjaan
  * pendapatanBulanan
  * statusRumah

- WargaDAO (Data Access Object)
  * Method: tambahWarga(), getAllWarga(), updateWarga(), deleteWarga()

Pada Package pendataanwarga.controller disini ada :  

- WargaController
  * idData
  * tanggalPendataan
  * statusValidasi
  * wargaDAO (Objek WargaDAO)  

Pada Package pendataanwarga.view disini ada :  

- WargaView
  * input (Scanner)
  * controller (Objek WargaController)

Pada Package pendataanwarga.main disini ada :  
- MainApp (Entry point)

---

## 3. Penjelasan Struktur Package
Struktur package pada Mini Project 4 ini disusun untuk memisahkan kelas sesuai perannya:
- pendataanwarga.config : Berisi kelas konfigurasi basis data JDBC dan Hibernate ORM.
- pendataanwarga.model : Berisi entitas data, interface, dan kelas DAO untuk akses basis data.
- pendataanwarga.controller : Berisi kelas pengontrol logika bisnis sistem.
- pendataanwarga.view : Berisi kelas antarmuka pengguna CLI dan penanganan input.
- pendataanwarga.main : Berisi kelas utama untuk menjalankan program.

---

## 4. Penjelasan Penerapan Encapsulation & Inheritance  
- Encapsulation
  * Semua atribut pada setiap class di dalam package model dan controller menggunakan access modifier private agar tidak bisa diakses secara langsung dari luar class.
  * Menggunakan method getter dan setter untuk mengakses dan mengubah nilai atribut.
  * Terdapat validasi data pada setter seperti setJumlahTanggungan (otomatis diset ke 0 jika minus), setPendapatanBulanan (diset ke 0 jika minus), dan setUmur pada WargaLansia (memastikan umur lansia minimal 60 tahun).

- Inheritance
  * Class Warga bertindak sebagai Abstract Superclass yang dipetakan ke tabel database menggunakan @Inheritance(strategy = InheritanceType.SINGLE_TABLE).
  * Class WargaLansia dan WargaDisabilitas bertindak sebagai Subclass yang mewarisi (extends) class Warga dengan pembeda @DiscriminatorValue.
  * Menggunakan super pada konstruktor subclass untuk meneruskan data ke superclass.

---

## 5. Penjelasan Penerapan Polymorphism & Abstraction  
- Abstraction
  * Class Warga dideklarasikan sebagai abstract class dengan anotasi @Entity, sehingga tidak bisa dibuat objeknya secara langsung tanpa melalui subclass-nya.
  * Memiliki abstract method getKategori() yang wajib di-override oleh WargaLansia dan WargaDisabilitas.
  * Mengimplementasikan interface ValidasiSyarat.

- Polymorphism Overriding
  * Meng-override method tampilkanInfo(), cekKelayakanBantuan(), dan getCatatanBantuan() pada subclass WargaLansia dan WargaDisabilitas.

- Polymorphism Overloading
  * Diterapkan pada method tampilkanInfo() di class Warga.
  * Versi pertama tampilkanInfo() menampilkan detail lengkap data warga.
  * Versi kedua tampilkanInfo(boolean ringkas) menampilkan ringkasan data warga dalam satu baris.

---

## 6. Penjelasan Penerapan JDBC  
- Koneksi Database: Menggunakan Koneksi.java dengan DriverManager.getConnection() untuk menghubungkan program Java ke MySQL Database db_pendataan_warga.
- Prepared Statement & DAO Pattern: Seluruh akses dan eksekusi kueri dipisahkan ke dalam kelas WargaDAO.java untuk menjamin keamanan dari SQL Injection.
- Dummy Data (.sql): Menyiapkan file db_pendataan_warga.sql yang berisi skema tabel dan dummy data awal (Mbah Maimunah & Rahmat Hidayat) agar saat fitur Read dijalankan data langsung tampil tanpa input manual dari awal.

---

## 7. Penjelasan Letak Penerapan Nilai Tambah  
Nilai tambah pada project Mini Project 4 ini adalah pengimplementasian Framework ORM (Hibernate / Jakarta Persistence - JPA):
- Konfigurasi Dependencies & File XML: Menambahkan dependency Hibernate pada pom.xml dan membuat file hibernate.cfg.xml pada folder src/main/resources untuk mengatur konfigurasi dialek MySQL dan pendaftaran entitas.
- Inisialisasi Factory (HibernateUtil.java): Diletakkan pada package pendataanwarga.config untuk membungkus SessionFactory Hibernate.
- Anotasi Entitas ORM: Diterapkan pada package pendataanwarga.model:
  - Anotasi @Embeddable pada KriteriaKemiskinan.java.
  - Anotasi @Entity, @Table, @Inheritance, dan @Embedded pada Warga.java.
  - Anotasi @Entity dan @DiscriminatorValue pada WargaLansia.java dan WargaDisabilitas.java.
- Akses Data Hibernate (WargaDAO.java): Pengelolaan operasi CRUD database menggunakan API Session Hibernate seperti session.persist(), session.createQuery(), session.merge(), dan session.remove() tanpa kueri SQL manual baris demi baris.

---

## 8. Dokumentasi Output Program & Implementasi Code  

### 1. Persiapan Database & File SQL
<img width="900" alt="WhatsApp Image 2026-10-15 at 9 22 07 PM" src="https://github.com/user-attachments/assets/22bf2a3c-98b2-47ca-af74-e6adcbe44c9a" /> <br>
 
Gambar 1: Pembuatan database db_pendataan_warga dan skema tabel warga beserta query dummy data awal pada phpMyAdmin/Laragon.

<img width="975" alt="image" src="https://github.com/user-attachments/assets/53578f9a-5b67-42ee-b652-1a4bd6098294" /> <br>

Gambar 2: File db_pendataan_warga.sql diletakkan di dalam project NetBeans yang menampung script penyiapan tabel dan data awal untuk syarat wajib tugas.

---

### 2. Build Status Maven
<img width="975" alt="image" src="https://github.com/user-attachments/assets/938a7686-1289-4548-a0fe-2c6e30b8d777" /> <br>

Gambar 3: Tampilan Output NetBeans saat menjalankan Clean and Build yang menghasilkan indikator BUILD SUCCESS, menandakan seluruh dependency JDBC dan Hibernate ORM terunduh dan terkompilasi tanpa error.

---

### 3. Tampilan Menu Utama & Read Data Awal Database (Read)
<img width="975" alt="image" src="https://github.com/user-attachments/assets/728f6c47-d3fc-4560-8c25-694c4e747bd3" /> <br>

Gambar 4: Tampilan Header Sistem dan Menu Utama program saat pertama kali dijalankan dari MainApp.java.

<img width="975" alt="image" src="https://github.com/user-attachments/assets/f7e31245-42af-4c47-bcc8-e96623a91112" /> <br>

Gambar 5: Eksekusi menu pilihan 2 (Read) yang secara otomatis membaca dan menampilkan dummy data awal dari database MySQL (Mbah Maimunah & Rahmat Hidayat) lengkap dengan perhitungan otomatis kelayakan bantuan.

---

### 4. Tambah Data Warga (Create)
<img width="775" alt="image" src="https://github.com/user-attachments/assets/258d15e1-52c4-4f45-9f97-5025d3ad89db" /> <br>

Gambar 6: Tampilan proses input data warga baru kategori Lansia (Kakek Farrel). Data berhasil diproses dan disimpan ke database dengan pesan "Yeyy Data Berhasil ditambahkan ke Database!".

<img width="553" height="309" alt="image" src="https://github.com/user-attachments/assets/5f3b2109-46d0-4a64-81e6-035691b742cc" /> <br>

Gambar 7: Verifikasi daftar warga setelah penambahan, memperlihatkan record ke-3 (Kakek Farrel) telah muncul pada tampilan CLI.

<img width="975" height="183" alt="image" src="https://github.com/user-attachments/assets/195eee41-1a27-4bba-944d-a0cbf15ae5af" /> <br>

Gambar 8: Bukti bahwa data ke-3 yang diinput via aplikasi telah tersimpan secara permanen pada tabel warga di database phpMyAdmin.

---

### 5. Tampilkan Ringkasan Warga (Overloading)
<img width="975" alt="image" src="https://github.com/user-attachments/assets/94c4da5a-a67a-48f5-9ec2-76620ab33982" /> <br>

Gambar 9: Menampilkan ringkasan data warga satu baris menggunakan pemanggilan method overloading tampilkanInfo(true) untuk memperlihatkan status bantuan sosial secara ringkas.

---

### 6. Ubah Data Warga (Update)
<img width="850" alt="image" src="https://github.com/user-attachments/assets/8639ecec-cb94-4f82-975b-5779329d9d5c" /> <br>

Gambar 10: Proses pengubahan data warga nomor urut 3 menjadi Nenek Asa. Sistem berhasil memperbarui record dengan notifikasi "Data warga berhasil diperbarui di Database!".

<img width="975" alt="image" src="https://github.com/user-attachments/assets/1b6c6bb9-f45b-4be8-8e39-75fb8a61523d" /> <br>
Gambar 11: Bukti pada phpMyAdmin bahwa record NIK 6472010304880003 nama dan alokasi pendapatannya telah berubah menjadi Nenek Asa secara real-time.

---

### 7. Hapus Data Warga (Delete)
<img width="788" alt="image" src="https://github.com/user-attachments/assets/bcb6fad5-0696-4dfe-b1c1-ecab0b45804e" /> <br>
Gambar 12: Eksekusi menu hapus data warga nomor urut 3 dengan memberikan konfirmasi 'y'. Data berhasil dihapus dari database.

<img width="975" alt="image" src="https://github.com/user-attachments/assets/989daaa4-8891-4926-bb10-d79117cbf54e" /> <br>
 
Gambar 13: Bukti pada database phpMyAdmin bahwa record data ke-3 telah terhapus dan tersisa 2 data awal kembali.

---

### 8. Keluar Program
<img width="975" alt="image" src="https://github.com/user-attachments/assets/d8ef2dd2-a0cc-491e-974a-da3c1c337785" /> <br>
Gambar 14: Menutup jalannya program melalui pilihan menu 6 dan menampilkan ucapan pesan terima kasih.

---

### 9. Bukti Pengetrapan Nilai Tambah (Framework ORM Hibernate)
<img width="975" alt="image" src="https://github.com/user-attachments/assets/9e2fa15f-efb8-4860-8cb7-50cf8bc8a671" /> <br>
Gambar 15: File konfigurasi hibernate.cfg.xml yang mendaftarkan koneksi driver MySQL, dialek Hibernate, serta mapping kelas entitas Warga, WargaLansia, dan WargaDisabilitas.

<img width="975" alt="image" src="https://github.com/user-attachments/assets/944324e1-e634-466a-b2ae-77e98aa76984" /> <br>
Gambar 16: Kelas HibernateUtil.java yang berfungsi membuat dan mengelola SessionFactory tunggal untuk kebutuhan sesi ORM Hibernate.

<img width="975" alt="image" src="https://github.com/user-attachments/assets/ac23be72-aa2d-4520-abba-c94a30c6d836" /> <br>
Gambar 17: Penerapan nilai tambah ORM pada metode tambahWarga() di WargaDAO.java menggunakan perintah session.persist(warga) untuk menyimpan objek Java langsung ke database.

<img width="975" alt="image" src="https://github.com/user-attachments/assets/7bacea96-9669-450b-b1c7-d01fa818f155" /> <br>
Gambar 18: Penerapan nilai tambah ORM pada metode getAllWarga() menggunakan HQL session.createQuery("FROM Warga", Warga.class) untuk mengambil seluruh data entitas.

<img width="975" alt="image" src="https://github.com/user-attachments/assets/85391dc8-8e70-492b-9626-3706213270f0" /> <br>
Gambar 19: Penerapan nilai tambah ORM pada metode updateWarga() menggunakan session.get() dan session.merge(target) untuk memperbarui record entitas di database.
