
package com.gome.work.core;

public interface Constants {


    public static final String EXTRA_MODEL = "extra.model";

    public static final String MODEL_VIEW = "model.view";

    /**
     * 单选
     */
    public static final String MODEL_PICK_SINGLE = "model.pick.single";

    /**
     * 多选
     */
    public static final String MODEL_PICK_MULTI = "model.pick.multi";


    /**
     * 全局定义所有需要shared-preference 机制保存数据的key值
     */
    public interface PreferKeys {

        String BANNER_LIST = "pre.banner";

        String NEWS_LIST = "pre.news";

        String CHANNEL_LIST = "channel.news";

        public String ACCOUNT = "pre.account";

        public String ACCESS_TOKEN_INFO = "pre.access.token.info";

        /**
         * 当前登陆者的用户信息
         */
        public String LOGIN_USER_INFO = "pre.login.user";

        public static final String ACCESS_TOKEN = "pre.access.token";

        public static final String REQUEST_TOKEN = "pre.request.token";

        public static final String SHARED_PREFERENCES_NAME = "sp_work";

        /**
         * 上一版本Code
         */
        public static final String LAST_VERSION_CODE = "last.version.code";

        public static final String IS_SHOW_GUIDE_PAGE = "guide.page";

        public static final String SETTING_INFO = "setting_info";

        /**
         * 广告信息
         */
        public static final String AD_DATA = "ad.data";

        /**
         * 工作banner广告
         */
        public static final String JSON_WORK_BANNER = "json.work.banner";

        public static final String CITY_DATA = "city.data";

        public static final String RECOMMEND_AD_DATA = "recommend.ad.data";

//        /**
//         * 配置数据-学习阶段
//         */
//        public static final String SYS_CFG_GRADE = "sys.config.grade";
//
//        /**
//         * 配置数据--课程
//         */
//        public static final String SYS_CFG_COURSE = "sys.config.course";
//
//        /**
//         * 配置数据-教学类型
//         */
//        public static final String SYS_TEACH_TYPE = "sys.config.teach.type";
//
//        /**
//         * 配置数据-教学年龄
//         */
//        public static final String SYS_TEACH_AGE = "sys.config.teach.age";
//
//        /**
//         * 配置数据-教学年龄
//         */
//        public static final String SYS_STUDENT_SCORE = "sys.config.student.score";


        public static final String SYS_CONFIG_DATA = "sys.config.data";

        /**
         * 位置信息
         */
        public static final String SYS_LAST_LOCATION = "last.location";

        /**
         * 应用最近的升级信息
         */
        public static final String UPDATE_LAST_INFO = "update.last.info";

        /**
         * 各厂商离线推送push token
         */
        public static final String DEVICE_PUSH_TOKEN = "device.push.token";
    }

}
