# ViewModel Savedstate Helpers

[![Build Status](https://www.travis-ci.org/liip/viewmodel-savedstate-helpers.svg?branch=master)](https://www.travis-ci.org/liip/viewmodel-savedstate-helpers)
[![GitHub license](https://img.shields.io/github/license/liip/viewmodel-savedstate-helpers.svg)](https://github.com/liip/viewmodel-savedstate-helpers/blob/master/LICENSE)
[![Jitpack](https://jitpack.io/v/liip/viewmodel-savedstate-helpers.svg)](https://jitpack.io/#liip/viewmodel-savedstate-helpers)

Helper to access easily save the ViewModel state for Activity restoration.

## Installation

In your root *build.gradle* at the end of repositories:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

In your app *build.gradle*, add the dependencies:

```gradle
dependencies {
    // Add this if you don't have it already
    implementation 'androidx.lifecycle:lifecycle-viewmodel-savedstate:1.0.0-alpha02'

    // viewmodel-savedstate-helpers
    implementation 'com.github.liip:viewmodel-savedstate-helpers:1.0.1-alpha02'
}
```

## Usage

### Implement your ViewModel

```kotlin
// Import the library
import ch.liip.viewmodelsavedstatehelpers.*

// Define a ViewModel that takes a SavedStateHandle in argument
class MainViewModel(handle: SavedStateHandle) : ViewModel() {
    // Simple string that is saved in the SavedState
    var manualText by handle.delegate<String?>()

    // MutableLiveData that is saved in the SavedState
    val liveDataText by handle.livedata<String?>()
}
```

### Use your ViewModel

Create your ViewModel like explained in the [official documentation](https://developer.android.com/topic/libraries/architecture/viewmodel-savedstate).

```kotlin
val vm = ViewModelProvider(this, SavedStateVMFactory(this)).get(MainViewModel::class.java)
```

You can then use the ViewModel like you would do usually. Your data is saved and restored automatically!

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Obtain the ViewModel with SavedStateVMFactory
    viewModel = ViewModelProviders.of(this, SavedStateVMFactory(this)).get(MainViewModel::class.java)

    // Observe the livedata
    viewModel.liveDataText.observe(this, Observer {
        liveDataText.setText(it)
    })

    // Save the values
    button.setOnClickListener {
        viewModel.liveDataText.value = liveDataText.text.toString()
    }
}
```

### Demo app

You can check the demo Android application to see it in action.

## Blogpost

Read the accompanying blogpost on [liip.ch](https://www.liip.ch/en/blog/easily-save-android-viewmodel-state).
