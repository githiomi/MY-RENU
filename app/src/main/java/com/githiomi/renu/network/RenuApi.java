package com.githiomi.renu.network;

import com.githiomi.renu.models.Category;
import com.githiomi.renu.models.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RenuApi {

    @GET(Constants.CATEGORY_ROUTE)
        Call< List<Category> > getCategories();

}
