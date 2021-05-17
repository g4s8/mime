[Mime](https://github.com/g4s8/mime) is an object-oriented java library without dependencies for parsing and creating
HTTP media-types according to [RFC6838](https://tools.ietf.org/html/rfc6838) specifications.

[![CI checks](https://github.com/g4s8/mime/actions/workflows/ci-checks.yml/badge.svg)](https://github.com/g4s8/mime/actions/workflows/ci-checks.yml)
[![License](https://img.shields.io/github/license/g4s8/mime.svg?style=flat-square)](https://github.com/g4s8/mime/blob/master/LICENSE.txt)
[![Test Coverage](https://img.shields.io/codecov/c/github/g4s8/mime.svg?style=flat-square)](https://codecov.io/github/g4s8/mime?branch=master)

## Usage

MIME type has mandatory type and subtype, e.g. `application/json` has `application` type
and `json` subtype. Some MIME types has additional parameters, e.g. `text/plain; encoding=utf-8` has
`charset` param with `utf-8` value.

For all these entities there are related method exist. To construct MIME parser use `new MimeTypeOf` constructor:
```java
var mime = new MimeTypeOf("text/xml; encoding=utf-8");
var type = mime.type(); // "text"
var subtype = mime.subtype(); // "xml"
var encoding = mime.param("encoding"); // "utf-8"
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

Maintaining:

[![PDD status](http://www.0pdd.com/svg?name=g4s8/mime)](http://www.0pdd.com/p?name=g4s8/mime)
