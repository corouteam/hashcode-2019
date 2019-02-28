class OutputGenerator{
    companion object {

        fun generateOutput(slides: ArrayList<Slide>){
            val strings = ArrayList<String>()

            strings.add(slides.size.toString())
            slides.forEach {
                if (it.photo2 == null) {
                    strings.add("${it.photo1?.id}")
                } else {
                    strings.add("${it.photo1?.id} ${it.photo2?.id}")
                }
            }

            HappyParser.CreateFile("outputC.txt", strings)
        }
    }
}