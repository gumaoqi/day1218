package home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.day_0508_caipu.R;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import base.MyBaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import entity.CaiPu;
import entity.FenLei;
import introduction.IntroActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tool.ShareTools;
import tool.ToastUtils;

/**
 * Created by Administrator on 2017/5/8.
 */

public class HomeActivity extends MyBaseActivity {
    final String TAG = "HomeActivity";


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
        setContentView(R.layout.activity_home);
    }

    @BindView(R.id.home_toolbar)
    Toolbar homeToolbar;
    @BindView(R.id.home_banner)
    XBanner homeBanner;
    @BindView(R.id.home_rv_fenlei)
    RecyclerView homeRvFenlei;
    @BindView(R.id.home_rv_caipu)
    RecyclerView homeRvCaipu;
    @BindView(R.id.home_navigationview)
    NavigationView homeNavigationview;
    @BindView(R.id.home_drawerLayout)
    DrawerLayout homeDrawerLayout;

    @Override
    public void iniview() {
        ButterKnife.bind(this);
    }

    Handler handler;

    @Override
    public void setview() {
        homeToolbar.setTitle("家常菜谱");
        actionBarDrawerToggle = new ActionBarDrawerToggle
                (this, homeDrawerLayout, homeToolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        homeRvFenlei.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        homeRvFenlei.setAdapter(fenLeiAdapter);
        homeRvCaipu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    /*
    菜谱请求接口，返回类型为菜谱，需要传递两个参数
     */
    public interface CaiPuService {
        @GET("cook/query.php")
        Call<CaiPu> getCaiPu(@Query("menu") String menu,
                             @Query("key") String key,
                             @Query("pn") String pn
        );
    }

    List<CaiPu.ResultBean.DataBean> dataBeanList;//存储返回数据的集合
    int count = 1;//当前请求到第几条数据

    /**
     * 请求数据方法
     *
     * @param name 搜索的关键字
     * @param pn   从第几条数据开始返回
     * @return 返回的数据
     */
    public void getDataBean(String name, String pn) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.juhe.cn/")//★这里最后面必须能带“/”
                .addConverterFactory(GsonConverterFactory.create())//设置将json解析为javabean所用的方式
                .build();
        CaiPuService caiPuService =
                retrofit.create(CaiPuService.class);
        Call<CaiPu> call = caiPuService.getCaiPu(name, "7ccd31e7b7e9881019afc7480e0aca22", pn);
        call.enqueue(new Callback<CaiPu>() {
            @Override
            public void onResponse(Call<CaiPu> call, Response<CaiPu> response) {
                CaiPu body = response.body();
                String result = body.getResultcode();
                CaiPu.ResultBean resultBean = body.getResult();
                resultBean.getTotalNum();
                dataBeanList = resultBean.getData();

                count += 30;
                Message message = handler.obtainMessage();
                message.what = 1;
                handler.sendMessage(message);
                call.cancel();
            }

            @Override
            public void onFailure(Call<CaiPu> call, Throwable t) {
                dataBeanList = new ArrayList<CaiPu.ResultBean.DataBean>();
            }
        });
    }

    ActionBarDrawerToggle actionBarDrawerToggle;
    ShareTools shareTools;
    List<FenLei> fenLeiList;
    FenLeiAdapter fenLeiAdapter;
    List<CaiPu> caiPuList;
    CaiPuAdapter caiPuAdapter;
    List<String> imgList;//xbanner图片集合
    List<String> titleList;//xbanner标题集合

    @Override
    public void inidata() {
        shareTools = ShareTools.getInstance(this);
        shareTools.saveFirst();
        fenLeiList = new ArrayList<FenLei>();
        fenLeiList.add(new FenLei(R.mipmap.home_1, "家常菜"));
        fenLeiList.add(new FenLei(R.mipmap.home_2, "快手菜"));
        fenLeiList.add(new FenLei(R.mipmap.home_3, "创意菜"));
        fenLeiList.add(new FenLei(R.mipmap.home_4, "素菜"));
        fenLeiList.add(new FenLei(R.mipmap.home_5, "凉菜"));
        fenLeiList.add(new FenLei(R.mipmap.food_poole, "更多"));
        fenLeiAdapter = new FenLeiAdapter(this);
        fenLeiAdapter.setList(fenLeiList);
        caiPuAdapter = new CaiPuAdapter(this);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                caiPuAdapter.setList(dataBeanList);
                homeRvCaipu.setAdapter(caiPuAdapter);
                imgList = new ArrayList<String>();
                titleList = new ArrayList<String>();
                for (CaiPu.ResultBean.DataBean d : dataBeanList) {
                    if (imgList.size() < 5) {//取出5条数据显示
                        imgList.add(d.getAlbums().get(0));
                        titleList.add(d.getTitle());
                    }
                }
                homeBanner.setData(imgList, titleList);// 为XBanner绑定数据
                homeBanner.setmAdapter(new XBanner.XBannerAdapter() {// XBanner适配数据
                    @Override
                    public void loadBanner(XBanner banner, View view, int position) {
                        Glide.with(HomeActivity.this).load(imgList.get(position)).into((ImageView) view);
                    }
                });
                // 设置XBanner的页面切换特效
                homeBanner.setPageTransformer(Transformer.Default);
                // 设置XBanner页面切换的时间，即动画时长
                homeBanner.setPageChangeDuration(1000);
                // XBanner中某一项的点击事件
                homeBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                    @Override
                    public void onItemClick(XBanner banner, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("pass", dataBeanList.get(position));
                        startActivity(IntroActivity.class, bundle, 0, 0);
                    }
                });
                return true;
            }
        });
        getDataBean("炒肉", "1");
    }

}
