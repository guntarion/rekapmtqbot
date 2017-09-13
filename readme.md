# Rekap MTQ bot

Bot Telegram untuk membantu rekap otomatis aktivitas tahfizh tahsin MTQ Online (www.tahfizhonline.com)
Silahkan menggunakan source code ini untuk menjalankan bot yg serupa untuk kepentingan non komersial

[![License: CC BY-NC 4.0](https://licensebuttons.net/l/by-nc/4.0/80x15.png)](https://creativecommons.org/licenses/by-nc/4.0/)

### Gambaran Umum
Ini adalah aplikasi bot yang dibuat dg bahasa pemrograman Java dengan menggunakan library Java untuk telegram yang luar biasa oleh 
[Ruben](https://github.com/rubenlagus/TelegramBots)

RekapMTQbot menjalankan fungsi sbg berikut:

1. memonitor dan membuat rekap setoran harian (setoran baru maupun revisi)

2. memonitor dan merekap setoran MRJ dan simak atasnya

3. membuat list daftar MRJ akhir pekan

4. memonitor dan merekap MRJ akhir pekan

5. memonitor keaktifan (ghoib dan udzur) santri

### Mengapa Bot ini dibuat

Karena kelas yang ditangani makin buanyak :-D

## Konsep

RekapMTQbot didasarkan pada prinsip dasar bhw program MTQ dibangun atas dasar kursi kepesertaan, di mana terdapat 10 kursi kepesertaan utk tiap kelas.

Jumlah seat tiap kelas itu tidak bisa berkurang (dan tidak bisa nambah). meskipun santri datang dan pergi, tapi kursinya ya tetap ada di situ. Dg kata lain: yg datang dan pergi BUKAN seat-nya, melainkan santrinya. krn jumlah kelas banyak, maka jumlah seat nya juga banyak. 

Utk bahasan bot ini, saya mengistilahkan seat sbg *okupansi*

Aktivitas terkait bot menyerupai yg dilakukan oleh admin di telegram, yakni:
1. mbikin kelas
2. masukin santri ke kelas
3. ngeluarin santri dari kelas

Ada dua kategori grup yg diperlukan:

1. grup khusus admin
ini adl grup tempat admin menjalankan perintah ke-adminan. 

2. grup kelas
ya kelas yg kita ketahui itu.

Jadi yg dilakukan adl:
1. admin mbikin kelas/grup telegram
2. admin mendaftarkan kelas itu di bot

lalu...

3. admin masukin santri di kelas/grup setoran
4. admin mendaftarkan santri ybs di bot

## Penggunaan



### Manajemen Kelas ###

RekapMTQBot memiliki konsep kelas dan okupansi. Kelas ya sbgmn yg kita kenal, ada kelas 05Bi, 05Aa, dst. Okupansi adalah slot santri yg ada di tiap kelas, yg ada 10 (sepuluh) slot. Data okupansi adl yg membuat monitoring data dimungkinkan. 

Berikut adl perintah seputar manajemen kelas. 

```
.listkelas
```
Melihat seluruh daftar kelas; kalau ingin tahu mana saja kelas yg sudah dibikin dan juga statusnya

```
.register
```
digunakan untuk membuat dan mendaftarkan kelas baru. Perintah ini harus diikuti dengan informasi nama kelas, semisal: .register 02bi atau .register 02Bi

```
.disable (nama_kelas)
```
Menonaktifkan kelas, dalam arti kelas ybs tidak dimonitor rekapannya.

```
.enable (nama_kelas)
```
Mengaktifkan kembali kelas. 

```
.DELETE (nama_kelas)
```
Menghapus kelas, semisal salah ketik atau lainnya. 

```
.okupansi (nama_kelas)
```
Melisting daftar okupansi di kelas ybs shg tahu mana yg masih lowong.

```
.rekapsetoran (nama_kelas)
```
Kirim rekap setoran untuk kelas yg ditunjuk tanpa menunggu trigger (trigger normal 00.05 WIB).

```
.mrjharian (nama_kelas)
```
Kirim rekap MRJ utk kelas yg ditunjuk tanpa tunggu trigger (trigger normal 09.00 WIB).


### Manajemen Santri ###

```
.addsatuan (nama_slot) (username_santri)
```
Memasukkan satu orang santri ke dalam slot okupansi kelas ybs. Contoh format slot okupansi = 05B-1-01.

```
.addall (nama_kelas) (daftar nama 10 user)
```
Memasukkan 10 username secara bersamaan ke dalam kelas. Daftar nama 10 user dipisah dg koma tanpa spasi. Jika jumlah yg dimasukkan < 10, maka gunakan tanda tanya "?" utk slot yg kosong.

```
.remove (username_santri)
```
Menghapus santri dari monitoring, semisal krn masuk takhasus atau di-DO.

```
.setghoib (username_santri)
```
Mengeset nilai bilangan ghoib santri ybs

```
.setudzur (username_santri)
```
Mengeset nilai bilangan udzur santri ybs

### Command oleh Santri ###

Command oleh santri selalu terdiri dari tiga huruf dan diawali dg titik (.) atau slash (/)

```
.new (info)
```
Untuk kirim setoran hafalan baru. Info adalah nomer suroh diikuti ayat permulaan dan ayat akhir (tetap diisi meski cuma setor satu ayat).

contoh: .new 93:5-10
no_suroh : ayat_mulai - ayat_end

```
.rev (info)
```
Kirim setoran revisi. Info sama persis dg info pada .new

```
.mrj (info)
```
Kirim setoran murojaah. Info adl nomer halaman. 

```
.sim
```
Simak setoran MRJ pasangan. Bot tidak peduli dia menyimak punya siapa.

```
.skt 
```
Ijin berhalangan karena sakit

```
.udz
```
Ijin berhalangan - udzur selain sakit

## Catatan

Di setiap dibutuhkan input berupa nama kelas, perhatikan bahwa jumlah digit nama kelas selalu ada 4 (empat).

Misal 02Bi, 05Ai, 12Ba, dst
'02' = dua digit awal adl nama kelas; di mana angka < 10 selalu diawali dg angka nol.
'B' = kategori kelas, A atau B. Bisa ditulis besar atau kecil; tapi baiknya dibiasakan huruf kecil 
'i/a' = ikhwan atau akhowat

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* Java SKD 1.8
* Telegram Bot Java Library (https://github.com/rubenlagus/TelegramBots)
* Maven
* Telegram API 3.3
* SQLite (production)
* MySQL (deployment)
* Hibernate
* Joda Time 2.9.9
* Emoji Java 3.1.0


## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.


## Authors

* **Akhmad Guntar** - *Initial work* - [guntarion](https://github.com/guntarion)

Yang mana bot ini merupakan kerja bareng dengan pengurus kesekretariatan MTQ Online @ArdynataMTQ @zakkiy @GunturPerwiraNegara @Abu_Asadulloh di bawah arahan mudir MTQ @abuazamMTQ

## License

This RekapMTQbot project is licensed under the **GNU GPL v3**
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc
