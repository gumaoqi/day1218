package introduction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.day_0508_caipu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import entity.CaiPu;

/**
 * Created by Administrator on 2017/5/9.
 */

public class JianJieFragment extends Fragment {
    @BindView(R.id.fragment_jianjie_tv_chara)
    TextView fragmentJianjieTvChara;
    @BindView(R.id.fragment_jianjie_tv_tag)
    TextView fragmentJianjieTvTag;
    @BindView(R.id.fragment_jianjie_tv_peiliao)
    TextView fragmentJianjieTvPeiliao;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jianjie, null);
        unbinder = ButterKnife.bind(this, view);
        fragmentJianjieTvChara.setText(IntroActivity.dataBean.getImtro());
        fragmentJianjieTvTag.setText(IntroActivity.dataBean.getTags());
        fragmentJianjieTvPeiliao.setText(IntroActivity.dataBean.getBurden());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
