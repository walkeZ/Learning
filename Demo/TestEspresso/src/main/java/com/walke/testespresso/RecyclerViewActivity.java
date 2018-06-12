package com.walke.testespresso;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by walke.Z on 2018/6/12.
 */

public class RecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 30; i++)
            data.add("Item " + i);
        MRecAdapter adapter = new MRecAdapter(this, data);
        mRecyclerView.setAdapter(adapter);


    }


    public class MRecAdapter extends RecyclerView.Adapter<MRecAdapter.RecViewHolder> {

        private List<String> data = new ArrayList<>();
        private Context context;

        public MRecAdapter(Context context, List<String> data) {
            this.context = context;
            this.data.addAll(data);
        }

        @Override
        public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycleview, parent, false));
        }

        @Override
        public void onBindViewHolder(RecViewHolder holder, final int position) {
            holder.tvItem.setText(data.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context).setTitle(data.get(position))
                            .setMessage("this is dialog")
                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class RecViewHolder extends RecyclerView.ViewHolder {

            public TextView tvItem;

            public RecViewHolder(View itemView) {
                super(itemView);
                tvItem = (TextView) itemView.findViewById(R.id.tv_item);
            }
        }

    }

}
