class Slide(var photo1: Photo?, var photo2: Photo?) {

    var peso: Int = 0
    var checked = false

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
