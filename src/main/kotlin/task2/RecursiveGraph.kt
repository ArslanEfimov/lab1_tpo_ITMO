package task2

class RecursiveGraph(private val graph: Map<Int, List<Int>>) {
    fun dfs(node: Int, visited: MutableSet<Int> = mutableSetOf()): List<Int> {
        if (!graph.containsKey(node)) {
            throw IllegalArgumentException("No such vertex exists")
        }
        if (node in visited) return emptyList()

        visited.add(node)
        val result = mutableListOf(node)
        graph[node]?.forEach { neighbor ->
            result.addAll(dfs(neighbor, visited))
        }
        return result
    }
}