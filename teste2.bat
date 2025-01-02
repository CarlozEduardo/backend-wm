@echo off
SET MYSQL_USER=root
SET MYSQL_PASSWORD=215912

echo Verificando MySQL...
mysql -u%MYSQL_USER% -p%MYSQL_PASSWORD% < script.sql

echo Configuracao concluida!
pause