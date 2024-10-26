package com.example.midtermandroid_yerkazy

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.Color
import com.example.midtermandroid_yerkazy.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(navController: NavHostController, movieName: String) {
    val movieData = mapOf(
        "Inception" to Pair(
            "Inception",
            "Inception is a 2010 science fiction heist thriller directed by Christopher Nolan. The film follows Dom Cobb, a skilled thief who specializes in extracting valuable secrets from deep within the subconscious during the dream state. His unique ability has made him a coveted player in the dangerous world of corporate espionage but has cost him everything he loves. Cobb is given a chance at redemption when he is offered a seemingly impossible task: planting an idea into someone’s mind. If he succeeds, it will be the perfect crime, but a dangerous enemy anticipates Cobb’s every move."
        ),
        "The Matrix" to Pair(
            "The Matrix",
            "The Matrix is a 1999 science fiction action film written and directed by the Wachowskis. It depicts a dystopian future in which humanity is unknowingly trapped inside a simulated reality called the Matrix, created by intelligent machines to distract humans while using their bodies as an energy source. The story follows Neo, a hacker, who discovers the truth about the Matrix and joins a rebellion against the machines. Featuring groundbreaking special effects and philosophical themes of reality, identity, and free will, The Matrix is regarded as one of the greatest sci-fi films of all time."
        ),
        "Interstellar" to Pair(
            "Interstellar",
            "Interstellar is a 2014 epic science fiction film directed by Christopher Nolan. It stars Matthew McConaughey as a former NASA pilot turned farmer, Cooper, who leads a team of astronauts on a journey through a wormhole near Saturn in search of a new habitable planet for humanity. As Earth becomes increasingly uninhabitable due to crop failures and extreme weather, Cooper must confront the challenges of space travel, time dilation, and the limits of human knowledge while also facing emotional dilemmas involving his daughter. The film explores themes of love, sacrifice, and humanity's place in the universe."
        ),
        "The Godfather" to Pair(
            "The Godfather",
            "The Godfather is a 1972 American crime film directed by Francis Ford Coppola, based on Mario Puzo's novel of the same name. It chronicles the powerful Italian-American crime family of Don Vito Corleone, played by Marlon Brando, and his youngest son, Michael, who reluctantly joins the mafia and becomes a ruthless leader. The film is known for its portrayal of the Corleone family's rise to power, the intricate depiction of organized crime, and the balance between loyalty, family ties, and personal ambition. The Godfather is often regarded as one of the greatest films in world cinema."
        ),
        "The Dark Knight" to Pair(
            "The Dark Knight",
            "The Dark Knight is a 2008 superhero film directed by Christopher Nolan. It is the second film in Nolan's Batman trilogy, following Batman Begins. In this film, Batman, portrayed by Christian Bale, faces his greatest challenge yet in the form of the Joker, played by Heath Ledger. The Joker is a psychopathic criminal mastermind who seeks to create chaos in Gotham City. The film is acclaimed for its mature themes, moral dilemmas, and the performance of Ledger, whose portrayal of the Joker won him a posthumous Academy Award. The Dark Knight is widely regarded as one of the best superhero films ever made."
        ),
        "Nomads" to Pair(
            "Nomads",
            "Nomads is a 2005 historical drama about the 18th-century struggles of the Kazakh people to preserve their freedom against the invading Jungars. The film follows the life of Mansur, a warrior who becomes one of the leaders of the Kazakh people in their fight for independence. Nomads is a visually stunning portrayal of Kazakh history, culture, and traditions, showcasing the courage and resilience of the Kazakh warriors and the importance of unity in the face of oppression."
        ),
        "Myn Bala: Warriors of the Steppe" to Pair(
            "Myn Bala",
            "Myn Bala: Warriors of the Steppe is a 2011 Kazakh historical drama set in 1729, during a time of intense conflict between the Kazakh people and the invading Jungars. The film tells the story of Sartai, a young warrior who leads a group of brave teenagers, known as 'myn bala' (thousand boys), in the fight for their nation's freedom. With epic battle scenes and a deep focus on the courage and sacrifices of the young warriors, the movie explores themes of patriotism, friendship, and honor."
        ),
        "Tomyris" to Pair(
            "Tomyris",
            "Tomyris is a 2019 historical epic about the legendary queen of the Massagetae, Tomyris, who is famous for leading her people in the battle against the Persian king, Cyrus the Great. The film depicts Tomyris's rise to power, her leadership, and the eventual defeat of Cyrus, one of the most powerful rulers of the ancient world. With strong themes of female empowerment, justice, and leadership, Tomyris highlights the strength and resilience of one of history's most formidable female warriors."
        ),
        "Kyz Zhibek" to Pair(
            "Kyz Zhibek",
            "Kyz Zhibek is a 1970 romantic drama based on a famous Kazakh epic poem. The story follows the tragic love of Zhibek, a beautiful Kazakh maiden, and Tolegen, a noble young man. Their love is torn apart by feuding families and the jealous rival Bekezhan, leading to a tragic ending. The film is a beautifully shot period piece, filled with Kazakh folk music, cultural traditions, and a poignant exploration of love, sacrifice, and fate."
        ),
        "Business Kazakh Style" to Pair(
            "Business Kazakh Style",
            "Business Kazakh Style is a modern Kazakh comedy that follows the life of a businessman who finds himself in a series of humorous situations as he navigates the world of modern entrepreneurship. With witty dialogue, colorful characters, and a satirical look at the challenges of balancing traditional Kazakh values with the pressures of modern life, this film delivers plenty of laughs while also touching on important themes of cultural identity and modernization."
        )
    )

    val (movieTitle, description) = movieData[movieName] ?: Pair("Unknown Movie", "Description not available.")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = movieTitle) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Image(
                    painter = painterResource(id = when (movieName) {
                        "Inception" -> R.drawable.inception_image
                        "The Matrix" -> R.drawable.matrix_image
                        "Interstellar" -> R.drawable.interstellar_image
                        "The Godfather" -> R.drawable.godfather_image
                        "The Dark Knight" -> R.drawable.darkknight_image
                        "Nomads" -> R.drawable.nomads_image
                        "Myn Bala: Warriors of the Steppe" -> R.drawable.mynbala_image
                        "Tomyris" -> R.drawable.tomyris_image
                        "Kyz Zhibek" -> R.drawable.kyz_zhibek_image
                        "Business Kazakh Style" -> R.drawable.business_kazakh_image
                        else -> R.drawable.default_image
                    }),
                    contentDescription = movieTitle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Details about $movieTitle",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = description,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                lineHeight = 24.sp
            )
        }
    }
}
