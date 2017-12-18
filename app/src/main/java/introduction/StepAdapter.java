package introduction;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.day_0508_caipu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import entity.CaiPu;

/**
 * Created by Administrator on 2017/5/10.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.ViewHolder> {
    Context context;
    List<CaiPu.ResultBean.DataBean.StepsBean> stepsBeanList;

    public StepAdapter(Context context) {
        this.context = context;
        stepsBeanList = new ArrayList<CaiPu.ResultBean.DataBean.StepsBean>();
    }

    public List<CaiPu.ResultBean.DataBean.StepsBean> getStepsBeanList() {
        return stepsBeanList;
    }

    public void setStepsBeanList(List<CaiPu.ResultBean.DataBean.StepsBean> stepsBeanList) {
        this.stepsBeanList = stepsBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_step, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(stepsBeanList.get(position).getImg())
                .resize(200, 150)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.itemStepIv);
        holder.itemStepTv.setText(stepsBeanList.get(position).getStep());
    }

    @Override
    public int getItemCount() {
        return stepsBeanList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_step_iv)
        ImageView itemStepIv;
        @BindView(R.id.item_step_tv)
        TextView itemStepTv;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
