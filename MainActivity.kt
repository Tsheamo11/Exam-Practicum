import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val songTitles = arrayOfNulls<String>(4)
    private val artistNames = arrayOfNulls<String>(4)
    private val ratings = IntArray(4)
    private val comments = arrayOfNulls<String>(4)
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songInput = findViewById<EditText>(R.id.songTitleInput)
        val artistInput = findViewById<EditText>(R.id.artistInput)
        val ratingInput = findViewById<EditText>(R.id.ratingInput)
        val commentInput = findViewById<EditText>(R.id.commentInput)

        val addButton = findViewById<Button>(R.id.addButton)
        val viewButton = findViewById<Button>(R.id.viewButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        addButton.setOnClickListener {
            if (index < 4) {
                try {
                    val title = songInput.text.toString()
                    val artist = artistInput.text.toString()
                    val rating = ratingInput.text.toString().toInt()
                    val comment = commentInput.text.toString()

                    if (rating in 1..5) {
                        songTitles[index] = title
                        artistNames[index] = artist
                        ratings[index] = rating
                        comments[index] = comment
                        index++
                        Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Rating must be between 1 and 5", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Invalid input. Try again.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Playlist full.", Toast.LENGTH_SHORT).show()
            }
        }

        viewButton.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("titles", songTitles)
            intent.putExtra("artists", artistNames)
            intent.putExtra("ratings", ratings)
            intent.putExtra("comments", comments)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finish()
        }
    }

