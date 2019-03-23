### My Astral Diary
<img src="assets/store/screenshot_1.png" alt="screenshot 1" width="175" />&nbsp;<img src="assets/store/screenshot_2.png" alt="screenshot 2" width="175" />&nbsp;<img src="assets/store/screenshot_3.png" alt="screenshot 3" width="175" />&nbsp;<img src="assets/store/screenshot_4.png" alt="screenshot 4" width="175" />

##### Prerequisites:

1. You need to have JDK installed and the JAVA_HOME system variable set.

##### Before building:

1. Copy "signing/signing.gradle.example" as "signing/signing.gradle"
2. (Only for release builds) Create a keystore in the "signing" directory and set correct values in the "signing/signing.gradle" file.

##### Building release builds:

1. Execute command "gradlew assembleRelease" (Windows) / "gradle assembleRelease" (Other)
2. The .apk file should appear in "project/build/outputs/apk/release" directory.

##### After releasing to store:

1. Upload the "project/build/outputs/mappings/release/mapping.txt" file to store console. This will de-obfuscate crash reports.

##### About architecture

This project applies Domain Driven Development principles, separating project into the main Domain layer and gui/data layers which depend on it. To improve scalability it has been also separated into modules by feature, and common utilities have been moved to their own modules as well.

<img src="assets/store/architecture_diagram.png" alt="architecture diagram" width="720" />