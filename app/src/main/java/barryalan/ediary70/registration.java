package barryalan.ediary70;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class registration extends AppCompatActivity {

    //REGISTRATION PAGE BUTTONS
    Button btn_submit;
    EditText et_name;
    EditText et_password;
    EditText et_passwordVerification;
    EditText et_username;
    EditText et_email;
    CheckBox cb_passwordVisibility;

    //LOGIN PAGE BUTTONS
    EditText et_username1;
    EditText et_password1;

    //EVERYTHING THAT HAPPENS IN REGISTRATION PAGE RUNS FROM HERE-----------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //CREATE AND LINK REGISTRATION PAGE BUTTONS AND TEXT BOXES TO THE ONES ON THE DISPLAY
        btn_submit = (Button) findViewById(R.id.btn_Rsubmit);
        et_name = (EditText) findViewById(R.id.et_Rname);
        et_password = (EditText) findViewById(R.id.et_Rpassword);
        et_passwordVerification = (EditText) findViewById(R.id.et_RpasswordVerification);
        et_username = (EditText) findViewById(R.id.et_Rusername);
        et_email = (EditText) findViewById(R.id.et_Remail);
        cb_passwordVisibility = (CheckBox) findViewById (R.id.cb_RpasswordVisibility);

        //CREATE AND LINK LOGIN PAGE BUTTONS AND TEXT BOXES TO THE ONES ON THE DISPLAY
        et_password1 = (EditText) findViewById(R.id.et_Lpassword);
        et_username1 = (EditText) findViewById(R.id.et_Lusername);

        //CREATE A NEW INSTANCE OF THE DATABASE FOR ACCESS
        final databaseHelper db = new databaseHelper(this);

        //IF THE BUTTON LINKED TO BTN IS CLICKED----------------------------------------------------
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CONSTRAINTS CHECKS ON THE REGISTRATION PAGE FIELDS
                if(!(isEmpty(et_name)) & !(isEmpty(et_password)) & !(isEmpty(et_username)) & !(isEmpty(et_email))){//check if all boxes have an input
                    if(!(db.isUsernameTaken(et_username))){ //check if username is taken
                        if(!(db.isEmailTaken(et_email))){ //check if email is taken
                            if(isUsernameLengthValid(et_username)) { //check if username is the allowed length
                                if(isPasswordLengthValid(et_password)) { //check if password is the allowed length
                                    if(doesPasswordContainNumber(et_password)) { //check if password has at least one number
                                        if(!(isUsernameInPassword(et_username, et_password))) { //check if username is included in the password
                                            if(doPasswordsMatch(et_password,et_passwordVerification)){ //check if password fields input matches
                                                if(isEmailExtensionValid(et_email)){ //check if email extension is valid Ex. @gmail.com
                                                    db.addUser(new user(et_username.getText().toString(), et_email.getText().toString(), et_password.getText().toString()));
                                                    db.close();
                                                    gotoLoginPage();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        //ALLOWS THE USE OF THE SHOW PASSWORD FEATURE IN THE LOGIN PAGE-----------------------------

        cb_passwordVisibility.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //CHECKBOX STATUS IS CHANGED FROM UNCHECKED TO CHECKED
                if (!isChecked) {
                    // SHOW PASSWORDS
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_passwordVerification.setTransformationMethod(PasswordTransformationMethod.getInstance());

                } else {
                    // MASK PASSWORDS
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_passwordVerification.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

    }

    //LINKS THE REGISTRATION PAGE TO THE LOGIN PAGE THROUGH THE BUTTON------------------------------
    private void gotoLoginPage() {
        Intent name = new Intent(this, login.class);
        startActivity(name);
    }

    //CHECKS IF THE TEXT BOX IS EMPTY --------------------------------------------------------------
    public boolean isEmpty(EditText edittext) {

        String message;
        String hint =  edittext.getHint().toString();
        switch (hint){
            case "Name":
                message = "Enter a name";
                break;
            case "Password":
                message = "Enter a password";
                break;
            case "Username":
                message = "Enter a username";
                break;
            case "Email":
                message = "Enter an email address";
                break;
            default:
                message = "Enter a value";
                break;
        }

        //VARIABLES FOR THE MESSAGE
        Context context = getApplicationContext();
        CharSequence text = "Please Enter all values";
        int duration = Toast.LENGTH_SHORT;


        //CREATES MESSAGE
        Toast toast = Toast.makeText(context, text, duration);

        String value = edittext.getText().toString();
        if (value.isEmpty()) {
            edittext.setError(message);//message is chosen on switch statement above
            toast.show(); //DISPLAYS MESSAGE
            return true;
        } else {
            return false;
        }

    }

    //CHECKS IF USERNAME IS BETWEEN 3 AND 21 CHARACTERS---------------------------------------------
    public boolean isUsernameLengthValid(EditText et_username){
        if(et_username.getText().length() > 1 & et_username.getText().length() < 22){
            return true;
        }
        et_username.setError("Username must between 3 and 21 characters long");
        return false;
    }

    //CHECKS IF PASSWORD IS BETWEEN 3 AND 16 CHARACTERS---------------------------------------------
    public boolean isPasswordLengthValid(EditText et_password){
        if(et_password.getText().length() > 1 & et_password.getText().length() < 17){
            return true;
        }
        et_password.setError("Password must between 2 and 16 characters long");
        return false;
    }

    //CHECKS IF USERNAME IS INCLUDED IN PASSWORD----------------------------------------------------
    public boolean isUsernameInPassword(EditText et_username, EditText et_password){
        if(et_password.getText().toString().contains(et_username.getText().toString())) {
            et_password.setError("Username cannot be part of your password");
            return true;
        }
        return false;
    }

    //CHECKS IF PASSWORD CONTAINS A NUMBER----------------------------------------------------------
    public boolean doesPasswordContainNumber(EditText et_password){
        //.* means any character from 0 to infinite occurence, than the \\d+
        // (double backslash I think is just to escape the second backslash) and \d+ means a digit from 1 time to infinite.
        if(et_password.getText().toString().contains("1")) {//".*\\d+.*"
            return true;
        }
        et_password.setError("Password must contain at least one number");
        return false;
    }

    //CHECKS IF PASSWORD VERIFICATION MATCHES PASSWORD----------------------------------------------
    public boolean doPasswordsMatch(EditText et_password, EditText et_passwordVerification){
        if(et_passwordVerification.getText().toString().matches(et_password.getText().toString())){
            return true;
        }
        et_passwordVerification.setError("Passwords do not match");
        return false;
    }

    //CHECKS IF THE EMAIL ADDRESS EXTENSION IS VALID------------------------------------------------
    public boolean isEmailExtensionValid(EditText et_email){
        if(et_email.getText().toString().endsWith("@hotmail.com") || et_email.getText().toString().endsWith("@gmail.com") ||
                et_email.getText().toString().endsWith("@txwes.edu") || et_email.getText().toString().endsWith("@yahoo.com") ||
                et_email.getText().toString().endsWith("@outlook.com") || et_email.getText().toString().endsWith("@icloud.com")) {
            return true;
        }
        et_email.setError("Email is not valid or extension (Ex. @gmail.com) is not supported");
        return false;
    }
}




