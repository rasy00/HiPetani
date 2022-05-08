CREATE TABLE `admin` (
  `id` int(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
  `username` varchar(20) NOT NULL,
  `passwords` varchar(100) NOT NULL,
  `kode_akses` varchar(10) NOT NULL
);

CREATE TABLE `kategori` (
  `id` int(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
  `admin` int(11) DEFAULT NULL,
  `nama` varchar(20) DEFAULT NULL,
  FOREIGN KEY (`admin`) REFERENCES `admin` (`id`)
);

CREATE TABLE `member` (
  `id` int(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
  `username` varchar(20) NOT NULL,
  `passwords` varchar(100) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `noHP` varchar(12) NOT NULL
);

CREATE TABLE `pertanyaan` (
   `id` int(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
  `judul` varchar(100) DEFAULT NULL,
  `body` varchar(150) DEFAULT NULL,
  `likes` int(11) DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  FOREIGN KEY (`author`) REFERENCES `member` (`id`)
);

CREATE TABLE `kontrakpnk` (
  `pertanyaan` int(11) DEFAULT NULL,
  `kategori` int(11) DEFAULT NULL,
  FOREIGN KEY (`pertanyaan`) REFERENCES `pertanyaan` (`id`),
  FOREIGN KEY (`kategori`) REFERENCES `kategori` (`id`)
);
