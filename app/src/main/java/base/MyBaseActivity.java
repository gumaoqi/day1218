package base;

import android.content.Intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by Administrator on 2017/5/2 0002.
 */
//项目的基类Activity
public abstract class MyBaseActivity extends AppCompatActivity {//123s
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    //设置布局
    public abstract void setLayout();

    //初始化控件
    public abstract void iniview();

    //设置控件相关操作
    public abstract void setview();

    //初始化数据
    public abstract void inidata();


    /**
     * @param c       跳转的目标页面
     * @param bundle  传递的参数
     * @param enterId 页面载入的动画
     * @param exitId  页面退出的动画
     */
    public void startActivity(Class c, Bundle bundle, int enterId, int exitId) {
        Intent intent = new Intent(this, c);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (enterId != 0 && exitId != 0) {

            overridePendingTransition(enterId, exitId);

        }
    }
}

