package ru.skillbranch.devintensive.extensions

fun String.truncate(n: Int = 16): String {
    if (n < 0) return this
    if (n >= length) return "..."
    var tr = dropLast(length - n).trimEnd()
    if (this.trimEnd() != tr)
        tr += "..."

    return tr
}

fun String.stripHtml(): String {
    val spaces = " {2,}".toRegex()
    val htmlTags = "<[^>]+>".toRegex()
    val escapeSeq = "&[^;]+;".toRegex()
    return this
        .replace(spaces, " ")
        .replace(htmlTags, "")
        .replace(escapeSeq, "")
}