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
import java.util.ArrayList;
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
                    adapter.notifyItemChanged(i);
                }
            }
        });

        List<MultipleItemEntity> deleteList = new ArrayList<>();

        mTvDelete.setOnClickListener(v -> {
            if (adapter != null) {
                deleteList.clear();
                List<MultipleItemEntity> itemEntityList = adapter.getData();
                for (int i = (itemEntityList.size()-1); i >=0; i = i -1) {
                    MultipleItemEntity entity = itemEntityList.get(i);
                    switch (entity.getItemType()) {
                        case PersonalType.FOOTER_DATE:
                            if (entity.getField(MultipleFields.PERSONAL_FOOTER_SELECT_DELETE)){
                                adapter.remove(i);
                            }
                            break;
                        case PersonalType.FOOTER_ITEM:
                            if (entity.getField(MultipleFields.PERSONAL_FOOTER_SELECT_DELETE_ITEM)) {
                                deleteList.add(entity);
                                adapter.remove(i);
                            }
                            break;
                        default:
                            break;
                    }

                    adapter.notifyItemChanged(i);
                }

                String jsonData = getJsonData(deleteList);

                Log.e("browse",jsonData);

                RestClient.builder()
                        .url("http://192.168.1.186/Remove/DelBrowsingHistory")
                        .loader(getContext())
                        .params("heatlist",jsonData)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                Log.e("browse",response);
                            }
                        })
                        .build()
                        .post();

                //

                linearLayout.setVisibility(View.GONE);
                if (adapter != null) {
                    List<MultipleItemEntity> itemEntityLists = adapter.getData();
                    for (int i = 0; i < itemEntityLists.size(); i++) {
                        MultipleItemEntity entity = itemEntityList.get(i);
                        if (entity.getField(MultipleFields.PERSONAL_FOOTER_IS_DELETE_ITEM) != null) {
                            itemEntityList.get(i).setField(MultipleFields.PERSONAL_FOOTER_IS_DELETE_ITEM, false);
                        }

                        if (entity.getField(MultipleFields.PERSONAL_FOOTER_IS_DELETE) != null) {
                            itemEntityList.get(i).setField(MultipleFields.PERSONAL_FOOTER_IS_DELETE, false);
                        }
                        adapter.notifyItemChanged(i);
                    }
                }

            }
        });

    }

    /**
     *  {"heatlist": [{"heatTarget":0,"heatType":1,"targerID":0,"memberID":1,"time":"2018-07-10"}]}
     * @param deleteList
     * @return
     */

    private String getJsonData(List<MultipleItemEntity> deleteList) {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{"+"\""+"heatlist"+"\""+":");

        stringBuffer.append("[");
        for(int i = 0 ;i<deleteList.size();i++){
            MultipleItemEntity entity = deleteList.get(i);
            stringBuffer.append("{");
            stringBuffer.append("\""+"heatTarget"+"\""+":"+entity.getField(MultipleFields.PERSONAL_FOOTER_HEATTARGET)+",");
            stringBuffer.append("\""+"heatType"+"\""+":"+1+",");
            stringBuffer.append("\""+"targetID"+"\""+":"+entity.getField(MultipleFields.PERSONAL_FOOTER_TARGERID)+",");
            stringBuffer.append("\""+"time"+"\""+":"+entity.getField(MultipleFields.PERSONAL_FOOTER_DATE));

            stringBuffer.append("}");
            if (i != (deleteList.size()-1)){
                stringBuffer.append(",");
            }
        }
        stringBuffer.append("]");

        stringBuffer.append("}");
        return stringBuffer.toString();
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

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                List<MultipleItemEntity> entities = adapter.getData();
                int size = entities.size();
                boolean isCheck = entities.get(position).getField(MultipleFields.PERSONAL_FOOTER_SELECT_DELETE);
                entities.get(position).setField(MultipleFields.PERSONAL_FOOTER_SELECT_DELETE, isCheck);
                adapter.notifyItemChanged(position);

                for (int i = position + 1; i < size; i++) {
                    MultipleItemEntity entity = entities.get(i);
                    if (entity.getItemType() == PersonalType.FOOTER_ITEM) {
                        entity.setField(MultipleFields.PERSONAL_FOOTER_SELECT_DELETE_ITEM, isCheck);
                        adapter.notifyItemChanged(i);
                    } else {
                        break;
                    }
                }
            }
        });
    }
}
