# **Задание№1**

Создать приложение, которое сможет считывать из файла набор фигур (должно быть не менее 4 различных типов фигур, и при этом хоть один параметр общий). 
Желательно использовать _абстрактную фабрику_.

Доступны должно быть следующие действия:
<li>повернуть на
<li> переместить на
<li> увеличить/ уменьшить в
<li> рассчитать площадь

По Вашему усмотрению программа может быть с выводом графическим ( консоль, графическое приложение, веб-проект), либо без (таким образом на вход файл - результат тоже файл)

# **Реализация**

Программа запускается с основного меню.

При создании фигур используется _абстрактная фабрика_

### Основное меню:

Пользователю доступны следующие действия:

1 - Вывод всех фигур 
<li>данные считываются с файла Figure.txt, на их основе создаются фигуры. Информация о фигурах выводится в консоль.

2 - Выбор фигуры 
<li>данные считываются с файла Figure.txt, на их основе создаются фигуры. Пользователю выводится информация о количестве фигур и предлагается выбрать фигуру. После выбора происходит переход на подменю "Действия с фигурой".

3 - Добавление фигуры 
<li>с консоли вводятся координаты. На основе введенных координат создается фигура. Пользователю выводится информация о созданной фигуре, изменения сохраняются в файл Figure.txt

4 - Удаление фигуры 
<li>данные считываются с файла Figure.txt, на их основе создаются фигуры. Пользователю выводится информация о количестве фигур и предлагается выбрать удаляемую фигуру. Изменения сохраняются в файл Figure.txt

5 - Выход

### Подменю "Действия с фигурой":
1 - Поворот фигуры

2 - Перемещение фигуры

3 - Масштабирование фигуры

4 - Рассчитать площадь

5 - Вернуться в главное меню

Для совершения операций создается копия фигуры.
При выборе действий 1-3 подменю пользователю после внесенных изменений предлагается сохранить новую фигуру (подменю "Сохранение изменений").

### Подменю "Сохранение изменений":
1 - Сохранить

2 - Отмена

При выборе пункта 1 - фигура, выбранная в основном меню, заменяется на копию, полученную в подменю "Действия с фигурой". Изменения сохраняются в файл Figure.txt.

