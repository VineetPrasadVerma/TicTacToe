package com.example.vineetprasadverma.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private  boolean player1Turn = true;

    private int roundCounts;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                String buttonId = "button_" + i + j;
                int resId = getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button resetButton = findViewById(R.id.reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {

        if(!((Button)v).getText().toString().equals("")){
            return;
        }

        if(player1Turn){
            ((Button)v).setText("X");
        }else{
            ((Button)v).setText("0");
        }

        roundCounts++;

        if(checkWinner()){
            if(player1Turn){
                player1Wins();
            }else {
                player2Wins();
            }
        }else if(roundCounts  == 9){
            draw();
        }else{
            player1Turn = !player1Turn;
        }
    }

    private boolean checkWinner() {

        String[][] value = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                value[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (value[i][0].equals(value[i][1]) && value[i][0].equals(value[i][2]) && !value[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (value[0][i].equals(value[1][i]) && value[0][i].equals(value[2][i]) && !value[0][i].equals("")) {
                return true;
            }
        }

        if(value[0][0].equals(value[1][1]) && value[0][0].equals(value[2][2]) && !value[0][0].equals("")){
            return true;
        }

        if(value[0][2].equals(value[1][1]) && value[0][2].equals(value[2][0]) && !value[0][2].equals("")){
            return true;
        }

        return false;
    }

    private void player1Wins(){
        player1Points++;
        textViewPlayer1.setText("Player 1 :" + player1Points);
        Toast.makeText(this,"PLayer 1 Wins!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void player2Wins(){
        player2Points++;
        textViewPlayer2.setText("Player 2 :" + player2Points);
        Toast.makeText(this,"PLayer 2 Wins!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void draw(){
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void resetBoard(){
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                buttons[i][j].setText("");
            }
        }
        roundCounts = 0;
    }

    private void resetGame(){
        player1Points = 0;
        player2Points = 0;
        textViewPlayer1.setText("Player 1 : " + player1Points);
        textViewPlayer2.setText("Player 2 : " + player2Points);
        resetBoard();
    }

}
