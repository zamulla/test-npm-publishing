plugins {
    alias(libs.plugins.npmPublish)
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    js {
        outputModuleName = "shared"
        browser()
        binaries.library()
        generateTypeScriptDefinitions()
        compilerOptions {
            target = "es2015"
        }
    }

    compilerOptions {
        optIn.add("kotlin.js.ExperimentalJsExport")
    }

    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jsMain.dependencies {
            implementation(libs.wrappers.browser)
        }
    }
}

npmPublish {
    organization = "test-karikaturkin"

    registries {
        npmjs {
            authToken = System.getenv("NPM_TOKEN")
        }
    }

    packages {
        named("js") {
            version = "0.0.1"
            packageName = "greetings"
            readme = file("../README.md")

            packageJson {
                license = "Apache 2.0"
                homepage = "https://github.com/Kotlin/kotlin-multiplatform-web-library#readme"
                description = "Shared Kotlin/JS Greetings library"
                keywords = listOf("kotlin", "kotlin-js", "greetings", "shared", "api")
                author {
                    name = "Aleksey Zamulla"
                    url = "https://github.com/zamulla/"
                }
                repository {
                    type = "git"
                    url = "https://github.com/zamulla/test-npm-publishing.git"
                }
            }
        }
    }
}
