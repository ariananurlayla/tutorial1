<h1>Refleksi 1</h1>

<h2>Clean Code:</h2>
<ul>
  <li><strong>Penamaan yang Bermakna:</strong> Penggunaan nama yang jelas dan deskriptif untuk fungsi dan file membantu memahami fungsionalitasnya.</li>
  <li><strong>Fungsi yang Jelas:</strong> Membuat fungsi yang bertanggung jawab atas satu tugas saja dengan jelas akan membantu memperjelas struktur kode.</li>
  <li><strong>Komentar:</strong> Memberikan informasi tambahan yang membantu memahami fungsionalitas dari potongan kode tersebut. Namun, hindari penggunaan komentar jika kode sudah mudah dipahami tanpa komentar.</li>
</ul>

<h2>Secure Coding:</h2>
<ul>
  <li>Dalam pembuatan produk baru, menggunakan metode POST membantu menjaga keamanan operasi, seperti melindungi dari serangan CSRF.</li>
</ul>

<h2>Kesalahan dan Solusi:</h2>
<ul>
  <li>Ketidakhadiran sistem autentikasi dan otorisasi merupakan kekurangan penting dalam memastikan keamanan, privasi, dan integritas aplikasi secara keseluruhan. Solusinya adalah menerapkan sistem autentikasi untuk meningkatkan keamanan aplikasi.</li>
  <li>Sebelumnya, algoritma pencarian produk diletakkan di layer service, menyebabkan kegagalan tes. Solusinya adalah memindahkan algoritma pencarian produk ke layer Repository untuk memastikan tes dapat berjalan dengan baik.</li>
</ul>


<h1>Refleksi 2</h1>

<ol>
  <li>
    <h2>Unit Test:</h2>
    <ul>
      <li>Pengalaman menulis pengujian unit meningkatkan kepercayaan terhadap kode yang berhasil melewati pengujian.</li>
      <li>Tidak ada angka pasti untuk menentukan jumlah pengujian unit yang diperlukan, namun semakin besar cakupan pengujian terhadap kode akan semakin baik.</li>
      <li>Selain cakupan kode, pengujian yang cermat terhadap berbagai kemungkinan kasus juga penting untuk memastikan kualitas kode.</li>
      <li>Meskipun mencapai cakupan kode 100% penting, namun hal itu tidak menjamin bahwa kode tidak memiliki bug atau error. Masih mungkin terdapat bug karena pengujian mungkin tidak mencakup semua skenario atau kondisi yang mungkin terjadi pada program.</li>
    </ul>
  </li>
  <li>
    <h2>Refleksi:</h2>
    <ul>
      <li>Pembuatan kelas Java baru hanya untuk memeriksa jumlah item dalam daftar produk kurang ideal dan dapat menurunkan kebersihan kode. Hal ini disebabkan oleh beberapa faktor:
        <ul>
          <li>Duplikasi kode: Pengujian fungsional untuk verifikasi nama produk dan jumlah produk memiliki banyak kesamaan, sehingga membuat kode yang berulang-ulang.</li>
          <li>Efisiensi: Adanya duplikasi kode membuat kode menjadi kurang efisien dan sulit dipelihara.</li>
          <li>Keberlanjutan: Jika terjadi perubahan pada sistem, seperti pada template HTML, programmer harus melakukan modifikasi pada kedua kelas secara terpisah, yang dapat memakan waktu dan rawan kesalahan.</li>
        </ul>
      </li>
      <li>Solusinya adalah menggabungkan kedua kelas tersebut menjadi satu kelas tunggal. Hal ini memungkinkan:
        <ul>
          <li>Penggabungan kode: Potongan kode yang serupa, seperti simulasi pembuatan produk, dapat dipindahkan ke dalam satu metode yang dapat dipanggil oleh kedua pengujian.</li>
          <li>Efisiensi: Penggabungan kode meningkatkan efisiensi dan kemudahan dalam pemeliharaan kode.</li>
          <li>Keberlanjutan: Modifikasi kode hanya perlu dilakukan pada satu kelas, sehingga menghemat waktu dan meminimalisir risiko kesalahan.</li>
        </ul>
      </li>
    </ul>
  </li>
</ol>
