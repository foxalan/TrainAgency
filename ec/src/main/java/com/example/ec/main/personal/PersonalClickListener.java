package com.example.ec.main.personal;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.core.delegate.LatteDelegate;
import com.example.ec.main.personal.footer.FooterDelegate;
import com.example.ec.main.personal.list.ListBean;
import com.example.ec.main.personal.notice.NoticeDelegate;
import com.example.ec.main.personal.userinfo.UserInfoDelegate;

import static me.yokeyword.fragmentation.ISupportFragment.SINGLETASK;

/**
 * @author alan
 * 点击事件
 */

public class PersonalClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;
    private final PersonalDelegate personalDelegate;

    public PersonalClickListener(LatteDelegate delegate,PersonalDelegate personalDelegate) {
        this.DELEGATE = delegate;
        this.personalDelegate = personalDelegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id) {
            case 0:
                DELEGATE.getParentDelegate().getSupportDelegate().start(new UserInfoDelegate(personalDelegate),SINGLETASK);
                break;
            //case 1:
            case 2:
                DELEGATE.getParentDelegate().getSupportDelegate().start(new NoticeDelegate());
                break;
            case 4:
                DELEGATE.getParentDelegate().getSupportDelegate().start(new FooterDelegate());
                break;
            case 7:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
                break;
            case 8:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
