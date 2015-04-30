import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

public class Tuo
 { 
 
 String str1="0";  //运算数1  初值一定为0  为了程序的安全
 String str2="0";  //运算数2
 String fh="+"; //运算符
 String jg="";//结果
 
 //状态开关  重要
 int k1=1;//开关1 用于选择输入方向  将要写入str2或 str2
 int k2=1;//开关2 符号键 次数  k2>1说明进行的是2+3-9+8 这样的多符号运算
 int k3=1;//开关3 str1  是否可以被清0  ==1时可以  !=1时不能被清0
 int k4=1;//开关4 str2  同上
 int k5=1;//开关5  控制小数点可否被录入  ==1时可以  !=1 输入的小数点被丢掉
 JButton jicunqi; //寄存器  记录 是否连续按下符号键
 Vector vt=new Vector(20,10);
 
 JFrame frame=new JFrame("sunshine---计算器");
 JTextField jg_TextField=new JTextField(jg,20);//20列
 JButton clear_Button=new JButton("清除");
 JButton button0=new JButton("0");
 JButton button1=new JButton("1");
 JButton button2=new JButton("2");
 JButton button3=new JButton("3");
 JButton button4=new JButton("4");
 JButton button5=new JButton("5");
 JButton button6=new JButton("6");
 JButton button7=new JButton("7");
 JButton button8=new JButton("8");
 JButton button9=new JButton("9");
 JButton button_Dian=new JButton(".");
 JButton button_jia=new JButton("+");
 JButton button_jian=new JButton("-");
 JButton button_cheng=new JButton("*");
 JButton button_chu=new JButton("/");
 JButton button_dy=new JButton("=");
 ////////////////////////////////////////////////////////////////////////
 public static void main(String[] args)
 {
  Tuo tuo=new Tuo();
 }
 
 
 
 /////////////////////////////////////////////////////////////////////////
 Tuo()
 {
  button0.setMnemonic(KeyEvent.VK_0);//等效键
  //其它 等效键  略,
  
  jg_TextField.setHorizontalAlignment(JTextField.RIGHT );//文本框 右对齐
  
  JPanel pan=new JPanel();
  pan.setLayout(new GridLayout(4,4,5,5));//四行四列 边距为5像素
  pan.add(button7);
  pan.add(button8);
  pan.add(button9);
  pan.add(button_chu);
  pan.add(button4);
  pan.add(button5);
  pan.add(button6);
  pan.add(button_cheng);
  pan.add(button1);
  pan.add(button2);
  pan.add(button3);
  pan.add(button_jian);
  pan.add(button0);
  pan.add(button_Dian);
  pan.add(button_dy);
  pan.add(button_jia);
  pan.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));//pan对象的边距
  
  JPanel pan2=new JPanel();
  pan2.add(jg_TextField);
  
  
  JPanel pan3=new JPanel(); //为什么要 多此一句呢？ 因为我不会设置 按钮的大小
  pan3.setLayout(new FlowLayout());
  pan3.add(clear_Button);
  //clear_Button.setSize(10,10);//设置清零按钮的大小  吗的 不好使 ！！
  
  frame.setLocation(300, 200); //主窗口 出现在位置
  frame.setResizable(false);   //不能调大小 
  frame.getContentPane().setLayout(new BorderLayout());
  frame.getContentPane().add(pan2,BorderLayout.NORTH);
  frame.getContentPane().add(pan,BorderLayout.CENTER);
  frame.getContentPane().add(pan3,BorderLayout.SOUTH);
  
  
  frame.pack();
  frame.setVisible(true);
  
  
  //以上是 控件 和  布局
  //下面是事件处理 程 序
  
  //--------------- 数 字 键 ----------------
  class JianTing implements ActionListener
  {
   public void actionPerformed(ActionEvent e)
   {
    String ss=((JButton)e.getSource()).getText();
    jicunqi=(JButton)e.getSource(); 
    vt.add(jicunqi);    
    if (k1==1)
    {
     if(k3==1)
     {
      str1="";
      k5=1;//还原开关k5状态      
     }
     str1=str1+ss;
     //k2=1;
     k3=k3+1;
     //System.out.println(str1);
     jg_TextField.setText(str1);//显示
     
    }
    else if(k1==2)
    {
     if (k4==1)
     {
      str2="";
      k5=1; //还原开关k5状态      
     }
     str2=str2+ss;
     //k2=2;
     k4=k4+1;
      ///////////////测试////////////////
     jg_TextField.setText(str2);
    }
    
   
    
    
   }
  }
  
  //－－－－－－－－符 号－－－－－－－－－－－
  class JianTing_fh implements ActionListener
  {
   public void actionPerformed(ActionEvent e)
   {
    String ss2=((JButton)e.getSource()).getText();
    jicunqi=(JButton)e.getSource();
    vt.add(jicunqi);
    
    if(k2==1)
    {
     k1=2;//开关 k1  为1时,向数1写  为2时，向数2写 
     k5=1;
     fh=ss2;
     k2=k2+1;//按符号键的次数
    }
    else
    {
     int a=vt.size();
     JButton c=(JButton)vt.get(a-2);

     if(!(c.getText().equals("+"))&&!(c.getText().equals("-"))&&!(c.getText().equals("*"))&&!(c.getText().equals("/")))
     //if(!(vt.get(a-2).getText().equals("-"))||!(vt.get(a-2).getText().equals("+"))||!(vt.get(a-2).getText().equals("*"))||!(vt.get(a-2).getText().equals("/"))) 
     { yuns();
      str1=jg;
      k1=2;//开关 k1  为1时,向数1写  为2时，向数2写 
      k5=1;
      k4=1;
      fh=ss2;
     } k2=k2+1;
     
    }
    
   }
  }
  
  //－－－－－－－－清除－－－－－－－
  class JianTing_clear implements ActionListener
  {
   public void actionPerformed(ActionEvent e)
   {
    jicunqi=(JButton)e.getSource();
    vt.add(jicunqi);
    k5=1;
    k2=1;
    k1=1;
    k3=1;
    k4=1;
    str1="0";
    str2="0";
    fh="";
    jg="";
    jg_TextField.setText(jg);
    vt.clear();
   }
  }
  
  //----------------等  于 ---------------------
  class JianTing_dy implements ActionListener
  {
   public void actionPerformed(ActionEvent e)
   {
    
     jicunqi=(JButton)e.getSource();
     vt.add(jicunqi);
     yuns();
     k1=1; //还原开关k1状态
     //str1=jg;
     k2=1;
     k3=1;//还原开关k3状态
     k4=1; //还原开关k4状态
    
     str1=jg; //为7+5=12 +5=17 这种计算做准备
   }
  }
  //----------------小数点 ---------------------
  class JianTing_xiaos implements ActionListener
  {
   public void actionPerformed(ActionEvent e)
   {
    jicunqi=(JButton)e.getSource();
    vt.add(jicunqi);
    if(k5==1)
    {
     String ss2=((JButton)e.getSource()).getText();
     if (k1==1)
     {
      if(k3==1)
      {
       str1="";
       k5=1; //还原开关k5状态      
      }
      str1=str1+ss2;
      //k2=1;
      k3=k3+1;
      //System.out.println(str1);
      jg_TextField.setText(str1);//显示
      
      
     }
     else if(k1==2)
     {
      if (k4==1)
      {
       str2="";
       k5=1; //还原开关k5状态      
      }
      str2=str2+ss2;
      //k2=2;
      k4=k4+1;
       ///////////////测试////////////////
      jg_TextField.setText(str2);
     }
    }
    
    k5=k5+1; // 
   }
  }
  
  
  //注册 监听器
  JianTing_dy jt_dy=new JianTing_dy();
  JianTing jt= new JianTing();//临听数字键
  JianTing_fh jt_fh= new JianTing_fh();//临 听符 号键
  JianTing_clear jt_c=new JianTing_clear(); //清除键
  JianTing_xiaos jt_xs=new JianTing_xiaos();// 小数点 键
  
  
  button7.addActionListener(jt);
  button8.addActionListener(jt);
  button9.addActionListener(jt);
  button_chu.addActionListener(jt_fh);
  button4.addActionListener(jt);
  button5.addActionListener(jt);
  button6.addActionListener(jt);
  button_cheng.addActionListener(jt_fh);
  button1.addActionListener(jt);
  button2.addActionListener(jt);
  button3.addActionListener(jt);
  button_jian.addActionListener(jt_fh);
  button0.addActionListener(jt);
  button_Dian.addActionListener(jt_xs);
  button_dy.addActionListener(jt_dy);
  button_jia.addActionListener(jt_fh);
  clear_Button.addActionListener(jt_c);
  
  //关闭事件处理程序
  frame.addWindowListener(new WindowAdapter()
  {
   public void windowClosing(WindowEvent e)
   {
    System.exit(0);
   }
  });
  
  
 }
 
 //---------------计 算------------------ 
 public void yuns()
 {
  double a2;//运算数1
  double b2;//运算数2
  String c=fh;// 运算符
  double jg2=0 ;//结果
  
  if (c.equals(""))
  {
   //System.out.println("请输入运算符");
   jg_TextField.setText("请输入运算符");
  
  }
  else 
  {
       
   System.out.println("str1:"+str1);//调试时 使 用
   System.out.println("str2:"+str2);//调试时 使 用
   System.out.println("运算符:"+fh);//调试时 使 用
   if (str1.equals(".")) //字符串 "." 转换成double型数据时 会出错  所以手工转 
    str1="0.0";
   if (str2.equals("."))
    str2="0.0";
   a2=Double.valueOf(str1).doubleValue();
   b2=Double.valueOf(str2).doubleValue();
   
   System.out.println("double型的a2："+a2);  //调试时 使 用
   System.out.println("double型的b2："+b2);  //调试时 使 用
   
   
   if (c.equals("+"))
   {
    jg2=a2+b2;
   }
   if (c.equals("-"))
   {
    jg2=a2-b2;
   }
   if (c.equals("*"))
   {
    jg2=a2*b2;
   }
   if (c.equals("/"))
   {
     if(b2==0)
     {
      jg2=0;//0000000000000   by 0 cu!
     }
     else
     {
      jg2=a2/b2;
     }
    
    
   }
   
   System.out.println("double型a2"+fh+"b2结果："+jg2);
   
   System.out.println();
   jg=((new Double(jg2)).toString());

   jg_TextField.setText(jg);
  }
 } 
}
