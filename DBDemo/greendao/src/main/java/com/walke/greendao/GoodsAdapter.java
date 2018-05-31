package com.walke.greendao;

import android.content.Context;

import com.walke.greendao.base.RVBaseAdapter;
import com.walke.greendao.base.RVBaseViewHolder;
import com.walke.greendao.bean.Goods;

import java.util.List;

/**
 * Created by walke.Z on 2018/5/31.
 */

public class GoodsAdapter extends RVBaseAdapter<Goods> {

    public GoodsAdapter(Context context, List<Goods> goodses, int layoutid) {
        super(context,goodses,layoutid);
    }

    @Override
    protected void itemView(Context context, RVBaseViewHolder holder, Goods goods) {
        if (goods==null)
            return;
        holder.setImage(R.id.lig_img,goods.getImg_url());
        holder.setText(R.id.lig_title,goods.getName());
        holder.setText(R.id.lig_sold,"已售"+goods.getSell_num()+"件");
        holder.setText(R.id.lig_price,"￥"+goods.getPrice());
        holder.setText(R.id.lig_Id,"数据库id："+goods.getId());
    }


}
