import java.util.ArrayList;
import java.util.Random;


public class Deck {
	private ArrayList<Card> cards;
	private ArrayList<Card> usedCard;
	public int nUsed;
	public Deck(int nDeck){
		cards=new ArrayList<Card>();
		for(int deck=1;deck<=nDeck;deck++){  		//deck->至少要一副牌，nDeck->至多要幾副牌
			for(Card.Suit s : Card.Suit.values()){  //一副牌有4種花色
				for(int rank=1;rank<=13;rank++){	//1種花色13張牌
					Card card=new Card(s,rank);		//實體化卡牌
					cards.add(card);				//新增卡牌
				}
			}
		}
		shuffle(); 	//洗牌
	}	
	public Card getOneCard(){
		Card c1=new Card(cards.get(nUsed).getSuit(),cards.get(nUsed).getRank());	//實體化並發牌
		usedCard.add(cards.get(nUsed));		//將發出的牌紀錄在usedCard
		nUsed++;	//紀錄發出的牌數量

		if(nUsed==52) {	//當牌發完了
			shuffle();	//洗牌
			nUsed=0;	//發出的牌數量歸0
		}
		return c1;
	}
	public void shuffle(){		
		Random rnd = new Random(); 	//使用Random 產生一個rnd
		nUsed=0;	//發出的牌數量為0
		usedCard=new ArrayList<Card>();
		/* 洗牌過程中隨機挑選某一張牌，
		 * 選到的牌會被依序放入usedCard，
		 * 並將cards裡被選中的牌移除，
		 * 經過"洗牌"(迴圈)重複n次後，
		 * usedCard中得到的卡牌花色、牌號就會被打亂
		   再將洗好的牌從usedCard放回cards陣列中*/
		for(int n=0;n<52;n++){      	
			int j = rnd.nextInt(52-n);	//產生一個隨機變數rnd，決定選到的數字在一疊撲克牌中的"排序"
			usedCard.add(cards.get(j));	//將選到的牌暫時放到usedCard陣列中
			cards.remove(cards.get(j));	//將選到的牌從cards陣列中移除
		}
		for(int x=0;x<52;x++){
			cards.add(usedCard.get(x));	//依序將usedCard陣列中的牌放回cards陣列中，完成洗牌動作
		}
	}
	public ArrayList<Card> getAllCards(){
		return cards;
	}
}
