package com.example.vins;

public class WQUPC {
    public  int[] id;
    public int[][] matrix;
    public  byte[] sz;

    public WQUPC(int n){
        int x = n*n;
        id = new int[x];
        sz = new byte[x];
        matrix = new int[n][n];
        int ctr = 0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                matrix[i][j] = -2;
                id[ctr] = ctr;
                sz[ctr] = 1;
                ctr++;
            }

        }
    }

    public  int root(int i){
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }

//        matrix[i/matrix.length][i%matrix[0].length] = i;

        return i;
    }

    public  boolean connected (int p, int q){
        return root(p) == root(q);
    }

    public  void union (int p , int q) {
        int i = root(p);
        int j = root(q);

        if(matrix[i/matrix.length][i%matrix[0].length] == -2 ||
                matrix[j/matrix.length][j%matrix[0].length] == -2){
//            System.out.println("Either both paths or one of the paths are closed");
            return;
        }
        if (i == j) {
            return;
        }

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
            matrix[i/ matrix.length][i%matrix[0].length] = j;

        } else {
            id[j] = i;
            sz[i] += sz[j];
            matrix[j/ matrix.length][j%matrix[0].length] = i;

        }

    }
}




































/*
package com.example.vins;

public class WQUPC {
    public  int[][] id;
    public  byte[] sz;

    public WQUPC(int n){
        id = new int[n][n];
        sz = new byte[n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                id[i][j] = i;
                sz[j] = 1;
            }

        }
    }

    public  int root(int i){
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }

        return i;
    }

    public  boolean connected (int p, int q){
        return root(p) == root(q);
    }

    public  void union (int p , int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) {
            return;
        }

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }

    }
}
*/
