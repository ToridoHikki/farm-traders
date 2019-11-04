package vn.silverbot99.core.app.domain.excecutor

import vn.silverbot99.core.base.domain.interactor.UseCaseExecution

class AndroidUseCaseExecution : UseCaseExecution(AndroidTaskSchedulerProvider().createScheduler(), AndroidPostTaskSchedulerProvider().createScheduler())