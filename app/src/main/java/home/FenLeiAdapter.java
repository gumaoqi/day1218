package home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.day_0508_caipu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import entity.CaiPu;
import entity.FenLei;
import find.FindActivity;
import introduction.IntroActivity;
import more.MoreActivity;

/**
 * Created by Administrator on 2017/5/9.
 */

public class FenLeiAdapter extends RecyclerView.Adapter<FenLeiAdapter.ViewHolder> {
    Context context;
    List<FenLei> list;

    public FenLeiAdapter(Context context) {
        this.context = context;
        list = new ArrayList<FenLei>();
    }

    public List<FenLei> getList() {
        return list;
    }

    public void setList(List<FenLei> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_rv_fenlei, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemHomeRvCiOne.setImageResource(list.get(3 * position).getImgID());
        holder.itemHomeRvTvOne.setTag(list.get(3 * position).getName());
        holder.itemHomeRvTvOne.setText(list.get(3 * position).getName());
        holder.itemHomeRvTvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("pass", (String) v.getTag());
                Intent intent = new Intent(context, FindActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.itemHomeRvCiTwo.setImageResource(list.get(3 * position + 1).getImgID());
        holder.itemHomeRvTvTwo.setTag(list.get(3 * position + 1).getName());
        holder.itemHomeRvTvTwo.setText(list.get(3 * position + 1).getName());
        holder.itemHomeRvTvTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("pass", (String) v.getTag());
                Intent intent = new Intent(context, FindActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.itemHomeRvCiThree.setImageResource(list.get(3 * position + 2).getImgID());
        holder.itemHomeRvTvThree.setTag(list.get(3 * position + 2).getName());
        holder.itemHomeRvTvThree.setText(list.get(3 * position + 2).getName());
        holder.itemHomeRvTvThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((String) v.getTag()).equals("更多")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("pass", (String) v.getTag());
                    Intent intent = new Intent(context, MoreActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("pass", (String) v.getTag());
                    Intent intent = new Intent(context, FindActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (list.size() + 2) / 3;
    }

    /*
    创建的viewholder，一行有三个图片和文本
    需要集合中3个数据填充一行
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_home_rv_ci_one)
        CircleImageView itemHomeRvCiOne;
        @BindView(R.id.item_home_rv_tv_one)
        TextView itemHomeRvTvOne;
        @BindView(R.id.item_home_rv_ci_two)
        CircleImageView itemHomeRvCiTwo;
        @BindView(R.id.item_home_rv_tv_two)
        TextView itemHomeRvTvTwo;
        @BindView(R.id.item_home_rv_ci_three)
        CircleImageView itemHomeRvCiThree;
        @BindView(R.id.item_home_rv_tv_three)
        TextView itemHomeRvTvThree;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
