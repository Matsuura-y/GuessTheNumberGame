import java.util.*;

//ユーザーの入力値をチェックするクラス
class Check{
	static final int MIN = 0;
	static final int MAX = 100;
	//入力値が数字かどうか判定するメソッド。
	static boolean isNum(String nStr, String mStr){
		if(nStr.matches("[0-9]{0,}") && mStr.matches("[0-9]{0,}")) return true;
		
		System.out.println("入力が間違っているようです。0から100の整数を入力してください。>");
		System.out.println();
		return false;
		
	}
	//オーバーロード。
	static boolean isNum(String nStr){
		if(nStr.matches("[0-9]{0,}")) return true;
		
		System.out.print("入力が間違っているようです。0から100の整数を入力してください。>");
		return false;
	}
	//入力値が条件に合致するかどうか判定するメソッド。
	static boolean isValidNum(int n, int m){
		if(n >= MIN && m <= MAX && m >= (n+10)) return true;

		System.out.println("入力できる範囲は0から100の整数とします。最大値は、最小値よりも10以上大きい数字を入力してください。");
		System.out.println("最大値は、最小値よりも10以上大きい数字を入力してください。");
		return false;
	}
	//入力値が適当な場合は、ユーザーの入力値を配列に格納して返す。
	static int[] cheked(){
		Scanner sc = new Scanner(System.in);

		System.out.println("2つの数字（最小置nと最大値m）を選び入力してください。入力できる数字は、0から100の整数です。");
		System.out.println();
		System.out.println("最大値は、最小値よりも10以上大きい数字を入力してください。");
		System.out.println("例 適:最小値n = 1、最大値m = 11 不適:最小値n = 1、最大値m = 10");
		System.out.println();
		
		int n, m;
		while(true){
			System.out.print("最小値nを入力してください > ");
			String nStr = sc.next();

			System.out.print("最大値mを入力してください > ");
			String mStr = sc.next();

			//入力値が数字かどうか判定する。
			if(!isNum(nStr, mStr)) continue;

			n = Integer.parseInt(nStr);
			m = Integer.parseInt(mStr);

			//最小値と最大値の関係が条件に適しているかどうか判定する。
			if(!isValidNum(n, m)) continue;
			System.out.println("あなたが入力した最小値は " + n + ", 最大値は " + m + " です。");
			System.out.println();
			break;
			
		}
		//sc.close();
		return new int[]{n, m};
		
	}
	
}
public class GuessTheNumberGame{
	//表示時間を調整するためプログラムを一時停止するメソッド。
	static void sleep(int time){
		try {
            Thread.sleep(time);//timeミリ秒間停止
        } catch (InterruptedException e) {
            System.out.println("エラーが発生しました。ゲームを終了します。");
			return;
        }
	}

	//ヒントを表示する。
	static void printHints(int guessNum, int ANS){
		System.out.println("ヒント");
		if(guessNum > ANS) System.out.println("正解の数字は、入力された数 " + guessNum + " より小さいです。");
		else if(guessNum < ANS) System.out.println("正解の数字は、入力された数 " + guessNum + "より大きいです。");
		System.out.println();
	}
	
	public static void main(String[] args){

		System.out.println("【数当てゲーム】");
		System.out.println("ルール:あなたが入力した2つの数字の範囲において、コンピュータが数字をひとつ選びます。");
		System.out.println("コンピュータが選んだ数字を推定して当ててください。3回まで挑戦できます。");

		System.out.println();

		//適切な最小値及び最大値を配列に入れる。
		int[] input = Check.cheked();//input[0]:最小値、input[1]:最大値

		//ユーザーの最小値、最大値の範囲で乱数をつくる。
		final int ANS = new Random().nextInt(input[1]-input[0]+1) + (input[0]);

		Scanner sc = new Scanner(System.in);

		int guessNum = 0;//ユーザーが推測した数字を保存する変数。初期値を0とする。
        for(int i = 1; i <= 3; i ++){
			System.out.println(i + "回目の挑戦。");//挑戦回数を表示する。

            //3回目の入力のとき、「最後」を強調する。
            if(i == 3) System.out.println("これが最後のチャンス！");

			sleep(2000);//1行間停止

			//2回目以降はヒントを表示する。
			if(i > 1) printHints(guessNum, ANS);

            //入力された数字を数値に変換する。
			System.out.print("コンピュータが選んだ数字を当ててください > ");
			String guess = sc.next();
            while (!Check.isNum(guess)) {
				guess = sc.next();				
			}
			guessNum = Integer.parseInt(guess);
            
            //正解かどうか判定する。
			System.out.println("当たりかどうか判定中...");
			System.out.println();
			sleep(2000);//2秒間停止
            System.out.println((guessNum == ANS) ? "当たり！やったね！" : "残念ハズレ。。。");
            System.out.println();

            if(guessNum == ANS) break;//正解だったらループを抜ける。
            
        }
		sc.close();

        sleep(2000);//2秒後に終了を表示する。
        

        System.out.println("あなたの回答は " + guessNum + "、 正解は、" + ANS + " でした。");
		if(guessNum != ANS && ((ANS+1) == guessNum || (ANS -1) == guessNum)) System.out.println("おしいっ！");
        System.out.println("ゲームはおしまいです。また挑戦してね！");        
		
		return;
	}
}
