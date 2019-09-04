-- Получить количество неактивных (не было сессий) клиентов
SELECT COUNT(a.userid)
FROM USERS a
  LEFT JOIN USERSESSIONS b ON a.userid = b.userid
WHERE b.userid IS NULL;

-- Получить список уникальных UserId активных пользователей, которые не пользовались каналом 1.
SELECT a.userid
FROM USERSESSIONS a
  LEFT JOIN USERSESSIONS b ON a.userid = b.userid AND b.channeltype = 1
WHERE b.userid IS NULL
GROUP BY a.userid;

-- Получить максимальное UserId активного пользователя, статус которого также не равен 1.
SELECT MAX(userid)
FROM USERS
WHERE status != 1 && userid = ANY (SELECT userid
                                   FROM USERSESSIONS);

-- Получить список количества сессий с разделением на MobAppVersion. То есть список, сгруппированный по MobAppVersion, в котором для каждой
-- из MobAppVersion будет подсчитано количество сессий со "старой" mobosversion, "старой" версией считается версия ниже 80 или неуказанная.
SELECT COUNT(sessionid)
FROM USERSESSIONS
WHERE (mobosversion < 80 OR mobosversion IS NULL)
GROUP BY mobappversion;


