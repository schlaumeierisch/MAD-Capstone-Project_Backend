-- language
insert into language(id, language_id, name)
values (001, 'dom-id-lang-001', 'German'),
       (002, 'dom-id-lang-002', 'Spanish'),
       (003, 'dom-id-lang-003', 'Dutch');

-- difficulty level
insert into difficulty_level(id, difficulty_level_id, name, language_id)
values (001, 'dom-id-diff-001', 'Level 1', 'dom-id-lang-001'),
       (002, 'dom-id-diff-002', 'Level 2', 'dom-id-lang-001'),
       (003, 'dom-id-diff-003', 'Level 3', 'dom-id-lang-001'),
       (004, 'dom-id-diff-004', 'Level 1', 'dom-id-lang-002'),
       (005, 'dom-id-diff-005', 'Level 2', 'dom-id-lang-002'),
       (006, 'dom-id-diff-006', 'Level 3', 'dom-id-lang-002'),
       (007, 'dom-id-diff-007', 'Level 1', 'dom-id-lang-003'),
       (008, 'dom-id-diff-008', 'Level 2', 'dom-id-lang-003'),
       (009, 'dom-id-diff-009', 'Level 3', 'dom-id-lang-003');

-- flashcard
insert into flashcard(id, flashcard_id, front, back, difficulty_level_id)
values (001, 'dom-id-fc-001', 'Hello', 'Hallo', 'dom-id-diff-001'),
       (002, 'dom-id-fc-002', 'Thank you', 'Danke', 'dom-id-diff-001'),
       (003, 'dom-id-fc-003', 'Tree', 'Baum', 'dom-id-diff-002'),
       (004, 'dom-id-fc-004', 'Chair', 'Stuhl', 'dom-id-diff-002'),
       (005, 'dom-id-fc-005', 'Airplane', 'Flugzeug', 'dom-id-diff-003'),
       (006, 'dom-id-fc-006', 'Bottle', 'Flasche', 'dom-id-diff-003'),
       (007, 'dom-id-fc-007', 'Hello', 'Hola', 'dom-id-diff-004'),
       (008, 'dom-id-fc-008', 'Thank you', 'Gracias', 'dom-id-diff-004'),
       (009, 'dom-id-fc-009', 'Apple', 'Manzana', 'dom-id-diff-005'),
       (010, 'dom-id-fc-010', 'Ich love you', 'Te amo', 'dom-id-diff-005'),
       (011, 'dom-id-fc-011', 'You are welcome', 'De nada', 'dom-id-diff-006'),
       (012, 'dom-id-fc-012', 'Sorry', 'Perdon', 'dom-id-diff-006'),
       (013, 'dom-id-fc-013', 'Hello', 'Dag', 'dom-id-diff-007'),
       (014, 'dom-id-fc-014', 'Thank you', 'Bedankt', 'dom-id-diff-007'),
       (015, 'dom-id-fc-015', 'Girl', 'Meisje', 'dom-id-diff-008'),
       (016, 'dom-id-fc-016', 'Boy', 'Jongen', 'dom-id-diff-008'),
       (017, 'dom-id-fc-017', 'Newspaper', 'Krant', 'dom-id-diff-009'),
       (018, 'dom-id-fc-018', 'Television', 'Televisie', 'dom-id-diff-009');

-- history entry
insert into history_entry(id, history_entry_id, language, difficulty_level, total_answers, correct_answers, date)
values (001, 'dom-id-hist-001', 'German', 'Level 1', '2', '2', '2022-12-12 14:21'),
       (002, 'dom-id-hist-002', 'German', 'Level 3', '2', '1', '2022-12-15 18:45'),
       (003, 'dom-id-hist-003', 'Spanish', 'Level 2', '2', '1', '2022-12-21 19:03'),
       (004, 'dom-id-hist-004', 'Spanish', 'Level 3', '2', '0', '2022-12-23 09:13'),
       (005, 'dom-id-hist-005', 'Dutch', 'Level 2', '2', '2', '2022-12-18 13:12'),
       (006, 'dom-id-hist-006', 'Dutch', 'Level 2', '2', '2', '2022-12-11 21:10');