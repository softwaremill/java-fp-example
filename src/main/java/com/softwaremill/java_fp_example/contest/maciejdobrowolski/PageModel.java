package com.softwaremill.java_fp_example.contest.maciejdobrowolski;

import com.github.florent37.retrojsoup.annotations.Select;
import io.reactivex.Observable;

interface PageModel {

    @Select("head meta")
    Observable<MetaEntry> metaTags();

}
