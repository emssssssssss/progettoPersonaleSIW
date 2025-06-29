insert into museo (id, nome, descrizione, indirizzo, orari_apertura)values (1,'Museo d Arte Moderna','Un museo dedicato all arte contemporanea e moderna.','Via delle Belle Arti 12, Roma','09:00 - 19:00');

insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (1, 'Leonardo da Vinci', 'Pittore, inventore e scienziato italiano del Rinascimento.', 1452, 1519, 'images/profiloLeonardoDaVinci.jpg', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (2, 'Frida Kahlo', 'Celebre pittrice messicana conosciuta per i suoi autoritratti intensi.', 1907, 1954, 'images/profiloFridaKahlo.webp', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (3, 'Banksy', 'Artista di street art britannico, la cui identità rimane sconosciuta.', 1974, NULL, 'images/profiloBansky.jpg', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (4, 'Yayoi Kusama', 'Artista giapponese nota per le sue installazioni immersive e i motivi a pois.', 1929, NULL, 'images/profiloYayoiKusama.jpg', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (5, 'Jean-Michel Basquiat', 'Pittore e writer statunitense, figura centrale della scena artistica newyorkese degli anni 80.', 1960, 1988, 'images/profiloJeanMichelBasquiat.png', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (6, 'Tamara de Lempicka', 'Pittrice polacca famosa per il suo stile Art Déco elegante e sensuale.', 1898, 1980, 'images/profiloTamaraDeLempicka.jpg', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (7, 'Keith Haring', 'Artista e attivista statunitense noto per i suoi graffiti stilizzati e messaggi sociali.', 1958, 1990, 'images/profiloKeithHaring.jpg', 1);


insert into evento (id, titolo, descrizione, data_inizio, data_fine, museo_id, immagine_url) values (1, 'Rinascimento in Mostra', 'Un evento speciale dedicato ai grandi maestri del Rinascimento.', '2025-09-15', '2025-10-15', 1, 'images/sfondoRinascimento.jpg');
insert into evento (id, titolo, descrizione, data_inizio, data_fine, museo_id, immagine_url) values (2, 'Notte al Museo', 'Un''esperienza notturna unica con guide speciali e performance dal vivo.', '2025-12-01', '2025-12-02', 1, 'images/sfondoNotteAlMuseo.webp');
insert into evento (id, titolo, descrizione, data_inizio, data_fine, museo_id, immagine_url) values (3, 'Arte Contemporanea e Urban', 'Focus sull''arte urbana con installazioni interattive.', '2025-07-10', '2025-08-10', 1, 'images/sfondoArteContemporanea.jpg');

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
(3, '2025-04-01', '20:00:00', 50, 20, 1, 2);

-- Eventi per evento_id = 3
insert into fascia (id, data, orario_inizio, capienza_massima, posti_prenotati, museo_id, evento_id) 
values (11, '2025-07-13', '11:00:00', 40, 10, 1, 3), (12, '2025-07-14', '15:00:00', 35, 20, 1, 3), (13, '2025-07-15', '17:00:00', 30, 25, 1, 3),
(4, '2025-07-12', '11:00:00', 40, 15, 1, 3);


insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (1, 'La Gioconda', 'Uno dei dipinti più famosi al mondo, realizzato da Leonardo da Vinci.', 1503, 'images/OperaLaGioconda.jpg', 1, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (2, 'Le Due Frida', 'Dipinto iconico che rappresenta due versioni di Frida Kahlo.', 1939, 'images/OperaLeDueFrida.jpg', 2, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (4, 'L''Ultima Cena', 'Rappresenta l''ultima cena di Gesù con i suoi apostoli.', 1498, 'images/OperaUltimaCena.webp', 1, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (5, 'Uomo Vitruviano', 'Studio delle proporzioni del corpo umano.', 1490, 'images/OperaUomoVitruviano.jpg', 1, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (6, 'San Giovanni Battista', 'Un dipinto enigmatico con gesti simbolici.', 1513, 'images/OperaSanGiovanniBattista.jpg', 1, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (7, 'Madonna delle Rocce', 'Raffigura la Vergine Maria con il bambino Gesù e Giovanni Battista.', 1486, 'images/OperaMadonnaDelleRocce.jpg', 1, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (8, 'Autoritratto con Collana di Spine', 'Autoritratto con simboli di dolore e natura.', 1940, 'images/OperaCollanaDiSpine.jpg', 2, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (9, 'La Colonna Spezzata', 'Espressione del dolore fisico dell''artista.', 1944, 'images/OperaColonnaSpezzata.jpg', 2, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (10, 'Il Cervo Ferito', 'Metafora della sofferenza di Frida Kahlo.', 1946, 'images/OperaCervoFerito.jpg', 2, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (11, 'Autoritratto con Treccia', 'Uno dei suoi ultimi autoritratti.', 1941, 'images/OperaTreccia.jpg', 2, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (12, 'There Is Always Hope', 'Graffiti iconico con una bambina e un palloncino.', 2002, 'images/OperaHope.jpg', 3, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (13, 'Love Hurts', 'Palloncino a forma di cuore ricoperto di cerotti.', 2013, 'images/OperaLoveHurts.webp', 3, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (14, 'Love is in the Air', 'Attivista lancia un mazzo di fiori.', 2005, 'images/OperaLoveInTheAir.jpg', 3, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (15, 'Napalm', 'Critica alla guerra e all''ipocrisia occidentale.', 2004, 'images/OperaNapalm.jpeg', 3, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (16, 'Infinity Mirror Room', 'Installazione immersiva di specchi e luci.', 2013, 'images/OperaInfinityRoom.jpg', 4, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (17, 'Obliteration Room', 'Stanza interamente coperta di pois colorati.', 2011, 'images/OperaObliteration.webp', 4, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (18, 'Pumpkin', 'Zucca gialla a pois, simbolo distintivo dell''artista.', 1994, 'images/OperaPumpkin.jpeg', 4, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (19, 'Dots Obsession', 'Serie di installazioni a pois rossi e bianchi.', 2000, 'images/OperaDotsObsession.jpg', 4, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (20, 'Untitled (Skull)', 'Rappresentazione intensa di un teschio umano.', 1981, 'images/OperaUntitled.jpg', 5, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (21, 'Hollywood Africans', 'Critica della rappresentazione razziale nei media.', 1983, 'images/OperaHollywoodAfricans.webp', 5, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (22, 'Boy and Dog in a Johnnypump', 'Figura urbana dinamica e caotica.', 1982, 'images/OperaBoyDog.webp', 5, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (23, 'Irony of Negro Policeman', 'Sguardo critico sull’identità afroamericana.', 1981, 'images/OperaNegroPoliceman.jpg', 5, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (24, 'Autoritratto in Bugatti Verde', 'Simbolo dell''indipendenza e della modernità femminile.', 1929, 'images/OperaBugattiVerde.jpg', 6, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (25, 'La Bella Rafaela', 'Sensualità e classicismo in stile Art Déco.', 1927, 'images/OperaBellaRafaela.jpg', 6, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (26, 'Adamo ed Eva', 'Figura mitologica in stile geometrico e sensuale.', 1931, 'images/OperaAdamoEva.jpg', 6, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (27, 'Ritratto di Madame M.', 'Eleganza e mistero in composizione geometrica.', 1932, 'images/OperaMadameM.webp', 6, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (28, 'Radiant Baby', 'Simbolo del linguaggio visivo di Haring.', 1982, 'images/OperaRadiantBaby.webp', 7, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (29, 'Barking Dog', 'Uno dei simboli ricorrenti di Haring.', 1983, 'images/OperaBarkingDog.webp', 7, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (30, 'Best Buddies', 'Celebrazione dell''amicizia e della solidarietà.', 1990, 'images/OperaBestBuddies.jpg', 7, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (31, 'Ignorance = Fear', 'Messaggio contro la paura e l''ignoranza.', 1989, 'images/OperaIgnoranceFear.webp', 7, 1);







insert into opera_eventi (opere_id, eventi_id) values (1, 1);  -- La Gioconda → Rinascimento in Mostra
insert into opera_eventi (opere_id, eventi_id) values (2, 2);  -- Le Due Frida → Notte al Museo
insert into opera_eventi (opere_id, eventi_id) values (4, 1);
insert into opera_eventi (opere_id, eventi_id) values (5, 1);
insert into opera_eventi (opere_id, eventi_id) values (6, 1);
insert into opera_eventi (opere_id, eventi_id) values (7, 1);
insert into opera_eventi (opere_id, eventi_id) values (8, 2);
insert into opera_eventi (opere_id, eventi_id) values (9, 2);
insert into opera_eventi (opere_id, eventi_id) values (10, 2);
insert into opera_eventi (opere_id, eventi_id) values (11, 2);
insert into opera_eventi (opere_id, eventi_id) values (12, 3);
insert into opera_eventi (opere_id, eventi_id) values (13, 3);
insert into opera_eventi (opere_id, eventi_id) values (14, 3);
insert into opera_eventi (opere_id, eventi_id) values (15, 3);
insert into opera_eventi (opere_id, eventi_id) values (16, 3);
insert into opera_eventi (opere_id, eventi_id) values (17, 3);
insert into opera_eventi (opere_id, eventi_id) values (18, 3);
insert into opera_eventi (opere_id, eventi_id) values (19, 3);
insert into opera_eventi (opere_id, eventi_id) values (20, 3);
insert into opera_eventi (opere_id, eventi_id) values (21, 3);
insert into opera_eventi (opere_id, eventi_id) values (22, 3);
insert into opera_eventi (opere_id, eventi_id) values (23, 3);
insert into opera_eventi (opere_id, eventi_id) values (24, 2);
insert into opera_eventi (opere_id, eventi_id) values (25, 2);
insert into opera_eventi (opere_id, eventi_id) values (26, 2);
insert into opera_eventi (opere_id, eventi_id) values (27, 2);
insert into opera_eventi (opere_id, eventi_id) values (28, 3);
insert into opera_eventi (opere_id, eventi_id) values (29, 3);
insert into opera_eventi (opere_id, eventi_id) values (30, 3);
insert into opera_eventi (opere_id, eventi_id) values (31, 3);


insert into prenotazione (id, utente_id, fascia_id, numero_biglietti, numero_persone, stato, data_prenotazione) values (1, 1, 1, 2, 2, 'CONFERMATA', '2025-06-16');
insert into prenotazione (id, utente_id, fascia_id, numero_biglietti, numero_persone, stato, data_prenotazione) values (2, 2, 2, 3, 3, 'CONFERMATA', '2025-06-15');
insert into prenotazione (id, utente_id, fascia_id, numero_biglietti, numero_persone, stato, data_prenotazione) values (3, 3, 3, 1, 1, 'ANNULLATA',  '2025-06-14');
insert into prenotazione (id, utente_id, fascia_id, numero_biglietti, numero_persone, stato, data_prenotazione) values (4, 1, 1, 2, 2, 'CONFERMATA', '2025-06-23');
insert into prenotazione (id, utente_id, fascia_id, numero_biglietti, numero_persone, stato, data_prenotazione) values (5, 1, 5, 2, 2, 'CONFERMATA', '2025-06-23');
insert into prenotazione (id, utente_id, fascia_id, numero_biglietti, numero_persone, stato, data_prenotazione) values (6, 1, 4, 2, 2, 'CONFERMATA', '2025-06-01');
insert into prenotazione (id, utente_id, fascia_id, numero_biglietti, numero_persone, stato, data_prenotazione) values (7, 2, 2, 1, 1, 'CONFERMATA', '2025-06-23');
insert into prenotazione (id, utente_id, fascia_id, numero_biglietti, numero_persone, stato, data_prenotazione) values (8, 2, 11, 3, 3, 'CONFERMATA','2025-06-23');
insert into prenotazione (id, utente_id, fascia_id, numero_biglietti, numero_persone, stato, data_prenotazione) values (9, 2, 1, 1, 1, 'CONFERMATA', '2025-06-05');
insert into prenotazione (id, utente_id, fascia_id, numero_biglietti, numero_persone, stato, data_prenotazione) values (10, 3, 3, 4, 4, 'ANNULLATA',  '2025-06-23');
insert into prenotazione (id, utente_id, fascia_id, numero_biglietti, numero_persone, stato, data_prenotazione) values (11, 3, 12, 2, 2, 'CONFERMATA','2025-06-23');
insert into prenotazione (id, utente_id, fascia_id, numero_biglietti, numero_persone, stato, data_prenotazione) values (12, 3, 6, 1, 1, 'CONFERMATA', '2025-06-10');

select setval('museo_id_seq', (select MAX(id) from museo));
select setval('artista_id_seq', (select MAX(id) from artista));
select setval('evento_id_seq', (select MAX(id) from evento));
select setval('fascia_id_seq', (select MAX(id) from fascia));
select setval('opera_id_seq', (select MAX(id) from opera));
select setval('prenotazione_id_seq', (select MAX(id) from prenotazione));
select setval('utente_id_seq', (select MAX(id) from utente));

