package com.example.sample.viewmodel;

import static com.example.sample.Constant.FIRESTROE_COLLECT_CHATROOM;
import static com.example.sample.Constant.FIRESTROE_COLLECT_SESSIONS;
import static com.example.sample.Constant.FIRESTROE_COLLECT_USERS;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample.Constant;
import com.example.sample.MyApplication;
import com.example.sample.data.ChatRoomBean;
import com.example.sample.data.ClassMates;
import com.example.sample.data.SessionBean;
import com.example.sample.utils.mmkv.MMKVUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class CourseClassmatesViewModel extends ViewModel {
    public MutableLiveData<ClassMates> jumpToSessionAty = new MutableLiveData<>();

    public MutableLiveData<List<ClassMates>> classMatesLiveData = new MutableLiveData<>();

    public void getCourseSubscribers(String courseId) {
        FirebaseFirestore.getInstance()
                .collection(Constant.FIRESTROE_COLLECT_COURSE)
                .document(courseId)
                .collection(Constant.FIRESTROE_COLLECT_SUBSCRIBER)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<ClassMates> classMates = task.getResult().toObjects(ClassMates.class);
                            classMatesLiveData.setValue(classMates);
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
            if (sessionBean.getSessionId().equals(data.getEmail())) {
                isAdded = true;
                break;
            }
        }

        if (isAdded) {
            jumpToSessionAty.setValue(data);
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
                            sessionBean.setSessionName(data.getDisplayName());
                            sessionBean.setSessionId(data.getEmail());
                            sessionBean.setLastMsg("");
                            sessionBean.setUpdateTime(System.currentTimeMillis());
                            //创建firestore对话数据
                            FirebaseFirestore.getInstance()
                                    .collection(FIRESTROE_COLLECT_USERS)
                                    .document(MyApplication.user1.getEmail())
                                    .collection(FIRESTROE_COLLECT_SESSIONS)
                                    .document(data.getEmail())
                                    .set(sessionBean);


                            sessionBean.setSessionName(MyApplication.user1.getDisplayName());
                            sessionBean.setSessionId(MyApplication.user1.getEmail());
                            FirebaseFirestore.getInstance()
                                    .collection(FIRESTROE_COLLECT_USERS)
                                    .document(data.getEmail())
                                    .collection(FIRESTROE_COLLECT_SESSIONS)
                                    .document(MyApplication.user1.getEmail())
                                    .set(sessionBean);
                        }
                    }
                });
    }
}
