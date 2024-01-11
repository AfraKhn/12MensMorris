package com.example.a12manmorriswithjava.ActivityControllers;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a12manmorriswithjava.Classes.ButtonsInfo;
import com.example.a12manmorriswithjava.Classes.Player;
import com.example.a12manmorriswithjava.Classes.Taw;
import com.example.a12manmorriswithjava.Interfaces.IArena;
import com.example.a12manmorriswithjava.R;

import java.util.HashMap;
import java.util.Map;

public class Arena extends AppCompatActivity implements IArena {
    com.example.a12manmorriswithjava.Classes.Arena arena = new com.example.a12manmorriswithjava.Classes.Arena();
    Player player1 = new Player(Taw.BLUE, arena);
    Player player2 = new Player(Taw.RED, arena);
    Map<Taw, Player> players = new HashMap<>();
    private TextView tw_red;
    private TextView tw_blue;
    private Button btn0, btn1, btn2,
            btn3, btn4, btn5, btn6, btn7,
            btn8, btn9, btn10, btn11, btn12,
            btn13, btn14, btn15, btn16, btn17,
            btn18, btn19, btn20, btn21, btn22, btn23;
    boolean fly_blue;
    boolean fly_red;
    String state = PUT;
    int[] buttonsColors = new int[]{
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    };
    int selectedIndex;
    Button selectedButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);

        players.put(player1.getTawType(), player1);
        players.put(player2.getTawType(), player2);
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

    @SuppressLint("ClickableViewAccessibility")
    public void btnClicked(Button btn, int index) {
//      Toast.makeText(Arena.this, "state is " + state, Toast.LENGTH_SHORT).show();
        Player playerred = players.get(Taw.RED);
        Player playerblue = players.get(Taw.BLUE);
        //Toast.makeText(Arena.this, "Blue is " + playerblue.getTawsOnHand() + "and red is " + playerred.getTawsOnHand(), Toast.LENGTH_SHORT).show();
//==================================================Start Of Put==============================================
        if (state.equals(PUT)) {
            //bayad kari konim check kone ke taw dare ke bezare , player morede nazar
            boolean isPutable;
            isPutable = arena.putTaw(arena.getTurn(), index);
            //Toast.makeText(Arena.this, "putable is " + isPutable , Toast.LENGTH_SHORT).show();
            if (isPutable) {
                arena.changeTurn();
                //  Toast.makeText(Arena.this, "What got put down is  " + arena.getTurn(), Toast.LENGTH_SHORT).show();
                // Put Taw Down
                boolean scored = arena.checkScored(index);
                if (arena.getTurn() == Taw.BLUE) {
                    buttonsColors[index] = BLUE;
                    make_btn_Black(btn);
                    Player p_blue = players.get(Taw.BLUE);
                    tw_blue.setText(p_blue.getTawsOnHand() + "");
                    Player p_red = players.get(Taw.RED);
                    if (p_red.getTawsOnHand() == 0 && state.equals(PUT)) {
                        state = SELECT;
                        checkBlueWon();
                        checkRedWon();
                        if (state.equals(BLUEWON) || state.equals(REDWON)) {
                            somebody_Wins();
                        }
                    }
                }
                if (arena.getTurn() == Taw.RED) {
                    buttonsColors[index] = RED;
                    make_btn_White(btn);

                    Player p_red = players.get(Taw.RED);
                    tw_red.setText(p_red.getTawsOnHand() + "");

                    Player p_blue = players.get(Taw.BLUE);
                    if (p_blue.getTawsOnHand() == 0 && state.equals(PUT)) {
                        state = SELECT;
                        checkBlueWon();
                        checkRedWon();
                        if (state.equals(BLUEWON) || state.equals(REDWON)) {
                            somebody_Wins();
                        }
                    }
                }
                if (scored) {
                    Toast.makeText(Arena.this, "State turned into DELETE", Toast.LENGTH_SHORT).show();
                    arena.changeTurn();
                    state = DELETE;
                }
                arena.changeTurn();
            } else {

            }

        }

//===================================================End Of Put==============================================

//===================================================Start Of Delete===========================================
        else if (state.equals(DELETE)) {
            int color = buttonsColors[index];
            if (arena.getTurn() == Taw.BLUE && color == RED) {
                arena.del(index);
                arena.changeTurn();
                make_btn_default(btn);
                buttonsColors[index] = DEFAULT;
                Player red = players.get(Taw.RED);
                if (red.getTawsOnHand() == 0) {
                    state = SELECT;
                    checkBlueWon();
                    checkRedWon();
                    if (state.equals(BLUEWON) || state.equals(REDWON)) {
                        somebody_Wins();
                    }
                } else {
                    state = PUT;
                }
                if (red.getTawsOnHand() <= 0 && taws_On_Board(RED) < 4) {
                    fly_red = true;
                }


            }
            if (arena.getTurn() == Taw.RED && color == BLUE) {
                Player reddd = players.get(Taw.BLUE);
                //Toast.makeText(Arena.this, "deleting index " + index + " taw on hand of blue " + reddd.getTawsOnHand(), Toast.LENGTH_SHORT).show();
                arena.del(index);
                arena.changeTurn();
                make_btn_default(btn);
                buttonsColors[index] = DEFAULT;
                //arena.changeTurn();
                // age dokmehaye abi 0 bashe mire halate SELECT , age na mire halate PUT
                Player blue = players.get(Taw.BLUE);
                if (blue.getTawsOnHand() == 0) {
                    state = SELECT;
                    checkBlueWon();
                    checkRedWon();
                    if (state.equals(BLUEWON) || state.equals(REDWON)) {
                        somebody_Wins();
                    }
                } else {
                    state = PUT;
                }
                if (blue.getTawsOnHand() <= 0 && taws_On_Board(BLUE) < 4) {
                    fly_blue = true;
                }
            }
            if (state.equals(BLUEWON) || state.equals(REDWON)) {
                somebody_Wins();
            }
        }
//===============================================End Of Delete==================================================

// ta vagti ke nare state delete , turn avaz nmishe
//==============================================Start Of move===================================================
        else if (state.equals(MOVE)) {
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
                if (color == RED) {
                    make_btn_White_Selected(btn);
                    make_btn_White(selectedButton);
                    selectedButton = btn;
                    selectedIndex = index;
                }
            }
//====================================================CHANGE MIND=============================================================
            //fly
            else if (canFly(selectedColor)) {
                boolean isFlyable = arena.fly(selectedIndex, index);
                if (selectedColor == BLUE) {
                    if (isFlyable) {
                        make_btn_Black(btn);
                        make_btn_default(selectedButton);
                        state = SELECT;
                        buttonsColors[index] = BLUE;
                        buttonsColors[selectedIndex] = DEFAULT;
                    }
                }

                if (selectedColor == RED) {
                    if (isFlyable) {
                        make_btn_White(btn);
                        make_btn_default(selectedButton);
                        state = SELECT;
                        buttonsColors[index] = RED;
                        buttonsColors[selectedIndex] = DEFAULT;
                    }

                }
                if (isFlyable && arena.checkScored(index)) {
                    arena.changeTurn();
                    state = DELETE;
                }

            }
            //move
            else {
                boolean isMovable = arena.move(selectedIndex, index);
                if (isMovable) {
                    Toast.makeText(Arena.this, "in move",Toast.LENGTH_SHORT).show();
                    if (selectedColor == BLUE) {
                        make_btn_Black(btn);
                        make_btn_default(selectedButton);
                        buttonsColors[index] = BLUE;
                        buttonsColors[selectedIndex] = DEFAULT;
                    }
                    if (selectedColor == RED) {
                        make_btn_White(btn);
                        make_btn_default(selectedButton);
                        buttonsColors[index] = RED;
                        buttonsColors[selectedIndex] = DEFAULT;
                    }
                    state = SELECT;
                    checkBlueWon();
                    checkRedWon();
                    if (state.equals(BLUEWON) || state.equals(REDWON)) {
                        somebody_Wins();
                    }
                }
                if (isMovable && arena.checkScored(index)) {
                    arena.changeTurn();
                    state = DELETE;
                }

            }

        }
//===============================================End Of move===================================================

//===============================================Start Of Select==================================================
        else if (state.equals(SELECT)) {
            //befarma
            int color = buttonsColors[index];
            if (arena.getTurn() == Taw.BLUE && color == BLUE) {
                make_btn_Black_Selected(btn);
                selectedButton = btn;
                selectedIndex = index;
                state = MOVE;
            }
            if (arena.getTurn() == Taw.RED && color == RED) {
                make_btn_White_Selected(btn);
                selectedButton = btn;
                selectedIndex = index;
                state = MOVE;
            }
        }
//================================================End Of Select==================================================

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

    public boolean canFly(int color) {
        boolean result = false;
        if (color == RED) {
            result = fly_red;
        }
        if (color == BLUE) {
            result = fly_blue;
        }

        return result;
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

    public void alert_win(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle("Game Ended!");
        builder.setPositiveButton("Restart The Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Arena.this, Arena.class);
                startActivity(intent);

            }
        });
        builder.setNegativeButton("Back To Main", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Arena.this, MainActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void somebody_Wins() {
        if (REDWON.equals(state)) {
            alert_win("Player Red Won The Game!!");
        }
        if (state.equals(BLUEWON)) {
            alert_win("Player Blue Won The Game");
        }
    }


}


