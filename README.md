<h1 align="center">Привет, Я <a href="https://t.me/tryingescape" target="_blank">Хасан</a> 
<img src="https://github.com/blackcater/blackcater/raw/main/images/Hi.gif" height="32"/></h1>

[![Typing SVG](https://readme-typing-svg.herokuapp.com?font=Fira+Code&pause=1000&center=true&width=1000&lines=%D0%AF+%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D1%89%D0%B8%D0%BA-%D0%B0%D0%B2%D1%82%D0%BE%D0%BC%D0%B0%D1%82%D0%B8%D0%B7%D0%B0%D1%82%D0%BE%D1%80+%D0%BD%D0%B0+Java)](https://git.io/typing-svg)
<h3 align="center">Компания Performance Lab </h3>
<h3 align="center">Это мой проект в рамках ИПР </h3>

Проект содержит в себе 2 модуля : <a href="https://github.com/Iamimprovingmyself/TestPflb/tree/main/src/main/java/ru/dns/framework" target="_blank">UI<a/> и <a href ="https://github.com/Iamimprovingmyself/TestPflb/tree/main/src/main/java/ru/framework/db/pflb" target="_blank">API<a/>     
  UI часть реализована с помощью:
  1.  Selenium
  2.  Junit-4
  3.  Cucumber-5
  4.  Allure для формирования отчетов
  
  Использован паттерн page-object,
  страницы описаны в  <a href="https://github.com/Iamimprovingmyself/TestPflb/tree/main/src/main/java/ru/dns/framework/pages" target="_blank">Pages<a/> , шаги в <a href="https://github.com/Iamimprovingmyself/TestPflb/tree/main/src/main/java/ru/dns/framework/steps/Steps.java" target="_blank">Steps<a/>
  
  Тесты лежат в <a href="https://github.com/Iamimprovingmyself/TestPflb/tree/main/src/test/resources/features" target="_blank">features<a/>
  
  Для запуска ui тестов использовать файл "src/test/java/ru/dns/framework/<a href="https://github.com/Iamimprovingmyself/TestPflb/tree/main/src/main/java/ru/dns/framework/steps/Steps.java" target="_blank">CucumberRunner.Java<a/>"
  
  API часть реализована с помощью:
  1. Rest Assured
  2. Junit-5
  3. Allure для формирования отчетов
  
  Формирование спецификаций запросов реализовано в <a href="https://github.com/Iamimprovingmyself/TestPflb/blob/main/src/test/java/ru/framework/db/pflb/Config.java" target="_blank">Config<a/>,
шаги описаны в <a href="https://github.com/Iamimprovingmyself/TestPflb/blob/main/src/test/java/ru/framework/db/pflb/Steps.java" target="_blank">Steps<a/>

  тесты в файле <a href="https://github.com/Iamimprovingmyself/TestPflb/blob/main/src/test/java/ru/framework/db/pflb/StepTest.java" target="_blank">StepTest<a/>
  
Для запуска тестов модуля api использовать команду:                        
  mvn clean test -Dlogin=*login* -Dpassword=*password* -DforkCount=0 allure:serve


