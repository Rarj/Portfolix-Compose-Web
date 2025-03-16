This is a Kotlin Multiplatform project targeting iOS, Web.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `wasmJsMain` would be the right folder for such calls.

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

You can open the web application by running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task.

Credits
Github Icon <a target="_blank" href="https://icons8.com/icon/62856/github">GitHub</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>
LinkedIn Icon <a target="_blank" href="https://icons8.com/icon/98960/linkedin">LinkedIn</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>
Instagram Icon <a target="_blank" href="https://icons8.com/icon/85140/instagram">Instagram</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>
Medium Icon <a target="_blank" href="https://icons8.com/icon/BzFWSIqh6bCr/medium">Medium</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>
Box Icon <a target="_blank" href="https://icons8.com/icon/83142/box">Box</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>
University
Icon <a href="https://www.freepik.com/icon/graduation-cap_3914110#fromView=search&page=1&position=7&uuid=06df8762-939c-4122-808b-1a43ba5f6ad2">
Icon by UIcons</a>
Briefcase Icon <a href="https://www.freepik.com/icon/business-time_7661403">Icon by UIcons</a>
Technology Icon <a href="https://www.freepik.com/icon/model-cube_15399262">Icon by UIcons</a>
Confetti Icon <a href="https://www.flaticon.com/uicons">Icon by Uicons</a>>
