package com.lqt.dodgame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.lqt.dodgame.model.IMiniMax;
import com.lqt.dodgame.model.Minimax;
import com.lqt.dodgame.model.Node;

public class MainActivity extends AppCompatActivity {

    static boolean selectedChessmanMove = false;
    static int selectedFrom = 0;
    static int selectedTo = 0;
    static Minimax minimax = new Minimax();
    static Node nodePresent = new Node(IMiniMax.MAP_ROOT, 3, 1);
    static int idImageViewCell[] = {
            R.id.Image_Cell_0, R.id.Image_Cell_1, R.id.Image_Cell_2,
            R.id.Image_Cell_3, R.id.Image_Cell_4, R.id.Image_Cell_5,
            R.id.Image_Cell_6, R.id.Image_Cell_7, R.id.Image_Cell_8
    };
    static ImageView imageViewCell[] = new ImageView[9];
    static int imageChessman[] = {R.drawable.cell_black_inactive, R.drawable.cell_none, R.drawable.cell_white_inactive};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();

        LoadMap();
    }

    //check can move
    private boolean canMove(int from, int to, int mapP[]) {
        if (mapP[to] == 0) {
            if (to - from == 1 || to - from == 3 || from - to == 3) {
                return true;
            }
        }
        return false;
    }

    //on click for image
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void clickImageCell(View view) {
        int mapPoint[] = nodePresent.getMapPoint();

        //if selected
        if (selectedChessmanMove) {
            selectedTo = Integer.valueOf(view.getTag().toString());
            if (selectedFrom == selectedTo) {
                //reset all value
                selectedChessmanMove = false;
                selectedFrom = -1;
                selectedTo = -1;
                LoadMap();
                return;
            }
            if (canMove(selectedFrom, selectedTo, mapPoint)) {
                //Move
                PlayerMove(selectedFrom, selectedTo);
                Toast.makeText(MainActivity.this, "Move to " + selectedTo, Toast.LENGTH_SHORT).show();
                //BOT move
                BOTMove();

            } else {
                //Select again
                Toast.makeText(MainActivity.this, "You can't select this sell! Try again, please.", Toast.LENGTH_SHORT).show();
                return;
            }

            //reset all value select
            selectedChessmanMove = false;
            selectedFrom = -1;
            selectedTo = -1;
            LoadMap();
        } else {    //if don't selected
            selectedFrom = Integer.valueOf(view.getTag().toString());
            if (mapPoint[selectedFrom] == -1) {
                if (selectedFrom == 2 || selectedFrom == 5 || selectedFrom == 8) {
                    mapPoint[selectedFrom] = 0;
                    nodePresent = new Node(mapPoint, 3, 1);
                    if (!isEndGame()) {
                        BOTMove();
                    } else {
                        showDialogEndGame();
                    }

                    //reset all value
                    selectedChessmanMove = false;
                    selectedFrom = -1;
                    selectedTo = -1;
                    LoadMap();
                    return;
                }
                imageViewCell[selectedFrom].setImageResource(R.drawable.cell_black_active);
                Log.e(MainActivity.class.toString(), "selected");
            } else {
                //Select again
                Toast.makeText(MainActivity.this, "You can't select this sell! Try again, please.", Toast.LENGTH_SHORT).show();
                selectedFrom = -1;
                selectedTo = -1;
                return;
            }

            //reset all value
            selectedChessmanMove = true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void BOTMove() {
        nodePresent = minimax.MiniMaxVal(nodePresent, 3);
        if (isEndGame()) {
            showDialogEndGame();
        }
    }

    private void PlayerMove(int selectedFrom, int selectedTo) {
        int mapPoint[] = nodePresent.getMapPoint();
        int t = mapPoint[selectedFrom];
        mapPoint[selectedFrom] = mapPoint[selectedTo];
        mapPoint[selectedTo] = t;

        nodePresent = new Node(mapPoint, 3, 1);
    }

    private void showDialogEndGame() {
        AlertDialog.Builder diaglog = new AlertDialog.Builder(this);
        diaglog.setMessage("Would you like replay?");
        diaglog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //todo
                ResetGame();
            }
        });
        diaglog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //todo
            }
        });
        diaglog.show();
    }

    private void ResetGame() {
        selectedFrom = -1;
        selectedTo = -1;
        selectedChessmanMove = false;
        nodePresent = new Node(IMiniMax.MAP_ROOT, 3, 1);
        LoadMap();
    }

    private boolean isEndGame() {
        // if count chessman black or chessman white == 0 then end game
        int mapPoint[] = nodePresent.getMapPoint();
        int checkWhite = 0;
        int checkBlack = 0;
        int blackStranded = 2;
        for (int i = 0; i < 9; i++) {
            if (mapPoint[i] == -1) {
                checkBlack++;
//                if (canMove(i, i + 1, mapPoint) && canMove(i, i + 3, mapPoint) && canMove(i, i - 3, mapPoint)) {
//                    blackStranded--;
//                }
            } else if (mapPoint[i] == 1) {
                checkWhite++;
            }
        }
        if (checkBlack == 0 || checkWhite == 0 || blackStranded == 0) {
            return true;
        }
        return false;
    }

    private void LoadMap() {
        //if cell = -1 then imagechess[0] (black color) same 0 and -1
        int mapPoint[] = nodePresent.getMapPoint();
        for (int i = 0; i < 9; i++) {
            if (mapPoint[i] == -1) {
                imageViewCell[i].setImageResource(imageChessman[0]);
            } else if (mapPoint[i] == 0) {
                imageViewCell[i].setImageResource(imageChessman[1]);
            } else if (mapPoint[i] == 1) {
                imageViewCell[i].setImageResource(imageChessman[2]);
            }
        }
    }

    private void InitView() {
        for (int i = 0; i < 9; i++) {
            imageViewCell[i] = findViewById(idImageViewCell[i]);
        }
    }
}