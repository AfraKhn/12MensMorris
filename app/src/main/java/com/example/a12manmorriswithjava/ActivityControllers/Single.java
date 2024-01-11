package com.example.a12manmorriswithjava.ActivityControllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a12manmorriswithjava.Interfaces.IArena;
import com.example.a12manmorriswithjava.Interfaces.ISetting;
import com.example.a12manmorriswithjava.R;
import com.example.a12manmorriswithjava.SinglePlayerClasses.Arena;
import com.example.a12manmorriswithjava.SinglePlayerClasses.Player;
import com.example.a12manmorriswithjava.SinglePlayerClasses.Taw;

import java.util.HashMap;
import java.util.Map;

public class Single extends AppCompatActivity implements IArena, ISetting {
    SharedPreferences pref;
    Arena arena;
    Player player1;
    Player player2;
    Map<Taw, Player> players;
    private TextView tw_red;
    private TextView tw_blue;
    private Button btn0, btn1, btn2,
            btn3, btn4, btn5, btn6, btn7,
            btn8, btn9, btn10, btn11, btn12,
            btn13, btn14, btn15, btn16, btn17,
            btn18, btn19, btn20, btn21, btn22, btn23;
    String state = PUT;
    int[] buttonsColors = new int[]{
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    };
    private Button selectedButton;
    private int selectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);


        arena = new Arena();
        player1 = new Player(Taw.BLUE, arena);
        player2 = new Player(Taw.RED, arena);
        players = new HashMap<>();
        pref = getApplicationContext().getSharedPreferences(PREF, 0);
        String diff = pref.getString(DIFFICULTY_STATE, "");
        if (diff.equals("Easy")) {
            arena.setGameMode(1);
        }
        if (diff.equals("Medium")) {
            arena.setGameMode(2);
        }
        if (diff.equals("Hard")) {
            arena.setGameMode(3);
        }


        players.put(Taw.BLUE, player1);
        players.put(Taw.RED, player2);
        arena.setPlayers(players);

        tw_blue = findViewById(R.id.tw_blue);
        tw_red = findViewById(R.id.tw_red);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);
        btn11 = findViewById(R.id.btn11);
        btn12 = findViewById(R.id.btn12);
        btn13 = findViewById(R.id.btn13);
        btn14 = findViewById(R.id.btn14);
        btn15 = findViewById(R.id.btn15);
        btn16 = findViewById(R.id.btn16);
        btn17 = findViewById(R.id.btn17);
        btn18 = findViewById(R.id.btn18);
        btn19 = findViewById(R.id.btn19);
        btn20 = findViewById(R.id.btn20);
        btn21 = findViewById(R.id.btn21);
        btn22 = findViewById(R.id.btn22);
        btn23 = findViewById(R.id.btn23);
        btn0.setOnClickListener((view) -> btnClicked(btn0, 0));
        btn1.setOnClickListener((view) -> btnClicked(btn1, 1));
        btn2.setOnClickListener((view) -> btnClicked(btn2, 2));
        btn3.setOnClickListener((view) -> btnClicked(btn3, 3));
        btn4.setOnClickListener((view) -> btnClicked(btn4, 4));
        btn5.setOnClickListener((view) -> btnClicked(btn5, 5));
        btn6.setOnClickListener((view) -> btnClicked(btn6, 6));
        btn7.setOnClickListener((view) -> btnClicked(btn7, 7));
        btn8.setOnClickListener((view) -> btnClicked(btn8, 8));
        btn9.setOnClickListener((view) -> btnClicked(btn9, 9));
        btn10.setOnClickListener((view) -> btnClicked(btn10, 10));
        btn11.setOnClickListener((view) -> btnClicked(btn11, 11));
        btn12.setOnClickListener((view) -> btnClicked(btn12, 12));
        btn13.setOnClickListener((view) -> btnClicked(btn13, 13));
        btn14.setOnClickListener((view) -> btnClicked(btn14, 14));
        btn15.setOnClickListener((view) -> btnClicked(btn15, 15));
        btn16.setOnClickListener((view) -> btnClicked(btn16, 16));
        btn17.setOnClickListener((view) -> btnClicked(btn17, 17));
        btn18.setOnClickListener((view) -> btnClicked(btn18, 18));
        btn19.setOnClickListener((view) -> btnClicked(btn19, 19));
        btn20.setOnClickListener((view) -> btnClicked(btn20, 20));
        btn21.setOnClickListener((view) -> btnClicked(btn21, 21));
        btn22.setOnClickListener((view) -> btnClicked(btn22, 22));
        btn23.setOnClickListener((view) -> btnClicked(btn23, 23));

    }

    private void btnClicked(Button btn, int index) {
//                                                                                                         PUT

//======================================================================================
        if (state.equals(PUT)) {
            player1.putTaw(index);
            buttonsColors[index] = BLUE;
            make_btn_Black(btn);
            while (arena.getTurn() == player2.getTawType()) {
                int best = arena.bestNode();
                player2.putTaw(best);
                buttonsColors[best] = RED;
                make_btn_White(getIndexOfButton(best));
            }
            if (player1.getTawsOnHand() == 0) {
                state = SELECT;
                checkBlueWon();
                checkRedWon();
                if (state.equals(BLUEWON) || state.equals(REDWON)) {
                    somebody_Wins();
                }
            }
        } else if (state.equals(DELETE)) {
            int color = buttonsColors[index];
            if (color == RED) {
                arena.del(index);
                player1.setCatchedTaws(player1.getCatchedTaws() + 1);
                state = SELECT;
                make_btn_default(btn);
                buttonsColors[index] = DEFAULT;
                if (taws_On_Board(RED) < 3) {
                    somebody_Wins();
                }
                computer_move();
            }


        } else if (state.equals(MOVE)) {
            int color = buttonsColors[index];
            int selectedColor = buttonsColors[selectedIndex];
            // ==================================================CHANGE MIND============================================================
            if (color == selectedColor && btn != selectedButton) {
                state = MOVE;
                if (color == BLUE) {
                    make_btn_Black_Selected(btn);
                    make_btn_Black(selectedButton);
                    selectedButton = btn;
                    selectedIndex = index;
                }
            }
            // target = index , index = selectedIndex
            else {
                player1.moveTaw(selectedIndex, index);
                if (arena.checkScored(index)) {
                    state = DELETE;
                } else {
                    computer_move();

                }


            }


        } else if (state.equals(SELECT)) {
            int color = buttonsColors[index];
            if (color == BLUE) {
                make_btn_Black_Selected(btn);
                selectedButton = btn;
                selectedIndex = index;
                state = MOVE;
            }
        }
    }

    public void make_btn_Black(Button btn) {
        btn.setBackgroundResource(R.drawable.btn_black);
    }

    public void make_btn_Black_Selected(Button btn) {
        btn.setBackgroundResource(R.drawable.selected_black);
    }

    public void make_btn_White_Selected(Button btn) {
        btn.setBackgroundResource(R.drawable.selected_white);
    }

    public void make_btn_White(Button btn) {
        btn.setBackgroundResource(R.drawable.btn_white);
    }

    public void make_btn_default(Button btn) {
        btn.setBackgroundResource(R.drawable.btn_default);
    }

    private Button getIndexOfButton(int index) {
        switch (index) {
            case 0:
                return btn0;
            case 1:
                return btn1;

            case 2:
                return btn2;

            case 3:
                return btn3;

            case 4:
                return btn4;

            case 5:
                return btn5;

            case 6:
                return btn6;

            case 7:
                return btn7;

            case 8:
                return btn8;

            case 9:
                return btn9;

            case 10:
                return btn10;

            case 11:
                return btn11;

            case 12:
                return btn12;

            case 13:
                return btn13;

            case 14:
                return btn14;

            case 15:
                return btn15;

            case 16:
                return btn16;

            case 17:
                return btn17;
            case 18:
                return btn18;

            case 19:
                return btn19;
            case 20:
                return btn20;

            case 21:
                return btn21;

            case 22:
                return btn22;

            case 23:
                return btn23;

            default:
                return null;
        }


    }

    //ghermez zamani mibare ke abi natoone harekat kone
    public void checkRedWon() {
        String preState = state;
        state = REDWON;
        for (int i = 0; i < 24; i++) {
            // i=selectedIndex
            if (buttonsColors[i] == BLUE) {
                for (int j = 0; j < 24; j++) {
                    // j=target
                    if (buttonsColors[j] == DEFAULT) {
                        boolean isMovable = arena.check_Move(i, j);
                        //mitoone harekat kone pas STATE be halate ghabli barmigarde
                        if (isMovable) {
                            state = preState;//age ismovable = true changeturn mikone
                            break;
                        }
                    }
                }
                //break;
            }
        }
    }

    //abi zamani mibare ke ghermez natoone harekat kone
    public void checkBlueWon() {
        String preState = state;
        state = BLUEWON;
        for (int i = 0; i < 24; i++) {
            // i=selectedIndex
            if (buttonsColors[i] == RED) {
                for (int j = 0; j < 24; j++) {
                    // j=target
                    if (buttonsColors[j] == DEFAULT) {
                        boolean isMovable = arena.check_Move(i, j);
                        //mitoone harekat kone pas STATE be halate ghabli barmigarde
                        if (isMovable) {
                            state = preState;//age ismovable = true changeturn mikone
                            break;
                        }
                    }
                }
                // break;
            }
        }

    }

    public void somebody_Wins() {
        if (REDWON.equals(state)) {
            alert_win("Player Red Won The Game!!");
        }
        if (state.equals(BLUEWON)) {
            alert_win("Player Blue Won The Game");
        }
    }

    public void alert_win(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle("Game Ended!");
        builder.setPositiveButton("Restart The Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Single.this, Single.class);
                startActivity(intent);

            }
        });
        builder.setNegativeButton("Back To Main", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Single.this, MainActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public int taws_On_Board(int color) {
        int counter = 0;
        for (int i = 0; i < 24; i++) {
            if (buttonsColors[i] == color) {
                counter++;
            }
        }
        if (counter == 2) {
            if (color == RED) {
                state = BLUEWON;
            }
            if (color == BLUE) {
                state = REDWON;
            }
        }
        return counter;
    }

    public void computer_move() {
        int[] arr = arena.bestMovement();
        player2.moveTaw(arr[0], arr[1]);
        if (arr[2] != -1) {
            arena.del(arr[2]);
            buttonsColors[arr[2]] = DEFAULT;
            make_btn_default(getIndexOfButton(arr[2]));
        }
        make_btn_default(getIndexOfButton(arr[0]));
        make_btn_White(getIndexOfButton(arr[1]));
        buttonsColors[arr[0]] = DEFAULT;
        buttonsColors[arr[1]] = RED;
    }
}