package com.atguigu.sparsearray;

public class SparseArray {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //创建一个原始的二维数组11*11
        //0表示没有棋子，1表示黑子，2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        System.out.println("原始的二维数组~~");
        //输出原始的二维数组
        for(int[] row : chessArr1){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将二维数组转换为 稀疏数组
        //1.先遍历二维数组 得到非0数据个数
        int sum = 0;
        for(int i=0 ; i<chessArr1.length ;i++){
            for(int j=0 ; j<chessArr1.length ;j++){
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println("sum="+sum);
        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        //遍历二维数组，将非0的值存放到sparseArr中
        int floor=1;
        for(int i=0 ; i<chessArr1.length ;i++){
            for(int j=0 ; j<chessArr1.length ;j++){
                if(chessArr1[i][j] != 0){
                    sparseArr[floor][0] =i;
                    sparseArr[floor][1] =j;
                    sparseArr[floor][2] =chessArr1[i][j];
                    floor++;
                }
            }
        }
        //输出稀疏数组
        System.out.println("得到的稀疏数组为：");
//		for(int[] row : sparseArr){
//			for(int data : row){
//				System.out.printf("%d\t",data);
//			}
//			System.out.println();
//		}
        for (int i=0;i<sparseArr.length;i++){
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        //将稀疏数组恢复成原来的数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for(int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        System.out.println("恢复后的数组为：");
        for(int[] row : chessArr2){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();

        }


    }
}

