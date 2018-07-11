package com.example.ec.main.personal.help;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.core.app.AccountManager;
import com.example.core.app.Latte;
import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;
import com.example.core.net.callback.ISuccess;
import com.example.ec.R;
import com.example.ec.main.personal.list.ListAdapter;
import com.example.ec.main.personal.list.ListBean;
import com.example.ec.main.personal.list.ListItemType;
import com.example.ui.recycler.BaseDecoration;
import com.example.ui.widget.LimitScrollEditText;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author alan
 *         Date  2018/7/4.
 *         Function : 帮助反馈
 *         Issue :
 */

public class AdviceDelegate extends LatteDelegate {

    private RecyclerView mRycHelp;
    private AppCompatTextView mTvCommit;
    private IconTextView iconTextView;

    private LimitScrollEditText mTvAdvice;
    private  int adviceId = 1;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_advice;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        mRycHelp = rootView.findViewById(R.id.rv_user_question);
        mTvCommit = rootView.findViewById(R.id.tv_commit);
        iconTextView = rootView.findViewById(R.id.iv_back_help);
        mTvAdvice = rootView.findViewById(R.id.lsl_advice);

        final ListBean notice = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_CHECK)
                .setId(1)
                .isChecked(false)
                .setValue("功能异常：功能故障或不可用")
                .build();

        final ListBean say = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_CHECK)
                .setId(2)
                .setValue("产品建议:功能不全,产品")
                .build();

        final ListBean  footer = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_CHECK)
                .setId(3)
                .setValue("安全问题")
                .build();

        final ListBean resource = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_CHECK)
                .setId(4)
                .setValue("其它问题")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(notice);
        data.add(say);
        data.add(footer);
        data.add(resource);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRycHelp.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRycHelp.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 2));
        mRycHelp.setAdapter(adapter);


        /**
         * http://192.168.1.186/Increase/AddQuestion
         * post: questionID=1&memberID=1&feedBackContent=搜索功能出现异常，无法搜索到！请及时处理！&time=2018-07-08
         */
        //提交
        mTvCommit.setOnClickListener(v -> {
            String content = mTvAdvice.getText();

            if (content.length()<10){
                Latte.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),"问题意见没有10个字",Toast.LENGTH_SHORT).show();
                    }
                });
                return;
            }

            for(ListBean bean:adapter.getData()){
                if (bean.isCheck()){
                    Log.e("helpS",bean.getValue()+"=====");
                    adviceId =   bean.getId();
                }
            }

            RestClient.builder()
                    .url("http://192.168.1.186/Increase/AddQuestion")
                    .loader(getContext())
                    .params("questionID",adviceId)
                    .params("memberID", AccountManager.getUserId())
                    .params("feedBackContent",content)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            final JSONObject object = JSON.parseObject(response);
                            Log.e("login","login:"+response.toString());
                            String msg = object.getString("msg");
                            Latte.getHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    })
                    .build()
                    .post();
        });

        iconTextView.setOnClickListener(v -> getSupportDelegate().pop());

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {


        return new DefaultHorizontalAnimator();
    }
}
