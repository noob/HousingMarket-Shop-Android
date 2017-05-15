package com.carlos.housingmarket_shop_android.service;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.util.Log;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;

public class GetService extends GTIntentService {

    String TAG = "GetService";

    public GetService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
    }

    //推送消息接收方法
    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        Log.e(TAG, "onReceiveMessageData -> " + "msg = " + msg.toString());
        String appid = msg.getAppid();
        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();
        byte[] payload = msg.getPayload();
        String pkg = msg.getPkgName();
        String cid = msg.getClientId();
        boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
        Log.d(TAG, "call sendFeedbackMessage = " + (result ? "success" : "failed"));
        if (payload == null) {
        } else {
            String data = new String(payload);
            System.out.println("data-------->"+data);
        }

    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
    }


}
