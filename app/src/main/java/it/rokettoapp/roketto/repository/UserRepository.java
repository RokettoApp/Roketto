package it.rokettoapp.roketto.repository;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.model.AuthenticationResponse;
import it.rokettoapp.roketto.util.SharedPreferencesProvider;

public class UserRepository {

    private static final String TAG = "UserRepository";

    private final FirebaseAuth mFirebaseAuth;

    private final Application mApplication;
    private final SharedPreferencesProvider mSharedPreferencesProvider;

    private final MutableLiveData<AuthenticationResponse> mAuthenticationResponseLiveData;

    public UserRepository(Application application) {

        mFirebaseAuth = FirebaseAuth.getInstance();
        mApplication = application;
        mAuthenticationResponseLiveData = new MutableLiveData<>();
        mSharedPreferencesProvider = new SharedPreferencesProvider(application);
    }

    public MutableLiveData<AuthenticationResponse> signInWithEmail(String email, String password) {

        if (email != null && !email.isEmpty() && password != null && !password.isEmpty())  {
            mFirebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(ContextCompat.getMainExecutor(mApplication), task -> {

                        AuthenticationResponse authenticationResponse;
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail success");
                            authenticationResponse = new AuthenticationResponse();
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            authenticationResponse.setSuccess(true);
                            if (user != null) {
                                mSharedPreferencesProvider.setAuthenticationToken(
                                        user.getIdToken(false).getResult().getToken());
                                mSharedPreferencesProvider.setUserId(user.getUid());
                            }
                        } else {
                            Log.e(TAG, "signInWithEmail failure", task.getException());
                            authenticationResponse = new AuthenticationResponse();
                            authenticationResponse.setSuccess(false);
                            if (task.getException() != null) {
                                authenticationResponse.setMessage(
                                        task.getException().getLocalizedMessage());
                            } else {
                                authenticationResponse.setMessage(
                                        mApplication.getString(R.string.registration_failure));
                            }
                        }
                        mAuthenticationResponseLiveData.postValue(authenticationResponse);
                    });
        }
        return mAuthenticationResponseLiveData;
    }

    public MutableLiveData<AuthenticationResponse> createUserWithEmail(String email, String password) {

        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(ContextCompat.getMainExecutor(mApplication), task -> {

                    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail success");
                        FirebaseUser user = mFirebaseAuth.getCurrentUser();
                        authenticationResponse.setSuccess(true);
                        if (user != null) {
                            mSharedPreferencesProvider.setAuthenticationToken(
                                    user.getIdToken(false).getResult().getToken());
                            mSharedPreferencesProvider.setUserId(user.getUid());
                        }
                    } else {
                        Log.e(TAG, "createUserWithEmail failure", task.getException());
                        authenticationResponse.setSuccess(false);
                        if (task.getException() != null) {
                            authenticationResponse.setMessage(
                                    task.getException().getLocalizedMessage());
                        } else {
                            authenticationResponse.setMessage(
                                    mApplication.getString(R.string.registration_failure));
                        }
                    }
                    mAuthenticationResponseLiveData.postValue(authenticationResponse);
                });
        return mAuthenticationResponseLiveData;
    }

    public MutableLiveData<AuthenticationResponse> createUserWithGoogle(Intent data) {

        Task<GoogleSignInAccount> signInTask = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = signInTask.getResult(ApiException.class);
            Log.d(TAG, "firebaseAuthWithGoogle: " + account.getId());
            AuthCredential credential =
                    GoogleAuthProvider.getCredential(account.getIdToken(), null);
            mFirebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener(ContextCompat.getMainExecutor(mApplication), task -> {

                        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential success");
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            authenticationResponse.setSuccess(true);
                            if (user != null) {
                                mSharedPreferencesProvider.setAuthenticationToken(
                                        user.getIdToken(false).getResult().getToken());
                                mSharedPreferencesProvider.setUserId(user.getUid());
                            }
                        } else {
                            authenticationResponse.setSuccess(false);
                            if (task.getException() != null) {
                                authenticationResponse.setMessage(
                                        task.getException().getLocalizedMessage());
                            } else {
                                authenticationResponse.setMessage(
                                        mApplication.getString(R.string.registration_failure));
                            }
                        }
                        mAuthenticationResponseLiveData.postValue(authenticationResponse);
                    });
        } catch (ApiException e) {
            Log.w(TAG, "Google sign in failed", e);
        }
        return mAuthenticationResponseLiveData;
    }
}