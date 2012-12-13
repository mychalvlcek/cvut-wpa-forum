# WPA - JAVA EE Semester Project

#### Diskuzní Forum - popis

Pokus o klasické diskuzní fórum, kde registrovaní uživatelé vkládají své přispěvky do různých
témat, které jsou dále rozděleny do různých sekcí (kategorií). Obyčejný návštěvník fóra má
možnost pouze pasivního prohlížení webu bez žádné větší interakce, zatímco ostatní uživatelské
role mají podstatně větší možnosti.

#### Instalace

Ke spuštění projektu je potřeba mít nainstalovanou databázi PostgreSQL a
naslouchající na portu **5432** a v ní vytvořeného uživatele **postgres** (heslo: postgres) a vytvořenou databázi **forum**.
Toto nastavení lze změnit v souboru `/WEB-INF/properties/jdbc.properties`.

###### Příklad pomocí příkazového řádku:

* `$ psql localhost` - připojení k databázi
* `# CREATE USER postgres WITH PASSWORD 'postgres';` - vytvoření uživatele postgres
* `# CREATE DATABASE forum OWNER postgres;` - vytvoření databáze forum jejíž vlastník je uživatel *postgres*

Poté stačí build a run projektu. Po naběhnutí uvítací stránky v prohlížeči
kliknout na tlačítko "Init databázových dat" k načtení testovacích dat.
