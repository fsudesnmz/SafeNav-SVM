# SafeNav-SVM: Otonom Navigasyon Güvenlik Modülü

SafeNav-SVM, otonom araçların navigasyon süreçlerinde karşılaştıkları engelleri matematiksel olarak en güvenli şekilde ayrıştıran bir güvenlik modülü projesidir. Bu proje, iki farklı engel grubunu sadece birbirinden ayırmakla kalmaz, her iki sınıfa olan uzaklığı maksimize ederek **"Maksimum Güvenlik Koridoru" (Maximum Margin)** oluşturur.

## 🚀 Proje Amacı
Navigasyon sistemlerinde güvenliği artırmak adına, doğrusal ayrıştırılabilir koordinat setleri arasında en geniş boşluğu bırakan optimum sınır denklemini hesaplamak ve bu süreci Nesne Yönelimli Programlama (OOP) prensiplerine uygun bir mimariyle sunmaktır.

## 🛠️ Teknik Özellikler
- **Programlama Dili:** Java 17 (Microsoft OpenJDK LTS)
- **Bağımlılık Yönetimi:** Maven
- **Algoritma:** Support Vector Machines (SVM)
- **Optimizasyon:** Stochastic Gradient Descent (SGD)
- **Mimari:** Katmanlı Mimari (Model-Service-UI)

## 📐 Algoritmik Yaklaşım ve Matematiksel Model
Sistem, ayrıştırıcı düzlemi ($w \cdot x + b = 0$) hesaplarken şu yöntemleri kullanır:
- **Hinge Loss:** Yanlış sınıflandırmaları ve marjin ihlallerini cezalandıran kayıp fonksiyonu.
- **L2 Regularization:** Modelin aşırı öğrenmesini (overfitting) engelleyen ve marjini genişletmeye zorlayan düzenleme.
- **Optimum Sınır:** Hesaplanan sınırın neden en güvenli olduğu, her iki engel grubunun "Destek Vektörleri"ne (en yakın noktalar) tam olarak eşit ve maksimum uzaklıkta olmasıyla ispatlanmıştır.

## 🏗️ Yazılım Mimarisi (OOP)
Proje, puanlama kriterlerinde belirtilen "Katmanların İzolasyonu" prensibine göre tasarlanmıştır:
- **`model`**: `Point` ve `Boundary` sınıfları ile veri kapsülleme (Encapsulation).
- **`service`**: `SVMTrainer` sınıfı ile iş mantığının (Business Logic) ayrıştırılması.
- **`ui`**: `Visualizer` sınıfı ile grafiksel sunum (Java Swing).

## 📊 Karmaşıklık Analizi (Big-O)
- **Eğitim (Training):** $O(Epoch \times N)$ - Burada $N$ veri noktası sayısıdır.
- **Karar Verme (Inference):** $O(1)$ - Otonom araç sürüş sırasında sadece bir lineer denklem çözdüğü için sabit zamanda karar verir. Bu, gerçek zamanlı sistemler için kritik bir verimliliktir.

## 🧠 Bellek Yönetimi
- **Sıfır Bellek Sızıntısı:** Nesne ömürleri ve referans yönetimleri Java Garbage Collector ile uyumlu şekilde optimize edilmiştir.
- **Verimlilik:** Gereksiz nesne üretiminden kaçınılmış, büyük veri setlerine uygun şekilde `primitive` bazlı koordinat hesaplamaları tercih edilmiştir.

## 📸 Uygulama Ekran Görüntüsü
![Uygulama Çıktısı](https://github.com/fsudesnmz/SafeNav-SVM/blob/main/output.png?raw=true)

## 📦 Kurulum ve Çalıştırma
1. Depoyu klonlayın: `git clone https://github.com/fsudesnmz/SafeNav-SVM.git`
2. Proje dizinine gidin: `cd SafeNav-SVM`
3. Derleyin: `mvn clean install`
4. Çalıştırın: `java -cp target/SafeNav-SVM-1.0-SNAPSHOT.jar com.otonom.guvenlik.Main`
