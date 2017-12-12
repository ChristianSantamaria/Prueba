package com.example.csantamariacameselle.prueba;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Integer> colores = new ArrayList<Integer>();

    Boolean TuTurno = false;

    Button botonRojo;
    Button botonAzul;
    Button botonVerde;
    Button botonAmarillo;
    Button botonStart;

    TextView Puntuacion;

    int repeticion = 0;


    void runColor(final Integer color) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (color) {
                    case 1:
                        botonRojo.setBackgroundColor(botonRojo.getContext().getResources().getColor(R.color.colorRojo));
                        break;
                    case 2:
                        botonVerde.setBackgroundColor(botonVerde.getContext().getResources().getColor(R.color.colorVerde));
                        break;
                    case 3:
                        botonAzul.setBackgroundColor(botonAzul.getContext().getResources().getColor(R.color.colorAzul));
                        break;
                    case 4:
                        botonAmarillo.setBackgroundColor(botonAmarillo.getContext().getResources().getColor(R.color.colorAmarillo));
                        break;
                }
            }
        });
    }

    void runSequence() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    for (Integer color : colores) {
                        if (color == 1) {
                            Thread.sleep(250);
                            System.out.println("Duerme 1.");
                            runColor(color);
                            Thread.sleep(1000);
                            ApagarBotones();

                        } else if (color == 2) {
                            Thread.sleep(250);
                            System.out.println("Duerme 2.");
                            runColor(color);
                            Thread.sleep(1000);
                            ApagarBotones();

                        } else if (color == 3) {
                            Thread.sleep(250);
                            System.out.println("Duerme 3.");
                            runColor(color);
                            Thread.sleep(1000);
                            ApagarBotones();

                        } else if (color == 4) {
                            Thread.sleep(250);
                            System.out.println("Duerme 4.");
                            runColor(color);
                            Thread.sleep(1000);
                            ApagarBotones();

                        }
                    }
                } catch (InterruptedException e) {
                }
            };
        }).start();
    }

    void runClick(final int color) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    if (color == 1) {
                        runColor(color);
                        Thread.sleep(500);
                        ApagarBotones();

                    } else if (color == 2) {
                        runColor(color);
                        Thread.sleep(500);
                        ApagarBotones();

                    } else if (color == 3) {


                        runColor(color);
                        Thread.sleep(500);
                        ApagarBotones();

                    } else if (color == 4) {

                        runColor(color);
                        Thread.sleep(500);
                        ApagarBotones();

                    }
                } catch (InterruptedException e) {
                }
            };
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonRojo = (Button) findViewById(R.id.buttonRojo);
        botonAzul = (Button) findViewById(R.id.buttonAzul);
        botonVerde = (Button) findViewById(R.id.buttonVerde);
        botonAmarillo = (Button) findViewById(R.id.buttonAmarillo);

        botonStart = (Button) findViewById(R.id.btnStart);
        Puntuacion = (TextView) findViewById(R.id.textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                startGame(v);
                break;
            case R.id.buttonRojo:
                runClick(1);
                VerificarCombinacion(1, v);
                break;
            case R.id.buttonVerde:
                runClick(2);
                VerificarCombinacion(2, v);
                break;
            case R.id.buttonAzul:
                runClick(3);
                VerificarCombinacion(3, v);
                break;
            case R.id.buttonAmarillo:
                runClick(4);
                VerificarCombinacion(4, v);
                break;
        }
    }

    public void startGame(View v) {
        if (!TuTurno) {
            CrearColor(v);
            botonStart.setVisibility(View.INVISIBLE);

        }
    }


    public void CrearColor(View v) {
        Integer ColorRandom = 1 + (int) (Math.random() * 4);
        colores.add(ColorRandom);
        runSequence();
        TuTurno = true;
    }

    public void VerificarCombinacion(Integer num, View v) {
        if(colores.size() != 0) {
            if (repeticion <= colores.size()) {
                if (num == colores.get(repeticion)) {
                    repeticion++;
                    if (repeticion == colores.size()) {
                        Puntuacion.setText("Puntiacion: " + repeticion + "0");
                        repeticion = 0;
                        TuTurno = false;
                        startGame(v);
                    }
                } else {
                    botonStart.setVisibility(View.VISIBLE);
                    Toast.makeText(this, "Has Perdido... Prueba otra vez", Toast.LENGTH_SHORT).show();
                    repeticion = 0;
                    colores.clear();
                }
            }
        }
    }


    public void ApagarBotones() {
        botonRojo.setBackgroundColor(botonRojo.getContext().getResources().getColor(R.color.colorRojoM));
        botonVerde.setBackgroundColor(botonVerde.getContext().getResources().getColor(R.color.colorVerdeM));
        botonAzul.setBackgroundColor(botonAzul.getContext().getResources().getColor(R.color.colorAzulM));
        botonAmarillo.setBackgroundColor(botonVerde.getContext().getResources().getColor(R.color.colorAmarilloM));
    }


}