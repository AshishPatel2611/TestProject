package com.example.ashishpatel.myapplication.utils

import android.app.Activity
import android.content.Context

/**
 * Created by codexalters on 12/3/18.
 */
object MyAppPreferenceUtils {

   /* fun saveUser(context: Activity, user: UserBean) {

        PreferenceUtils.setString(context, Const.ID, user.id)
        PreferenceUtils.setString(context, Const.USER_NAME, user.vName)
        PreferenceUtils.setString(context, Const.USER_EMAIL, user.vEmail)
        PreferenceUtils.setString(context, Const.GENDER, user.nGender)
        PreferenceUtils.setString(context, Const.PROFILE_IMAGE, user.vImagePath)
        PreferenceUtils.setString(context, Const.CONTACT_NUMBER, user.nContactNo)
        PreferenceUtils.setString(context, Const.ADDRESS, user.vAddress)
        PreferenceUtils.setString(context, Const.LATITUDE, user.dLat)
        PreferenceUtils.setString(context, Const.LONGITUDE, user.dLng)
        PreferenceUtils.setString(context, Const.USER_TYPE, user.nUserType)
        PreferenceUtils.setString(context, Const.AMOUT, user.dTotalAmount)
        PreferenceUtils.setString(context, Const.BANK_NAME, user.vBankName)
        PreferenceUtils.setString(context, Const.BRANCH_NAME, user.vBranchName)
        PreferenceUtils.setString(context, Const.ACC_NUMBER, user.nAccountNumber)
        PreferenceUtils.setString(context, Const.ROUTING_NUMBER, user.nRoutingNo)
        PreferenceUtils.setString(context, Const.TOKEN, "Bearer ".plus(user.vToken))
    }


    fun getUser(context: Activity): UserBean {

        val user = UserBean()
        user.id = PreferenceUtils.getString(context, Const.ID, user.id)
        user.vName = PreferenceUtils.getString(context, Const.USER_NAME, user.vName)
        user.vEmail = PreferenceUtils.getString(context, Const.USER_EMAIL, user.vEmail)
        user.nGender = PreferenceUtils.getString(context, Const.GENDER, user.nGender)
        user.vImagePath = PreferenceUtils.getString(context, Const.PROFILE_IMAGE, user.vImagePath)
        user.nContactNo = PreferenceUtils.getString(context, Const.CONTACT_NUMBER, user.nContactNo)
        user.vAddress = PreferenceUtils.getString(context, Const.ADDRESS, user.vAddress)
        user.dLat = PreferenceUtils.getString(context, Const.LATITUDE, user.dLat)
        user.dLng = PreferenceUtils.getString(context, Const.LONGITUDE, user.dLng)
        user.nUserType = PreferenceUtils.getString(context, Const.USER_TYPE, user.nUserType)
        user.dTotalAmount = PreferenceUtils.getString(context, Const.AMOUT, user.dTotalAmount)
        user.vBankName = PreferenceUtils.getString(context, Const.BANK_NAME, user.vBankName)
        user.vBranchName = PreferenceUtils.getString(context, Const.BRANCH_NAME, user.vBranchName)
        user.nAccountNumber = PreferenceUtils.getString(context, Const.ACC_NUMBER, user.nAccountNumber)
        user.nRoutingNo = PreferenceUtils.getString(context, Const.ROUTING_NUMBER, user.nRoutingNo)
        user.vToken = PreferenceUtils.getString(context, Const.TOKEN, user.vToken)

        return user
    }*/

    fun getToken(context: Context): String {
        return PreferenceUtils.getString(context, Const.TOKEN, "")
    }


    fun getLoginStatus(context: Activity): Boolean {
        return PreferenceUtils.getBoolean(context, Const.IS_LOGGED_IN, false)
    }

    fun setLoginStatus(context: Activity, loggedIn: Boolean) {
        PreferenceUtils.setBoolean(context, Const.IS_LOGGED_IN, loggedIn)
    }

    fun getProfileImage(context: Activity): String {
        return PreferenceUtils.getString(context, Const.PROFILE_IMAGE, "")
    }

    fun getUserName(context: Activity): String {
        return PreferenceUtils.getString(context, Const.USER_NAME, "User")
    }
}