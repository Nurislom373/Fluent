# Fluent - Easy Telegram Bots with Spring!

## Prerequisites

Make sure you are using Spring Boot version 3.0.0 or above.

## Getting Started

### Maven

```xml
<dependency>
    <groupId>io.github.nurislom373</groupId>
    <artifactId>spring-boot-starter-fluent</artifactId>
    <version>1.2.0</version>
</dependency>
```

### Gradle

```groovy
implementation group: 'io.github.nurislom373', name: 'spring-boot-starter-fluent', version: '1.2.0'
```

## How to Use

After adding the dependency, set up your bot by adding the bot token and username in your application.yaml file.

```yaml
fluent:
  bot:
    token: <your bot token>
    username: <your bot username>
    process-type: update
```
Once you've added the token and username, create a bot controller.

```java
@UpdateController
public class FluentController {

    private final FluentTemplate fluentTemplate;

    public FluentController(FluentTemplate fluentTemplate) {
        this.fluentTemplate = fluentTemplate;
    }

    @HandleMessage("/start")
    public void fluent(Update update) {
        Attributes attributes = FluentContextHolder.getCurrentAttributes();
        String text = update.getMessage().getText();
        fluentTemplate.sendText(text);
    }

}
```

Handling exceptions is easy too! Just create an exception controller.

```java
@ExceptionController
public class ExceptionHandler  {

    private final FluentTemplate fluentTemplate;

    public FluentController(FluentTemplate fluentTemplate) {
        this.fluentTemplate = fluentTemplate;
    }
    
    @HandleException({RuntimeException.class})
    void handleRuntimeExceptions(Update update, Throwable throwable) {
        fluentTemplate.sendText("I'm Handle Exception : " + throwable.getMessage());
    }

}
```

# Detailed documentation

## 1. Overview

This library provides a high-level abstraction for developing Telegram bots 🤖. The library simplifies the process of 
receiving updates from Telegram and sending messages 📨. Additionally, you can easily integrate this library into your 
project and customize it according to your preferences by using its latest version when developing your application 🚀. 

## 1.1 Architecture

...

## 2. Creating your bot

As written in the 'Getting Started' section of the tutorial, after adding the necessary dependency to our first program, 
you need to write the credentials related to the bot in a properties or YAML file. This alone is sufficient for now to 
start the bot and use it.

### Maven

```xml
<dependency>
    <groupId>io.github.nurislom373</groupId>
    <artifactId>spring-boot-starter-fluent</artifactId>
    <version>1.2.0</version>
</dependency>
```

### Gradle

```groovy
implementation group: 'io.github.nurislom373', name: 'spring-boot-starter-fluent', version: '1.2.0'
```

### Yaml configuration

```yaml
fluent:
  bot:
    token: <your bot token>
    username: <your bot username>
    process-type: update
```

## 2. Receiving updates
## 3. Making requests
## 4. Making keyboards
## 5. State
## 6. Interceptors
## 7. Conditions
## 8. Configure Postgresql
## 9. Inline Query
## 10. Customization

## License

[MIT License](https://github.com/Nurislom373/Fluent-Doc/blob/main/LICENSE)

© 2023 Nurislom

You have permission to use this software for free. For more details, check the [full license](https://github.com/Nurislom373/Fluent-Doc/blob/main/LICENSE).