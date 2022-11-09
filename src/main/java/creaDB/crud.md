

```sql
create database `pizzeria` default charset=utf8mb4;
use pizzeria;
show databases;
grant all privileges on `pizzeria`.* to 'pizzaiolo'@'%' identified by 'password' with grant option;

create table pizze (
    id INT not null auto_increment primary key,
    nome VARCHAR(50) not null,
    ingredienti VARCHAR(100) not null,
    prezzo DOUBLE(3, 2) default 1)
    Engine=InnoDB;
describe pizze;

create table clienti (
    id INT not null auto_increment primary key,
    nome VARCHAR(50) not null,
    cognome VARCHAR(50) not null,
    indirizzo VARCHAR(50) not null)
    Engine=InnoDB;
describe clienti;

create table ordini (
    id INT not null auto_increment primary key,
    data DATE not null,
    ora TIME not null,
    id_cliente INT not null,
    ora_consegna TIME,
    foreign key (id_cliente) references clienti(id))
    Engine=InnoDB;
describe ordini;

create table pizzeOrdinate (
    id INT not null auto_increment primary key,
    id_ordine INT not null,
    id_pizza INT not null,
    foreign key (id_ordine) references ordini(id),
    foreign key (id_pizza) references pizze(id))
    Engine=InnoDB;
describe pizzeOrdinate;

show tables;

--- popolamento

INSERT INTO pizze (nome, ingredienti, prezzo)
    values('margherita', 'pomodoro e mozzarella', 4.5),
        ('quattro Formaggi bianca', 'mozzarella, gorgonzola, scamorza, formaggio', 7);
SELECT * FROM pizze;

INSERT INTO clienti (nome, cognome, indirizzo)
    values('Mario', 'Cocco', 'via del fiume'),
        ('Carlo', 'billo', 'via del mare');
SELECT * FROM clienti;

INSERT INTO ordini (data, ora, id_cliente, ora_consegna)
    values("2022-11-9", '12:44:00', 1, '13:00:00'),
        ("2022-11-12", '13:3:00', 2, '14:00:00');
SELECT * FROM ordini;

INSERT INTO pizzeordinate (id_ordine, id_pizza)
    values(1, 1),
        (2,2);
SELECT * FROM pizzeordinate;


```