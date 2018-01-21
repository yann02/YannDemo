package com.yann.yanndemo.ui.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yann.yanndemo.R;
import com.yann.yanndemo.dialog.DialogHelper;
import com.yann.yanndemo.entity.SimpleListItemEntity;

import java.util.ArrayList;
import java.util.List;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;

public class DialogActivity extends Activity implements View.OnClickListener, DialogHelper.OnHelperSelectorListener {
    private Button btn_dialog_selector;

    //选择弹出框
    private List<SimpleListItemEntity> mSimpleListItemEntity = new ArrayList<>();
    private String selectorBranchName = "";
    private String branchNames = "";
    private static final String isProcessVariable = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView();
        initData();
    }

    private void initData() {
        for (int i = 0; i < 6; i++) {
            SimpleListItemEntity simpleListItemEntity = new SimpleListItemEntity();
            simpleListItemEntity.setId(i + "");
            simpleListItemEntity.setName("星期" + i);
            mSimpleListItemEntity.add(simpleListItemEntity);
        }

    }

    private void initView() {
        btn_dialog_selector = (Button) findViewById(R.id.btn_dialog_selector);
        btn_dialog_selector.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_selector:
                DialogHelper.create(this).setIsProcessVariable(isProcessVariable).setSelectorData(mSimpleListItemEntity).setSelectorListener(this)
                        .showSelectorDialog();
                break;
            default:
                break;
        }
    }

    /**
     * 获取选择弹出框返回的数据
     *
     * @param trees
     */
    @Override
    public void getSelectorPosition(List<SimpleListItemEntity> trees) {
        StringBuilder branchIdBuilder = new StringBuilder();
        StringBuilder branchNameBuilder = new StringBuilder();
        if (!selectorBranchName.equals(" ")) {
            selectorBranchName = " ";
        }
        branchNames = "";
        if (trees != null && !trees.isEmpty()) {
            for (int i = 0; i < trees.size(); i++) {
                String branchId = trees.get(i).getId();
                if (i == 0) {
                    branchIdBuilder.append(branchId);
                    branchNameBuilder.append(trees.get(i).getName());
                } else {
                    branchIdBuilder.append("," + branchId);
                    branchNameBuilder.append("," + trees.get(i).getName());
                }
                for (int j = 0; j < mSimpleListItemEntity.size(); j++) {
                    if (branchId.equals(mSimpleListItemEntity.get(j).getId())) {
                        mSimpleListItemEntity.get(j).setSelector(true);
                    }
                }
            }
            branchNames = branchNameBuilder.toString();
            selectorBranchName = " " + branchNames;
        }
        Toast.makeText(this, selectorBranchName, Toast.LENGTH_LONG).show();
        Log.d(TAG, "selectorBranchName===" + selectorBranchName);
    }
}
