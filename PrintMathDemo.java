package Train.Method.TeachDemo.SeventhAppTrain;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.security.interfaces.RSAKey;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * �������10�����ڼӼ�������ɣ�
 * Ҫ��1���Զ�����10��100���ڵ�����������ʽ��+ - *  /����Ҫ��������Ҳ��100���ڣ���ɣ�
��2���޳��ظ���ʽ��  2 + 3 =    ��  2 + 3 =     ���ظ���ʽ      2 + 3 =   ��   3 + 2 =  �������ظ���ʽ����ɣ�
��3����Ŀ�����ɶ���(���)
��4����ز����ɿ���
          �Ƿ�����˷��ͳ�������ɣ�
          �Ƿ��У��������
          ��������ֵ��Χ�ɿأ�������� ��100����   ����1000���ڣ�
          �������Ƿ񺬸�����������
  ��5�����ɵ�������洢���ⲿ�ļ�result.txt�У���ɣ�
 * @author ��
 * @date 2019��9��6������10:56:56
 */
public class PrintMathDemo {
	public static void main(String[] args) throws FileNotFoundException, ExcessNumException , InputNotSumException{	
		
		/**���öԻ��������������жϸ�ʽ�Ƿ���ȷ*/
		int num = Integer.MIN_VALUE;
		while(true) {
			try {
				String strnum = JOptionPane.showInputDialog(null, "��Ҫ������ѧ��", "����", JOptionPane.QUESTION_MESSAGE);
				num = Integer.parseInt(strnum);
				if (num > 100) {
					JOptionPane.showMessageDialog(null, "�������ִ���100������������100���ڵ���������");
					continue;
				}
				break;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "�����ʽ��������������100���ڵ���������");
			}
		}
		
		String[] result = new String[num];
		String conCat = null;
		
		/**���öԻ���ѡ������*/
		String strtype = (String)JOptionPane.showInputDialog(null, "ѡ������", "��Ŀ����", JOptionPane.QUESTION_MESSAGE, 
				null, 
				new String[] {"1.���ڼӼ�", "2.���ڼӼ��˳�"},
				"1.���ڼӼ�");
		char chnum = strtype.charAt(0);
		int intnum = ((int)chnum - 48);//���� - ���޸�
		
		 
		/** ������������txt�ļ���*/
		PrintStream txt = new PrintStream("e:/result.txt");
		System.setOut(txt);
		
		System.out.println("������" + num + "����");
		/** ѡ���������*/
		switch (chnum) {
		case '1':
			result = creatStringMath(num, result, conCat, intnum);//��������,������ˣ�����Խ��
			break;

		case '2':
			result = creatStringMath(num, result, conCat, intnum);
			break;
		}
		
		for (int j = 0; j < result.length; j++) {
			System.out.println(result[j] + " " + "=");
		}
		JOptionPane.showMessageDialog(null, "�ѵ���result.txt�ļ���");
		
//		PrintStream out = System.out;
//		System.out.println("sssss");
	}
	
	/**
	 * �ж��Ƿ����ַ������ظ���
	 * @param result
	 * @param conCat
	 * @return
	 */
	public static boolean isCompareString (String[] result, String conCat) {
		boolean stringResult = true;
		for (int i = 0; i < result.length; i++) {
			if(conCat.equals(result[i])) { //���ֱ����Աȹ�ϵ�����Ѿ���
				stringResult = false;
				break;
			}
		}	
		return stringResult;
	}
	/**
	 * ѡ���ַ�����
	 * @param intnum
	 * @return
	 * ��������֮ǰ���ұ���ֱ����creatStringMath ������ʹ��ifѡ�񣬲���if��ֱ������������һ���ַ����ֲ�ֱ�Ӹ�ֵ�������˴����
	 * ��������if�Ĵ���ʱ��Чʱ�䣬���Գ�ʱ��Ŀ�ס�ˡ�����Ч�ʿ��ܲ����ߣ������ơ�
	 */
	public static char[] SelcetSigh(int intnum) {
		int c = 0;
		if (intnum == 1) {
			c = 2;
		}else if(intnum == 2) { 
			c = 4;
		}else {
			System.out.println("err.intnum����1����2");
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
	 * ѡ�������
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
	 * ���ɰ��ڼӼ�����ѧ��
	 * @param num		�ַ�������Ŀռ��С
	 * @param str		�ַ�������
	 * @param conCat	��ʱ�ַ����������������������������ַ���
	 * @return
	 */
	public static String[] creatStringMath(int num,String[] str, String conCat, int intnum) {
		int i = 0, c = intnum;
		char[] chSign = null;
		char[] preSign = SelcetSigh(c);
		chSign = preSign;
		/** ������Ŀѭ��*/
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

