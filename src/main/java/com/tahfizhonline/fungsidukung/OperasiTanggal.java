package com.tahfizhonline.fungsidukung;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.chrono.IslamicChronology;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class OperasiTanggal {

    public int hitungBedaHarinya(String dateStop) {
        String dateStart = "01 01 1900";
//        String dateStop = "17/12/2016";
        int selisihHariAlaExcel = 0;

        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            DateTime dt1 = new DateTime(d1);
            DateTime dt2 = new DateTime(d2);

            selisihHariAlaExcel = Days.daysBetween(dt1, dt2).getDays() + 1;

//            System.out.print("Selisih harinya = " + selisihHariAlaExcel + " days, ");
//            System.out.print(Hours.hoursBetween(dt1, dt2).getHours() % 24 + " hours, ");
//            System.out.print(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + " minutes, ");
//            System.out.print(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60 + " seconds.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return selisihHariAlaExcel;
    }

    public String generateTodayTanggal() {
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.now();
//        System.out.println(dtf1.format(localDate)); // 2017/07/16
//        System.out.println(dtf2.format(localDate)); // 170716

        return dtf2.format(localDate);
    }

    public String getTodayDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd");
        LocalDate localDate = LocalDate.now();
        return dateTimeFormatter.format(localDate);
    }

    public String getDayOfWeek() {
        String namaHari = "";
        LocalDate localDate = LocalDate.now();
//        System.out.println(localDate.getDayOfWeek());

        if (localDate.getDayOfWeek().toString().equals("SUNDAY")) namaHari = "Ahad";
        else if (localDate.getDayOfWeek().toString().equals("MONDAY")) namaHari = "Senin";
        else if (localDate.getDayOfWeek().toString().equals("TUESDAY")) namaHari = "Selasa";
        else if (localDate.getDayOfWeek().toString().equals("WEDNESDAY")) namaHari = "Rabu";
        else if (localDate.getDayOfWeek().toString().equals("THURSDAY")) namaHari = "Kamis";
        else if (localDate.getDayOfWeek().toString().equals("FRIDAY")) namaHari = "Jumat";
        else if (localDate.getDayOfWeek().toString().equals("SATURDAY")) namaHari = "Sabtu";

        return namaHari;
    }

    public String[] getHijriDate() {

        HijrahDate hdate = HijrahChronology.INSTANCE.date(LocalDate.now());

        String arrTanggalHijriah[] = hdate.toString().split(" ");
        String infoTanggalHijriah = arrTanggalHijriah[2];

        String arrElemenTanggalHijriah[] = infoTanggalHijriah.split("-");
        String hijriDate = arrElemenTanggalHijriah[2];
        String hijriMonth = arrElemenTanggalHijriah[1];
        String hijriYear = arrElemenTanggalHijriah[0];

        String namaBulanIslam = "";
        if (hijriMonth.equals("1")) namaBulanIslam = "Muharram";
        else if (hijriMonth.equals("2")) namaBulanIslam = "Safar";
        else if (hijriMonth.equals("3")) namaBulanIslam = "Rabiul awal";
        else if (hijriMonth.equals("4")) namaBulanIslam = "Rabiul akhir";
        else if (hijriMonth.equals("5")) namaBulanIslam = "Jumadil awal";
        else if (hijriMonth.equals("6")) namaBulanIslam = "Jumadil akhir";
        else if (hijriMonth.equals("7")) namaBulanIslam = "Rajab";
        else if (hijriMonth.equals("8")) namaBulanIslam = "Sya'ban";
        else if (hijriMonth.equals("9")) namaBulanIslam = "Ramadhan";
        else if (hijriMonth.equals("10")) namaBulanIslam = "Syawal";
        else if (hijriMonth.equals("11")) namaBulanIslam = "Dzulkaidah";
        else if (hijriMonth.equals("12")) namaBulanIslam = "Dzulhijjah";

//        System.out.println(hijriDate + " " + hijriMonth + " (" + namaBulanIslam + ") " + hijriYear);

        String[] tanggalHijriah = {hijriDate, hijriMonth, namaBulanIslam, hijriYear};

        return tanggalHijriah;

    }

    public String getTanggalPelaporan() {
        String[] tanggalIslam = getHijriDate();

        StringBuilder sb = new StringBuilder();
        sb.append(getDayOfWeek()); sb.append(", ");
        sb.append(tanggalIslam[0]); sb.append(" ");
        sb.append(tanggalIslam[2]); sb.append(" ");
        sb.append(tanggalIslam[3]); sb.append("H\n");

        return sb.toString();
    }



}
