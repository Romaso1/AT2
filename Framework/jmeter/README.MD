# AT2 Framework for MantisBT

## Як запустити

1. Встановіть JMeter 5.6.3 або вище.
2. Розмістіть текс в `user.properties` з такими параметрами:
   ```properties
   mantis.url=http://localhost/mantis
   login.username=administrator
   login.password=root
   csv.projects=projects.csv
   csv.users=users.csv


& "C:\Users\romad\OneDrive\Рабочий стол\1\apache-jmeter-5.6.3\bin\jmeter.bat" `
   -n `
   -t "C:\Users\romad\OneDrive\Документы\GitHub\AT2\Framework\jmeter\mantis_full_test_plan.jmx" `
   -l "C:\Users\romad\OneDrive\Документы\GitHub\AT2\Framework\jmeter\results.jtl" `
   -e `
   -o "C:\Users\romad\OneDrive\Документы\GitHub\AT2\Framework\jmeter\html_report"