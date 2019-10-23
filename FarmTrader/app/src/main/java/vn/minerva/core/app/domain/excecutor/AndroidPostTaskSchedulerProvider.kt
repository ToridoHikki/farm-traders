package vn.minerva.core.app.domain.excecutor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import vn.minerva.core.base.domain.excutor.SchedulerProvider

class AndroidPostTaskSchedulerProvider : SchedulerProvider {
    override fun createScheduler(): Scheduler = AndroidSchedulers.mainThread()
}