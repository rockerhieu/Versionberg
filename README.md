[![CircleCI](https://circleci.com/gh/rockerhieu/Versionberg.svg?style=svg)](https://circleci.com/gh/rockerhieu/Versionberg)
![Versionberg on Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.rockerhieu/Versionberg/badge.svg)

## Versionberg

Gradle plugin for versioning your library/application on every git commit.

![image](https://github.com/rockerhieu/Versionberg/raw/master/assets/poster.jpg)

## Usage

### Gradle 2.1+
```groovy
plugins {
  id "io.github.rockerhieu.versionberg" version "<latest-version>"
}
```

### Older Gradle versions
```groovy
buildscript {
    repositories {
        jcenter()
        maven { url 'https://oss.sonatype.org/content/repositories/snapshots/' }
    }
    dependencies {
        classpath 'io.github.rockerhieu:versionberg:<latest-version>'
    }
}

apply plugin: 'io.github.rockerhieu.versionberg'
```

### Config
A version contains 4 parts: major, minor, patch and build.
```groovy
versionberg {
    major 1   									// default is 0
    minor 0 									// default is 0
    patch 10									// default is 0
    build 2 									// default is ${commitCount}
    nameTemplate '${major}.${minor}-SNAPSHOT'	// default is '${major}.${minor}.${patch}.${commitSha}'
    codeTemplate '${commitCount}'				// default is '(((${major} * 100) + ${minor}) * 100) * 100000 + ${build}'
}
```

## Contributing

Please fork this repository and contribute back using
[pull requests](https://github.com/rockerhieu/Versionberg/pulls).

Any contributions, large or small, major features, bug fixes, additional
language translations, unit/integration tests are welcomed and appreciated
but will be thoroughly reviewed and discussed.

## License

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
