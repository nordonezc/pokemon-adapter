# Pokeapi Adapter

* Checkout the application in this [link](https://pokemon-adapter.herokuapp.com/).
* Checkout a web application which consume this project [here](https://niki-pokedex.herokuapp.com/) and its [repository](https://github.com/nordonezc/pokedex)
* This project was build with [Maven](https://maven.apache.org/)


## Build

Build with [Spring Boot]()
>
     <parent>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-parent</artifactId>
         <version>2.3.0.RELEASE</version>
         <relativePath/>
     </parent>


## Deployment on production

### Heroku

[![N|Solid](https://www3.assets.heroku.com/assets/logo-purple-08fb38cebb99e3aac5202df018eb337c5be74d5214768c90a8198c97420e4201.svg)](https://www.heroku.com/)


Regarding to the heroku configuration

1. Create a free account in Heroku
2. Create a new app in Heroku
3. Go to the **deploy** tab and connect with your github repository then grant the necessary privileges

After that, you have to alternatives:
- **Manual**: Keep reading
- **Automatic**: Go to Travis CI section

In the **deploy** tab, at the end of the page you could select the branch of your github repository and click **Deploy branch**

After few minutes in the top of your heroku account you can click on **Open app** button and this will redirect you to your application


## Automatic deployment with Travis CI

It is important to say that all the configuration done with heroku section it is mandatory no matter it is manual or automatic deployment. 

### Application Configuration
In the root directory of your application it is necesary to add the file **.travis.yml** with the language that is being used.

### Travis CI

[![N|Solid](https://miro.medium.com/max/600/1*M-Kj85siknLr66JqJ71PRA.png)](https://travis-ci.org/)

1. Create a free account
2. Link with your github account
3. Grant the priviledges to the desired repositories

#### Heroku
In the **deploy** tab, go to **Automatic deploy** 

#### Finally

With the configuration done. Every time you commit and push changes to your repository will update your application

## Running unit tests

The unit tests was build with [JUnit](https://junit.org/junit5/) and [AssertJ](https://assertj.github.io/doc/)
> 
          <dependency>  
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <scope>test</scope>
          </dependency>
          <dependency>
              <groupId>org.assertj</groupId>
              <artifactId>assertj-core</artifactId>
              <scope>test</scope>
          </dependency>


## Utilities

The project used [Lombok](https://projectlombok.org/), in order to add methods through to annotations

          <dependency>
              <groupId>org.projectlombok</groupId>
              <artifactId>lombok</artifactId>
              <version>${lombok.version}</version>
          </dependency>


The project used [Vavr](https://www.vavr.io/) aiming to close to functional programming

          <dependency>
              <groupId>io.vavr</groupId>
              <artifactId>vavr</artifactId>
              <version>${vavr.version}</version>
          </dependency>
