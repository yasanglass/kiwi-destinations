package dev.yasan.kiwi.data.entity

enum class SearchSortMode(val param: String) {
    POPULARITY("popularity"),
    QUALITY("quality"),
    PRICE("price"),
    DURATION("duration"),
    DATE("date");
}