🃏clean-code-deck 🃏
---

### Description ?

> This **project** is related to this description :
>> This game is a game of
> > cards to collect, evolve and with which players can face the cards
> > other players. These cards are heroes, each with their specificities and rarity.
>
> The goal of this application it's to learn how to build a Hexagonal Architecture
> with SOLID principles and base code tested.
---

### Requirements ?

#### Install :

* Java17
    * macOS : execute the command in the terminal ``brew install openjdk@17``
    * Windows : see the [tutorial](https://java.tutorials24x7.com/blog/how-to-install-java-17-on-windows)
* [IntelliJ](https://www.jetbrains.com/idea/download/?source=google&medium=cpc&campaign=9736964302&term=intellij%20idea&content=602143185538&gclid=CjwKCAiArY2fBhB9EiwAWqHK6p_4ouOGjREHl3lQVuOk2uDV1zVjgXv85LKubo850OYSnTweu859shoCUToQAvD_BwE#section=mac) (
  IDEA)
* Maven3
    * macOS : execute the command in the terminal ``brew install maven``
    * Windows : see the [tutorial](https://maven.apache.org/install.html)
* [Docker](https://www.docker.com/) (for postgres database)
* [Postman](https://www.postman.com/) (for test REST API)

---

### How to use ?

#### Before

* Create a external volume on Docker and name it "clean-code-deck".

#### Run

```bash
docker-compose up -d
```

> This command will bring the database up on a local environment.
**After that you can run the *Spring Application* on IntelliJ ! 🚀**

#### After (Stop)

* Stop the *Spring Application* on IntelliJ.
* ```docker-compose stop```

> Be sure that you don't use ```docker-compose down``` to not clear the volume of the database and clear the data
> inside.
---

### Contributing

* MARLEIX Noé -> [noeoxycode](https://github.com/noeoxycode)
* MOUCHON Sacha -> [cawa-dev](https://github.com/cawa-dev)

---

### License

[MIT](https://choosealicense.com/licenses/mit/)
