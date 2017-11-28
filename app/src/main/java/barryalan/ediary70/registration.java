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

    //Registration page buttons declaration
    Button btn_submit;
    EditText et_name;
    EditText et_username;
    EditText et_password;
    EditText et_passwordVerification;
    EditText et_email;
    CheckBox cb_passwordVisibility;

    //Login page buttons declaration
    EditText et_username1;
    EditText et_password1;

    //Everything that happens in registration page runs from here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Create and link registration page buttons and text boxes to the ones on the display
        btn_submit = (Button) findViewById(R.id.btn_Rsubmit);
        et_name = (EditText) findViewById(R.id.et_Rname);
        et_password = (EditText) findViewById(R.id.et_Rpassword);
        et_passwordVerification = (EditText) findViewById(R.id.et_RpasswordVerification);
        et_username = (EditText) findViewById(R.id.et_Rusername);
        et_email = (EditText) findViewById(R.id.et_Remail);
        cb_passwordVisibility = (CheckBox) findViewById (R.id.cb_RpasswordVisibility);

        //Create and link login page buttons and text boxes to the ones on the display
        et_password1 = (EditText) findViewById(R.id.et_Lpassword);
        et_username1 = (EditText) findViewById(R.id.et_Lusername);

        //Create a new instance of the database for access
        final databaseHelper db = new databaseHelper(this);

        //If the button linked to btn_submit is clicked on
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a user with the information inputted in the registration page
                user user1 =  new user(et_name.getText().toString(),et_username.getText().toString(), et_email.getText().toString(), et_password.getText().toString());

                //Constraint checks on the registration page fields
                if(!(isEmpty(et_name)) & !(isEmpty(et_password)) & !(isEmpty(et_username)) & !(isEmpty(et_email))){//Check if all boxes have an input
                    if(!(db.isUsernameTaken(et_username))){ //Is username taken
                        if(!(db.isEmailTaken(et_email))){ //Is email taken
                            if(isUsernameLengthValid(et_username)) { //Is username the allowed length
                                if(isPasswordLengthValid(et_password)) { //Is password the allowed length
                                    if(doesPasswordContainNumber(et_password)) { //Does password have at least one number
                                        if(!(isUsernameInPassword(et_username, et_password))) { //If username included in the password
                                            if(doPasswordsMatch(et_password,et_passwordVerification)){ //Do password fields inputs match
                                                if(isEmailExtensionValid(et_email)){ //Is email extension valid Ex. @gmail.com
                                                    db.addUser(user1);
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


        //Allows the use of the show password feature in the login page
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

    //Checks if a text box is empty
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

    //Checks if username is between 3 and 21 characters
    public boolean isUsernameLengthValid(EditText et_username){
        if(et_username.getText().length() > 1 & et_username.getText().length() < 22){
            return true;
        }
        et_username.setError("Username must between 3 and 21 characters long");
        return false;
    }

    //Checks if password is between 3 and 16 characters
    public boolean isPasswordLengthValid(EditText et_password){
        if(et_password.getText().length() > 1 & et_password.getText().length() < 17){
            return true;
        }
        et_password.setError("Password must between 2 and 16 characters long");
        return false;
    }

    //Checks if first string (username) is included in second string (password)
    public boolean isUsernameInPassword(EditText et_username, EditText et_password){
        if(et_password.getText().toString().contains(et_username.getText().toString())) {
            et_password.setError("Username cannot be part of your password");
            return true;
        }
        return false;
    }

    //Checks if string (password) contains number
    public boolean doesPasswordContainNumber(EditText et_password){
        //.* means any character from 0 to infinite occurence, than the \\d+
        // (double backslash I think is just to escape the second backslash) and \d+ means a digit from 1 time to infinite.
        if(et_password.getText().toString().contains("1")) {//".*\\d+.*"
            return true;
        }
        et_password.setError("Password must contain at least one number");
        return false;
    }

    //Checks if first string (password) matches second string (password verification)
    public boolean doPasswordsMatch(EditText et_password, EditText et_passwordVerification){
        if(et_passwordVerification.getText().toString().matches(et_password.getText().toString())){
            return true;
        }
        et_passwordVerification.setError("Passwords do not match");
        return false;
    }

    //Checks if the email extension matches one of the supported extensions
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




