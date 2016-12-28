## Web приложение "Туристическая компания" @author Artem Faenko
### 1 Общие сведения

**1.1 Стек технологий:**
- Spring(MVC, Security), Hibernate, JS, jQuery(ajax, autocomplete), JSP, CSS, сборщик Gradle, база данных MySQL 

**1.2 Библиотеки:**
- Загрузка/Выгрузка файлов *Apache Commons FileUpload*
- Поиск с ограничениями *Hibernate Criteria API*
- Генерация PDF документов *iTextpdf*
- Парсинг сайтов авиабилетов и отелей *Selenium*, *Jsoup*
- Аутентификация пользователей с хешированием паролей(bcrypt) *Spring Security*
- Локализация языков *Internationalization (i18n)*
- Логирование *slf4j*
- Просмотр изображений *fancybox*
- Просмотр карт *Google Maps API*

**1.3 Требования для запуска проекта:**
- Данные для авторизации - login: **admin** password: **123**
- Настройки Hibernate в файле WEB-INF/classes/application.yml
- Backup базы MySQL лежит в папке DataBase_dump (username:"root", password:"root")
- Файл виртуального браузера в папке WEB-INF/classes/phantomjs/bin/phantomjs.exe
- Приложение тестировалось в Mozilla Firefox

***
### 2 Страница Логина
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/log_s.png)

### 3 Главная страница
**3.1 Графики:** 
- Заказов, Туристов, Выручки за неделю.

**3.2 Вывод количества:** 
- Заказов, Туров, Туристов.
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/ind_1.png)

**3.3 Частичная локализация на английский страниц сайта. Смена локали нажатием на ссылку** 

![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/lang_1.png)

### 4 Страница туристов
**4.1 Добавление туриста** 

![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/c_d_1.png)

**4.2 Редактирование туриста** 
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/c_d_2.png)

**4.3 Быстрый поиск туриста по ФИО (autocomplete)** 

![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/c_p_b.png)

**4.4 Поиск по выборке из нескольких параметров (дата, тел., ФИО)** 

![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/c_p.png)

**4.5 Просмотр туриста, внутрениий переход по вкладкам:**
- Документы - скачать, просмотр, закачать документы(pdf, jpg, word). При составлении заказа автоматически формируються документы договора.
- Туры - просмотр всех туров туриста.
- Заказы - просмотр всех заказов туриста.

![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/c_o_1.png)
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/c_o_2.png)
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/c_o_3.png)
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/c_o_4.png)

### 5 Страница туров
**5.1 Добавление тура**

**Для поиска авиабилетов и номеров отелей был использован парсинг динамических сайтов.**
- Использовались две библиотеки *Selenium* и *Jsoup*.

![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/t_d_2.png)
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/t_d_3.png)
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/t_d_4.png)
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/t_d_8.png)
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/t_d_5.png)
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/t_d_6.png)

**5.2 Просмотр тура**

![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/t_o_1.png)
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/t_o_2.png)
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/t_o_3.png)

**5.3 Поиск туров**

![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/z_p_2.png)

### 6 Страница заказов
**6.1 Добавление заказа**

![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/z_d_1.png)
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/z_d_2.png)
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/z_d_3.png)

**6.2 Просмотр заказа**

![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/z_o_1.png)


### 7 Панель администратора
**7.1 Вкладки добавления(редактирования) пользователей, логи операций, выход из системы.**

В истеме 3 вида ролей пользователей:
- Администратор
- Менеджер
- Бухгалтер

![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/ad_1.png)
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/ad_2.png)

### 8 Отчеты
**8.1 Отчет прибыли за неделю**

![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/otch.png)


**8.2 Документы формирующиеся при составлении заказа**
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/dog_1.png)
![CC0](https://github.com/webserverby/travel-spring-hibernate/blob/master/screenshots/dog_2.png)


