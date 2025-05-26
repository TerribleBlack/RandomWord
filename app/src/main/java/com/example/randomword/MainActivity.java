package com.example.randomword;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.randomword.fairies.DataStorage;

public class MainActivity extends AppCompatActivity {

    private DataStorage dataStorage;
    private EditText inputText;
    private TextView outputView;
    Button saveButton, searchButton, randomButton, showAllButton, clearAllButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        dataStorage = new DataStorage(this);
        inputText = findViewById(R.id.editTextText);
        outputView = findViewById(R.id.textView);

        saveButton = findViewById(R.id.savebutton);
        searchButton = findViewById(R.id.searchbutton);
        randomButton = findViewById(R.id.randombutton);
        showAllButton = findViewById(R.id.showallbutton);
        clearAllButton = findViewById(R.id.clearallbutton);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeItem();
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchItem();
            }
        });
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRandomItem();
            }
        });
        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllItems();
            }
        });
        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearStorage();
            }
        });
    }
    private void storeItem(){
        String input = inputText.getText().toString().trim();
        if(!input.isEmpty()){
            dataStorage.setStoredItems(input);
            outputView.setText("Item stored: " + input);
            inputText.setText("");
        }else {
            outputView.setText("Enter something to store !");
        }
    }
    private void searchItem(){
        String search = inputText.getText().toString().trim();
        if(!search.isEmpty()){
            String results = dataStorage.searchItem(search);
            outputView.setText("Found: " + results);
            inputText.setText("");
        }else {
            outputView.setText("Enter something to search !");
        }
    }
    private void getRandomItem(){
        String randomIt = dataStorage.getRandomItem();
        outputView.setText("Random Item: " + randomIt);
    }
    private void showAllItems(){
        String IwantItAll = dataStorage.getallItems();
        outputView.setText("All Items: " + IwantItAll);
    }
    private void clearStorage(){
        dataStorage.ClearStorage();
        outputView.setText("All Items have been deleted");
    }
}