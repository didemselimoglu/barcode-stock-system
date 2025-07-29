#  Barcode Stock System

**Barcode Stock System**, Android cihazlar üzerinden barkod okuyarak ürün stok bilgilerine anında ulaşılmasını sağlayan bir sistemdir. Sistem; mobil istemci (Android), sunucu tarafı REST API (Spring Boot), ve veritabanı (MySQL) katmanlarından oluşur. Barkod tanıma için Google'ın ML Kit kütüphanesi kullanılmıştır.

---

##  Özellikler

### Android Uygulama
- Jetpack Compose ile modern kullanıcı arayüzü
- Barkod tarayıcı (CameraX + ML Kit)
- Ürün bilgilerini görsel kartlar halinde gösterme
- Giriş sistemi (JWT ile güvenli oturum)
- API ile veri alışverişi (Retrofit ile)
- Network, hata ve loading yönetimi

### Backend API
- RESTful API mimarisi
- Kullanıcı doğrulama (Spring Security + JWT)
- Ürün sorgulama ve kayıt endpointleri
- MySQL ile ilişkisel veri yönetimi
- Katmanlı mimari (Controller → Service → Repository → DB)

---

##  Mimari

```text
Android Uygulama (Kotlin)
│
├── Jetpack Compose UI
├── ViewModel (MVVM)
├── Repository → Retrofit API
│
↓
REST API (Spring Boot)
├── Controller
├── Service
├── Repository (Spring Data JPA)
↓
MySQL Veritabanı
