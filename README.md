OKYS – Öğrenci Kayıt ve Yönetim Sistemi


1. Projenin Kısa Tanımı ve Amacı

OKYS, üniversite ortamında öğrenci, eğitmen, ders, kayıt ve not bilgilerinin yönetimini sağlayan bir web
uygulamasıdır. Proje Java Spring Boot kullanılarak geliştirilmiştir ve REST API prensiplerine uygun bir
arayüz sunar. Uygulama PostgreSQL veritabanı ile çalışır ve bulutta Railway platformu üzerinde barındırılır.
Amaç, öğrenci kayıt süreçlerini, ders atamalarını ve not takibini merkezi bir sistem üzerinden
kolaylaştırmaktır (CRUD işlemleri ve temel iş mantığı katmanlarıyla). Örneğin, Spring Boot “minimal
konfigürasyonla bağımsız, production düzeyinde uygulamalar oluşturmayı kolaylaştırır” . PostgreSQL ise
“güçlü, açık kaynaklı bir ilişkisel veritabanı yönetim sistemidir” . Railway, uygulamayı hızlıca dağıtmak için
kullanılan bir PaaS platformudur .



2. Kullanılan Teknolojiler

OKYS’te aşağıdaki ana teknolojiler kullanılmıştır:

Java: Yüksek seviyeli, genel amaçlı bir programlama dilidir. NESNE-YÖNELİMLİ, taşınabilir (write once,
run anywhere) yapısıyla bilinir . Proje Java 17 veya üzeri JDK ile derlenir.

Spring Boot: Spring çerçevesinin üzerine inşa edilmiş, konfigürasyonu azaltarak hızlı geliştirmeye
imkan veren bir Java framework’üdür. Resmi tanımına göre “ayrı çalıştırılabilir, üretim hazır Spring
tabanlı uygulamalar oluşturmayı kolaylaştırır” . Spring Boot, web servisi geliştirmek için gömülü
Tomcat sunucu ve otomatik yapılandırma sağlar.

Spring Data JPA: Java Persistence API (JPA) spesifikasyonunu kullanarak nesne-ilişkisel haritalamayı
sağlayan kütüphanedir. JPA, nesne yönelimli modeller ile ilişkisel veritabanları arasında köprü görevi
görür . Projede her @Entity sınıfı bir veritabanı tablosunu temsil eder .

PostgreSQL: Açık kaynak kodlu bir ilişkisel veritabanı yönetim sistemidir. Güvenilirliği ve performansı
ile bilinir . Veriler tablolarda saklanır ve Spring Data JPA üzerinden erişilir. (Örnek: PostgreSQL’de
id , ad , email gibi sütunlar kullanılır.)

REST API: Uygulama, HTTP protokolü üzerinden JSON formatında haberleşen REST mimarisini
kullanır. REST API, duruma bağlı olmayan yapılarla (stateless) web servisleri oluşturma prensibidir
. Her kaynak için (örneğin öğrenci, ders vb.) GET/POST/PUT/DELETE uç noktaları mevcuttur.

JSON: Uygulama, istemci-sunucu iletişiminde JSON formatını kullanır. Spring Boot varsayılan olarak
yanıtları JSON formatında döner ve Jackson kütüphanesi ile Java nesnelerini JSON’a çevirir.




3. Proje Yapısı
OKYS, katmanlı mimariye uygun şekilde organize edilmiştir. Başlıca katmanlar şunlardır:

Model/Entity Katmanı: model paketinde tanımlı JPA @Entity sınıflarıdır. Örneğin Ogrenci ,
Egitmen , Ders , Kayit , Not gibi varlıklar, veritabanındaki karşılık gelen tabloları temsil eder
Bu sınıflar alan (field) seviyesinde sütunları, ilişkileri ve kimlik (ID) bilgilerini içerir.

Repository Katmanı: repository paketinde yer alır. Spring Data JPA ile oluşturulmuş arayüzler
( JpaRepository implementasyonları) veritabanı işlemlerini yönetir. Her repository sınıfı, temel
CRUD işlemleri (create, read, update, delete) için hazır metotlar sağlar . Örneğin
OgrenciRepository extends JpaRepository<Ogrenci, Long> şeklinde tanımlanır ve
findAll() , findById() , save() gibi metodları içerir.

Controller Katmanı: controller paketindeki @RestController sınıfları HTTP isteklerini
karşılar. Bu sınıflar gelen istekleri alır, gerekiyorsa servis katmanına iletir ve yanıt olarak JSON döner.
Spring mimarisinde controller katmanı HTTP GET, POST, PUT, DELETE gibi talepleri işler . Örneğin
/api/ogrenciler uç noktası OgrenciController içinde tanımlanır, öğrenci listelerini ve
detaylarını döner.

