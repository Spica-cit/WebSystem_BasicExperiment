package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerModel {
	
	int games;
	int chips;
	int count[];
	
	List<Integer> deckcards;
	List<Integer> handcards;
	
	String buttonLabel;
	String message;
	
	public PokerModel() {
		deckcards = new ArrayList<>();
		handcards = new ArrayList<>();
	}
	
	public void reset() {
		chips = 500;
		games = 0;
	}
	
	public void setHandcards(int a, int b, int c, int d, int e) {
		handcards.set(0, a);
		handcards.set(1, b);
		handcards.set(2, c);
		handcards.set(3, d);
		handcards.set(4, e);
	}
	
	public void nextgame() {
		deckcards.clear();
		for (int i = 1; i <= 52; i++) {
			deckcards.add(i);
		}
		Collections.shuffle(deckcards);
		
		handcards.clear();
		for (int i = 0; i < 5; i++) {
			int n = deckcards.remove(0);
			handcards.add(n);
		}
		
		System.out.printf("%d: ", deckcards.size());
		for (int n: deckcards) {
			System.out.printf("%d ",  n);
		}
		System.out.println();
		
		message = "交換するカードをチェックしてください";
		buttonLabel = "交換";
		games++;
	}
	
	public void change(List<String> index) {
		System.out.println("index="+index);
		for (int i = 0; i <  5;i++) {
			String s = "" + i;
			if(index.contains(s)) {
				int c = deckcards.remove(0);
				i = Integer.parseInt(s);
				handcards.set(i,  c);
			}
		}
		evaluate();
		buttonLabel = "次のゲーム";
	}
	
	public void evaluate() {
		countNumber();
		
		int red = countRed();
		int black = countBlack();
		int loyal = countLoyal();
		int sflash = countSflash();
		int four = countFour();
		int straight = countStraight();
		int flash = countFlash();
		int three = countThree();
		int pair = countPair();
		int seven = countSeven();
		int point = 0;
		
		if(loyal==1 && (red==5 || black==5) && flash==1) {
			message = "ロイヤルストレートフラッシュ";
			point = 500;
		}else if(sflash==1) {
			message = "ストレートフラッシュ";
			point = 450;
		}else if(four==1) {
			message = "フォーカード";
			point = 400;
		}else if(three==1 && pair==1) {
			message = "フルハウス";
			point = 350;
		}else if((red==5 || black==5) && flash==1) {
			message = "フラッシュ";
			point = 300;
		}else if(straight==1) {
			message = "ストレート";
			point = 250;
		}else if(three==1) {
			message = "スリーカード";
			point = 200;
		}else if(pair==2) {
			message = "ツーペア";
			point = 150;
		}else if(pair==1) {
			message = "ワンペア";
			point = 100;
		}else if(seven > 0) {
			message = "セブン";
			point = seven * 10;
		}else {
			message = "ハイカード";
			point = -200;
		}
		
		chips += point;
		message += ": " + point;
		
		if(chips <= 0) {
			message += "・・・ゲームオーバー";
		}
	}
	
	int countSeven() {
		int seven = 0;
		
		for(int i=0; i<5; i++) {
			if(handcards.get(i)%13 == 7) {
				seven += 1;
			}
		}
		return seven;
	}
	
	int countRed() {
		int red = 0;
		
		for(int i=0; i<5; i++) {
			if(handcards.get(i)<=39 && handcards.get(i)>=14) {
				red += 1;
			}
		}
		return red;
	}
	
	int countBlack() {
		int black = 0;
		
		for(int i=0; i<5; i++) {
			if(handcards.get(i)<=13 || handcards.get(i)>=40) {
				black += 1;
			}
		}
		return black;
	}
	
	int countLoyal() {
		int loyal = 0;
		
		if(count[0]==1 && count[9]==1 && count[10]==1 && count[11]==1 && count[12]==1) {
			loyal += 1;
		}
		
		return loyal;
	}
	
	int countSflash(){
		int sflash = 0;
		int check;
		
		if(handcards.get(0)%13 == 0) {
			check = handcards.get(0) / 13 - 1;
		}else {
			check = handcards.get(0) / 13;
		}
		
		for(int i=1; i<5; i++) {
			if(handcards.get(i)/13==check || handcards.get(i)/13-1==check) {
				if(i==4) {
					if(Collections.max(handcards)-Collections.min(handcards)==4) {
						sflash += 1;
					}
				}
			}else {
				break;
			}
		}
		
		return sflash;
	}
	
	int countStraight() {
		int straight = 0;
		int max = handcards.get(0);
		int min = handcards.get(0);
		int check;
		
		for(int i=1; i<5; i++) {
			check = handcards.get(i) % 13;
			if(check==0) {
				max = 13;
			}else if(check==1) {
				min = 1;
			}else if(max < check) {
				max = check;
			}else if(min > check) {
				min = check;
			}
		}
		
		if(max-min==4) {
			straight += 1;
		}
		
		for(int i: count) {
			if(i>=2) {
				straight = 0;
				break;
			}
		}
		
		return straight;
	}
	
	int countFlash() {
		int flash = 0;
		int check;
		
		if(handcards.get(0)%13 == 0) {
			check = handcards.get(0) / 13 - 1;
		}else {
			check = handcards.get(0) / 13;
		}
		
		for(int i=1; i<5; i++) {
			if(handcards.get(i)/13==check || handcards.get(i)/13==check+1) {
				if(i==4) {
					flash += 1;
				}
			}else {
				break;
			}
		}
		return flash;
	}
	
	int countFour() {
		int four = 0;
		
		for(int i: count) {
			if(i==4) {
				four += 1;
			}
		}
		
		return four;
	}
	
	int countThree(){
		int three = 0;
		
		for(int i: count) {
			if(i==3) {
				three += 1;
			}
		}
		return three;
	}
	
	int countPair() {
		int pair = 0;
		
		for(int i: count) {
			if(i==2) {
				pair += 1;
			}
		}
		
		return pair;
	}
	
	void countNumber() {
		count = new int[13];
		
		for(int i: handcards) {
			int n = (i - 1) % 13;
			count[n] += 1;
		}
		
		for(int i: count) {
			System.out.printf("%d ",  i);
		}
		System.out.println();
	}
	
	public int getGames() {
		return games;
	}
	
	public int getChips() {
		return chips;
	}
	
	public int getHandcardAt(int i) {
		return handcards.get(i);
	}
	
	public String getButtonLabel() {
		return buttonLabel;
	}
	
	public String getMessage() {
		return message;
	}

}
