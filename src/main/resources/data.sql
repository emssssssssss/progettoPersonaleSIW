insert into museo (id, nome, descrizione, indirizzo, orari_apertura)values (1,'Museo d Arte Moderna','Un museo dedicato all arte contemporanea e moderna.','Via delle Belle Arti 12, Roma','09:00 - 19:00');

insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (1, 'Leonardo da Vinci', 'Leonardo da Vinci è stato uno dei più grandi geni del Rinascimento italiano, eccellente pittore, ingegnere, architetto, scienziato e inventore. Le sue opere combinano arte e scienza, e la sua curiosità enciclopedica lo ha reso una figura emblematica della cultura universale.', 1452, 1519, 'images/profiloLeonardoDaVinci.jpg', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (2, 'Frida Kahlo', 'Frida Kahlo è stata una pittrice messicana simbolo della forza femminile, nota per i suoi autoritratti carichi di dolore, simbolismo e identità culturale. La sua arte riflette le sue sofferenze fisiche e interiori, rendendola un''icona del surrealismo e del femminismo.', 1907, 1954, 'images/profiloFridaKahlo.webp', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (3, 'Banksy', 'Banksy è un enigmatico artista britannico di street art, conosciuto per le sue opere provocatorie, ironiche e socialmente impegnate, spesso realizzate in spazi pubblici. Nonostante la fama mondiale, la sua identità resta avvolta nel mistero.', 1974, NULL, 'images/profiloBansky.jpg', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (4, 'Yayoi Kusama', 'Yayoi Kusama è un''artista giapponese celebre per le sue installazioni immersive, le sue opere psichedeliche e i caratteristici motivi a pois. La sua arte, profondamente influenzata dalle sue esperienze psicologiche, ha avuto un impatto significativo sulla pop art, il minimalismo e l''arte contemporanea.', 1929, NULL, 'images/profiloYayoiKusama.jpg', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (5, 'Jean-Michel Basquiat', 'Jean-Michel Basquiat è stato un artista statunitense di origini haitiane e portoricane, noto per i suoi dipinti graffianti e viscerali che uniscono graffiti, testi e simbolismo. Esploso nella scena newyorkese degli anni ''80, è diventato un''icona dell''arte contemporanea prima della sua prematura scomparsa.', 1960, 1988, 'images/profiloJeanMichelBasquiat.png', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (6, 'Tamara de Lempicka', 'Tamara de Lempicka è stata una pittrice polacca celebre per il suo stile Art Déco, caratterizzato da eleganza, linee geometriche e sensualità. Le sue opere raffigurano donne sofisticate e sicure di sé, incarnando lo spirito moderno degli anni ''20 e ''30.', 1898, 1980, 'images/profiloTamaraDeLempicka.jpg', 1);
insert into artista (id, nome, biografia, anno_nascita, anno_morte, immagine_url, museo_id) values (7, 'Keith Haring', 'Keith Haring è stato un artista e attivista statunitense, noto per i suoi graffiti dallo stile immediato e per l''uso di simboli ricorrenti come bambini, cani e cuori. La sua arte, spesso a sfondo sociale, ha affrontato temi come l''AIDS, la guerra, la droga e i diritti civili.', 1958, 1990, 'images/profiloKeithHaring.jpg', 1);


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


insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (1, 'La Gioconda', 'Capolavoro rinascimentale di Leonardo da Vinci, celebre per il misterioso sorriso della protagonista e per l''uso innovativo dello sfumato, considerato uno dei dipinti più studiati e visitati al mondo.', 1503, 'images/OperaLaGioconda.jpg', 1, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (2, 'Le Due Frida', 'Opera simbolica in cui Frida Kahlo rappresenta due versioni di sé stessa, una vestita in abiti europei e l''altra in abiti tradizionali messicani, esplorando il suo senso di identità e il dolore per la separazione da Diego Rivera.', 1939, 'images/OperaLeDueFrida.jpg', 2, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (4, 'L''Ultima Cena', 'Famosa rappresentazione dell''ultima cena di Gesù con i dodici apostoli, in cui Leonardo cattura le reazioni emotive dei discepoli nel momento in cui Gesù rivela il tradimento imminente.', 1498, 'images/OperaUltimaCena.webp', 1, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (5, 'Uomo Vitruviano', 'Disegno iconico di Leonardo che illustra le proporzioni ideali del corpo umano secondo Vitruvio, unendo arte e scienza in un unico studio di armonia anatomica.', 1490, 'images/OperaUomoVitruviano.jpg', 1, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (6, 'San Giovanni Battista', 'Dipinto enigmatico e spirituale, in cui Leonardo raffigura San Giovanni con un gesto indicativo e uno sguardo penetrante, immerso in una luce soffusa.', 1513, 'images/OperaSanGiovanniBattista.jpg', 1, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (7, 'Madonna delle Rocce', 'Opera sacra che raffigura la Vergine Maria con il Bambino Gesù, San Giovanni Battista e un angelo, ambientati in un paesaggio roccioso e simbolico, ricco di dettagli e profondità.', 1486, 'images/OperaMadonnaDelleRocce.jpg', 1, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (8, 'Autoritratto con Collana di Spine', 'Autoritratto emblematico di Frida Kahlo, in cui l''artista si raffigura con una collana di spine e un colibrì morto, circondata da simboli di sofferenza e natura.', 1940, 'images/OperaCollanaDiSpine.jpg', 2, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (9, 'La Colonna Spezzata', 'Frida si ritrae con il busto aperto da cui emerge una colonna spezzata, simbolo del dolore fisico causato dall''incidente subito e delle sue frequenti operazioni.', 1944, 'images/OperaColonnaSpezzata.jpg', 2, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (10, 'Il Cervo Ferito', 'Metafora della sofferenza emotiva e fisica, dove Frida si rappresenta come un cervo colpito da frecce, in un paesaggio inquietante e surreale.', 1946, 'images/OperaCervoFerito.jpg', 2, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (11, 'Autoritratto con Treccia', 'Uno degli ultimi autoritratti di Frida, realizzato in un momento di fragilità fisica, dove la treccia e il volto sereno diventano simbolo di resistenza e identità.', 1941, 'images/OperaTreccia.jpg', 2, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (12, 'There Is Always Hope', 'Uno dei graffiti più celebri di Banksy, con una bambina che lascia volare via un palloncino a forma di cuore: simbolo di speranza, amore perduto e desiderio di libertà.', 2002, 'images/OperaHope.jpg', 3, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (13, 'Love Hurts', 'Il cuore rosso, simbolo di amore, è ricoperto di cerotti in questo graffito di Banksy, che riflette sulle ferite emotive causate dalle relazioni e dall''abbandono.', 2013, 'images/OperaLoveHurts.webp', 3, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (14, 'Love is in the Air', 'Opera iconica in cui un manifestante lancia un mazzo di fiori anziché una molotov: potente messaggio visivo di protesta pacifica e speranza.', 2005, 'images/OperaLoveInTheAir.jpg', 3, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (15, 'Napalm', 'Banksy unisce immagini contrastanti per denunciare l''orrore della guerra del Vietnam e la superficialità dell''Occidente, in un''immagine scioccante e provocatoria.', 2004, 'images/OperaNapalm.jpeg', 3, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (16, 'Infinity Mirror Room', 'Installazione immersiva composta da pareti di specchi e luci LED, che crea l''illusione di uno spazio infinito, invitando lo spettatore a riflettere sulla propria percezione.', 2013, 'images/OperaInfinityRoom.jpg', 4, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (17, 'Obliteration Room', 'Stanza bianca completamente trasformata dai visitatori che applicano adesivi colorati, simboleggiando la partecipazione collettiva e la dissoluzione dell''ordine visivo.', 2011, 'images/OperaObliteration.webp', 4, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (18, 'Pumpkin', 'Scultura raffigurante una grande zucca gialla decorata con pois neri, elemento ricorrente nell''immaginario di Kusama, simbolo di infanzia, stabilità e ossessione.', 1994, 'images/OperaPumpkin.jpeg', 4, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (19, 'Dots Obsession', 'Serie di installazioni in cui l''artista utilizza pois rossi e bianchi per creare ambienti immersivi, allucinatori e ripetitivi, specchio delle sue visioni interiori.', 2000, 'images/OperaDotsObsession.jpg', 4, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (20, 'Untitled (Skull)', 'Dipinto energico e inquieto in cui Jean-Michel Basquiat raffigura un teschio umano scomposto, simbolo della morte e della fragilità dell''esistenza, eseguito con linee grezze e colori contrastanti.', 1981, 'images/OperaUntitled.jpg', 5, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (21, 'Hollywood Africans', 'Opera di denuncia della discriminazione razziale, in cui Basquiat ritrae sé stesso e due amici nel contesto di Hollywood, con testi e simboli che criticano gli stereotipi imposti ai neri nei media.', 1983, 'images/OperaHollywoodAfricans.webp', 5, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (22, 'Boy and Dog in a Johnnypump', 'Composizione frenetica e colorata che mostra una figura maschile e un cane davanti a una fontana cittadina, evocando l''energia pulsante delle strade newyorkesi e l''infanzia urbana.', 1982, 'images/OperaBoyDog.webp', 5, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (23, 'Irony of Negro Policeman', 'Basquiat rappresenta un poliziotto afroamericano in modo grottesco e spettrale, criticando le dinamiche di potere e le contraddizioni interne all''identità razziale in contesti oppressivi.', 1981, 'images/OperaNegroPoliceman.jpg', 5, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (24, 'Autoritratto in Bugatti Verde', 'Celebre ritratto di Tamara de Lempicka in cui si autorappresenta al volante di una lussuosa Bugatti, icona di femminilità moderna, indipendenza e stile Art Déco raffinato.', 1929, 'images/OperaBugattiVerde.jpg', 6, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (25, 'La Bella Rafaela', 'Ritratto sensuale e composto ispirato alla bellezza classica, in cui de Lempicka esplora il corpo femminile attraverso il filtro geometrico ed elegante dell''Art Déco.', 1927, 'images/OperaBellaRafaela.jpg', 6, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (26, 'Adamo ed Eva', 'Rivisitazione moderna del mito biblico, con figure stilizzate e plastiche immerse in uno spazio geometrico e raffinato, espressione del gusto moderno e simbolico di de Lempicka.', 1931, 'images/OperaAdamoEva.jpg', 6, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (27, 'Ritratto di Madame M.', 'Composizione sofisticata che ritrae una donna elegante e altera, tipica delle muse di Tamara de Lempicka, con forti contrasti cromatici e forme stilizzate.', 1932, 'images/OperaMadameM.webp', 6, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (28, 'Radiant Baby', 'Uno dei simboli più noti di Keith Haring, il “bambino radiante” rappresenta purezza, energia vitale e speranza, realizzato con le sue linee marcate e il linguaggio visivo diretto.', 1982, 'images/OperaRadiantBaby.webp', 7, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (29, 'Barking Dog', 'Icona ricorrente nelle opere di Haring, il “cane che abbaia” incarna l''urgenza di comunicazione, protesta sociale e istinto primordiale, con uno stile semplice ma potente.', 1983, 'images/OperaBarkingDog.webp', 7, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (30, 'Best Buddies', 'Opera gioiosa e simbolica che celebra l''amicizia e la solidarietà tra individui, raffigurati attraverso sagome stilizzate in un abbraccio vivace e ottimista.', 1990, 'images/OperaBestBuddies.jpg', 7, 1);
insert into opera (id, titolo, descrizione, anno, immagine_url, artista_id, museo_id) values (31, 'Ignorance = Fear', 'Poster politico di Haring contro l''ignoranza e la paura legate all''AIDS, raffigurante tre figure stilizzate che si coprono occhi, orecchie e bocca, simbolo di silenzio e stigma sociale.', 1989, 'images/OperaIgnoranceFear.webp', 7, 1);










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

