package tool;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/5/8 0002.
 * 输出提示工具
 */

public class ToastUtils {
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
