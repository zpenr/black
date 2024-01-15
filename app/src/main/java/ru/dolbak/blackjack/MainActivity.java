package ru.dolbak.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button battonTakeCard, buttonStopGame;
    TextView playersTextView, dealersTextView, gameResultTextView;

    boolean theGameIsActive = true;

    Card[] dealersCards =  {new Card(Rate.COVER, Suit.Spades), new Card(Rate.COVER, Suit.Spades),
            new Card(Rate.COVER, Suit.Spades), new Card(Rate.COVER, Suit.Spades),
            new Card(Rate.COVER, Suit.Spades)};

    void putCard(int slot, Card card){
        int[] slots = {R.id.dealer_card_4, R.id.dealer_card_5, R.id.dealer_card_3,
                R.id.dealer_card_2, R.id.dealer_card_1, R.id.player_card_5, R.id.player_card_4,
                R.id.player_card_2, R.id.player_card_3, R.id.player_card_1};
        ImageView imageView = findViewById(slots[slot]);
        int cardDrawableID = CardMisc.CardToImageID(card);
        imageView.setImageDrawable(getResources().getDrawable(cardDrawableID));
    }

    void stopGame (int playersPoints, int dealerPoints){
        for(int i = 0;  i < dealersCards.length; i++){
        putCard(i, dealersCards[i]);
        }
        dealersTextView.setText("Очки дилера : " + dealerPoints);
        if( playersPoints > 21 && dealerPoints >21){
            gameResultTextView.setText("ничья");
            gameResultTextView.setTextColor(Color.DKGRAY);
            //ничья
        } else if (playersPoints>21) {
            gameResultTextView.setText("поражение");
            gameResultTextView.setTextColor(Color.RED);
            //поражение
        } else if (dealerPoints>21) {
            gameResultTextView.setText("победа");
            gameResultTextView.setTextColor(Color.GREEN);
        } else if (dealerPoints > playersPoints) {
            gameResultTextView.setText("поражение");
            gameResultTextView.setTextColor(Color.RED);
            //поражение
        } else if (dealerPoints == playersPoints) {
            gameResultTextView.setText("ничья");
            gameResultTextView.setTextColor(Color.DKGRAY);
            //ничия
        }
        else {
            gameResultTextView.setText("победа");
            gameResultTextView.setTextColor(Color.GREEN);
            //победа
        }
        theGameIsActive = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        battonTakeCard  = findViewById(R.id.button_take_card);
        buttonStopGame = findViewById(R.id.button_stop_game);
        playersTextView = findViewById(R.id.players_text_view);
        dealersTextView = findViewById(R.id.dilers_text_view);
        gameResultTextView = findViewById(R.id.gameResult);

       Deck deck = new Deck();




       int dealerCards = 0, dealerPoints = 0;
        final int[] playerCards = {0};
        final int[] playerPoints = { 0 };


        for(int i =0 ;i <2; i++){
           Card card = deck.take();
           putCard(playerCards[0] + 5, card);
           playerCards[0]++;
           playerPoints[0] +=card.points;
           playersTextView.setText("Очки игрока : "+ playerPoints[0]);
       }

       while (dealerCards < 5 && dealerPoints <15){
        Card card = deck.take();
           if (dealerCards == 0){
               putCard(dealerCards, card);

           }
        dealersCards[dealerCards]=card;
        dealerCards++;
        dealerPoints+=card.points;
        dealersTextView.setText("Очки дилера : ???");
       }
        int finalDealerPoints = dealerPoints;
    battonTakeCard.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!theGameIsActive){
             return ;
            }
            Card card = deck.take();
            putCard(playerCards[0] + 5, card);
            playerCards[0]++;
            playerPoints[0] +=card.points;
            playersTextView.setText("Очки игрока : "+ playerPoints[0]);

            if(playerCards[0] >=5 || playerPoints[0] >=21){
                stopGame(playerPoints[0], finalDealerPoints);
            }
        }
    });

        buttonStopGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!theGameIsActive){
                    return ;
                }
                stopGame(playerPoints[0], finalDealerPoints);
            }
        });
    }
}