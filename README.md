[![CircleCI](https://circleci.com/gh/rockerhieu/Versionberg.svg?style=svg)](https://circleci.com/gh/rockerhieu/Versionberg)
![Versionberg on Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.rockerhieu/Versionberg/badge.svg)

# Versionberg

Gradle plugin for versioning your library/application on every git commit.

![image](https://github.com/rockerhieu/Versionberg/raw/master/assets/poster.jpg)

# Usage
You can use the new plugin syntax for gradle `2.1+`:
```groovy
plugins {
  id "io.github.rockerhieu.versionberg" version "<latest-version>"
}
```

Or the legacy way:
 ```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'io.github.rockerhieu:versionberg:<latest-version>'
    }
}

apply plugin: 'io.github.rockerhieu.versionberg'
```

## Java
```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'io.github.rockerhieu:versionberg:<latest-version>'
    }
}

apply plugin: 'io.github.rockerhieu.versionberg'
apply plugin: 'java'

versionberg {
    major 1
    minor 0
    patch 0
}

version = versionberg.name
```

## Android
```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'io.github.rockerhieu:versionberg:<latest-version>'
    }
}

apply plugin: 'io.github.rockerhieu.versionberg'
apply plugin: 'com.android.application'

versionberg {
    major 1
    minor 0
    patch 0
}

android {
    defaultConfig {
        versionCode versionberg.code
        versionName versionberg.name
    }
}
```

## Advanced usage
You can define template for version name and version code if you don't want to use the default settings provided by `Versionberg`

```groovy
versionberg {
    // Increase when you make incompatible API changes (default value is 0)
    major 1

    // Increase when you add functionality in a backwards-compatible manner (default value is 0)
    minor 0

    // Increase when you make backwards-compatible bug fixes (default value is 0)
    patch 0

    // default is ${commitCount}, uncomment to use a custom build number
    // build 2

    // Default version name template is '${major}.${minor}.${patch}.${build}'
    nameTemplate '${major}.${minor}-SNAPSHOT'
    
    // Default version code template is '${build}'
    codeTemplate '(((${major} * 100) + ${minor}) * 100) * 100000 + ${build}'
}
```

See [Advanced Usage](https://github.com/rockerhieu/Versionberg/wiki/Advanced-Usage#template-variables) for a the list of all available variables can be used in `nameTemplate` and `codeTemplate`.

## My config
This is the config that I been using for some years:
```groovy
versionberg {
    major 1
    minor 0
    patch 10
    nameTemplate '${major}.${minor}.${commitCount}.${commitSha}'
    codeTemplate '(((${major} * 100) + ${minor}) * 100) * 100000 + ${build}'
}
```

Main advantages:
* `${commitCount}`: easy to reference to a version when communicating
* `${commitSha}`: easy to identify the git revision of a given build

# Acknowledgements
Poster by [Breaking Bad Generator](http://sbll.org/breakingbad/)

# Contributing

Please fork this repository and contribute back using
[pull requests](https://github.com/rockerhieu/Versionberg/pulls).

Any contributions, large or small, major features, bug fixes, additional
language translations, unit/integration tests are welcomed and appreciated
but will be thoroughly reviewed and discussed.

# License

* [The MIT License](https://opensource.org/licenses/MIT)

```
Copyright (c) 2016 Hieu Rocker

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the "Software"),
to deal in the Software without restriction, including without limitation
the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
DEALINGS IN THE SOFTWARE.
```
