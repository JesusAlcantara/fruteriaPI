-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-06-2022 a las 19:15:10
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `fruteriapi`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`, `descripcion`) VALUES
(2, 'Verduras', 'Descripción de la categoría \'Verduras\'.'),
(1, 'Frutas', 'Descripción de la categoría \'Frutas\'.'),
(29, 'Bebidas', 'Descripción de la categoría \'Bebidas\'.'),
(40, 'Otros', 'Descripción de la categoría \'Otros\'.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(129);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id` bigint(20) NOT NULL,
  `fecha_pedido` datetime DEFAULT NULL,
  `usuario` bigint(20) DEFAULT NULL,
  `entrega` bit(1) NOT NULL,
  `valoracion` varchar(255) DEFAULT NULL,
  `precio_total` float NOT NULL,
  `fecha_entrega` datetime DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `pos_domicilio` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido_productos`
--

CREATE TABLE `pedido_productos` (
  `pedido_id` bigint(20) NOT NULL,
  `productos_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` bigint(20) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` float NOT NULL,
  `categoria` bigint(20) DEFAULT NULL,
  `id_pedido` bigint(20) DEFAULT NULL,
  `empleado` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `cantidad`, `foto`, `nombre`, `precio`, `categoria`, `id_pedido`, `empleado`) VALUES
(38, 6, 'cocacola.png', 'Coca-Cola', 1.7, 29, NULL, 43),
(39, 15, 'tomatepera.png', 'Tomate Pera', 1.49, 2, NULL, 43),
(41, 10, 'huevosdocena.png', 'Docena de Huevos XL', 2.3, 40, NULL, 43),
(45, 20, 'patatas.png', 'Patata Freír', 0.89, 2, NULL, 49),
(46, 12, 'cerezas.png', 'Cerezas', 6.3, 1, NULL, 49),
(50, 12, 'mandarina.png', 'Mandarina', 1.79, 1, NULL, 49),
(68, 12, 'kiwizespri.png', 'Kiwi Zespri', 4.99, 1, NULL, 49),
(128, 20, 'patatacocer.jpg', 'Patata Cocer', 0.89, 2, NULL, 49);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto_pedidos`
--

CREATE TABLE `producto_pedidos` (
  `producto_id` bigint(20) NOT NULL,
  `pedidos_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `rol` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `activo` int(11) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `c_password` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `apellidos`, `email`, `nombre`, `password`, `rol`, `telefono`, `activo`, `token`, `c_password`) VALUES
(1, 'Cortez Iturralde', 'admin@admin.com', 'Carlos Alberto', '$2a$10$yUj0TJ4qA8T3yy6URXfJ/OLHeDFHfZwBGQyWKbVVpUiJ8ZXvU77ce', 'ROL_ADMIN', '654124585', 1, NULL, ''),
(2, 'Alcántara Gómez', 'jesusalcantaragomez11@gmail.com', 'Jesús', '$2a$10$WrKJNPUW8TD.xzV03Sk/yeViUQpqNKSNPBeKAIr5RK35uv1QOZh7e', 'ROL_CLIENTE', '62653349', 1, NULL, ''),
(23, 'Marín Martínez', 'luisa@gmail.com', 'Luisa', '$2a$10$AOi5Y3WFdKaT/6stZBYywusjJhQ3DT7.eLl6Kziwqizi5Sbrryady', 'ROL_CLIENTE', '654123654', 0, NULL, ''),
(24, 'Cortez Robalino', 'michelle@gmail.com', 'Michelle', '$2a$10$YEhwe3naqG.B/9RErwm6d.0XjxqqCn7C9uQywg5QhvhrF4cj8F1Ky', 'ROL_CLIENTE', '65412563', 0, NULL, ''),
(43, 'Alcántara Gómez', 'jesus@gmail.com', 'Jesus', '$2a$10$lLvo.wwgZJ6FvTmmmykRVuK6axBGSUYDjvbJu/7j60ZTGW7iMp8ea', 'ROL_EMPLEADO', '626253349', 1, NULL, '123456789'),
(47, 'García Rodríguez', 'juan@gmail.com', 'Juan', '$2a$10$V86/3hEAMAalJ2cP/mIWrelte4mFOOAgwv8XnBooqAcg3w8pOBN5K', 'ROL_CLIENTE', '652251436', 1, NULL, ''),
(123, 'Rodríguez Santos', 'luissantos@gmail.com', 'Luis', '$2a$10$mXktAHFZ/AUwHs0jPfGQe.9nLde.7vOv4HtS7ys/HrXub3UZp1p0O', 'ROL_CLIENTE', '625456584', 1, NULL, '123456789'),
(49, 'Martínez', 'miguelito@gmail.com', 'Miguelito', '$2a$10$QDunNW5l9Pco4oIaPkHo5OAX0eI0PRohZa0h8r6RZV9c6rgwqSDh2', 'ROL_EMPLEADO', '65412563', 1, NULL, '$2a$10$ZLkaOEH8R34jDUlMrUE73.fnEAGG4VBUbY/As30z12dq86C.gm.LS'),
(122, 'de la Jara Vera', 'pablo@gmail.com', 'Pablo', '$2a$10$T6ANXa52p1ForFihaOOlCeTkXrnonbL2Hu7TNZw8inVo1YDKrW81G', 'ROL_EMPLEADO', '666919244', 0, NULL, '$2a$10$jFDNgz.NkFi2uV7C3QxO0ueZKApbLaD3LJP0f1Bt3AWjWETIUwKqy');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcue42wbaxuo9hu7cy2ixrokx5` (`usuario`);

--
-- Indices de la tabla `pedido_productos`
--
ALTER TABLE `pedido_productos`
  ADD KEY `FK9q00p33elayx84wl4im8ehr4x` (`productos_id`),
  ADD KEY `FKv1xsxti8g695nhdlbdluwo88` (`pedido_id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKioi0b2bhj9qm0nrebs04ald0s` (`id_pedido`),
  ADD KEY `FKydg56fw3q3f2lfcvtpkt36ls` (`categoria`),
  ADD KEY `FKctkhut18wkrc2lluvk4q76mb1` (`empleado`);

--
-- Indices de la tabla `producto_pedidos`
--
ALTER TABLE `producto_pedidos`
  ADD KEY `FKqg63f620481p342viyrim6y33` (`pedidos_id`),
  ADD KEY `FKt7t1cdpji9a1k4g36sk41qt17` (`producto_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_5171l57faosmj8myawaucatdw` (`email`) USING HASH;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
