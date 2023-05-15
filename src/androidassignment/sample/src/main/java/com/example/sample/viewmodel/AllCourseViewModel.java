package com.example.sample.viewmodel;

import static com.example.sample.Constant.FIRESTROE_COLLECT_COURSE;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample.data.CourseBean;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class AllCourseViewModel extends ViewModel {
    public MutableLiveData<List<CourseBean>> allCourseResult = new MutableLiveData<>();

    public void searchAllCourse(String content) {
        //为空则全部展示
        if (TextUtils.isEmpty(content)) {

        } else {//根据内容搜索

        }
        FirebaseFirestore.getInstance()
                .collection(FIRESTROE_COLLECT_COURSE)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<CourseBean> list = task.getResult().toObjects(CourseBean.class);
                            allCourseResult.setValue(list);
                        }
                    }
                });
    }

}
