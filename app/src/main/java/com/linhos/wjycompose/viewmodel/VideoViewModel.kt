package com.linhos.wjycompose.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.linhos.wjycompose.model.entity.VideoEntity
import com.linhos.wjycompose.model.service.HomeService

class VideoViewModel : ViewModel() {
    var list by mutableStateOf(
        listOf(
            VideoEntity(
                title = "第一个video entity title,第一个video entity title,第一个video entity title,第一个video entity title",
                type = "视频课程",
                duration = "00:02:00",
                imageUrl = "https://picsum.photos/200/300?random=1"
            ),
            VideoEntity(
                title = "第2个video entity title,第2个video entity title,第2个video entity title",
                type = "视频课程",
                duration = "00:03:00",
                imageUrl = "https://picsum.photos/200/300?random=2"
            ),
            VideoEntity(
                title = "第3个video entity title",
                type = "视频课程",
                duration = "00:04:00",
                imageUrl = "https://picsum.photos/200/300?random=3"
            ), VideoEntity(
                title = "第4个video entity title",
                type = "视频课程",
                duration = "00:05:00",
                imageUrl = "https://picsum.photos/200/300?random=4"
            ), VideoEntity(
                title = "第5个video entity title",
                type = "视频课程",
                duration = "00:06:00",
                imageUrl = "https://picsum.photos/200/300?random=5"
            ), VideoEntity(
                title = "第6个video entity title",
                type = "视频课程",
                duration = "00:07:00",
                imageUrl = "https://picsum.photos/200/300?random=6"
            ), VideoEntity(
                title = "第7个video entity title",
                type = "视频课程",
                duration = "00:08:00",
                imageUrl = "https://picsum.photos/200/300?random=7"
            ), VideoEntity(
                title = "第8个video entity title,第8个video entity title,第8个video entity title,第8个video entity title",
                type = "视频课程",
                duration = "00:09:00",
                imageUrl = "https://picsum.photos/200/300?random=8"
            )
        )
    )
    private val pageSize=10
    private var pageOffset=1
    suspend fun fetchList(){
       val res= HomeService.instance().videoList(pageOffset,pageSize)
        pageOffset++
        Log.d("===","fetchList")
        Log.d("===","${res.code}")
        if(res.code==0 && res.data!=null){
            Log.d("===","fetchList true")
            list=res.data
            fatchLoaded=true
        }

    }
    var fatchLoaded by mutableStateOf(false)
    suspend fun refresh(){
        fetchList()
        if (list.size<pageSize){
            pageOffset=1
        }
    }

    suspend fun fetchVideoInfo(){
        val info =HomeService.instance().videoInfo("")
        if (info.code==0 ){
            info.data?.let {
                title=it.title
                content="""$head
                    ${it.desc}
                    $end
                """.trimMargin()

                if (it.video!=null)videoUrl=it.video
            }
        }
    }

    var title by mutableStateOf("中老铁路首趟国际旅客列车发车，昆明至万象间可实现乘火车当日通达")


    val head = """
        <!DOCTYPE html>
        <html lang="zh-cmn-Hans">
        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta http-equiv="content-language" content="zh-CN">
            <title>中老铁路首趟国际旅客列车发车，昆明至万象间可实现乘火车当日通达</title>
            <style>img{
                max-width:100% !important
            }</style>
        </head>
        <body>""".trimIndent()

    val end = """
        </body>
        </html>""".trimIndent()

