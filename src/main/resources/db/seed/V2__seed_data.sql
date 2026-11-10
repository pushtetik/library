INSERT INTO "user" (email, password_hash, full_name, role) VALUES
                                                              ('admin@library.local', 'changeme', 'Админ Админов',   'ADMIN'),
                                                              ('ivan@library.local',  'changeme', 'Иван Иванов',     'USER'),
                                                              ('anna@library.local',  'changeme', 'Анна Смирнова',   'USER'),
                                                              ('petr@library.local',  'changeme', 'Пётр Петров',     'USER');

INSERT INTO book (title, author, isbn, published_year) VALUES
    ('Война и мир',                       'Лев Толстой',       '978-5-04-116640-3', 1869),
    ('Анна Каренина',                     'Лев Толстой',       '978-5-04-116641-0', 1877),
    ('Преступление и наказание',          'Фёдор Достоевский', '978-5-04-118285-4', 1866),
    ('Идиот',                             'Фёдор Достоевский', '978-5-04-118286-1', 1869),
    ('Мастер и Маргарита',                'Михаил Булгаков',   '978-5-04-118287-8', 1967),
    ('1984',                              'Джордж Оруэлл',     '978-5-04-118288-5', 1949),
    ('Скотный двор',                      'Джордж Оруэлл',     '978-5-04-118289-2', 1945),
    ('Понедельник начинается в субботу',  'Стругацкие',        NULL,                1965),
    ('Трудно быть богом',                 'Стругацкие',        '978-5-04-118290-8', NULL);


INSERT INTO loan (book_id, user_id, loan_date, due_date, return_date) VALUES

    (1, 2, CURRENT_DATE - 20, CURRENT_DATE - 6, CURRENT_DATE - 10),

    (3, 2, CURRENT_DATE - 15, CURRENT_DATE - 1, CURRENT_DATE - 5),

    (5, 2, CURRENT_DATE - 12, CURRENT_DATE + 2, CURRENT_DATE - 2),

    (6, 2, CURRENT_DATE - 7,  CURRENT_DATE + 7, NULL),

    (8, 3, CURRENT_DATE - 3,  CURRENT_DATE + 11, NULL);
