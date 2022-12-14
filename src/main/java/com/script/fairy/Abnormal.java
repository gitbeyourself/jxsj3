package com.script.fairy;

import android.content.Context;
import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class Abnormal  {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    Util util;
    int task_id;
    boolean restart=false;
    public Abnormal(AtFairyImpl atFairy) throws Exception {
        mFairy = atFairy;
        util=new Util(mFairy);
        task_id = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        if(AtFairyConfig.getOption("restart").equals("1")){
            restart=true;
        }
    }

    public String CMD(String[] mString) {
        Context mContext = mFairy.getContext();
        Runtime cmd = Runtime.getRuntime();
        String inline = "";
        String header = "";
        try {
            Process p = cmd.exec(mString);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while ((inline = br.readLine()) != null) {
                String mStr = inline;
                if ( mStr.contains("CPU%")) {
                    header = mStr;
                    if(sb.length() > 0) {

                        sb.delete(0, sb.length());
                    }
                    continue;
                    // LtLog.i("haha123: " + mFairy.getLineInfo("============================") + mStr);
                }



                if(mStr.contains(mContext.getPackageName())){
//                    sb.append(inline);
                    LtLog.i("CPU USAGE-INFO-HEADER : "  + header);
                    LtLog.i("CPU USAGE-INFO-BODY   : "  + mStr);
                }

//                }
            }
            inline = br.readLine();
            br.close();
            p.waitFor();

        } catch (Exception e) {

        }
        return inline;
    }


    //????????????
    public void erro() throws Exception {

        if(restart){
            int hour=mFairy.dateHour();
            int minute= mFairy.dateMinute();
            if(hour==5&&minute==0){
                mFairy.restart();
                LtLog.e(mFairy.getLineInfo("????????????"));
                return;
            }
        }

        result=mFairy.findPic(  696,263,1039,492,"shiyong.png");
        mFairy.onTap(0.8f,result,"err??????",500);

        result=mFairy.findPic( 1000,5,1146,66,"continue.png");
        mFairy.onTap(0.8f,result,"err????????????",500);

        result=mFairy.findPic(  new String[]{"word select channel.png","word select channel1.png"});
        mFairy.onTap(0.8f,result,result.x+53,result.y+97,result.x+73,result.y+117,"error_????????????",1000);

        result=mFairy.findPic(  new String[]{"pic activity exit.png","pic activity exit1.png"});
        mFairy.onTap(0.8f,result,"error_????????????",1000);

        result=mFairy.findPic(  "word know.png");
        mFairy.onTap(0.8f,result,"error_????????????",500);

        result=mFairy.findPic(  "word none find.png");
        mFairy.onTap(0.8f,result,509,439,523,453,"error_??????",500);

        result=mFairy.findPic(  "word btn start.png");
        mFairy.onTap(0.8f,result,"error_????????????",1000);

        result=mFairy.findPic(  "word notice.png");
        mFairy.onTap(0.8f,result,1149,69,1159,81,"error_????????????",1000);

        result=mFairy.findPic(  "word hint image.png");
        mFairy.onTap(0.8f,result,715,406,743,430,"error_?????????????????????",1000);

        result=mFairy.findPic(403,432,903,587, new String[]{"word btn enter.png","word btn enter1.png"});
        mFairy.onTap(0.8f,result,"error_????????????",1000);

        result=mFairy.findPic(  "word btn next step.png");
        mFairy.onTap(0.8f,result,"error_?????????",1000);

        result=mFairy.findPic(  "any close.png");
        mFairy.onTap(0.75f,result,"error_??????????????????",500);

        result=mFairy.findPic(  1225,665,1269,713,"chat continue.png");
        mFairy.onTap(0.8f,result,"error_?????????????????????",200);
        if(result.sim>0.8f)util.initDaze();

        result=mFairy.findPic(  "chat continue1.png");
        mFairy.onTap(0.8f,result,"error_?????????????????????",200);
        if(result.sim>0.8f)util.initDaze();

        result = mFairy.findPic(  1221, 0,1280, 51, "chat continue2.png");
        mFairy.onTap(0.8f,result,"error_????????????",200);

        result=mFairy.findPic(  new String[]{"word ren.png","word no like.png"});
        mFairy.onTap(0.8f,result,"error_????????????????????????",500);

        result=mFairy.findPic(  673,318,827,347,"qinmi.png");
        mFairy.onTap(0.8f,result,705,434,756,463,"error_???????????? ??????????????????",100);

        result=mFairy.findPic(  "word hint have latest version.png");
        mFairy.onTap(0.8f,result,635,429,646,440,"error_???????????????????????????",1000);

        result = mFairy.findPic("new sure.png");
        mFairy.onTap(0.8f, result, "err??????qq??????????????????", 1000);

        result = mFairy.findPic("new authorization.png");
        mFairy.onTap(0.8f, result, "err??????qq??????", 1000);

        result = mFairy.findPic(new String[]{"new login.png","new login2.png","new login3.png"});
        mFairy.onTap(0.8f, result, "err??????qq??????", 1000);
    }
}
