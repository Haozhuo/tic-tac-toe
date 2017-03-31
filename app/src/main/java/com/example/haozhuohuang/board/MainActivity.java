package com.example.haozhuohuang.board;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    //0 yellow; 1 red
    int activePlayer = 0;

    //2 means untouched
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        //get which chess is tapped
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        //if the grid is un-tapped
        if(gameState[tappedCounter]==2){
            counter.setTranslationY(-1000f);
            //change the gameState to current active player
            gameState[tappedCounter] = activePlayer;

            if(activePlayer==0){
                //set the image
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }
            else{
                //set the image
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            //animation
            counter.animate().translationYBy(1000f).setDuration(300);

            for(int [] winPos:winningPos){
                if(gameState[winPos[0]] != 2 && gameState[winPos[0]]==gameState[winPos[1]]
                        &&gameState[winPos[1]]==gameState[winPos[2]]){
                    System.out.println(gameState[winPos[0]]);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
