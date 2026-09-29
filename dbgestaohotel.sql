-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 29/09/2026 às 11:53
-- Versão do servidor: 8.4.7
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `dbgestaohotel`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `hospedagens_reservas`
--

CREATE TABLE `hospedagens_reservas` (
  `codigo_Reserva` int NOT NULL,
  `tipoQuartoReservado` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `numero_Quarto` int NOT NULL,
  `valorQuarto` decimal(7,2) NOT NULL,
  `dataReserva` date NOT NULL,
  `dataEntrada` date NOT NULL,
  `dataSaida` date NOT NULL,
  `cpf_Usuario` varchar(11) COLLATE utf8mb4_general_ci NOT NULL,
  `nomeUsuario` varchar(100) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `hospedagens_reservas`
--

INSERT INTO `hospedagens_reservas` (`codigo_Reserva`, `tipoQuartoReservado`, `numero_Quarto`, `valorQuarto`, `dataReserva`, `dataEntrada`, `dataSaida`, `cpf_Usuario`, `nomeUsuario`) VALUES
(116, 'Individual (Banheiro Coletivo)', 6, 70.00, '2024-12-07', '2024-12-17', '2024-12-11', '12345678923', 'teste'),
(117, 'Duplo (Banheiro Coletivo)', 1, 130.00, '2024-12-09', '2024-12-09', '2024-12-09', '12345678923', 'teste');

-- --------------------------------------------------------

--
-- Estrutura para tabela `hospedes`
--

CREATE TABLE `hospedes` (
  `cpf_hospede` varchar(11) COLLATE utf8mb4_general_ci NOT NULL,
  `nomeCliente` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `sobrenomeCliente` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `empresa` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `cpf_Usuario` varchar(11) COLLATE utf8mb4_general_ci NOT NULL,
  `codigo_Reserva` int NOT NULL,
  `nomeUsuario` varchar(50) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `hospedes`
--

INSERT INTO `hospedes` (`cpf_hospede`, `nomeCliente`, `sobrenomeCliente`, `empresa`, `cpf_Usuario`, `codigo_Reserva`, `nomeUsuario`) VALUES
('22222222222', 'Geronimo', 'Perez', 'Perezlandia', '12345678923', 117, 'teste'),
('99999999999', 'fernando', 'Munhoz', 'Sei la', '12345678923', 116, 'teste');

-- --------------------------------------------------------

--
-- Estrutura para tabela `produtos`
--

CREATE TABLE `produtos` (
  `codigo_Produto` int NOT NULL,
  `nomeProduto` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `quantidade` int NOT NULL,
  `precoCompra` decimal(7,2) NOT NULL,
  `precoVenda` decimal(7,2) NOT NULL,
  `marca` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `cpf_Usuario` varchar(11) COLLATE utf8mb4_general_ci NOT NULL,
  `nomeUsuario` varchar(100) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produtos`
--

INSERT INTO `produtos` (`codigo_Produto`, `nomeProduto`, `quantidade`, `precoCompra`, `precoVenda`, `marca`, `cpf_Usuario`, `nomeUsuario`) VALUES
(2, 'Agua', 18, 4.00, 8.00, 'Agua fresca', '12345678923', 'teste'),
(4, 'pedras', 18, 5.00, 10.00, 'pedraboas', '12345678923', 'teste'),
(5, 'maçã', 2, 5.00, 5.00, 'boavida', '12345678923', 'teste'),
(7, 'batatas', 2, 5.00, 10.00, 'batatabonit', '12345678923', 'teste'),
(8, 'peixe', 2, 5.00, 10.00, 'peixebom', '12345678923', 'teste'),
(10, 'uvas-passas', 2, 4.00, 6.00, 'uvamelhor', '12345678923', 'teste'),
(13, 'Frango', 343, 345.00, 36.00, 'Frangobom', '12345678923', 'teste'),
(16, 'Pera', 3, 5.00, 15.00, 'peragostosa', '12345678923', 'teste'),
(17, 'Morango', 23, 50.00, 345.00, 'dfhfd', '12345678923', 'teste'),
(18, 'Acai', 500, 123.00, 12.00, 'mmmmm', '12345678923', 'teste'),
(19, 'Péssego', 250, 8.00, 12.00, 'Pessego Sabor', '12345678923', 'teste');

-- --------------------------------------------------------

--
-- Estrutura para tabela `quartos`
--

CREATE TABLE `quartos` (
  `numero_Quarto` int NOT NULL,
  `tipoQuarto` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `quantidadeCamas` int NOT NULL,
  `valorDiaria` decimal(5,2) NOT NULL,
  `estatus` enum('Disponível','Ocupado','Indisponível') COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Disponível'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `quartos`
--

INSERT INTO `quartos` (`numero_Quarto`, `tipoQuarto`, `quantidadeCamas`, `valorDiaria`, `estatus`) VALUES
(1, 'Duplo (Banheiro Coletivo)', 2, 130.00, 'Ocupado'),
(2, 'Duplo (Banheiro Coletivo)', 2, 130.00, 'Indisponível'),
(3, 'Duplo (Banheiro Coletivo)', 2, 130.00, 'Disponível'),
(4, 'Individual (Banheiro Coletivo)', 2, 130.00, 'Indisponível'),
(5, 'Individual (Banheiro Coletivo)', 2, 130.00, 'Indisponível'),
(6, 'Individual (Banheiro Coletivo)', 1, 70.00, 'Ocupado'),
(7, 'Duplo (Banheiro Coletivo)', 2, 130.00, 'Disponível'),
(8, 'Individual (Banheiro Coletivo)', 1, 70.00, 'Disponível'),
(9, 'Individual (Banheiro Coletivo)', 1, 70.00, 'Disponível'),
(10, 'Individual (Banheiro Coletivo)', 1, 70.00, 'Disponível'),
(11, 'Duplo (Banheiro Coletivo)', 2, 130.00, 'Disponível'),
(12, 'Individual (Banheiro Privado)', 1, 130.00, 'Indisponível'),
(13, 'Duplo (Banheiro Coletivo)', 2, 130.00, 'Disponível'),
(14, 'Individual (Banheiro Coletivo)', 1, 70.00, 'Disponível'),
(15, 'Triplo (Banheiro Privado)', 3, 230.00, 'Disponível'),
(16, 'Individual (Banheiro Privado)', 1, 100.00, 'Disponível'),
(17, 'Individual (Banheiro Privado)', 1, 100.00, 'Disponível'),
(18, 'Individual (Banheiro Privado)', 1, 100.00, 'Disponível'),
(19, 'Casal', 1, 170.00, 'Disponível'),
(20, 'Individual (Banheiro Privado)', 1, 100.00, 'Disponível'),
(21, 'Duplo (Banheiro coletivo)', 2, 130.00, 'Indisponível'),
(22, 'Casal', 1, 170.00, 'Disponível'),
(23, 'Duplo (Banheiro Coletivo)', 2, 130.00, 'Disponível'),
(24, 'Individual (Banheiro Coletivo)', 1, 70.00, 'Disponível'),
(25, 'Triplo (Banheiro Privado)', 3, 230.00, 'Disponível'),
(26, 'Individual (Banheiro Privado)', 1, 100.00, 'Disponível'),
(27, 'Individual (Banheiro Privado)', 1, 100.00, 'Disponível'),
(28, 'Individual (Banheiro Privado)', 1, 100.00, 'Disponível'),
(29, 'Individual (Banheiro Privado)', 1, 100.00, 'Disponível'),
(30, 'Casal', 1, 170.00, 'Disponível'),
(31, 'Triplo (Banheiro Privado)', 3, 230.00, 'Disponível'),
(32, 'Duplo (Banheiro Coletivo)', 2, 130.00, 'Indisponível');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `cpf_Usuario` varchar(11) COLLATE utf8mb4_general_ci NOT NULL,
  `nomeUsuario` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `sobrenomeUsuario` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `dataNascimento` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `nivelAcesso` enum('Atendente','Administrador') COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `celular` varchar(14) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `nomeLogin` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `senha` varchar(60) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuario`
--

INSERT INTO `usuario` (`cpf_Usuario`, `nomeUsuario`, `sobrenomeUsuario`, `dataNascimento`, `nivelAcesso`, `email`, `celular`, `nomeLogin`, `senha`) VALUES
('01234512345', 'poi', 'poi', '2001-11-11', 'Atendente', 'poi@mail.com', '09109881234', 'poi', '$2a$10$EvES1.FzpfzW0S8.bjzlDO7PiSUK.w5yX2TeuGhLLAQH9/1qkE1c2'),
('08745967', 'papa', 'papa', '2000-12-05', 'Atendente', 'papa@mail.com', '3458392865', 'papa', '$2a$10$un8snJZskXvB7KjMTIAHCOuFioSysdLT6/53Mgi5mO1d4YazTw1Cq'),
('12121212235', 'hhh', 'hhhh', '2009-06-02', 'Atendente', 'jksdjv@gmail.com', '(56)56865-7956', 'gdfg', '$2a$10$PnoVRPiKp3NOy.yUL8ONDuN440.kPCLvVq6WEKhhWqQi.oVraM42.'),
('123321', 'asd', 'azx', '2024-09-02', 'Administrador', 'qas@mail.com', '0916345', 'ert', '$2a$10$l5F.nyJ0ixXL3Ydnw7JYQenktNVtxph3uX6VhyDuEHEzKBZ4gQVb6'),
('12332112322', 'qazx', 'qwer', '2024-09-05', 'Administrador', 'erty@mail.com', '6721324354', 'pomp', '$2a$10$Wam.XKuOHybFUbh0JwE6D.OJDHbktAl4/2EyMk2vmxtSC7ZlQv3w6'),
('1234', 'Karli', 'Munoz', '2024-09-01', 'Administrador', 'jesusvzlanz@gmail.com', '45123456789', 'Karli', '$2a$10$9O9PxaJrKBRDYjM23cAJQevTYyCiRYVho.qqK79quZEVPBBIxUzNe'),
('12345', 'cad', 'asd', '2024-09-03', 'Administrador', 'alfma@mail.com', '45123456', 'cam', '$2a$10$u32xt0RHClegypBY006jVOyOy4.2eL7w7i0G5qV5cXxBPp/Ara/Je'),
('12345678900', 'toro', 'toro', '2000-12-12', 'Administrador', 'toro@mail.com', '(44)44444-4440', 'toro', '$2a$10$QJc2bvv100xPRfuMJXO/.OavXLgVec9SrTMuRnJeynpuYP35yR1Hu'),
('12345678923', 'Pedro', 'Perez', '2000-10-31', 'Administrador', 'pedros@mail.com', '1234567890', 'teste', '$2a$10$0un1QGO6V0EWMlE4b2WRFOjVOxJVF.KUipiJoQuX/lRk9UaBivC6W'),
('12345678924', 'mentes', 'abiertas', '1990-04-04', 'Atendente', 'mentes@mail.com', '(44)44444-4444', 'mentes', '$2a$10$jLakLg4xuSusP3YIwCgUiOOvuApBcmLxklYE0VKkJs00hQbS1M8Nq'),
('21212121212', 'quentim', 'tarantino', '2000-03-30', 'Atendente', 'quentim@zohomail.com', '(21)21212-1212', 'quentim', '$2a$10$jt3HRwTaOaUtaii8KW/rZ.zzdNFckV4S8zzKr.urfbE42Z4PlHUWy'),
('212424424', 'Karli1', 'Munoz1', '1987-02-01', 'Atendente', 'jesusvznz@gmail.com', '45123400', 'huk', '$2a$10$21Uiati.LCgjuP8lYNyRUeSAjwUj7FS2BSgHh9IS7onabJjSqijh6'),
('22222222222', 'weg', 'df', '1990-02-02', 'Administrador', 'df@mail.com', '(22)22222-2222', 'dr', '$2a$10$e8lf857lod/xnJ4h.dS7M.P.LeepcJBFwz75PHSg.mUlXUxUzTI82'),
('22233311121', 'asw', 'asw', '1990-12-21', 'Atendente', 'asw@mail.com', '67654345678', 'asw', '$2a$10$ijUFgahBOhros1zrTYD0h.rV3CF31KVqIKVtT4ksbOaNi0QybUQV2'),
('23232323232', 'ret', 'ter', '2000-10-31', 'Atendente', 'quentim@yahoo.com', '(23)23232-3232', 'retter', '$2a$10$s.y4kiuJY4QzpwApW8yjI.r7/dwkneMa.J4IBI3nkIDwpr4ggbvQm'),
('33333333333', 'kiko', 'kiko', '1990-03-03', 'Administrador', 'kiko@yahoo.com', '(33)33333-3333', 'kiko', '$2a$10$DabhyzpkHJmONkzVALGcKe8bWKsc8xrkcCVttNVTWskewNesF91gC'),
('34542654645', 'rthrh', 'dgdfhdf', '2000-12-12', 'Administrador', 'jesusvzlaz@gmail.com', '35345345324', 'dfgdsfs', '$2a$10$.2RqWBf6q/iGQWAv1DVmGeShp//0D1cmM8QxJ0Y2A9atYbCmThMCy'),
('34563465', 'Karli2', 'Munoz2', '1990-07-07', 'Atendente', 'sdfhz@gmail.com', '4554686', 'adfggfh', '$2a$10$2IAakQlQMhq3sdOTypcjVe9jfZjwVLX9f61MT0AKfrh.bxGw7n3ua'),
('34634456451', 'dgdfds', 'dfsdfhsd', '2008-07-06', 'Atendente', 'adfdsfh@gmail.com', '45754671212', 'per', '$2a$10$eyBDoKHZFHUksJZgKSdw5.7XiWbw7mG3KAF.CbHwwwScmNkmo/NBq'),
('34645645765', '345', '4632', '2000-05-05', 'Atendente', 'dfgdfgdf@mail.com', '(65)78653-7542', 'ere', '$2a$10$lYF1FwWmcCckhAxxWXek6O1XGuq4QofympeT5.E9gEjlGYRnBByom'),
('3465868', 'rytery', 'rtutyu', '2000-08-08', 'Atendente', 'kjhkjh@mail.com', '68568654', 'fdshfsh', '$2a$10$c/.kvpz/doCybPBqiHuIAON5mlSOAv14ZHM1Qg7DFNPvk.xUWGuwO'),
('4363546', 'DFHSDFH', 'DFHA', '2000-01-09', 'Atendente', 'DGSDFHGDS@yahoo.com', '464365', 'DFHFDH', '$2a$10$fhRtfKcgX6qfAej/DOKbRe08grhBjzgyLWkYwe8xa1Xcsyn4erdye'),
('437546', 'dhdfj', 'fdhfgdhdf', '2009-06-05', 'Administrador', '45645@mail.com', '4575467', 'dfgsdfgs', '$2a$10$VAe/VSbgE8nhs9FAMsxbI.JokiYzIWrLPrrsV1fv4a8Enleg4b2sm'),
('44335544331', 'tyu', 'ret', '1990-12-15', 'Atendente', 'tyu@mail.com', '11667788551', 'tyu', '$2a$10$ASbPYPEmHqsR/QRkrfd/l.j1KlKsHHklzBhxRXfzML3ir5OW2d8R2'),
('44545342312', 'timon', 'timo', '2010-01-01', 'Atendente', 'timo@mail.com', '71545454323', 'timo', '$2a$10$6Kw.NKJ1F1vgDBBxtzwEH.zoBEWvA1UZSt7cN5F3YMVNwUndjKjgm'),
('44545342319', 'tivo', 'tivo', '2009-01-01', 'Administrador', 'tivo@mail.com', '71545454321', 'tivo', '$2a$10$ZOfc3hbZ7dHsc14iXw6v4OI.XmfMIDrSIUIrl8Q.vvQdmKsduHHq6'),
('45568679485', 'teste', 'dfgdf', '2000-05-02', 'Administrador', 'dfgdf@gmx.com', '(34)65676-7587', 'ddf', '$2a$10$zx1oLo8RnQievJwYVUvh5eHHIiOh/MXJp5zzIU55iyDUyO75zpqO6'),
('456264246', 'dnfg', 'ngfd', '2000-04-04', 'Atendente', 'hhhh@mail.com', '3452354235', 'hhhhh', '$2a$10$HAFJko7LB7IZLWUp5nhbQeQyN3gXu..49WeSn0OsKs84niolLTSuC'),
('45675865784', 'dfghfd', 'asdbvfb', '1000-03-12', 'Administrador', 'gdgsdfhd@mail.com', '(56)86579-5760', 'tyf', '$2a$10$4qsJAZ62Mz7coi02PB8sDuH7oN/nl10VzHrBxvUh.MKLlPc7c.epy'),
('55122132545', 'dede', 'dede', '1990-11-11', 'Atendente', 'dede@mail.com', '88121545465', 'dede', '$2a$10$AnCpu1.Toj0V1GGsnPljOuYhoI.VDdUMyIcfPMA92ArV30g3E.876'),
('56798780980', 'mens', 'mansd', '1978-02-23', 'Administrador', 'mesnas@mail.com', '(80)89578-9678', 'sdfgdf', '$2a$10$/8S2Y5PK7aPC2Xg9SuC1Iu0veN/3YxyIeMsRkLNsIy5oy1aINXj1q'),
('5686578', 'JKHJK', 'WRTGA', '2007-02-19', 'Atendente', '3453453@mail.com', '676758', 'dfsdf', '$2a$10$ZOZYt3XShTaLkc1ZhQ4UJexLeNK8.eJWvmOX8TEVjyfOL87U9AtQK'),
('70125454651', 'pepe', 'mujica', '2024-09-08', 'Atendente', 'pepe@mail.com', '89123432198', 'pepe', '$2a$10$NttiNu2r3BUn1knkcvs1Oul.Jlq05n4kRbKDtw.MkMLW5LcSAIlOW'),
('77213209876', 'martin', 'prado', '2024-09-06', 'Administrador', 'martin@mail.com', '33212145654', 'martin@mail.com', '$2a$10$favc6t/BeDmEpyDBMTSfquIy4hY8vwNchXZ40Atb.I69WT64Ho0pm'),
('789687', 'lola', 'lopez', '2000-05-21', 'Atendente', 'lola@mail.com', '5686579567', 'lola', '$2a$10$ajV8j76DWqWdae9Qhj0b/O.4vu17PuYzby1SUAWzRdMaJ4xUgthtW'),
('87123456734', 'joao', 'martinez', '1980-11-30', 'Atendente', 'joao@mail.com', '6712345678', 'joao', '$2a$10$sXHHl2PVVFTVzUXCv.OQ4ujAtRFpxJ3ChUdfAoLcvfTJHaB45N79G'),
('88454545453', 'erver', 'asfbv', '1988-12-12', 'Atendente', 'wrngfn@mail.com', '77444365654', 'ter', '$2a$10$8LrYWKb7NDtCUIIDJMGMOeDe/UOFtnjDXo9Us6me6EWNE7IhGdG0S'),
('97598256895', 'wxmm', 'dfgdsfh', '1999-08-08', 'Administrador', 'asgdf@hotmail.com', '(54)82547-2457', 'asg', '$2a$10$24z8eGEGi9QMIlwkUe8taO/s3Libv9JnZT/bEA2Wd.MWTI4wKVX.m'),
('adfgdfsg', 'dsfgdsfg', 'asdgdag', '2000-06-06', 'Atendente', 'sadfasd@mail.com', '4565463465', 'ghdfgh', '$2a$10$8nDFdODpVQbXeq0S37M9au2/kxFUJY97l.lQDNAFicqZ.ufvW0PQW'),
('fghfdgh', 'sfgh', 'fghdfg', '1988-06-05', 'Atendente', 'ererg@mail.com', '457456765', 'fghdfgh', '$2a$10$1xCt1T0NrIuKEzTz6mSLRu7ZQF02mXH7RGp5xBVKNyNhC9WJYp1De'),
('sdfhsdf', 'dfhbfdh', 'sdfh', '2000-02-23', 'Administrador', 'dfhsdfhgf@mail.com', 'dfhfdghfg', 'dfgafhsdf', '$2a$10$ETU56pRN0eVdQO/sAcUxzuB6zmzdHEq0Ev3ohLCRUfIbGK4P02kiW');

-- --------------------------------------------------------

--
-- Estrutura para tabela `vendas`
--

CREATE TABLE `vendas` (
  `codigo_Venda` int NOT NULL,
  `codigo_Produto` int NOT NULL,
  `valorVenda` decimal(7,2) NOT NULL,
  `dataVenda` date NOT NULL,
  `nomeUsuario` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `cpf_Usuario` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `vendas`
--

INSERT INTO `vendas` (`codigo_Venda`, `codigo_Produto`, `valorVenda`, `dataVenda`, `nomeUsuario`, `cpf_Usuario`) VALUES
(6, 4, 20.00, '2024-12-09', 'teste', NULL),
(7, 4, 20.00, '2024-12-09', 'teste', NULL),
(8, 4, 20.00, '2024-12-09', 'teste', NULL),
(9, 4, 20.00, '2024-12-09', 'teste', NULL),
(10, 4, 20.00, '2024-12-09', 'teste', NULL),
(11, 4, 10.00, '2024-12-09', 'teste', NULL),
(12, 4, 20.00, '2024-12-09', 'teste', NULL),
(13, 4, 160.00, '2024-12-09', 'teste', NULL),
(14, 4, 20.00, '2024-12-09', 'teste', NULL),
(15, 4, 50.00, '2024-12-09', 'teste', NULL),
(16, 4, 10.00, '2024-12-09', 'teste', NULL),
(17, 4, 10.00, '2024-12-09', 'teste', NULL),
(18, 4, 10.00, '2024-12-09', 'teste', NULL),
(21, 4, 10.00, '2024-12-09', 'teste', NULL),
(22, 4, 10.00, '2024-12-09', 'teste', NULL),
(23, 4, 10.00, '2024-12-09', 'teste', NULL),
(24, 4, 20.00, '2024-12-09', 'teste', NULL),
(25, 4, 10.00, '2024-12-09', 'teste', NULL),
(26, 4, 10.00, '2024-12-09', 'teste', NULL),
(27, 4, 10.00, '2024-12-09', 'teste', NULL),
(28, 4, 10.00, '2024-12-09', 'teste', NULL),
(29, 4, 10.00, '2024-12-10', 'teste', NULL),
(30, 4, 10.00, '2024-12-10', 'teste', NULL),
(31, 4, 20.00, '2024-12-10', 'teste', NULL),
(32, 4, 20.00, '2024-12-10', 'teste', NULL),
(33, 4, 20.00, '2024-12-10', 'teste', NULL),
(34, 4, 20.00, '2024-12-10', 'teste', NULL),
(35, 4, 10.00, '2024-12-10', 'teste', NULL),
(36, 4, 10.00, '2024-12-10', 'teste', NULL),
(37, 2, 16.00, '2024-12-10', 'teste', NULL),
(38, 13, 72.00, '2024-12-12', 'teste', NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `vendas_itens`
--

CREATE TABLE `vendas_itens` (
  `codigo_venda_itens` int NOT NULL,
  `codigo_Venda` int NOT NULL,
  `codigo_Produto` int NOT NULL,
  `nomeProduto` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `quantidade_Itens` int NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `valorVenda` decimal(10,2) NOT NULL,
  `nomeUsuario` varchar(100) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `vendas_itens`
--

INSERT INTO `vendas_itens` (`codigo_venda_itens`, `codigo_Venda`, `codigo_Produto`, `nomeProduto`, `quantidade_Itens`, `subtotal`, `valorVenda`, `nomeUsuario`) VALUES
(4, 6, 4, 'pedras', 2, 20.00, 0.00, 'teste'),
(5, 7, 4, 'pedras', 2, 20.00, 0.00, 'teste'),
(6, 8, 4, 'pedras', 2, 20.00, 0.00, 'teste'),
(7, 9, 4, 'pedras', 2, 20.00, 0.00, 'teste'),
(8, 10, 4, 'pedras', 2, 20.00, 0.00, 'teste'),
(9, 11, 4, 'pedras', 1, 10.00, 0.00, 'teste'),
(10, 12, 4, 'pedras', 2, 20.00, 0.00, 'teste'),
(11, 13, 4, 'pedras', 16, 160.00, 0.00, 'teste'),
(12, 14, 4, 'pedras', 2, 20.00, 0.00, 'teste'),
(13, 15, 4, 'pedras', 5, 50.00, 0.00, 'teste'),
(14, 16, 4, 'pedras', 1, 10.00, 0.00, 'teste'),
(15, 17, 4, 'pedras', 1, 10.00, 0.00, 'teste'),
(16, 18, 4, 'pedras', 1, 10.00, 0.00, 'teste'),
(19, 21, 4, 'pedras', 1, 10.00, 0.00, 'teste'),
(20, 22, 4, 'pedras', 1, 10.00, 0.00, 'teste'),
(21, 23, 4, 'pedras', 1, 10.00, 0.00, 'teste'),
(22, 24, 4, 'pedras', 2, 20.00, 0.00, 'teste'),
(23, 25, 4, 'pedras', 1, 10.00, 0.00, 'teste'),
(24, 26, 4, 'pedras', 1, 10.00, 0.00, 'teste'),
(25, 27, 4, 'pedras', 1, 10.00, 0.00, 'teste'),
(26, 28, 4, 'pedras', 1, 10.00, 0.00, 'teste'),
(27, 29, 4, 'pedras', 1, 10.00, 0.00, 'teste'),
(28, 30, 4, 'pedras', 1, 10.00, 0.00, 'teste'),
(29, 31, 4, 'pedras', 2, 20.00, 0.00, 'teste'),
(30, 32, 4, 'pedras', 2, 20.00, 0.00, 'teste'),
(31, 33, 4, 'pedras', 2, 20.00, 0.00, 'teste'),
(32, 34, 4, 'pedras', 2, 20.00, 0.00, 'teste'),
(33, 35, 4, 'pedras', 1, 10.00, 0.00, 'teste'),
(34, 36, 4, 'pedras', 1, 10.00, 0.00, 'teste'),
(35, 37, 2, 'Agua', 2, 16.00, 0.00, 'teste'),
(36, 38, 13, 'Frango', 2, 72.00, 0.00, 'teste');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `hospedagens_reservas`
--
ALTER TABLE `hospedagens_reservas`
  ADD PRIMARY KEY (`codigo_Reserva`),
  ADD KEY `cpf_Usuario` (`cpf_Usuario`),
  ADD KEY `fk_numero_Quarto` (`numero_Quarto`);

--
-- Índices de tabela `hospedes`
--
ALTER TABLE `hospedes`
  ADD PRIMARY KEY (`cpf_hospede`),
  ADD KEY `cpf_Usuario` (`cpf_Usuario`),
  ADD KEY `codigoReserva` (`codigo_Reserva`);

--
-- Índices de tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`codigo_Produto`),
  ADD KEY `fk_usuario` (`cpf_Usuario`);

--
-- Índices de tabela `quartos`
--
ALTER TABLE `quartos`
  ADD PRIMARY KEY (`numero_Quarto`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`cpf_Usuario`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `nomeLogin` (`nomeLogin`),
  ADD UNIQUE KEY `senha` (`senha`),
  ADD UNIQUE KEY `nomeLogin_2` (`nomeLogin`),
  ADD UNIQUE KEY `celular` (`celular`);

--
-- Índices de tabela `vendas`
--
ALTER TABLE `vendas`
  ADD PRIMARY KEY (`codigo_Venda`),
  ADD KEY `cpf_Usuario` (`cpf_Usuario`),
  ADD KEY `fk_produto` (`codigo_Produto`);

--
-- Índices de tabela `vendas_itens`
--
ALTER TABLE `vendas_itens`
  ADD PRIMARY KEY (`codigo_venda_itens`),
  ADD UNIQUE KEY `unica_venda_produto` (`codigo_Venda`,`codigo_Produto`),
  ADD KEY `fk_produtos` (`codigo_Produto`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `hospedagens_reservas`
--
ALTER TABLE `hospedagens_reservas`
  MODIFY `codigo_Reserva` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=118;

--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `codigo_Produto` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de tabela `vendas`
--
ALTER TABLE `vendas`
  MODIFY `codigo_Venda` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT de tabela `vendas_itens`
--
ALTER TABLE `vendas_itens`
  MODIFY `codigo_venda_itens` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `hospedagens_reservas`
--
ALTER TABLE `hospedagens_reservas`
  ADD CONSTRAINT `fk_numero_Quarto` FOREIGN KEY (`numero_Quarto`) REFERENCES `quartos` (`numero_Quarto`),
  ADD CONSTRAINT `hospedagens_reservas_ibfk_1` FOREIGN KEY (`cpf_Usuario`) REFERENCES `usuario` (`cpf_Usuario`);

--
-- Restrições para tabelas `hospedes`
--
ALTER TABLE `hospedes`
  ADD CONSTRAINT `hospedes_ibfk_1` FOREIGN KEY (`cpf_Usuario`) REFERENCES `usuario` (`cpf_Usuario`),
  ADD CONSTRAINT `hospedes_ibfk_2` FOREIGN KEY (`codigo_Reserva`) REFERENCES `hospedagens_reservas` (`codigo_Reserva`);

--
-- Restrições para tabelas `produtos`
--
ALTER TABLE `produtos`
  ADD CONSTRAINT `fk_usuario` FOREIGN KEY (`cpf_Usuario`) REFERENCES `usuario` (`cpf_Usuario`);

--
-- Restrições para tabelas `vendas`
--
ALTER TABLE `vendas`
  ADD CONSTRAINT `fk_produto` FOREIGN KEY (`codigo_Produto`) REFERENCES `produtos` (`codigo_Produto`),
  ADD CONSTRAINT `vendas_ibfk_1` FOREIGN KEY (`cpf_Usuario`) REFERENCES `usuario` (`cpf_Usuario`);

--
-- Restrições para tabelas `vendas_itens`
--
ALTER TABLE `vendas_itens`
  ADD CONSTRAINT `fk_produtos` FOREIGN KEY (`codigo_Produto`) REFERENCES `produtos` (`codigo_Produto`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_vendas` FOREIGN KEY (`codigo_Venda`) REFERENCES `vendas` (`codigo_Venda`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
