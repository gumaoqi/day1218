package introduction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ImageView;

import com.example.administrator.day_0508_caipu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import base.MyBaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import entity.CaiPu;
import tool.ToastUtils;

/**
 * Created by Administrator on 2017/5/9.
 */

public class IntroActivity extends MyBaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inidata();
        setLayout();
        iniview();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setview();
    }

    @Override
    public void setLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_intro);
    }

    @BindView(R.id.intro_backdrop)
    ImageView introBackdrop;
    @BindView(R.id.intro_toolbar)
    Toolbar introToolbar;
    @BindView(R.id.intro_collapsing)
    CollapsingToolbarLayout introCollapsing;
    @BindView(R.id.intro_appbar)
    AppBarLayout introAppbar;
    @BindView(R.id.intro_tl)
    TabLayout introTl;
    @BindView(R.id.intro_vp)
    ViewPager introVp;

    @Override
    public void iniview() {
        ButterKnife.bind(this);
    }

    @Override
    public void setview() {
        introVp.setAdapter(vpAdapter);
        introTl.setTabMode(TabLayout.GRAVITY_CENTER);
        introTl.setupWithViewPager(introVp);
        introToolbar.setTitle(dataBean.getTitle());
        Picasso.with(this).load(dataBean.getAlbums().get(0))
                .resize(200, 150)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(introBackdrop);

    }

    List<Fragment> fragmentList;
    List<String> titleList;

    VpAdapter vpAdapter;
    public static CaiPu.ResultBean.DataBean dataBean;

    @Override
    public void inidata() {
        Intent intent = getIntent();
        dataBean = (CaiPu.ResultBean.DataBean) intent.getExtras().getSerializable("pass");
        ToastUtils.showToast(this, dataBean.getTitle());
        fragmentList = new ArrayList<Fragment>();
        titleList = new ArrayList<String>();
        fragmentList.add(new JianJieFragment());
        titleList.add("简介");
        fragmentList.add(new StepFragment());
        titleList.add("步骤");
        vpAdapter = new VpAdapter(getSupportFragmentManager(), this);
        vpAdapter.setList(fragmentList);
        vpAdapter.setStringList(titleList);
    }


}
