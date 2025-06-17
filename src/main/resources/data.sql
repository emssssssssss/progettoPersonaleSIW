insert into museo (id, nome, descrizione, indirizzo, orari_apertura)values (1,'Museo d Arte Moderna','Un museo dedicato all arte contemporanea e moderna.','Via delle Belle Arti 12, Roma','09:00 - 19:00');

insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (1, 'Leonardo da Vinci', 'Pittore, inventore e scienziato italiano del Rinascimento.', 1452, 1519, 'https://example.com/leonardo.jpg', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (2, 'Frida Kahlo', 'Celebre pittrice messicana conosciuta per i suoi autoritratti intensi.', 1907, 1954, 'https://example.com/frida.jpg', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (3, 'Banksy', 'Artista di street art britannico, la cui identità rimane sconosciuta.', 1974, NULL, 'https://example.com/banksy.jpg', 1);

insert into evento (id, titolo, descrizione, data_inizio, data_fine, museo_id) values (1, 'Rinascimento in Mostra', 'Un evento speciale dedicato ai grandi maestri del Rinascimento.', '2025-09-15', '2025-10-15', 1);
insert into evento (id, titolo, descrizione, data_inizio, data_fine, museo_id) values (2, 'Notte al Museo', 'Un''esperienza notturna unica con guide speciali e performance dal vivo.', '2025-12-01', '2025-12-02', 1);
insert into evento (id, titolo, descrizione, data_inizio, data_fine, museo_id) values (3, 'Arte Contemporanea e Urban', 'Focus sull''arte urbana con installazioni interattive.', '2025-07-10', '2025-08-10', 1);

insert into utente (id, nome, email, password, ruolo) 
values (1, 'Anna Rossi', 'anna.rossi@example.com', '$2a$10$XKtkhBOYbJJHOcOkgjew8e7MsHKxpY6c3AGnTuCAZ/pmQQ.8G2Jbe', 'VISITATORE');
-- password: password123

insert into utente (id, nome, email, password, ruolo) 
values (2, 'Luca Bianchi', 'luca.bianchi@example.com', '$2a$10$WqPUKpgXgCkFHeCdrgvVO.6Bd4PHq11Xr5aln1GsTP9Vldn1piW/S', 'VISITATORE');
-- password: securepass

insert into utente (id, nome, email, password, ruolo) 
values (3, 'Mario Verdi', 'mario.verdi@example.com', '$2a$10$dAyw2.3jE5OYjD4HD9k8q.SatCdL/6tpKAjrjwwA6H78gSk0QoC/C', 'STAFF');
-- password: staffpass1

insert into fascia (id, data, orario_inizio, capienza_massima, posti_prenotati, museo_id, evento_id) values (1, '2025-09-16', '10:00:00', 30, 5, 1, 1);
insert into fascia (id, data, orario_inizio, capienza_massima, posti_prenotati, museo_id, evento_id) values (2, '2025-09-16', '15:00:00', 25, 10, 1, 1);
insert into fascia (id, data, orario_inizio, capienza_massima, posti_prenotati, museo_id, evento_id) values (3, '2025-12-01', '20:00:00', 50, 20, 1, 2);
insert into fascia (id, data, orario_inizio, capienza_massima, posti_prenotati, museo_id, evento_id) values (4, '2025-07-12', '11:00:00', 40, 15, 1, 3);

insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (1, 'La Gioconda', 'Uno dei dipinti più famosi al mondo, realizzato da Leonardo da Vinci.', 1503, 'https://example.com/gioconda.jpg', 1, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (2, 'Le Due Frida', 'Dipinto iconico che rappresenta due versioni di Frida Kahlo.', 1939, 'https://example.com/frida2.jpg', 2, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (3, 'Girl with Balloon', 'Famosa opera di street art di Banksy.', 2002, 'https://example.com/balloon.jpg', 3, 1);

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

