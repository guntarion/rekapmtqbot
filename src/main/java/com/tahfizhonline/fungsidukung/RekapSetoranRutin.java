package com.tahfizhonline.fungsidukung;

import com.tahfizhonline.MTQBotRekap;
import com.tahfizhonline.entity.SqliteJdbc;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class RekapSetoranRutin  implements Job {
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        System.out.println("Menjalankan rekap setoran rutin harian");
        SqliteJdbc database = new SqliteJdbc();
        database.generateDailyRekap();
    }
}
