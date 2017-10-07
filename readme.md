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

Karena kelas yang ditangani makin buanyak dan tenaga admin terbatas 😆

## Konsep

RekapMTQbot didasarkan pada prinsip dasar bhw program MTQ dibangun atas dasar kursi kepesertaan, di mana terdapat 10 kursi kepesertaan utk tiap kelas.

Jumlah seat tiap kelas itu tidak bisa berkurang (dan tidak bisa nambah). meskipun santri datang dan pergi, tapi kursinya ya tetap ada di situ. Dg kata lain: yg datang dan pergi BUKAN seat-nya, melainkan santrinya. krn jumlah kelas banyak, maka jumlah seat nya juga banyak. 

Utk bahasan bot ini, saya mengistilahkan seat sbg *okupansi*

Aktivitas terkait bot menyerupai yg dilakukan oleh admin di sekolah nyata, yakni:
1. membuat kelas
2. mendaftarkan santri
3. memasukkan santri ke kelas
3. mengeluarkan santri dari kelas

Ada dua kategori grup yg diperlukan:

1. grup khusus admin
ini adl grup tempat admin menjalankan perintah ke-adminan. Tapi perintah admin dapat dijalankan via chat japri dg bot. 

2. grup kelas
ya kelas yg kita ketahui itu.

Jadi yg dilakukan oleh admin adl:
1. membuat kelas/grup telegram
2. mendaftarkan kelas itu di bot

lalu...

3. meminta santri untuk melakukan registrasi
4. memasukkan santri yg sudah register di kelas/grup setoran

## Penggunaan

### Umum ###

```
.status
```
Cek apa bot online atau tidak. Perintah ini bisa dilaunch bila santri ingin tahu apakah dia mengirim info yg salah terkait setoran (sehingga tidak diterima) ataukah bot-nya yg sedang offline. Perintah ini terutama relevan ketika bot masih di tahap produksi (dipasang di kompie yg berpotensi sleep).

```
.id
```
Menginfokan first_name, last_name, username, dan user_id dari user yg mengeluarkan command. 

```
.broadcast.all
```
Membroadcast pesan ke seluruh kelas aktif. Dilakukan dg mereply pesan yg ingin dibroadcast. 



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
listid (nama_kelas)
```
Melisting daftar user_id dan nama santri di kelas ybs.

```
.rekapsetoran (nama_kelas)
```
Kirim rekap setoran untuk kelas yg ditunjuk tanpa menunggu trigger (trigger normal 00.01 WIB).

```
.mrjharian (nama_kelas)
```
Kirim rekap MRJ utk kelas yg ditunjuk tanpa tunggu trigger (trigger normal 09.00 WIB).

```
.mrjap
```
Kirim rekap MRJ akhir pekan utk kelas yg ditunjuk tanpa tunggu trigger (trigger normal Ahad 22.01 WIB).

```
.rkp (nama_kelas) (bilangan_mundur)
```
Meminta info rekap dari kelas ttg `n` hari ke belakang. Bila 1 berarti kemarin, bila 2 berarti dua hari lalu dst. 

```
.ismusyrif
```
Mendaftarkan user sbg musyrif di kelas ybs. Dilakukan dg mereply pesan manapun yg ditulis oleh sang musyrif. Pendaftaran ini dimaksudkan untuk memungkinkan musyrif mengeluarkan command yg khusus diperuntukkan bagi musyrif.

```
.iskorlas
```
Mendaftarkan user sbg korlas di kelas ybs. Dilakukan dg mereply pesan manapun yg ditulis oleh sang korlas. Pendaftaran ini dimaksudkan untuk memungkinkan musyrif mengeluarkan command yg khusus diperuntukkan bagi korlas.


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
.removebyid (user_id)
```
Menghapus santri dari monitoring dg menggunakan user_id nya. Misal bila ybs sudah ganti username. 

```
.setghoib (username_santri)
```
Mengeset nilai bilangan ghoib santri ybs

```
.setudzur (username_santri)
```
Mengeset nilai bilangan udzur santri ybs

### Command oleh Santri ###

Command oleh santri selalu terdiri dari tiga huruf dan diawali dg titik (.)

