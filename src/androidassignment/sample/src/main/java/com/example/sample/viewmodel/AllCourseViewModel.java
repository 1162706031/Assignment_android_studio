package com.example.sample.viewmodel;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample.data.CourseBean;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllCourseViewModel extends ViewModel {
    public MutableLiveData<List<CourseBean>> allCourseResult = new MutableLiveData<>();

    public void searchAllCourse(String content) {
        //为空则全部展示
        if (TextUtils.isEmpty(content)) {

        } else {//根据内容搜索

        }

        List<CourseBean> datalist = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CourseBean item = new CourseBean();
            item.setCourseName("Course" + i);
            item.setTeacherName("sam" + i);
            datalist.add(item);
        }
        allCourseResult.setValue(datalist);
    }


    public void uploadAllcourse() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("FireStore", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d("FireStore", e.getMessage());
//                    }
//                });

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("FireStore", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("FireStore", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
