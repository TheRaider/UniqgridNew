package com.uniqgrid.solarenergy.uniqgrid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView tvRegister,tvLogo;
    Button bLogin;
    TextInputLayout tilEmailId,tilPassword;
    String  password,emailid;
    ProgressDialog pd;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);

        tvLogo = (TextView)findViewById(R.id.tvLogo);
        tilEmailId = (TextInputLayout) findViewById(R.id.tilEmailId);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);

        // Changing Font of Uniqgrid
        Typeface ocrExtendedFont = Typeface.createFromAsset(getAssets(),  "fonts/ocrExtended.TTF");
        tvLogo.setTypeface(ocrExtendedFont);


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emailid = tilEmailId.getEditText().getText().toString();
                password = tilPassword.getEditText().getText().toString();

                if (emailid.isEmpty()) {
                    tilEmailId.getEditText().setError("Please enter your Email-Id");
                } else {
                    if (password.isEmpty()) {
                        tilPassword.getEditText().setError("Please enter Password");
                    } else {
                        if (isValidEmailAddress(emailid)) {

                            if (isValidPassword(password)) {

                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                   /*  intent.putExtra("name",name);
                intent.putExtra("email",email);
                intent.putExtra("proPicUrl",proPicUrl);*/
                                startActivity(intent);

                               /*  try {
                                   loginInput.put("email",emailid);
                                    loginInput.put("password",password);

                                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                  //  pd.show();
                                   // loginConnection();



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } */


                            } else {
                                tilPassword.getEditText().setError("Password is too short");
                            }

                        } else {
                            tilEmailId.getEditText().setError("Please enter a valid Email Address");
                        }


                    }
                }






            }
        });




        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });





    }

    public void loginConnection(){
      /*  AndroidNetworking.post("https://api.thinkmerit.in/api/auth/login")
                .addJSONObjectBody(loginInput) // posting json
                .setTag("loginConnection")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response

                        String rcvdMsg = "";
                        String successMsg="";

                        try {
                            successMsg = response.getString("token");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            JSONArray rcvdMsgA = response.getJSONArray("error");
                            rcvdMsg = rcvdMsgA.getString(0);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(rcvdMsg.equals("invalid_credentials")){
                            Snackbar.make(findViewById(android.R.id.content), "Invalid Credentials", Snackbar.LENGTH_LONG).show();
                            LoginPage.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    pd.dismiss();
                                }
                            });
                        }
                        else  if((!(successMsg.equals("")))) {

                            SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(LoginPage.this);
                            SharedPreferences.Editor editor = app_preferences.edit();
                            editor.putString("token", successMsg);
                            editor.putInt("should_display_intro", 0);
                            editor.apply();

                            Toast.makeText(LoginPage.this,"Login Successful",Toast.LENGTH_LONG).show();
                            getUserDetailsConnection();
                        }else{
                            Snackbar.make(findViewById(android.R.id.content), "Please check your Network Connection", Snackbar.LENGTH_LONG).show();

                            LoginPage.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    pd.dismiss();
                                }
                            });
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error

                        if(error.getErrorCode() == 401){
                            Snackbar.make(findViewById(android.R.id.content), "Invalid Credentials", Snackbar.LENGTH_LONG).show();
                        }
                        else if(error != null && !error.getErrorDetail().equalsIgnoreCase("requestCancelledError")){
                            Snackbar.make(findViewById(android.R.id.content), error.getErrorDetail(), Snackbar.LENGTH_LONG).show();
                        }

                        LoginPage.this.runOnUiThread(new Runnable() {
                            public void run() {
                                pd.dismiss();
                            }
                        });
                    }
                });
                */
    }


    public boolean isValidEmailAddress(String email) {

        return  (!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());

    }

    public boolean isValidPassword(String password){
        if(password.length()>=6){
            return  true;
        } else {
            return false;
        }
    }
}