```
`.ana (nama panggilan/kunyah), (setoran suroh dan ayat terakhir yg mendapat ✅),(no HP), gender),(tahun kelahiran)`
```
Meregistrasikan diri. Ingat bahwa meregistrasi sbg santri tidak lantas membuat ybs teregistrasi sbg anggota di kelas setoran. 

contoh: 
`.ana Abu Qayyim,104:4,0813923422,i,1983`

antar informasi dipisah dg tanda koma. untuk gender, i = ikhwan, a = akhowat

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
Kirim setoran murojaah untuk santri kelas B yg sudah terkena kewajiban murojaah. Info adl nomer halaman. 

```
.map
```
Kirim setoran murojaah akhir pekan. Tidak ada info lain yg mengikuti command ini.


```
.sim
```
Simak setoran MRJ pasangan. Bot tidak peduli yang disimak setoran siapa.

```
.skt 
```
Ijin berhalangan karena sakit

```
.udz (info)
```
Ijin berhalangan - udzur selain sakit. Disertai dg keterangan, di mana info ini akan diteruskan ke grup kesekretariatan MTQ.

### Command oleh Musyrif ###

Koreksian dari musyrif akan masuk secara otomatis dalam rekap harian. Sbgmn normalnya koreksian, perintah-perintah ini dilakukan dg mereply pesan audio dari santri. Bisa juga dg mereply pesan setoran si santri, yakni pesan yg berisikan `.new ...` atau `.rev ...`

```
✅
```
Menandai pesan audio yg direply sbg lulus.

```
✅ 📝
```
Menandai pesan audio yg direply sbg lulus dg catatan. Antara dua ikon tersebut harus diberi pemisah spasi.

```
♻️
```
Menandai pesan audio yg direply sbg wajib diulang.

Perintah ini tidak bekerja bila musyrif menyertai informasi tambahan di setelah ikon tersebut. Misalkan perintah `✅ mantaapp! 👍🏼` tidak terekap oleh bot.


## Catatan

Di setiap dibutuhkan input berupa nama kelas, perhatikan bahwa jumlah digit nama kelas selalu ada 4 (empat).

Misal 02Bi, 05Ai, 12Ba, dst
'02' = dua digit awal adl nama kelas; di mana angka < 10 selalu diawali dg angka nol.
'B' = kategori kelas, A atau B. Bisa ditulis besar atau kecil; tapi baiknya dibiasakan huruf kecil 
'i/a' = ikhwan atau akhowat

## Kebijakan di sistem

Berikut adalah fungsionalitas dan kebijakan dari sistem:
* Yang bisa kirim perintah admin ya hanya admin aja
* Perintah yg jadi santapan santri tidak bisa dikirim via chat pribadi dg bot
* Bila santri belum terdaftar, maka setoran tidak dihiraukan
* Untuk mendaftar di database bot, santri harus punya username terlebih dahulu
* Bila info setoran tidak lengkap atau salah, maka akan ada notifikasi. Untuk saat ini, notifikasinya adalah bila setoran tidak disertai info ayat akhir, bila pesan dikirim tanpa mereply pesan audio, bila pesan dikirim di hari sabtu dan ahad.
* Setoran `.map` yg dikirim melewati pukul 12.00 WIB hari Ahad, akan ditolak. 


## Built With

* Java SDK 1.8
* Telegram API 3.3
* Telegram Bot Java Library (https://github.com/rubenlagus/TelegramBots)
* Maven
* SQLite (production)
* MySQL (deployment)
* Hibernate
* Joda Time 2.9.9
* Emoji Java 3.1.0
* JUnit 4 with Hamcrest
* Quartz Scheduler


## Authors

* **Akhmad Guntar** - *Initial work* - [guntarion](https://github.com/guntarion)

Yang mana bot ini merupakan kerja bareng dengan pengurus kesekretariatan MTQ Online @ArdynataMTQ @zakkiy @GunturPerwiraNegara @Abu_Asadulloh di bawah arahan mudir MTQ @abuazamMTQ

## License

This RekapMTQbot project is licensed under the **GNU GPL v3**
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
