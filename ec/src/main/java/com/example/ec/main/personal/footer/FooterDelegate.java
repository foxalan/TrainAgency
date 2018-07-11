package com.example.ec.main.personal.footer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.core.app.AccountManager;
import com.example.core.app.Latte;
import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;
import com.example.core.net.callback.ISuccess;
import com.example.ec.R;
import com.example.ec.main.personal.PersonalType;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;
import com.joanzapata.iconify.widget.IconTextView;

import java.lang.invoke.LambdaConversionException;
import java.util.List;


/**
 * @author alan
 *         Date  2018/7/9.
 *         Function : 我的足
 *         Issue :
 */

public class FooterDelegate extends LatteDelegate implements ISuccess {

    private String tag = "FooterDelegate";
    private RecyclerView mRycFooter;

    private IconTextView icDelete;
    private LinearLayout linearLayout;
    private TextView mTvDelete;
    private FooterAdapter adapter;
    private IconTextView mIctBack;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_footer;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        mRycFooter = rootView.findViewById(R.id.ryc_footer);
        icDelete = rootView.findViewById(R.id.ic_delete);
        linearLayout = rootView.findViewById(R.id.ll_delete);
        mTvDelete = rootView.findViewById(R.id.tv_delete);

        mIctBack = rootView.findViewById(R.id.ict_back);
        mIctBack.setOnClickListener(v -> getSupportDelegate().pop());

        initData();

        icDelete.setOnClickListener(v -> {
            linearLayout.setVisibility(View.VISIBLE);
            if (adapter != null) {
                List<MultipleItemEntity> itemEntityList = adapter.getData();
                for (int i = 0; i < itemEntityList.size(); i++) {
                    MultipleItemEntity entity = itemEntityList.get(i);
                    if (entity.getField(MultipleFields.PERSONAL_FOOTER_IS_DELETE_ITEM) != null) {
                        itemEntityList.get(i).setField(MultipleFields.PERSONAL_FOOTER_IS_DELETE_ITEM, true);
                    }

                    if (entity.getField(MultipleFields.PERSONAL_FOOTER_IS_DELETE) != null) {
                        itemEntityList.get(i).setField(MultipleFields.PERSONAL_FOOTER_IS_DELETE, true);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        mTvDelete.setOnClickListener(v -> {
            if (adapter != null) {

                List<MultipleItemEntity> itemEntityList = adapter.getData();
                for (int i = 0; i < itemEntityList.size(); i++) {
                    MultipleItemEntity entity = itemEntityList.get(i);
                    switch (entity.getItemType()) {
                        case PersonalType.FOOTER_DATE:

                            break;
                        case PersonalType.FOOTER_ITEM:
                            if(entity.getField(MultipleFields.PERSONAL_FOOTER_SELECT_DELETE_ITEM)){
                                Log.e("footerdelete",entity.getField(MultipleFields.PERSONAL_FOOTER_DATE)+"===");
                                Log.e("footerdelete",entity.getField(MultipleFields.PERSONAL_FOOTER_ID)+"===");
                            }
                            break;
                        default:
                            break;
                    }

                }
                Latte.getHandler().post(() -> adapter.notifyDataSetChanged());
            }
        });

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
        adapter = new FooterAdapter(new FooterDataConveter().setJsonData(response).convert());
        mRycFooter.setAdapter(adapter);
        adapter.setFooterDateListener((isCheck, date) -> {

            if (adapter != null) {
                List<MultipleItemEntity> itemEntityList = adapter.getData();
                for (int i = 0; i < itemEntityList.size(); i++) {
                    MultipleItemEntity entity = itemEntityList.get(i);
                    switch (entity.getItemType()) {
                        case PersonalType.FOOTER_DATE:
                            break;
                        case PersonalType.FOOTER_ITEM:
                            String dateTime = entity.getField(MultipleFields.PERSONAL_FOOTER_DATE);
                            if (date.equals(dateTime)) {
                                Log.e("footerdelete","after:"+date+"==="+dateTime);
                                entity.setField(MultipleFields.PERSONAL_FOOTER_SELECT_DELETE_ITEM, isCheck);
                            }
                            break;
                        default:
                            break;
                    }
                }

                Latte.getHandler().post(() -> adapter.notifyDataSetChanged());
            }
        });
    }
}
