package task2
import java.util.Stack

class Graph(private val graph: Map<Int, List<Int>>){
    fun dfs(start: Int): List<Int>{
        if(!graph.containsKey(start)) throw IllegalArgumentException("No such vertex exists")
        val visited = mutableSetOf<Int>()
        val result = mutableListOf<Int>()
        val stack: Stack<Int> = Stack<Int>()

        stack.push(start)
        while (stack.isNotEmpty()){
            val node = stack.pop()
            if(node !in visited){
                visited.add(node)
                result.add(node)
                for(i in graph[node]?.size!! - 1 downTo 0){
                    val neighbor = graph[node]?.get(i)
                    if(neighbor !in visited) stack.push(neighbor)
                }
            }
        }
        return result
    }
}