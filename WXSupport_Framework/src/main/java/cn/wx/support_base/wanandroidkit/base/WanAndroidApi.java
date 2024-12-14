package cn.wx.support_base.wanandroidkit.base;

/**
 * @author 小昕
 * wanandroid平台的api接口：https://www.wanandroid.com/blog/show/2
 * 再次感谢鸿洋大佬
 */
public class WanAndroidApi {

    public static final String BASE_URL = "https://www.wanandroid.com";

    //首页相关
    public static final String ARTICLE_LIST = "/article/list/%s/json"; //文章列表
    public static final String HOME_BANNER = "/banner/json";//首页Banner
    public static final String USUALLY_WEBSITES = "/friend/json";//常用网站
    public static final String HOTKEY_SEARCH = "/hotkey/json";//搜索热词
    public static final String TOP_ARTICLE = "/article/top/json";//置顶文章

    //体系
    public static final String STRUCTURE_DATA = "/tree/json";//体系数据
    public static final String KNOWLEDGE_STRUCTURE_ARTICLE = "/article/list/0/json?cid=60";//知识体系下的文章
    public static final String SEARCH_BY_AUTHOR = "/article/list/0/json?author=%s";//根据作者名搜索文章

    //导航
    public static final String NAV_DATA = "/navi/json";//导航数据

    //项目
    public static final String PROJECT_CLASSIC = "/project/tree/json";//项目分类
    /**
     * 1、页码，从1开始
     * 2、cid：项目id，即tabId
     */
    public static final String PROJECT_LIST_DATA = "/project/list/%d/json?cid=%d";//项目列表数据

    //登录与注册
    public static final String USER_LOGIN = "/user/login";//登录
    public static final String USER_REGISTER = "/user/register";//注册
    public static final String USER_LOGOUT = "/user/logout/json";//登出

    //收藏
    public static final String COLLECT_ARTICLES_LIST = "/lg/collect/list/0/json";//收藏文章列表
    public static final String COLLECT_INSIDE_ARTICLE = "/lg/collect/%d/json";//收藏站内文章
    public static final String COLLECT_OUTSIDE_ARTICLE = "/lg/collect/add/json";//收藏站外文章
    /**
     * POST
     * 文章 id:拼接在 url 上
     * 	title: 文章标题
     * 	link: 文章 url
     * 	author: 作者
     * 	注意：调用此接口，一定要带上 title,link,author，否则会认为想设置为""。
     */
    public static final String EDIT_COLLECT_ARTICLE = "/lg/collect/user_article/update/%d/json";//编辑收藏的文章
    /**
     * 方法：POST
     * 参数：
     * 	id:拼接在链接上
     */
    public static final String CANCEL_COLLECT_ARTICLE_LIST = "/lg/uncollect_originId/%d/json";//取消文章收藏
    /**
     * 方法：POST
     * 参数：
     * 	id:拼接在链接上
     * 	originId:列表页下发，无则为-1
     */
    public static final String CANCEL_COLLECT_FROM_MY_COLLECT = "/lg/uncollect/%d/json";//取消文章收藏，从我的收藏页中取消
    public static final String COLLECT_WEBSITE_LIST = "/lg/collect/usertools/json";//收藏网站列表
    public static final String COLLECT_WEBSITE = "/lg/collect/addtool/json";//收藏网站
    public static final String EDIT_COLLECT_WEBSITE = "/lg/collect/updatetool/json";//编辑收藏的网站
    public static final String DELETE_COLLECT_WEBSITE = "/lg/collect/deletetool/json";//删除收藏的网站

    //搜索
    /**
     * 方法：POST
     * 参数：
     * 	页码：拼接在链接上，从0开始。
     * 	k ： 搜索关键词
     */
    public static final String SEARCH = "/article/query/%d/json";//搜索

    //个人信息
    public static final String OWNER_INFO = "/user/lg/userinfo/json";//个人信息

    //公众号
    public static final String PUBLIC_LIST = "/wxarticle/chapters/json";//公众号列表
    /**
     * 1、公众号ID
     * 2、页码
     */
    public static final String PUBLIC_HISTORY = "/wxarticle/list/%d/%d/json";//某个公众号的历史数据:公众号ID，页码
    /**
     * 1、公众号id
     * 2、页码
     * 3、搜索关键词
     */
    public static final String PUBLIC_HISTORY_ARTICLES = "/wxarticle/list/%d/%d/json?k=%s";//某个公众号中搜索历史文章
}
