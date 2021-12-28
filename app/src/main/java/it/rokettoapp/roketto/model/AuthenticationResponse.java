package it.rokettoapp.roketto.model;

public class AuthenticationResponse {

    private boolean mSuccess;
    private String mMessage;

    public AuthenticationResponse() {   }

    public boolean isSuccess() {

        return mSuccess;
    }

    public void setSuccess(boolean success) {

        this.mSuccess = success;
    }

    public String getMessage() {

        return mMessage;
    }

    public void setMessage(String message) {

        this.mMessage = message;
    }

}
