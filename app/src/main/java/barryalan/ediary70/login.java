package barryalan.ediary70;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    //Define a user object to access user info
    user User1 = new user();

    //Login page buttons declaration
    Button btn_Lsignin;
    Button btn_LforgotLogin;
    EditText et_Lusername;
    EditText et_Lpassword;
    CheckBox cb_LpasswordVisibility;

    //Defines the number of times a user fails to provide a valid login
    int numberOfTries = 0;

    //Everything that happens in login page runs from here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Create and link buttons and text boxes to the ones on the display
        btn_Lsignin = (Button) findViewById(R.id.btn_Lsignin);
        btn_LforgotLogin = (Button) findViewById(R.id.btn_LforgotLogin);
        et_Lusername = (EditText) findViewById(R.id.et_Lusername);
        et_Lpassword = (EditText) findViewById(R.id.et_Lpassword);
        cb_LpasswordVisibility = (CheckBox) findViewById(R.id.cb_LshowPassword);

        //If the button linked to btn_Lsignin is clicked on
        btn_Lsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if the user inputted a username and password
                if (!(isEmpty(et_Lusername)) & !(isEmpty(et_Lpassword))) {
                    if (isValid(et_Lusername, et_Lpassword)) { //Are the provided credentials valid?
                        //Are the valid login credentials those of an admin?
                        if(et_Lusername.getText().toString().compareTo("Admin123") == 0 && et_Lpassword.getText().toString().compareTo("coco123") == 0){
                            gotoAdminActivity(v);
                        }
                        else {
                            User1.setCurrentUserName(et_Lusername.getText().toString());
                            gotoHealthCareMenuActivity(v);
                        }
                    }
                }
            }
        });


        //Allows the use of the show password feature in the login page
        cb_LpasswordVisibility.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Checkbox status is changed from unchecked to checked
                if (!isChecked) {
                    // SHOW PASSWORD
                    et_Lpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // MASK PASSWORD
                    et_Lpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    //Checks if the text box is empty or not
    public boolean isEmpty(EditText edittext) {

        String message;
        String hint = edittext.getHint().toString();
        switch (hint) {
            case "Name":
                message = "Enter a name";
                break;
            case "Password":
                message = "Enter a password";
                break;
            default:
                message = "Enter a value";
                break;
        }

        //Variables for the message
        Context context = getApplicationContext();
        CharSequence text = "Please Enter all values";
        int duration = Toast.LENGTH_SHORT;

        //Creates message
        Toast toast = Toast.makeText(context, text, duration);

        String value = edittext.getText().toString();
        if (value.isEmpty()) {
            edittext.setError(message);//Message is chosen on switch statement above
            toast.show(); //Displays message
            return true;
        }
        else {
            return false;
        }

    }

    //Checks if username and password are in the database and match each other
    public boolean isValid(EditText usernametext, EditText passwordtext) {

        //Creating a new instance of the database in order to access it
        databaseHelper lbh = new databaseHelper(this);

        //Creating the login successful message
        Context context = getApplicationContext();
        CharSequence text = "Login Successful!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);

        //Creating the login failed message
        Context context1 = getApplicationContext();
        CharSequence text1 = "Login Failed!" + numberOfTries;
        int duration1 = Toast.LENGTH_SHORT;
        Toast toast1 = Toast.makeText(context1, text1, duration1);

        //Get the inputted text from the text boxes
        String userName = usernametext.getText().toString();
        String password = passwordtext.getText().toString();

        // Fetch the password from the database from the respective username
        String storedPassword = lbh.getUserPassword(userName);

        //Gives you three tries until the system allows for a forgot login
        if (numberOfTries > 1) {
            btn_LforgotLogin.setVisibility(View.VISIBLE);
        }

        //Validate the password with the one on the database
        if (password.equals(storedPassword)) {
            toast.show();
            return true;
        } else {
            toast1.show();
            numberOfTries++;
            return false;
        }
    }

    //LINK THE LOGIN PAGE TO THE REGISTRATION PAGE THROUGH THE BUTTON-------------------------------
    public void gotoRegistrationActivity(View view) {
        Intent name = new Intent(this, registration.class);
        startActivity(name);
    }

    //LINK THE LOGIN PAGE TO THE FORGOT LOGIN PAGE--------------------------------------------------
    public void gotoForgotLoginActivity(View view) {
        Intent name = new Intent(this, forgotLogin.class);
        startActivity(name);
    }

    //LINK TO THE HEALTH MENU PAGE THROUGH THE BUTTON-------------------------------
    public void gotoHealthCareMenuActivity(View view) {
        Intent name = new Intent(this, healthCareMenu.class);
        startActivity(name);
    }

    //LINK THE LOGIN PAGE TO THE ADMIN PAGE---------------------------------------------------------
    public void gotoAdminActivity(View view) {
        Intent name = new Intent(this, Admin.class);
        startActivity(name);
    }
}
