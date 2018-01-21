package com.yann.yanndemo.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yann.yanndemo.R;
import com.yann.yanndemo.entity.SimpleListItemEntity;

/**
 * Created by wyy on 2018/1/20.
 */

public class DialogSelectorAdapter extends BaseQuickAdapter<SimpleListItemEntity, BaseViewHolder> {

    private static final String TAG = DialogSelectorAdapter.class.getSimpleName();
    private OnItemClickListener mOnItemClickListener;
    private int isSingle = 1;    //为1的话为单选，默认为单选，否则的话为多选
    public DialogSelectorAdapter(@LayoutRes int layoutResId, int type) {
        super(layoutResId);
        this.isSingle = type;
    }

    public void setOnBranchItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, SimpleListItemEntity item, final int position) {
        RelativeLayout rootView = helper.getView(R.id.rl_branch_item_root);
        TextView name = helper.getView(R.id.txt_tree_name);
        CheckBox cb_contact = helper.getView(R.id.cb_contact);
        RadioButton rb_contact = helper.getView(R.id.rb_contact);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(position);
            }
        });
        name.setText(item.getName());
        if (isSingle == 1) {
            rb_contact.setChecked(item.isSelector());
        } else {
            cb_contact.setVisibility(View.VISIBLE);
            rb_contact.setVisibility(View.GONE);
            cb_contact.setChecked(item.isSelector());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
