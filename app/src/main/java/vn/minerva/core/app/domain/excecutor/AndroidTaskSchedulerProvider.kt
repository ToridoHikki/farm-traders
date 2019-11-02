package vn.minerva.core.app.domain.excecutor

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import vn.minerva.core.base.domain.excutor.SchedulerProvider

class AndroidTaskSchedulerProvider : SchedulerProvider {
    override fun createScheduler(): Scheduler = Schedulers.from(TaskExecutor.get())
}