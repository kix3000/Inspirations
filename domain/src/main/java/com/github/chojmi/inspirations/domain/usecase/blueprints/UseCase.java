package com.github.chojmi.inspirations.domain.usecase.blueprints;

import com.github.chojmi.inspirations.domain.executor.PostExecutionThread;
import com.github.chojmi.inspirations.domain.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.github.chojmi.inspirations.domain.utils.Preconditions.checkNotNull;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link DisposableObserver}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class UseCase<T extends BaseSubmitUiModel, Params extends BaseSubmitEvent> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposables;

    public UseCase(@NonNull ThreadExecutor threadExecutor, @NonNull PostExecutionThread postExecutionThread) {
        this.threadExecutor = checkNotNull(threadExecutor);
        this.postExecutionThread = checkNotNull(postExecutionThread);
        this.disposables = new CompositeDisposable();
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    public abstract Observable<T> buildUseCaseObservable(Observable<Params> params);

    /**
     * Executes the current use case.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable build
     * by {@link #buildUseCaseObservable(Observable<Params>)} ()} method.
     */
    public void execute(DisposableObserver<T> observer, Observable<Params> inputEvents) {
        checkNotNull(observer);
        final Observable<T> observable = this.buildUseCaseObservable(inputEvents)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(Disposable disposable) {
        checkNotNull(disposable);
        checkNotNull(disposables);
        disposables.add(disposable);
    }
}
