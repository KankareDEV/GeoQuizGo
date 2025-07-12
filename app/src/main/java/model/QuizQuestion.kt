package com.example.geoquizgo.data

data class Question(
    val text: String,
    val options: List<String>,
    val correctIndex: Int
)

enum class Category {
    Europe, USA, SouthAmerica, Asia
}

enum class Language {
    EN, IT
}

object QuizQuestions {

    fun getQuestions(category: Category, language: Language): List<Question> {
        return when (language) {
            Language.EN -> getEnglishQuestions(category)
            Language.IT -> getItalianQuestions(category)
        }
    }

    private fun getEnglishQuestions(category: Category): List<Question> {
        return when (category) {
            Category.Europe -> listOf(
                Question("What is the capital of Germany?", listOf("Berlin", "Paris", "Rome", "Madrid"), 0),
                Question("Which country uses the Euro?", listOf("Sweden", "Hungary", "France", "Poland"), 2),
                Question("Which sea is south of Italy?", listOf("Baltic Sea", "Black Sea", "Mediterranean Sea", "North Sea"), 2),
                Question("Which country is famous for tulips?", listOf("Germany", "Netherlands", "Austria", "Belgium"), 1),
                Question("What mountain range is in Switzerland?", listOf("Andes", "Rockies", "Alps", "Himalayas"), 2)
            )

            Category.USA -> listOf(
                Question("What is the capital of the USA?", listOf("New York", "Los Angeles", "Washington D.C.", "Chicago"), 2),
                Question("Which state is known as the Sunshine State?", listOf("California", "Texas", "Florida", "Nevada"), 2),
                Question("Where is the Grand Canyon?", listOf("Nevada", "Arizona", "Utah", "New Mexico"), 1),
                Question("Which US state is an island?", listOf("Alaska", "Hawaii", "California", "Maine"), 1),
                Question("Where is the Statue of Liberty?", listOf("Chicago", "Los Angeles", "New York", "Boston"), 2)
            )

            Category.SouthAmerica -> listOf(
                Question("What is the capital of Brazil?", listOf("Rio", "Brasília", "São Paulo", "Salvador"), 1),
                Question("Which country speaks Spanish?", listOf("Brazil", "Argentina", "Guyana", "Suriname"), 1),
                Question("Where are the Andes mountains?", listOf("Chile", "Paraguay", "Uruguay", "Panama"), 0),
                Question("What is the largest rainforest?", listOf("Sahara", "Congo", "Amazon", "Alps"), 2),
                Question("Which country is famous for tango?", listOf("Peru", "Argentina", "Colombia", "Ecuador"), 1)
            )

            Category.Asia -> listOf(
                Question("What is the capital of Japan?", listOf("Seoul", "Tokyo", "Beijing", "Bangkok"), 1),
                Question("Which country is in Asia?", listOf("Brazil", "India", "Portugal", "Greece"), 1),
                Question("Where is the Great Wall located?", listOf("India", "Korea", "China", "Thailand"), 2),
                Question("Which Asian country has islands?", listOf("Kazakhstan", "Mongolia", "Philippines", "Uzbekistan"), 2),
                Question("Which city is in South Korea?", listOf("Osaka", "Taipei", "Seoul", "Hanoi"), 2)
            )
        }
    }

    private fun getItalianQuestions(category: Category): List<Question> {
        return when (category) {
            Category.Europe -> listOf(
                Question("Qual è la capitale della Germania?", listOf("Berlino", "Parigi", "Roma", "Madrid"), 0),
                Question("Quale paese usa l'euro?", listOf("Svezia", "Ungheria", "Francia", "Polonia"), 2),
                Question("Quale mare è a sud dell’Italia?", listOf("Mar Baltico", "Mar Nero", "Mar Mediterraneo", "Mare del Nord"), 2),
                Question("Quale paese è famoso per i tulipani?", listOf("Germania", "Paesi Bassi", "Austria", "Belgio"), 1),
                Question("Quale catena montuosa si trova in Svizzera?", listOf("Ande", "Rocciose", "Alpi", "Himalaya"), 2)
            )

            Category.USA -> listOf(
                Question("Qual è la capitale degli Stati Uniti?", listOf("New York", "Los Angeles", "Washington D.C.", "Chicago"), 2),
                Question("Quale stato è chiamato lo Stato del Sole?", listOf("California", "Texas", "Florida", "Nevada"), 2),
                Question("Dove si trova il Grand Canyon?", listOf("Nevada", "Arizona", "Utah", "Nuovo Messico"), 1),
                Question("Quale stato americano è un'isola?", listOf("Alaska", "Hawaii", "California", "Maine"), 1),
                Question("Dove si trova la Statua della Libertà?", listOf("Chicago", "Los Angeles", "New York", "Boston"), 2)
            )

            Category.SouthAmerica -> listOf(
                Question("Qual è la capitale del Brasile?", listOf("Rio", "Brasilia", "San Paolo", "Salvador"), 1),
                Question("Quale paese parla spagnolo?", listOf("Brasile", "Argentina", "Guyana", "Suriname"), 1),
                Question("Dove si trovano le Ande?", listOf("Cile", "Paraguay", "Uruguay", "Panama"), 0),
                Question("Qual è la più grande foresta pluviale?", listOf("Sahara", "Congo", "Amazzonia", "Alpi"), 2),
                Question("Quale paese è famoso per il tango?", listOf("Perù", "Argentina", "Colombia", "Ecuador"), 1)
            )

            Category.Asia -> listOf(
                Question("Qual è la capitale del Giappone?", listOf("Seul", "Tokyo", "Pechino", "Bangkok"), 1),
                Question("Quale paese si trova in Asia?", listOf("Brasile", "India", "Portogallo", "Grecia"), 1),
                Question("Dove si trova la Grande Muraglia?", listOf("India", "Corea", "Cina", "Thailandia"), 2),
                Question("Quale paese asiatico ha isole?", listOf("Kazakistan", "Mongolia", "Filippine", "Uzbekistan"), 2),
                Question("Quale città è in Corea del Sud?", listOf("Osaka", "Taipei", "Seoul", "Hanoi"), 2)
            )
        }
    }
}
