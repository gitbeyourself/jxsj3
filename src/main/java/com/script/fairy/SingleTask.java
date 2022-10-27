package com.script.fairy;
import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/25 0025.
 */

public class SingleTask extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    Util util;
    List taskList=null;
    long daze=0;
    public SingleTask(AtFairyImpl atFairy) throws Exception {
        mFairy = atFairy;
        util = new Util(mFairy);
    }

    public void setTaskName(int taskContentNum) throws Exception {
        super.setTaskName(taskContentNum);
        mFairy.condit();
    }

    //新手指引
    //新手指引 至星辰塔5层 或者龙山历练
    //取完名字和捏完脸后开启脚本
    public void novice() throws Exception {
        new SingleTask(mFairy){

            @Override
            public void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic(1101,8,1277,89,new String[]{"tiaoguo2.png","tiaoguo1.png"});
                mFairy.onTap(0.8f, result, "跳过", 1000);
            }
            @Override
            public void create() throws Exception {
                setTaskName(0);
            }
            @Override
            public void content_0() throws Exception {

                long dazeTime = mFairy.mMatTime(1189,151,76,23, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

                result=mFairy.findPic(5,132,329,435,new String[]{"thread.png","thread.png"});
                mFairy.onTap(0.8f,result,"主线",500);

                result=mFairy.findPic(1,54,1274,715,new String[]{"guide.png","guide1.png","guide2.png","guide4.png"});
                mFairy.onTap(0.9f,result,"指引",500);

                result=mFairy.findPic(749,197,1184,485,"dialogue.png");
                mFairy.onTap(0.8f,result,"弹窗对话1",500);

                result=mFairy.findPic(31,488,1072,634,"chuandai.png");
                mFairy.onTap(0.8f,result,"穿戴",500);

                result=mFairy.findPic(47,132,303,241,"target.png");
                if(result.sim>0.8f){
                    mFairy.onTap(0.8f,result,"关卡目标",500);
                    result = mFairy.findPic(1116,299,1149,315, new String[]{"zdzd.png", "zdzd1.png"});
                    LtLog.e("自动战斗相似度"+result.sim);
                    if (result.sim > 0.8f) {
                    } else {
                        mFairy.onTap(1129,283,1136,292, "自动战斗", 500);
                    }
                }


                result=mFairy.findPic("grab.png");
                mFairy.onTap(0.8f,result,"手",500);

                result=mFairy.findPic(608,463,806,575,"wancheng.png");
                mFairy.onTap(0.8f,result,874,545,882,554,"章节完成",500);

                result=mFairy.findPic(660,600,1171,693,"fhzc.png");
                mFairy.onTap(0.8f,result,"返回主城",500);

                result=mFairy.findPic(803,200,1067,317,"gsy.png");
                if(result.sim>0.8f && dazeTime > 40){
                    mFairy.onTap(0.8f,result,1110,144,1123,157,"任务结束",1000);
                    setTaskEnd();
                    return;
                }
            }

        }.taskContent(mFairy,"一键40级");
    }

    int overTime=0,picCount=0;
    String change="";

    //日常任务
    public void daily()throws Exception{
        new SingleTask(mFairy){
            @Override
            public void create() throws Exception {
                taskList=new ArrayList<String>();
                //清理背包
                if(AtFairyConfig.getOption("clear").equals("1")){
                    taskList.add("clear");
                }
                //日常主线
                if(AtFairyConfig.getOption("main").equals("1")){
                    taskList.add("main");
                }
                //星辰塔
                if(AtFairyConfig.getOption("xct").equals("1")){
                    taskList.add("xct");
                }
                //通天秘境
                if(AtFairyConfig.getOption("ttmj").equals("1")){
                    taskList.add("ttmj");
                }
                //龙山历练
                if(AtFairyConfig.getOption("lsll").equals("1")){
                    taskList.add("lsll");
                }
                //魔祖宝藏
                if(AtFairyConfig.getOption("mzbz").equals("1")){
                    taskList.add("mzbz");
                }

                //魔族宝藏类型
                if(!AtFairyConfig.getOption("treasure_type").equals("")){
                    if(AtFairyConfig.getOption("treasure_type").equals("1"))taskList.add("mzbz");
                    if(AtFairyConfig.getOption("treasure_type").equals("2"))taskList.add("daily_mzbz_update");
                }

                //科举乡试
                if(AtFairyConfig.getOption("kjxs").equals("1")){
                    taskList.add("kjxs");
                }
                //隐形密探
                if(AtFairyConfig.getOption("yxmt").equals("1")){
                    taskList.add("yxmt");
                }

                //赠送礼物
                if(!AtFairyConfig.getOption("sendObj").equals("") && !AtFairyConfig.getOption("sendObj").equals("0")){
                    taskList.add("zslw");
                }
                //本源争夺战
                if(AtFairyConfig.getOption("7592").equals("1")){
                    taskList.add("7592");
                }
                //雕像参拜
                if(AtFairyConfig.getOption("dxcb").equals("1")){
                    taskList.add("dxcb");
                }

                //家族签到
                if(AtFairyConfig.getOption("jzqd").equals("1")){
                    taskList.add("jzqd");
                }

                //家族捐献
                if(AtFairyConfig.getOption("jzjx").equals("1")){
                    taskList.add("jzjx");
                }

                util.back_city();
                setTaskName(0);
            }

            @Override
            public void content_0() throws Exception {
                if(taskList.size()==0){
                    LtLog.e(mFairy.getLineInfo("所有日常完成，没有任务了"));
                    setTaskEnd();
                    return;
                }
                overTime=0;
                daze=0;
                //清理背包
                if(taskList.get(0).equals("clear")){
                    daily_clear();
                    taskList.remove(0);
                    return;
                }

                //星辰塔
                //ttmj
                if(taskList.get(0).equals("ttmj")){
                    ttmj();
                    taskList.remove(0);
                    return;
                }
                //龙山历练
                if(taskList.get(0).equals("lsll")){
                    daily_lsll();
                    taskList.remove(0);
                    return;
                }
                //魔族宝藏
                if(taskList.get(0).equals("mzbz")){
                    daily_mzbz();
                    taskList.remove(0);
                    return;
                }
                //魔族宝藏
                if(taskList.get(0).equals("daily_mzbz_update")){
                    daily_mzbz_update();
                    taskList.remove(0);
                    return;
                }
                //科举乡试
                if(taskList.get(0).equals("kjxs")){
                    daily_kjxs();
                    taskList.remove(0);
                    return;
                }
                //隐形密探
                if(taskList.get(0).equals("yxmt")){
                    szmt();
                    taskList.remove(0);
                    return;
                }
                //赠送礼物
                if(taskList.get(0).equals("zslw")){
                    daily_zslw1();
                    taskList.remove(0);
                    return;
                }
                //本源争夺战
                if(taskList.get(0).equals("7592")){
                    szzyzd();
                    taskList.remove(0);
                    return;
                }

                //雕像参拜
                if(taskList.get(0).equals("dxcb")){
                    daily_dxcb();
                    taskList.remove(0);
                    return;
                }
                //家族签到
                if(taskList.get(0).equals("jzqd")){
                    daily_jzqd();
                    taskList.remove(0);
                    return;
                }
                //家族捐献
                if(taskList.get(0).equals("jzjx")){
                    daily_jzjx();
                    taskList.remove(0);
                    return;
                }
            }

        }.taskContent(mFairy,"日常任务");
    }

    public  void  fuli()throws Exception{
        new SingleTask(mFairy){
            @Override
            public void content_0() throws Exception {
                util.close();
                setTaskName(1);return;
            }
            public void content_1() throws Exception {
                if (overtime(10,0))return;
                result = mFairy.findPic(1170,158,1257,250, "menu.png");
                mFairy.onTap(0.8f, result,  "菜单", Sleep);

                result = mFairy.findPic(1061,5,1119,45, "zhankai.png");
                mFairy.onTap(0.8f, result,  "展开", Sleep);

                result = mFairy.findPic(711,2,1111,86, "fuli.png");
                mFairy.onTap(0.8f, result, "福利", Sleep);

                result = mFairy.findPic(204,526,1042,693,new String[]{"fuli1.png","fuli2.png"});
                if (result.sim>0.7f){
                    setTaskName(2);return;
                }
            }
            public void content_2() throws Exception {
                if (overtime(20,3)){
                    util.close();
                    setTaskName(3);return;
                }
                result = mFairy.findPic(1170,158,1257,250, "menu.png");
                mFairy.onTap(0.8f, result,  "菜单", Sleep);

                result = mFairy.findPic(1061,5,1119,45, "zhankai.png");
                mFairy.onTap(0.8f, result,  "展开", Sleep);

                result = mFairy.findPic(711,2,1111,86, "fuli.png");
                mFairy.onTap(0.8f, result, "福利", Sleep);

                result = mFairy.findPic(108,72,1172,360, new  String[]{"reward.png"});
                mFairy.onTap(0.8f, result, "在线领奖", 2000);
                if (result.sim > 0.8f){
                    for (int i=0; i<=20; i++){
                        result = mFairy.findPic( 492,96,1130,647,new  String[]{"Available.png","Available1.png"});
                        mFairy.onTap(0.8f, result, "可领取", Sleep);
                    }
                    result = mFairy.findPic(1118,64,1199,142, new  String[]{"cha1.png"});
                    mFairy.onTap(0.8f, result, "关叉", Sleep);
                    if (result.sim > 0.8f){
                        setTaskName(3);return;
                    }
                }
            }
            public void content_3() throws Exception {
                if (overtime(20,4)){
                    util.close();
                    setTaskName(4);return;
                }

                result = mFairy.findPic(1170,158,1257,250, "menu.png");
                mFairy.onTap(0.8f, result,  "菜单", Sleep);

                result = mFairy.findPic(1061,5,1119,45, "zhankai.png");
                mFairy.onTap(0.8f, result,  "展开", Sleep);

                result = mFairy.findPic(711,2,1111,86, "fuli.png");
                mFairy.onTap(0.8f, result, "福利", Sleep);

                result = mFairy.findPic(108,72,1172,360, "qiandao.png");
                mFairy.onTap(0.8f, result, "每日签到", 5000);

                result = mFairy.findPic(1118,64,1199,142, new  String[]{"cha1.png"});
                mFairy.onTap(0.8f, result, "关叉", Sleep);
                if (result.sim > 0.8f){
                    setTaskName(4);return;
                }

            }
            public void content_4() throws Exception {
                if (overtime(20,5)){
                    util.close();
                    setTaskName(5);return;
                }

                result = mFairy.findPic(1170,158,1257,250, "menu.png");
                mFairy.onTap(0.8f, result,  "菜单", Sleep);

                result = mFairy.findPic(1061,5,1119,45, "zhankai.png");
                mFairy.onTap(0.8f, result,  "展开", Sleep);

                result = mFairy.findPic(711,2,1111,86, "fuli.png");
                mFairy.onTap(0.8f, result, "福利", Sleep);

                result = mFairy.findPic(108,72,1172,360, new  String[]{"lixian.png"});
                mFairy.onTap(0.8f, result, "离线经验", 2000);
                if (result.sim > 0.8f){
                    for (int i=0; i<=10; i++){
                        result1 = mFairy.findPic( 601,454,1131,572,new  String[]{"putong.png"});
                        mFairy.onTap(0.8f, result1, "可领取", Sleep);

                        result = mFairy.findPic( 507,328,846,532,new  String[]{"mylxsc.png"});
                        mFairy.onTap(0.8f, result, "可领取", Sleep);
                        if (result.sim > 0.8f){
                            result = mFairy.findPic(1059,5,1275,199, new  String[]{"cha1.png"});
                            mFairy.onTap(0.8f, result, "关叉", Sleep);
                            setTaskName(5);return;
                        }
                    }
                    result = mFairy.findPic(1059,5,1275,199, new  String[]{"cha1.png"});
                    mFairy.onTap(0.8f, result, "关叉", Sleep);

                    if (result1.sim > 0.8f){
                        setTaskName(5);return;
                    }
                }

            }
            public void content_5() throws Exception {
                if (overtime(20,6)){
                    util.close();
                    setTaskName(6);return;
                }

                result = mFairy.findPic(1170,158,1257,250, "menu.png");
                mFairy.onTap(0.8f, result,  "菜单", Sleep);

                result = mFairy.findPic(1061,5,1119,45, "zhankai.png");
                mFairy.onTap(0.8f, result,  "展开", Sleep);

                result = mFairy.findPic(711,2,1111,86, "fuli.png");
                mFairy.onTap(0.8f, result, "福利", Sleep);

                result = mFairy.findPic(108,72,1172,360, new  String[]{"zhaohui.png"});
                mFairy.onTap(0.8f, result, "奖励找回", 2000);
                mFairy.onTap(0.8f, result, 969,157,979,167,"普通找回", 2000);
                if (result.sim > 0.8f){
                    for (int i=0; i<=10; i++){
                        result = mFairy.findPic( 967,191,1158,603,"qbzh.png");
                        mFairy.onTap(0.8f, result, "全部找回", Sleep);
                        mFairy.onTap(0.8f, result, 996,154,1014,165,"全部找回", Sleep);
                    }
                    result = mFairy.findPic(607,312,1022,434, new  String[]{"myjl.png"});
                    if (result.sim > 0.8f){
                        result = mFairy.findPic(1118,64,1199,142, new  String[]{"cha1.png"});
                        mFairy.onTap(0.8f, result, "关叉", Sleep);
                        setTaskName(6);return;
                    }
                }

            }
            public void content_6() throws Exception {
                if (overtime(20,7)){
                    util.close();
                    result = mFairy.findPic(13,10,101,95, "signout.png");
                    mFairy.onTap(0.8f, result,  "返回", Sleep);
                    setTaskName(7);return;
                }
                result = mFairy.findPic(1170,158,1257,250, "menu.png");
                mFairy.onTap(0.8f, result,  "菜单", Sleep);

                result = mFairy.findPic(1061,5,1119,45, "zhankai.png");
                mFairy.onTap(0.8f, result,  "展开", Sleep);

                result = mFairy.findPic(711,2,1111,86, "fuli.png");
                mFairy.onTap(0.8f, result, "福利", Sleep);

                result = mFairy.findPic(108,72,1172,360,"qiri.png");
                mFairy.onTap(0.8f, result, "创角七日", 2000);
                if (result.sim > 0.8f){
                    for (int i=0; i<=10; i++){
                        result = mFairy.findPic( 970,237,1155,627,"Available2.png");
                        mFairy.onTap(0.8f, result, "领取", Sleep);
                    }
                    result = mFairy.findPic(1118,64,1199,142, new  String[]{"cha1.png"});
                    mFairy.onTap(0.8f, result, "关叉", Sleep);
                    if (result.sim > 0.8f){
                        util.close();
                        result = mFairy.findPic(13,10,101,95, "signout.png");
                        mFairy.onTap(0.8f, result,  "返回", Sleep);
                        setTaskName(7);return;
                    }
                }
            }
            public void content_7() throws Exception {
                if (overtime(20,0)){
                    util.close();
                    result = mFairy.findPic(13,10,101,95, "signout.png");
                    mFairy.onTap(0.8f, result,  "返回", Sleep);
                    setTaskEnd();
                    return;

                }

                result = mFairy.findPic(1101,155,1260,250, "menu1.png");
                mFairy.onTap(0.8f, result,  "菜单展开", Sleep);

                result = mFairy.findPic(1002,244,1275,626, "chengjiu.png");
                mFairy.onTap(0.8f, result,  "成就", Sleep);

                result = mFairy.findPic(84,10,295,68, new  String[]{"chengjiu1.png"});
                if (result.sim > 0.8f){
                    for (int i=0; i<=15; i++){
                        result = mFairy.findPic( 1042,208,1238,656,new  String[]{"lingqu.png"});
                        mFairy.onTap(0.8f, result, "可领取", Sleep);
                        if (result.sim > 0.8f){
                            i=10;
                        }else{
                            if (i>=5 && i<=12){
                                result = mFairy.findPic( 301,209,371,597,"red.png");
                                mFairy.onTap(0.8f, result,result.x-37,result.y+35,result.x-49,result.y+50, "红点", Sleep);
                            }
                        }
                    }
                    util.close();
                    setTaskEnd();
                    return;
                }

            }
        }.taskContent(mFairy,"福利任务中");
    }//福利
    public void mrdt() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = util.mission("mrdt.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }
            int x=0;
            public void content_2() throws Exception {

                result = mFairy.findPic(957,434,1234,606,new String[]{"ksdt.png"});
                mFairy.onTap(0.8f, result, "开始答题", Sleep);

                result = mFairy.findPic(876, 5, 1120, 146, "daily.png");
                if (result.sim > 0.8f) {
                    LtLog.e("在桌面  重进");
                    setTaskName(1);
                }

                result = mFairy.findPic(503,351,1193,574,new String[]{"a.png"});
                mFairy.onTap(0.8f, result, "默认A！", Sleep);

                result = mFairy.findPic(68,94,138,322, "mrdt1.png");
                if (result.sim > 0.8f) {
                    x=0;

                    result = mFairy.findPic("kjSignOut.png");
                    mFairy.onTap(0.8f, result, "答题退出", Sleep);
                    if (result.sim > 0.8f) {
                        setTaskEnd();
                    }
                }else{
                    x++;
                }
                if (x>=20){
                    util.close();
                    setTaskName(1);
                }
            }
        }.taskContent(mFairy, "每日答题任务中");
    }//每日答题
    public void xltb() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                util.close();
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                int ret = util.mission("xltb.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            public void content_2() throws Exception {
                overtime(10, 0);
                long dazeTime = mFairy.mMatTime(1215, 128, 61, 16, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 40) {
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic(846,51,1274,645, "kshq.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 844,80,850,93,"退出", Sleep);
                    mFairy.onTap(0.8f, result, 1237,68,1245,78,"退出", Sleep);
                    LtLog.e(mFairy.getLineInfo("没有藏宝图了"));
                    util.close();
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(1025,524,1238,644, "qwtb.png");
                mFairy.onTap(0.8f, result, "前往探宝", Sleep);

                result = mFairy.findPic(373,44,1273,709, "shiyong1.png");
                mFairy.onTap(0.8f, result, "立即使用", Sleep);

                if (result.sim < 0.8f) {
                    result = mFairy.findPic(362, 99, 895, 527, "plsy.png");
                    mFairy.onTap(0.8f, result, 755, 462, 768, 472, "批量使用", Sleep);
                    err = 0;
                }
                result = mFairy.findPic(218,96,700,347,"tbk.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("任务进行中！"));
                    mFairy.initMatTime();
                }

                result = mFairy.findPic(492,382,782,546,new String[]{"wbz.png","xunlu.png"});
                if (result.sim > 0.8f) {
                    err = 0;
                    LtLog.e(mFairy.getLineInfo("任务进行中！"));
                    mFairy.initMatTime();
                }
            }
        }.taskContent(mFairy, "寻龙探宝任务中");
    }//寻龙探宝
    //商会任务
    public void shrw() throws  Exception{
        new SingleTask(mFairy) {

            @Override
            public void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic(1101,8,1277,89,new String[]{"tiaoguo2.png","tiaoguo1.png"});
                mFairy.onTap(0.8f, result, "跳过", 1000);
            }

            /**
             * 调用工具类初始化接任务
             * @throws Exception
             */
            public void content_0() throws Exception {
                util.close();
                setTaskName(1);
            }

            /**
             * 如果任务已经接取，不需要继续执行工具类中找任务方法，直接开始setTaskName(3)
             * @throws Exception
             */
            public void content_1() throws Exception {
                int ret = util.mission("shrw.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }

            /**
             * 寻找任务并且前往
             * @throws Exception
             */
            public void content_2() throws Exception {
                overtime(30, 0);
                long dazeTime = mFairy.mMatTime(1162,119,60,18, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 30) {
                    setTaskName(1);
                    return;
                }
                result=mFairy.findPic(944,429,1265,577,"jieshou.png");
                mFairy.onTap(0.8f,result,"接受任务",1000);

                result=mFairy.findPic(1051,194,1214,544,"caiji.png");
                mFairy.onTap(0.8f,result,"采集",1000);

                result=mFairy.findPic(380,166,907,506,"shrw1.png");
                mFairy.onTap(0.8f,result,740,448,757,461,"采集",1000);

                result=mFairy.findPic(844,216,1040,356,"shou.png");
                mFairy.onTap(0.8f,result,"采集点击",1000);

                result=mFairy.findPic(543,400,768,520,"wcrw.png");
                if (result.sim > 0.8f){
                    mFairy.onTap(0.8f,result,"完成任务",1000);

                    result=mFairy.findPic(652,411,860,498,"wcrw1.png");
                    mFairy.onTap(0.8f,result,"完成任务",1000);

                    LtLog.e("商会任务完成");
                    setTaskEnd();
                    return;
                }
            }

        }.taskContent(mFairy,"商会任务");
    }

    //龙山历练
    public void daily_lsll() throws  Exception{
        new SingleTask(mFairy) {
            int clearNum=1;//计次清理了几次背包(2次以上结束)
            /**
             * 调用工具类初始化接任务
             * @throws Exception
             */
            public void content_0() throws Exception {
                util.close();
                setTaskName(1);
            }

            /**
             * 如果任务已经接取，不需要继续执行工具类中找任务方法，直接开始setTaskName(3)
             * @throws Exception
             */
            public void content_1() throws Exception {
                if (overtime(8, 2)) return;
                result = mFairy.findPic("pic open task.png");
                mFairy.onTap(0.8f,result,"显示任务",Sleep);

                result = mFairy.findPic("task lan.png");
                mFairy.onTap(0.75f,result,"显示任务l栏",Sleep);

                result=mFairy.findPic(49,184,251,386,new String[]{"word task lsll.png","word task lsll1.png"});
                mFairy.onTap(0.7f,result,"龙山历练指引",Sleep);
                if (result.sim>0.7f){
                    util.initDaze();
                    setTaskName(3);return;
                }
                mFairy.taskSlid(err, new int[]{1, 2, 4, 6}, 6, 125,384,136,200, 1000, 1500);
            }

            /**
             * 寻找任务并且前往
             * @throws Exception
             */
            public void content_2() throws Exception {
                //取消自动作战，不然会打断传送
                result=mFairy.findPic("auto ing.png");
                mFairy.onTap(0.8f,result,"停止自动作战",500);
                //寻找任务并且前往
                int ret =util.mission("single longshanlilian.png",0);
                if (ret==1){
                    util.initDaze();
                    setTaskName(3);return;
                }else {
                    setTaskEnd();return;
                }
            }

            /**
             * 任务执行过程
             * @throws Exception
             */
            public void content_3() throws Exception {
                //已经接取任务
                result=mFairy.findPic("shangxian.png");
                if(result.sim>0.8){
                    LtLog.e(mFairy.getLineInfo("接取任务超过上限"));
                    util.close();
                }

                result=mFairy.findPic("word dailyInterface.png");
                mFairy.onTap(0.8f,result,1219,68,1236,87,"关闭日常界面",1000);

                result=mFairy.findPic("word back already full.png");
                if(result.sim>0.8){
                    LtLog.e(mFairy.getLineInfo("背包已满，清理后完成"));
                    util.clear1();
                    if(clearNum++>=2){
                        LtLog.e(mFairy.getLineInfo("背包已满，无法再清理，结束任务"));
                        setTaskEnd();
                        return;
                    }
                    setTaskName(0);
                    return;
                }

                if(daze>=10){
                    setTaskName(0);
                    LtLog.e(mFairy.getLineInfo("龙山历练超时，重置任务"));
                    return;
                }

                result=mFairy.findPic(new String[]{"pic conjure.png"});
                mFairy.onTap(0.8f,result,"施法",6000);

                result=mFairy.findPic("mubiao.png");
                if(result.sim>0.8f){
                    result1=mFairy.findPic(new String[]{"word dong.png","word zhong.png"});
                    if(result1.sim>0.8f){
                        LtLog.e(mFairy.getLineInfo("龙山历练寻路中.."));
                        mFairy.sleep(1000);
                    }
                    result1=mFairy.findPic("auto ing.png");
                    if(result1.sim>0.8f){
                        LtLog.e(mFairy.getLineInfo("龙山自动战斗.."));
                        mFairy.sleep(1000);
                    }
                    if(result1.sim<0.8f){
                        mFairy.onTap(0.8f,result,result.x+108,result.y-15,result.x+109,result.y-14,"目标指引1",200);
                        mFairy.onTap(0.8f,result,result.x+108,result.y-5,result.x+109,result.y-4,"目标指引2",2000);
                    }
                }

                result=mFairy.findPic(37,487,1267,565,"start1.png");
                mFairy.onTap(0.8f,result,"开启任务",1000);

                result=mFairy.findPic(611,395,642,468,"affirm.png");
                mFairy.onTap(0.8f,result,"点击确认",500);

                result=mFairy.findPic("use.png");
                mFairy.onTap(0.8f,result,"自动使用",500);

                result=mFairy.findPic("finish.png");
                mFairy.onTap(0.8f,result,"完成任务",500);

                result=mFairy.findPic("accept.png");
                mFairy.onTap(0.8f,result,"接受任务",500);

                result=mFairy.findPic(928,165,1002,510,"dialogue.png");
                result1=mFairy.findPic(1225,665,1269,713,"chat continue.png");
                if(result.sim>0.8f||result1.sim>0.8){
                    util.initDaze();
                    mFairy.onTap(0.75f,result,"回复NPC",500);
                }

                //打造武器
                result=mFairy.findPic("zao.png");
                if(result.sim>0.8f){
                    result=mFairy.findPic("word warm.png");
                    mFairy.onTap(0.8f,result,"加热",1000);

                    result=mFairy.findPic("word make.png");
                    mFairy.onTap(0.8f,result,"打造",200);
                    mFairy.onTap(0.8f,result,"打造",200);
                    mFairy.onTap(0.8f,result,"打造",200);
                }

                result=mFairy.findPic("mi.png");
                if(result.sim>0.8f){
                    draw=0;y=0;
                    setTaskName(5);
                    return;
                }

                result=mFairy.findPic("word up.png");
                mFairy.onTap(0.8f,result,1240,675,1245,680,"亲密度提升",500);

                result=mFairy.findPic("word close.png");
                mFairy.onTap(0.8f,result,"关闭",500);

                result=mFairy.findPic(new String[]{"lsll.png"});
                if(result.sim>0.8){
                    mFairy.onTap(0.8f,result,603,398,672,423,"龙山历练大环赠礼",100);
                    LtLog.e("龙山历练完成");
                    setTaskName(0);
                    setTaskEnd();
                    return;
                }
            }

            int draw=0,index=0;//翻牌的次数
            int y=0;
            public void content_4()throws Exception{
                //连连看界面 写完后我自己都看不懂了
                result=mFairy.findPic("mi.png");
                if(result.sim>0.8f){
                    //单数 x固定不变
                    //复数 y>x 后一张牌大于前一张牌
                    //index指第几张牌
                    index=0;
                    for(int i=1;i<=4;i++){
                        for(int n=1;n<=4;n++){
                            index++;
                            result=mFairy.findPic(281+(n-1)*163,92+(i-1)*158,348+(n-1)*163,158+(i-1)*158,"pic LinkGame.png");
                            if(result.sim>0.7f){
                                //单数
                                if(draw%2==0){
                                    if(index==8){
                                        mFairy.onTap(0.7f,result,822,257,833,269,"翻牌 单",3000);
                                    }else{
                                        mFairy.onTap(0.7f,result,"翻牌 单",3000);
                                    }
                                    draw++;
                                }else{
                                    //复数
                                    if(index>y){
                                        if(index==8){
                                            mFairy.onTap(0.7f,result,822,257,833,269,"翻牌 复",3000);
                                        }else{
                                            mFairy.onTap(0.7f,result,"翻牌 复",3000);
                                        }
                                        draw++;
                                        y=index;
                                    }else{
                                        continue;
                                    }
                                    //大于16 从第一张牌开始
                                    if(y>=16){
                                        y=0;
                                    }
                                }
                                return;
                            }
                        }
                    }
                }else{
                    setTaskName(3);
                    LtLog.e(mFairy.getLineInfo("龙山解密完成"));
                    return;
                }
            }

            List<FindResult> picList=null;
            public void content_5()throws Exception{
                result=mFairy.findPic("mi.png");
                if(result.sim>0.8f){
                    try{
                        //单数 x固定不变
                        //复数 y>x 后一张牌大于前一张牌
                        //index指第几张牌
                        index=0;
                        picList=new ArrayList<FindResult>();
                        for(int i=1;i<=4;i++){
                            for(int n=1;n<=4;n++){
                                index++;
                                result=mFairy.findPic(281+(n-1)*163,92+(i-1)*158,348+(n-1)*163,158+(i-1)*158,"pic LinkGame.png");
                                if(result.sim>0.7f){
                                    picList.add(result);
                                }
                            }
                        }
                        int size=picList.size();
                        if(size%2==0){
                            //单牌
                            result=picList.get(0);
                            mFairy.onTap(0.7f,result,result.x+40,result.y+40,result.x+41,result.y+41,"单牌",2000);

                        }else{
                            //双牌
                            if(y>=size){
                                y=0;
                            }
                            result=picList.get(y);
                            mFairy.onTap(0.7f,result,result.x+40,result.y+40,result.x+41,result.y+41,"双牌",2000);
                            y++;
                        }
                    }catch(Exception e){
                        LtLog.e(mFairy.getLineInfo("捕获到异常"));
                        LtLog.e(mFairy.getLineInfo(e.getMessage()));
                    }

                }else{
                    setTaskName(3);
                    LtLog.e(mFairy.getLineInfo("龙山解密完成"));
                    return;
                }
            }
            @Override
            public void inOperation() throws Exception {
                result=mFairy.findPic(611,395,642,468,"affirm.png");
                mFairy.onTap(0.8f,result,"点击确认",500);

                result=mFairy.findPic(611,379,676,457,"word close.png");
                mFairy.onTap(0.8f,result,"点击关闭",500);
                /*try{
                    result=mFairy.findMultiColor(57,143,85,175,0.9f,"101,216,221",
                            "0|5|100,212,217&0|11|102,214,218&0|14|104,211,215&4|0|98,220,227&8|0|93,231,239&12|0|104,204,207&4|7|103,209,213&4|14|107,202,204&13|14|107,201,204");
                    mFairy.onTap(0.85f,result,"切换到任务页",1000);
                }catch (Exception e){
                    LtLog.e(mFairy.getLineInfo("获取出错"));
                    LtLog.e(mFairy.getLineInfo(e.getMessage()));
                }*/
                super.inOperation();
            }
        }.taskContent(mFairy,"龙山历练");
    }

    //魔祖宝藏
    public void daily_mzbz() throws  Exception{
        new SingleTask(mFairy) {
            /**
             * 调用工具类初始化接任务
             * @throws Exception
             */
            public void content_0() throws Exception {
                util.close();
                result=mFairy.findPic("leave.png");
                if(result.sim>0.8f){
                    LtLog.e(mFairy.getLineInfo("在副本中，"));
                    setTaskName(3);
                    daze=util.initDaze();
                    return;
                }
                setTaskName(2);
            }

            /**
             * 如果任务已经接取，不需要继续执行工具类中找任务方法，直接开始setTaskName(3)
             * @throws Exception
             */
            public void content_1() throws Exception {
                if (overtime(8, 2)) return;
                result = mFairy.findPic("pic open task.png");
                mFairy.onTap(0.8f,result,"显示任务",Sleep);

                result = mFairy.findPic("task lan.png");
                mFairy.onTap(0.75f,result,"显示任务l栏",Sleep);

//                result=mFairy.findPic(5,201,217,366,"word huodong deal worm action.png");
//                mFairy.onTap(0.7f,result,"除虫行动活动指引",Sleep);
//                if (result.sim>0.7f){
//                    setTaskName(3);return;
//                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 3, 160,214, 160,346, 1000, 1500);
            }

            /**
             * 寻找任务并且前往
             * @throws Exception
             */
            public void content_2() throws Exception {
                //取消自动作战，不然会打断传送
                result=mFairy.findPic("auto ing.png");
                mFairy.onTap(0.8f,result,"停止自动作战",500);
                //寻找任务并且前往
                int ret =util.mission("single mozubaozang.png",0);
                if (ret==1){
                    util.initDaze();
                    setTaskName(3);return;
                }else {
                    setTaskEnd();return;
                }
            }

            /**
             * 任务执行过程
             * @throws Exception
             */
            public void content_3() throws Exception {
                result=mFairy.findPic("get goods.png");
                mFairy.onTap(0.8f,result,629,404,638,414,"获得物品",500);

                result=mFairy.findPic(615,112,655,146,"bird tip.png");
                if(result.sim>0.8f){
                    mFairy.onTap(0.8f,result,1081,9,1096,26,"指引打开随身背包，重置任务",500);
                    setTaskName(0);
                    return;
                }

                //背包界面
                result=mFairy.findPic(new String[]{"pack1.png","pack2.png"});
                if(result.sim>0.8f){
                    LtLog.e("****背包里面****");
                    result=mFairy.findPic(639,118,1168,417,new String[]{"cangbaotu.png","cbtu.png"});
                    mFairy.onTap(0.8f,result,"背包_选中藏宝图",1000);
//                    picCount=picCount(0.8f,result,"Treasure");
//                    if(picCount==1){
//
//                    }

                    result=mFairy.findPic(660,161,1157,354,"use.png");
                    mFairy.onTap(0.8f,result,"背包_使用藏宝图",1000);
                }

                //副本界面
                result=mFairy.findPic("fuben.png");
                if(result.sim>0.8){
                    if(daze>6){
                        mFairy.onTap(132,272,146,287,"点击副本指引",500);
                    }
                }

                result=mFairy.findPic(698,534,769,582,"leave1.png");
                mFairy.onTap(0.8f,result,"副本挑战成功,离开",500);

                result=mFairy.findPic("word to strong.png");
                if(result.sim>0.8){
                    LtLog.e("没有藏宝图,任务结束");
                    setTaskEnd();
                    return;
                }

                result=mFairy.findPic(910,458,1046,498,"use1.png");
                mFairy.onTap(0.8f,result,"立即使用",1000);

                //超时处理
                if(daze>15){
                    LtLog.e(mFairy.getLineInfo("魔族宝藏超时，重置任务前往"));
                    setTaskName(0);
                    return;
                }
            }

            @Override
            public void inOperation() throws Exception {
                super.inOperation();
            }
        }.taskContent(mFairy,"魔祖宝藏");
    }

    //魔祖宝藏  升级版
    public void daily_mzbz_update() throws  Exception{
        new SingleTask(mFairy) {
            int ran=0;//在背包界面滑动的次数
            /**
             * 调用工具类初始化接任务
             * @throws Exception
             */
            public void content_0() throws Exception {
                util.close();
                ran=0;
                result=mFairy.findPic("leave.png");
                if(result.sim>0.8f){
                    LtLog.e(mFairy.getLineInfo("在副本中，"));
                    setTaskName(2);
                    daze=util.initDaze();
                    return;
                }
                setTaskName(1);
            }

            public void content_1()throws Exception{
                daze=util.initDaze();
                overtime(20,0);
                result = mFairy.findPic("pack.png");
                mFairy.onTap(0.8f, result, "打开背包", 2000);

                //新手引导
                result=mFairy.findPic(615,112,655,146,"bird tip.png");
                if(result.sim>0.8f){
                    mFairy.onTap(0.8f,result,1081,9,1096,26,"指引打开随身背包，重置任务",500);
                    setTaskName(0);
                    return;
                }

                //背包界面
                result=mFairy.findPic("pack1.png");
                if (result.sim> 0.8){
                    result=mFairy.findPic(362,205,431,281,"pic package treasure map select.png");
                    mFairy.onTap(0.8f,result,846,219,858,231,"已选中藏宝图，点击使用",1000);
                    if(result.sim>0.8f){
                        setTaskName(2);
                        return;
                    }
                    //没有选中藏宝图
                    else{
                        result=mFairy.findPic(658,142,1152,589,"cangbaotu.png");
                        mFairy.onTap(0.8f,result,"背包_选中藏宝图",1000);
                        if (result.sim < 0.9f) {
                            //向上滑动
                            mFairy.ranSwipe(912, 543, 912, 150, 500, 1500l,0);
                            result=mFairy.findPic(670,142,741,236,"pic packet lock.png");
                            if(result.sim>0.8f){
                                mFairy.onTap(1218,70,1234,84,"清理背包，滑到底部，清理完成，关闭背包界面",1000);
                                setTaskEnd();
                                return;
                            }
                            if(ran++>=5){
                                mFairy.onTap(1218,70,1234,84,"没有藏宝图，关闭背包界面",1000);
                                setTaskEnd();
                                return;
                            }
                            LtLog.e(mFairy.getLineInfo("向上滑动一次寻找="+ran));
                        }
                    }
                }
            }

            public void content_2() throws Exception {
                result=mFairy.findPic("get goods.png");
                mFairy.onTap(0.8f,result,629,404,638,414,"获得物品",500);

                result=mFairy.findPic("pack1.png");
                mFairy.onTap(0.8f,result,1221,71,1232,83,"关闭背包",1000);

                //副本界面
                result=mFairy.findPic("fuben.png");
                if(result.sim>0.8){
                    if(daze>6){
                        mFairy.onTap(132,272,146,287,"点击副本指引",500);
                    }
                }

                result=mFairy.findPic(698,534,769,582,"leave1.png");
                mFairy.onTap(0.8f,result,"副本挑战成功,离开",500);

                result=mFairy.findPic("word to strong.png");
                if(result.sim>0.8){
                    LtLog.e("没有藏宝图,任务结束");
                    setTaskEnd();
                    return;
                }

                //超时处理
                if(daze>20){
                    LtLog.e(mFairy.getLineInfo("魔族宝藏超时，重置任务前往"));
                    setTaskName(0);
                    return;
                }
            }

            @Override
            public void inOperation() throws Exception {
                result=mFairy.findPic("pack full.png");
                if(result.sim>0.8) {
                    LtLog.e(mFairy.getLineInfo("背包已满,选择清理"));
                    mFairy.onTap(0.8f, result, 519, 404, 571, 429, "选中不分解", 1000);
                    util.clear1();
                    ran=0;
                    setTaskName(1);
                    return;
                }

                result=mFairy.findPic(942,384,1012,442,"cangbaotu.png");
                mFairy.onTap(0.8f,result,966,476,974,485,"藏宝图，立即使用",1000);
                if(result.sim>0.8f){
                    setTaskName(2);
                    daze=util.initDaze();
                }
                super.inOperation();
            }
        }.taskContent(mFairy,"魔祖宝藏 升级版 ");
    }

    //科举乡试
    public void daily_kjxs() throws  Exception{
        new SingleTask(mFairy) {
            /**
             * 调用工具类初始化接任务
             * @throws Exception
             */
            public void content_0() throws Exception {
                util.close();
                setTaskName(2);
            }

            /**
             * 如果任务已经接取，不需要继续执行工具类中找任务方法，直接开始setTaskName(3)
             * @throws Exception
             */
            public void content_1() throws Exception {
                if (overtime(8, 2)) return;
                result = mFairy.findPic("pic open task.png");
                mFairy.onTap(0.8f,result,"显示任务",Sleep);

                result = mFairy.findPic("task lan.png");
                mFairy.onTap(0.75f,result,"显示任务l栏",Sleep);
//                result=mFairy.findPic(5,201,217,366,"word huodong deal worm action.png");
//                mFairy.onTap(0.7f,result,"除虫行动活动指引",Sleep);
//                if (result.sim>0.7f){
//                    setTaskName(3);return;
//                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 3, 160,214, 160,346, 1000, 1500);
            }

            /**
             * 寻找任务并且前往
             * @throws Exception
             */
            public void content_2() throws Exception {
                //取消自动作战，不然会打断传送
                result=mFairy.findPic("auto ing.png");
                mFairy.onTap(0.8f,result,"停止自动作战",500);
                //寻找任务并且前往
                int ret =util.mission("single kejuxiangshi.png",0);
                if (ret==1){
                    util.initDaze();
                    setTaskName(3);return;
                }else {
                    setTaskEnd();return;
                }
            }

            /**
             * 任务执行过程
             * @throws Exception
             */
            public void content_3() throws Exception {
                result=mFairy.findPic("safety area.png");
                if(result.sim>0.8){
                    mFairy.onTap(0.8f, result, "前往安全区", 1000);
                    setTaskName(0);
                    return;
                }

                if(daze>10){
                    mFairy.getLineInfo("科举乡试发呆超时，重置任务");
                    setTaskName(1);
                }

                //开始答题
                result=mFairy.findPic(902,532,966,581,"start.png");
                mFairy.onTap(0.8f, result, "开始答题", 100);

                result=mFairy.findPic("keju.png");
                if(result.sim>0.8f){
                    mFairy.onTap(0.8f,result,531,315,552,335, "答题中 所有选择C", 250);
                    util.initDaze();
                }

                result=mFairy.findPic("exit.png");
                mFairy.onTap(0.8f, result, "答题完成", 1000);

                result=mFairy.findPic("xiangshiwancheng.png");
                if(result.sim>0.8){
                    mFairy.onTap(0.8f, result,615,394,659,421,"完成奖励", 1000);
                    LtLog.e("科举乡试完成");
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy,"科举乡试");
    }

    //赠送礼物
    public void daily_zslw1() throws  Exception{
        new SingleTask(mFairy) {
            String sendObj="";//选择送礼物给谁
            int find=0;//查找了计次(3次以上没找到结束)
            public void create()throws Exception{
                if(!AtFairyConfig.getOption("sendObj").equals("")){
                    sendObj=AtFairyConfig.getOption("sendObj");
                }
            }

            /**
             * 调用工具类初始化接任务
             * @throws Exception
             */
            public void content_0() throws Exception {
                util.close();
                setTaskName(2);
                find=0;
            }

            /**
             * 如果任务已经接取，不需要继续执行工具类中找任务方法，直接开始setTaskName(3)
             * @throws Exception
             */
            public void content_1() throws Exception {
                if (overtime(8, 2)) return;
                result = mFairy.findPic("pic open task.png");
                mFairy.onTap(0.8f,result,"显示任务",Sleep);

                result = mFairy.findPic("task lan.png");
                mFairy.onTap(0.75f,result,"显示任务l栏",Sleep);

//                result=mFairy.findPic(5,201,217,366,"word huodong deal worm action.png");
//                mFairy.onTap(0.7f,result,"除虫行动活动指引",Sleep);
//                if (result.sim>0.7f){
//                    setTaskName(3);return;
//                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 3, 160,214, 160,346, 1000, 1500);
            }

            /**
             * 寻找任务并且前往
             * @throws Exception
             */
            public void content_2() throws Exception {
                //取消自动作战，不然会打断传送
                result=mFairy.findPic("auto ing.png");
                mFairy.onTap(0.8f,result,"停止自动作战",500);
                //寻找任务并且前往
                int ret =util.mission("single zengsongliwu.png",0);
                if (ret==1){
                    util.initDaze();
                    setTaskName(3);return;
                }else {
                    setTaskEnd();return;
                }
            }

            /**
             * 任务执行过程
             * @throws Exception
             */
            public void content_3() throws Exception {
                //超时处理
                if(daze>10){
                    LtLog.e(mFairy.getLineInfo("赠送礼物发呆超时，重置任务"));
                    setTaskName(0);
                    return;
                }

                result=mFairy.findPic("word dailyInterface.png");
                mFairy.onTap(0.8f,result,1219,68,1236,87,"关闭日常界面",1000);

                result=mFairy.findPic(601,396,677,438,"affirm.png");
                mFairy.onTap(0.8f,result,"确认",200);

                result=mFairy.findPic("accept1.png");
                mFairy.onTap(0.8f,result,"接受任务",1000);

                result=mFairy.findPic("finish2.png");
                mFairy.onTap(0.8f,result,"完成任务",1000);

                result = mFairy.findPic( "yiwenlu.png");
                if(result.sim>0.8f){
                    result=mFairy.findPic(10,344,1267,476,"word send "+sendObj+".png");
                    if(result.sim>0.8f){
                        mFairy.onTap(0.8f,result,"选择送礼物的对象",1000);
                    }else{
                        //向右滑动 0 1 2
                        if(find++>=3){
                            LtLog.e(mFairy.getLineInfo("没有该对象，赠送礼物结束"));
                            setTaskEnd();
                            return;
                        }
                        LtLog.e(mFairy.getLineInfo("寻找送礼对象"));
                        mFairy.ranSwipe(1000,512,500,512,500,1500l,0);
                        util.initDaze();
                    }
                }

                result = mFairy.findPic("visit.png");
                mFairy.onTap(0.8f,result,"前往拜访",1000);

                result = mFairy.findPic(183,174,289,554,"send.png");
                mFairy.onTap(0.8f,result,"送礼物",1000);

                result = mFairy.findPic("send1.png");
                picCount=picCount(0.8f,result,"send");
                if(picCount<=2){
                    mFairy.onTap(0.8f,result,"立即赠送",1000);
                }else{
                    mFairy.onTap(1212,171,1229,188,"赠送完成",1000);
                    setTaskEnd();
                    return;
                }

                result=mFairy.findPic("send2.png");
                if(result.sim>0.8){
                    mFairy.onTap(0.8f,result,1215,175,1229,188,"礼物不足或者赠送完成",1000);
                    setTaskEnd();
                    return;
                }

                result=mFairy.findPic("xueying.png");
                result1 = mFairy.findPic("send1.png");
                if (result.sim > 0.8f&& result1.sim < 0.8f){
                    LtLog.e("没有赠送按钮结束");
                    setTaskEnd();return;
                }
            }
        }.taskContent(mFairy,"赠送礼物");
    }

    //清理背包
    public void daily_clear()throws Exception{
        util.clear1();
    }

    //家族签到
    public void daily_jzqd() throws  Exception{
        new SingleTask(mFairy) {
            /**
             * 调用工具类初始化接任务
             * @throws Exception
             */
            public void content_0() throws Exception {
                util.close();
                daze=util.initDaze();
                result=mFairy.findPic("word haodashi.png");
                if(result.sim>0.8f){
                    setTaskName(3);
                    return;
                }
                setTaskName(2);
            }

            /**
             * 如果任务已经接取，不需要继续执行工具类中找任务方法，直接开始setTaskName(3)
             * @throws Exception
             */
            public void content_1() throws Exception {
                if (overtime(8, 2)) return;
                result = mFairy.findPic("pic open task.png");
                mFairy.onTap(0.8f,result,"显示任务",Sleep);

                result = mFairy.findPic("task lan.png");
                mFairy.onTap(0.75f,result,"显示任务l栏",Sleep);

//                result=mFairy.findPic(5,201,217,366,"word huodong deal worm action.png");
//                mFairy.onTap(0.7f,result,"除虫行动活动指引",Sleep);
//                if (result.sim>0.7f){
//                    setTaskName(3);return;
//                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 3, 160,214, 160,346, 1000, 1500);
            }

            /**
             * 寻找任务并且前往
             * @throws Exception
             */
            public void content_2() throws Exception {
                //取消自动作战，不然会打断传送
                result=mFairy.findPic("auto ing.png");
                mFairy.onTap(0.8f,result,"停止自动作战",500);
                //寻找任务并且前往
                int ret =util.mission("single jiazuqiandao.png",0);
                if (ret==1){
                    util.initDaze();
                    setTaskName(3);return;
                }else {
                    setTaskEnd();return;
                }
            }

            /**
             * 任务执行过程
             * @throws Exception
             */
            public void content_3() throws Exception {
                if(daze>15){
                    LtLog.e(mFairy.getLineInfo("家族签到超时，重置任务"));
                    setTaskName(0);
                    return;
                }
                result=mFairy.findPic("hao.png");
                mFairy.onTap(0.8f,result,"剧情提示",500);

                result=mFairy.findPic("word haodashi.png");
                if(result.sim>0.8f){
                    result=mFairy.findPic(172,390,310,568,"family sign in.png");
                    mFairy.onTap(0.8f,result,"点击签到",500);
                }

                result=mFairy.findPic("fu.png");
                mFairy.onTap(0.8f,result,"祈福",500);

                result=mFairy.findPic(576,582,647,621,"lingqu.png");
                mFairy.onTap(0.8f,result,"领取奖励",500);

                result=mFairy.findPic("share.png");
                mFairy.onTap(0.8f,result,933,246,965,279,"家族签到完成",500);

                result=mFairy.findPic("buji.png");
                if(result.sim>0.8){
                    mFairy.onTap(0.8f,result,636,406,640,412,"领取家族补给箱",500);
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy,"家族签到");
    }

    //家族捐献
    public void daily_jzjx() throws  Exception{
        new SingleTask(mFairy) {
            /**
             * 调用工具类初始化接任务
             * @throws Exception
             */
            public void content_0() throws Exception {
                util.close();
                setTaskName(2);
            }

            /**
             * 如果任务已经接取，不需要继续执行工具类中找任务方法，直接开始setTaskName(3)
             * @throws Exception
             */
            public void content_1() throws Exception {
                if (overtime(8, 2)) return;
                result = mFairy.findPic("pic open task.png");
                mFairy.onTap(0.8f,result,"显示任务",Sleep);

                result = mFairy.findPic("task lan.png");
                mFairy.onTap(0.75f,result,"显示任务l栏",Sleep);

//                result=mFairy.findPic(5,201,217,366,"word huodong deal worm action.png");
//                mFairy.onTap(0.7f,result,"除虫行动活动指引",Sleep);
//                if (result.sim>0.7f){
//                    setTaskName(3);return;
//                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 3, 160,214, 160,346, 1000, 1500);
            }

            /**
             * 寻找任务并且前往
             * @throws Exception
             */
            public void content_2() throws Exception {
                //取消自动作战，不然会打断传送
                result=mFairy.findPic("auto ing.png");
                mFairy.onTap(0.8f,result,"停止自动作战",500);
                //寻找任务并且前往
                int ret =util.mission("single jiazujuanxian.png",0);
                if (ret==1){
                    util.initDaze();
                    setTaskName(3);return;
                }else {
                    setTaskEnd();return;
                }
            }

            /**
             * 任务执行过程
             * @throws Exception
             */
            public void content_3() throws Exception {
                if(daze>10){
                    LtLog.e(mFairy.getLineInfo("家族捐献超时，重置任务"));
                    setTaskName(0);
                    return;
                }
                result=mFairy.findPic("gongxian.png");
                mFairy.onTap(0.8f,result,713,439,726,449,"确认提示",500);

                result=mFairy.findPic("juanxian.png");
                if(result.sim>0.8f){
                    util.initDaze();
                    mFairy.onTap(0.8f,result,"低级捐献",1000);
                }

                result=mFairy.findPic("finish1.png");
                if(result.sim>0.8){
                    mFairy.onTap(0.8f,result,1137,55,1169,90,"家族捐献完成",500);
                    setTaskEnd();
                    return;
                }
            }

            @Override
            public void inOperation() throws Exception {
                result=mFairy.findPic("word hint bangyin.png");
                if(result.sim>0.8f){
                    mFairy.onTap(0.8f,result,533,439,556,459,"绑银不足，无法捐献，结束任务",1000);
                    setTaskEnd();
                    return;
                }
                super.inOperation();
            }
        }.taskContent(mFairy,"家族捐献");
    }

    //雕像参拜
    public void daily_dxcb()throws  Exception{
        new SingleTask(mFairy) {
            public void content_0() throws Exception {
                util.close();
                visitIndex=1;
                setTaskName(2);
            }

            public void content_2() throws Exception {
                //取消自动作战，不然会打断传送
                result=mFairy.findPic("auto ing.png");
                mFairy.onTap(0.8f,result,"停止自动作战",500);
                //寻找任务并且前往
                int ret =util.mission("single diaoxiangcanbai.png",0);
                if (ret==1){
                    mFairy.initDaze();
                    visitIndex=1;
                    setTaskName(3);return;
                }else {
                    setTaskEnd();return;
                }
            }

            int visitIndex=1,group=0;//第几个雕像(一共有7个位置，不是每个位置都有雕像)，第几轮(执行两轮，两轮后不管是否参拜完三个都结束)
            boolean initBacak=false;//是否回到起点
            public void content_3() throws Exception {
                if(daze>50){
                    setTaskName(0);
                    LtLog.e(mFairy.getLineInfo("参拜雕像超时，重置任务"));
                    return;
                }
                int x1=0,y1=0,x2=0,y2=0;
                if(visitIndex>=1&&visitIndex<=3){
                    y1=256;x2=750;y2=200;
                }
                switch(visitIndex){
                    case 1:
                        x1=397;
                        break;
                    case 2:
                        x1=380;
                        break;
                    case 3:
                        x1=363;
                        break;
                    case 4:
                        x1=396;y1=240;x2=385;y2=164;
                        break;
                    case 5:
                        x1=380;y1=239;x2=471;y2=195;
                        break;
                    case 6:
                        x1=364;y1=239;x2=509;y2=211;
                        break;
                    case 7:
                        x1=346;y1=238;x2=451;y2=451;
                        break;
                }
                x1=(int)(x1 *1.3323+y1 *0.0098+309.8233);
                y1=(int)(x1 *-0.0+y1 *-1.3333+686.6667);
                result=mFairy.findPic("map down.png");
                result1=mFairy.findPic("word place xdc.png");
                if(result.sim>0.9f&&result1.sim<0.70f){
                    LtLog.e(mFairy.getLineInfo("不在夏都城，重新启动任务"));
                    setTaskName(0);
                    return;
                }

                result=mFairy.findPic("word 2.5D.png");
                mFairy.onTap(0.8f,result,"切换3D视角",500);

                result=mFairy.findPic(611,395,642,468,"affirm.png");
                mFairy.onTap(0.8f,result,"点击确认",100);

                result=mFairy.findPic(496,387,606,538,"cancel.png");
                mFairy.onTap(0.8f,result,"取消",1000);


                result= mFairy.findPic(942,0,1209,291,new String[]{"close.png","close4.png","close3.png","close6.png"});
                mFairy.onTap(0.8f,result,"雕像参拜 关闭",1000);

                result= mFairy.findPic(1209,0,1280,291,new String[]{"close.png","close4.png","close3.png","close6.png"});
                result1=mFairy.findPic("world map.png");
                if(result.sim>0.8f&&result1.sim<0.8f){
                    mFairy.onTap(0.8f,result,"雕像参拜 关闭",1000);
                }

                result=mFairy.findPic("word activity hint.png");
                mFairy.onTap(0.8f,result,967,172,986,194,"关闭活动提醒",1000);

                //有地图 点击坐标前往
                result=mFairy.findPic("world map.png");
                result1=mFairy.findPic(408,95,449,142,"close left.png");
                if(result.sim>0.8&&result1.sim<0.8f){
                    if(initBacak){
                        mFairy.onTap(881,360,882,361,"回到起始点",500);
                        mFairy.onTap(881,360,882,361,"回到起始点",500);
                        initBacak= false;
                    }else{
                        visitIndex++;
                        mFairy.onTap(x1,y1,x1+1,y1+1,"前往雕像"+visitIndex,500);
                        mFairy.onTap(x1,y1,x1+1,y1+1,"前往雕像"+visitIndex,500);
                        initBacak= true;
                    }
                    result = mFairy.findPic(890,3,1275,235,new String[]{"close.png","close3.png"});
                    mFairy.onTap(0.8f,result,"关闭区域地图",2000);
                }

                //参拜完7个
                if(visitIndex>7){
                    setTaskName(0);
                    LtLog.e(mFairy.getLineInfo("参拜完7个，回任务栏查看完成状态"));
                    //1 2
                    if(++group>=2){
                        LtLog.e(mFairy.getLineInfo("雕像数量不够，无法完成任务"));
                        setTaskEnd();
                        return;
                    }
                    return;
                }

                //不在寻路
                result=mFairy.findPic(new String[]{"word find.png","word way.png"});
                if(result.sim<0.8f){
                    result=mFairy.findPic(408,95,449,142,"close left.png");
                    result1=mFairy.findPic("word visit.png");

                    //有左边的展开
                    if(result.sim>0.8f){
                        if(result1.sim>0.8f){
                            mFairy.onTap(0.8f,result1,"参拜雕像"+visitIndex,500);
                        }else{
                            mFairy.onTap(0.8f,result,"关闭左侧页面",500);
                        }
                    }else{
                        mFairy.onTap(x2,y2,x2+1,y2+1,"选择雕像参拜",1000);

                        result=mFairy.findPic("map down.png");
                        mFairy.onTap(0.8f,result,1168,92,1185,107,"打开区域地图",1000);
                        return;
                    }
                }
            }
        }.taskContent(mFairy,"雕像参拜");
    }

    //隐形密探
    public void szmt() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void content_0() throws Exception {
                util.close();
                result = mFairy.findPic("pic open task.png");
                mFairy.onTap(0.8f,result,"显示任务",Sleep);

                result = mFairy.findPic("task3.png");
                mFairy.onTap(0.8f,result,"切换任务栏",Sleep);

                result = mFairy.findPic("lianmeng.png");
                mFairy.onTap(0.8f,result,"联盟栏",Sleep);

//                //联盟
//                result=mFairy.findPic(49,178,179,390,"word task yxmt.png");
//                mFairy.onTap(0.7f,result,"隐形密探指引",1000);
//                if(result.sim>0.7f){
//                    daze=util.initDaze();
//                    setTaskName(3);
//                    return;
//                }

                setTaskName(1);return;
            }

            int mtCount = 0;

            public void content_1() throws Exception {
                long dazeTime = mFairy.mMatTime(1189,151,76,23, 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));

                result = mFairy.findPic("task3.png");
                mFairy.onTap(0.8f,result,"切换任务栏",Sleep);

                result = mFairy.findPic("lianmeng.png");
                mFairy.onTap(0.7f,result,"联盟栏",Sleep);



                result = mFairy.findPic(40,163,269,271, new String[]{"qbzz.png","qbzz2.png"});
                mFairy.onTap(0.7f, result, "左侧隐形密探", Sleep);
                LtLog.e("***"+result.sim);
                if (result.sim >= 0.7f) {
                   // result1 = mFairy.findPic(47,261,215,399, "qingbao.png");
                    if (result.sim > 0.7f) {
                        LtLog.e(mFairy.getLineInfo("密探前往"));
                        if (mtCount == 0) {//116,227   234,311,240,314   235,334,241,337
                            mFairy.onTap(0.7f, result, result.x + 127, result.y + 85, result.x + 124, result.y + 87, "第1个前往", Sleep);
                        }
                        if (mtCount == 1) {
                            mFairy.onTap(0.7f, result, result.x + 127, result.y + 108, result.x + 128, result.y + 111, "第2个前往", Sleep);
                        }
                        if (mtCount == 2) {
                            mFairy.onTap(0.7f, result, result.x + 127, result.y + 132, result.x + 128, result.y + 135, "第3个前往", Sleep);
                        }
                        if (mtCount == 3) {
                            mFairy.onTap(0.7f, result, result.x + 117, result.y + 156, result.x + 128, result.y + 159, "第4个前往", Sleep);
                        }
                    }
                }else {
                    mFairy.ranSwipe(66,316,71,203,1000,1500);
                }

                if (dazeTime > 10) {
                    mFairy.initMatTime();
                    mtCount++;
                    if (mtCount >= 4) {
                        mtCount = 0;
                    }
                    mFairy.initMatTime();
                    result1 = mFairy.findPic("meng.png");
                    result = mFairy.findPic(43,171,230,421,"qbzz.png");
                    LtLog.e("*-****+"+result1.sim);
                    if (result.sim < 0.7f && result1.sim>0.8f) {
                        LtLog.e(mFairy.getLineInfo("左侧没有密探结束"));
                        setTaskEnd();
                        return;
                    }else {
                        setTaskName(0);return;
                    }
                }

                result=mFairy.findPic("word yxmt get.png");
                util.onTap(0.8f,result,"接取隐形密探任务",1000);

                result=mFairy.findPic("word yxmt hand in.png");
                util.onTap(0.8f,result,"交付隐形密探任务",1000);

                result=mFairy.findPic("cutting.png");
                util.onTap(0.8f,result,"打听情报",1000);

                result=mFairy.findPic("yes.png");
                util.onTap(0.8f,result,"确认",1000);

                result = mFairy.findPic( "death.png");
                mFairy.onTap(0.8f, result, "复活", Sleep);

            }
        }.taskContent(mFairy, "隐形密探任务中");
    }

    //本源争夺
    public void szzyzd() throws Exception {
        new SingleTask(mFairy) {
            @Override
            public void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic("jiaofu.png");
                mFairy.onTap(0.8f, result, "交付资源争夺任务", Sleep);
                if (result.sim > 0.8f) {
                    if (AtFairyConfig.getOption("szzy").equals("1")) {
                        stopCount++;
                        if (stopCount >= 3) {
                            LtLog.e(mFairy.getLineInfo("完成3次结束"));
                            setTaskEnd();
                            return;
                        }
                    }
                }
                result = mFairy.findPic("hand.png");
                mFairy.onTap(0.7f, result, "采集资源", Sleep);
                if (result.sim > 0.7f) {
//                    result1 = mFairy.findPic("wfdq.png");
//                    if (result1.sim > 0.8f) {
//                        zyCount++;
//                        if (zyCount > 4) {
//                            zyCount = 0;
//                        }
//                        LtLog.e(mFairy.getLineInfo("无法采集"));
//                    } else {
//                        Thread.sleep(10000);
//                    }
                    Thread.sleep(10000);
                    //mFairy.initMatTime();
                }
                result=mFairy.findPic("yes.png");
                mFairy.onTap(0.8f,result,"确认",1000);

                result = mFairy.findPic( "death.png");
                mFairy.onTap(0.8f, result, "复活", Sleep);
                if (result.sim > 0.8f) {
                    zyCount++;
                    if (zyCount > 4) {
                        zyCount = 0;
                    }
                }
            }

            public void content_0() throws Exception {
                util.close();
                setTaskName(1);return;
            }

            int zyCount = 0, stopCount = 0;

            public void content_1() throws Exception {
                result = mFairy.findPic("task3.png");
                mFairy.onTap(0.8f,result,"切换任务栏",Sleep);

                result = mFairy.findPic("lianmeng.png");
                mFairy.onTap(0.7f,result,"联盟栏",2000);

                result1 = mFairy.findPic(41,164,271,377,"byzd.png");
                if (picCountS(0.7f, result1, "没有资源争夺") > 30) {
                    LtLog.e(mFairy.getLineInfo("没有资源争夺结束"));
                    setTaskEnd();
                    return;
                }// 130,198  248,290,254,296            121 185       238 281
                mFairy.onTap(0.7f, result1,  result1.x+118,result1.y+92,result1.x+124,result1.y+98,"左侧资源争夺", 5000);
                if (result1.sim <0.7f){
                    mFairy.ranSwipe(71,203,66,316,1000,1500);
                }


                result = mFairy.findPic(36,320,256,393,"byzd.png");
                if(result.sim>0.7f){
                    LtLog.e(mFairy.getLineInfo("资源争夺往上滑"));
                    mFairy.ranSwipe(124,372,125,247,500,1000l,1);
                    return;
                }


                    result = mFairy.findPic("benyuan.png");
                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("本源界面"));
                    if (zyCount == 0) {
                        result = mFairy.findPic(929,140,1096,206, new String[]{"guard.png","baphu.png"});
                        if (result.sim < 0.8f) {
                            mFairy.onTap(999,168,1010,178, "前往第一个", Sleep);
                            setTaskName(2);
                            return;
                        } else {
                            zyCount++;
                        }
                    }
                    if (zyCount == 1) {
                        result = mFairy.findPic(940,230,1091,292, new String[]{"guard.png","baphu.png"});
                        if (result.sim < 0.8f) {
                            mFairy.onTap(1010,260,1020,267, "前往第2个", Sleep);
                            setTaskName(2);
                            return;
                        } else {
                            zyCount++;
                        }
                    }
                    if (zyCount == 2) {
                        result = mFairy.findPic(935,311,1094,383, new String[]{"guard.png","baphu.png"});
                        if (result.sim < 0.8f) {
                            mFairy.onTap(992,338,1002,347, "前往第3个", Sleep);
                            setTaskName(2);
                            return;
                        } else {
                            zyCount++;
                        }
                    }
                    if (zyCount == 3) {
                        result = mFairy.findPic(931,400,1094,463, new String[]{"guard.png","baphu.png"});
                        if (result.sim < 0.8f) {
                            mFairy.onTap(989,426,1003,435, "前往第4个", Sleep);
                            setTaskName(2);
                            return;
                        } else {
                            zyCount++;
                        }
                    }
                    if (zyCount == 4) {
                        result = mFairy.findPic(931,481,1093,553, new String[]{"guard.png","baphu.png"});
                        if (result.sim < 0.8f) {
                            mFairy.onTap(998,515,1014,526, "前往第5个", Sleep);
                            setTaskName(2);
                            return;
                        } else {
                            zyCount = 0;
                        }
                    }
                }
            }

            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(1192,152,79,19 , 0.9f);
                LtLog.e(mFairy.getLineInfo("发呆时间=" + dazeTime));
                if (dazeTime > 5) {
                    mFairy.initMatTime();
                    result=mFairy.findPic("pic yjzl enter.png");
                    mFairy.onTap(0.8f,result,1173,95,1182,104,"打开地图",2000);
                    result=mFairy.findPic(542,282,779,403,"peoper.png");
                    mFairy.onTap(0.8f,result,result.x+1,result.y+14,result.x+2,result.y+16,"前往挖晶石",2000);
                    mFairy.onTap(0.8f,result,1225,40,1237,53,"关闭",2000);
                   // mFairy.ranSwipe(192,589, 111,502, 4000, (long) 1000, 2);
                    setTaskName(1);
                    return;
                }
            }
        }.taskContent(mFairy, "资源争夺中");
    }

    //通天秘境
    public void ttmj() throws  Exception{
        new SingleTask(mFairy) {
            /**
             * 调用工具类初始化接任务
             * @throws Exception
             */
            public void content_0() throws Exception {
                util.close();
                setTaskName(2);
            }

            /**
             * 如果任务已经接取，不需要继续执行工具类中找任务方法，直接开始setTaskName(3)
             * @throws Exception
             */
            public void content_1() throws Exception {
                if (overtime(8, 2)) return;
                result = mFairy.findPic("pic open task.png");
                mFairy.onTap(0.8f,result,"显示任务",Sleep);

                result = mFairy.findPic("task lan.png");
                mFairy.onTap(0.75f,result,"显示任务l栏",Sleep);
//                result=mFairy.findPic(46,183,244,394,"single.png");
//                mFairy.onTap(0.7f,result,"除虫行动活动指引",Sleep);
//                if (result.sim>0.7f){
//                    setTaskName(3);return;
//                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 3, 160,214, 160,346, 1000, 1500);
            }

            /**
             * 寻找任务并且前往
             * @throws Exception
             */
            public void content_2() throws Exception {
                //取消自动作战，不然会打断传送
                result=mFairy.findPic("auto ing.png");
                mFairy.onTap(0.8f,result,"停止自动作战",500);
                //寻找任务并且前往
                int ret =util.mission("ttmj.png",0);
                if (ret==1){
                    util.initDaze();
                    setTaskName(3);return;
                }else {
                    setTaskEnd();return;
                }
            }

            /**
             * 任务执行过程
             * @throws Exception
             */
            public void content_3() throws Exception {
                result=mFairy.findPic("saodang.png");
                mFairy.onTap(0.8f,result,"前往扫荡",1000);

             /*   result=mFairy.findPic(1032,500,1122,548,"go.png");
                mFairy.onTap(0.8f,result,"前往",1000);

                result=mFairy.findPic("yijiansaodang.png");
                mFairy.onTap(0.8f,result,"一键扫荡",1000);*/

                result=mFairy.findPic(942,614,1136,693,"start.png");
                if(result.sim>0.8f){
                    daze=util.initDaze();
                    mFairy.onTap(0.8f,result,"开始挑战",1000);
                }

                result=mFairy.findPic(601,552,1100,673,"continue.png");
                mFairy.onTap(0.8f,result,"继续挑战",1000);

                //发呆
                result=mFairy.findPic("leave2.png");
                if(result.sim>0.8){
                    result=mFairy.findPic("auto.png");
                    mFairy.onTap(0.8f,result,"开始自动作战",1000);
                }

                result=mFairy.findPic("zuigao.png");
                if(result.sim>0.8f){
                   // mFairy.onTap(0.8f,result,1217,68,1236,88,"已达最高层，任务结束",1000);
                    LtLog.e("最高层结束");
                    setTaskEnd();
                    return;
                }

                result=mFairy.findPic("zaici.png");
                mFairy.onTap(0.8f,result,"失败再次挑战",1000);
               /* result=mFairy.findPic("zuigao.png");
                if(result.sim>0.8f) {

                    setTaskEnd();
                    return;
                }*/

                result=mFairy.findPic(new String[]{"zaici voer.png","num.png"});
                if(result.sim>0.8) {
                    LtLog.e("没次数了 结束");
                    //mFairy.onTap(1216,68,1237,86,"挑战次数不足，结束任务",1000);
                    setTaskEnd();
                    return;
                }
                if(daze>20){
                    LtLog.e(mFairy.getLineInfo("通天秘境超时，重置任务"));
                    setTaskName(0);
                    return;
                }
            }
        }.taskContent(mFairy,"通天秘境");
    }

    boolean rebuild=false;//原地复活
    @Override
    public void inOperation() throws Exception {
        //daze=util.dazeTime();
        result=mFairy.findPic(390,188,887,241,"fuhuo.png");
        if(result.sim>0.8){
            LtLog.e("被野怪或其他玩家打死，选择复活方式后，继续任务");
            if(rebuild){
                mFairy.onTap(0.8f,result,516,461,529,473,"原地复活",500);
            }else{
                mFairy.onTap(0.8f,result,744,458,758,474,"安全期复活",500);
            }
            setTaskName(0);
            return;
        }


        result=mFairy.findPic(362,240,477,335,"pic hint.png");
        if(result.sim>0.8f){
            //确定 复活
            result=mFairy.findPic(new String[]{"word hint rebuild.png"});
            if(result.sim>0.8f){
                result=mFairy.findPic(696,345,759,480,"affirm.png");
                mFairy.onTap(0.8f,result,"确定",1000);
            }

            result=mFairy.findPic(500,345,603,480,"cancel.png");
            mFairy.onTap(0.8f,result,"取消",500);
        }

        //自动寻路中
        result=mFairy.findPic(561,467,747,513,new String[]{"xunlu.png","word way.png"});
        if(result.sim>0.8){
            LtLog.e(mFairy.getLineInfo("自动寻路中"));
            mFairy.initMatTime();
            err=0;
        }
        //传送
        result=mFairy.findPic(543,400,768,520,new String[]{"chuansong.png"});
        if(result.sim>0.8f){
            LtLog.e(mFairy.getLineInfo("地图传送中"));
            mFairy.initMatTime();
            err=0;
        }
    }

    //线上测试
    void lineTest() throws Exception{
        new SingleTask(mFairy){
            @Override
            public void create() throws Exception {
                taskList=new ArrayList();
                if(AtFairyConfig.getOption("killGame").equals("1")){
                    taskList.add("killGame");
                }
                if(AtFairyConfig.getOption("restart").equals("1")){
                    taskList.add("restart");
                }
                if(AtFairyConfig.getOption("restartGame").equals("1")){
                    taskList.add("restartGame");
                }
                if(AtFairyConfig.getOption("baoshi").equals("1")){
                    taskList.add("baoshi");
                }
                if(AtFairyConfig.getOption("team_follow").equals("1")){
                    taskList.add("team_follow");
                }
                setTaskName(0);
            }

            @Override
            public void content_0() throws Exception {
                setTaskName(1);
            }

            @Override
            public void content_1() throws Exception {
                if(taskList.size()==0){
                    LtLog.e(mFairy.getLineInfo("没有测试任务了"));
                    setTaskEnd();
                    return;
                }
                if(taskList.get(0).equals("killGame")){
                    LtLog.e(mFairy.getLineInfo("1秒后杀死游戏"));
                    mFairy.sleep(1000);
                    mFairy.killUserGame();
                    taskList.remove(0);
                    return;
                }

                if(taskList.get(0).equals("restart")){
                    LtLog.e(mFairy.getLineInfo("1秒后重启脚本"));
                    mFairy.sleep(1000);
                    mFairy.restart();
                    taskList.remove(0);
                    return;
                }

                if(taskList.get(0).equals("restartGame")){
                    LtLog.e(mFairy.getLineInfo("1秒后重启游戏"));
                    mFairy.sleep(1000);
                    util.restartGame();
                    taskList.remove(0);
                    return;
                }

                if(taskList.get(0).equals("team_follow")){
                    mFairy.sleep(1000);
                    new TeamTask(mFairy).team_follow();
                    return;
                }

                if(taskList.get(0).equals("baoshi")){
                    new TimingActivity(mFairy).timing_baoshi();
                    taskList.remove(0);
                    return;
                }
            }
        }.taskContent(mFairy,"测试");
    }
}