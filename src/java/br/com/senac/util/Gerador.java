/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.util;

/**
 *
 * @author Dell
 */
public class Gerador {
    
    public static String gerarNome(int numero){
        
        String[] letras = {"0","1","2","3","4","5","6","7","8","9","a","b","c",
            "d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t",
            "u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K",
            "L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String nome = "";
        int indice;
        for (int i = 0; i < numero; i++) {
            indice = (int)(Math.random() * letras.length);
            nome += letras[indice];
        }
        return nome;
    }
    
    public static void main(String[] args) {
        System.out.println(gerarNome(5));
    }
}
