package find;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.day_0508_caipu.R;

import java.util.ArrayList;
import java.util.List;

import base.MyBaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entity.CaiPu;
import home.HomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tool.ToastUtils;

/**
 * Created by Administrator on 2017/5/11.
 */

public class FindActivity extends MyBaseActivity {


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
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activitty_find);

    }

    @BindView(R.id.find_tb)
    Toolbar findTb;
    @BindView(R.id.find_rec)
    RecyclerView findRec;
    @BindView(R.id.find_srl)
    SwipeRefreshLayout findSrl;
    @BindView(R.id.find_tv)
    TextView findTv;

    @Override
    public void iniview() {
        ButterKnife.bind(this);
    }

    LinearLayoutManager mLinearLayoutManager;

    @Override
    public void setview() {
        findTb.setTitle("加载中");
        findTb.setBackgroundColor(Color.YELLOW);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        findRec.setLayoutManager(mLinearLayoutManager);
        findRec.setAdapter(caiPuAdapter);
        count = 1;
        total = 0;
        getDataBean("肉", count + "");
        findSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                findTb.setTitle("加载中");
                count = 1;
                shanglaFlag = true;
                getDataBean("肉", count + "");
            }
        });
        findRec.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int previousTotal = 0;
            private boolean loading = true;
            int firstVisibleItem, visibleItemCount, totalItemCount;

            private int currentPage = 1;


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = mLinearLayoutManager.getItemCount();
                Log.i("123", "onScrolled: " + recyclerView.getChildCount() + "--" + mLinearLayoutManager.getItemCount());
                firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading
                        && (totalItemCount - visibleItemCount) <= firstVisibleItem && dataBeanList.size() > 0) {
                    currentPage++;
                    findTb.setTitle("加载中");
                    getDataBean("肉", count + "");
                    loading = true;
                }
            }
        });
    }

    List<CaiPu.ResultBean.DataBean> dataBeanList;//存储返回数据的集合
    String name = "";
    Handler handler;
    boolean flag = false;//判断是否是第一次请求数据
    boolean shanglaFlag = false;
    CaiPuAdapter caiPuAdapter;

    @Override
    public void inidata() {
        dataBeanList = new ArrayList<CaiPu.ResultBean.DataBean>();
        Intent intent = getIntent();
        name = intent.getExtras().getString("pass");
        caiPuAdapter = new CaiPuAdapter(this);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                findRec.setAdapter(caiPuAdapter);
                findTb.setTitle(name);
                findSrl.setRefreshing(false);
                findTv.setText("下拉加载更多");
                mLinearLayoutManager.scrollToPositionWithOffset(total, 0);
                return true;
            }
        });
    }

    int count = 1;//当前请求到第几条数据
    int total = 0;//当前显示到第几条数据

    /**
     * @param namea 搜索的关键字
     * @param pn    从第几条数据开始返回
     */
    public void getDataBean(String namea, String pn) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.juhe.cn/")//★这里最后面必须能带“/”
                .addConverterFactory(GsonConverterFactory.create())//设置将json解析为javabean所用的方式
                .build();
        HomeActivity.CaiPuService caiPuService =
                retrofit.create(HomeActivity.CaiPuService.class);
        Call<CaiPu> call = caiPuService.getCaiPu("肉", "7ccd31e7b7e9881019afc7480e0aca22", pn);
        call.enqueue(new Callback<CaiPu>() {
            @Override
            public void onResponse(Call<CaiPu> call, Response<CaiPu> response) {
                CaiPu body = response.body();
                String result = body.getResultcode();
                CaiPu.ResultBean resultBean = body.getResult();
                resultBean.getTotalNum();
                if (shanglaFlag) {
                    dataBeanList = new ArrayList<CaiPu.ResultBean.DataBean>();
                    total = 0;
                    shanglaFlag = false;
                } else {
                    total = dataBeanList.size() - 5;
                }
                for (CaiPu.ResultBean.DataBean d : resultBean.getData()) {
                    if (d.getTags().contains(name)) {
                        dataBeanList.add(d);
                    }
                }
                caiPuAdapter.setList(dataBeanList);
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

    @OnClick(R.id.find_tv)
    public void onClick() {
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
}
