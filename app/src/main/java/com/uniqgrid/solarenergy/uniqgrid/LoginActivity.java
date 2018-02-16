package com.uniqgrid.solarenergy.uniqgrid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

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

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();

        AndroidNetworking.initialize(getApplicationContext(), okHttpClient);

        pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Please wait......");
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(false);



     //   overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);



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




                                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                pd.show();
                                loginConnection(emailid,password);



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
                String url = "https://forms.zohopublic.com/Uniqgrid/form/LeadsOnlineChannel/formperma/4413KG9B1DB1ajF6B4F653CGF";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
//                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
//                startActivity(intent);
            }
        });





    }

    public void loginConnection(final String email, final String passwo){

        AndroidNetworking.get("https://www.ragic.com/uniqgrid/crm3/1?where=1000507,eq,"+email+"&api")
                .addHeaders("Authorization","Basic ajZNMW9hMFQrV2lqT2NxdURuTzJGbS8yRnhrY0crQmpUdGt0R1RPNFhHSldKK2lTL3dBWVhKOG1ScHpEQXVNOQ==") // posting header
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response

                        String crctPassword = "";
                        String successMsg="abcd";
                        JSONObject content = new JSONObject();
                        Iterator<String> keys = response.keys();

                        if(response.length() == 0 || !(keys.hasNext())){
                            Log.d("result","length is 0");
                            Snackbar.make(findViewById(android.R.id.content), "Email is not yet registered", Snackbar.LENGTH_LONG).show();
                            LoginActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    pd.dismiss();
                                }
                            });
                        }else{
                            try {
                                if(keys.hasNext()) {
                                    content = response.getJSONObject(keys.next());
                                    crctPassword = content.getString("Account Password");
                                }
                            }catch (JSONException exception){

                            }
                            if (!passwo.equals(crctPassword)){
                                    Log.d("result","invalid password" + passwo +" " + crctPassword);
                                    Snackbar.make(findViewById(android.R.id.content), "Invalid password", Snackbar.LENGTH_LONG).show();
                                LoginActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        pd.dismiss();
                                    }
                                });
                            }else{
                                Log.d("result","success");
                                successMsg = "success";
                                SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                                SharedPreferences.Editor editor = app_preferences.edit();
                                editor.putString("token", successMsg);
                                editor.putString("email",email);
                                editor.putString("content",content.toString());
                                editor.apply();

                                LoginActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        pd.dismiss();
                                    }
                                });

                                Intent intent = new Intent(LoginActivity.this,WelcomeActivity.class);
                                startActivity(intent);
                                finish();

                                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();

                                }



                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                            Snackbar.make(findViewById(android.R.id.content), "Please check your Network Connection", Snackbar.LENGTH_LONG).show();

                            LoginActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    pd.dismiss();
                                }
                            });
                        }

                });


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

    @Override
    public void onBackPressed() {
        if(pd!=null && pd.isShowing()){
            pd.dismiss();
            AndroidNetworking.forceCancelAll();
        }else{
            super.onBackPressed();
            overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
        }

    }
}
