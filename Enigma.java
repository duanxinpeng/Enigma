import java.util.Scanner;

public class Enigma {
	int[]reflector= {18, 16, 12, 15, 19, 13, 23, 20, 9, 8, 21, 14, 2, 5, 11, 3, 1, 22, 0, 4, 7, 10, 17, 6, 25, 24};
	int[][]raw_cog={{6, 10, 23, 9, 19, 2, 21, 1, 7, 24, 0, 17, 15, 3, 8, 4, 14, 12, 16, 25, 11, 18, 22, 20, 13, 5}  
			       ,{0, 7, 1, 10, 19, 5, 9, 18, 4, 23, 2, 20, 15, 24, 22, 8, 12, 25, 6, 13, 3, 11, 21, 17, 16, 14}  
				   ,{23, 2, 4, 13, 14, 21, 12, 0, 9, 16, 5, 17, 20, 22, 10, 24, 7, 3, 25, 8, 15, 6, 19, 18, 11, 1}};
	int[][]cog=new int[3][26];//the cog in fact;
	int lenCipher;
	public Enigma(int a,int b,int c) {//a b c指的是转子的不同状态
		lenCipher=0;
		cog[0]=shift(raw_cog[0],a);
		cog[1]=shift(raw_cog[1],b);
		cog[2]=shift(raw_cog[2],c);
	}
	//array shift;
	public int[] shift(int[]src,int num) {
		int[]dest=new int[src.length];
		System.arraycopy(src, num, dest, 0, src.length-num);
		System.arraycopy(src, 0, dest, src.length-num, num);
		return dest;
	}
	//encrypt a char
	public char enChar(char theChar) {
		int num=theChar-'a';
		for(int i=0;i<3;i++) {
			num=cog[i][num];
		}
		num=reflector[num];
		for(int i=2;i>=0;i--) {
			num=index(cog[i],num);
		}
		return (char) ('a'+num);
	}
	public String encrypt(String plaintext) {
		plaintext=plaintext.toLowerCase();
		char[]plain=plaintext.toCharArray();
		char[]cipher=new char[plain.length];
		for(int i=0;i<plain.length;i++) {
			if(plain[i]>='a'&&plain[i]<='z') {
				cipher[i]=enChar(plain[i]);
				lenCipher++;
				//rotate the cog that should be
				for(int j=0;j<3;j++) {
					if(lenCipher>=Math.pow(26,j)&&lenCipher%(Math.pow(26,j))==0) {
						cog[j]=shift(cog[j],1);
					}
				}
			}
		}
		return String.valueOf(cipher);
	}
	//index method
	public int index(int[]arr,int num) {
		int i=0;
		for(i=0;i<arr.length;i++) {
			if(arr[i]==num)
				break;
		}
		return i;
	}
	public static void main(String[] args) {
		Enigma enigma=new Enigma(0,0,0);
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入你想要加密的字符串（只能是由26个字母组成的字符串！）：");
		String plaintext=scanner.nextLine();
		System.out.println("密文：");
		String cipher=enigma.encrypt(plaintext);
		System.out.println(cipher);
		
	}
}
