package com.example.s530742.chandaluri_program05;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Naveen Kumar Chandaluri on 2/26/2018.
 */

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> symbols = new ArrayList<>();
    private ArrayList<String> code = new ArrayList<>();
    private ArrayList<String> listSecretCode = new ArrayList<>();
    private TextView guessTv,attempts,guessed,size;
    private CodeWord codeWord;
    private ListView list;
    private Button retry_button,undo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guessTv = (findViewById(R.id.guess));
        attempts = (findViewById(R.id.attempts));
        guessed = (findViewById(R.id.guessed));
        retry_button = (findViewById(R.id.retry));
        list = (findViewById(R.id.symbol));
        size = (findViewById(R.id.size));
        undo = (findViewById(R.id.undo));
        retry_button.setEnabled(false);
        undo.setEnabled(false);
        Random random = new Random();

            codeWord = new CodeWord(3+random.nextInt(3));
            symbols = codeWord.getSymbols();
            size.setText(codeWord.getCode().size()+"");
            System.out.println(codeWord.getCode());

        final ListAdapter listSource = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, symbols) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View v = super.getView(position, convertView, parent);
                    return v;
                }
            };
            final ListView symbolTV = findViewById(R.id.symbol);
            symbolTV.setAdapter(listSource);

            symbolTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String val =(String) adapterView.getItemAtPosition(i);
                    code.add(val);
                    codeWord.setGuess(code);
                    guessTv.setText(null);
                    if(codeWord.getGuess().size()<codeWord.getCode().size())
                    {
                        if(codeWord.getGuess().size() == 0)
                            undo.setEnabled(false);
                        else
                            undo.setEnabled(true);
                    }


                    if(codeWord.getGuess().size()== codeWord.getCode().size())
                    {
                        if(codeWord.getGuess().equals(codeWord.getCode()))
                        {
                            AlertDialog dialogBox = new AlertDialog.Builder(MainActivity.this).create();
                            dialogBox.setTitle("Success!!");
                            dialogBox.setMessage(" Secret code Matched!!");
                            dialogBox.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            recreate();
                                        }
                                    });
                            dialogBox.show();
                            codeWord.setStatus(true);
                            undo.setEnabled(false);
                            listSecretCode.clear();
                        }else {
                            retry_button.setEnabled(true);
                            list.setEnabled(false);

                            codeWord.setAttempts(codeWord.getAttempts()+1);
                            attempts.setText(codeWord.getAttempts()+"");
                            listSecretCode.add(codeWord.getGuess().toString());


                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog.setTitle("Secret code Not Matched!!");
                            if(listSecretCode.size() == 0)
                            alertDialog.setMessage("No of Attempts :" + codeWord.getAttempts());
                            else {
                                StringBuilder sb = new StringBuilder();
                                sb.append("No. of attempts: " + codeWord.getAttempts());
                                sb.append("\n");
                                for(int s=0;s<listSecretCode.size();s++) {
                                    sb.append(listSecretCode.get(s).toString().replaceAll("\\[", " ").replaceAll("\\]", "")+"\n");
                                }
                                alertDialog.setMessage(sb);
                            }
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                            undo.setEnabled(false);
                            if(codeWord.getAttempts() == 10)
                            {
                                alertDialog.setTitle("Not Matched for 10 attempts!!");
                                alertDialog.setMessage("The Secret Code is: "+ codeWord.getCode());

                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();
                                recreate();
                            }
                        }
                    }

                    for(int g=0;g<codeWord.getGuess().size();g++)
                    {
                        if(codeWord.getGuess().get(g).equals(codeWord.getCode().get(g)))
                        guessTv.append(codeWord.getGuess().get(g)+" ");
                        else
                            guessTv.append("-");
                    }
                    int count = 0;
                    for(int c=0;c<codeWord.getGuess().size();c++)
                    {
                        if(codeWord.getGuess().size()<=codeWord.getCode().size()) {
                            if (codeWord.getCode().get(c).equals(codeWord.getGuess().get(c))) {
                                count++;
                            }
                        }
                    }
                    attempts.setText(codeWord.getAttempts()+"");
                    guessed.setText(count+"");
                }
            });
    }
    public void retry(View v)
    {
        if(codeWord.getGuess().size() != 0) {
            retry_button.setEnabled(false);
            list.setEnabled(true);
            codeWord.getGuess().clear();
            guessTv.setText(null);
            if(codeWord.getGuess().size()== codeWord.getCode().size())
            {
                retry_button.setEnabled(true);
            }
            int count = 0;
            for(int c=0;c<codeWord.getGuess().size();c++)
            {
                if(codeWord.getGuess().size()<=codeWord.getCode().size()) {
                    if (codeWord.getCode().get(c).equals(codeWord.getGuess().get(c))) {
                        count++;
                    }
                }
            }
            guessed.setText(count+"");
            guessTv.setText(null);
            for (int ii = 0; ii < codeWord.getGuess().size(); ii++) {
                guessTv.append(codeWord.getGuess().get(ii) + " ");
            }
        }else{
            undo.setEnabled(false);
        }
    }
    public void undo(View v)
    {
        if(codeWord.getGuess().size() != 0) {
            codeWord.getGuess().remove(codeWord.getGuess().size()-1);

            if(codeWord.getGuess().size()== codeWord.getCode().size())
            {
                retry_button.setEnabled(false);
            }

            int count = 0;
            for(int c=0;c<codeWord.getGuess().size();c++)
            {
                if(codeWord.getGuess().size()<=codeWord.getCode().size()) {
                    if (codeWord.getCode().get(c).equals(codeWord.getGuess().get(c))) {
                        count++;
                    }
                }
            }
            guessed.setText(count+"");
            guessTv.setText(null);
            for (int ii = 0; ii < codeWord.getGuess().size(); ii++) {
                if(codeWord.getGuess().get(ii).equals(codeWord.getCode().get(ii)))
                    guessTv.append(codeWord.getGuess().get(ii)+" ");
                else
                    guessTv.append("-");
            }
        }
    }

    public void reset(View v)
    {
        recreate();
    }
}

