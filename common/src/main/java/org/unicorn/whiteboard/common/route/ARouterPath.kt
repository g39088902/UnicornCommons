package org.unicorn.whiteboard.common.route

/**
 * create by tlq,on 2022/2/23
 * Desc:/module名/组件名/destination
 */
object ARouterPath {
    //登录注册
    const val UPDATE = "/app/activity/update"
    const val LOGIN = "/login/activity/login"
    const val LOGIN_FRAGMENT = "/login/fragment/login"
    const val REGISTER_FRAGMENT = "/login/fragment/register"
    const val PHONE_FRAGMENT = "/login/fragment/phone"
    const val PRIVACY_PROTOCOL = "/login/activity/privacyProtocol"
    const val CHILD_PRIVACY_PROTOCOL = "/login/activity/childPrivacyProtocol"
    const val USER_PROTOCOL = "/login/activity/userProtocol"
    const val SAFETY = "/login/activity/safety"

    //主页
    const val HOME_PAGE = "/app/activity/homePage"
    const val CHECK_HOMEWORK = "/app/activity/checkHomework"
    const val NOTIFICATION = "/app/activity/notification"
    const val NOTIFICATION_FRAGMENT = "/app/fragment/notification"
    const val NOTIFICATION_DETAIL = "/app/fragment/detail"

    //学校管理
    const val SCHOOL_MANAGE = "/app/activity/schoolManage"
    const val MANAGE_SCHOOL_FRAGMENT = "/app/fragment/manageSchool"

    // 用户信息
    const val CHANGE_PASSWORD = "/login/fragment/changePassword"
    const val BIND_PHONE = "/login/fragment/bindPhone"

    //个人资料
    const val PROFILE = "/login/activity/profile"
    const val USER_PROFILE_FRAGMENT = "/login/fragment/userProfile"

}