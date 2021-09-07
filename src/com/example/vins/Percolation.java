package com.example.vins;

public class Percolation {
    public static int size = 3;
    private static WQUPC wqupc = new WQUPC(size);
    public static int matrixSize = wqupc.matrix[0].length; //
    public static int j = 0;
    public static int k = 0;

    public static void main(String[] args) {
        //Open sites here
//        open(2);
//        open(0);
        for (int i = 0; i < 4; i++){
            open((int) (Math.random()*(8 - 0 + 1) + 0));
        }

        //Get the latest open virtual top site at row 0 for virtual top connection
        while (!isOpen(0,j)){
            ++j;
            if (j == matrixSize) break;
            System.out.println(j);
        }

        //Connect the virtual top to all open Row[0] elements
        while (k < matrixSize -1){
            wqupc.union(j, k);
            k++;
        }

//        System.out.println(isFull(2,0));
//        isFull(1,0);
//        for (int i = 0; i < 5; i++){
//            isFull((int) (Math.random()*(4 - 0 + 1) + 0),(int) (Math.random()*(4 - 0 + 1) + 0));
//        }
        for (int i = 0; i < size; i++){
            for (int f = 0; f < size; f++){
                isFull(i,f);
            }
        }
        System.out.println(percolates());

 /*       for (int i = 0; i < wqupc.id.length; i++) {
            System.out.println(i + " : " + wqupc.root(i));
        }*/
        //Display matrix
        for (int i = 0; i < wqupc.matrix.length; i++){
            for (int j = 0; j < wqupc.matrix.length; j++){
                System.out.print(wqupc.matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }


    public static void open(int n){
        if (n > wqupc.id.length - 1){
            throw new IllegalArgumentException();
        }
        wqupc.matrix[n/wqupc.matrix.length][n%matrixSize] = -1;
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
            if (row + 1 <= matrixSize - 1 && isOpen(row+1, col)){
                wqupc.union(wqupc.id[size * row + col], wqupc.id[size * (row+1) + col]);
            } if ((row - 1 <= matrixSize - 1 && row-1 >= 0) && isOpen(row-1, col)){
                wqupc.union(wqupc.id[size * row + col], wqupc.id[size * (row-1) + col]);
            } if (col + 1 <= matrixSize - 1 && isOpen(row, col+1)){
                wqupc.union(wqupc.id[size * row + col], wqupc.id[size * row + (col+1)]);
            } if ((col - 1 <= matrixSize - 1 && col-1 >= 0) && isOpen(row, col-1)){
                wqupc.union(wqupc.id[size * row + col], wqupc.id[size * row + (col-1)]);
            }
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


        if (wqupc.root(wqupc.id[size * 0 + j]) == wqupc.root(wqupc.id[size * (matrixSize-1) + d])){
//            System.out.println(wqupc.root(wqupc.id[size * 0 + j]));
//            System.out.println(wqupc.root(wqupc.id[size * (matrixSize-1) + d]));

            return true;
        }
        return false;
    }
}

