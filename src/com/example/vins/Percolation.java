package com.example.vins;

public class Percolation {
    public static int size = 10;
    private static WQUPC wqupc = new WQUPC(size);
    public static int matrixSize = wqupc.matrix[0].length; //
    public static int j = 0;
    public static int k = 0;

    public static void main(String[] args) {

        for (int i = 0; i < 35; i++){
            open((int) (Math.random()*(99 + 1) + 0));
            percolates();
        }

        do{
            if (j >= matrixSize) j = 0;
            if (!isOpen(0, j)){
                ++j;
            }

            wqupc.union(j,k);
            k++;
        }while (k < matrixSize && j < matrixSize);

        for (int i = 0; i < wqupc.id.length; i++) {
            System.out.println(i + " : " + wqupc.root(i));
        }

        //Display matrix
        for (int i = 0; i < wqupc.matrix.length; i++){
            for (int n = 0; n < wqupc.matrix.length; n++){
                if (wqupc.id[size * i + n] == wqupc.root(size * i + n) && wqupc.matrix[i][n] != -2){
                    wqupc.matrix[i][n] = wqupc.root(size * i + n);
                }
                System.out.print(wqupc.matrix[i][n] + "\t");
            }
            System.out.println();
        }

        System.out.println(percolates());
    }


    public static void open(int n){
        if (n > wqupc.id.length - 1){
            throw new IllegalArgumentException();
        }

        wqupc.matrix[n/wqupc.matrix.length][n%matrixSize] = -1;

        isFull(n/wqupc.matrix.length, n%matrixSize);
    }

    public static  boolean isOpen(int row, int col){
        if (row > matrixSize - 1 || col > matrixSize - 1){
            throw new IllegalArgumentException();
        }
        boolean isOpen = wqupc.matrix[row][col] != -2 ? true: false;
        if (isOpen){
            if (row + 1 <= matrixSize - 1){
                wqupc.union(wqupc.id[size * row + col], wqupc.id[size * (row+1) + col]);
            } if ((row - 1 <= matrixSize - 1 && row-1 >= 0)){
                wqupc.union(wqupc.id[size * row + col], wqupc.id[size * (row-1) + col]);
            } if (col + 1 <= matrixSize - 1){
                wqupc.union(wqupc.id[size * row + col], wqupc.id[size * row + (col+1)]);
            } if ((col - 1 <= matrixSize - 1 && col-1 >= 0)){
                wqupc.union(wqupc.id[size * row + col], wqupc.id[size * row + (col-1)]);
            }
        }
        return isOpen;
    }

    public static boolean isFull(int row, int col){
        if (row > matrixSize - 1 || col > matrixSize - 1){
            throw new IllegalArgumentException();
        }
        if (isOpen(row,col)){
            if (wqupc.root(wqupc.id[size * 0 + j]) == wqupc.root(wqupc.root(wqupc.id[size * row + col]))) return true;
        }
        return false;
    }

    public static int numberOfOpenSites(){
        int sitesOpen = 0;
        for (int i = 0; i < matrixSize; i++){
            for (int j = 0; j < matrixSize; j++){
                if (wqupc.matrix[i][j] == -1){
                    sitesOpen++;
                }
            }
        }
        return sitesOpen;
    }

    public static boolean percolates(){
        int d = 0;
        int x = 0;

        while (!isOpen(matrixSize-1,d) ){
            d++;
            if (d== matrixSize-1 && !isOpen(matrixSize-1, d)) break;
        }

        while (x < matrixSize){
            wqupc.union(wqupc.id[size * (matrixSize-1) + d], wqupc.id[size * (matrixSize-1) + x]);
            x++;
        }

        if (isFull(matrixSize - 1, d)) return true;

        return false;
    }
}

