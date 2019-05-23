# 任务状态
Quartz提供了getTriggerState方法来获取当前执行状态。
其中返回值分别代表意思如下：
STATE_BLOCKED 4 阻塞
STATE_COMPLETE 2 完成
STATE_ERROR 3 错误
STATE_NONE -1 不存在
STATE_NORMAL 0 正常
STATE_PAUSED 1 暂停
具体代码如下：

```StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
Scheduler scheduler = schedulerFactory.getScheduler();
int state = scheduler.getTriggerState(triggerName, triggerGroup);
```
DELETED
COMPLETE
PAUSED
PAUSED_BLOCKED
ERROR
BLOCKED
NORMAL

# 获取状态实现方法
org.quartz.impl.jdbcjobstore.JobStoreSupport.getTriggerState(java.sql.Connection, org.quartz.TriggerKey)

