# Fluent - Easy Telegram Bots with Spring!

## Prerequisites

Make sure you are using Spring Boot version 3.0.0 or above.

## Getting Started

### Maven

```xml
<dependency>
    <groupId>io.github.nurislom373</groupId>
    <artifactId>spring-boot-starter-fluent</artifactId>
    <version>1.1.0</version>
</dependency>
```

### Gradle

```groovy
implementation group: 'io.github.nurislom373', name: 'spring-boot-starter-fluent', version: '1.0.0'
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

    @HandleMessage(value = "/fluent")
    void fluent(Update update, AbsSender sender) throws TelegramApiException {
        String text = update.getMessage().getText();
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

    @HandleAny(type = HandleType.MESSAGE)
    private void handleAnyMessages(Update update, AbsSender sender) throws TelegramApiException {
        String text = "I'm handle any messages";
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

    @HandleCallback(values = {"EN", "RU", "UZ"})
    private void handleCallback(Update update, AbsSender sender) throws TelegramApiException {
        String text = "Callback handler!";
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

    @HandleMessage(value = "START_WITH('/fluent', value)", scope = MatchScope.EXPRESSION)
    public void handleStartWithFluent(Update update, AbsSender sender) throws TelegramApiException {
        String text = "Handle Update With Expression";
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.execute(message);
    }

    @HandleMessage(value = "START_WITH('a', value) && END_WITH('z', value)", scope = MatchScope.EXPRESSION)
    public void handleStartWithA(Update update, AbsSender sender) throws TelegramApiException {
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), "...");
        message.setReplyToMessageId(update.getMessage().getMessageId());
        sender.execute(message);
    }

    @HandleMessage(value = "/send_username {name:[a-z]}", scope = MatchScope.VAR_EXPRESSION)
    public void handleVarExpression(Update update, AbsSender sender, @BotVariable("name") String name) throws TelegramApiException {
        String text = "Hello ".concat(name).concat("!");
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), text);
        sender.executeAsync(message);
    }

}
```

Handling exceptions is easy too! Just create an exception controller.

```java
@ExceptionController
public class ExceptionHandler  {

    @HandleException({RuntimeException.class})
    void handleRuntimeExceptions(Update update, AbsSender sender, Throwable throwable) throws TelegramApiException {
        String text = "I'm Handle Exception : " + throwable.getMessage();
        SendMessage message = new SendMessage(UpdateUtils.getUserId(update).toString(), text);
        sender.execute(message);
    }

}
```

## License

[MIT License](https://github.com/Nurislom373/Fluent-Doc/blob/main/LICENSE)

© 2023 Nurislom

You have permission to use this software for free. For more details, check the [full license](https://github.com/Nurislom373/Fluent-Doc/blob/main/LICENSE).