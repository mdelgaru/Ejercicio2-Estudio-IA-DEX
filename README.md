# Formulario + BBDD Cloud con Supabase

Aplicación en Android nativo desarrollada con Kotlin que se conecta a una base de datos en la nube (Supabase) para la recuperación y persistencia de datos.

## Descripción

Esta aplicación es un ejercicio de desarrollo móvil en la que se recuperan datos de un formulario subidos por un usuario, mostrándolos en estilo tarjeta. Además, permite la creación de nuevos objetos mediante un formulario que el usuario puede rellenar y enviar.

## DEMO

Link:
> Vídeo demonstrativo del funcionamiento de la aplicación: navegación, envío y recuperación de datos, forzar errores y validación de campos.

## Características Principales
- Interfaz desarrollada con Jetpack Compose
- Separación de responsabilidades
- Conexión a base de datos remota _(Supabase)_
- Manejo de errores de conexión
- Validación de campos del formulario
- Navegación sencilla entre pantallas
- Pantallas de carga al recuperar y enviar datos
- Gestión de estados

## Consideraciones
- Para el guardado de la URL y API key para la conexión con base de datos se ha creado y configurado un archivo de configuración **local.properties**.