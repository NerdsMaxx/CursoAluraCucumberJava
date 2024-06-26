--DROP TABLE IF EXISTS `leilao`;
--DROP TABLE IF EXISTS `lance`;
--DROP TABLE IF EXISTS `users`;
--
--CREATE TABLE `users` (
--  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
--  `email` varchar(255) NOT NULL,
--  `enabled` bit(1) NOT NULL,
--  `username` varchar(255) NOT NULL,
--  `role` varchar(255) DEFAULT NULL,
--  `password` varchar(255) NOT NULL,
--  PRIMARY KEY (`user_id`)
--) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
--CREATE TABLE `leilao` (
--  `id` bigint(20) NOT NULL AUTO_INCREMENT,
--  `data_abertura` date NOT NULL,
--  `nome` varchar(255) NOT NULL,
--  `valor_inicial` decimal(19,2) NOT NULL,
--  `usuario_user_id` bigint(20) NOT NULL,
--  PRIMARY KEY (`id`),
--  KEY `FKja7vplfvv740bo6vccdmgg3ne` (`usuario_user_id`),
--  CONSTRAINT `FKja7vplfvv740bo6vccdmgg3ne` FOREIGN KEY (`usuario_user_id`) REFERENCES `users` (`user_id`)
--) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
--CREATE TABLE `lance` (
--  `id` bigint(20) NOT NULL AUTO_INCREMENT,
--  `data` date NOT NULL,
--  `valor` decimal(19,2) NOT NULL,
--  `leilao_id` bigint(20) NOT NULL,
--  `usuario_user_id` bigint(20) DEFAULT NULL,
--  PRIMARY KEY (`id`),
--  KEY `FKao15gcf542nf81fp2qq5j3igu` (`leilao_id`),
--  KEY `FKa0r0xo76a1ld305ao4kweawcb` (`usuario_user_id`),
--  CONSTRAINT `FKa0r0xo76a1ld305ao4kweawcb` FOREIGN KEY (`usuario_user_id`) REFERENCES `users` (`user_id`),
--  CONSTRAINT `FKao15gcf542nf81fp2qq5j3igu` FOREIGN KEY (`leilao_id`) REFERENCES `leilao` (`id`)
--) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Drop tables if they exist
DROP TABLE IF EXISTS leilao CASCADE;
DROP TABLE IF EXISTS lance CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Create users table
CREATE TABLE users (
  user_id SERIAL PRIMARY KEY,
  email VARCHAR(255) NOT NULL,
  enabled BOOLEAN NOT NULL,
  username VARCHAR(255) NOT NULL,
  role VARCHAR(255),
  password VARCHAR(255) NOT NULL
);

-- Create leilao table
CREATE TABLE leilao (
  id SERIAL PRIMARY KEY,
  data_abertura DATE NOT NULL,
  nome VARCHAR(255) NOT NULL,
  valor_inicial NUMERIC(19,2) NOT NULL,
  usuario_user_id BIGINT NOT NULL,
  FOREIGN KEY (usuario_user_id) REFERENCES users (user_id)
);

-- Create lance table
CREATE TABLE lance (
  id SERIAL PRIMARY KEY,
  data DATE NOT NULL,
  valor NUMERIC(19,2) NOT NULL,
  leilao_id BIGINT NOT NULL,
  usuario_user_id BIGINT,
  FOREIGN KEY (leilao_id) REFERENCES leilao (id),
  FOREIGN KEY (usuario_user_id) REFERENCES users (user_id)
);