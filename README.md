# Mime - Media types (MIME types) RFC6838

[![DevOps By Rultor.com](http://www.rultor.com/b/g4s8/mime)](http://www.rultor.com/p/g4s8/mime)

 [![Bintray](https://api.bintray.com/packages/g4s8/maven-android/com.g4s8.mime/images/download.svg)](https://bintray.com/g4s8/maven-android/com.g4s8.mime/_latestVersion)

[![Build Status](https://img.shields.io/travis/g4s8/mime.svg?style=flat-square)](https://travis-ci.org/g4s8/mime)
[![Build status](https://ci.appveyor.com/api/projects/status/9mk57am5d3r70geo?svg=true)](https://ci.appveyor.com/project/g4s8/mime)
[![PDD status](http://www.0pdd.com/svg?name=g4s8/mime)](http://www.0pdd.com/p?name=g4s8/mime)
[![License](https://img.shields.io/github/license/g4s8/mime.svg?style=flat-square)](https://github.com/g4s8/mime/blob/master/LICENSE.txt)
[![Test Coverage](https://img.shields.io/codecov/c/github/g4s8/mime.svg?style=flat-square)](https://codecov.io/github/g4s8/mime?branch=master)

## About
[Mime](https://github.com/g4s8/mime) - object-oriented java library without dependencies for parsing and creating
HTTP media-types according to [RFC6838](https://tools.ietf.org/html/rfc6838) specifications.

## Usage
For better understanding it's recommended to see [unit-tests](https://github.com/g4s8/mime/blob/master/src/test/java/com/g4s8/mime/).

### Parsing from string
```java
final MimeType mime = new MimeTypeOf("text/html");
System.out.printf("type: '%s', subtype: '%s'", mime.type(), mime.subtype());
// type: 'text', subtype: 'html'
```
### Common params
The most common params, such as charset, boundary etc. can be accessed through `MimeTypeSmart` object:
```java
System.out.printf(
    "encoding: '%s'",
    new MimeTypeSmart(
        new MimeTypeOf("plain/text; charset=utf-8")
    ).charset()
);
// encoding: 'utf-8'
```

## Testing
[Hamcrest](http://hamcrest.org/JavaHamcrest/) matchers are included in `test` package. Them can be used to verify mime-types:
```java
// verify type
MatcherAssert.assertThat(
    new MimeTypeOf("application/pdf"),
    new HmMimeHasType("application")
);

// verify subtype
MatcherAssert.assertThat(
    new MimeTypeOf("image/bmp"),
    new HmMimeHasSubType("bmp")
);

// verify parameter
MatcherAssert.assertThat(
    new MimeTypeOf("image/bmp; charset=utf-8"),
    new HmMimeHasParameter("charset", "utf-8")
);
```

## Contributing
If you are interested in contributing please refer to [CONTRIBUTING.md](CONTRIBUTING.md)
