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
@HandleMessage(value = "message:", match = MatchType.STARTS_WITH)
public void startsWithExampleHandler() {
    fluentTemplate.sendText("I handle requests starts with 'message:'");
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

* `STARTS_WITH`

START_WITH - match qilish strategiyasi telegramdan kelgan text xabarlarni match qilish uchun ishlatiladi. Belgilangan qiymatga
kelgan xabarni boshlanishi belgilangan qiymatga mos kelishiga tekshiradi. Yani kelgan xabar boshlanishi 'abs' bilan boshlanganligiga
tekshiradi agar mos kelsa ushbu handle ishga tushadi, aks bo'lsa yo'q :(

```java
@HandleMessage(value = "abs", match = MatchType.STARTS_WITH)
public void startsWithExampleHandler() {
    // ...
}
```

* `ENDS_WITH`

END_WITH - match strategiyasi ham `STARTS_WITH` ga o'xshab asosan text xabarlar match qilish uchun ishlatiladi. ENDS_WITH
strategiyasi STARTS_WITH ga o'xshash faqat ENDS_WITH xabarni boshlanishini emas oxirini belgilangan qiymatga mos kelishini
tekshiradi.

```java
@HandleMessage(value = "xyz", match = MatchType.ENDS_WITH)
public void endsWithExampleHandler() {
    // ...
}
```

* `CONTAINS`

CONTAINS - match strategiyasi ham asosan text xabarlar match qilish uchun ishlatiladi. CONTAINS strategiyasi belgilangan qiymatni
kelgan xabarni ichida bor yoki yoqligiga tekshiradi. Agar belgilangan qiymat telegramdan kelgan xabarni ichida mavjud bo'lsa
handler chaqiriladi.

```java
@HandleMessage(value = "hello", match = MatchType.CONTAINS)
public void endsWithExampleHandler() {
    // ...
}
```

* `EQUALS`

EQUALS - match strategiyasi default match strategiyasi hisoblanadi. EQUALS strategiyasi belgilangan qiymatga telegramdan kelgan
xabar mos kelgan taqdirdagina handler chaqiriladi.

```java
@HandleMessage(value = "/start", match = MatchType.EQUALS)
private void startExample(Update update) {
    // ...
}
```

* `REGEX`

REGEX - match strategiyasi belgilangan regex qiymatga telegramdan kelgan xabarni mos kelishini tekshiradi.
Pastdagi kodga e'tibor bering. Ushbu kodda file type `jpeg, png, pdf` bo'lsagina ushbu handler chaqiriladi.

```java
@HandleDocument(
        value = "([a-zA-Z0-9\\s_\\\\.\\-\\(\\):])+(.jpeg|.png|.pdf)$",
        match = MatchType.REGEX,
        property = DocumentScope.FILE_NAME
)
private void handleDocument() {
    // ...
}
```

* `EQUALS_IGNORE_CASE`

EQUALS_IGNORE_CASE - match strategiyasi `EQUALS` bilan bir xil faqat bitta farqi `EQUALS_IGNORE_CASE` harflarni katta yoki
kichikligiga qaramaydi.

```java
@HandleMessage(value = "/START", match = MatchType.EQUALS_IGNORE_CASE)
private void startExample(Update update) {
    // ...
}
```

* `EXPRESSION`

EXPRESSION - match strategiyasi sizga spel yani (Spring Expression Language) dan foydalanib expressionlarni yozish imkoni beradi va ushbu 
expressionga kelgan xabar mos kelsagina handler method chaqiriladi. Pastdagi kodga e'tibor bering. SPEL dan foydalanib
kelgan xabar boshlanishi 'a' harfdan boshlansa va tugashi esa 'z' harf bilan tugasa ushbu handler chaqiriladi.

'#value' - bu telegramdan kelgan xabar spelga o'zgaruvchi sifatida qo'shib qoyiladi. value o'zgaruvchisiga murojaat qilib o'zingizga
mos expressionni yozishingiz mumkin.

```java
@HandleMessage(value = "#value.startsWith('a') && #value.endsWith('z')", match = MatchType.EXPRESSION)
public void handleExpression() {
    // ...
}
```

* `VAR_EXPRESSION`

VAR_EXPRESSION - match strategiyasi ham text xabarlar bilan ishlash uchun ishlatiladi. VAR_EXPRESSION kelgan xabardan
regex patternga mos kelgan o'zgaruvchilarni ajratib olish uchun ishlatiladi. Pastdagi kodni ko'rishingiz mumkin.

```java
@HandleMessage(value = "/username : {name:[a-z]}", match = MatchType.VAR_EXPRESSION)
void startWithAbsHandler(Update update, @BotVariable("name") String username) {
    fluentTemplate.sendText("Username : " + username);
}
```

### Handle any



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