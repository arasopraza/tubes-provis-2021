-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 05, 2021 at 07:42 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kemahasiswaan_10119001_10119013`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_barang`
--

CREATE TABLE `t_barang` (
  `IdBarang` varchar(30) NOT NULL,
  `NamaBarang` varchar(70) NOT NULL,
  `Stok` int(5) NOT NULL,
  `Satuan` varchar(10) NOT NULL,
  `HargaModal` double NOT NULL,
  `HargaJual` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_barang`
--

INSERT INTO `t_barang` (`IdBarang`, `NamaBarang`, `Stok`, `Satuan`, `HargaModal`, `HargaJual`) VALUES
('GARSERVITCPC', 'GARNIER LIGHT COMPLETE VITAMIN C SERUM 7,5ML', 12, 'pcs', 12000, 16200),
('QTLTEMCR60G', 'QTELA TEMPE CABE RAWIT 60G', 24, 'pcs', 5000, 6000),
('ROMAWFRCKLT5X20G', 'ROMA WAFER COKLAT 5X20GR', 12, 'pcs', 6000, 6200);

-- --------------------------------------------------------

--
-- Table structure for table `t_mahasiswa`
--

CREATE TABLE `t_mahasiswa` (
  `nim` varchar(8) NOT NULL,
  `nama` varchar(70) NOT NULL,
  `ttl` varchar(20) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_mahasiswa`
--

INSERT INTO `t_mahasiswa` (`nim`, `nama`, `ttl`, `tgl_lahir`, `alamat`) VALUES
('10105120', 'Hendra Herlambang', 'Bandung', '1988-05-21', 'Jl. Tubagus Ismail No 5'),
('10105121', 'Ratu Husna', 'Bandung', '1988-03-20', 'Jl. Cimandiri 15'),
('10105122', 'Angga Setiyadi', 'Bandung', '1988-10-30', 'Jl. Sekeloa 20');

-- --------------------------------------------------------

--
-- Table structure for table `t_mata_kuliah`
--

CREATE TABLE `t_mata_kuliah` (
  `kd_mk` varchar(8) NOT NULL,
  `nama_mk` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_mata_kuliah`
--

INSERT INTO `t_mata_kuliah` (`kd_mk`, `nama_mk`) VALUES
('IF20001', 'Pemrograman Dasar'),
('IF34348', 'Pemrograman Lanjut'),
('IF37325P', 'Komputer Grafik'),
('IF99191', 'Algoritma'),
('IF99192', 'Teorema Bahasa');

-- --------------------------------------------------------

--
-- Table structure for table `t_member`
--

CREATE TABLE `t_member` (
  `IdMember` int(15) NOT NULL,
  `NamaMember` varchar(70) NOT NULL,
  `NoTelpMember` varchar(13) NOT NULL,
  `Point` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_member`
--

INSERT INTO `t_member` (`IdMember`, `NamaMember`, `NoTelpMember`, `Point`) VALUES
(1, 'Fiona Avila Putri', '081122334455', 10),
(2, 'Arsy Opraza Akma', '089988776655', 10);

-- --------------------------------------------------------

--
-- Table structure for table `t_nilai`
--

CREATE TABLE `t_nilai` (
  `kd_nlai` int(11) NOT NULL,
  `nim` varchar(8) NOT NULL,
  `kd_mk` varchar(8) NOT NULL,
  `kehadiran` int(10) NOT NULL,
  `tugas_satu` int(10) NOT NULL,
  `tugas_dua` int(10) NOT NULL,
  `tugas_tiga` int(10) NOT NULL,
  `uts` int(10) NOT NULL,
  `uas` int(10) NOT NULL,
  `angkatan` int(11) NOT NULL,
  `nilai` double NOT NULL,
  `indeks` char(1) NOT NULL,
  `ket` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_nilai`
--

INSERT INTO `t_nilai` (`kd_nlai`, `nim`, `kd_mk`, `kehadiran`, `tugas_satu`, `tugas_dua`, `tugas_tiga`, `uts`, `uas`, `angkatan`, `nilai`, `indeks`, `ket`) VALUES
(3, '10105122', 'IF34348', 0, 0, 0, 0, 0, 0, 0, 90, 'A', 'Lulus');

-- --------------------------------------------------------

--
-- Table structure for table `t_user`
--

CREATE TABLE `t_user` (
  `Username` varchar(30) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Nama` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_user`
--

INSERT INTO `t_user` (`Username`, `Password`, `Nama`) VALUES
('admin', 'admin', 'admin1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_barang`
--
ALTER TABLE `t_barang`
  ADD PRIMARY KEY (`IdBarang`);

--
-- Indexes for table `t_mahasiswa`
--
ALTER TABLE `t_mahasiswa`
  ADD PRIMARY KEY (`nim`);

--
-- Indexes for table `t_mata_kuliah`
--
ALTER TABLE `t_mata_kuliah`
  ADD PRIMARY KEY (`kd_mk`);

--
-- Indexes for table `t_member`
--
ALTER TABLE `t_member`
  ADD PRIMARY KEY (`IdMember`);

--
-- Indexes for table `t_nilai`
--
ALTER TABLE `t_nilai`
  ADD PRIMARY KEY (`kd_nlai`),
  ADD KEY `nilai` (`nim`),
  ADD KEY `t_nilai` (`kd_mk`);

--
-- Indexes for table `t_user`
--
ALTER TABLE `t_user`
  ADD PRIMARY KEY (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_member`
--
ALTER TABLE `t_member`
  MODIFY `IdMember` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `t_nilai`
--
ALTER TABLE `t_nilai`
  MODIFY `kd_nlai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_nilai`
--
ALTER TABLE `t_nilai`
  ADD CONSTRAINT `nilai` FOREIGN KEY (`nim`) REFERENCES `t_mahasiswa` (`nim`),
  ADD CONSTRAINT `t_nilai` FOREIGN KEY (`kd_mk`) REFERENCES `t_mata_kuliah` (`kd_mk`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
