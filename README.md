
# Students Manager

## Spis treści
1. [Wprowadzenie](#wprowadzenie)
2. [Instrukcja kompilacji i uruchamiania](#instrukcja-kompilacji-i-uruchamiania)
3. [Funkcjonalności systemu](#funkcjonalności-systemu)
4. [Konfiguracja bazy danych](#konfiguracja-bazy-danych)

---

## Wprowadzenie
Students Manager to aplikacja pozwalająca na zarządzanie danymi studentów w bazie danych MySQL.  
Umożliwia dodawanie, usuwanie, edytowanie oraz przeglądanie studentów, a także obliczanie średniej ocen wszystkich studentów.

---

## Instrukcja kompilacji i uruchamiania

### Wymagania wstępne
1. **Java Development Kit (JDK)** w wersji 8 lub nowszej.
2. **Maven** – narzędzie do zarządzania zależnościami.
3. **MySQL Server** – baza danych.
4. Sterownik JDBC dla MySQL (np. `mysql-connector-java`).

### Instrukcja krok po kroku

1. **Klonowanie projektu**  
   Sklonuj repozytorium na swój lokalny komputer:
   ```bash
   git clone https://github.com/begiedz/students-manager
   cd studentsManager
   ```  

2. **Kompilacja projektu**  
   Przejdź do katalogu projektu i uruchom kompilację przy użyciu Maven:
   ```bash
   mvn clean install
   ```  

3. **Uruchamianie aplikacji**  
   Uruchom aplikację:
   ```bash
   java -jar target/studentsManager.jar
   ```  

---

## Funkcjonalności systemu

1. **Dodawanie studenta**
    - Możliwość dodania nowego studenta poprzez wprowadzenie danych (imienia, wieku, oceny, ID).

2. **Usuwanie studenta**
    - Usunięcie istniejącego studenta z bazy danych na podstawie unikalnego identyfikatora (ID).

3. **Aktualizacja danych studenta**
    - Modyfikowanie istniejących danych studenta (np. imienia, wieku, oceny).

4. **Wyświetlanie wszystkich studentów**
    - Pobieranie i wyświetlanie listy wszystkich studentów zapisanych w bazie danych.

5. **Obliczanie średniej ocen**
    - Automatyczne obliczanie średniej oceny wszystkich studentów.

---

## Konfiguracja bazy danych

### Tworzenie bazy danych
1. Zaloguj się do MySQL:
   ```bash
   mysql -u root -p
   ```  

2. Utwórz bazę danych o nazwie `students-manager`:
   ```sql
   CREATE DATABASE `students-manager`;
   ```  

3. Przełącz się na nowo utworzoną bazę:
   ```sql
   USE `students-manager`;
   ```  

4. Utwórz tabelę `students` z następującą strukturą:
   ```sql
   CREATE TABLE `students` (
       `id` INT AUTO_INCREMENT PRIMARY KEY,
       `name` VARCHAR(100) NOT NULL,
       `age` INT NOT NULL,
       `grade` DOUBLE NOT NULL,
       `studentID` VARCHAR(50) UNIQUE NOT NULL
   );
   ```  

### Konfiguracja aplikacji
Aplikacja domyślnie korzysta z następujących danych logowania do bazy danych:
- **Host**: `localhost`
- **Port**: `3306`
- **Użytkownik**: `root`
- **Hasło**: *(pozostaw puste, jeśli nie ustawiłeś hasła)*

Jeśli dane logowania są inne, edytuj odpowiednio klasę `DatabaseConnection` w pliku:
```java
private static final String url = "jdbc:mysql://<host>:<port>/students-manager";
private static final String username = "<username>";
private static final String password = "<password>";
```  

---

## Uwagi
- Upewnij się, że baza danych jest uruchomiona przed startem aplikacji.
- W razie błędów upewnij się, że sterownik JDBC jest poprawnie skonfigurowany.
