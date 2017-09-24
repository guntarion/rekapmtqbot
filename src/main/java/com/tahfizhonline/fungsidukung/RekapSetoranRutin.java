package com.tahfizhonline.fungsidukung;

import com.tahfizhonline.entitydefinition.SqliteJdbc;
import com.tahfizhonline.entityoperation.OperasiInfoKelas;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class RekapSetoranRutin  implements Job {
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        System.out.println("Melihat daftar kelas");
//        SqliteJdbc database = new SqliteJdbc();
//        database.generateDailyRekap();
        OperasiInfoKelas operasiInfoKelas = new OperasiInfoKelas();
        operasiInfoKelas.listSeluruhKelas();


    }
}
