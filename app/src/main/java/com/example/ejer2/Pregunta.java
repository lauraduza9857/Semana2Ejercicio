package com.example.ejer2;

public class Pregunta {

    //atributos

    private int operandoA;
    private int operandoB;
    private String operador;
    private String[] operadores = { "+", "-", "x", "/"};

    //constructor
    public Pregunta(){

        this.operandoA = (int) (Math.random() * 11);
        this.operandoB = (int) ((Math.random() * 11) + 1);

        int posicion = (int) (Math.random() * 4);
        this.operador = operadores[posicion];

    }

    //métodos
    public String getPregunta(){

        return operandoA + "  " + operador + "  " + operandoB;

    }


    public int getanswerText(){


        int respuesta = 0;
        switch (operador){

            case "+":
                respuesta = this.operandoA + this.operandoB;
                break;

            case "-":
                respuesta = this.operandoA - this.operandoB;
                break;

            case "x":
                respuesta = this.operandoA * this.operandoB;
                break;

            case "/":
                respuesta = this.operandoA / this.operandoB;
                break;

        }

        return respuesta;


    }

}
