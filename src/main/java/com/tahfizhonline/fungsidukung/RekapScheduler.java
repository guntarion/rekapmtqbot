package com.tahfizhonline.fungsidukung;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class RekapScheduler {

    public void rekapanSetoranHarian() {
        JobDetail job = JobBuilder.newJob(RekapSetoranRutin.class)
                .withIdentity("rekapSetoranRutin", "rekapanRutin").build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("rekapSetoranRutinTrigger", "rekapanRutin")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0 59 23 ? * MON-FRI"))
                .build();
        try {
            //schedule it
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
