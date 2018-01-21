package com.yann.yanndemo.dialog;

import android.content.Context;

import com.yann.yanndemo.R;
import com.yann.yanndemo.entity.SimpleListItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyy on 2018/1/21.
 */

public class DialogHelper {
    private static final String TAG = DialogHelper.class.getSimpleName();
    private Context context;
    private int style = R.style.CustomDialog;//默认style
    private String title = "";//标题
    private List<SimpleListItemEntity> mSimpleListItemEntity = new ArrayList<>();    //选择列表的数据
    private OnHelperSelectorListener onSelectorListener;
    private DialogSelector mDialogSelector;
    private String isProcessVariable;    //判断单选还是多选

    private DialogHelper(Context context) {
        this.context = context;
    }

    /**
     * 初始化
     *
     * @param context
     * @return
     */
    public static DialogHelper create(Context context) {
        return new DialogHelper(context);
    }

    /**
     * 可选，设置dialog的style，一般情况下用默认的
     *
     * @param style
     * @return
     */
    public DialogHelper style(int style) {
        this.style = style;
        return this;
    }

    /**
     * 设置标题，建议使用的时候都配置
     *
     * @param title
     * @return
     */
    public DialogHelper title(String title) {
        this.title = title;
        return this;
    }

    /**
     * 设置部门列表的数据
     *
     * @param contactTreeList
     * @return
     */
    public DialogHelper setSelectorData(List<SimpleListItemEntity> contactTreeList) {
        this.mSimpleListItemEntity = contactTreeList;
        return this;
    }

    /**
     * 设置选择部门监听
     *
     * @param onSelectorListener
     * @return
     */
    public DialogHelper setSelectorListener(OnHelperSelectorListener onSelectorListener) {
        this.onSelectorListener = onSelectorListener;
        return this;
    }

    public DialogHelper setIsProcessVariable(String isProcessVariable) {
        this.isProcessVariable = isProcessVariable;
        return this;
    }

    public void showSelectorDialog() {
        mDialogSelector = new DialogSelector(context, mSimpleListItemEntity, isProcessVariable, new DialogSelector.OnSelectorListener() {
            @Override
            public void getSelectorData(List<SimpleListItemEntity> trees) {
                mDialogSelector.dismiss();
                onSelectorListener.getSelectorPosition(trees);
            }

            @Override
            public void cancel() {
                mDialogSelector.dismiss();
            }
        });
        mDialogSelector.setCancelable(false);
        mDialogSelector.show();
    }

    public interface OnHelperSelectorListener {
        void getSelectorPosition(List<SimpleListItemEntity> trees);
    }

}
