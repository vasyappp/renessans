#language: ru
  Функционал: Вклады
    
    Структура сценария: Расчет вклада
      Когда выбран тип услуги "<serviceType>"
      И выбрана услуга "<service>"
      Тогда заголовок страницы равен "<title>"

      Когда заполнены поля калькулятора:
      |Валюта|<currency>|
      |Сумма вклада|<amount>|
      |На срок|<period>|
      |Ежемесячное пополнение|<replenishment>|

      И выставлены чекбоксы:
      |Ежемесячная капитализация|<capitalization>|
      |Частичное снятие|<withdrawal>|

      Тогда рассчитанные значения равны:
      |Ставка|<percentResult>|
      |Начислено|<earnedResult>|
      |Пополнение|<replenishResult>|
      |К снятию|<amountResult>|

      Примеры:
      |serviceType|service|title|currency|amount|period|replenishment|capitalization|withdrawal|percentResult|earnedResult|replenishResult|amountResult|
      |Вклады|Калькулятор|Рассчитайте доходность по вкладу|Рубли|2 000 000|6 месяцев|30000|true|true|6.25%|65 132,87|150 000|2 215 132,87|
      |Вклады|Калькулятор|Рассчитайте доходность по вкладу|Доллары США|50 000|9 месяцев|1000|true|false|0.75%|301,42|8 000|58 301,42|