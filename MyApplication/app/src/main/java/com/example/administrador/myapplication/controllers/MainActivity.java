package com.example.administrador.myapplication.controllers;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.User;
import com.example.administrador.myapplication.util.AppUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_material);

        final EditText txtLogin = AppUtil.get(findViewById(R.id.editTextLogin));
        final EditText txtPass = AppUtil.get(findViewById(R.id.editTextPass));
        final Button btnLogin = (Button) findViewById(R.id.buttonLogin);

        // Change typeface for the password field
        txtPass.setTypeface(Typeface.DEFAULT);
        txtPass.setTransformationMethod(new PasswordTransformationMethod());

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = verifyMandatoryFields(txtLogin, txtPass);
                if(isValid) {
                    User user = new User(txtLogin.getText().toString().trim(), txtPass.getText().toString().trim());
                    if(user.findUser()) {
                        startActivity(new Intent(MainActivity.this, ServiceOrderListActivity.class));
                    }else{
                        Toast.makeText(MainActivity.this, R.string.login_fail, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private boolean verifyMandatoryFields(EditText... fields) {
        boolean isValid = true;
        for (EditText field : fields) {
            field.setError(null);
            if (TextUtils.isEmpty(field.getText())) {
                field.setError(getString(R.string.msg_mandatory));
                if (isValid) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

}
