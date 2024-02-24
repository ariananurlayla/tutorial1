App link: https://eshop-ariananurlayla.koyeb.app/

# MODULE 1

## Refleksi 1
**Clean Code**
- Meaningful names: Penggunaan nama yang jelas dan deskriptif untuk fungsi dan file membantu memahami fungsionalitasnya.
- Function: Membuat fungsi yang bertanggung jawab atas satu tugas saja dengan jelas akan membantu memperjelas struktur kode. 
- Comments: Memberikan informasi tambahan yang membantu memahami fungsionalitas dari potongan kode tersebut. Namun, hindari penggunaan komentar jika kode sudah mudah dipahami tanpa komentar.

**Secure Coding**
Dalam pembuatan produk baru, menggunakan method POST membantu menjaga keamanan operasi, seperti melindungi dari serangan CSRF.

**Kesalahan dan Solusi**
- Ketidakhadiran sistem autentikasi dan otorisasi merupakan kekurangan penting dalam memastikan keamanan, privasi, dan integritas aplikasi secara keseluruhan. Solusinya adalah menerapkan sistem autentikasi untuk meningkatkan keamanan aplikasi.
- Sebelumnya, algoritma pencarian produk diletakkan di layer service, menyebabkan kegagalan tes. Solusinya adalah memindahkan algoritma pencarian produk ke layer Repository untuk memastikan tes dapat berjalan dengan baik.

## Refleksi 2
**Unit Test**
- Pengalaman menulis pengujian unit meningkatkan kepercayaan terhadap kode yang berhasil melewati pengujian.
- Tidak ada angka pasti untuk menentukan jumlah pengujian unit yang diperlukan, namun semakin besar cakupan pengujian terhadap kode akan semakin baik.
- Selain cakupan kode, pengujian yang cermat terhadap berbagai kemungkinan kasus juga penting untuk memastikan kualitas kode.
- Meskipun mencapai cakupan kode 100% penting, namun hal itu tidak menjamin bahwa kode tidak memiliki bug atau error. Masih mungkin terdapat bug karena pengujian mungkin tidak mencakup semua skenario atau kondisi yang mungkin terjadi pada program.

**Refleksi**
- Pembuatan kelas Java baru hanya untuk memeriksa jumlah item dalam daftar produk kurang ideal dan dapat menurunkan kebersihan kode. Hal ini disebabkan oleh beberapa faktor:
  - Duplikasi kode: Pengujian fungsional untuk verifikasi nama produk dan jumlah produk memiliki banyak kesamaan, sehingga membuat kode yang berulang-ulang.\
  - Efisiensi: Adanya duplikasi kode membuat kode menjadi kurang efisien dan sulit dipelihara.
  - Keberlanjutan: Jika terjadi perubahan pada sistem, seperti pada template HTML, programmer harus melakukan modifikasi pada kedua kelas secara terpisah, yang dapat memakan waktu dan rawan kesalahan.
- Solusinya adalah menggabungkan kedua kelas tersebut menjadi satu kelas tunggal.
  - Penggabungan kode: Potongan kode yang serupa, seperti simulasi pembuatan produk, dapat dipindahkan ke dalam satu method yang dapat dipanggil oleh kedua pengujian.
  - Efisiensi: Penggabungan kode meningkatkan efisiensi dan kemudahan dalam pemeliharaan kode.
Keberlanjutan: Modifikasi kode hanya perlu dilakukan pada satu kelas, sehingga menghemat waktu dan meminimalisir risiko kesalahan.


# MODULE 2

##Refleksi
**Code quality issues**
Code reliability diselesaikan dengan mengikuti instruksi yang tersedia, yaitu menambahkan deskripsi ke table yang digunakan.
Rename template file html karena case sensitive.

**CI/CD Proyek**
Proyek telah menerapkan prinsip Continuous Integration (CI) dan Continuous Deployment (CD). CI melakukan verifikasi secara otomatis pada codebase setiap kali ada perubahan, contohnya saat menjalankan unit test dan memeriksa keamanan kode menggunakan tools, seperti SonarCloud dan OSSF Scorecard. Selanjutnya, CD artinya melakukan deploy secara otomatis setiap tersedia build terbaru dari codebase, seperti Koyeb yang dapat digunakan untuk deployment otomatis. Dengan demikian, proyek ini telah menggabungkan CI dan CD untuk memastikan pengujian dan deployment yang konsisten setiap terjadi perubahan pada codebase.


