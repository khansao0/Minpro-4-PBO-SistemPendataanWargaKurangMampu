
USE db_pendataan_warga;

DROP TABLE IF EXISTS warga;

CREATE TABLE warga (
    nik VARCHAR(20) PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    alamat VARCHAR(255) NOT NULL,
    jumlah_tanggungan INT NOT NULL,
    id_kriteria VARCHAR(20) NOT NULL,
    jenis_pekerjaan VARCHAR(100) NOT NULL,
    pendapatan_bulanan DOUBLE NOT NULL,
    status_rumah VARCHAR(50) NOT NULL,
    kategori VARCHAR(50) NOT NULL,
    umur INT DEFAULT 0,
    kondisi_kesehatan VARCHAR(100) DEFAULT '-',
    jenis_disabilitas VARCHAR(100) DEFAULT '-',
    kebutuhan_alat_bantu VARCHAR(100) DEFAULT '-'
);

INSERT INTO warga (nik, nama, alamat, jumlah_tanggungan, id_kriteria, jenis_pekerjaan, pendapatan_bulanan, status_rumah, kategori, umur, kondisi_kesehatan, jenis_disabilitas, kebutuhan_alat_bantu) VALUES
('6472010101500001', 'Mbah Maimunah', 'Jl. Gerilya No. 12', 1, 'K01', 'Buruh Harian', 500000, 'Numpang', 'LANSIA', 68, 'Hipertensi', '-', '-'),
('6472010203950002', 'Rahmat Hidayat', 'Jl. Siradj Salman No. 04', 3, 'K02', 'Pedagang Keliling', 750000, 'Sewa', 'DISABILITAS', 0, '-', 'Tunawicara', 'Kursi Roda');