package task3.model

enum class TypeOfMessage{
    KINDLY,
    PASSIVE_AGGRESSIVE,
    AGGRESSIVE
}
data class Message(val content: String, val type: TypeOfMessage) {
}