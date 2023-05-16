package com.example.sample.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample.Constant;
import com.example.sample.data.CourseFileBean;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class CourseFilesViewModel extends ViewModel {


    public MutableLiveData<List<CourseFileBean>> filesLiveData = new MutableLiveData<>();


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


}
