package com.script.fairy;

import com.script.content.ScProxy;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import org.json.JSONException;
import org.json.JSONObject;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;


/**
 * Created by Administrator on 2019/1/24 0024.
         */
public class TaskMain {
     AtFairyImpl mFairy;
     Util util;
     TeamTask teamTask;
     SingleTask singleTask;
     LimitLessTask limitlessTask;
     TimingActivity timingActivity;
     FindResult result;
     public  TaskMain (AtFairyImpl atFairy) throws Exception {
         mFairy = atFairy;
         mFairy.setGameName("剑侠世界3");
         mFairy.setGameVersion(9);
         init();
         util= new Util(mFairy);
         teamTask=new TeamTask(mFairy);
         singleTask=new SingleTask(mFairy);
         timingActivity=new TimingActivity(mFairy);
         limitlessTask=new LimitLessTask(mFairy);
         Thread.sleep(4000);
         mFairy.initMatTime();
    }

     public void main() throws Exception {

         if(!AtFairyConfig.getOption("task_id").equals("")){
             task_id = Integer.parseInt(AtFairyConfig.getOption("task_id"));
         }

         ScProxy.profiler().startWithUserTag("");
         switch (task_id) {

             case 2769://新手指引22级
                 singleTask.novice();
                 break;
             case 2771://日常任务
                 if (AtFairyConfig.getOption("fuli").equals("1")){
                     singleTask.fuli();//签到追回
                 }
                 if (AtFairyConfig.getOption("mrdt").equals("1")){
                     singleTask.mrdt();//每日答题
                 }
                 if (AtFairyConfig.getOption("xltb").equals("1")){
                     singleTask.xltb();//寻龙探宝
                 }
                 if (AtFairyConfig.getOption("shrw").equals("1")){
                     singleTask.shrw();//商会任务
                 }
                 break;
             case 2775://野外挂机
                 //limitlessTask.limitless();
                 limitlessTask.hang();
                 break;
             case 1939://新手指引22级//测试
                 singleTask.novice();
                 break;
             case 1937://日常任务ahuihiji
                     singleTask.fuli();//签到追回
                     singleTask.mrdt();//每日答题
                     singleTask.xltb();//寻龙探宝
                 break;
             case 1943://野外挂机
                 limitlessTask.hang();
                 break;
             case 1647://副本任务
                 result=mFairy.findPic("leave.png");
                 if (result.sim > 0.8f){

                 }else {
                     util.back_city();
                 }
                 teamTask.copy();
                 break;
             case 1649://野外挂机
                 limitlessTask.limitless();
                 limitlessTask.hang();
                 break;
             case 1955://线上测试
                 singleTask.lineTest();
                break;
             case 100://测试
                 //singleTask.szmt();

                 singleTask.szzyzd();
                break;

            //以下是测试渠道
             case 2368://新手指引
                 singleTask.novice();
                 break;
             case 2370://日常任务
                 singleTask.daily();
                 break;
             case 2372://副本任务
                 util.back_city();
                 teamTask.copy();
                 break;
         }

         mFairy.finish(AtFairyConfig.getTaskID(), TASK_STATE_FINISH);
         Thread.sleep(2000);
    }

    private int  task_id;
    public void  init() throws Exception {
        task_id = 0;
        try {
            JSONObject optionJson = new JSONObject(AtFairyConfig.getUserTaskConfig());
            LtLog.e(mFairy.getLineInfo("选项列表" + optionJson));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!AtFairyConfig.getOption("task_id").equals("")) {
            task_id = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        }
    }
}
