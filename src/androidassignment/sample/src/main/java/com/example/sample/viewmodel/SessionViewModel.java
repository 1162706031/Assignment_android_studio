package com.example.sample.viewmodel;

import static com.example.sample.Constant.FIRESTROE_COLLECT_CHATROOM;
import static com.example.sample.Constant.FIRESTROE_COLLECT_MSGS;
import static com.example.sample.Constant.FIRESTROE_COLLECT_SESSIONS;
import static com.example.sample.Constant.FIRESTROE_COLLECT_USERS;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample.MyApplication;
import com.example.sample.data.MsgBean;
import com.example.sample.data.SessionBean;
import com.example.sample.utils.mmkv.MMKVUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionViewModel extends ViewModel {

    public MutableLiveData<List<SessionBean>> chatList = new MutableLiveData<>();


    public MutableLiveData<List<MsgBean>> recordList = new MutableLiveData<>();
    public MutableLiveData<String> title = new MutableLiveData<>();
    private List<MsgBean> recordLocal = new ArrayList<>();
    private SessionBean mCurrentConversation;

    /**
     * 获取聊天记录
     *
     * @param
     */
    public void getSessionRecord(String sessionId) {
        //获取本地聊天记录
        for (SessionBean sessionBean : MMKVUtils.getSessionList()) {
            if (sessionId.equals(sessionBean.getSessionId())) {
                mCurrentConversation = sessionBean;
            }
        }
        if (mCurrentConversation == null) {
            FirebaseFirestore.getInstance()
                    .collection(FIRESTROE_COLLECT_USERS)
                    .document(MyApplication.user1.getEmail())
                    .collection(sessionId)
                    .document(sessionId)
                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                mCurrentConversation = task.getResult().toObject(SessionBean.class);
                                if (mCurrentConversation == null) {
                                    return;
                                }
                                recordLocal = MMKVUtils.getSessionRecordList(sessionId);
                                recordList.setValue(recordLocal);
                                title.setValue(mCurrentConversation.getSessionName());
                            }
                        }
                    });
        } else {
            recordLocal = MMKVUtils.getSessionRecordList(sessionId);
            recordList.setValue(recordLocal);
            title.setValue(mCurrentConversation.getSessionName());
        }
    }

    /**
     * 发送消息
     *
     * @param s
     */
    public void sendMsg(String s) {
        MsgBean item = new MsgBean();
        item.setText(s);
        item.setUserId(MyApplication.user1.getEmail());
        item.setUpdateTime(System.currentTimeMillis());
        recordLocal.add(item);
        recordList.setValue(recordLocal);
        //更新本地聊天记录
        MMKVUtils.updateRecordList(mCurrentConversation.getSessionId(), recordLocal);
        //更新远程聊天记录
        FirebaseFirestore.getInstance()
                .collection(FIRESTROE_COLLECT_CHATROOM)
                .document(mCurrentConversation.getRoomId())
                .collection(FIRESTROE_COLLECT_MSGS)
                .add(item);

        //更新本地聊天列表

        List<SessionBean> sessionList = MMKVUtils.getSessionList();
        for (SessionBean bean : sessionList) {
            if (bean.getSessionName().equals(mCurrentConversation.getSessionId())) {
                bean.setSessionId(mCurrentConversation.getSessionId());
                bean.setLastMsg(s);
                bean.setUpdateTime(item.getUpdateTime());
            }
        }
        MMKVUtils.updateSessionList(sessionList);
        //更新远程聊天列表
        Map<String, Object> map = new HashMap<>();
        map.put("lastMsg", s);
        map.put("updateTime", item.getUpdateTime());
        FirebaseFirestore.getInstance()
                .collection(FIRESTROE_COLLECT_USERS)
                .document(mCurrentConversation.getSessionId())
                .collection(FIRESTROE_COLLECT_SESSIONS)
                .document(MyApplication.user1.getEmail())
                .update(map);
    }


    /**
     * 获取聊天列表
     */
    public void getSessionList() {
        //先获取本地的
        chatList.setValue(MMKVUtils.getSessionList());
    }

}
