# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

# unreleased

# 0.2.0

### :warning: Breaking changes

- offer implicit circe decoders only for `RefinedTypeThrow[O, N]` instead of `RefinedType[O, N, E]`. This is part of a larger decision that pureharm simply uses Throwable subtypes for errors. The support for the generic version also tripped up end users that didn't have a `Show[Throwable]` instance in scope.

### Deprecations

- all `unsafe` syntax extensions are now deprecated. Will be removed in `0.3.0`. Just throw exceptions in the few places in client code where necessary.

### New Scala versions:

- 2.13 for JS platform

### Dependency upgrades:

- [pureharm-core-anomaly](https://github.com/pureharm-core/releases) `0.2.0`
- [pureharm-core-sprout](https://github.com/busymachines/pureharm-core/releases) `0.2.0`

Internals:

- [pureharm-testkit](https://github.com/busymachines/pureharm-testkit/releases) `0.2.0`

# 0.1.1

This is only a maintenance release.

- drop compile dependency on [`pureharm-effects-cats`](https://github.com/busymachines/pureharm-effects-cats/releases).
  If you pulled the dependency yourself via this module, then just add it manually

# 0.1.0

Split out from [pureharm](https://github.com/busymachines/pureharm) as of version `0.0.7`.

- upgrade to pureharm 0.1.0, which drops PhantomType support entirely.

Dependencies:

- [circe](https://github.com/circe/circe) `0.13.0`
- [pureharm-core-anomaly](https://github.com/pureharm-core/releases) `0.1.0`
- [pureharm-core-sprout](https://github.com/busymachines/pureharm-core/releases) `0.1.0`
- [pureharm-effects-cats](https://github.com/busymachines/pureharm-effects-cats/releases) `0.1.0`
