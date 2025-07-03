package io.github.droidkaigi.confsched.testing

data class DescribedRobotBehavior(
    val description: String,
    val steps: List<TestNode>,
)

sealed interface TestNode {
    data class Describe(val description: String) : TestNode
    data class Run(val action: suspend () -> Unit) : TestNode
}
