package com.hui.mvptest.home.view.fragment.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hui.mvptest.R;
import com.hui.mvptest.base.fragment.AppFragment;
import com.hui.mvptest.util.SetUtil;

/**
 * Created by walke.Z on 2017/8/2.
 */

public class ChildFragment extends AppFragment {

    private static ChildFragment mChildFragment;
    private TextView mTextView;
    private RecyclerView mRecyclerView;

    public static ChildFragment getChildFragment(String title,String[] arr){
        //Java.lang.IllegalStateException: Can't change tag of fragment fendo1MainActivity{531dbce4 id=0x7f0b0054
        // Android:switcher:2131427412:0}: was android:switcher:2131427412:0 now android:switcher:2131427412:1
        //if (mChildFragment==null)
            //mChildFragment=new ChildFragment();
        mChildFragment=new ChildFragment();
        Bundle args=new Bundle();
        args.putString("title",title);
        args.putStringArray("strArr",arr);
        mChildFragment.setArguments(args);
        return mChildFragment;
    }


    @Override
    protected int rootLayoutId() {
        return R.layout.fragment_child;
    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {
        mTextView = ((TextView) rootView.findViewById(R.id.fc_text));
        mRecyclerView = ((RecyclerView) rootView.findViewById(R.id.fc_recyclerView));
        Bundle arguments = getArguments();
        if (arguments!=null){
            String title = arguments.getString("title", "title");
            String[] strArrs = arguments.getStringArray("strArr");
            mTextView.setText(title);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(layoutManager);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), SetUtil.arrToList(strArrs));
            mRecyclerView.setAdapter(adapter);
            adapter.setItemClickListener(new RecyclerViewAdapter.onItemClickListener() {
                @Override
                public void onClick(int position) {

                }
            });
        }

    }

    @Override
    protected void initData() {

    }

}
