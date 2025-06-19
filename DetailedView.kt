import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val songTitles = intent.getStringArrayExtra("titles")
        val artistNames = intent.getStringArrayExtra("artists")
        val ratings = intent.getIntArrayExtra("ratings")
        val comments = intent.getStringArrayExtra("comments")

        val detailsText = findViewById<TextView>(R.id.detailsText)
        val averageText = findViewById<TextView>(R.id.averageText)
        val displayButton = findViewById<Button>(R.id.displayButton)
        val averageButton = findViewById<Button>(R.id.averageButton)
        val backButton = findViewById<Button>(R.id.backButton)

        displayButton.setOnClickListener {
            val builder = StringBuilder()
            for (i in 0 until (songTitles?.size ?: 0)) {
                builder.append("Song: ${songTitles?.get(i)}\n")
                builder.append("Artist: ${artistNames?.get(i)}\n")
                builder.append("Rating: ${ratings?.get(i)}\n")
                builder.append("Comment: ${comments?.get(i)}\n\n")
            }
            detailsText.text = builder.toString()
        }

        averageButton.setOnClickListener {
            if (ratings != null && ratings.isNotEmpty()) {
                val average = ratings.sum().toDouble() / ratings.size
                averageText.text = "Average Rating: %.2f".format(average)
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }

}

