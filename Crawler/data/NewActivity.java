2
https://raw.githubusercontent.com/pjmatuck/dev.venture/master/FakeMessenger/app/src/main/java/learn/java/fakemessenger/NewActivity.java
package learn.java.fakemessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    public static final String ANSWER_ID = NewActivity.class.getSimpleName() + ".ANSWER";

    TextView textViewMessageReceived;
    EditText editTextAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        textViewMessageReceived = findViewById(R.id.textview_message);
        editTextAnswer = findViewById(R.id.edittext_answer);

        Intent intent = getIntent();
        if(intent.hasExtra(MainActivity.EXTRA_ID)) {
            String messageReceived = intent.getStringExtra(MainActivity.EXTRA_ID);
            textViewMessageReceived.setText(messageReceived);
        } else {
            textViewMessageReceived.setText("Não há mensagens.");
        }
    }

    public void sendAnswer(View view){
        String answer = editTextAnswer.getText().toString();
        Intent answerIntent = new Intent();
        answerIntent.putExtra(ANSWER_ID, answer);
        setResult(RESULT_OK, answerIntent);
        finish();
    }
}
