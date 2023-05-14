package com.example.sample.viewmodel;

import static com.example.sample.Constant.FIRESTROE_COLLECT_USERS;

import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample.MyApplication;
import com.example.sample.data.UserInfoBean;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthViewModel extends ViewModel {

    public MutableLiveData<Pair<Boolean, String>> signUpResult = new MutableLiveData<>();

    public void signUp(String user, String email, String pwd) {
        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FireStoreAuth", "createUserWithEmail:success");
                            setUserInfo(email, user);
                        } else {
                            signUpResult.setValue(Pair.create(false, task.getResult().toString()));
                            // If sign in fails, display a message to the user.
                            Log.w("FireStoreAuth", "createUserWithEmail:failure", task.getException());
//                            ToastUtils.show(this,"login");

                        }
                    }
                });
    }

    private void setUserInfo(String email, String user) {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setDisplayName(user);
        userInfoBean.setEmail(email);
        FirebaseFirestore.getInstance()
                .collection(FIRESTROE_COLLECT_USERS)
                .document(email)
                .set(userInfoBean)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            signUpResult.setValue(Pair.create(true, email));
                        } else {
                            signUpResult.setValue(Pair.create(false, task.getResult().toString()));
                        }
                    }
                });
    }


    public MutableLiveData<Boolean> loginResult = new MutableLiveData<>();


    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public void login(String email, String pwd) {
        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FireStoreAuth", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            getUserInfo(user.getEmail());
                        } else {
                            loginResult.setValue(false);
                            // If sign in fails, display a message to the user.
                            Log.w("FireStoreAuth", "createUserWithEmail:failure", task.getException());
//                            ToastUtils.show(this,"login");

                        }
                    }
                });
    }

    private void getUserInfo(String email) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(FIRESTROE_COLLECT_USERS).document(email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    UserInfoBean bean = document.toObject(UserInfoBean.class);
                    if (bean == null) {
                        loginResult.setValue(false);
                        return;
                    }
                    MyApplication.setUserLogin(bean);
                    loginResult.setValue(true);
                } else {
                    loginResult.setValue(false);
                    Log.w("FireStore", "Error getting documents.", task.getException());
                }
            }
        });
    }
}
