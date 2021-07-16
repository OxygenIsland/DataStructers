package com.atguigu.recursion;

public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙，初始化墙
        for (int i = 0; i < 7;i++){
            map [0][i]=1;
            map[7][i] = 1;
        }
        for (int i = 0; i <8;i++){
            map[i][0]=1;
            map[i][6] = 1;
        }
        map[3][1]=1;
        map[3][2] = 1;
        //输出地图
        setWay(map,1,1);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"  ");
            }
            System.out.printf("\n");
        }

    }
    //使用递归回溯来给小球找路
    //约定：当map[i][j]为0表示该点没有走过，1表示墙，2表示通路可以走，3表示该点已经走过走不通
    //走迷宫的策略   下=>右=>上=>左
    /**
     *
     * @param map 地图
     * @param i 从那个位置开始找（1,1）
     * @param j
     * @return 找到通路（6,5）则返回true否则false
     */
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5] == 2){//通路已经找到
            return true;
        }else {
            if(map[i][j]==0){//当前的点还没有走过
                map[i][j]=2;
                if(setWay(map,i+1,j)){//向下走
                    return true;
                }else if(setWay(map, i, j+1)){
                    return true;
                }else if (setWay(map, i-1, j)){
                    return true;
                }else if (setWay(map, i, j-1)){
                    return true;
                }else {//走不通
                    map[i][j] = 3;
                    return false;
                }
            }else {//如果map[i][j]！=0，可能是1,2,3
                return false;
            }
        }
    }
}
