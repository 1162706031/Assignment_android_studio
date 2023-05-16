package com.example.sample.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample.Constant;
import com.example.sample.MyApplication;
import com.example.sample.data.ClassMates;
import com.example.sample.data.CourseBean;
import com.example.sample.data.CourseFileBean;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class CourseDetailViewModel extends ViewModel {

    String mCourseId;
    public MutableLiveData<CourseBean> courseLiveData = new MutableLiveData<>();

    public MutableLiveData<List<CourseFileBean>> filesLiveData = new MutableLiveData<>();
    public MutableLiveData<List<ClassMates>> classMatesLiveData = new MutableLiveData<>();

    public void getCourse() {
        FirebaseFirestore.getInstance()
                .collection(Constant.FIRESTROE_COLLECT_COURSE)
                .document(mCourseId)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            CourseBean courseBean = task.getResult().toObject(CourseBean.class);
                            courseLiveData.setValue(courseBean);
                        }
                    }
                });
    }

    public void getCourseFiles(String courseId) {
        FirebaseFirestore.getInstance()
                .collection(Constant.FIRESTROE_COLLECT_COURSE)
                .document(courseId)
                .collection(Constant.FIRESTROE_COLLECT_FILES)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<CourseFileBean> fileBeans = task.getResult().toObjects(CourseFileBean.class);
                            filesLiveData.setValue(fileBeans);
                        }
                    }
                });
    }

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

    public void subscribe() {
        if (courseLiveData.getValue() == null) {
            return;
        }
        FirebaseFirestore.getInstance()
                .collection(Constant.FIRESTROE_COLLECT_COURSE)
                .document(mCourseId)
                .collection(Constant.FIRESTROE_COLLECT_SUBSCRIBER)
                .document(MyApplication.user1.getEmail())
                .set(MyApplication.user1);

        FirebaseFirestore.getInstance()
                .collection(Constant.FIRESTROE_COLLECT_USERS)
                .document(MyApplication.user1.getEmail())
                .collection(Constant.FIRESTROE_COLLECT_COURSE)
                .document(courseLiveData.getValue().getCourseId())
                .set(courseLiveData.getValue());
    }

    public void checkSubscribe(String courseId) {
        mCourseId = courseId;

    }
}
