package com.example.haozhuohuang.board;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0 yellow; 1 red
    int activePlayer = 0;
    boolean isGameActive = true;
    int gridOccupied = 0;

    //2 means untouched
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        //get which chess is tapped
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        //if the grid is un-tapped
        if(gameState[tappedCounter]==2 && isGameActive){
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

            gridOccupied++;
            //animation
            counter.animate().translationYBy(1000f).setDuration(100);

            if(gridOccupied==9){
                System.out.println("Here");
                isGameActive = false;

                TextView winMessage = (TextView) findViewById(R.id.playAgainText);
                winMessage.setText("It's a draw!");
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);
                return;
            }

            for(int [] winPos:winningPos){
                if(gameState[winPos[0]] != 2 && gameState[winPos[0]]==gameState[winPos[1]]
                        &&gameState[winPos[1]]==gameState[winPos[2]]){
                    String winner = "red";
                    if(gameState[winPos[0]]==0)
                        winner = "yellow";
                    //someone has win a game
                    TextView winMessage = (TextView) findViewById(R.id.playAgainText);
                    winMessage.setText(winner+" win the game");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    isGameActive = false;
                }
            }
        }
    }

    public void playAgain(View view){
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        isGameActive = true;
        gridOccupied = 0;

        activePlayer = 0;
        for(int i = 0; i < gameState.length; i++)
            gameState[i] = 2;

        GridLayout grid = (GridLayout) findViewById(R.id.grid);

        for(int i = 0; i < grid.getChildCount(); i++){
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
