package cn.com.egova.quartz.service;


import cn.com.egova.quartz.entity.JobAndTrigger;
import com.github.pagehelper.PageInfo;

public interface IJobAndTriggerService {
    PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);

    void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception;

    void delJob(String jobClassName, String jobGroupName) throws Exception;

    void rescheduleJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception;

    void pauseJob(String jobClassName, String jobGroupName) throws Exception;

    void resumeJob(String jobClassName, String jobGroupName) throws Exception;
}
