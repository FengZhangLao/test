using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ConsoleApplication30
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("请输入英文句子：");
            string str = Console.ReadLine();
            string[] allChar = str.Split(' ');
            string result = "";
            for (int i = allChar.Length; i > 0; i--)
            {
                result += allChar[i - 1] + " ";
            }
            Console.WriteLine("翻转后的结果为:{0}",result);
            Console.ReadLine();
        }
    }
}