    /*  val content = """$head
          <p>
              北京时间4月13日8点08分，中老铁路首趟国际客运列车D887次旅客列车从昆明南发车，开往老挝万象。 从今天起，中老铁路将从昆明南、万象站双向对开国际旅客列车，昆明至万象间可实现乘火车当日通达。
          </p>
          $end
      """.trimIndent()*/
    var content by mutableStateOf(
        """$head
                <div class="content all-txt">
        <p>
            （观察者网讯）美国方面对TikTok的打压还在继续。据美国有线电视新闻网（CNN）4月7日报道，在共和党籍州长德桑蒂斯主政的佛罗里达州，当地多所公立大学被要求在校园网和公共设备中封杀包括TikTok在内的中国社交软件，而理由又是所谓的“保护数据安全”。
        </p>
        <p style="text-align:center;">
            <img src="https://i.guancha.cn/news/social/2023/04/10/20230410124848623.png" title="点击查看大图" class="bigimg" width="530"> 
        </p>
        
        <p>
            报道称，佛州州立大学系统（SUS）负责人雷·罗德里格斯（Ray Rodrigues）4月5日发出一份备忘录，要求“相关机构从大学拥有的所有设备上剔除SUS要求封禁的技术，切断与这些技术存在关联的网络流量”，该禁令即刻生效。SUS是佛州的公立大学体系，包含佛罗里达大学、佛罗里达州立大学、北佛罗里达大学等12所主要公立大学，覆盖超30万学生。
        </p>
        <p>
            根据SUS发出的指令，除TikTok外，腾讯QQ、微信及任何子公司或关联公司的技术也将被封禁。另外，来自俄罗斯的反病毒软件卡巴斯基和社交平台VKontakte也在封禁之列。
        </p>
        <p>
            在写给CNN的一份声明中，SUS宣称这么做是为了保护所谓的“数据隐私”。
        </p>
        <p>
            “数据隐私，特别是涉及学生数据和教师研究的数据隐私，是佛州州立大学系统的一个关键优先事项，”
        </p>
        <p style="text-align:center;">
            <img src="https://i.guancha.cn/news/social/2023/04/10/20230410125108783.jpg" title="点击查看大图" class="bigimg" width="530"> 
        </p>
        
        <p style="text-align:left;">
            声明称，根据3月29日更新的规定，SUS已建立技术审查机制，SUS所有12所公立大学需遵循佛州政府批准的“网络威胁禁用技术清单”，TikTok、微信、QQ、VKonatke和卡巴斯基都被认定为“对国家安全构成直接风险的外国行为对象”，因此处于被封禁之列。
        </p>
        <p>
            佛罗里达州立大学在发给学生的邮件称，上述软件和应用与外国政府存在关联，会收集用户的“个人生物信息”，从而威胁到美国的个人乃至美国国家安全。校方强烈建议所有学生和教职员工“停止使用这些被禁止的技术，并从他们的个人设备中删除这些应用程序”，“采取这一行动将有助于保护你的个人信息和大学数据”，学校公共网络也会封杀这些软件的接入。
        </p>
        <p>
            不过“今日美国”坦言，尽管学校将上述中国社交软件描述得很吓人，但并没有证据支撑这些指控。
        </p>
        <p style="text-align:center;">
            <img src="https://i.guancha.cn/news/external/2023/04/10/20230410125140413.png" title="点击查看大图" class="bigimg" width="530"> 
        </p>
        
        <p>
            美国媒体《每日点报》（daily dot）9日报道分析，禁令不能完全阻止用户访问网站，如果移动用户使用移动数据而不是通过WiFi连接网站，他们仍然可以观看TikTok视频。一位TikTok用户称，禁令妨碍了他开展工作，导致他不得不切换到移动数据。
        </p>
        <p>
            此前，美国已有多个州颁布了类似禁令。综合美媒报道，去年12月以来已有包括田纳西州、俄克拉荷马州、得克萨斯州、马里兰州、南卡罗来纳州等州宣布在州政府设备或网络上禁用TikTok。
        </p>
        <p>
            例如在去年12月，共和党籍马里兰州州长拉里·霍甘（Larry Hogan）宣布了一项紧急网络安全指令，禁止TikTok、QQ、微信以及华为等中俄软件和产品平台，他声称这些实体有可能收集敏感个人信息。
        </p>
        <p>
            就在今年2月，另一名共和党人得州州长格雷格·阿博特（Greg Abbott）公布了一项适用于州政府机构的全州示范安全计划，包括禁止并防止在任何州政府配发的设备上下载或使用TikTok和禁用技术。
        </p>
        <p>
            对于美国方面屡屡以“国家安全”为由打压别国企业之举，中国外交部发言人曾表示，在美国一些政客眼里，“国家安全”仿佛成了美国给别国找事的“万金油”，成为这些人无理蛮横打压非美国企业的“尚方宝剑”。这种霸凌行径是对美方一贯标榜的市场经济和公平竞争原则的公然否定，违反国际贸易规则，肆意侵害他国利益，也必将损害美国自身利益。
        </p>
        <p>
            <strong>本文系观察者网独家稿件，未经授权，不得转载。</strong> 
        </p>
        </div>
$end""".trimIndent()
    )

    var videoUrl by mutableStateOf("https://1251245530.vod2.myqcloud.com/34386e2dvodtranssh1251245530/fe4d4574243791581392273409/video_10_0.m3u8")

}