## WeatherPlus

![BUILD Status](https://travis-ci.org/muslumsezgin/WeatherPlus.svg?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/muslumszgn/WeatherPlus/badge.svg?branch=master)](https://coveralls.io/github/muslumszgn/WeatherPlus?branch=master)


WeatherPlus hava durumu uygulamasıdır. Haftalık hava durumu incelemesi ve şehir kayıt etme özellikleri vardır. Spring Boot ve Vaadin teknolojileri kullanılarak hazırlanmıştır.

# İçerik

-	[Ekranlar](#ekranlar)
-	[Teknolojiler](#teknolojiler)
-	[Başlatma](#başlatma)


## Ekranlar
 ![Screenshot](https://github.com/muslumszgn/WeatherPlus/blob/master/ekrangoruntusu/image1.PNG?raw=true)
 ![Screenshot](https://github.com/muslumszgn/WeatherPlus/blob/master/ekrangoruntusu/image2.PNG?raw=true)

## Teknolojiler

### [Maven](https://maven.apache.org/)

Apache Maven bir yazılım proje yönetimi ve anlama aracıdır. Bir proje nesne modeli (POM) kavramına dayanan Maven, bir projenin yapısını, raporlamasını ve belgelerini merkezi bir bilgi parçasından yönetebilir.

### [Spring Boot](https://github.com/spring-projects/spring-boot)

Spring Boot, Spring kaynaklı, üretim düzeyindeki uygulamalar ve hizmetleri minimum gereksinim ile oluşturmayı kolaylaştırır. Yeni ve mevcut kullanıcıların ihtiyaç duydukları bitleri hızla alabilmeleri için Spring platformunun fikir sahibi bir görüşünü alır.
 
### [Vaadin](https://vaadin.com/)

Vaadin Framework, web uygulamaları oluşturmak için sunucu tarafında bir Java kullanıcı arabirimi çerçevesidir. Bileşen seti ve otomatik tarayıcı-sunucu iletişimi ile Vaadin Framework, geliştirme yapmanızı sağlar.

### [Gson](https://github.com/google/gson)

Gson, Java Nesnelerini JSON gösterimine dönüştürmek için kullanılabilen bir Java kitaplığıdır. Bir JSON dizesini eşdeğer bir Java nesnesine dönüştürmek için de kullanılabilir. 


## Başlatma

Projenin ana dizininde aşağıdaki komutu kullanarak uygulamayı başlatabilirsiniz. 
Uygulama http://localhost:8091/ linkinde çalışmaktadır. application.properties dosyası üzerinden port değiştirilebilir.

```
mvn clean install spring-boot:run
```
