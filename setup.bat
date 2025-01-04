@echo off
echo Iniciando Front-End e Back-End...

:: Iniciar o Front-End em um novo terminal
start "" cmd /k "cd freela-wm && git pull && npm install && npm run dev"

:: Iniciar o Back-End no terminal atual
cd backend-wm
git pull
if errorlevel 1 (
    echo Falha ao atualizar o back-end.
    pause
    exit /b 1
)
.\mvnw spring-boot:run
if errorlevel 1 (
    echo Falha ao iniciar o back-end.
    pause
    exit /b 1
)

echo Configuração concluída!
pause
