## WeatherPlus

WeatherPlus hava durumu uygulamasıdır. Haftalık hava durumu incelemesi ve şehir kayıt etme özellikleri vardır. Spring Boot ve Vaadin teknolojileri kullanılarak hazırlanmıştır.

# İçerik

-	[Ekranlar](#ekranlar)
-	[Teknolojiler](#teknolojiler)
-	[Get Started](#calıştırma)


## Ekranlar
 ![Screenshot](https://cdn.dribbble.com/users/218187/screenshots/2357046/weather_1x.png)
 ![Screenshot](https://cdn.dribbble.com/users/218187/screenshots/2357046/weather_1x.png)

## Teknolojiler

### [Maven](https://maven.apache.org/)

Apache Maven bir yazılım proje yönetimi ve anlama aracıdır. Bir proje nesne modeli (POM) kavramına dayanan Maven, bir projenin yapısını, raporlamasını ve belgelerini merkezi bir bilgi parçasından yönetebilir.

### [Spring Boot](https://github.com/spring-projects/spring-boot)

Spring Boot, Spring kaynaklı, üretim düzeyindeki uygulamalar ve hizmetleri minimum gereksinim ile oluşturmayı kolaylaştırır. Yeni ve mevcut kullanıcıların ihtiyaç duydukları bitleri hızla alabilmeleri için Spring platformunun fikir sahibi bir görüşünü alır.
 
### [Vaadin](https://vaadin.com/)

Vaadin Framework, web uygulamaları oluşturmak için sunucu tarafında bir Java kullanıcı arabirimi çerçevesidir. Bileşen seti ve otomatik tarayıcı-sunucu iletişimi ile Vaadin Framework, geliştirme yapmanızı sağlar.

### [Gson](https://github.com/google/gson)

Gson, Java Nesnelerini JSON gösterimine dönüştürmek için kullanılabilen bir Java kitaplığıdır. Bir JSON dizesini eşdeğer bir Java nesnesine dönüştürmek için de kullanılabilir. 


## Çalıştırma

Projenin ana dizininde aşağıdaki komutu kullanarak uygulamayı başlatabilirsiniz. 
Uygulama http://localhost:8091/ linkinde çalışmaktadır. application.properties dosyası üzerinden port değiştirilebilir.

```
mvn clean install spring-boot:run
```
