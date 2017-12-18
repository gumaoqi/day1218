package find;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.day_0508_caipu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import entity.CaiPu;
import introduction.IntroActivity;

/**
 * Created by Administrator on 2017/5/9.
 */

public class CaiPuAdapter extends RecyclerView.Adapter<CaiPuAdapter.ViewHolder> {
    Context context;
    List<CaiPu.ResultBean.DataBean> list;

    public CaiPuAdapter(Context context) {
        this.context = context;
        list = new ArrayList<CaiPu.ResultBean.DataBean>();
    }

    public List<CaiPu.ResultBean.DataBean> getList() {
        return list;
    }

    public void setList(List<CaiPu.ResultBean.DataBean> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_rv_caipu, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    int myPosition;

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        myPosition = position;
        Picasso.with(context).load(list.get(position).getAlbums().get(0))
                .resize(200, 150)
                .centerCrop()
                .placeholder(R.mipmap.app_icon)
                .error(R.mipmap.app_icon)
                .into(holder.itemHomeRvCaipuCi);
        holder.itemView.setTag(list.get(position));
        holder.itemHomeRvCaipuTvName.setText(position + 1 +":"+ list.get(position).getTitle());
        holder.itemHomeRvCaipuTvTime.setText(list.get(position).getIngredients());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("pass", (CaiPu.ResultBean.DataBean) v.getTag());
                Intent intent = new Intent(context, IntroActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_home_rv_caipu_ci)
        CircleImageView itemHomeRvCaipuCi;
        @BindView(R.id.item_home_rv_caipu_tv_name)
        TextView itemHomeRvCaipuTvName;
        @BindView(R.id.item_home_rv_caipu_tv_time)
        TextView itemHomeRvCaipuTvTime;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
