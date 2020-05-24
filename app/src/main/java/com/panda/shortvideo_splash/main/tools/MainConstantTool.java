package com.panda.shortvideo_splash.main.tools;

import android.support.annotation.IntDef;

import static com.panda.shortvideo_splash.main.tools.MainConstantTool.BEIJING;
import static com.panda.shortvideo_splash.main.tools.MainConstantTool.HANGZHOU;
import static com.panda.shortvideo_splash.main.tools.MainConstantTool.SHANGHAI;
import static com.panda.shortvideo_splash.main.tools.MainConstantTool.SHENZHEN;

/**
 * @author jiangzq
 * @description:
 * @date :2020/5/24 9:08
 */
//注意导包
@IntDef({SHANGHAI,HANGZHOU,BEIJING,SHENZHEN})
public @interface MainConstantTool {
    //public static int ShangHai = 0;
    int SHANGHAI = 0;
    int HANGZHOU = 1;
    int BEIJING = 2;
    int SHENZHEN = 3;
}
