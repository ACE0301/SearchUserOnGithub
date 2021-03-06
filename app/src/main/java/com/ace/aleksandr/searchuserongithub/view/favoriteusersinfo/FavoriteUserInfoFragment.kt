package com.ace.aleksandr.searchuserongithub.view.favoriteusersinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ace.aleksandr.searchuserongithub.R
import com.ace.aleksandr.searchuserongithub.model.UserRealm
import kotlinx.android.synthetic.main.fragment_favorite_user_info.*


class FavoriteUserInfoFragment : Fragment(), FavoriteUserInfoView {

    companion object {
        const val TAG = "FavoriteUserInfoFragment"
        private const val ARGUMENT_USER_LOGIN = "ARGUMENT_USER_LOGIN"

        fun newInstance(userLogin: String) = FavoriteUserInfoFragment().apply {
            arguments = Bundle().apply {
                putString(ARGUMENT_USER_LOGIN, userLogin)
            }
        }
    }

    private val presenter by lazy {
        FavoriteUserInfoPresenter(this, arguments?.getString(ARGUMENT_USER_LOGIN) ?: "") }
    private val mAdapter = FavoriteUserInfoAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_favorite_user_info, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvFavoriteUsersInfoList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }
    }

    override fun showUserInfo(user: UserRealm) {
        tvFavoriteUserLastName.text =
            "Имя пользователя ${user.name ?: "не указано"}, город: ${user.location ?: "не указан"}"
        mAdapter.data = user.listOfRepos?.map { it.name ?: "отсутствует" } ?: emptyList()
    }

    override fun showError(errorText: String) {
        Toast.makeText(activity, errorText, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        presenter.onDestroy()
        super.onDestroyView()
    }

}