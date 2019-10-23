package vn.minerva.core.app.domain.excecutor

import vn.minerva.core.base.domain.interactor.UseCaseExecution

class AndroidUseCaseExecution : UseCaseExecution(AndroidTaskSchedulerProvider().createScheduler(), AndroidPostTaskSchedulerProvider().createScheduler())