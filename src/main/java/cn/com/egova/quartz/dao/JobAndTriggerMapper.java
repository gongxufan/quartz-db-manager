package cn.com.egova.quartz.dao;

import java.util.List;

import cn.com.egova.quartz.entity.JobAndTrigger;

public interface JobAndTriggerMapper {
    public List<JobAndTrigger> getJobAndTriggerDetails();
}
