package more;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.day_0508_caipu.R;

import java.util.ArrayList;
import java.util.List;

import base.MyBaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import entity.More;

/**
 * Created by Administrator on 2017/5/11.
 */

public class MoreActivity extends MyBaseActivity {

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
        setContentView(R.layout.activity_more);

    }

    @BindView(R.id.more_tb)
    TabLayout moreTb;
    @BindView(R.id.more_rv)
    RecyclerView moreRv;

    @Override
    public void iniview() {
        ButterKnife.bind(this);


    }

    @Override
    public void setview() {
        moreRv.setLayoutManager(linearLayoutManager);
        moreRv.setAdapter(moreAdapter);
        moreTb.setTabMode(TabLayout.MODE_SCROLLABLE);
        moreTb.setSelectedTabIndicatorColor(Color.YELLOW);

        for (int i = 0; i < moreList.size(); i++) {
            moreTb.addTab(moreTb.newTab().setText(moreList.get(i).getStr1()), i);
        }
        moreTb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                linearLayoutManager.scrollToPositionWithOffset(tab.getPosition(), 0);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        moreRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                moreTb.setScrollPosition(linearLayoutManager.findFirstVisibleItemPosition(), 0.0f, true);
            }
        });
    }

    MoreAdapter moreAdapter;
    List<More> moreList;
    LinearLayoutManager linearLayoutManager;

    @Override
    public void inidata() {
        moreAdapter = new MoreAdapter(this);
        moreList = new ArrayList<More>();
        moreList.add(new More("季节", "春", "夏", "秋", "冬", "", "", "", ""));
        moreList.add(new More("口味", "酸", "甜", "苦", "辣", "咸", "", "", ""));
        moreList.add(new More("做法", "炒", "烧", "蒸", "炸", "煮", "炖", "", ""));
        moreList.add(new More("菜系", "川菜", "粤菜", "浙菜", "湘菜", "鲁菜", "苏菜", "闽菜", "徽菜"));
        moreList.add(new More("分类", "家常菜", "特色菜", "快手菜", "凉菜", "下饭菜", "", "", ""));
        moreList.add(new More("时间", "早餐", "午餐", "晚餐", "宵夜", "", "", "", ""));
        moreList.add(new More("材料", "素菜", "猪肉", "牛肉", "鱼肉", "羊肉", "鸡肉", "鸭肉", "兔肉"));
        moreList.add(new More("类别", "汤", "菜", "甜点", "", "", "", "", ""));
        moreList.add(new More("配菜", "青椒", "木耳", "蘑菇", "青菜", "洋葱", "", "", ""));
        moreList.add(new More("用时", "20分钟内", "20-40分钟", "40-60分钟", "60分钟以上", "", "", "", ""));
        moreAdapter.setList(moreList);
        linearLayoutManager = new LinearLayoutManager(this);
    }
}
