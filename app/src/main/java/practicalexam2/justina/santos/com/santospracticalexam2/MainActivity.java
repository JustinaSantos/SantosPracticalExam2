package practicalexam2.justina.santos.com.santospracticalexam2;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText fname, lname, exam1, exam2, ave;
    String first, last, e1, e2;
    double average;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        exam1 = findViewById(R.id.exam1);
        exam2 = findViewById(R.id.exam2);
    }

    public void saveExternal(View v)
    {
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "External.txt");
        first = fname.getText().toString();
        last = lname.getText().toString();
        e1 = exam1.getText().toString();
        e2 = exam2.getText().toString();
        average = (Double.parseDouble(e1) + Double.parseDouble(e2)) / 2;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(first.getBytes());
            fos.write(last.getBytes());
            fos.write(String.valueOf(average).getBytes());
        } catch (Exception e) {
            Log.d("Error", "Error writing to file");
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        File folder2 = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file2 = new File(folder2, "External.txt");
        FileInputStream fin = null;
        int c;
        StringBuffer buffer = new StringBuffer();
        try {
            fin = new FileInputStream(file2);
            while ((c = fin.read()) != -1)
            {
                buffer.append((char)c);
            }

            ave.setText(buffer);
        } catch (Exception e) {
            Toast.makeText(this, "ERROR IN READING SD CARD", Toast.LENGTH_SHORT).show();
        }



    }

}
