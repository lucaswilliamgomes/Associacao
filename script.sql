DROP DATABASE IF EXISTS `associacao`;
CREATE DATABASE IF NOT EXISTS `associacao`;

USE `associacao`;

CREATE TABLE IF NOT EXISTS `associacao`(
	`numero` INT NOT NULL,
    `nome` VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `associado`(
	`numero` INT NOT NULL,
    `nome` VARCHAR(255) NOT NULL,
    `telefone` VARCHAR(255) NOT NULL,
    `data_associacao` LONG NOT NULL,
    `nascimento` LONG NOT NULL,
    `associacao` INT NOT NULL,
    `discriminador` BOOLEAN,
    `data_remissao` LONG
);

CREATE TABLE IF NOT EXISTS `taxa`(
	`nome` VARCHAR(255) NOT NULL,
    `vigencia` LONG NOT NULL,
    `valor` DOUBLE NOT NULL,
    `parcelas` INT NOT NULL,
    `administrativa` BOOLEAN NOT NULL,
    `associacao` INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `reuniao`(
    `pauta` VARCHAR(1024) NOT NULL,
	`data` LONG NOT NULL,
    `associacao` INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `reuniaoassociado`(
	`reuniao` INT NOT NULL, #DATA DA REUNIAO
    `associado` INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `pagamento`(
	`codigo` INT NOT NULL, 
    `data` LONG NOT NULL, 
    `valor` DOUBLE NOT NULL, 
    `associado` INT NOT NULL, 
    `taxa` VARCHAR(255) NOT NULL, 
    `associacao` INT NOT NULL
);