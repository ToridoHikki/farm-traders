package vn.silverbot99.core.base.domain.interactor

import io.reactivex.Scheduler

open class UseCaseExecution constructor(val execution: Scheduler, val postExecution: Scheduler)