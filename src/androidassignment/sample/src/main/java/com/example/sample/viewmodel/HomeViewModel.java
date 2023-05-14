package com.example.sample.viewmodel;

import static com.example.sample.Constant.FIRESTROE_COLLECT_CHATROOM;
import static com.example.sample.Constant.FIRESTROE_COLLECT_MSGS;
import static com.example.sample.Constant.FIRESTROE_COLLECT_SESSIONS;
import static com.example.sample.Constant.FIRESTROE_COLLECT_USERS;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample.MyApplication;
import com.example.sample.data.ChatRoomBean;
import com.example.sample.data.ClassMates;
import com.example.sample.data.MsgBean;
import com.example.sample.data.SessionBean;
import com.example.sample.livebus.LiveBus;
import com.example.sample.utils.mmkv.MMKVUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HomeViewModel extends ViewModel {

    /**
     * 监听聊天消息
     * 在activity中监听
     */
    public void setSessionListener() {
        FirebaseFirestore.getInstance()
                .collection(FIRESTROE_COLLECT_USERS)
                .document(MyApplication.user1.getEmail())
                .collection(FIRESTROE_COLLECT_SESSIONS)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        //更新聊天记录和聊天列表

                        List<SessionBean> sessionsRemote = new ArrayList<>();
                        for (DocumentChange documentChange : value.getDocumentChanges()) {
                            SessionBean sessionBean = documentChange.getDocument().toObject(SessionBean.class);
                            sessionsRemote.add(sessionBean);
                        }

                        List<SessionBean> sessionLocal = MMKVUtils.getSessionList();
                        HashMap<String, SessionBean> map = new HashMap<>();
                        for (SessionBean local : sessionLocal) {
                            map.put(local.getSessionId(), local);
                        }
                        for (SessionBean remote : sessionsRemote) {
                            SessionBean local = map.get(remote.getSessionId());
                            if (local == null) {
                                remote.setUpdateTime(0);
                                map.put(remote.getSessionId(), remote);
                            } else if (remote.getUpdateTime() > local.getUpdateTime()) {
                                remote.setUpdateTime(local.getUpdateTime());
                                map.put(remote.getSessionId(), remote);
                            }
                        }
                        //需更新的聊天
                        List<SessionBean> newList = new ArrayList<>(map.values());
                        //更新到本地
                        MMKVUtils.updateSessionList(newList);
                        //更新每个聊天记录
                        for (SessionBean updateRecordSession : sessionsRemote) {
                            FirebaseFirestore.getInstance()
                                    .collection(FIRESTROE_COLLECT_CHATROOM)
                                    .document(updateRecordSession.getRoomId())
                                    .collection(FIRESTROE_COLLECT_MSGS)
                                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                List<MsgBean> recordList = new ArrayList<>();
                                                for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                                    MsgBean msg = document.toObject(MsgBean.class);
                                                    recordList.add(msg);
                                                }
                                                recordList.sort(new Comparator<MsgBean>() {
                                                    @Override
                                                    public int compare(MsgBean msgBean, MsgBean t1) {
                                                        return (int) (msgBean.getUpdateTime() - t1.getUpdateTime());
                                                    }
                                                });
                                                MMKVUtils.updateRecordList(updateRecordSession.getSessionId(), recordList);
                                                //通知其他页面
                                                LiveBus.getInstance().messageArrived(updateRecordSession);
                                            }
                                        }
                                    });
                        }
                    }
                });
    }


    /**
     * 获取所有用户
     */


    public MutableLiveData<List<ClassMates>> classMates = new MutableLiveData<>();

    public void getClassMate() {
        FirebaseFirestore.getInstance()
                .collection(FIRESTROE_COLLECT_USERS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<ClassMates> list = new ArrayList<>();
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                if (document.getId().equals(MyApplication.user1.getEmail())) {
                                    continue;
                                }
                                ClassMates classMates1 = new ClassMates();
                                classMates1.setName(document.get("displayName", String.class));
                                classMates1.setUid(document.getId());
                                list.add(classMates1);
                            }
                            classMates.setValue(list);
                        }
                    }
                });
    }

    /**
     * 开始一个会话
     *
     * @param
     */
    public void startSession(ClassMates data) {
        //是否有会话
        boolean isAdded = false;
        for (SessionBean sessionBean : MMKVUtils.getSessionList()) {
            if (sessionBean.getSessionId().equals(data.getUid())) {
                isAdded = true;
                break;
            }
        }

        if (isAdded) {
//            jumpSessionAty(name);
        } else {

            createChatRoom(data);
        }
    }

    /**
     * 创建聊天室
     *
     * @param data
     */
    private void createChatRoom(ClassMates data) {
        ChatRoomBean room = new ChatRoomBean();
        room.setUpdateTime(System.currentTimeMillis());
        FirebaseFirestore.getInstance()
                .collection(FIRESTROE_COLLECT_CHATROOM)
                .add(room)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            String roomId = task.getResult().getId();
                            SessionBean sessionBean = new SessionBean();
                            sessionBean.setRoomId(roomId);
                            sessionBean.setSessionName(data.getName());
                            sessionBean.setSessionId(data.getUid());
                            sessionBean.setLastMsg("");
                            sessionBean.setUpdateTime(System.currentTimeMillis());
                            //创建firestore对话数据
                            FirebaseFirestore.getInstance()
                                    .collection(FIRESTROE_COLLECT_USERS)
                                    .document(MyApplication.user1.getEmail())
                                    .collection(FIRESTROE_COLLECT_SESSIONS)
                                    .document(data.getUid())
                                    .set(sessionBean);


                            sessionBean.setSessionName(MyApplication.user1.getDisplayName());
                            sessionBean.setSessionId(MyApplication.user1.getEmail());
                            FirebaseFirestore.getInstance()
                                    .collection(FIRESTROE_COLLECT_USERS)
                                    .document(data.getUid())
                                    .collection(FIRESTROE_COLLECT_SESSIONS)
                                    .document(MyApplication.user1.getEmail())
                                    .set(sessionBean);
                        }
                    }
                });
    }
}
