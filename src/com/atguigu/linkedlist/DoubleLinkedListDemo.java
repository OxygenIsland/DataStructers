package com.atguigu.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试:");
        HeroNode2 heroNode1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 heroNode4 = new HeroNode2(4,"林冲","豹子头");

        DoubleLinkedList item = new DoubleLinkedList();
        System.out.println("=======添加函数测试=======");
        item.add2(heroNode4);
        item.add2(heroNode3);
        item.add2(heroNode2);
        item.add2(heroNode1);
        item.show();
        System.out.println("=======删除函数测试=======");
        item.delete(3);
        item.show();
        //修改
        System.out.println("=======修改函数测试=======");
        HeroNode2 heroNode5 = new HeroNode2(4,"公孙胜","入云龙");
        item.update(heroNode5);
        item.show();

    }
}

//创建一个双向链表的类
class DoubleLinkedList{
    //先初始化一个头节点，头节点不要动,不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");//被private修饰的成员只能在本类中被访问

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //打印链表
    public void show() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表已空");
            return;
        }
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移，避免构成死循环
            temp = temp.next;
        }

    }

    //添加链表元素
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
        while (true) {
            //遍历链表找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后节点，则将temp后移
            temp = temp.next;
        } //当退出while循环时，temp指向链表的最后
        //形成双向链表
        temp.next = heroNode;
        heroNode.pre=temp;
    }

    public void add2(HeroNode2 heroNode) {
        //第二种方式在添加英雄时，根据排名将英雄插入到指定位置（如果有这个排名，则添加失败，并给出提示）
        //因为单链表，因此我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode2 temp = head;
        boolean flag = false;//添加的英雄编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后
                break;//
            }
            if (temp.next.no > heroNode.no) {//位置找到了，就在temp的后边插入
                break;
            } else if (temp.no == heroNode.no) {//说明希望添加的heroNode的编号依然存在
                flag = true;
                break;
            }
            temp = temp.next;//后移遍历链表

        }
        //判断flag的值
        if (flag) {//不能添加，说明编号存在
            System.out.printf("编号 %d 已经存在，不能添加到指定位置\n", heroNode.no);
            return;
        } else {
            if (temp.next!=null) {
                temp.next.pre=heroNode;
            }
            heroNode.next=temp.next;
            temp.next = heroNode;
            heroNode.pre = temp;
        }

    }

    //修改节点信息，根据no编号来修改，即no编号不能改。
    public void update(HeroNode2 heroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.printf("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号，定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;//链表已经遍历完毕
            }
            if (temp.no == heroNode.no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            //没有找到
            System.out.printf("没有找到编号%d的节点，不能修改\n", heroNode.no);
        }
    }

    //删除链表节点
    public void delete(int no) {
        //判断是否为空
        if (head.next == null) {
            System.out.printf("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;//辅助变量（指针）
        boolean flag = false;
        while (true) {
            if (temp.no == no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
            if (temp == null) {
                break;
            }
        }
        if (flag) {
            temp.pre.next = temp.next;

            if (temp.next!=null){
                temp.next.pre = temp.pre;//如果是删除最后一个节点，就不需要执行下面这句话，否则会出现空指针异常（调用null方法引起的异常）
            }

        } else {
            System.out.printf("要删除的%d节点不存在\n", no);
        }

    }
}
//定义HeroNode2，每个HeroNode2 对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点。该语句表示next这个变量类型为HeroNode这个类的实例
    //  可以通过类名+方法名调用的方法叫做静态方法，属于类级别方法。类方法可在不实例化对象的前提下【直接调用】。
    //  一般类的静态方法和静态变量都是通过static关键字修饰的。而且静态方法体里不能应用任何实例变量和实例方法。
    //  调用这个方法和new一个对象效果是一样的。不过只是把实例的创建封装在方法里,通过类名加方法来调用而已，
    //  让实例化代码简洁点外，无任何差别。
    public HeroNode2 pre;//指向上一个节点

    //构造器
    public HeroNode2(int hNo,String hName,String hNickname){
        this.no = hNo;
        this.nickname = hNickname;
        this.name = hName;
    }
    //为了显示方便，重新写toString方法

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
