# pureharm-json-circe

See [changelog](./CHANGELOG.md).

## modules

- `"com.busymachines" %% s"json-circe" % "0.3.0"`. Which in turn depends on:
  - [circe](https://github.com/circe/circe) `0.14.1`
  - [pureharm-core-anomaly](https://github.com/pureharm-core/releases) `0.3.0`
  - [pureharm-core-sprout](https://github.com/busymachines/pureharm-core/releases) `0.3.0`

### Scala versions

- Scala 2.13: for JVM and JS
- Scala 3: for JVM and JS

## usage
Under construction. See [release notes](https://github.com/busymachines/pureharm-core/releases) and tests for examples.

### :warning: if you are upgrading from versions 0.2.0 or before

With circe `0.14.1` the method of deriving _sealed traits_ using the `_type` discriminator no longer exists _for Scala 3_. So in an effort to support Scala 3 this behavior is deprecated in Scala 2, and in Scala 3 it is stubbed out. Please make efforts in migrating to the usage of `busymachines.pureharm.json.GenericSemiAutoDerivation` xor its counterpart `busymachines.pureharm.json.derived`. The deprecation warnings will guide you. Note that this is non-trivial in case you were using the old method to derive _sealed trait_ hierarchies, otherwise the transition does not break anything.

## Copyright and License

All code is available to you under the Apache 2.0 license, available
at [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0) and also in
the [LICENSE](./LICENSE) file.
