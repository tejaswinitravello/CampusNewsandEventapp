package app.campusnewsevents.tejaswinis3567095

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private data class NewsItem(
    val title: String,
    val category: String,
    val date: String,
    val summary: String
)

private data class CampusEvent(
    val title: String,
    val date: String,
    val time: String,
    val venue: String
)

private val sampleNews = listOf(
    NewsItem(
        title = "Library extends evening hours",
        category = "Campus",
        date = "November 21",
        summary = "The main library will stay open later during the exam period."
    ),
    NewsItem(
        title = "Student research showcase announced",
        category = "Academics",
        date = "November 20",
        summary = "Students are invited to share projects at this year's showcase."
    ),
    NewsItem(
        title = "Campus cleanup day this weekend",
        category = "Community",
        date = "November 19",
        summary = "Join fellow students to help care for shared campus spaces."
    )
)

private val sampleEvents = listOf(
    CampusEvent(
        title = "Open Mic Night",
        date = "November 24",
        time = "6:00 PM",
        venue = "Student Center"
    ),
    CampusEvent(
        title = "Career and Internship Fair",
        date = "November 27",
        time = "10:00 AM",
        venue = "Main Hall"
    )
)

@Composable
fun CampusHomeScreen() {
    var searchText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }

    val categories = listOf("All", "Campus", "Academics", "Community")

    val filteredNews = sampleNews.filter { news ->
        val matchesCategory =
            selectedCategory == "All" || news.category == selectedCategory
        val matchesSearch =
            news.title.contains(searchText, ignoreCase = true) ||
                    news.summary.contains(searchText, ignoreCase = true)

        matchesCategory && matchesSearch
    }

    Scaffold(
        containerColor = Color(0xFFF7F8FA),
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("🏠  Home", fontWeight = FontWeight.Bold)
                Text("📰  News")
                Text("📅  Events")
                Text("♡  Saved")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Campus Connect",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Your campus, all in one place",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            item {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Search campus news") },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )
            }

            item {
                Text(
                    text = "Browse by category",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    categories.forEach { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            label = { Text(category) }
                        )
                    }
                }
            }

            item {
                Text(
                    text = "Upcoming events",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            items(sampleEvents) { event ->
                EventCard(event = event)
            }

            item {
                Text(
                    text = "Latest campus news",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            if (filteredNews.isEmpty()) {
                item {
                    Text(
                        text = "No news found. Try another search or category.",
                        color = Color.Gray
                    )
                }
            } else {
                items(filteredNews) { news ->
                    NewsCard(news = news)
                }
            }
        }
    }
}

@Composable
private fun NewsCard(news: NewsItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(Color(0xFFE5EAF3), RoundedCornerShape(12.dp)),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("News image", color = Color.Gray)
            }

            Spacer(Modifier.height(12.dp))
            Text(news.category, color = Color(0xFF345995))
            Text(news.title, fontWeight = FontWeight.Bold)
            Text(news.date, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Spacer(Modifier.height(4.dp))
            Text(news.summary, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(8.dp))
            Text("♡  Save article", color = Color(0xFF345995))
        }
    }
}

@Composable
private fun EventCard(event: CampusEvent) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(event.title, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            Text("${event.date} · ${event.time}")
            Text("Venue: ${event.venue}", color = Color.Gray)
            Spacer(Modifier.height(8.dp))
            Button(onClick = { /* Add event action  */ }) {
                Text("View event")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CampusHomeScreenPreview() {
    MaterialTheme {
        CampusHomeScreen()
    }
}