package com.atguigu.stack;

public class Calculater {
    public static void main(String[] args) {//处理多位数时，会出现错误！
        String expression = "7*2*2-5+1-5+3-4";
        //创建两个栈，一个是数栈，一个是符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(20);
        //定义相关的变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepnumber = "";//用于拼接多位数
        //开始while循环的扫描expression
        while(true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch
            if(operStack.IsOperator(ch)){//如果是运算符
                //判断当前的符号栈是否为空
                if(!operStack.isEmpty()){
                    //如果不为空，则比较符号优先级
                    if(operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.cal(num1,num2,oper);
                        numStack.push(res);//运算结果入栈
                        operStack.push(ch);//当前的操作符入栈
                    }else {
                        operStack.push(ch);
                    }
                }else {
                    operStack.push(ch);
                }
            }else {//如果是数，直接入栈
                //当处理多位数时，需要向expression表达式index后再看一位，如果是数就进行扫描，如果是符号才入栈
                //需要定义一个变量 字符串用于拼接
                keepnumber += ch;
                //如果ch已经是最后一位则不需要再向后检测
                if(index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepnumber));
                }else {
                    //判断下一个字符是不是数字，如果是数字就继续扫描，再拼接，如果是运算符则入栈
                    if(operStack.IsOperator(expression.substring(index+1,index + 2).charAt(0))){
                        //下一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepnumber));
                        //!!!!!!!!!!keepnumber要清空
                        keepnumber = "";
                    }
                }
            }
            //让index+1，并判断是否扫描到expression的最后
            index++;
            if(index>=expression.length()){
                break;
            }
        }
        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1,num2,oper);
            numStack.push(res);//运算结果入栈
        }
        int res2 = numStack.pop();
        System.out.printf("表达式%s=%d",expression,res2);
    }
}

//先创建一个栈
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[ ] stack;//数组，数组模拟栈，数据就放在该数组中
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];//初始化数组才能往里面放数据
    }
    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top==-1;
    }
    public int peek(){
        return stack[top];
    }
    //入栈-push
    public void push(int value){
        //先判断栈是否满
        if (isFull()){
            return;
        }
        top++;
        stack[top]=value;

    }
    //出栈
    public int pop(){
        if (isEmpty()) {
            throw new RuntimeException("栈空");//运行异常，不捕获也可以抛出，return不需要有，异常就表示终止程序
        }
        int val = stack[top];
        top--;
        return val;
    }
    //遍历栈
    public void show(){
        if (isEmpty()){
            System.out.printf("栈空，没有数据");
            return;
        }
        for (int i = top; i >=0; i--) {
            System.out.printf("\nstack[%d]=%d",i,stack[i]);
        }
        return;
    }
    //返回运算符的优先级，优先级是由程序员来确定的，使用数字表示
    //数字越大，则优先级就越高
    public int priority(int oper){
        if(oper=='*'||oper=='/'){
            return  1;
        }else if (oper == '+' || oper == '-') {
            return  0;
        }else {
            return  -1;
        }
    }

    //判断是不是一个运算符
    public boolean IsOperator(char val){
        return val =='+'||val == '-' || val =='*'||val =='/';
    }
    //计算方法
    public int cal(int num1,int num2,int oper){
        int res = 0;//用于存放计算结果
        switch (oper){
            case '+':
                res = num1+num2;
                break;
            case '-':
                res = num2-num1;//注意顺序
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                res = num2/num1;
                break;
            default:
                break;
        }
        return res;

    }
}
