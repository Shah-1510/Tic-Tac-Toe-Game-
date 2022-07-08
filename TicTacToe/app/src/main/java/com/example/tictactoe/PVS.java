package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;
import com.thecode.aestheticdialogs.OnDialogClickListener;

import java.util.Random;


public class PVS extends AppCompatActivity {
    boolean gameActive = true;
    // Player representation
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int Xwin=0;
    int Ywin=0;
    int[] gameState = {2, 2 , 2, 2, 2, 2, 2, 2, 2};
    boolean[] check={true,true,true,true,true,true,true,true,true};
    //    State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};
public int RandomFun(){
    Random rand =new Random();
    int upperbound=9;
    int int_random = rand.nextInt(upperbound);
    if(check[int_random]==false){
        return RandomFun();
    }else{
        return int_random;
    }
}
    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            check[tappedImage]=false;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("Your Turn - Tap to play");
                boolean doubleCheck=false;
                for(int i=0; i<gameState.length; i++){
                    if(gameState[i]==2){
                        doubleCheck=true;
                         break;
                    }
                }

                for(int[] winPosition: winPositions){
                    if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                            gameState[winPosition[1]] == gameState[winPosition[2]] &&
                            gameState[winPosition[0]]!=2){
                        // Somebody has won! - Find out who!
                        String winnerStr;
                        gameActive = false;
                        if(gameState[winPosition[0]] == 0){
                            winnerStr = "X has won";

                        }
                        else{
                            winnerStr = "O has won";
                        }

                        // Update the status bar for winner announcement
                        TextView statuss = findViewById(R.id.status);
                        statuss.setText(winnerStr);
                        TextView textView=findViewById(R.id.textView);
                        textView.setText("Player X wins: "+String.valueOf(Xwin)+"\nBOT 'O' wins: "+String.valueOf(Ywin));

                    }




                }





                if(doubleCheck && gameActive){

                int getRan=RandomFun();
                gameState[getRan]=activePlayer;
                check[getRan]=false;
                if(getRan==0){
                    ((ImageView)findViewById(R.id.imageView0)).setImageResource(R.drawable.o);
                    img.animate().translationYBy(1000f).setDuration(300);
                } else if (getRan==1) {
                    ((ImageView)findViewById(R.id.imageView1)).setImageResource(R.drawable.o);
                    img.animate().translationYBy(1000f).setDuration(300);
                }
                else if (getRan==2) {
                    ((ImageView)findViewById(R.id.imageView2)).setImageResource(R.drawable.o);
                    img.animate().translationYBy(1000f).setDuration(300);
                }
                else if (getRan==3) {
                    ((ImageView)findViewById(R.id.imageView3)).setImageResource(R.drawable.o);
                    img.animate().translationYBy(1000f).setDuration(300);
                }
                else if (getRan==4) {
                    ((ImageView)findViewById(R.id.imageView4)).setImageResource(R.drawable.o);
                    img.animate().translationYBy(1000f).setDuration(300);
                }
                else if (getRan==5) {
                    ((ImageView)findViewById(R.id.imageView5)).setImageResource(R.drawable.o);
                    img.animate().translationYBy(1000f).setDuration(300);
                }
                else if (getRan==6) {
                    ((ImageView)findViewById(R.id.imageView6)).setImageResource(R.drawable.o);
                    img.animate().translationYBy(1000f).setDuration(300);
                }
                else if (getRan==7) {
                    ((ImageView)findViewById(R.id.imageView7)).setImageResource(R.drawable.o);
                    img.animate().translationYBy(1000f).setDuration(300);
                }
                else if (getRan==8) {
                    ((ImageView)findViewById(R.id.imageView8)).setImageResource(R.drawable.o);
                    img.animate().translationYBy(1000f).setDuration(300);
                }
                }
                activePlayer=0;

            }
//            else {
//                img.setImageResource(R.drawable.o);
//                activePlayer = 0;
//                TextView status = findViewById(R.id.status);
//                status.setText("X Bitch Turn - Tap to play");
//            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        // Check if any player has won
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2){
                // Somebody has won! - Find out who!
                String winnerStr;
                gameActive = false;
                if(gameState[winPosition[0]] == 0){
                    winnerStr = "X has won";
                    Xwin++;
                    new AestheticDialog.Builder(this, DialogStyle.EMOTION, DialogType.SUCCESS)
                            .setAnimation(DialogAnimation.SPIN)
                            .setTitle("THE WINNER IS !!")
                            .setMessage("Player 1")
                            .show();
                }
                else{
                    winnerStr = "O has won";
                    Ywin++;
                    new AestheticDialog.Builder(this, DialogStyle.EMOTION, DialogType.SUCCESS)
                            .setAnimation(DialogAnimation.SPIN)
                            .setTitle("THE WINNER IS !!")
                            .setMessage("BOT")
                            .show();
                }

                // Update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
                TextView textView=findViewById(R.id.textView);
                textView.setText("Player X wins: "+String.valueOf(Xwin)+"\nBOT 'O' wins: "+String.valueOf(Ywin));

            }




        }
        boolean emptySquare = false;
        for (int squareState : gameState) {
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }
        if (!emptySquare && gameActive) {
            // Game is a draw
            gameActive = false;
            String winnerStr;
            winnerStr = "No one won";
            TextView status = findViewById(R.id.status);
            status.setText(winnerStr);
            new AestheticDialog.Builder(this, DialogStyle.EMOTION, DialogType.ERROR)
                    .setAnimation(DialogAnimation.FADE)
                    .setTitle("IT'S A DRAW !!")
                    .setMessage("No One Won")
                    .show();
        }
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
            check[i]=true;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");

    }

    ImageView back_pvs_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_v_s);

        back_pvs_btn=findViewById(R.id.back_pvs_btn);
        back_pvs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MiddleScreen.class);
                startActivity(i);
            }
        });
    }
}


