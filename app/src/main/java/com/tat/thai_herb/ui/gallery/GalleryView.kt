package com.tat.thai_herb.ui.gallery

import com.tat.thai_herb.model.respone.ContentImage
import com.tat.thai_herb.utilty.BasePresenter

class GalleryView {
    interface View : BasePresenter {
        fun responeImage(part: List<ContentImage>)
    }
}