package com.example.ec.sign;



/**
 * @author alan
 *         Date  2018/6/8.
 *         Function :
 *         Issue :
 */

public class SignInPresenterImpl implements ISignInPresenter{

    private ISignInListener signInListener;
  //  private MsgProveHandler msgProveHandler;
    private String country = "86";

    public SignInPresenterImpl(ISignInListener signInListener){
        this.signInListener = signInListener;
//        msgProveHandler = MsgProveHandler.create(this);
    }


    public void submit(String phone,String msg){
        //msgProveHandler.submitCode(country,phone,msg);
    }

    public void sendCode(String phone){
        //msgProveHandler.sendCode("86",phone);
    }


    @Override
    public void phoneError() {
        signInListener.phoneError();
    }

    @Override
    public void proveFail() {
        signInListener.proveError();
    }

    @Override
    public void proveSuccess() {
        signInListener.success();
    }
}
