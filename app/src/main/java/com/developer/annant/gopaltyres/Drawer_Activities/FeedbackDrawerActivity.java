package com.developer.annant.gopaltyres.Drawer_Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.developer.annant.gopaltyres.R;

public class FeedbackDrawerActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

           editText  = (EditText) findViewById(R.id.write_feedback);




        ///Implement Intent in the given
       /* final TextView textView = (TextView) findViewById(R.id.feedback_info);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FeedbackDrawerActivity.this,editText.getText().toString(),Toast.LENGTH_LONG ).show();
            }
        });

*/ //
    } //End OnCreate



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_feedback_action, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_feedback:
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); //only email app should handle this
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{ "annant.gupta05@outlook.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback On Gopal Tyres");
                intent.putExtra(Intent.EXTRA_TEXT, editText.getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);

                }

                break;//End Action Feedback


            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}//End Main


