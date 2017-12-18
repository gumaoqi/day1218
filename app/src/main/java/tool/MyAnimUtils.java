package tool;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.administrator.day_0508_caipu.R;

/**
 * Created by Administrator on 2017/5/8.
 */

public class MyAnimUtils {
    //第一个参数Context为程序的上下文
    //第二个参数id为动画XML文件的引用
    public static Animation loadAnimation(Context context, int id) {
        return AnimationUtils.loadAnimation(context, id);
    }
}