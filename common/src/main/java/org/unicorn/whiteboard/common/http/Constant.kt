package org.unicorn.whiteboard.common.http

object Constant {

    const val ENV = "dev"
    const val AUENV = "dev" // autumn使用的环境
    const val BASE_URL = "https://$ENV.unicorn.org.cn/education/api/"
    const val OSS_URL = "https://$ENV.unicorn.org.cn/minioimg/"
    const val ODS_URL = "https://$AUENV.unicorn.org.cn/ods/api/"


    const val COGNITO_URL = "https://$AUENV.unicorn.org.cn/cognito/api/"

}
