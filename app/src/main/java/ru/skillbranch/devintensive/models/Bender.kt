package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {
    private var errorCount = 0

    fun askQuestion(): String = question.question

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        val trAnswer = answer.trim()

        val validateMessage = validate(trAnswer)
        if (validateMessage != "")
            return "$validateMessage\n${question.question}" to status.color

        return if (question.answers.contains(trAnswer.toLowerCase())) {
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        } else {
            errorCount++
            if (errorCount >= MAX_ERROR_COUNT) {
                errorCount = 0
                status = Status.NORMAL
                question = Question.NAME
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            } else {
                status = status.nextStatus()
                "Это неправильный ответ\n${question.question}" to status.color
            }
        }
    }

    private fun validate(trAnswer: String): String {
        return when (question) {
            Question.NAME -> {
                val firstIsUpperCase = trAnswer.firstOrNull()?.isUpperCase() != false
                if (firstIsUpperCase) {
                    ""
                } else {
                    "Имя должно начинаться с заглавной буквы"
                }
            }

            Question.PROFESSION -> {
                val firstIsLowerCase = trAnswer.firstOrNull()?.isLowerCase() != false
                if (firstIsLowerCase) {
                    ""
                } else {
                    "Профессия должна начинаться со строчной буквы"
                }
            }

            Question.MATERIAL -> {
                val containsDigits = trAnswer.contains("\\d".toRegex())
                if (!containsDigits) {
                    ""
                } else {
                    "Материал не должен содержать цифр"
                }
            }

            Question.BDAY -> {
                val containsLetters = trAnswer.contains("\\D".toRegex())
                if (!containsLetters) {
                    ""
                } else {
                    "Год моего рождения должен содержать только цифры"
                }
            }

            Question.SERIAL -> {
                val contains7Digits = trAnswer.length == 7 && !trAnswer.contains("\\D".toRegex())
                if (contains7Digits) {
                    ""
                } else {
                    "Серийный номер содержит только цифры, и их 7"
                }
            }
            Question.IDLE -> "" // Ignore
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            val values = values()
            return if (ordinal < values.lastIndex) {
                values[ordinal + 1]
            } else {
                values[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion() = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion() = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion() = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion() = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion() = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion() = IDLE
        };

        abstract fun nextQuestion(): Question
    }

    companion object Static {
        const val MAX_ERROR_COUNT = 3
    }
}