Servis (Business) Katmanı (opsiyonel): Uygulamanın iş mantığının yer aldığı katmandır. Burada
validasyon, işlem akışı veya ek iş kuralları uygulanır. Controller’dan gelen veriyi işler ve repository
katmanıyla etkileşir. Tipik bir Spring Boot akışında, servis katmanı gelen taleplerin çekirdeğini yönetir
. Örneğin OgrenciService sınıfı, bir öğrenci ekleme veya güncelleme işleminden önce gerekli
iş kurallarını uygular.

Katmanlar arası iletişim şöyle işler: İstemci (frontend veya API tüketicisi) bir HTTP isteği gönderir. İstek önce
Controller katmanına gelir ve uygun metotla eşleşir . Gerekirse Service katmanı devreye girer ve iş
kuralları uygulanır . Sonra Repository katmanı aracılığıyla veritabanına erişilir (Spring Data JPA ile ORM
işlemleri yapılır) . Sonuç JSON formatında istemciye geri döner.



4. Kurulum ve Çalıştırma Adımları

Gereksinimler: Çalışmak için bilgisayara Java JDK 17+ ve Maven (veya Gradle) yüklü olmalıdır. Ayrıca
PostgreSQL sunucusu kurulmalı veya Railway gibi bir bulut veritabanı kullanılabilir.
Proje Kaynak Kodunun Temini: Projenin Git reposu (varsa) klonlanır veya arşiv indirilir.
Bağımlılıkların Eklenmesi: pom.xml (Maven) veya build.gradle dosyasına aşağıdaki
bağımlılıklar eklenmelidir: Spring Data JPA starter ve PostgreSQL JDBC sürücüsü . Örnek Maven
bölümü:

 <dependencies>
   <!-- Spring Data JPA ile Hibernate desteği -->
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-jpa</artifactId>
   </dependency>
   <!-- PostgreSQL JDBC sürücüsü -->
   <dependency>
      <groupId>org.postgresql</groupId>
      <artifactId>postgresql</artifactId>
 </dependency>
 <!-- (Diğer Spring Web ve güvenlik bağımlılıkları eklenebilir) -->
</dependencies>


Veritabanı Oluşturma: PostgreSQL içerisinde bir veritabanı oluşturulmalıdır (örneğin okys_db
adında). Ayrıca, uygulama için bir kullanıcı ve parola tanımlayın. Örnek:

CREATE USER okys_user WITH PASSWORD 'sifre123';
CREATE DATABASE okys_db OWNER okys_user;


application.properties Yapılandırması: Uygulamanın src/main/resources/
application.properties dosyasına veritabanı bağlantı bilgileri eklenir. Örneğin:

spring.datasource.url=jdbc:postgresql://localhost:5432/okys_db
spring.datasource.username=okys_user
spring.datasource.password=sifre123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

Bu ayarlar, uygulamanın PostgreSQL veritabanına bağlanmasını sağlar . ( ddl-auto=update ile
tablo oluşturulur veya güncellenir.)

Uygulamayı Çalıştırma: Maven kullanılıyorsa, proje klasöründe terminal/komut satırında aşağıdaki
komutla uygulama başlatılabilir:

mvn spring-boot:run

Bu komut Spring Boot Maven plugin’i aracılığıyla uygulamayı ayağa kaldırır . Alternatif olarak
projenin jar dosyasını paketleyip çalıştırabilirsiniz ( mvn clean package ve sonra java -jar
target/app.jar ). Uygulama başlatıldığında, varsayılan olarak http://localhost:8080
adresinden API uç noktalarına erişilir.

Railway Ortamı (opsiyonel): Eğer Railway kullanılıyorsa, veritabanı bağlantı bilgileri genellikle
ortam değişkeni (env var) olarak sağlanır. Örneğin SPRING_DATASOURCE_URL veya benzer bir
Railway URL’si kullanılabilir. Railway’e deployment yaparken (5. bölümde açıklanacak), aynı
application.properties yerine ortam değişkenleri tercih edilebilir.



5. API Dökümantasyonu

Aşağıda OKYS API’sinin temel uç noktaları ve örnek JSON gövdeleri verilmiştir. Her kaynak için RESTful
yöntemler kullanılmıştır. Uç noktalar projenin @RestController sınıflarında tanımlıdır. Aşağıdaki
adresleri /api öneki ile veya doğrudan kullanabilirsiniz (örneklerde /api koyulmuştur). Sonuçlar JSON
formatında döner.

Öğrenci (Student) Endpoint’leri:

GET /api/students – Tüm öğrencilerin listesini döner. Örnek cevap:
[
{ "id": 1, "ad": "Ali Veli", "email": "ali.veli@example.com", "bolum":
"Bilgisayar Müh." },
{ "id": 2, "ad": "Ayşe Demir", "email": "ayse.demir@example.com",
"bolum": "Matematik" }
]

GET /api/students/{id} – Belirtilen ID’li öğrenciyi döner. Örnek cevap:
{ "id": 1, "ad": "Ali Veli", "email": "ali.veli@example.com", "bolum":"Bilgisayar Müh." }

