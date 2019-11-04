package vn.silverbot99.core.app.domain.excecutor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import vn.silverbot99.core.base.domain.excutor.SchedulerProvider

class AndroidPostTaskSchedulerProvider : SchedulerProvider {
    override fun createScheduler(): Scheduler = AndroidSchedulers.mainThread()
}