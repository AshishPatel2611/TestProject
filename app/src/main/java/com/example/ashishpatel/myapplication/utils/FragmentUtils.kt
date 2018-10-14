package com.example.ashishpatel.myapplication.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

/**
 * Created by ashish on 4/12/17.
 */
object FragmentUtils {

    fun addFragment_(activity: AppCompatActivity, fragment: Fragment, container: Int) {

        val currentFragment = activity.supportFragmentManager.findFragmentById(container)

        if (currentFragment != null && fragment.javaClass.simpleName == currentFragment.javaClass.simpleName)
            return
        val fragmentPopped = activity.supportFragmentManager
                .popBackStackImmediate(fragment.javaClass.simpleName, 0)
        if (!fragmentPopped && activity.supportFragmentManager.findFragmentByTag(fragment.javaClass.simpleName) == null) {
            val transaction = activity.supportFragmentManager.beginTransaction()

            transaction.add(container, fragment, fragment.javaClass.simpleName)
            transaction.addToBackStack(fragment.javaClass.simpleName)
            transaction.commit()
        }
    }

    fun replaceFragment(activity: AppCompatActivity, fragment: Fragment, container: Int) {

        val currentFragment = activity.supportFragmentManager.findFragmentById(container)
        if (currentFragment != null && fragment.javaClass.simpleName == currentFragment.javaClass.simpleName)
            return
        activity.supportFragmentManager.popBackStackImmediate(fragment.javaClass.simpleName, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        if (activity.supportFragmentManager.findFragmentByTag(fragment.javaClass.simpleName) == null) {
            val transaction = activity.supportFragmentManager.beginTransaction()

            transaction.replace(container, fragment, fragment.javaClass.simpleName)
            transaction.addToBackStack(fragment.javaClass.simpleName)
            transaction.commit()

        }
    }

// if animation required 

/* transaction.setCustomAnimations(R.animator.fragment_slide_left_enter,
                    R.animator.fragment_slide_left_exit,
                    R.animator.fragment_slide_right_enter,
                    R.animator.fragment_slide_right_exit)
 */


    fun replaceFragmentWithoutBackstack(activity: AppCompatActivity, fragment: Fragment, container: Int) {

        val currentFragment = activity.supportFragmentManager.findFragmentById(container)
        if (currentFragment != null && fragment.javaClass.simpleName == currentFragment.javaClass.simpleName)
            return
        activity.supportFragmentManager.popBackStackImmediate(fragment.javaClass.simpleName, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(container, fragment, fragment.javaClass.simpleName)
        transaction.commit()
    }

    fun addFragmentWithoutBackstack(activity: AppCompatActivity, fragment: Fragment, container: Int) {

        //  val currentFragment = activity.supportFragmentManager.findFragmentById(container)

        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.add(container, fragment, fragment.javaClass.simpleName)
        transaction.commit()
    }

    fun addFragment(activity: AppCompatActivity, fragment: Fragment, container: Int) {

        val transaction = activity.supportFragmentManager.beginTransaction()
        val currentFragment = activity.supportFragmentManager.findFragmentById(container)

        if (currentFragment != null && fragment.javaClass.simpleName == currentFragment.javaClass.simpleName)
            return
        if (currentFragment != null) {
            transaction.remove(currentFragment)
        }
        transaction.add(container, fragment, fragment.javaClass.simpleName)
        transaction.addToBackStack(fragment.javaClass.simpleName)
        transaction.commit()
    }

    fun addMenuFragment(activity: AppCompatActivity, fragment: Fragment, container: Int) {

        activity.supportFragmentManager.popBackStack()

        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.add(container, fragment, fragment.javaClass.simpleName)

        transaction.commit()
    }

}