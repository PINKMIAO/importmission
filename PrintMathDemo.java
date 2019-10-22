package Train.Method.TeachDemo.SeventhAppTrain;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.security.interfaces.RSAKey;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * 随机生成10到百内加减法（完成）
 * 要求1）自动生成10道100以内的四则运算算式（+ - *  /），要求运算结果也在100以内（完成）
（2）剔除重复算式。  2 + 3 =    和  2 + 3 =     是重复算式      2 + 3 =   和   3 + 2 =  不属于重复算式（完成）
（3）题目数量可定制(完成)
（4）相关参数可控制
          是否包含乘法和除法（完成）
          是否有（）运算符
          操作数数值范围可控（如操作数 在100以内   还是1000以内）
          操作数是否含负数　　　　
  （5）生成的运算题存储到外部文件result.txt中（完成）
 * @author 喵
 * @date 2019年9月6日上午10:56:56
 */
public class PrintMathDemo {
	public static void main(String[] args) throws FileNotFoundException, ExcessNumException , InputNotSumException{	
		
		/**利用对话框生成数量并判断格式是否正确*/
		int num = Integer.MIN_VALUE;
		while(true) {
			try {
				String strnum = JOptionPane.showInputDialog(null, "需要几道数学题", "题数", JOptionPane.QUESTION_MESSAGE);
				num = Integer.parseInt(strnum);
				if (num > 100) {
					JOptionPane.showMessageDialog(null, "输入数字大于100，请重新输入100以内的正整数！");
					continue;
				}
				break;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "输入格式错误，请重新输入100以内的正整数！");
			}
		}
		
		String[] result = new String[num];
		String conCat = null;
		
		/**利用对话框选择类型*/
		String strtype = (String)JOptionPane.showInputDialog(null, "选择类型", "题目类型", JOptionPane.QUESTION_MESSAGE, 
				null, 
				new String[] {"1.百内加减", "2.百内加减乘除"},
				"1.百内加减");
		char chnum = strtype.charAt(0);
		int intnum = ((int)chnum - 48);//出错 - 已修改
		
		 
		/** 将输出结果导入txt文件中*/
		PrintStream txt = new PrintStream("e:/result.txt");
		System.setOut(txt);
		
		System.out.println("本次有" + num + "道题");
		/** 选择输出类型*/
		switch (chnum) {
		case '1':
			result = creatStringMath(num, result, conCat, intnum);//遇到错误,解决不了，数据越界
			break;

		case '2':
			result = creatStringMath(num, result, conCat, intnum);
			break;
		}
		
		for (int j = 0; j < result.length; j++) {
			System.out.println(result[j] + " " + "=");
		}
		JOptionPane.showMessageDialog(null, "已导入result.txt文件中");
		
//		PrintStream out = System.out;
//		System.out.println("sssss");
	}
	
	/**
	 * 判断是否在字符串里重复过
	 * @param result
	 * @param conCat
	 * @return
	 */
	public static boolean isCompareString (String[] result, String conCat) {
		boolean stringResult = true;
		for (int i = 0; i < result.length; i++) {
			if(conCat.equals(result[i])) { //出现变量对比关系错误，已纠正
				stringResult = false;
				break;
			}
		}	
		return stringResult;
	}
	/**
	 * 选择字符数组
	 * @param intnum
	 * @return
	 * 完成这个的之前，我本来直接在creatStringMath 方法里使用if选择，并在if里直接声明定义了一个字符数字并直接赋值，遇到了大错误。
	 * 我忘记了if的存在时有效时间，所以长时间的卡住了。以下效率可能并不高，待完善。
	 */
	public static char[] SelcetSigh(int intnum) {
		int c = 0;
		if (intnum == 1) {
			c = 2;
		}else if(intnum == 2) { 
			c = 4;
		}else {
			System.out.println("err.intnum不是1或者2");
		}
		
		char[] chSign = new char[c];
		switch (intnum) {
		case 1:
			char[] preSign = {'+', '-'};
			for (int i = 0; i < chSign.length; i++) {
				chSign[i] = preSign[i];
			}
			break;
		case 2:
			char[] preSign1 = {'+', '-', '*', '/'};
			for (int i = 0; i < chSign.length; i++) {
				chSign[i] = preSign1[i];
			}
			break;
		}
		return chSign;
	}
	/**
	 * 选择随机数
	 * @param intnum
	 * @return
	 */
	public static int SelcetRandomNum(int intnum) {
		int random2 = Integer.MIN_VALUE;
		switch (intnum) {
		case 1:
			int random = (int)(Math.random()>0.5?1:0);
			random2 = random;
			break;
		case 2:
			int random1 = (int)(Math.random()* 4);
			random2 = random1;
			break;
		}
		return random2;
	}
	/**
	 * 生成百内加减的数学题
	 * @param num		字符串数组的空间大小
	 * @param str		字符串数组
	 * @param conCat	临时字符串变量，用来保存连接起来的字符串
	 * @return
	 */
	public static String[] creatStringMath(int num,String[] str, String conCat, int intnum) {
		int i = 0, c = intnum;
		char[] chSign = null;
		char[] preSign = SelcetSigh(c);
		chSign = preSign;
		/** 生成题目循环*/
		while(i < num) {
			int random = SelcetRandomNum(intnum);
			int random1 = (int)(Math.random()*100 + 1);
			int random2 = (int)(Math.random()*100 + 1);
			Integer iRandom1 = random1;
			Integer iRandom2 = random2;
				
			switch(chSign[random]){
				case '+':
					if (random1 + random2 > 100) {
						continue;
						}
					conCat = (iRandom1.toString() + " " + "+" + " " + iRandom2.toString());
					break;
					
				case '-':
					if (random1 - random2 < 0) {
						conCat = (iRandom2.toString() + " " + "-" + " " + iRandom1.toString());
						break;
					}
					conCat = (iRandom1.toString() + " " + "-" + " " + iRandom2.toString());
					break;
					
				case '*':
					if((random1 / 4) * (random2 / 4) > 100 && (random1 / 4) * (random2 / 4) != 0) {
						continue;
					}
					iRandom1 = random1 /4;
					iRandom2 = random2 /4;
					conCat = (iRandom1.toString() + " " + "*" + " " + iRandom2.toString());
					break;
					
				case '/':
					if (random1 % random2 == 0) {
						conCat = (iRandom1.toString() + " " + "/" + " " + iRandom2.toString());
					}else if(random2 % random1 == 0){
						conCat = (iRandom2.toString() + " " + "/" + " " + iRandom1.toString());
					}else {
						continue;
					}
					break;
			}
			boolean cc = isCompareString(str, conCat);
			if(cc = true)
				str[i] = conCat;		
			i++;
			}
		return str;
	}
	
	
}

