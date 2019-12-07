

#### Gerekli Kurulumlar
* Java JDK 8 +
* Apache Maven
* Docker
* NodeJs

#### Kullanılan Teknolojiler
* Java 8
* Docker
* Maven
* Selenium Grid
* TestNG / Dataprovider (Data Driven Testing)
* NodeJs

#### Testin Çalıştırılabilmesi İçin Ortam Nasıl Oluşturulur?

###### Sırasıyla;

* Proje github'dan clone edilir.
* Docker Deamon başlatışır ve projenin olduğu dizinin içine girilerek **`docker-compose up -d`**  komutu çalıştırılır.
    * Bu yml dosyası gerekli altyapıları içindeki image'lar ile kurar. 
    * İmage list; ngnix,selenium-grid-hub,firefox node, chrome node.
* Bu compose.yml dosyası tek bir firefox ve chrome tarayıcısını hub'a register eder.(her biri farklı iki nod) Eğer scale etmek istoyorsanız compose dosyasını **`docker-compose up -d --scale firefox=<sayi> --scale chrome=<sayi>`**
olarak çalıştırmanız yeterlidir.
* **`http://localhost:4444/grid/console`**'e giderek Selenium Grid hub'nın ayakta olduğu ve node'ların register olup olduğu kontrol edilir.
* **`localhost:8001`**' giderek ngnix'in çalışırlığı kontrol edilir.

#### Test Nasıl Çalıştırılır?

* _LinkAndLinkResponseCodeTest (STEP/1)_ testini çalıştırmak için;
    *   **`mvn test -DsuiteXmlFile=LinkAndLinkRespTestNG.xml`**

* _ImgLoadTimeAndResponseCodeTest(STEP/2)_ testini çalıştırmak için;
    *   **`mvn test -DsuiteXmlFile=ImgLoadAndRespTestNg.xml`**
    
* _DataDrivenLoginTest (STEP/3)_ testini çalıştırmak için;
    *   **`mvn test -DsuiteXmlFile=LoginDataDrivenTestNG.xml`**
    
Komutlarını çalıştırılması yeterli olacaktır.

##### Not: Bu komutlar proje dizininin içerisinde çalıştırılması gerekmektedir, aksi takdirde .xml file uzantısı için tam path vermeniz gerekir.

#### Proje ile CI/CD Adına Ek Olarak Neler Yapılabilir?

* Proje Jenkins üzerinde bir job ile tetiklendirilebilir.
Şöyle ki;
package.json dosyası içerisinde mevcut npm scrip komutları bulunmaktadır, bu komutlar ile Jenkins içerisinden sh script yazarak 
testleri schedule edebilirsiniz.

#### Jenkins Aracılığıyla Test Çalıştırmak İçin?

**Gerekli Kurulumlar:**
* Jenkins, Java JDK 8 ,Maven, NodeJs, npm ve npm-run-all

**Npm İle Pipeline Script Yazımı:**

* _LinkAndLinkResponseCodeTest (STEP/1)_ testini çalıştırmak için;
    * **`git 'https://github.com/ogulcanarbc/TestAutomationProject.git'`**
    * **`sh 'npm butik-linkAndLinkResp-test'`**

* _ImgLoadTimeAndResponseCodeTest(STEP/2)_ testini çalıştırmak için;
    * **`git 'https://github.com/ogulcanarbc/TestAutomationProject.git'`**
    * **`sh 'npm butik-imgLoadAndResp-test'`**
                
 * _DataDrivenLoginTest (STEP/3)_ testini çalıştırmak için;
    * **`git 'https://github.com/ogulcanarbc/TestAutomationProject.git'`**
    * **`sh 'npm login-datadriven-test'`**
                        
 * Bu üç testi paralel çalıştırmak için:
    * **`git 'https://github.com/ogulcanarbc/TestAutomationProject.git'`**
    * **`sh 'login-butik-imageload-parallel-test'`**
                                
* Test sonuçları ile ilgili csv dosyalarını ve ekran görüntülerini proje dizini içerisindeki **`./reports`** dosyası içinde görebilirsiniz.          
                
* Son olarak Html formatında bir raporunuz var ise, test bittikten sonra şu komutu çalıştırarak ngnix sunucusu üzerinde raporu yayınlayabilirsiniz.
    
    *   **`docker cp  <proje-path>/index.html trendyol_web_server_1:/var/www/html/index.html`**
    
    Ardından **`localhost:8001`**'e girerek html raporunuzu görüntüleyebilirsiniz.
    
   
