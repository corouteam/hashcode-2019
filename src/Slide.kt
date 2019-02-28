class Slide {
    var photo1: Photo? = null
    var photo2: Photo? = null

    val tags: HashSet<String>?
        get() {
            var finalTags: HashSet<String>? = null

            if (isSingleSlide) {
                return photo1!!.tags
            } else {
                photo1?.tags?.forEach {
                    finalTags?.add(it)
                }

                photo2?.tags?.forEach {
                    finalTags?.add(it)
                }

                return finalTags
            }
        }

    val isSingleSlide: Boolean
        get() = photo2 == null
}