# MODULE 3

## Refleksi
**Pengaplikasian SOLID Principle**
1. Single Responsibility Principle (SRP): satu class bertugas untuk mengerjakan satu tugas saja, class-class yang ada dipisahkan agar memudahkan maintainability.
- Dalam proyek ini, tanggung jawab Controller (ProductController dan CarController) dipisahkan agar setiap class berfokus pada tugasnya masing-masing. Di samping itu, HomeController diimplementasikan sehingga ProductController berfokus untuk mapping Product dan CarController berfokus untuk mapping Car.
2. Open-Closed Principle (OCP): menekankan bahwa kode harus terbuka untuk di-extend tanpa memodifikasi kode yang sudah ada.
- Method update pada CarRepository dimodifikasi dengan tujuan mendapatkan fleksibilitas lebih terhadap perubahan atribut Car atau penambahan subclass Car tanpa harus mengubah method tersebut.
3. Liskov Substitution Principle (LSP):  subclass harus bisa menggantikan superclassnya.
- Proyek ini tidak mengimplementasikan inheritance, yang berarti proyek ini tidak melanggar LSP.
4. Interface Segregation Principle (ISP): sebuah interface hanya memiliki satu tanggung jawab dan tidak memaksakan implementasi yang tidak relevan.
- Proyek ini telah memisahkan interface CarService dan ProductService, di mana keduanya memiliki tanggung jawab yang spesifik sesuai dengan namanya.
5. Dependency Inversion Principle (DIP): class sebaiknya bergantung pada abstraksi daripada implementasi konkret.
- Pada proyek ini, CarServiceImpl bergantung pada interface CarService dan CarController yang menyimpan service dalam bentuk interfacenya, bukan implementasi konkretnya.

**Keuntungan pengaplikasian SOLID Principle**
- Readability dan pemeliharaan: Penerapan Single Responsibility Principle (SRP) membuat kode lebih mudah dipahami karena setiap kelas memiliki tanggung jawab yang jelas dan terpisah. Ini mempermudah pemeliharaan kode dalam jangka panjang.
- Fleksibilitas kode: Penerapan Dependency Inversion Principle (DIP) meningkatkan fleksibilitas kode. Hal ini disebabkan karena kode tidak terikat pada satu implementasi khusus sehingga memungkinkan apabila ingin mengganti implementasi tanpa harus mengubah banyak bagian pada kode.
- Prinsip Dependency Inversion (DIP) dan Open-Closed Principle (OCP) memungkinkan penambahan fitur baru tanpa harus menyentuh atau mengubah bagian-bagian yang sudah ada. Hal ini mengurangi risiko terjadinya kesalahan atau error di bagian lain kode.
- Identifikasi bug: Dengan menerapkan SRP, identifikasi dan penanganan bug menjadi lebih mudah karena tanggung jawab setiap kelas sudah terpisah dengan jelas. Hal ini membantu dalam mempersempit pencarian saat menemui masalah pada kode.

**Kerugian tidak mengaplikasikan SOLID Principle**
- Kesulitan memahami kode: Tanpa penerapan SOLID principles, struktur kode menjadi sulit dipahami, dan akan menghabiskan waktu yang lebih banyak untuk memahami fungsionalitasnya. Misalnya, ketika semua fungsionalitas digabung menjadi satu kelas.
- Error: Tanpa penerapan prinsip SOLID, saat melakukan ekstensi atau menambahkan fitur baru, kode menjadi rentan error karena tidak ada pemisahan tanggung jawab yang jelas.
- Testing: Tanpa SOLID principles, proses ini menjadi lebih sulit karena setiap method mungkin melakukan banyak fungsionalitas yang sulit dilacak. Ini dapat menghambat proses penemuan dan perbaikan bug.
