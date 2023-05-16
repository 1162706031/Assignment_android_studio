package com.example.sample.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample.Constant;
import com.example.sample.data.CourseRecordBean;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class CourseRecordViewModel extends ViewModel {


    public MutableLiveData<List<CourseRecordBean>> recordsLiveData = new MutableLiveData<>();


    public void getCourseRecord(String courseId) {
        FirebaseFirestore.getInstance()
                .collection(Constant.FIRESTROE_COLLECT_COURSE)
                .document(courseId)
                .collection(Constant.FIRESTROE_COLLECT_RECORDS)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<CourseRecordBean> fileBeans = task.getResult().toObjects(CourseRecordBean.class);
                            recordsLiveData.setValue(fileBeans);
                        }
                    }
                });
    }


}
