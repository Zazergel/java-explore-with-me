<div class="header" markdown="1" align="center">

  ![Logo](https://imgur.com/Ga9QQJ6.png)
</div>
<h1 align="center">Explore with me</h1>
  <p align="center">
    Explore new events with your friends!
    <br/>
    <a href="https://github.com/Zazergel/java-explore-with-me/issues">Report Bug</a> *
    <a href="https://github.com/Zazergel/java-explore-with-me/issues">Request Feature</a>
  </p>
  <div class="header" markdown="1" align="center">

  ![Downloads](https://img.shields.io/github/downloads/Zazergel/java-explore-with-me/total) 
  ![Contributors](https://img.shields.io/github/contributors/Zazergel/java-explore-with-me?color=dark-green) 
  ![Forks](https://img.shields.io/github/forks/Zazergel/java-explore-with-me?style=social) 
  ![Issues](https://img.shields.io/github/issues/Zazergel/java-explore-with-me) 
</div>

## Описание

Свободное время — ценный ресурс. Ежедневно мы планируем, как его потратить — куда и с кем сходить. Сложнее всего в таком планировании поиск информации и переговоры. Нужно учесть много деталей: какие намечаются мероприятия, свободны ли в этот момент друзья, как всех пригласить и где собраться.<br>
**Explore with me** — приложение-афиша. В нем можно предложить какое-либо событие от выставки до похода в кино и собрать компанию для участия в нём.

Приложение состоит из основного сервиса, основной базы данных и сервиса статистики, базы данных статистики.
Имеется возможность запускать модули в отдельных docker контейнерах. 

Функционал приложения протестирован с помощью Postman коллекций

## Endpoints
  [Stats-service API](https://github.com/Zazergel/java-explore-with-me/blob/main/ewm-stats-service-spec.json)<br><br>
  [Main-service API](https://github.com/Zazergel/java-explore-with-me/blob/main/ewm-main-service-spec.json) 
<details>
  <summary><b>API for comments feature</b></summary>
  
   - ```[GET] /admin/comments?from={from}&size={size}``` – получить список всех комментариев с пагинацией
   - ```[DELETE] /admin/comments/{commentId}``` – удалить комментарий ```commentId```
   - ```[POST] /users/{userId}/comments?eventId={eventId}``` – создать новый комментарий к событию ```eventId``` пользователем ```userId```
   - ```[PATCH] /users/{userId}/comments/{commentId}``` – обновить свой комментарий ```commentId``` пользователем ```userId```
   - ```[DELETE] /users/{userId}/comments/{commentId}``` - удалить свой комментарий ```commentId``` пользователем ```userId```
   - ```[GET] /users/{userId}/comments?eventId={eventId}&from={from}&size={size}``` - получить список всех комментариев пользователя ```userId``` к событию ```eventId``` с пагинацией
   - ```[GET] /users/{userId}/comments?from={from}&size={size}``` - получить список всех комментариев пользователя ```userId``` с пагинацией
   - ```[GET] /comments?eventId={eventId}&from={from}&size={size}``` – получить список всех комментариев к событию ```eventId``` с пагинацией
   - ```[GET] /comments/{commentId}``` – получить комментарий ```commentId```
</details>


## Схема базы данных основного сервиса

<details>
  <summary><b>DB scheme</b></summary>

   ![App Screenshot](https://imgur.com/szMLevI.png)
</details>

# Build with

<p align="left">
    <img src="https://skillicons.dev/icons?i=java,maven,spring,postgres,hibernate,docker" />
</p>

### Installation

1. Склонируйте репозиторий
```sh
git clone https://github.com/Zazergel/java-explore-with-me.git
```
2. Для сборки проекта используйте [Maven](https://maven.apache.org/)
3. Установите [Docker](https://www.docker.com/products/docker-desktop/)
4. Соберите проект, выполнив команду:
```sh
mvn clean install
 ```
6. Запустите проект с помощью
```sh
docker-compose up
```


## Authors

 **[Zazergel](https://github.com/Zazergel/)**


[*Ссылка на финальный PR*](https://github.com/Zazergel/java-explore-with-me/pull/5)


