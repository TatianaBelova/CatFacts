package com.example.catfacts.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.KSerializer
import org.kodein.db.model.Id
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*

@Serializable(with = CatModelSerializer::class)
data class CatModel(
    @SerializedName("_id")
    @Id val uid: String,
//    val id: String,
    val text: String,
) : java.io.Serializable

class CatModelSerializer : KSerializer<CatModel> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("CatModel") {
        element<String>("uid")
        element<String>("text")
    }

    override fun deserialize(decoder: Decoder): CatModel = decoder.decodeStructure(descriptor) {
        var uid = ""
        var text = ""

        while (true) {
            when (val index = decodeElementIndex(descriptor)) {
                0 -> uid = decodeStringElement(descriptor, 0)
                1 -> text = decodeStringElement(descriptor, 1)
                CompositeDecoder.DECODE_DONE -> break
                else -> error("Unexpected index: $index")
            }
        }
        CatModel(uid, text)
    }

    override fun serialize(encoder: Encoder, value: CatModel) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.uid)
            encodeStringElement(descriptor, 1, value.text)
        }
    }
}