POST /api/students – Yeni öğrenci oluşturur. İstek gövdesinde JSON gönderilir. Örnek istek:

{ "ad": "Mehmet Can", "email": "mehmet.can@example.com", "bolum": "Fizik" }
Cevap gövdesi tipik olarak oluşturulan nesneyi içerir (ID ile birlikte) ve 201 Created statüsü döner.
Örnek cevap:

{ "id": 3, "ad": "Mehmet Can", "email": "mehmet.can@example.com", "bolum":
"Fizik" }

PUT /api/students/{id} – Mevcut öğrenciyi günceller. İstek gövdesi JSON’dur. Örneğin:
{ "id": 1, "ad": "Ali Veli", "email": "ali.veli@example.com", "bolum":"Elektrik-Elektronik" }

Güncelleme başarılıysa 200 OK döner; isteğe bağlı olarak güncellenmiş öğrenci nesnesi ile.
DELETE /api/students/{id} – Belirtilen ID’li öğrenciyi siler. Başarılı silmede genellikle 204
No Content döner.

Eğitmen (Teachers) Endpoint’leri: (İsimlendirme /api/egitmenler varsayılmıştır.)
GET /api/teachers – Tüm eğitmenler listesi. Örnek cevap:

[
{ "id": 1, "ad": "Dr. Ahmet Yılmaz", "bolum": "Bilgisayar
Mühendisliği" },
{ "id": 2, "ad": "Dr. Emine Çelik", "bolum": "Matematik" }
]

GET /api/teachers/{id} – Belirli eğitmen. Örnek cevap:
{ "id": 1, "ad": "Dr. Ahmet Yılmaz", "bolum": "Bilgisayar Mühendisliği" }

POST /api/teachers – Yeni eğitmen ekler. Örnek istek:
{ "ad": "Dr. Zeynep Kaya", "bolum": "Fizik" }
Örnek cevap (oluşturulan eğitmen):

{ "id": 3, "ad": "Dr. Zeynep Kaya", "bolum": "Fizik" }

PUT /api/teachers/{id} – Eğitmeni günceller. id ve güncel veriler JSON içinde verilir.

DELETE /api/teachers/{id} – Eğitmeni siler. Başarılı işlemde 204 döner.


Ders (Course) Endpoint’leri: ( /api/dersler )

GET /api/courses – Tüm dersler listesi. Örnek cevap:
[
{ "id": 1, "ad": "Veritabanı Sistemleri", "kredi": 3, "egitmenId": 1 },
{ "id": 2, "ad": "Lineer Cebir", "kredi": 4, "egitmenId": 2 }
]

( egitmenId dersi veren eğitmenin ID’sidir.)

GET /api/courses/{id} – Belirli ders. Örnek cevap:
{ "id": 1, "ad": "Veritabanı Sistemleri", "kredi": 3, "egitmenId": 1 }

POST /api/courses – Yeni ders ekler. Örnek istek:
{ "ad": "Algoritmalar", "kredi": 4, "egitmenId": 2 }
Örnek cevap:
{ "id": 3, "ad": "Algoritmalar", "kredi": 4, "egitmenId": 2 }

PUT /api/courses/{id} – Ders güncelleme.

DELETE /api/courses/{id} – Dersi siler.


Her bir controller sınıfı, yukarıdaki uç noktaları @GetMapping , @PostMapping vb. anotasyonlarıyla
tanımlar. Örneğin, tipik bir Spring Boot REST API şu uç noktalara sahip olabilir: GET /students , GET /
students/{id} , POST /students , PUT /students/{id} . Burada /students yerine
projemizde /ogrenciler kullanılmıştır. Tüm yanıtlar JSON biçimindedir ve HTTP durum kodları (200, 201,
204, 404, vb.) uygun şekilde döndürülür.



6. Deployment (Railway)
Uygulama Railway platformu üzerinde çalışmaktadır. Railway, GitHub reposu, Dockerfile veya Nixpacks
kullanarak kodu hızlıca buluta deploy etmeyi kolaylaştıran bir PaaS’tır . Örneğin, Railway’da yeni bir proje
oluşturup GitHub reposunu bağladıktan sonra “Deploy” düğmesine basabilirsiniz. Railway uygulamanın Java
olduğunu otomatik algılar ve gerekli derlemeyi yapar . Deployment tamamlandıktan sonra Railway’in
servis ayarlarından bir genel URL (domain) oluşturularak uygulamaya internet üzerinden ulaşabilirsiniz.
Veritabanı olarak PostgreSQL servisi eklenebilir ve bağlantı bilgileri (örneğin POSTGRES_URL ) ortam
değişkenleri aracılığıyla application.properties dosyasına iletilebilir. Railway kullanımı, sunucu
yönetimiyle uğraşmak yerine doğrudan kodu yayınlamayı sağlar.
