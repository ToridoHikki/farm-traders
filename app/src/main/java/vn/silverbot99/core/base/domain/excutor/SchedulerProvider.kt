package vn.silverbot99.core.base.domain.excutor

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun createScheduler() : Scheduler
}