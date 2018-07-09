package com.example.ec.main.personal.footer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.core.app.AccountManager;
import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;
import com.example.core.net.callback.ISuccess;
import com.example.ec.R;
import com.example.ec.main.personal.PersonalType;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;


/**
 * @author alan
 *         Date  2018/7/9.
 *         Function : 我的足
 *         Issue :
 */

public class FooterDelegate extends LatteDelegate implements ISuccess {

    private String tag = "FooterDelegate";
    private RecyclerView mRycFooter;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_footer;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRycFooter = rootView.findViewById(R.id.ryc_footer);
        initData();
    }

    /**
     * http://192.168.1.186/Select/BrowsingHistory?userid=5
     */
    private void initData() {
        RestClient.builder()
                .url("BrowsingHistory")
                .params("userid", AccountManager.getUserId())
                .success(this)
                .build()
                .get();

    }

    @Override
    public void onSuccess(String response) {
        //设置RecyclerView
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        mRycFooter.setLayoutManager(manager);
        final FooterAdapter adapter = new FooterAdapter(new FooterDataConveter().setJsonData(response).convert());
        mRycFooter.setAdapter(adapter);
        mRycFooter.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final MultipleItemEntity entity = (MultipleItemEntity) adapter.getData().get(position);
                switch (entity.getItemType()) {
                    case PersonalType.FOOTER_ITEM:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                final MultipleItemEntity entity = (MultipleItemEntity) adapter.getData().get(position);
                switch (entity.getItemType()) {
                    case PersonalType.FOOTER_DATE:
                        entity.setField(MultipleFields.PERSONAL_FOOTER_IS_DELETE, true);
                        adapter.notifyItemChanged(position);
                        break;
                    case PersonalType.FOOTER_ITEM:
                        entity.setField(MultipleFields.PERSONAL_FOOTER_IS_DELETE_ITEM, true);
                        adapter.notifyItemChanged(position);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }
}
