package com.example.groupassigment.data;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.groupassigment.data.model.LoggedInUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.concurrent.CountDownLatch;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

public class LoginDataSource {

    public interface LoginCallback {
        void onSuccess(LoggedInUser user);
        void onError(Exception e);
    }

    public void login(String username, String password, LoginCallback callback) {
        checkUser(username, password, callback);
    }

    private void checkUser(String username, String password, LoginCallback callback) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(username);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String passwordFromDB = snapshot.child(username).child("password").getValue(String.class);
                    if (passwordFromDB != null && passwordFromDB.equals(password)) {
                        String name = snapshot.child(username).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(username).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(username).child("username").getValue(String.class);
                        LoggedInUser user = new LoggedInUser(name, usernameFromDB, emailFromDB);
                        callback.onSuccess(user);
                    } else {
                        callback.onError(new IOException("Incorrect password"));
                    }
                } else {
                    callback.onError(new IOException("User not found"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(new IOException("Error querying the database"));
            }
        });
    }

    public void logout() {
        // TODO: revoke authentication
    }
}