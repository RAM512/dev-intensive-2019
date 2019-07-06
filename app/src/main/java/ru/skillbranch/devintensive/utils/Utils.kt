package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        if (fullName == null || fullName == "" || fullName == " ")
            return null to null

        val parts: List<String>? = fullName.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var translit = ""
        for (char in payload) {
            val c = char.toString().toLowerCase()
            translit +=
                if (char == ' ')
                    divider
                else if (c in translitDict) {
                    val tchar = translitDict[c]
                    if (char.isUpperCase())
                        tchar?.toUpperCase()
                    else
                        tchar
                } else {
                    char
                }
        }
        return translit
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val f = firstName?.trim()?.firstOrNull()
        val l = lastName?.trim()?.firstOrNull()
        return if (f == null && l == null) null else "${f ?: ""}${l ?: ""}".toUpperCase()
    }
}