class Slide(var photo1: Photo?, var photo2: Photo?) {

    var peso: Int = 0
    var checked = false
    fun getTagsT(): HashSet<String>?{
        var finalTags = HashSet<String>()
        if(photo1?.tags != null){
            println(photo1?.tags)
            finalTags?.addAll(photo1!!.tags)
        }
        if(photo2?.tags != null){
            println(photo2?.tags)
            finalTags?.addAll(photo2!!.tags)
        }
        return finalTags

    }
    val tags: HashSet<String>?
        get() {
            var finalTags = HashSet<String>()

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
