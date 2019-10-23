package vn.minerva.core.base.domain.interactor

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import vn.minerva.core.base.domain.exception.EmptyOutputException

abstract class UseCase<in Input, Output> internal constructor(private val useCaseExecution: UseCaseExecution) {
    private var disposables: CompositeDisposable = CompositeDisposable()

    /**
     * Builds an [Observable] which will be used when executing the current [UseCase].
     */
    internal abstract fun buildUseCaseObservable(input: Input): Observable<Output>

    fun executeAsync(resultListener: ResultListener<Output>, input: Input): UseCaseTask {
        val observer: DefaultObserver<Output> = DefaultObserver()
        observer.addResultListener(LogResultListener())
        observer.addResultListener(ViewResponseResultListener(resultListener))

        val observable = this.buildUseCaseObservable(input)
                .subscribeOn(useCaseExecution.execution)
                .observeOn(useCaseExecution.postExecution)

        val disposable = observable.subscribeWith(observer)
        addDisposable(disposable)
        return UseCaseTask(disposable)
    }

    @Throws(Throwable::class)
    fun execute(input: Input): Output {
        val outputObservable = OutputObservable<Output>()
        outputObservable.addResultListener(LogResultListener())
        this.buildUseCaseObservable(input).subscribeWith(outputObservable)
        if (outputObservable.output != null) {
            return outputObservable.output!!
        }
        throw if (outputObservable.exception != null) {
            outputObservable.exception!!
        } else EmptyOutputException()
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun cancel() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
        disposables = CompositeDisposable()
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}