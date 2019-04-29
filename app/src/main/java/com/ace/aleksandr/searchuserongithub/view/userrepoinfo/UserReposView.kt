package com.ace.aleksandr.searchuserongithub.view.userrepoinfo

import com.ace.aleksandr.searchuserongithub.model.GithubUser
import com.ace.aleksandr.searchuserongithub.model.UserRepo



interface UserReposView {
    fun showUser(user: GithubUser)
    fun showUserRepos(userRepos: List<UserRepo>)
    fun showError(errorText: String)
}
/**
 * уроки от Димана
 * **/
//
//interface DimonsView {
//    fun showAnimatioins(animations: List<Animation>)
//    fun showOffers(offers: List<Offer> )
//    fun showSwitchState(isOn: Boolean)
//    fun showEbalo()
//    fun showAdds()
//    fun showmark()
//
//
//}
//
//interface Presenter {
//    fun onAnimationClick(animation: Animation)
//    fun onFinishedAdd()
//
//
//}
//
//class PresenterImpl : Presenter{
//
//    val view: DimonsView? = null
//    val model: ColorSpace.Model? = null
//    override fun onAnimationClick(animation: Animation) {
//        view.showAdds()
//        model.saveSelectedAnimation()
//    }
//
//    override fun onFinishedAdd() {
//        view.showmark()
//    }
//
//}