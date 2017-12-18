package tool;

import android.content.Context;
import android.content.SharedPreferences;

import entity.CaiPu;

/**
 * Created by Administrator on 2017/5/8.
 * 共享参数，用于存储app是否是第一次进入等数据
 */

public class ShareTools {
    Context context;
    private static ShareTools shareTools;

    private ShareTools(Context context) {
        this.context = context;
    }

    /*
    单例模式，保证所有界面都是使用同一个share
     */
    public synchronized static ShareTools getInstance(Context context) {

        if (shareTools == null) {
            shareTools = new ShareTools(context);
        }
        return shareTools;

    }


    //存储是否第一次进入
    public void saveFirst() {
        SharedPreferences sp = context.getSharedPreferences("first", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isfirst", false);
        editor.commit();
    }

    //获取是否第一次进入
    public boolean loadFirst() {
        SharedPreferences sp = context.getSharedPreferences("first", Context.MODE_PRIVATE);
        return sp.getBoolean("isfirst", true);
    }
}
