-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 05-Jul-2021 às 18:52
-- Versão do servidor: 10.4.19-MariaDB
-- versão do PHP: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `tasker`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `empresas`
--

CREATE TABLE `empresas` (
  `idempresa` int(11) NOT NULL,
  `nomeFantasia` varchar(45) NOT NULL,
  `cnpj` char(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `empresas`
--

INSERT INTO `empresas` (`idempresa`, `nomeFantasia`, `cnpj`) VALUES
(1, 'Empresa Teste', '00000000000000'),
(2, 'Empresa Beta', '00000000000000'),
(3, 'Empresa Teste', '00000000000000'),
(4, 'Empresa Beta', '00000000000000'),
(5, 'SimoTech LTDA', '11111111111111'),
(6, 'PauloTech', '11111111111111');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ncs`
--

CREATE TABLE `ncs` (
  `idncs` int(11) NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `descricao` varchar(400) NOT NULL,
  `responsavel_idusuario` int(11) NOT NULL,
  `prazo` date DEFAULT NULL,
  `dataCadastro` date NOT NULL,
  `ncStatus_idncStatus` int(11) NOT NULL,
  `usuario_idusuario` int(11) NOT NULL,
  `empresa_idempresa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `ncs`
--

INSERT INTO `ncs` (`idncs`, `titulo`, `descricao`, `responsavel_idusuario`, `prazo`, `dataCadastro`, `ncStatus_idncStatus`, `usuario_idusuario`, `empresa_idempresa`) VALUES
(3, 'Teste', 'SSS', 4, '2021-07-30', '2021-06-20', 4, 3, 2),
(4, 'Teste', 'SSS', 3, '2021-07-30', '2021-06-20', 4, 4, 2),
(8, 'vcaswdcfqf', 'fvwfewqfe', 2, '2021-08-06', '2021-06-30', 4, 1, 1),
(11, 'vfvsfdvvs', 'afdvkhqabdcoic bqdadvc\nq[fvbqfv´ qfvqvf ', 1, '2021-08-01', '2021-06-30', 4, 1, 1),
(12, 'Caminhão roubado', 'cbqndhqpióuhqbfo´pqnf', 1, '2021-08-02', '2021-06-30', 2, 1, 1),
(13, 'aadf', 'adfasdfasfd', 1, '2021-01-01', '2021-06-30', 4, 1, 1),
(14, 'Caminhão', 'aasdujçhadfó~jçlnc´qn', 1, '2021-08-08', '2021-06-30', 4, 1, 1),
(16, 'XoCaminhãozinho', 'dfwfvwafe', 1, '2021-08-08', '2021-06-30', 4, 1, 1),
(21, 'Teste23', 'É uma descrição de exemplo, ela possuí espaçamento\nLorem Ipsum', 2, '2021-12-01', '2021-07-01', 4, 2, 1),
(23, 'Sinistro Caminhão', 'Caminhão pegou fogo e metade da carga foi roubada.', 1, '2021-08-01', '2021-07-01', 2, 2, 1),
(39, 'acdscdsc', 'sdad', 17, '2021-08-01', '2021-07-05', 4, 8, 5),
(40, 'wfwfrwer', 'fvafd', 17, '2021-08-01', '2021-07-05', 4, 8, 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `ncstatus`
--

CREATE TABLE `ncstatus` (
  `idncStatus` int(11) NOT NULL,
  `status` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `ncstatus`
--

INSERT INTO `ncstatus` (`idncStatus`, `status`) VALUES
(1, 'Concluído'),
(2, 'Em andamento'),
(3, 'Cancelado'),
(4, 'Aberto');

-- --------------------------------------------------------

--
-- Estrutura da tabela `niveisacesso`
--

CREATE TABLE `niveisacesso` (
  `id` int(11) NOT NULL,
  `acesso` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `niveisacesso`
--

INSERT INTO `niveisacesso` (`id`, `acesso`) VALUES
(1, 'Funcionário'),
(2, 'Gestor');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `idusuario` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `sobrenome` varchar(45) NOT NULL,
  `email` varchar(245) NOT NULL,
  `acesso` int(11) NOT NULL,
  `login` varchar(45) NOT NULL,
  `senha` char(65) NOT NULL,
  `empresa_idempresa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`idusuario`, `nome`, `sobrenome`, `email`, `acesso`, `login`, `senha`, `empresa_idempresa`) VALUES
(1, 'José', 'Funcionario', 'jose@empresateste', 1, 'test', 'test', 1),
(2, 'João', 'Funcionario', 'joao@empresateste', 2, 'adm', 'adm', 1),
(3, 'Pedrinho', 'Funcionario', 'pedrinho@empresabeta', 2, 'adm1', 'adm1', 2),
(4, 'Thiago', 'Funcionario', 'thiago@empresabeta', 1, 'test1', 'test1', 2),
(8, 'João', 'Sereia', 'jojo.sereia@simotech.com.br', 2, 'jojo', 'jojo', 5),
(17, 'Ana', 'Ana', 'ana@ana.com', 1, 'ana', 'ana', 5);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `empresas`
--
ALTER TABLE `empresas`
  ADD PRIMARY KEY (`idempresa`);

--
-- Índices para tabela `ncs`
--
ALTER TABLE `ncs`
  ADD PRIMARY KEY (`idncs`),
  ADD KEY `fk_ncs_empresa` (`empresa_idempresa`),
  ADD KEY `fk_ncs_responsavel` (`responsavel_idusuario`) USING BTREE,
  ADD KEY `fk_ncs_usuario` (`usuario_idusuario`) USING BTREE,
  ADD KEY `fk_ncs_ncStatus` (`ncStatus_idncStatus`);

--
-- Índices para tabela `ncstatus`
--
ALTER TABLE `ncstatus`
  ADD PRIMARY KEY (`idncStatus`);

--
-- Índices para tabela `niveisacesso`
--
ALTER TABLE `niveisacesso`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idusuario`,`empresa_idempresa`),
  ADD KEY `fk_usuario_empresa` (`empresa_idempresa`),
  ADD KEY `fk_acesso_useracesso` (`acesso`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `empresas`
--
ALTER TABLE `empresas`
  MODIFY `idempresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `ncs`
--
ALTER TABLE `ncs`
  MODIFY `idncs` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de tabela `ncstatus`
--
ALTER TABLE `ncstatus`
  MODIFY `idncStatus` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `niveisacesso`
--
ALTER TABLE `niveisacesso`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `ncs`
--
ALTER TABLE `ncs`
  ADD CONSTRAINT `fk_ncs_empresa` FOREIGN KEY (`empresa_idempresa`) REFERENCES `empresas` (`idempresa`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ncs_ncStatus` FOREIGN KEY (`ncStatus_idncStatus`) REFERENCES `ncstatus` (`idncStatus`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ncs_responsavel` FOREIGN KEY (`responsavel_idusuario`) REFERENCES `usuarios` (`idusuario`),
  ADD CONSTRAINT `fk_ncs_usuario` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuarios` (`idusuario`);

--
-- Limitadores para a tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `fk_acesso_useracesso` FOREIGN KEY (`acesso`) REFERENCES `niveisacesso` (`id`),
  ADD CONSTRAINT `fk_usuario_empresa` FOREIGN KEY (`empresa_idempresa`) REFERENCES `empresas` (`idempresa`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
