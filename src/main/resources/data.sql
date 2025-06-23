insert into museo (id, nome, descrizione, indirizzo, orari_apertura)values (1,'Museo d Arte Moderna','Un museo dedicato all arte contemporanea e moderna.','Via delle Belle Arti 12, Roma','09:00 - 19:00');

insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (1, 'Leonardo da Vinci', 'Pittore, inventore e scienziato italiano del Rinascimento.', 1452, 1519, '/images/profiloLeonardoDaVinci.jpg', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (2, 'Frida Kahlo', 'Celebre pittrice messicana conosciuta per i suoi autoritratti intensi.', 1907, 1954, '/images/profiloFridaKahlo.webp', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (3, 'Banksy', 'Artista di street art britannico, la cui identità rimane sconosciuta.', 1974, NULL, '/images/profiloBansky.jpg', 1);

insert into evento (id, titolo, descrizione, data_inizio, data_fine, museo_id, immagine_url) values (1, 'Rinascimento in Mostra', 'Un evento speciale dedicato ai grandi maestri del Rinascimento.', '2025-09-15', '2025-10-15', 1, '/images/sfondoRinascimento.jpg');
insert into evento (id, titolo, descrizione, data_inizio, data_fine, museo_id, immagine_url) values (2, 'Notte al Museo', 'Un''esperienza notturna unica con guide speciali e performance dal vivo.', '2025-12-01', '2025-12-02', 1, '/images/sfondoNotteAlMuseo.webp');
insert into evento (id, titolo, descrizione, data_inizio, data_fine, museo_id, immagine_url) values (3, 'Arte Contemporanea e Urban', 'Focus sull''arte urbana con installazioni interattive.', '2025-07-10', '2025-08-10', 1, '/images/sfondoArteContemporanea.jpg');

insert into utente (id, nome, email, password, ruolo) 
values (1, 'Anna Rossi', 'anna.rossi@example.com', '$2a$10$7Y7pHVk6jHSHKCowPrSh8Opypb2coYl7/ShaATGtGOdtlchK9tgAK', 'VISITATORE');
-- password: password123

insert into utente (id, nome, email, password, ruolo) 
values (2, 'Luca Bianchi', 'luca.bianchi@example.com', '$2a$10$7Y7pHVk6jHSHKCowPrSh8Opypb2coYl7/ShaATGtGOdtlchK9tgAK', 'VISITATORE');
-- password: password123

insert into utente (id, nome, email, password, ruolo) 
values (3, 'Mario Verdi', 'mario.verdi@example.com', '$2a$10$7Y7pHVk6jHSHKCowPrSh8Opypb2coYl7/ShaATGtGOdtlchK9tgAK', 'STAFF');
-- password: password123

-- Eventi per evento_id = 1
insert into fascia (id, data, orario_inizio, capienza_massima, posti_prenotati, museo_id, evento_id) 
values (5, '2025-09-17', '10:00:00', 30, 8, 1, 1), (6, '2025-09-17', '15:00:00', 25, 12, 1, 1), (7, '2025-09-18', '10:00:00', 30, 18, 1, 1),
(1, '2025-09-16', '10:00:00', 30, 5, 1, 1), (2, '2025-09-16', '15:00:00', 25, 20, 1, 1);

-- Eventi per evento_id = 2
insert into fascia (id, data, orario_inizio, capienza_massima, posti_prenotati, museo_id, evento_id) 
values (8, '2025-12-02', '20:00:00', 50, 25, 1, 2), (9, '2025-12-03', '18:00:00', 45, 20, 1, 2), (10, '2025-12-04', '20:00:00', 50, 30, 1, 2),
(3, '2025-12-01', '20:00:00', 50, 20, 1, 2);

-- Eventi per evento_id = 3
insert into fascia (id, data, orario_inizio, capienza_massima, posti_prenotati, museo_id, evento_id) 
values (11, '2025-07-13', '11:00:00', 40, 10, 1, 3), (12, '2025-07-14', '15:00:00', 35, 20, 1, 3), (13, '2025-07-15', '17:00:00', 30, 25, 1, 3),
(4, '2025-07-12', '11:00:00', 40, 15, 1, 3);


insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (1, 'La Gioconda', 'Uno dei dipinti più famosi al mondo, realizzato da Leonardo da Vinci.', 1503, '/images/OperaLaGioconda.jpg', 1, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (2, 'Le Due Frida', 'Dipinto iconico che rappresenta due versioni di Frida Kahlo.', 1939, '/images/OperaLeDueFrida.jpg', 2, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (3, 'Girl with Balloon', 'Famosa opera di street art di Banksy.', 2002, '/images/OperaGirlWithBaloon.jpg', 3, 1);

insert into opera_eventi (opere_id, eventi_id) values (1, 1);  -- La Gioconda → Rinascimento in Mostra
insert into opera_eventi (opere_id, eventi_id) values (2, 2);  -- Le Due Frida → Notte al Museo
insert into opera_eventi (opere_id, eventi_id) values (3, 3);  -- Girl with Balloon → Arte Contemporanea e Urban

insert into prenotazione (id, utente_id, fascia_id, numero_biglietti, numero_persone, stato, data_prenotazione) values (1, 1, 1, 2, 2, 'CONFERMATA', '2025-06-16');
insert into prenotazione (id, utente_id, fascia_id, numero_biglietti, numero_persone, stato, data_prenotazione) values (2, 2, 2, 3, 3, 'CONFERMATA', '2025-06-15');
insert into prenotazione (id, utente_id, fascia_id, numero_biglietti, numero_persone, stato, data_prenotazione) values (3, 3, 3, 1, 1, 'ANNULLATA',   '2025-06-14');

select setval('museo_id_seq', (select MAX(id) from museo));
select setval('artista_id_seq', (select MAX(id) from artista));
select setval('evento_id_seq', (select MAX(id) from evento));
select setval('fascia_id_seq', (select MAX(id) from fascia));
select setval('opera_id_seq', (select MAX(id) from opera));
select setval('prenotazione_id_seq', (select MAX(id) from prenotazione));
select setval('utente_id_seq', (select MAX(id) from utente));

