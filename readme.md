# Rekap MTQ bot

Bot Telegram untuk membantu rekap otomatis aktivitas tahfizh tahsin MTQ Online (www.tahfizhonline.com)

### Gambaran Umum
Ini adalah aplikasi bot yang dibuat dg bahasa pemrograman Java

RekapMTQbot menjalankan fungsi sbg berikut:

1. memonitor dan membuat rekap setoran harian (setoran baru maupun revisi)

2. memonitor dan merekap setoran MRJ dan simak atasnya

3. membuat list daftar MRJ akhir pekan

4. memonitor dan merekap MRJ akhir pekan

5. memonitor keaktifan (ghoib dan udzur) santri

### Mengapa Bot ini dibuat

A short description of the motivation behind the creation and maintenance of the project. This should explain **why** the project exists.

## Konsep

RekapMTQbot didasarkan pada prinsip dasar bhw program MTQ dibangun atas dasar kursi kepesertaan, di mana terdapat 10 kursi kepesertaan utk tiap kelas.

Jumlah seat tiap kelas itu tidak bisa berkurang (dan tidak bisa nambah). meskipun santri datang dan pergi, tapi kursinya ya tetap ada di situ. Dg kata lain: yg datang dan pergi BUKAN seat-nya, melainkan santrinya. krn jumlah kelas banyak, maka jumlah seat nya juga banyak. 

Utk bahasan bot ini, saya mengistilahkan seat sbg *okupansi*

Aktivitas terkait bot menyerupai yg dilakukan oleh admin di telegram, yakni:
1. mbikin kelas
2. masukin santri ke kelas
3. ngeluarin santri dari kelas

ada dua kategori grup yg diperlukan:

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


###Command oleh Santri###

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

## API Reference

Depending on the size of the project, if it is small and simple enough the reference docs can be added to the README. For medium size to larger projects it is important to at least provide a link to where the API reference docs live.

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

* Dropwizard - Bla bla bla
* Maven - Maybe
* Atom - ergaerga

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **John Doe** - *Initial work* - [JohnDoe](https://github.com/JohnDoe)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the **BSD License** - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc
