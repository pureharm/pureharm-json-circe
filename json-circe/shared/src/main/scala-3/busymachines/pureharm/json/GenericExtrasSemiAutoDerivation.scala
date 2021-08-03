package busymachines.pureharm.json

@scala.deprecated(
  "N.B.! On Scala 3 this is a simple stub w/ ??? implementations! This way of deriving is unsupported in circe 0.14.1. If you are using it for case classes then you can simply use busymachines.pureharm.json.GenericSemiAutoDerivation xor its counterpart busymachines.pureharm.json.derived"
)
trait GenericExtrasSemiAutoDerivation {

  @scala.deprecated(
    "In Scala 3 this method is a stub ???. Please migrate to busymachines.pureharm.json.derived — or mixin busymachines.pureharm.json.GenericSemiAutoDerivation",
    "0.3.0",
  )
  final def decoder[A]: Decoder[A] = ???

  @scala.deprecated(
    "In Scala 3 this method is a stub ???. Please migrate to busymachines.pureharm.json.derived — or mixin busymachines.pureharm.json.GenericSemiAutoDerivation",
    "0.3.0",
  )
  final def encoder[A]: Encoder.AsObject[A] = ???

  @scala.deprecated(
    "In Scala 3 this method is a stub ???. Please migrate to busymachines.pureharm.json.derived — or mixin busymachines.pureharm.json.GenericSemiAutoDerivation",
    "0.3.0",
  )
  final def codec[A]: Codec.AsObject[A] = ???

  @scala.deprecated(
    "In Scala 3 this method is a stub ???. Please migrate to busymachines.pureharm.json.derived — or mixin busymachines.pureharm.json.GenericSemiAutoDerivation",
    "0.3.0",
  )
  final def enumerationDecoder[A]: Decoder[A] = ???

  @scala.deprecated(
    "In Scala 3 this method is a stub ???. Please migrate to busymachines.pureharm.json.derived — or mixin busymachines.pureharm.json.GenericSemiAutoDerivation",
    "0.3.0",
  )
  final def enumerationEncoder[A]: Encoder[A] = ???

  @scala.deprecated(
    "In Scala 3 this method is a stub ???. Please migrate to busymachines.pureharm.json.derived — or mixin busymachines.pureharm.json.GenericSemiAutoDerivation",
    "0.3.0",
  )
  final def enumerationCodec[A]: Codec[A] = ???

}
