# KAYIT PROGRAMI

Kayıt programı, JAVA dilinde yazılmış bir "Form" uygulamasıdır.

Genel olarak "DersMenü" classındaki csv'ye kayıt edilen derslerin, "ÖğrenciMenü" classında öğrencinin girdiği bilgiler ve seçmek istediği dersi "Combobox"da 
daha önceden csv'ye kayıt edilen derslerin görünüp seçim yapmasıyla ve ardından kayıt yapması ve kayıt ederken de "DersMenü" classında kayıt edilen verilerin çekilip "Combobox" kullanılarak seçilip kayıt etmesini sağlar. Kayıt yaparken de "Ogrenci" ve "Ders" sınıflarını kullanır. Bu sınıfların amacı, program içinde öğrenci ve ders bilgilerini daha düzenli ve programatik bir şekilde yönetmek ve bu bilgileri dışa aktarmak (CSV formatında) olarak özetlenebilir. BU proje de bu hikayeyi uygulayan bir java programıdır. 

----------------

- Uygulamanın Kullanımında dikkat edilmesi gerekenler

- Öğrenci kayıt yaparken, bölümü doğru girilmeli (ders kayıtta hangi bölümle kayıt yapılmışsa o bölüm) ve ardından enter a basıp derslerin gelmesini sağlamalı daha sonra kaydet demelisiniz.

----------------------

- Anamenü'yü açtınız ve istediğiniz seçimi yapmanız için butanlar mevcut o butonlara tıklayarak yapabilirsiniz. Ardından seçtiğiniz kayıtta ana menüye dönmek için ayrı buton mevcuttur. Son olarak kayıt et butonları da bütün verileri girdikten sonra belirtilen csv dosyasına kayıt sağlayabilirsiniz. Csv den de kontrol sağlayabilirsiniz.

----------------------
-AnaMenü sınıfı, programın ana menüsünü temsil eder ve kullanıcıya ders oluşturma veya öğrenci kaydı yapma seçenekleri sunar. Kullanıcı, bu menüler aracılığıyla öğrenci veya ders bilgilerini girebilir. 

-Öğrenci ve ders bilgileri, sırasıyla "Ogrenci" ve "Ders" sınıfları tarafından çekilir.

-"OgrenciMenü" ve "DersMenü" sınıfları, kullanıcının öğrenci veya ders bilgilerini girmesini sağlar ve bu bilgileri CSV dosyalarına kaydeder. 
