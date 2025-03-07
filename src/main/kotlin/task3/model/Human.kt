package task3.model

import task3.interfaces.Speakable

class Human(val name: String) : Speakable {

    override fun speak(content: String, type: TypeOfMessage): Message {
        return Message(content, type)
    }
}