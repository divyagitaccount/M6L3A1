package com.example.m6l3a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {

    //Default file name
    private static final String FILE_NAME = "example.txt";

    // to take file content from user
    EditText mEditText;
    Button btn,btnload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //edit text referred
        mEditText = findViewById(R.id.edit_text);

        btn = findViewById(R.id.button_save);
        btnload = findViewById(R.id.button_load);

        btn.setOnClickListener(view -> {

            //Take Text from Ed (View v) {itText
                String text = mEditText.getText().toString();
                FileOutputStream fileOutputStream = null;
                try {
                    // assign a file name and file mode
                    fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);

                    // write text to file stream
                    fileOutputStream.write(text.getBytes());

                    mEditText.getText().clear();

                    //Make Toast about file location
                    Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                            Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fileOutputStream != null) {
                        try {
                            // close the output stream
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        });

        btn.setOnClickListener(view -> {


                // Input stream to get data from a file
                FileInputStream fileInputStream = null;

                try {
                    // give it a file name
                    fileInputStream = openFileInput(FILE_NAME);
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    // to read content line by line
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();

                    String text;
                    //While there is line append it to string.
                    while ((text = bufferedReader.readLine()) != null) {
                        stringBuilder.append(text).append("\n");
                    }

                    // Print string to edit text
                    mEditText.setText(stringBuilder.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fileInputStream != null) {
                        try {
                            // close the input stream
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

        });
    }


}