package more;

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
import entity.More;
import find.FindActivity;

/**
 * Created by Administrator on 2017/5/11.
 */

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.ViewHolder> {
    Context context;
    List<More> list;

    public MoreAdapter(Context context) {
        this.context = context;
        list = new ArrayList<More>();

    }

    public List<More> getList() {
        return list;
    }

    public void setList(List<More> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_more, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemMoreTvOne.setText(list.get(position).getStr1());
        holder.itemMoreTvTwo.setText(list.get(position).getStr2());
        holder.itemMoreTvThree.setText(list.get(position).getStr3());
        holder.itemMoreTvFour.setText(list.get(position).getStr4());
        holder.itemMoreTvFive.setText(list.get(position).getStr5());
        holder.itemMoreTvSix.setText(list.get(position).getStr6());
        holder.itemMoreTvSeven.setText(list.get(position).getStr7());
        holder.itemMoreTvEight.setText(list.get(position).getStr8());
        holder.itemMoreTvNine.setText(list.get(position).getStr9());
        holder.itemMoreTvTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((TextView) v).getText().toString().equals("")) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("pass", ((TextView) v).getText().toString());
                Intent intent = new Intent(context, FindActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.itemMoreTvThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((TextView) v).getText().toString().equals("")) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("pass", ((TextView) v).getText().toString());
                Intent intent = new Intent(context, FindActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.itemMoreTvFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((TextView) v).getText().toString().equals("")) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("pass", ((TextView) v).getText().toString());
                Intent intent = new Intent(context, FindActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.itemMoreTvFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((TextView) v).getText().toString().equals("")) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("pass", ((TextView) v).getText().toString());
                Intent intent = new Intent(context, FindActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.itemMoreTvSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((TextView) v).getText().toString().equals("")) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("pass", ((TextView) v).getText().toString());
                Intent intent = new Intent(context, FindActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.itemMoreTvSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((TextView) v).getText().toString().equals("")) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("pass", ((TextView) v).getText().toString());
                Intent intent = new Intent(context, FindActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.itemMoreTvEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((TextView) v).getText().toString().equals("")) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("pass", ((TextView) v).getText().toString());
                Intent intent = new Intent(context, FindActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.itemMoreTvNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((TextView) v).getText().toString().equals("")) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("pass", ((TextView) v).getText().toString());
                Intent intent = new Intent(context, FindActivity.class);
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
        @BindView(R.id.item_more_tv_one)
        TextView itemMoreTvOne;
        @BindView(R.id.item_more_tv_two)
        TextView itemMoreTvTwo;
        @BindView(R.id.item_more_tv_three)
        TextView itemMoreTvThree;
        @BindView(R.id.item_more_tv_four)
        TextView itemMoreTvFour;
        @BindView(R.id.item_more_tv_five)
        TextView itemMoreTvFive;
        @BindView(R.id.item_more_tv_six)
        TextView itemMoreTvSix;
        @BindView(R.id.item_more_tv_seven)
        TextView itemMoreTvSeven;
        @BindView(R.id.item_more_tv_eight)
        TextView itemMoreTvEight;
        @BindView(R.id.item_more_tv_nine)
        TextView itemMoreTvNine;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
