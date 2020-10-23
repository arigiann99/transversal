-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-10-2020 a las 20:17:50
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `universidadg2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `id_alumno` int(11) NOT NULL,
  `nombre_alumno` varchar(20) DEFAULT NULL,
  `legajo` int(10) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`id_alumno`, `nombre_alumno`, `legajo`, `activo`) VALUES
(2, 'Malco Grosso', 152801, 1),
(3, 'Marianela Garacciolo', 152799, 1),
(4, 'Leonel Messi', 15236, 1),
(5, 'Lucas Perez', 154899, 1),
(6, 'Alejandro Sosa', 152432, 1),
(7, 'Julio Bustos', 16748, 0),
(8, 'Ariel Garcia', 152797, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursada`
--

CREATE TABLE `cursada` (
  `id_cursada` int(11) NOT NULL,
  `id_alumno` int(11) NOT NULL,
  `id_materia` int(11) NOT NULL,
  `nota` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cursada`
--

INSERT INTO `cursada` (`id_cursada`, `id_alumno`, `id_materia`, `nota`) VALUES
(2, 2, 2, 5),
(4, 4, 2, 8),
(5, 5, 2, 2),
(6, 2, 3, 7),
(7, 6, 2, 5),
(8, 7, 2, 7.5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materia`
--

CREATE TABLE `materia` (
  `id_materia` int(11) NOT NULL,
  `nombre_materia` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `materia`
--

INSERT INTO `materia` (`id_materia`, `nombre_materia`) VALUES
(2, 'Laboratorio 1'),
(3, 'Programacion Web'),
(4, 'Matematica 3.0'),
(5, 'Ingles ');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`id_alumno`);

--
-- Indices de la tabla `cursada`
--
ALTER TABLE `cursada`
  ADD PRIMARY KEY (`id_cursada`),
  ADD KEY `id_alumno` (`id_alumno`),
  ADD KEY `id_materia` (`id_materia`);

--
-- Indices de la tabla `materia`
--
ALTER TABLE `materia`
  ADD PRIMARY KEY (`id_materia`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumno`
--
ALTER TABLE `alumno`
  MODIFY `id_alumno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `cursada`
--
ALTER TABLE `cursada`
  MODIFY `id_cursada` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `materia`
--
ALTER TABLE `materia`
  MODIFY `id_materia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cursada`
--
ALTER TABLE `cursada`
  ADD CONSTRAINT `cursada_ibfk_1` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id_alumno`),
  ADD CONSTRAINT `cursada_ibfk_2` FOREIGN KEY (`id_materia`) REFERENCES `materia` (`id_materia`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
