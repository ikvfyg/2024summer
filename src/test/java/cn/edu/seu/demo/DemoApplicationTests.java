package cn.edu.seu.demo;

import cn.edu.seu.demo.utils.ChatClient;
import cn.edu.seu.demo.utils.CmdExecutor;
import cn.edu.seu.demo.utils.Model_eval;
import cn.edu.seu.demo.utils.MySystem;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    MySystem mySystem;

    @Autowired
    CmdExecutor cmdExecutor;
    /**
     * 测试模型推理
     */
//    @Test
//    public void test_model_eval() {
//        String text = new String("错漏百出的教科书。授课老师挑出《计算机应用基础》百多处错误、不妥之处反被处分引出教科书名利之争一本高职学生使用的计算机教材,仅前三章已被授课老师挑出了68处错误。他向学校反映后,反而遭到了处分。事情发生在广东外语艺术职业学院,事情匪夷所思的背后,其实隐藏着复杂的利益纠葛。而这本出现多处“硬伤”的教科书,至今仍在该校学生手中继续使用。学校方面表示,已经组织教学委员会及校外专家对教材展开鉴定,若发现重大知识性错误,将立即停用。错得太离谱:授课老师边上课边纠错广东外语艺术职业学院副教授叶克江的桌面上,摆着一本《计算机应用基础》教材,里面密密麻麻地布满红色笔圈出的修订。“我前后看了不下3次,错漏触目惊心”,他说,自己一边一字不漏地阅读,一边对比实际操作的软件,并对读其他出版社同类教材。仅前三章,他声称已挑出68个错误和87个不妥之处。他将错误分成知识性错误和语句表达的不妥之处。不妥之处像错字漏字方面,比如“计算精度越来越高”,中间的“越”字不见了,“英文字符”也缺“文”;知识描述方面同样明显,如“单击”变“双击”,“右侧”成“左侧”,“代码”成“代表”,常人一眼就看得出确实是硬伤。最关键的是,该教科书以windows7操作系统为主要工作环境,但其中有的内容仍以WindowsXP系统进行表述。结果讲到窗口的“工具栏”时,WindowsXP中的确存在,但Windows7系统已更新了,学生看书时一头雾水。目前,全校一年级新生共订购了2700多本该教材,而叶克江上课时,一边讲解一边纠错。不少学生觉得改起来麻烦,甚至连教科书都不带了。对于文中一些“语句不通之处”,叶克江甚至放话:“学校有能读懂这一句话的,请与我联系,本人砸锅卖铁提供10万元悬赏奖金。”挑错后被处分:“盗用书号+私印”PK胡编乱造这本教材,其实是叶克江同校教师、学校计算机公共教研室主任杨伟杰主编的。后者在旁听叶克江授课时,叶克江当着他的面严厉予以纠正,多名学生和听课老师面面相觑。叶克江纠错时不仅执着,还很高调。去年10月初,他向学校教务处反映该书的这些错误,同时联系上出版该书的高等教育出版社,但没等到调查结果。同月21日,他将一篇名为“‘国家级垃圾教材’是如何炼成的”的邮件,通过校内邮件系统发给了全校所有老师。洋洋洒洒3000字中,叶克江言辞激烈,称该书主编杨伟杰利用手中“权力”选用自编的教材,才致使“垃圾”教材“误人子弟”,并表示“对本文的一切描述负完全责任”。此举招致学校极快的反应。去年11月1日,学校给予叶克江通报批评处分,称他“群发邮件,混淆是非,作出不实指责,对学院和相关人员的声誉造成不良影响。”在叶克江的角度,他是为捍卫教科书质量而战;但有人认为,这是一场同校老师间的教科书之争。这缘起于学校从去年9月起,叶克江自己编写的计算机基础教材在被连续使用4年后,被同事的杨伟杰版取而代之。2013年5月,学校对计算机教材的选用工作中,同时收到了两人送审的样书。校方表示,之所以“弃叶选杨”,是因为叶克江送审的教材,盗用了别的书号,自己再私自印刷了几百本来送审,这是不正当的。“不是盗用,是借用”,对此,叶克江感到十分不忿。过去4年,学校使用他的书作教材时,已经默认他先“借”书号、后印制教材的方式。而学校解释给他处分时还重提此事,说要继续调查,他认为是避重就轻,“反而不直接鉴定新教材是否有质量问题,就是想扯皮。”校方:最终鉴定结果未出来出版社:错误率“难以理解”一本的确存在错漏的教科书,目前仍在学校内使用。教务处相关负责人说,按学校的教材选用管理办法,课程教材的选用由课程负责人组织任课教师集体研究讨论后提出,教研室进行初审,系(部)主管教学的领导复审确定后报教务处。教材一经选定不得随意更改。他说,之所以处分叶克江,是因为他采取不当方式闹得沸沸扬扬。“学校也是错误教材的受害者,而教材有错就要向出版社纠正。”学校宣传部江老师则表示,学校之所以至今尚未表态和处理,是因为最终鉴定结果尚未形成。如果鉴定结果是本书存在重大的知识性错误,经教学委员会确认后,学校将立即停止使用;若存在校对上的错漏,学校将追究出版社的责任。对此,出版《计算机应用基础》的高等教育出版社表示,教材的编写错误率一般控制在万分之一,如果真的是前三章就挑出了68个错误,实在是“难以理解”。出版社会向此书的责任编辑反映,挑错者可以将书中错误一一列出寄给他们,以便改正。教材名利场:出书容易、能抽版税、“职称”加分一些高职教师说,编写教科书是不少老师的“兵家必争之地”。据广州市轻工高级职业技术学校一位老师透露,教科书编写相对简单,却带给老师不少利益,基本能拿到15%左右的版税,对教师评职称也很有帮助。“编教科书的难度是业内公认最容易的,同类型教材很多,不涉及有难度的学术问题,左抄抄、右抄抄就可以出本书。”他说,有些错误不可避免,因为电脑更新得比书本快,所以一般是边上课边纠错。但基础性知识性错误又另当别论。广东机电职业技术学院的一名老师也认为,编写教科书可以说是“一家便宜两家着”,不仅出版社获取经济利益,老师也能为“职称”加分,他也曾收到不少出版社编写教科书的邀约。这导致一些老师“七抄八抄”拼凑出教材,教材质量良莠不齐。据了解,杨伟杰编写的这本《计算机应用基础》属于广东省教育厅推动的课题“高等学校大学计算机公共课程教学改革”项目。他在编写完教材后,还获得了学校颁发的教学优秀二等奖。不过他不愿意回答记者任何关于此本教材的问题。");
//        String result = Model_eval.model_eval(text);
//        System.out.println(result);
//    }


    /**
     * 测试模型推理
     */
    @Test
    public void test_model() {
        try {
            String prompt = "澳大利亚央行将利率降至纪录低点,以应对疲软的经济前景,并遏制澳元进一步走强。05/0513:37|评论(0)A+澳大利亚央行周二发布声明称,将关键利率由2.25%调降至2%,符合此前交易员及接受彭博调查的29位经济学家中25位的预期。据彭博社报道,上月澳央行官员曾警告,矿业之外的行业投资可能下滑。澳大利亚政府不太可能推出新的刺激措施,来扶助受本币升值和铁矿石价格下跌打击而低于潜在水平的经济增长。“鉴于大宗商品价格下跌,矿业投资还可能有低于当前预期的风险,”预计到降息的澳新银行高级经济学家FelicityEmmett在决议公布前编写的研究报告中称。他表示此次决议可能反映出“央行经济增长预估轨迹有所下调”。";
            String response = ChatClient.llama3(prompt,2);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试添加摘要
     */
    @Test
    public void test_add_summary() {
        try {
            mySystem.GenerateSummary(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试ollama cmd
     */
    @Test
    public void test_cmd_executor() {
        try {
            cmdExecutor.main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 测试调用爬虫
     */
    @Test
    public void test_run_spider() {
        try {
            mySystem.RunSpider();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 测试JWT令牌生成
     */
    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 10086);
        claims.put("name", "张三");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "NewsSummary".repeat(10))//签名算法
                .setClaims(claims)//自定义内容(载荷)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置有效期为1h
                .compact();
        System.out.println(jwt);
    }

}
