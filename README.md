# 说明
主要实现定时任务的自定义管理，可以修改执行时间，暂停和恢复定时任务。在系统挂掉重启，能够恢复设置的定时任务。主要是利用官方提供的数据库持久化机制来实现的。

开发的时候只需要将业务逻辑代码封装到一个pojo即可，然后将定时任务添加到系统实现可插拔式管理。
# 任务状态
Quartz提供了getTriggerState方法来获取当前执行状态。
其中返回值分别代表意思如下：
```
STATE_BLOCKED 4 阻塞
STATE_COMPLETE 2 完成
STATE_ERROR 3 错误
STATE_NONE -1 不存在
STATE_NORMAL 0 正常
STATE_PAUSED 1 暂停
```
具体代码如下：

```StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
Scheduler scheduler = schedulerFactory.getScheduler();
int state = scheduler.getTriggerState(triggerName, triggerGroup);
```
对应数据表字段的值：
```
create table QRTZ_TRIGGERS
(
    SCHED_NAME     varchar(120) not null,
    TRIGGER_NAME   varchar(200) not null,
    TRIGGER_GROUP  varchar(200) not null,
    JOB_NAME       varchar(200) not null,
    JOB_GROUP      varchar(200) not null,
    DESCRIPTION    varchar(250) null,
    NEXT_FIRE_TIME bigint(13)   null,
    PREV_FIRE_TIME bigint(13)   null,
    PRIORITY       int          null,
    TRIGGER_STATE  varchar(16)  not null,
    TRIGGER_TYPE   varchar(8)   not null,
    START_TIME     bigint(13)   not null,
    END_TIME       bigint(13)   null,
    CALENDAR_NAME  varchar(200) null,
    MISFIRE_INSTR  smallint(2)  null,
    JOB_DATA       blob         null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint QRTZ_TRIGGERS_ibfk_1
        foreign key (SCHED_NAME, JOB_NAME, JOB_GROUP) references QRTZ_JOB_DETAILS (SCHED_NAME, JOB_NAME, JOB_GROUP)
)
```
字段TRIGGER_STATE的值如下：
```

DELETED
COMPLETE
PAUSED
PAUSED_BLOCKED
ERROR
BLOCKED
NORMAL
```

# 获取状态实现方法
`org.quartz.impl.jdbcjobstore.JobStoreSupport.getTriggerState(java.sql.Connection, org.quartz.TriggerKey)`
# snapshot
![Image add](https://github.com/gongxufan/quartz-manage/blob/master/snapshot/add.png)

![Image list](https://github.com/gongxufan/quartz-manage/blob/master/snapshot/list.png)


