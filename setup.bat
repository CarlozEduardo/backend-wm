@echo off
echo Rodando repositórios...

:: Rodar repositório Node.js
cd .\freela-wm
git pull
start npm start

:: Rodar repositório Java (Spring Boot)
cd .\backend-wm
git pull
.\mvnw spring-boot:run

echo Configuração concluída!
pause
