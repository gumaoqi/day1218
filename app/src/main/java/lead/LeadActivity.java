package lead;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.day_0508_caipu.R;

import java.util.ArrayList;

import base.MyBaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import home.HomeActivity;
import tool.MyAnimUtils;
import tool.ShareTools;
import tool.ToastUtils;

/**
 * Created by Administrator on 2017/5/8.
 */

public class LeadActivity extends MyBaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inidata();
        if (shareTools.loadFirst()) {
            setLayout();
            iniview();
        } else {
            //不是第一次进入。直接跳转进入登录注册页面
            startActivity(HomeActivity.class, null, R.anim.alpha_enter, R.anim.alpha_exit);
            finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        setview();
    }

    @Override
    public void setLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lead);

    }

    @BindView(R.id.lead_vp)
    ViewPager leadVp;
    @BindView(R.id.lead_mainfood_img)
    ImageView leadMainfoodImg;
    @BindView(R.id.lead_mainfood_tv)
    TextView leadMainfoodTv;
    @BindView(R.id.lead_desert_img)
    ImageView leadDesertImg;
    @BindView(R.id.lead_desert_tv)
    TextView leadDesertTv;
    @BindView(R.id.lead_soup_img)
    ImageView leadSoupImg;
    @BindView(R.id.lead_soup_tv)
    TextView leadSoupTv;
    @BindView(R.id.lead_moreperson_img)
    ImageView leadMorepersonImg;
    @BindView(R.id.lead_moreperson_tv)
    TextView leadMorepersonTv;
    @BindView(R.id.lead_jindu_one_img)
    ImageView leadJinduOneImg;
    @BindView(R.id.lead_jindu_two_img)
    ImageView leadJinduTwoImg;
    @BindView(R.id.lead_tag_two_img)
    ImageView leadTagTwoImg;
    @BindView(R.id.lead_skip_btn)
    Button leadSkipBtn;

    @Override
    public void iniview() {
        ButterKnife.bind(this);
    }

    @Override
    public void setview() {
        leadAdapter.setList(list);
        leadVp.setAdapter(leadAdapter);
        leadVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == (leadAdapter.getList().size() - 1)) {
                    leadMainfoodImg.setVisibility(View.VISIBLE);
                    leadMainfoodTv.setVisibility(View.VISIBLE);
                    leadDesertImg.setVisibility(View.VISIBLE);
                    leadDesertTv.setVisibility(View.VISIBLE);
                    leadSoupImg.setVisibility(View.VISIBLE);
                    leadSoupTv.setVisibility(View.VISIBLE);
                    leadMorepersonImg.setVisibility(View.VISIBLE);
                    leadMorepersonTv.setVisibility(View.VISIBLE);
                    leadSkipBtn.setVisibility(View.VISIBLE);
                    leadTagTwoImg.setVisibility(View.GONE);
                    leadJinduOneImg.setBackgroundResource(R.mipmap.lead_normal);
                    leadJinduTwoImg.setBackgroundResource(R.mipmap.lead_select);
                    setAllAnim();

                    leadSkipBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(HomeActivity.class, null, R.anim.alpha_enter, R.anim.alpha_exit);
                            finish();
                        }
                    });
                } else {
                    leadMainfoodImg.setVisibility(View.GONE);
                    leadMainfoodTv.setVisibility(View.GONE);
                    leadDesertImg.setVisibility(View.GONE);
                    leadDesertTv.setVisibility(View.GONE);
                    leadSoupImg.setVisibility(View.GONE);
                    leadSoupTv.setVisibility(View.GONE);
                    leadMorepersonImg.setVisibility(View.GONE);
                    leadMorepersonTv.setVisibility(View.GONE);
                    leadSkipBtn.setVisibility(View.GONE);
                    leadTagTwoImg.setVisibility(View.VISIBLE);
                    leadJinduOneImg.setBackgroundResource(R.mipmap.lead_select);
                    leadJinduTwoImg.setBackgroundResource(R.mipmap.lead_normal);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //先保证滑动到了最后一个页面
                if (leadVp.getCurrentItem() == (leadAdapter.getList().size() - 1)) {
                    switch (state) {
                        case 0://停止
                            if (isFlag) {
                                startActivity(HomeActivity.class, null, R.anim.alpha_enter, R.anim.alpha_exit);
                                finish();
                            }
                            isFlag = false;
                            break;
                        case 1://开始滑动
                            isFlag = true;
                            break;
                        case 2://滑动了
                            isFlag = false;
                            break;
                    }
                }
            }
        });
    }

    public void setAllAnim() {
        if (animFlag) {
            setTranslateAnimation(leadMainfoodImg, 0);
            setTranslateAnimation(leadMainfoodTv, 500);
            setTranslateAnimation(leadDesertImg, 1000);
            setTranslateAnimation(leadDesertTv, 1500);
            setTranslateAnimation(leadSoupImg, 2000);
            setTranslateAnimation(leadSoupTv, 2500);
            setTranslateAnimation(leadMorepersonImg, 3000);
            setTranslateAnimation(leadMorepersonTv, 3500);
            setTranslateAnimation(leadSkipBtn, 4000);
            animFlag = false;
        }
    }

    /**
     * \
     *
     * @param v         要添加动画的控件
     * @param starttime 需要延后的时长
     * @return
     */
    public void setTranslateAnimation(View v, long starttime) {
        AlphaAnimation AlphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        AlphaAnimation.setDuration(1000);
        AlphaAnimation.setStartOffset(starttime);
        v.startAnimation(AlphaAnimation);
    }

    boolean isFlag;
    LeadAdapter leadAdapter;
    ArrayList<View> list;
    ShareTools shareTools;
    Animation animation;
    boolean animFlag;

    @Override
    public void inidata() {
        isFlag = true;
        animFlag = true;
        shareTools = ShareTools.getInstance(this);
        leadAdapter = new LeadAdapter(this);
        list = new ArrayList<View>();
        View view1 = LayoutInflater.from(this).inflate(R.layout.item_lead_vp, null);
        ImageView iv1 = (ImageView) view1.findViewById(R.id.item_lead_vp_img);
        iv1.setBackgroundResource(R.mipmap.lead_backgroun);
        list.add(view1);
        View view2 = LayoutInflater.from(this).inflate(R.layout.item_lead_vp, null);
        ImageView iv2 = (ImageView) view2.findViewById(R.id.item_lead_vp_img);
        iv2.setBackgroundResource(R.mipmap.lead_love);
        list.add(view2);
        animation = MyAnimUtils.loadAnimation(this, R.anim.alpha);
    }


}
