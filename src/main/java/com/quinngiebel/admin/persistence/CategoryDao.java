package com.quinngiebel.admin.persistence;

import com.quinngiebel.admin.entities.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDao extends GenericDao<Category> {

    public CategoryDao() {
        this.setType(Category.class);
    }

    /**
     * Returns a JSON representation of the list of pieces.
     * @param resultList The list of pieces.
     * @return A JSON representation of the list
     */
    public String toJSON(List<Category> resultList) {
        return String.format("{\"categories\":[%s]}",
                resultList.stream().map(Category::toJSON).collect(Collectors.joining(",")));
    }
}
