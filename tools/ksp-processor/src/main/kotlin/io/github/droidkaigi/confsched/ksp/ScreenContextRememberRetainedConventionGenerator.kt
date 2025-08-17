package io.github.droidkaigi.confsched.ksp

import com.google.devtools.ksp.getDeclaredFunctions
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo

/**
 * Generates a short-hand function for creating a retained instance of a screen context.
 *
 * If you have a screen context defined like this:
 * ```kotlin
 * @ContributesGraphExtension(MyScope::class)
 * interface MyScreenContext : ScreenContext {
 *     @ContributesGraphExtension.Factory(AppScope::class)
 *     fun interface Factory {
 *         fun createMyScreenContext(
 *             @Provides param1: Type1,
 *             @Provides param2: Type2,
 *         ): MyScreenContext
 *     }
 * }
 * ```
 *
 * Then this processor will generate a function like this:
 * ```kotlin
 * @Composable
 * fun MyScreenContext.Factory.rememberMyScreenContextRetained(param1: Type1, param2: Type2): MyScreenContext {
 *     return rememberRetained {
 *         createMyScreenContext(param1, param2)
 *     }
 * }
 * ```
 *
 * This improves our code readability.
 */
class ScreenContextRememberRetainedConventionGenerator(
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val screenContextClasses = resolver
            .getSymbolsWithAnnotation(CONTRIBUTES_TO_GRAPH_EXTENSION_ANNOTATION_FQ_NAME)
            .filterIsInstance<KSClassDeclaration>()

        screenContextClasses.forEach { screenContextClass ->
            val screenContextName = screenContextClass.simpleName.asString()

            val factoryClass = screenContextClass.declarations
                .single { declaration ->
                    declaration.annotations.any { it.annotationType.resolve().toClassName().canonicalName == CONTRIBUTES_TO_GRAPH_EXTENSION_FACTORY_ANNOTATION_FQ_NAME }
                } as KSClassDeclaration

            val factoryMethod = factoryClass.getDeclaredFunctions().single()

            val generatedFile = FileSpec.builder(screenContextClass.packageName.asString(), "${screenContextName}RememberRetained")
                .addImport(RIN_PACKAGE_NAME, REMEMBER_RETAINED_FUNCTION_NAME)
                .addFunction(
                    FunSpec.builder("remember${screenContextName}Retained")
                        .addAnnotation(ClassName.bestGuess(COMPOSABLE_ANNOTATION_FQ_NAME))
                        .receiver(factoryClass.toClassName())
                        .returns(screenContextClass.toClassName())
                        .addParameters(
                            factoryMethod.parameters.map { parameter ->
                                ParameterSpec.builder(parameter.name!!.asString(), type = parameter.type.resolve().toClassName())
                                    .build()
                            },
                        )
                        .addCode(
                            """
                            return rememberRetained {
                                ${factoryMethod.simpleName.asString()}(${factoryMethod.parameters.joinToString(", ") { it.name!!.asString() }}) 
                            }
                            """.trimIndent(),
                        )
                        .build(),
                )
                .build()

            generatedFile.writeTo(
                codeGenerator = codeGenerator,
                dependencies = Dependencies(aggregating = false, screenContextClass.containingFile!!),
            )
        }

        return emptyList()
    }

    private companion object {
        private const val COMPOSABLE_ANNOTATION_FQ_NAME = "androidx.compose.runtime.Composable"
        private const val CONTRIBUTES_TO_GRAPH_EXTENSION_ANNOTATION_FQ_NAME = "dev.zacsweers.metro.ContributesGraphExtension"
        private const val CONTRIBUTES_TO_GRAPH_EXTENSION_FACTORY_ANNOTATION_FQ_NAME = "dev.zacsweers.metro.ContributesGraphExtension.Factory"
        private const val RIN_PACKAGE_NAME = "io.github.takahirom.rin"
        private const val REMEMBER_RETAINED_FUNCTION_NAME = "rememberRetained"
    }
}
