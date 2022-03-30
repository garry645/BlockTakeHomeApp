## Build tools & versions used
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.32"

    // Material Design
    implementation 'com.google.android.material:material:1.6.0-beta01'

    // Architectural Components
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"

    // Support libraries
    implementation "androidx.appcompat:appcompat:1.4.1"
    implementation "androidx.fragment:fragment-ktx:1.4.1"
    implementation "androidx.constraintlayout:constraintlayout:2.1.3"
    implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

    // Android KTX
    implementation "androidx.core:core-ktx:1.7.0"

    // Room and Lifecycle dependencies
    implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"

    // Coroutine Lifecycle Scopes
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0"

    // Activity KTX for viewModels()
    implementation "androidx.activity:activity-ktx:1.4.0"
    implementation "androidx.fragment:fragment-ktx:1.4.1"

    //Dagger - Hilt
    implementation "com.google.dagger:hilt-android:2.41"
    kapt "com.google.dagger:hilt-compiler:2.41"

    // Networking
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.okhttp3:okhttp:4.7.2"
    implementation "com.squareup.okhttp3:logging-interceptor:4.7.2"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"

    //Glide
    implementation 'com.github.bumptech.glide:glide:4.13.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.13.0'

    // Testing
    testImplementation "junit:junit:4.13.2"
    testImplementation "io.mockk:mockk:1.11.0"
    testImplementation "androidx.arch.core:core-testing:2.1.0"

    kapt "android.arch.persistence.room:compiler:1.1.1"

## Steps to run the app

    1. Open the project in Android Studio (or pull from my gitHub: https://github.com/garry645/BlockTakeHomeApp.git)
    2. Create an emulator
    3. Press the green play button in the top right hand corner

## What areas of the app did you focus on?

    My main focus was UI and architecture. I'm not a very good UI/UX designer, but I do feel very confident
    in my ability to receive a mock-up of a UI and implement it properly.

## What was the reason for your focus? What problems were you trying to solve?

    The main reason I focused on UI was because it is one of my biggest strengths. I feel very comfortable
    using xml along with android binding to create complex layouts. I focused on architecture because
    I wanted to show off my skills using the android jetpack libraries. Coroutines with Retrofit were useful for network calls,
    ViewModels helped me maintain state through the android lifecycle, and Hilt helped me inject my viewmodels easily into my fragment.

## How long did you spend on this project?

    I didn't keep complete track of my time spent on the project but I would estimate it took me anywhere from 4-6 hours to complete.

## Did you make any trade-offs for this project? What would you have done differently with more time?

    The main trade-off I made was with the animation of the visibility of data within the recyclerview viewholders. If I had more
    time/knowledge I would have tried to make the animations smoother.

## What do you think is the weakest part of your project?

    The weakest part of the project is most definitely the test cases. I have a lot of experience writing
    test cases, but I realized while creating this project that I am very weak when it comes to setting up the test
    module. I feel as though the cases I wrote show my ability to write cases even though the module may not
    have been set up correctly. This is the other major area of the app that I would improve given more time.

## Did you copy any code or dependencies? Please make sure to attribute them here!

    The dependencies I use actually come from a project I have developed. In my experience Hilt is a
    little funny when trying to line up dependency versions and this has caused problems for me in the
    past so I try to keep a running sheet of working dependency versions.

## Is there any other information you’d like us to know?

    I had a lot of fun developing this application. The main takeaway I'd like the reviewer to have is that I feel
    as though I have a lot of breadth but not much depth when it comes to Android Development. I know
    I have only scratched the surface of the dependencies I have used to develop this app and I want to learn
    more about how I can use these technologies to build faster and more reliable applications. 
