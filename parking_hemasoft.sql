-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-11-2021 a las 23:43:44
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `parking_hemasoft`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `cedula` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(20) NOT NULL,
  `telefono_movil` int(11) NOT NULL,
  `telefono_fijo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`cedula`, `nombre`, `apellidos`, `telefono_movil`, `telefono_fijo`) VALUES
(23, 'sdf', 'sdfg', 23, 1234),
(234, 'sdagf', 'fagdsaf', 654, 76543),
(2345, 'efadsfa', 'dsafa', 3456, 234),
(39452, 'Luis', 'Saldarriaga', 36422, 3652461),
(365545, 'Luis', 'Saldarriaga', 54525, 251323),
(39172496, 'Luis', 'Saldarriaga', 54154, 36542),
(780000337, 'Arturo', 'Benavides', 455555, 1234),
(1001810262, 'Luis', 'Saldarriaga', 6422, 3511);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro`
--

CREATE TABLE `registro` (
  `idregistro` int(11) NOT NULL,
  `placa` varchar(10) NOT NULL,
  `cedula` int(11) NOT NULL,
  `hora_ingreso` datetime NOT NULL,
  `hora_salida` datetime DEFAULT NULL,
  `estado` varchar(20) NOT NULL,
  `posicion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `registro`
--

INSERT INTO `registro` (`idregistro`, `placa`, `cedula`, `hora_ingreso`, `hora_salida`, `estado`, `posicion`) VALUES
(1, '345', 23, '2021-11-18 23:22:54', NULL, 'activo', 1),
(2, '456789', 780000337, '2021-11-18 23:24:59', NULL, 'activo', 2),
(3, 'ABC-554', 365545, '2021-11-19 16:25:08', NULL, 'activo', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculos`
--

CREATE TABLE `vehiculos` (
  `placa` varchar(10) NOT NULL,
  `marca` varchar(20) NOT NULL,
  `tipo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `vehiculos`
--

INSERT INTO `vehiculos` (`placa`, `marca`, `tipo`) VALUES
('345', 'gdgsdfg', 'dfgfdgsd'),
('456789', 'chevrolet', 'antiguo'),
('ABC-254', 'FORD', '4x4'),
('ABC-365', 'FORD', '4x4'),
('ABC-554', 'FORD', '4x4'),
('ABC-789', 'FORD', 'Auto'),
('cxvsgvasdg', 'affdfa', 'dfaad'),
('dsfgf', 'dafsfd', 'asdfas');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cedula`) USING BTREE;

--
-- Indices de la tabla `registro`
--
ALTER TABLE `registro`
  ADD PRIMARY KEY (`idregistro`),
  ADD KEY `placa` (`placa`),
  ADD KEY `cedula` (`cedula`);

--
-- Indices de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD PRIMARY KEY (`placa`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `registro`
--
ALTER TABLE `registro`
  MODIFY `idregistro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `registro`
--
ALTER TABLE `registro`
  ADD CONSTRAINT `cedula` FOREIGN KEY (`cedula`) REFERENCES `clientes` (`cedula`),
  ADD CONSTRAINT `placa` FOREIGN KEY (`placa`) REFERENCES `vehiculos` (`placa`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
