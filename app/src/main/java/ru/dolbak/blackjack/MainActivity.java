package ru.dolbak.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    void putCard(int slot, Card card){
        int[] slots = {R.id.dealer_card_4, R.id.dealer_card_5, R.id.dealer_card_3,
                R.id.dealer_card_2, R.id.dealer_card_1, R.id.player_card_5, R.id.player_card_4,
                R.id.player_card_2, R.id.player_card_3, R.id.player_card_1};
        ImageView imageView = findViewById(slots[slot]);
        int cardDrawableID = CardMisc.CardToImageID(card);
        imageView.setImageDrawable(getResources().getDrawable(cardDrawableID));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        putCard(0, new Card(Rate.ACE, Suit.Hearts));
        putCard(1, new Card(Rate.TWO, Suit.Spades));
        putCard(2, new Card(Rate.THREE, Suit.Diamonds));
        putCard(3, new Card(Rate.FOUR, Suit.Clubs));
        putCard(4, new Card(Rate.FIVE, Suit.Clubs));
        putCard(5, new Card(Rate.SIX, Suit.Diamonds));
        putCard(6, new Card(Rate.SEVEN, Suit.Spades));
        putCard(7, new Card(Rate.EIGHT, Suit.Hearts));
        putCard(8, new Card(Rate.NINE, Suit.Hearts));
        putCard(9, new Card(Rate.TEN, Suit.Hearts));


        //TODO: используйте putCard(номер слота, карта), чтобы отрисовать карту на столе


        Card myCard = new Card(Rate.QUEEN, Suit.Hearts);
        int p = myCard.rate.getPoints(); //таким образом можно получить "очки" карты
    }
}