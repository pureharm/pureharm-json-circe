# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

# unreleased

This is the first version available for Scala 3!

### :warning: breaking changes :warning:
- replaced `busymachines.pureharm.json.SemiAutoDerivation` w/ 
    - `busymachines.pureharm.json.GenericSemiAutoDerivation` which uses circe derivation strategies from `circe-generic` module
    - `busymachines.pureharm.json.GenericExtrasSemiAutoDerivation`, which is from start deprecated, and is equivalent to `SemiAutoDerivation`. Unfortunately, `circe-generic-extras` behavior that derived encoder/decoders for _sealed traits_ with the `_type` discriminator is _not available_ on Scala 3 at all! If you are on Scala 2 you can still use this method of deriving, but the deprecations will push you to _migrate_ to the behavior consistent w/ `GenericSemiAutoDerivation`. 
      - :warning: Remember that this changes the shape of the JSON for _sealed traits_, and if you are in a position where you have to provide support for this shape, you have to do it by hand. Helpers for this will be added in future versions.
      - :warning: this trait is a no-op `???` unimplemented stub on Scala 3!

### dependency upgrades
- [circe-generic](https://github.com/circe/circe/releases) `0.14.1`
- [circe-generic-extras](https://github.com/circe/circe-generic-extras/releases) `0.14.1` — only for Scala 2. For Scala 3 this doesn't exist!

### internals
- [pureharm-testkit](https://github.com/busymachines/pureharm-testkit/releases) `0.4.0`
- bump scalafmt to `3.0.0-RC6` — from `2.7.5`
- bump sbt to `1.5.5`
- bump sbt-spiewak to `0.21.0`
- bump sbt-scalafmt to `2.4.3`
- bump sbt-scalajs-crossproject to `1.1.0`
- bump sbt-scalajs to `1.6.0`

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
