package com.yann.yanndemo.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.helper.RecyclerViewHelper;
import com.yann.yanndemo.R;
import com.yann.yanndemo.adapter.DialogSelectorAdapter;
import com.yann.yanndemo.entity.SimpleListItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyy on 2018/1/20.
 */

public class DialogSelector extends AlertDialog implements DialogSelectorAdapter.OnItemClickListener {
    private RecyclerView rv_selector_branch;
    private OnSelectorListener cdListener;
    private List<SimpleListItemEntity> mSimpleListItemEntity = new ArrayList<>();
    private List<SimpleListItemEntity> selectorSimpleListItemEntity = new ArrayList<>();
    private DialogSelectorAdapter mSelectorBranchAdapter;
    private Context context;
    private Button btn_cancel;
    private Button btn_ok;
    private String isProcessVariable;

    public DialogSelector(Context context, List<SimpleListItemEntity> mSimpleListItemEntity, String isProcessVariable, OnSelectorListener cdListener) {
        super(context);
        this.context = context;
        this.cdListener = cdListener;
        this.mSimpleListItemEntity = mSimpleListItemEntity;
        this.isProcessVariable = isProcessVariable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_selector);
        this.setCanceledOnTouchOutside(true); // 点击外部会消失
        InitViews();
    }

    private void InitViews() {
        rv_selector_branch = (RecyclerView) findViewById(R.id.rv_selector_branch);
        mSelectorBranchAdapter = new DialogSelectorAdapter(R.layout.selector_branch_item, isProcessVariable.equals("1") ? 1 : 0);
        mSelectorBranchAdapter.setOnBranchItemClickListener(this);
        RecyclerViewHelper.initRecyclerViewV(context, rv_selector_branch, true, mSelectorBranchAdapter);
        mSelectorBranchAdapter.setNewData(mSimpleListItemEntity);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdListener.cancel();
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mSimpleListItemEntity.size(); i++) {
                    if (mSimpleListItemEntity.get(i).isSelector()) {
                        selectorSimpleListItemEntity.add(mSimpleListItemEntity.get(i));
                    }
                }
                cdListener.getSelectorData(selectorSimpleListItemEntity);

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        if (isProcessVariable.equals("1")) {
            for (int i = 0; i < mSimpleListItemEntity.size(); i++) {
                if (i == position) {
                    if (!mSimpleListItemEntity.get(i).isSelector()) {
                        mSimpleListItemEntity.get(i).setSelector(true);
                    }
                } else {
                    mSimpleListItemEntity.get(i).setSelector(false);
                }
            }
        } else {
            boolean temp = !mSimpleListItemEntity.get(position).isSelector();
            mSimpleListItemEntity.get(position).setSelector(temp);
        }
        mSelectorBranchAdapter.setNewData(mSimpleListItemEntity);
    }

    public interface OnSelectorListener {
        void getSelectorData(List<SimpleListItemEntity> trees);

        void cancel();
    }
}
