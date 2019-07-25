package com.ace.aleksandr.searchuserongithub.view.usersearch

import com.ace.aleksandr.searchuserongithub.base.BasePresenter
import com.ace.aleksandr.searchuserongithub.base.disposeIfNotNull
import com.ace.aleksandr.searchuserongithub.data.api.ApiHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserSearchPresenter(
    view: UserSearchView
) : BasePresenter<UserSearchView>(view) {


    override fun onCreate() {
    }

    private var disposableGetUserInfo: Disposable? = null

    fun getUserInfo(name: String) {
        /**
         * Применительно к Observable тип Disposable позволяет вызывать метод dispose,
         * означающий «Я закончил работать с этим ресурсом, мне больше не нужны данные».
         * Если у вас есть сетевой запрос, то он может быть отменён.
         * Если вы прослушивали бесконечный поток нажатий кнопок, то это будет означать,
         * что вы больше не хотите получать эти события, в таком случае можно удалить OnClickListener у View.
         *
         * Observable - поток данных(может выпустить 0, 1 или несколько элементов)
         *
         * Schedulers.io()
         * Этот планировщик основывается на неограниченном пуле потоков и используется для интенсивной работы с вводом-выводом без использования ЦП,
         * например, доступ к файловой системе, выполнение сетевых вызовов, доступ к базе данных и так далее.
         * Количество потоков в этом планировщике неограничено и может расти по мере необходимости.
         *
         * subscribeOn - указывает на каком шедулере нужно выполнить начало подписки(
         * в какой поток наблюдаемый источник (source observable) будет передавать элементы)
         * Если не использовать subscribeOn(), то все события происходят в том потоке, в котором произошел вызов кода (в нашем случае — main поток)
         *
         * observeOn - указывает на каком шедулере нужно "наблюдать" за данными выходящими из Observable
         * */
        disposableGetUserInfo.disposeIfNotNull()
        disposableGetUserInfo = ApiHolder.service.getUserInfo(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view?.isShowLoading(true) }
            .doFinally { view?.isShowLoading(false) }
            .subscribe({
                view?.showUserInfo(it)
            }, {
                view?.showError(it.message ?: "")
            })
    }

    override fun onDestroy() {
        disposableGetUserInfo?.dispose()
    }
}