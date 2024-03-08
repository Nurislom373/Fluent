# Fluent - Easy Telegram Bots with Spring!

## Overview

This library provides a high-level abstraction for developing Telegram bots 🤖. The library simplifies the process of
receiving updates from Telegram and sending messages 📨. Additionally, you can easily integrate this library into your
project and customize it according to your preferences by using its latest version when developing your application 🚀.

## Prerequisites

* Fluent 1.2.0 requires at least Java 17, Spring Boot version 3.0.0 or above.

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

## 1. Creating your bot

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

Let's see how we can receive requests that have come from Telegram in this section.

### 2.1 Controllers

We need to apply the `@UpdateController` annotation to classes that process requests coming from Telegram. This is necessary 
because when your application starts, the classes with the `@UpdateController` annotation are collected by the Fluent provider,
which then directs incoming requests to the methods of these classes.

```java
@UpdateController
public class FluentController {
    
    // ...
    
}
```

### 2.2 Handle message

Let's explore how we can write methods to process requests coming from Telegram in this section.

Let's start by discussing how to handle text messages that come through Telegram.
To handle text messages that come through Telegram, we use the @HandleMessage annotation. The @HandleMessage annotation 
allows us to implement various ways of handling text messages, such as using regex or expressions. It provides us with 
different paths to carry out the handling. Let's take a look at all of these.

```java
@HandleMessage("/start")
public void startExample() {
    fluentTemplate.sendText("Hello World!!!");
}
```

Tepada yozilgan kodda `@HandleMessage` annotatsiyasidan foydalanib telegramdan kelgan `/start` matnli xabarni ushlovchi method yozilgan.
Kelgan xabar `/start` bo'lsagina ushbu method ishga tushadi. Kelgan xabarni boshida yoki oxirida `/start` xabar bo'lsa unday bo'lmaydi.

Bunday bo'lishini yani kelgan matnli xabar to'liq `@HandleMessage` berilgan matnga mos bo'lsagina ishga tushishini sababi ko'rib chiqamiz.
`@HandleMessage` annotatsiyaini ichiga ko'rib ko'rishimiz mumkin.

```java
@ProcessUpdate
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleMessage {

    String value() default "/";

    MatchType match() default MatchType.EQUALS;
}
```

Tepadagi `@HandleMessage` annotatsiyasini kodida ko'zingiz tushgan bo'lishi mumkin `match()` methodiga default qiymati `EQUALS`
turibdi. Bundan chunishimiz mumkin default holatda ushbu annotatsiyaga berilgan xabar telegramdan kelgan matnli xabar bilan to'liq mos kelishini tekshiradi.
biz ushbu annotatsiyani match methodiga boshqa `MatchType` berish orqali kelgan xabarni tekshirish strategiyasini o'zgartirishimiz mumkin.
Misol uchun `message: ` bilan boshlangan istalgan matnli xabarni ulashni ko'ramiz. Buning uchun `@HandleMessage` annotatsiyasi qo'yilgan
methodni `MatchType` ni o'zgartirishimiz kerak. Pastdagi kodga ergashing:

```java
@HandleMessage(value = "message:", match = MatchType.START_WITH)
public void startWithExampleHandler() {
    fluentTemplate.sendText("I handle requests start with 'message:'");
}
```

### 2.3 Handler method add update parameter

Ushbu bo'limda handler methodlarga update parameterni qo'shish ko'ramiz. Pastdagi kodga ergashing:

```java
@HandleMessage("/start")
public void startExample(Update update) {
    fluentTemplate.sendText("Hello World!!!");
}
```

Tepadagi kodda ko'rganingizdek handler methodlarga update parameter qo'shish imkoni bor. Telegramdan kelgan update ni
parameter sifatida kutsangiz, ushbu handler methodga update parameteri kiritilgan holatda chaqiriladi. Bu sizga update 
objectdan o'zingizga kerak qiymatlarni olish imkoni beradi.

### MatchType

Ushbu bo'limda MatchType strategiyalarni ko'rib chiqamiz.

MatchType strategiyalari telegramdan kelgan xabarlarni tekshirish strategiyasini o'zgartirish uchun ishlatiladi.
Bu sizga murakkab conditionlarni yozish imkoni beradi.

* `START_WITH`
* `END_WITH`
* `CONTAINS`
* `EQUALS`
* `REGEX`
* `EQUALS_IGNORE_CASE`
* `EXPRESSION`
* `VAR_EXPRESSION`

## 3. FluentTemplate
## 3. Handling exceptions
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